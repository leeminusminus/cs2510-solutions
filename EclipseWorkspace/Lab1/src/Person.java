/* +-----------------+
 * | Person          |
 * +-----------------+
 * | String name     |
 * | int age         |
 * | String gender   |
 * | Address address +----------+
 * +-----------------+          |
 *                              v
 *                       +--------------+
 *                       | Address      |
 *                       +--------------+
 *                       | String city  |
 *                       | String state |
 *                       +--------------+
 */

// represents an address with a city and state

interface IAT {
}

class Tree implements IAT {
  Person person;
  IAT mother;
  IAT father;
  
  Tree(Person person, IAT mother, IAT father) {
    this.person = person;
    this.mother = mother;
    this.father = father;
  }
}

class Unknown implements IAT {
  Unknown() {}
}

class Address {
  String city;
  String state;
  
  Address(String city, String state) {
    this.city = city;
    this.state = state;
  }
}

// represents a person with their name, age, gender, and address
class Person {
  String name;
  int age;
  String gender;
  Address address;
  
  Person(String name, int age, String gender, Address address) {
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.address = address;
  }
}

// represents examples of Person and Address
class ExamplesPerson {
  ExamplesPerson() {}
  
  Address raleigh = new Address("Raleigh", "NC");
  Address boston = new Address("Boston", "MA");
  Address warwick = new Address("Warwick", "RI");
  Address nashua = new Address("Nashua", "NH");
  
  Person tim = new Person("Tim",23,"Male",this.boston);
  Person Kate = new Person("Kate",22,"Female",this.warwick);
  Person rebecca = new Person("Rebecca",31,"Non-binary",this.nashua);
  Person lee = new Person("Lee", 20, "Male", this.raleigh);
  Person maryam = new Person("Maryam", 24, "Female", this.raleigh);
}

// implements examples for IAT
class ExamplesIAT {
  ExamplesIAT() {}
  
  Address greenville = new Address("Greenville","SC");
  Unknown unknown = new Unknown();
  Person john = new Person("John",20,"Male",this.greenville);
  Person jack = new Person("Jack",50,"Male",this.greenville);
  Person mary = new Person("Mary",48,"Female",this.greenville);
  Person james = new Person("James",80,"Male",this.greenville);
  Person ada = new Person("Ada", 82,"Female",this.greenville);
  
  IAT tree = new Tree(john, new Tree(mary, this.unknown, this.unknown),
      new Tree(jack, new Tree(ada, this.unknown, this.unknown),
          new Tree(james, this.unknown, this.unknown)));
}