package FoodWorldCup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WorldCupDAOPrint implements WorldCupDAOinterface {
	ArrayList<String> NewFoodlist = new ArrayList<>();
	ArrayList<String> Foodlist = new ArrayList<String>();
	ArrayList<Winner> winnerlist = new ArrayList<Winner>();
	WorldCupPrint wcprint = new WorldCupPrint();
	Util util = new Util();

	@Override
	public ArrayList<String> randompick(int num) {
		ArrayList<String> TempFoodlist = new ArrayList<String>();
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			try {
				int index = random.nextInt(Foodlist.size());
				TempFoodlist.add(Foodlist.get(index));
				Foodlist.remove(index);
			} catch (Exception e) {

			}
		}
		return TempFoodlist;
	}

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
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		if (NewFoodlist.size() == 1) {
			wcprint.win(NewFoodlist.get(0));
			System.out.printf("%s 우승\n", NewFoodlist.get(0));
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
		} else {
			for (String s : NewFoodlist) {
				Foodlist.add(s);
			}
			worldcup(NewFoodlist.size());
		}
	}

	public void winnerlist() {
		int total = 0;
		sort();
		for (Winner w : winnerlist) {
			total += w.getCount();
		}
		System.out.println("음식\t\t승리횟수\t승률");
		for (Winner w : winnerlist) {
			System.out.printf("%-8s\t%-8d\t%-8.2f\n", w.getFood(), w.getCount(), w.getCount() / ((float) total));
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
		// TODO Auto-generated method stub

	}

	@Override
	public void printrestaurantinfo() {
		// TODO Auto-generated method stub

	}

	public void setting() {
		String[] dmenu = { "김밥", "김치찌개", "삼겹살", "부대찌개", "불고기", "된장찌개", "라면", "삼계탕", "짜장면", "짜장밥", "짬뽕", "짬뽕밥", "볶음밥",
				"탕수육", "마라탕", "동파육", "돈카츠", "라멘", "스시", "우동", "돈부리", "벤또", "사시미", "타코야키", "스테이크", "파스타", "피자", "스프",
				"빠에야", "샌드위치", "감바스", "타코" };
		Foodlist.removeAll(Foodlist);
		for (String s : dmenu) {
			Foodlist.add(s);
		}
	}


}
