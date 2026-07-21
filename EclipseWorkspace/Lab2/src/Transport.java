import tester.*;

//Represents a mode of transportation
interface IMOT {
	//returns true if this mode of transportation is at least
	//as efficient as the given mpg, false otherwise
	boolean isMoreFuelEfficientThan(int mpg);
	
	//returns true if this mode of transportation is at least
	//as efficient as the given mot, false otherwise
	boolean isMoreFuelEfficientThan(IMOT mot);
	
	// returns given mpg if there is one;
	// returns -1 if not
	int getMpg();
}

//Represents a bicycle as a mode of transportation
class Bicycle implements IMOT {
	String brand;

	Bicycle(String brand) {
		this.brand = brand;
	}
	
	//returns true if this mode of transportation is at least
	//as efficient as the given mpg, false otherwise
	public boolean isMoreFuelEfficientThan(int mpg) {
		return true;
	}
	
	//returns true if this mode of transportation is at least
	//as efficient as the given mot, false otherwise
	public boolean isMoreFuelEfficientThan(IMOT mot) {
		return true;
	}
	
	// returns given mpg if there is one;
	// returns -1 if not
	public int getMpg() {
		return -1;
	}
}

//Represents a car as a mode of transportation
class Car implements IMOT {
	String make;
	int mpg; // represents the fuel efficiency in miles per gallon

	Car(String make, int mpg) {
		this.make = make;
		this.mpg = mpg;
	}
	
	//returns true if this mode of transportation is at least
	//as efficient as the given mpg, false otherwise
	public boolean isMoreFuelEfficientThan(int mpg) {
		return this.mpg >= mpg;
	}
	
	// returns given mpg if there is one;
	// returns -1 if not
	public int getMpg() {
		return this.mpg;
	}
	
	//returns true if this mode of transportation is at least
	//as efficient as the given mot, false otherwise
	public boolean isMoreFuelEfficientThan(IMOT mot) {
		if ((mot.getMpg() == -1) == false && this.mpg >= mot.getMpg()) {
			return true;
		}
		else {
			return false;
		}
	}
}

//Keeps track of how a person is transported
class Person {
	String name;
	IMOT mot;

	Person(String name, IMOT mot) {
		this.name = name;
		this.mot = mot;
	}
	
  //Does this person's mode of transportation meet the given fuel
  //efficiency target (in miles per gallon)?
	boolean motMeetsFuelEfficiency(int mpg) {
		return this.mot.isMoreFuelEfficientThan(mpg);
	}
	
	boolean motIsMoreFuelEfficientThan(IMOT mot) {
		return this.mot.isMoreFuelEfficientThan(mot);
	}
}

class ExamplesPerson {
  IMOT diamondback = new Bicycle("Diamondback");
  IMOT toyota = new Car("Toyota", 30);
  IMOT lamborghini = new Car("Lamborghini", 17);
 
  Person bob = new Person("Bob", diamondback);
  Person ben = new Person("Ben", toyota);
  Person becca = new Person("Becca", lamborghini);
  
  //Tests
  boolean testMotMeetsFuelEfficiency(Tester t) {
  	return t.checkExpect(this.bob.motMeetsFuelEfficiency(15), true)
  			&& t.checkExpect(this.ben.motMeetsFuelEfficiency(15), true)
  			&& t.checkExpect(this.becca.motMeetsFuelEfficiency(15), true)
  			&& t.checkExpect(this.bob.motMeetsFuelEfficiency(25), true)
  			&& t.checkExpect(this.ben.motMeetsFuelEfficiency(25), true)
  			&& t.checkExpect(this.becca.motMeetsFuelEfficiency(25), false)
  			&& t.checkExpect(this.bob.motIsMoreFuelEfficientThan(this.toyota), true)
  			&& t.checkExpect(this.ben.motIsMoreFuelEfficientThan(this.diamondback), false)
  			&& t.checkExpect(this.ben.motIsMoreFuelEfficientThan(this.lamborghini), true)
  			&& t.checkExpect(this.becca.motIsMoreFuelEfficientThan(this.toyota), false);
  }
}