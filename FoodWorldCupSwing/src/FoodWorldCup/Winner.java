package FoodWorldCup;

public class Winner {
	private String food;
	private int count;

	public String getFood() {
		return food;
	}

	public int getCount() {
		return count;
	}

	public void win(String food) {
		this.food = food;
		count++;
	}

	public void morewin() {
		count++;
	}

}