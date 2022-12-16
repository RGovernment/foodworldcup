package FoodWorldCup;

public class Restaurant {
	private String Food;
	private String name;
	private float distance;
	private String address;
	private String callnumber;
	private float reviewrate;
	private String type;

	public String getFood() {
		return Food;
	}

	public void setFood(String food) {
		Food = food;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCallnumber() {
		return callnumber;
	}

	public void setCallnumber(String callnumber) {
		this.callnumber = callnumber;
	}

	public float getReviewrate() {
		return reviewrate;
	}

	public void setReviewrate(float reviewrate) {
		this.reviewrate = reviewrate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return String.format("%-8s\t%-12s\t%.2fkm\t%s\t%s\t%.2f\t%s", Food, name, distance,
				address, callnumber, reviewrate, type);
	}

}
