// represents an item in a deli (with price in cents)
interface Item {
}

// represents a soup as an item
class Soup implements Item {
  String name;
  int price;
  boolean vegetarian;
  
  Soup(String name, int price, boolean vegetarian) {
    this.name = name;
    this.price = price;
    this.vegetarian = vegetarian;
  }
}

// represents a salad as an item
class Salad implements Item {
  String name;
  int price;
  String dressing;
  
  Salad(String name, int price, String dressing) {
    this.name = name;
    this.price = price;
    this.dressing = dressing;
  }
}

// represents a sandwich as an item
class Sandwich implements Item {
  String name;
  int price;
  String filling0;
  String filling1;
  
  Sandwich(String name, int price, String filling0, String filling1) {
    this.name = name;
    this.price = price;
    this.filling0 = filling0;
    this.filling1 = filling1;
  }
}

// represents examples of Item
class ExamplesItem {
  ExamplesItem() {}
  
  Item mushroom = new Soup("Cream of Mushroom", 999, true);
  Item chkndl = new Soup("Chicken Noodle", 699, false);
  
  Item southwest = new Salad("Southwest", 899, "Ranch");
  Item cobb = new Salad("Cobb", 799, "Blue Cheese");
  
  Item pbj = new Sandwich("PB&J", 499, "Peanut Butter", "Jelly");
  Item hamcheese = new Sandwich("Ham & Cheese", 649, "Ham", "Colby Cheese");
}