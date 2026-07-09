import tester.*;

// To represent a car
class Automobile {
	String model;
	int price; // in dollars
	double mileage; // in miles per gallon
	boolean used;
	
	Automobile(String model, int price, double mileage, boolean used) {
		this.model = model;
		this.price = price;
		this.mileage = mileage;
		this.used = used;
	}
}

// To represent examples of Automobile with it's model,
// price (in dollars), mpg, and if it is used or not
class ExamplesAutomobile {  
	ExamplesAutomobile() {
	}
	
	Automobile corolla = new Automobile("Toyota Corolla", 23125, 35, false);
	Automobile focus = new Automobile("Ford Focus", 3495, 25, true);
	Automobile california = new Automobile("Ferrari California", 115064, 19, true);
}