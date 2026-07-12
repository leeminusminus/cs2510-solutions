import tester.*;

// To represent a dog, with it's name, breed,
// year of birth, state of residence,
// and if it is hypoallergenic
class Dog {
  String name;
  String breed;
  int yob;
  String state;
  boolean hypoallergenic;

  Dog(String name, String breed, int yob, String state, boolean hypoallergenic) {
    this.name = name;
    this.breed = breed;
    this.yob = yob;
    this.state = state;
    this.hypoallergenic = hypoallergenic;
  }
}

//To represent examples of Dog
class ExamplesDog {
  ExamplesDog() {
  }

  Dog huffle = new Dog("Hufflepuff", "Wheaten Terrier", 2012, "TX", true);
  Dog pearl = new Dog("Pearl", "Laborador Retriever", 2016, "MA", false);
  Dog dennis = new Dog("Dennis", "Pomeranian", 1970, "NJ", false);
}