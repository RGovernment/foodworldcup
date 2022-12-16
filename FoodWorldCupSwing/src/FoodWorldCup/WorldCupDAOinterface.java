package FoodWorldCup;

import java.util.ArrayList;

public interface WorldCupDAOinterface {
	Restaurant restaurant = new Restaurant();
	ArrayList<String> Foodlist = new ArrayList<>();
	ArrayList<String> NewFoodlist = new ArrayList<>();
	ArrayList<Restaurant> restaurantlist = new ArrayList<Restaurant>();

	public ArrayList<String> randompick(int num);

	public void worldcup(int num);

	public void printlist();

	public void printrestaurantinfo();
}