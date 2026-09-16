import tester.*;

// a class containing useful methods for other classes
class Utils {
  Utils() {
  }
  
  // returns val if it is between min and max;
  // otherwise throw an IllegalArgumentException with argument msg
  int checkRange(int val, int min, int max, String msg) {
    if (val >= min && val <= max) {
      return val;
    }
    else {
      throw new IllegalArgumentException(msg);
    }
  }
}

// represents a date, with its year, month, and day
class Date {
  int year;
  int month;
  int day;
  
  Date(int year, int month, int day) {
    this.year = new Utils().checkRange(year, 1500, 2100,
        "Invalid year: " + Integer.toString(year));
    this.month = new Utils().checkRange(month, 1, 12,
        "Invalid month: " + Integer.toString(month));
    this.day = new Utils().checkRange(day, 1, 31,
        "Invalid day: " + Integer.toString(day));
  }
  Date(int month, int day) {
    this(2026, month, day);
  }
}

// to represent examples and tests of Date
class ExamplesDate {
  ExamplesDate() {
  }
  
  Utils util = new Utils();
  
  // tests
  boolean testCheckRange(Tester t) {
    return
        t.checkExpect(util.checkRange(1, 1, 10, "Not in range"), 1) &&
        t.checkExpect(util.checkRange(5, 1, 10, "Not in range"), 5) &&
        t.checkExpect(util.checkRange(10, 1, 10, "Not in range"), 10) &&
        t.checkException(new IllegalArgumentException("Not in range"),
            util, "checkRange", 0, 1, 10, "Not in range") &&
        t.checkException(new IllegalArgumentException("Not in range"),
            util, "checkRange", 11, 1, 10, "Not in range");
  }
  boolean testDateException(Tester t) {
    return
        t.checkConstructorException(
            new IllegalArgumentException("Invalid year: 53000"),
            "Date",
            53000, 12, 30) &&
        t.checkConstructorException(
            new IllegalArgumentException("Invalid month: 14"),
            "Date",
            2026, 14, -30) &&
        t.checkConstructorException(
            new IllegalArgumentException("Invalid month: 14"),
            "Date",
            14, -30);
  }
}