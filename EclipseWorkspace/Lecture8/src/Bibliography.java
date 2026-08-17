import tester.*;

// represents a book, with its authors last name,
// first name, and the book's title
class Book {
  String lastName;
  String firstName;
  String title;
  
  Book(String lastName, String firstName, String title) {
    this.lastName = lastName;
    this.firstName = firstName;
    this.title = title;
  }
  
  // returns true if both books are the same;
  // otherwise returns false
  boolean sameBook(Book that) {
    return
        this.lastName.compareTo(that.lastName) == 0 &&
        this.firstName.compareTo(that.firstName) == 0 &&
        this.title.compareTo(that.title) == 0;
  }
}

// represents a list of books
interface ILoBook {
  // sorts the list of books and removes any duplicates
  ILoBook format();
  
  // removes any duplicates from the list
  ILoBook removeDuplicates();
  
  // does this list have a duplicate of the given book?
  boolean hasDuplicate(Book that);
  
  // sorts the list of books by the authors last name
  ILoBook sortByLastName();
  
  // inserts a given book into a *sorted* list at the correct position
  ILoBook insert(Book that);
}

// represents an empty list of books
class MtLoBook implements ILoBook {
  MtLoBook() {
  }
  
  // sorts the list of books and removes any duplicates
  public ILoBook format() {
    return new MtLoBook();
  }
  
  // removes any duplicates from the list
  public ILoBook removeDuplicates() {
    return new MtLoBook();
  }
  
  // does this list have a duplicate of the given book?
  public boolean hasDuplicate(Book that) {
    return false;
  }
  
  // sorts the list of books by the authors last name
  public ILoBook sortByLastName() {
    return new MtLoBook();
  }
  
  // inserts a given book into a *sorted* list at the correct position
  public ILoBook insert(Book that) {
    return new ConsLoBook(that, new MtLoBook());
  }
}

// represents a non-empty list of books
class ConsLoBook implements ILoBook {
  Book first;
  ILoBook rest;
  
  ConsLoBook(Book first, ILoBook rest) {
    this.first = first;
    this.rest = rest;
  }
  
  // sorts the list of books and removes any duplicates
  public ILoBook format() {
    return this.removeDuplicates().sortByLastName();
  }
  
  // removes any duplicates from the list
  public ILoBook removeDuplicates() {
    if (this.rest.hasDuplicate(this.first)) {
      return this.rest.removeDuplicates();
    }
    else {
      return new ConsLoBook(this.first, this.rest.removeDuplicates());
    }
  }
  
  // does this list have a duplicate of the given book?
  public boolean hasDuplicate(Book that) {
    return this.first.sameBook(that) || this.rest.hasDuplicate(that);
  }
  
  // sorts the list of books by the authors last name
  public ILoBook sortByLastName() {
    return this.rest.sortByLastName().insert(this.first);
  }
  
  // inserts a given book into a *sorted* list at the correct position
  public ILoBook insert(Book that) {
    if (this.first.lastName.compareTo(that.lastName) < 0) {
      return new ConsLoBook(this.first, this.rest.insert(that));
    }
    else {
      return new ConsLoBook(that, this);
    }
  }
}

// represents examples of Book and ILoBook
class ExamplesBook {
  ExamplesBook() {
  }
  
  Book book1 = new Book("Clarke", "Arthur", "2001: A Space Odyssey");
  Book book2 = new Book("Weir", "Andy", "The Martian");
  Book book3 = new Book("Cline", "Ernest", "Ready Player One");
  
  ILoBook mt = new MtLoBook();
  ILoBook list1 = new ConsLoBook(this.book1, this.mt);
  ILoBook list2 = new ConsLoBook(this.book1,
      new ConsLoBook(this.book2,
          new ConsLoBook(this.book3, this.mt)));
  ILoBook list3 = new ConsLoBook(this.book3, this.list2);
  ILoBook list4 = new ConsLoBook(this.book1,
      new ConsLoBook(this.book1, this.mt));
  ILoBook listSorted = new ConsLoBook(this.book1,
      new ConsLoBook(this.book3,
          new ConsLoBook(this.book2, this.mt)));
  
  // tests
  boolean testRemoveDuplicates(Tester t) {
    return
        t.checkExpect(this.list2.removeDuplicates(), this.list2) &&
        t.checkExpect(this.list4.removeDuplicates(), this.list1) &&
        t.checkExpect(this.list3.removeDuplicates(), this.list2);
  }
  boolean testSortByLastName(Tester t) {
    return
        t.checkExpect(this.list1.sortByLastName(), this.list1) &&
        t.checkExpect(this.list4.sortByLastName(), this.list4) &&
        t.checkExpect(this.list2.sortByLastName(), this.listSorted);
  }
  boolean testFormat(Tester t) {
    return
        t.checkExpect(this.list1.format(), this.list1) &&
        t.checkExpect(this.list2.format(), this.listSorted) &&
        t.checkExpect(this.list3.format(), this.listSorted);
  }
}