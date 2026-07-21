import tester.*;

// to represent a pet owner
class Owner {
	String name;
	IPet pet;
	int age;

	Owner(String name, IPet pet, int age) {
		this.name = name;
		this.pet = pet;
		this.age = age;
	}
	
	//is this Owner older than the given Owner?
	boolean isOlder(Owner other) {
		return this.age > other.age;
	}
	
	//does the name of this person's pet match the given name?
	boolean sameNamePet(String name) {
		return this.pet.sameName(name);
	}
	
	//returns the owner after their pet has perished
	Owner perish() {
		return new Owner(this.name, new NoPet(), this.age);
	}
}

// to represent a pet
interface IPet {
  //does the name of this pet match the given name?
	boolean sameName(String name);
}

// to represent a pet cat
class Cat implements IPet {
	String name;
	String kind;
	boolean longhaired;

	Cat(String name, String kind, boolean longhaired) {
		this.name = name;
		this.kind = kind;
		this.longhaired = longhaired;
	}
	
  //does the name of this pet match the given name?
	public boolean sameName(String name) {
		return this.name.equals(name);
	}
}

// to represent a pet dog
class Dog implements IPet {
	String name;
	String kind;
	boolean male;

	Dog(String name, String kind, boolean male) {
		this.name = name;
		this.kind = kind;
		this.male = male;
	}
	
  //does the name of this pet match the given name?
	public boolean sameName(String name) {
		return this.name.equals(name);
	}
}

class NoPet implements IPet {
	NoPet() {
	}
	public boolean sameName(String name) {
		return false;
	}
}

class ExamplesOwner {
	ExamplesOwner() {
	}
	
	IPet alan = new Cat("Alan", "Persian", true);
	IPet turing = new Cat("Turing", "British Shorthair", false);
	IPet woz = new Dog("Woz", "Pomeranian", true);
	IPet ada = new Dog("Ada", "Terrier", false);
	IPet none = new NoPet();
	
	Owner jeb = new Owner("Jeb", this.alan, 28);
	Owner bob = new Owner("Bob", this.turing, 24);
	Owner bill = new Owner("Bill", this.woz, 31);
	Owner paul = new Owner("Paul", this.ada, 23);
	Owner val = new Owner("Valentina", this.none, 32);
	
	// Tests
	boolean testIsOlder(Tester t) {
		return t.checkExpect(this.jeb.isOlder(this.bob), true)
				&& t.checkExpect(this.paul.isOlder(this.bill), false);
	}
	boolean testSameNamePet(Tester t) {
		return t.checkExpect(this.jeb.sameNamePet("Alan"), true)
				&& t.checkExpect(this.bob.sameNamePet("Steve"), false)
				&& t.checkExpect(this.bill.sameNamePet("Woz"), true)
				&& t.checkExpect(this.paul.sameNamePet("Lovelace"), false)
				&& t.checkExpect(this.val.sameNamePet("Katy"), false);
	}
	boolean testPerish(Tester t) {
		return t.checkExpect(this.jeb.perish(), new Owner("Jeb", this.none, 28))
				&& t.checkExpect(this.val.perish(),this.val);
	}
}