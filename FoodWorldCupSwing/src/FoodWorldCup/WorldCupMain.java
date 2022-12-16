package FoodWorldCup;

public class WorldCupMain {

	public static void main(String[] args) {

		WorldCupDAO cupDAO = new WorldCupDAO();
		Util util = new Util();
		boolean run = true;

		System.out.println("┌───────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│                                                                     │     ??????          │");
		System.out.println("│        ────      │            ┌─────┐  │   ┌─────┐   │     ───────  │    ?      ?         │");
		System.out.println("│      /      \\    │            │     │  │   │     │ ──┤          /   │          ?          │");
		System.out.println("│      \\      /    └────────    └─────┘  │   └─────┘   │         /\\   │        ?            │");
		System.out.println("│        ────     ───────────   ───┬───  │             │        /  \\  │        ?            │");
		System.out.println("│          │        ───────┐       │  ───┤      ───────        /    \\ │        ?            │");
		System.out.println("│      ────┴────    ┌──────┘       │     │            /               │                     │");
		System.out.println("│                   └───────                         /                         ?            │");
		System.out.println("│                                                                                           │");
		System.out.println("└───────────────────────────────────             ┌──────────────────────────────────────────┘");
		System.out.println("                                    \\            │");
		System.out.println("                                     \\           │");
		System.out.println("                                      \\          │");
		System.out.println("                                       \\         │");
		System.out.println("                                        \\        │");
		System.out.println("                                         \\       │");
		System.out.println("                                          \\      │");
		System.out.println("                                           \\     │");
		System.out.println("                                            \\    │");
		System.out.println("                                             \\   │");
		System.out.println("                                              \\  │");
		System.out.println("메뉴를 골라주세요.");
		while (run) {
			switch (util.menu_ck("푸드월드컵", "맛집리스트", "가게등록", "종료")) {
			case 1:
				cupDAO.list();
				int a = util.menu_ck("32강", "16강", "랭킹", "뒤로가기");
				int b = 0;
				if (a == 1) {
					b = 32;
				} else if (a == 2) {
					b = 16;
				} else if (a == 3) {
					cupDAO.winnerlist();
					break;
				} else if (a == 4) {
					break;
				}
				cupDAO.worldcup(b);
				break;

			case 2:
				cupDAO.printlist();
				break;
			case 3:
				cupDAO.insert();
				break;
			case 4:
				run = false;
				break;
			default:
				System.out.println("해당하는 메뉴는 없습니다.");
				break;
			}
		}
	}

}
