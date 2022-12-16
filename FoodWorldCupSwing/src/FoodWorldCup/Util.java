package FoodWorldCup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Util {
	Scanner sc = new Scanner(System.in);

	public int menu_ck(String... string) {
		while (true) {
			for (int i = 0; i < string.length; i++) {
				System.out.printf("%d. %-8s\t", i + 1, string[i]);
			}
			System.out.println();
			int num = int_ck();
			if (num <= string.length && num >= 1) {
				return num;
			} else {
				System.out.println("메뉴번호를 확인하세요.");
			}
		}
	}

	public int int_ck() {
		while (true) {
			try {
				int num = sc.nextInt();
				sc.nextLine();
				return num;
			} catch (Exception e) {
				System.out.println("숫자만 쓰세요");
				sc.nextLine();
			}

		}
	}

	public int int_ck(String str) {
		int num = 0;
		while (true) {
			System.out.printf("%s : ", str);
			if (sc.hasNextInt()) {
				num = sc.nextInt();
				sc.nextLine();
				return num;
			} else {
				System.out.println("숫자만 입력가능합니다.");
				sc.nextLine();
			}

		}
	}

	public float float_ck() {
		float num = 0;
		while (true) {
			if (sc.hasNextFloat()) {
				num = sc.nextFloat();
				sc.nextLine();
				return num;
			} else {
				System.out.println("숫자만 입력가능합니다.");
				sc.nextLine();
			}

		}
	}

	public float float_ck(String str) {
		System.out.printf("%s : ", str);
		float num = float_ck();
		return num;
	}

	public float float_point(float a, int b) {
		while (true) {
			String f1 = "[0-9]*\\.[0-9]{" + b + "}";
			Pattern p = Pattern.compile(f1);
			if (p.matcher(String.format("%.2f", a)).matches()) {
				return a;
			}
		}

	}

	public float float_ck(float f, String str) {
		while (true) {
			System.out.printf("%s : ", str);
			float num = float_ck();
			if (num > 0 && num <= f) {
				return num;
			} else {
				System.out.println("범위를 확인해주세요.");
			}
		}
	}

	public String str_ck(String str) {
		while (true) {

			System.out.printf("%s : ", str);
			String name = sc.nextLine();
			if (name != "") {
				return name;
			} else {
				System.out.println("공백은 입력 불가합니다.");
			}

		}
	}

	public <T> List<T> deduplication(final ArrayList<T> list, Function<? super T, ?> key) {
		return list.stream().filter(deduplication(key)).collect(Collectors.toList());
	}

	public static <T> Predicate<T> deduplication(Function<? super T, ?> key) {
		final Set<Object> set = ConcurrentHashMap.newKeySet();
		return predicate -> set.add(key.apply(predicate));
	}

}