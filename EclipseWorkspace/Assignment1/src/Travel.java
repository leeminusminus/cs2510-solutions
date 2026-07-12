import tester.*;

// To represent different types of housing
interface IHousing {
}

// To represent a hut, with it's population and it's capacity
// NOTE: population must be [0, capacity]
class Hut implements IHousing {
  int population;
  int capacity;
  
  Hut(int population, int capacity) {
    this.population = population;
    this.capacity = capacity;
  }
}

// To represent an inn, with a name, population, and capacity
// NOTE: population must be [0, capacity]
class Inn implements IHousing {
  String name;
  int population;
  int capacity;
  int stalls;
  
  Inn(String name, int population, int capacity, int stalls) {
    this.name = name;
    this.population = population;
    this.capacity = capacity;
    this.stalls = stalls;
  }
}

// To represent a castle, with its family name, the population,
// and its carriage capacity
class Castle implements IHousing {
  String name;
  String familyName;
  int population;
  int carriageHouse;
  
  Castle(String name, String familyName, int population, int carriageHouse) {
    this.name = name;
    this.familyName = familyName;
    this.population = population;
    this.carriageHouse = carriageHouse;
  }
}

// To represent different types of transport
interface ITransport {
}

// To represent a horse with a name, color,
// a to housing, and a from housing
class Horse implements ITransport {
  String name;
  String color;
  IHousing to;
  IHousing from;
  
  Horse(String name, String color, IHousing to, IHousing from) {
    this.name = name;
    this.color = color;
    this.to = to;
    this.from = from;
  }
}

// To represent a carriage with a max tonnage,
// a to housing, and a from housing
class Carriage implements ITransport {
  int tonnage;
  IHousing to;
  IHousing from;
  
  Carriage(int tonnage, IHousing to, IHousing from) {
    this.tonnage = tonnage;
    this.to = to;
    this.from = from;
  }
}

// To represent examples of IHousing and ITransport
class ExamplesTravel {
  ExamplesTravel() {
  }
  
  IHousing hovel = new Hut(1, 5);
  IHousing winterfell = new Castle("Winterfell", "Stark", 500, 6);
  IHousing crossroads = new Inn("Inn At The Crossroads", 20, 40, 12);
  IHousing sneedstead = new Hut(8, 16);
  IHousing bravehold = new Castle("Bravehold", "Lovell", 1024, 32);
  
  ITransport horse0 = new Horse("Woz", "Brown", this.crossroads, this.hovel);
  ITransport carriage1 = new Carriage(10, this.bravehold, this.sneedstead);
  ITransport horse2 = new Horse("Turing", "Grey", this.winterfell, this.crossroads);
}
