import tester.*;

interface IAT {
  //To compute the number of known ancestors of this ancestor tree
  //(excluding this ancestor tree itself)
	int count();
	
	//To compute the number of known ancestors of this Person (*including* this Person itself)
	int countHelp();

  //To compute how many ancestors of this ancestor tree (excluding this
  //ancestor tree itself) are women older than 40 (in the current year)?
	int femaleAncOver40();
	
  //To compute how many ancestors of this ancestor tree (*including* this
  //ancestor tree itself) are women older than 40 (in the current year)?
	int femaleAncOver40Help();
	
	//To compute the number of known partial generations of this ancestor tree
	int numPartialGens();
	
  //To compute the number of known full generations of this ancestor tree
	int numTotalGens();
	

  //To compute whether this ancestor tree is well-formed: are all known
  //people younger than their parents?
	boolean wellFormed();
	
	//To determine if this ancestry tree is born in or before the given year
	boolean wellFormedHelp(int yob);
	
	//To return the younger of this ancestor tree and the given ancestor tree
	IAT youngerIAT(IAT other);
	
	//To return either this ancestor tree (if this ancestor tree is younger
	//than the given yob) or the given ancestry tree
	IAT youngerIATHelp(IAT other, int otherYob);
	
  //To compute the names of all the known ancestors in this ancestor tree
  //(including this ancestor tree itself)
	ILoString ancNames();
	
	//To compute the youngest ancestor in the given generation of this ancestry tree
	IAT youngestAncInGen(int gen);

  //To compute this ancestor tree's youngest grandparent
	IAT youngestGrandparent();
}

class Unknown implements IAT {
	Unknown() {
	}
	
  //To compute the number of known ancestors of this Unknown (excluding this Unknown itself)
	public int count() {
		return 0;
	}
	
  //To compute the number of known ancestors of this Person (*including* this Person itself)
	public int countHelp() {
		return 0;
	}
	
	//To compute how many ancestors of this ancestor tree (excluding this
  //ancestor tree itself) are women older than 40 (in the current year)?
	public int femaleAncOver40() {
		return 0;
	}
	
  //To compute how many ancestors of this ancestor tree (*including* this
  //ancestor tree itself) are women older than 40 (in the current year)?
	public int femaleAncOver40Help() {
		return 0;
	}
	
	//To compute the number of known partial generations of this ancestor tree
	public int numPartialGens() {
		return 0;
	}
	
  //To compute the number of known full generations of this ancestor tree
	public int numTotalGens() {
		return 0;
	}
	
  //To determine if this Unknown is well-formed
  public boolean wellFormed() {
  	return true;
  }
  
  //To determine if this ancestry tree is born in or before the given year
	public boolean wellFormedHelp(int yob) {
		return true;
	}
	
  //To compute the names of all the known ancestors in this ancestor tree
  //(including this ancestor tree itself)
	/*public ILoString ancNames() {
		return new MtLoString();
	}*/
	
	//To return the younger of this ancestor tree and the given ancestor tree
	public IAT youngerIAT(IAT other) {
		return other;
	}
	
	//To return either this ancestor tree (if this ancestor tree is younger
	//than the given yob) or the given ancestry tree
	public IAT youngerIATHelp(IAT other, int otherYob) {
		return other;
	}
	
	//To compute the youngest ancestor in the given generation of this ancestry tree
	public IAT youngestAncInGen(int gen) {
		return new Unknown();
	}
	
  //To compute this ancestor tree's youngest grandparent
	public IAT youngestGrandparent() {
		return new Unknown();
	}
	
  //To compute the names of all the known ancestors in this ancestor tree
  //(including this ancestor tree itself)
	public ILoString ancNames() {
		return new MtLoString();
	}
}

class Person implements IAT {
	String name;
	int yob;
	boolean isMale;
	IAT mom;
	IAT dad;

	Person(String name, int yob, boolean isMale, IAT mom, IAT dad) {
		this.name = name;
		this.yob = yob;
		this.isMale = isMale;
		this.mom = mom;
		this.dad = dad;
	}
	
	//To compute the number of known ancestors of this Person (excluding this Person itself)
	public int count() {
		return this.mom.countHelp() + this.dad.countHelp();
	}
	
  //To compute the number of known ancestors of this Person (*including* this Person itself)
	public int countHelp() {
		return 1 + this.mom.countHelp() + this.dad.countHelp();
	}
	
	//To compute how many ancestors of this ancestor tree (excluding this
  //ancestor tree itself) are women older than 40 (in the current year)?
	public int femaleAncOver40() {
		return this.mom.femaleAncOver40Help() + this.dad.femaleAncOver40Help();
	}
	
  //To compute how many ancestors of this ancestor tree (*including* this
  //ancestor tree itself) are women older than 40 (in the current year)?
	public int femaleAncOver40Help() {
		if(!this.isMale && 2015 - this.yob > 40) {
			return 1 + this.femaleAncOver40();
		}
		else {
			return this.femaleAncOver40();
		}
	}
	
	//To compute the number of known partial generations of this ancestor tree
	public int numPartialGens() {
		if (this.mom.numPartialGens() > this.dad.numPartialGens()) {
			return 1 + this.mom.numPartialGens();
		}
		else {
			return 1 + this.dad.numPartialGens();
		}
	}
	
  //To compute the number of known full generations of this ancestor tree
	public int numTotalGens() {
		if (this.mom.numTotalGens() < this.dad.numTotalGens()) {
			return 1 + this.mom.numTotalGens();
		}
		else {
			return 1 + this.dad.numTotalGens();
		}
	}
  //To determine if this Unknown is well-formed
  public boolean wellFormed() {
  	return this.mom.wellFormedHelp(this.yob)
  			&& this.dad.wellFormedHelp(this.yob);
  }
  
  //To determine if this ancestry tree is born in or before the given year
	public boolean wellFormedHelp(int yob) {
		return this.yob <= yob
				&& this.mom.wellFormedHelp(this.yob)
				&& this.dad.wellFormedHelp(this.yob);
	}
	
  //To compute the names of all the known ancestors in this ancestor tree
  //(including this ancestor tree itself)
	public ILoString ancNames() {
		return new ConsLoString(this.name, new MtLoString())
				.append(this.mom.ancNames())
				.append(this.dad.ancNames());
						
	}
	
  //To return the younger of this Person and the given ancestor tree
	public IAT youngerIAT(IAT other) {
		return other.youngerIATHelp(this, this.yob);
	}
	
	//To return either this ancestor tree (if this ancestor tree is younger
	//than the given yob) or the given ancestry tree
	public IAT youngerIATHelp(IAT other, int otherYob) {
		if (this.yob > otherYob) {
			return this;
		}
		else {
			return other;
		}
	}
	
	//To compute the youngest ancestor in the given generation of this ancestry tree
	public IAT youngestAncInGen(int gen) {
		if (gen <= 0) {
			return this;
		}
		else {
			return this.mom.youngestAncInGen(gen - 1).youngerIAT(this.dad.youngestAncInGen(gen - 1));
		}
	}
	
  //To compute this ancestor tree's youngest grandparent
	public IAT youngestGrandparent() {
		return this.youngestAncInGen(2);
	}
}

interface ILoString {
	//To compute a list of strings appended on another
	ILoString append(ILoString that);
	
  //To compute an appended list via accumulation
	//ILoString appendHelp(ILoString that);
}

class ConsLoString implements ILoString {
	String first;
	ILoString rest;

	ConsLoString(String first, ILoString rest) {
		this.first = first;
		this.rest = rest;
	}
	
	//To compute a list of strings appended on another
	public ILoString append(ILoString that) {
		return new ConsLoString(this.first, this.rest.append(that));
	}
	
	/*
	public ILoString append(ILoString that) {
	  return that.appendHelp(this);
	}
	
  //To compute an appended list via accumulation
	public ILoString appendHelp(ILoString that) {
	  return this.rest.appendHelp(new ConsLoString(this.first, that));
	}
	*/
}

class MtLoString implements ILoString {
	MtLoString() {
	}
	
	//To compute a list of strings appended on another
	public ILoString append(ILoString that) {
		return that;
	}
	
	/*
  //To compute an appended list via accumulation
	public ILoString appendHelp(ILoString that) {
    return that;
  }
  */
}

class ExamplesIAT {
	IAT unk = new Unknown();
	
  IAT enid = new Person("Enid", 1904, false, new Unknown(), new Unknown());
  IAT edward = new Person("Edward", 1902, true, new Unknown(), new Unknown());
  IAT emma = new Person("Emma", 1906, false, new Unknown(), new Unknown());
  IAT eustace = new Person("Eustace", 1907, true, new Unknown(), new Unknown());

  IAT david = new Person("David", 1925, true, new Unknown(), this.edward);
  IAT daisy = new Person("Daisy", 1927, false, new Unknown(), new Unknown());
  IAT dana = new Person("Dana", 1933, false, new Unknown(), new Unknown());
  IAT darcy = new Person("Darcy", 1930, false, this.emma, this.eustace);
  IAT darren = new Person("Darren", 1935, true, this.enid, new Unknown());
  IAT dixon = new Person("Dixon", 1936, true, new Unknown(), new Unknown());

  IAT clyde = new Person("Clyde", 1955, true, this.daisy, this.david);
  IAT candace = new Person("Candace", 1960, false, this.dana, this.darren);
  IAT cameron = new Person("Cameron", 1959, true, new Unknown(), this.dixon);
  IAT claire = new Person("Claire", 1956, false, this.darcy, new Unknown());

  IAT bill = new Person("Bill", 1980, true, this.candace, this.clyde);
  IAT bree = new Person("Bree", 1981, false, this.claire, this.cameron);

  IAT andrew = new Person("Andrew", 2001, true, this.bree, this.bill);
  
  ILoString mt = new MtLoString();
  ILoString string0 = new ConsLoString("Apple", this.mt);
  ILoString string1 = new ConsLoString("Banana", new ConsLoString("Carrot", this.mt));
  ILoString string2 = new ConsLoString("Orange",
  		new ConsLoString("Pear",
  				new ConsLoString("Lemon", this.mt)));
  
  boolean testCount(Tester t) {
      return
          t.checkExpect(this.andrew.count(), 16) &&
          t.checkExpect(this.david.count(), 1) &&
          t.checkExpect(this.enid.count(), 0) &&
          t.checkExpect(new Unknown().count(), 0);
  }
  boolean testFemaleAncOver40(Tester t) {
      return
          t.checkExpect(this.andrew.femaleAncOver40(), 7) &&
          t.checkExpect(this.bree.femaleAncOver40(), 3) &&
          t.checkExpect(this.darcy.femaleAncOver40(), 1) &&
          t.checkExpect(this.enid.femaleAncOver40(), 0) &&
          t.checkExpect(new Unknown().femaleAncOver40(), 0);
  }
  boolean testNumGens(Tester t) {
    return
        t.checkExpect(this.andrew.numTotalGens(), 3) &&
        t.checkExpect(this.andrew.numPartialGens(), 5) &&
        t.checkExpect(this.enid.numTotalGens(), 1) &&
        t.checkExpect(this.enid.numPartialGens(), 1) &&
        t.checkExpect(new Unknown().numTotalGens(), 0) &&
        t.checkExpect(new Unknown().numPartialGens(), 0) &&
        t.checkExpect(this.bill.numTotalGens(), 3);
  }
  boolean testWellFormed(Tester t) {
      return
          t.checkExpect(this.andrew.wellFormed(), true) &&
          t.checkExpect(new Unknown().wellFormed(), true) &&
          t.checkExpect(
              new Person("Zane", 2000, true, this.andrew, this.bree).wellFormed(),
              false);
  }
  boolean testYoungerIAT(Tester t) {
  	return
  			t.checkExpect(this.unk.youngerIAT(this.unk), this.unk) &&
  			t.checkExpect(this.unk.youngerIAT(this.andrew), this.andrew) &&
  			t.checkExpect(this.andrew.youngerIAT(this.unk), this.andrew) &&
  			t.checkExpect(this.david.youngerIAT(this.daisy), this.daisy) &&
  			t.checkExpect(this.candace.youngerIAT(this.cameron), this.candace);
  }
  boolean testAncNames(Tester t) {
      return
          t.checkExpect(this.david.ancNames(),
              new ConsLoString("David",
                  new ConsLoString("Edward", new MtLoString()))) &&
          t.checkExpect(this.eustace.ancNames(),
              new ConsLoString("Eustace", new MtLoString())) &&
          t.checkExpect(new Unknown().ancNames(), new MtLoString());
  }
  boolean testYoungestGrandparent(Tester t) {
      return
          t.checkExpect(this.emma.youngestGrandparent(), new Unknown()) &&
          t.checkExpect(this.david.youngestGrandparent(), new Unknown()) &&
          t.checkExpect(this.claire.youngestGrandparent(), this.eustace) &&
          t.checkExpect(this.bree.youngestGrandparent(), this.dixon) &&
          t.checkExpect(this.andrew.youngestGrandparent(), this.candace) &&
          t.checkExpect(new Unknown().youngestGrandparent(), new Unknown());
  }
  boolean testAppend(Tester t) {
  	return
  			t.checkExpect(this.mt.append(this.mt), this.mt) &&
  			t.checkExpect(this.mt.append(this.string0),this.string0) &&
  			t.checkExpect(this.string0.append(this.mt), this.string0) &&
  			t.checkExpect(this.string0.append(string1),
  					new ConsLoString("Apple",
  							new ConsLoString("Banana",
  									new ConsLoString("Carrot", this.mt)))) &&
  			t.checkExpect(this.string1.append(string2),
  					new ConsLoString("Banana",
  							new ConsLoString("Carrot",
  									new ConsLoString("Orange",
  								  		new ConsLoString("Pear",
  								  				new ConsLoString("Lemon", this.mt))))));
  }
}