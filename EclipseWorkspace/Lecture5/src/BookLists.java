import tester.*;

interface ILoBook {
  // count the books in this list
  int count();

  // produce a list of all books published before the given date
  // from this list of books
  ILoBook allBefore(int year);

  // calculate the total sale price of all books in this list for a given discount
  double salePrice(int discount);

  // produce a list of all books in this list, sorted by their price
  ILoBook sortByPrice();
  
 //insert the given book into this list of books
 //already sorted by price
  ILoBook insertByPrice(Book b);
  
//produce a list of all books in this list, sorted by their name
 ILoBook sortByName();
 
//insert the given book into this list of books
//already sorted by name
 ILoBook insertByName(Book b);
}

class ConsLoBook implements ILoBook {
  Book first;
  ILoBook rest;

  ConsLoBook(Book first, ILoBook rest) {
    this.first = first;
    this.rest = rest;
  }
  
  /*
  TEMPLATE:
  ---------
  Fields:
  ... this.first ...                             -- Book
  ... this.rest ...                              -- ILoBook
  Methods:
  ... this.count() ...                           -- int
  ... this.salePrice(int discount) ...           -- double
  ... this.allBefore(int year) ...               -- ILoBook
  ... this.sortByPrice() ...                     -- ILoBook
  ... this.insertByName(ILoBook)...              -- ILoBook
  ... this.rest.sortByName() ...                 -- ILoBook
  ... this.rest.insertByName() ...                 -- ILoBook
  Methods for Fields:
  ... this.first.salePrice(int) ...              -- double
  ... this.first.publishedBefore(int)            -- boolean
  ... this.first.cheaperThan(Book) ...           -- boolean
  ... this.first.titleBefore(Book) ...           -- boolean
  ... this.rest.count() ...                      -- int
  ... this.rest.salePrice(int discount) ...      -- double
  ... this.rest.allBefore(int year) ...          -- ILoBook
  ... this.rest.sortByPrice() ...                -- ILoBook
  ... this.rest.insertByPrice(Book) ...          -- ILoBook
  ... this.rest.sortByName() ...                 -- ILoBook
  ... this.rest.insertByName(Book) ...           -- ILoBook
  */
  
 //count the books in this list
 public int count() {
   return 1 + this.rest.count();
 }
 
 // produce a list of all books published before the given date
 // from this list of books
 public ILoBook allBefore(int year) {
   if (this.first.publishedBefore(year)) {
     return new ConsLoBook(this.first, this.rest.allBefore(year));
   }
   else {
     return this.rest.allBefore(year);
   }
 }
 
 // calculate the total sale price of all books in this list for a given discount
 public double salePrice(int discount) {
   return this.first.salePrice(discount) + this.rest.salePrice(discount);
 }
 
 // produce a list of all books in this list, sorted by their price
 public ILoBook sortByPrice() {
   return this.rest.sortByPrice()
                   .insertByPrice(this.first);
 }
 
 //insert the given book into this list of books
 //already sorted by price
 public ILoBook insertByPrice(Book b) {
   if (this.first.cheaperThan(b)) {
     return new ConsLoBook(this.first, this.rest.insertByPrice(b));
   }
   else {
     return new ConsLoBook(b, this);
   }
 }
 
 // produce a list of all books in this list, sorted by their price
 public ILoBook sortByName() {
   return this.rest.sortByName()
                   .insertByName(this.first);
 }
 
 //insert the given book into this list of books
 //already sorted by price
 public ILoBook insertByName(Book b) {
   if (this.first.titleBefore(b)) {
     return new ConsLoBook(this.first, this.rest.insertByPrice(b));
   }
   else {
     return new ConsLoBook(b, this);
   }
 }
}

class MtLoBook implements ILoBook {
  MtLoBook() {
  }
  
 //count the books in this list
 public int count() {
   return 0;
 }
 
 // produce a list of all books published before the given date
 // from this list of books
 public ILoBook allBefore(int year) {
   return this;
 }
 
 // calculate the total sale price of all books in this list for a given discount
 public double salePrice(int discount) {
   return 0;
 }
 
 // produce a list of all books in this list, sorted by their price
 public ILoBook sortByPrice() {
   return this;
 }
 
 //insert the given book into this list of books
 //already sorted by price
 public ILoBook insertByPrice(Book b) {
   return new ConsLoBook(b, this);
 }
 
 //produce a list of all books in this list, sorted by their name
 public ILoBook sortByName() {
   return this;
 }

 //insert the given book into this list of books
 //already sorted by name
 public ILoBook insertByName(Book b) {
   return new ConsLoBook(b, this);
 }
}

class Book {
  String title;
  String author;
  int year;
  double price;

  Book(String title, String author, int year, double price) {
    this.title = title;
    this.author = author;
    this.year = year;
    this.price = price;
  }

  double salePrice(int discount) {
    return this.price - this.price * discount / 100;
  }
  
  boolean publishedBefore(int year) {
    return this.year < year;
  }
  
  public boolean cheaperThan(Book b) {
    return this.price < b.price;
  }
  
  public boolean titleBefore(Book b) {
    return this.title.compareTo(b.title) < 0;
  }
}

class ExamplesBooks {
  ExamplesBooks() {
  }

  //Books
  Book htdp = new Book("HtDP", "MF", 2001, 60);
  Book lpp = new Book("LPP", "STX", 1942, 25);
  Book ll = new Book("LL", "FF", 1986, 10);

  // lists of Books
  ILoBook mtlist = new MtLoBook();
  ILoBook lista = new ConsLoBook(this.lpp, this.mtlist);
  ILoBook listb = new ConsLoBook(this.htdp, this.mtlist);
  ILoBook listc = new ConsLoBook(this.lpp, new ConsLoBook(this.ll, this.listb));
  ILoBook listd = new ConsLoBook(this.ll,
      new ConsLoBook(this.lpp, new ConsLoBook(this.htdp, this.mtlist)));
  ILoBook liste = new ConsLoBook(this.htdp,
      new ConsLoBook(this.ll, new ConsLoBook(this.lpp, this.mtlist)));
  ILoBook listdUnsorted =
      new ConsLoBook(this.lpp,
        new ConsLoBook(this.htdp,
          new ConsLoBook(this.ll, this.mtlist)));
  ILoBook listeUnsorted =
      new ConsLoBook(this.lpp,
          new ConsLoBook(this.ll,
              new ConsLoBook(this.htdp, this.mtlist)));
  
  // Tests
  boolean testILoBookCount(Tester t) {
    return t.checkExpect(this.mtlist.count(), 0)
        && t.checkExpect(this.lista.count(), 1)
        && t.checkExpect(this.listc.count(), 3);
  }
  
  boolean testILoBookAllBefore(Tester t) {
    return t.checkExpect(this.mtlist.allBefore(2026), this.mtlist)
        && t.checkExpect(this.listc.allBefore(2002), this.listc)
        && t.checkExpect(this.listc.allBefore(2001),
            new ConsLoBook(this.lpp, new ConsLoBook(this.ll, this.mtlist)))
        && t.checkExpect(this.listc.allBefore(1943), this.lista)
        && t.checkExpect(this.listc.allBefore(1942), this.mtlist);
  }
  
  boolean testILoBookSalePrice(Tester t) {
    return t.checkInexact(this.mtlist.salePrice(20), 0.0, 0.01)
        && t.checkInexact(this.lista.salePrice(20), 20.0, 0.01)
        && t.checkInexact(this.lista.salePrice(0), 25.0, 0.01)
        && t.checkInexact(this.lista.salePrice(100), 0.0, 0.01)
        && t.checkInexact(this.listc.salePrice(20), 76.0, 0.01)
        && t.checkInexact(this.listc.salePrice(0), 95.0, 0.01)
        && t.checkInexact(this.listc.salePrice(100), 0.0, 0.01);
  }
  
  boolean testILoBookSortByPrice(Tester t) {
    return
    t.checkExpect(this.listc.sortByPrice(), this.listd) &&
    t.checkExpect(this.listdUnsorted.sortByPrice(), this.listd);
  }
  
  boolean testILoBookSortByName(Tester t) {
    return
    t.checkExpect(this.listc.sortByName(), this.liste) &&
    t.checkExpect(this.listeUnsorted.sortByName(), this.liste);
  }
}