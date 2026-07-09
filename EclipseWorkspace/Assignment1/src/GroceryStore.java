import tester.*;

interface IItem {
	// returns the cents per gram of a given item
	double unitPrice();
	
	// returns true if the unit price is lower than a given unit price; otherwise returns false
	boolean lowerUnitPrice(double up);
	
	// returns true if an item's unit price is lower than a given item; otherwise returns false
	boolean cheaperThan(IItem that);
}

class Coffee implements IItem {
	String brand;
	int price; // in cents
	double weight; // in grams
	boolean decaf;
	
	Coffee(String brand, int price, double weight, boolean decaf) {
		this.brand = brand;
		this.price = price;
		this.weight = weight;
		this.decaf = decaf;
	}
	
  // returns the cents per gram of a given item
	public double unitPrice() {
		return price / weight;
	}
	
	// returns true if the unit price is lower than a given unit price; otherwise returns false
	public boolean lowerUnitPrice(double up) {
		return this.unitPrice() < up;
	}
	
	// returns true if an item's unit price is lower than a given item; otherwise returns false
	public boolean cheaperThan(IItem that) {
		return this.unitPrice() < that.unitPrice();
	}
}

class Juice implements IItem {
	String brand;
	int price; // in cents
	double weight; // in grams
	String type; // fresh, bottled, canned...etc
	
	Juice(String brand, int price, double weight, String type) {
		this.brand = brand;
		this.price = price;
		this.weight = weight;
		this.type = type;
	}
	
  // returns the cents per gram of a given item
	public double unitPrice() {
		return price / weight;
	}
	
	// returns true if the unit price is lower than a given unit price; otherwise returns false
	public boolean lowerUnitPrice(double up) {
		return this.unitPrice() < up;
	}
	
	// returns true if an item's unit price is lower than a given item; otherwise returns false
	public boolean cheaperThan(IItem that) {
		return this.unitPrice() < that.unitPrice();
	}
}

class IceCream implements IItem {
	String brand;
	int price; // in cents
	double weight; // in grams
	String flavor;
	
	IceCream(String brand, int price, double weight, String flavor) {
		this.brand = brand;
		this.price = price;
		this.weight = weight;
		this.flavor = flavor;
	}
	
  // returns the cents per gram of a given item
	public double unitPrice() {
		return price / weight;
	}
	
	// returns true if the unit price is lower than a given unit price; otherwise returns false
	public boolean lowerUnitPrice(double up) {
		return this.unitPrice() < up;
	}
	
	// returns true if an item's unit price is lower than a given item; otherwise returns false
	public boolean cheaperThan(IItem that) {
		return this.unitPrice() < that.unitPrice();
	}
}

class ExamplesIItem {
	ExamplesIItem() {
	}
	
	IItem intel = new Coffee("Intelligentsia", 1689, 340.2, false);
	IItem tropicana = new Juice("Tropicana", 827, 2632.0, "Bottled");
	IItem bnj = new IceCream("Ben & Jerry's", 519, 453.6, "Mint");
	
	// Tests
	boolean testUnitPrice(Tester t) {
		return t.checkInexact(intel.unitPrice(), 4.965, 0.01)
				&& t.checkInexact(tropicana.unitPrice(), 0.314, 0.01)
				&& t.checkInexact(bnj.unitPrice(), 1.144, 0.01);
	}
	
	boolean testLowerUnitPrice(Tester t) {
		return t.checkExpect(intel.lowerUnitPrice(5.0), true)
				&& t.checkExpect(tropicana.lowerUnitPrice(tropicana.unitPrice()), false)
				&& t.checkExpect(bnj.lowerUnitPrice(0.5), false);
	}
	
	boolean testCheaperThan(Tester t) {
		return t.checkExpect(intel.cheaperThan(bnj), false)
				&& t.checkExpect(tropicana.cheaperThan(tropicana), false)
				&& t.checkExpect(bnj.cheaperThan(intel), true);
	}
}