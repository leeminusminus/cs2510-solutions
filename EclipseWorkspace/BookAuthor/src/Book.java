import tester.*;

/*
 * +---------------+
 * | Book          |
 * +---------------+
 * | String title  |
 * | String author |
 * | int price     |
 * +---------------+
 */

// to represent a book in a bookstore
class Book {
  String title;
  Author author;
  int price; // in dollars

  // the constructor
  Book(String title, Author author, int price) {
    this.title = title;
    this.author = author;
    this.price = price; // in dollars
  }

  /* TEMPLATE:
   * Fields:
   * ... this.title ...  -- String
   * ... this.author ... -- Author
   * ... this.price ...  -- int
   * 
   * Methods:
   * ... this.salePrice(int)   -- int
   * ... this.sameAuthor(Book) -- boolean
   * ... this.salePrice()      -- Book
   * 
   * Methods for fields:
   * ... this.author.sameAuthor(Author) ... -- boolean
   */

  // the given discount rate (as a percentage)
  int salePrice(int discount) {
    return this.price - (this.price * discount) / 100;
  }

  // is this book written by the same author as the given book?
  boolean sameAuthor(Book that) {
    return this.author.sameAuthor(that.author);
  }
  
  // produce a book like this one, but with the price reduced by 20%
  Book reducePrice() {
    return new Book(this.title, this.author, this.salePrice(20));
  }
}

// to represent an author of a book in a bookstore
class Author {
  String name;
  int yob;

  // the constructor
  Author(String name, int yob) {
    this.name = name;
    this.yob = yob;
  }
  
  /* TEMPLATE:
   * Fields:
   * ... this.name ... -- String
   * ... this.yob ...  -- int
   * 
   * Methods:
   * ... this.sameAuthor(Author) ... -- boolean
   */
  
  boolean sameAuthor(Author that) {
    return this.name.equals(that.name)
        && this.yob == that.yob;
  }
}

class ExamplesBooks {
  ExamplesBooks() {
  }

  // examples of authors
  Author matt = new Author("Matthias Felleisen", 1970);
  Author pat = new Author("Pat Conroy", 1948);

  // examples of books
  Book htdp = new Book("HtDP", this.matt, 60);
  Book beaches = new Book("Beaches", this.pat, 20);
  Book prince = new Book("Prince of Tides", this.pat, 15);

  // test the method salePrice for the class Book
  boolean testSalePrice(Tester t) {
    return t.checkExpect(this.htdp.salePrice(20), 48)
        && t.checkExpect(this.beaches.salePrice(50), 10);
  }
  
  // test the method salePrice for the class Book
  boolean testSameAuthor(Tester t) {
    return t.checkExpect(this.beaches.sameAuthor(prince), true)
        && t.checkExpect(this.beaches.sameAuthor(htdp), false);
  }
  
  // test the method reducePrice for class Book
  boolean testReducePrice(Tester t) {
    return t.checkExpect(this.htdp.reducePrice(),
        new Book("HtDP", this.matt, 48))
        && t.checkExpect(this.beaches.reducePrice(),
            new Book("Beaches", this.pat, 16));
  }
}
