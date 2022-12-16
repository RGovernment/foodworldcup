package FoodWorldCup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class WorldCupDAO implements WorldCupDAOinterface {

	ArrayList<String> Foodlist = new ArrayList<>();
	ArrayList<String> NewFoodlist = new ArrayList<>();
	ArrayList<Winner> winnerlist = new ArrayList<>();
	WorldCupPrint wcprint = new WorldCupPrint();
	Random random;
	Restaurant r;
	Scanner sc;
	StringBuilder sb;
	Util util;

	public WorldCupDAO() {
		sc = new Scanner(System.in);
		sb = new StringBuilder();
		random = new Random();
		util = new Util();
		String address = null;
		String number = null;
		String f_name = " ";
		String f_type = null;
		String shop = null;
		int number2 = 0;
		ArrayList<Restaurant> rest1 = new ArrayList<>();
		String[] dmenu = { "김밥", "김치찌개", "삼겹살", "부대찌개", "불고기", "된장찌개", "라면", "삼계탕", "짜장면", "짜장밥", "짬뽕", "짬뽕밥", "볶음밥",
				"탕수육", "마라탕", "동파육", "돈카츠", "라멘", "스시", "우동", "돈부리", "벤또", "사시미", "타코야키", "스테이크", "파스타", "피자", "스프",
				"빠에야", "샌드위치", "감바스", "타코" };

		for (int i = 0; i < dmenu.length; i++) {
			for (int j = 0; j < 3; j++) {
				r = new Restaurant();
				random = new Random();
				// 음식
				f_name = dmenu[i];

				// 이름

				int ab = random.nextInt(4);
				if (ab == 0) {
					shop = "추억 " + dmenu[i] + "가게";
				} else if (ab == 1) {
					shop = "사랑 " + dmenu[i] + "가게";
				} else if (ab == 2) {
					shop = "소망 " + dmenu[i] + "가게";
				} else if (ab == 3) {
					shop = "행복 " + dmenu[i] + "가게";
				}

				// 거리
				double a = Math.random() * (4 - 0.5) + 0.5;

				// 주소
				int abc = random.nextInt(4);
				if (abc == 0) {
					address = "문학동";
				} else if (abc == 1) {
					address = "학익2동";
				} else if (abc == 2) {
					address = "주안8동";
				} else if (abc == 3) {
					address = "구월3동";
				}

				// 핸드폰 번호
				number2 = (int) (Math.random() * (99999999 - 10000001)) + 10000001;
				number = Integer.toString(number2);
				String cut_n = number.substring(4, 8);
				String cut_n2 = number.substring(0, 4);
				sb.append("010");
				sb.append("-");
				sb.append(cut_n2);
				sb.append("-");
				sb.append(cut_n);
				number = sb.toString();
				sb.setLength(0);

				// 별점
				float b = (float) (Math.random() * (5 - 3)) + 3;

				// 타입
				String[] type = { "한식", "중식", "일식", "양식" };
				f_type = type[i / 8];

				r.setFood(f_name);
				r.setName(shop);
				r.setDistance((float) a);
				r.setAddress(address);
				r.setCallnumber(number);
				r.setReviewrate(b);
				r.setType(f_type);
				rest1.add(r);

			}
		}
		List<Restaurant> rest2 = util.deduplication(rest1, Restaurant::getName);
		restaurantlist.addAll(rest2);
		Collections.shuffle(restaurantlist);
	}

	public void list() {
		for (int i = 0; i < restaurantlist.size(); i++) {
			Foodlist.add(restaurantlist.get(i).getFood());
		}
		HashSet<String> hash = new HashSet<String>(Foodlist);
		Foodlist.removeAll(Foodlist);
		Foodlist.addAll(hash);
	}

	@Override
	public ArrayList<String> randompick(int num) {
		ArrayList<String> TempFoodlist = new ArrayList<String>();
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			int index = random.nextInt(Foodlist.size());
			TempFoodlist.add(Foodlist.get(index));
			Foodlist.remove(index);
		}
		return TempFoodlist;
	}

	@Override
	public void worldcup(int num) {
		NewFoodlist.removeAll(NewFoodlist);
		Foodlist = randompick(num);
		int size = Foodlist.size();
		for (int i = 1; i <= size / 2; i++) {
			ArrayList<String> TempFoodlist = randompick(2);
			wcprint.worldcup(TempFoodlist.get(0), TempFoodlist.get(1), num);
			boolean run = true;
			while (run) {
				int choice = wcprint.getchoice();
				if (choice != 0) {
					switch (choice) {
					case 1:
						NewFoodlist.add(TempFoodlist.get(0));
						run = false;
						break;
					case 2:
						NewFoodlist.add(TempFoodlist.get(1));
						run = false;
						break;
					}
				}
				try {
					TimeUnit.MILLISECONDS.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		if (NewFoodlist.size() == 1) {
			wcprint.win(NewFoodlist.get(0));
			System.out.printf("%s 우승\n", NewFoodlist.get(0));
			System.out.printf("%-8s\t%-12s\t\t%s\t%s\t%s\t%s\t%s\n", "음식", "상호", "거리", "주소", "전화번호", "별점", "분류");
			for (Restaurant rlist : restaurantlist) {
				if (rlist.toString().contains(NewFoodlist.get(0))) {
					System.out.println(rlist.toString());
				}
			}
			boolean ck = false;
			for (Winner w : winnerlist) {
				if (w.getFood().equals(NewFoodlist.get(0))) {
					w.morewin();
					ck = true;
				}
			}

			Winner winner = new Winner();
			if (!ck) {
				winner.win(NewFoodlist.get(0));
				winnerlist.add(winner);
			}
		} else

		{

			for (String s : NewFoodlist) {
				Foodlist.add(s);
			}

			worldcup(NewFoodlist.size());
		}

		System.out.println();

	}

	public void winnerlist() {
		int y = 0;
		int count = 1;
		int total = 0;
		sort();
		for (Winner w : winnerlist) {
			total += w.getCount();
		}
		System.out.println("랭킹순위\t음식\t\t승리횟수\t승률(%)");
		for (int i = 0; i < winnerlist.size(); i++) {
			y++;
			if (i - 1 > -1) {

				if (winnerlist.get(i).getCount() != winnerlist.get(i - 1).getCount()) {
					count = y;
				}
			}
			System.out.printf("%d등\t\t%-8s\t%-8d\t%-8.2f\n", count, winnerlist.get(i).getFood(),
					winnerlist.get(i).getCount(), winnerlist.get(i).getCount() / ((float) total) * 100);
		}
	}

	public void sort() {
		Collections.sort(winnerlist, new Comparator<Winner>() {
			@Override
			public int compare(Winner o1, Winner o2) {
				if (o1.getCount() > o2.getCount()) {
					return -1;
				} else if (o1.getCount() < o2.getCount()) {
					return 1;
				} else {
					return 0;
				}
			}
		});
	}

	@Override
	public void printlist() {
		boolean run = true;
		while (run) {
			int menu = util.menu_ck("한식", "중식", "일식", "양식", "오늘의 추천", "가게 상세정보", "뒤로가기");
			switch (menu) {
			case 1:
				typecheck("한식");
				break;
			case 2:
				typecheck("중식");
				break;
			case 3:
				typecheck("일식");
				break;
			case 4:
				typecheck("양식");
				break;

			case 5:
				System.out.println("오늘의 추천맛집");
				if (winnerlist.size() > 0) {
					try {
						for (int i = 0; i < 3; i++) {
							System.out.printf("%d위 [ %s ]\n", i + 1, winnerlist.get(i).getFood());
							for (int j = 0; j < restaurantlist.size(); j++) {
								if (winnerlist.get(i).getFood().equals(restaurantlist.get(j).getFood())) {
									System.out.println(restaurantlist.get(j).getName());
								}
							}
						}
					} catch (IndexOutOfBoundsException e) {
						break;
					}
				} else {
					System.out.println("데이터 수집이 부족합니다.");
				}
				System.out.println();
				break;

			case 6:
				System.out.println("가게 상세정보");
				printrestaurantinfo();
				break;

			case 7:
				run = false;
				break;
			default:
				break;
			}
		}

	}

	public void typecheck(String a) {
		ArrayList<String> foodlist = new ArrayList<>();
		for (Restaurant res : restaurantlist) {
			if (res.getType().equals(a)) {
				foodlist.add(res.getFood());
			}
		}
		HashSet<String> list = new HashSet<String>(foodlist);
		for (String li : list) {
			System.out.println("[" + li + "]");
		}
	}

	public void winnerfood(String str) {
		String food = str;
		System.out.printf("%-8s\t%-12s\t\t%s\t%s\t%s\t%s\t%s\n", "음식", "상호", "거리", "주소", "전화번호", "별점", "분류");
		for (Restaurant rl : restaurantlist) {
			if (food.contains(rl.getFood())) {
				System.out.println(rl.toString());
			}
		}
	}

	public void insert() {
		r = new Restaurant();
		r.setFood(util.str_ck("음식"));
		r.setName(util.str_ck("상호"));
		r.setAddress(util.str_ck("주소"));
		while (true) {
			String pattern = "^\\d{3}-\\d{3,4}-\\d{4}$";
			String str = util.str_ck("전화번호");
			if (Pattern.matches(pattern, str)) {
				r.setCallnumber(str);
				break;
			} else {
				System.out.println("휴대전화 형식 아닙니다.(형식 예시 : |000-000-0000 or 000-0000-0000|");
			}
		}
		r.setDistance(util.float_point(util.float_ck(4, "거리 /최대 입력 가능거리 : 4km"), 2));
		r.setReviewrate(util.float_point(util.float_ck(5, "별점 / 최대 입력 가능별점 : 5점"), 2));
		r.setType(util.str_ck("분류"));
		restaurantlist.add(r);
	}

	@Override
	public void printrestaurantinfo() {
		int a = util.menu_ck("키워드 검색", "단일 검색", "뒤로가기");
		if (a == 1) {
			String food = util.str_ck("음식이름");
			System.out.printf("%-8s\t%-12s\t\t%s\t%s\t%s\t%s\t%s\n", "음식", "상호", "거리", "주소", "전화번호", "별점", "분류");
			for (Restaurant rl : restaurantlist) {
				if (rl.getFood().contains(food)) {
					System.out.println(rl.toString());
				}
			}
		} else if (a == 2) {
			String food = util.str_ck("음식이름");
			System.out.printf("%-8s\t%-12s\t\t%s\t%s\t%s\t%s\t%s\n", "음식", "상호", "거리", "주소", "전화번호", "별점", "분류");
			for (Restaurant rl : restaurantlist) {
				if (food.equals(rl.getFood())) {
					System.out.println(rl.toString());
				}
			}
		} else if (a == 3) {
			return;
		}

	}

}
