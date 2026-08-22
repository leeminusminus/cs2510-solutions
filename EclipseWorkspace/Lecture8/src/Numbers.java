import tester.*;

// represents a list of integers
interface ILoNumber {
  // returns true if there is/are a number(s) that is/are even,
  // positive and odd, and between 5 and 10 (inclusive);
  // otherwise returns false
  boolean meetsConditions();
  
  //returns true if there is/are a number(s) that is/are even,
  // positive and odd, and between 5 and 10 (inclusive) so far
  boolean meetsConditionsHelper(boolean cond1, boolean cond2, boolean cond3);
  
  // returns true if there are numbers that are at least
  // 3 numbers that are positive and odd, or between 5 and 10 (inclusive);
  // otherwise returns false
  boolean meetsConditionsUnique();
  
  // returns true if there are numbers that are at least
  // 3 numbers that are positive and odd, or between 5 and 10 (inclusive) so far
  boolean meetsConditionsUniqueHelper(boolean cond1, boolean cond2, boolean cond3);
  
  // returns true if there are exactly 3 numbers that are at least
  // 3 numbers that are positive and odd, or between 5 and 10 (inclusive);
  // otherwise returns false
  boolean allMeetConditions();
  
 //returns true if there are exactly 3 numbers that are at least
 // 3 numbers that are positive and odd, or between 5 and 10 (inclusive) so far
 boolean allMeetConditionsHelper(int depth, boolean cond1, boolean cond2, boolean cond3);
}

// represents an empty list of integers
class MtLoNumber implements ILoNumber {
  MtLoNumber() {
  }
  
  // returns true if there is a number that is even,
  // positive and odd, and between 5 and 10 (inclusive);
  // otherwise produce false
  public boolean meetsConditions() {
    return false;
  }
  
  //returns true if there is a number that is even,
  // positive and odd, and between 5 and 10 (inclusive) so far
  public boolean meetsConditionsHelper(boolean cond1, boolean cond2, boolean cond3) {
    return cond1 && cond2 && cond3;
  }
  
  // returns true if there are numbers that are at least
  // 3 numbers that are positive and odd, or between 5 and 10 (inclusive);
  // otherwise produce false
  public boolean meetsConditionsUnique() {
    return false;
  }
  
  // returns true if there are numbers that are at least
  // 3 numbers that are positive and odd, or between 5 and 10 (inclusive) so far
  public boolean meetsConditionsUniqueHelper(boolean cond1, boolean cond2, boolean cond3) {
    return cond1 && cond2 && cond3;
  }
  
  // returns true if there are exactly 3 numbers that are at least
  // 3 numbers that are positive and odd, or between 5 and 10 (inclusive);
  // otherwise returns false
  public boolean allMeetConditions() {
    return false;
  }
  
  //returns true if there are exactly 3 numbers that are at least
  // 3 numbers that are positive and odd, or between 5 and 10 (inclusive) so far
  public boolean allMeetConditionsHelper(int depth, boolean cond1, boolean cond2, boolean cond3) {
    return cond1 && cond2 && cond3;
  }
}

// represents a non empty list of integers
class ConsLoNumber implements ILoNumber {
  int first;
  ILoNumber rest;
  
  ConsLoNumber(int first, ILoNumber rest) {
    this.first = first;
    this.rest = rest;
  }
  
  // returns true if there is a number that is even,
  // positive and odd, and between 5 and 10 (inclusive);
  // otherwise produce false
  public boolean meetsConditions() {
    return meetsConditionsHelper(false, false, false);
  }
  
  //returns true if there is a number that is even,
  // positive and odd, and between 5 and 10 (inclusive) so far
  public boolean meetsConditionsHelper(boolean cond1, boolean cond2, boolean cond3) {
    return this.rest.meetsConditionsHelper(
        cond1 || this.first % 2 == 0,
        cond2 || this.first > 0 && this.first % 2 == 1,
        cond3 || this.first >= 5 && this.first <= 10);
  }
  
  // returns true if there are numbers that are at least
  // 3 numbers that are positive and odd, or between 5 and 10 (inclusive);
  // otherwise produce false
  public boolean meetsConditionsUnique() {
    return meetsConditionsUniqueHelper(false, false, false);
  }
  
  // returns true if there are numbers that are at least
  // 3 numbers that are positive and odd, or between 5 and 10 (inclusive) so far
  public boolean meetsConditionsUniqueHelper(boolean cond1, boolean cond2, boolean cond3) {
    return
        this.rest.meetsConditionsUniqueHelper(
            cond1 || this.first % 2 == 0,
            cond2,
            cond3) ||
        this.rest.meetsConditionsUniqueHelper(
            cond1,
            cond2 || this.first > 0 && this.first % 2 == 1,
            cond3) ||
        this.rest.meetsConditionsUniqueHelper(
            cond1,
            cond2,
            cond3 || this.first >= 5 && this.first <= 10);
  }
  
  // returns true if there are exactly 3 numbers that are at least
  // 3 numbers that are positive and odd, or between 5 and 10 (inclusive);
  // otherwise returns false
  public boolean allMeetConditions() {
    return allMeetConditionsHelper(1, false, false, false);
  }
  
  //returns true if there are exactly 3 numbers that are at least
  // 3 numbers that are positive and odd, or between 5 and 10 (inclusive) so far
  public boolean allMeetConditionsHelper(int depth, boolean cond1, boolean cond2, boolean cond3) {
    if (depth > 3) {
      return false;
    }
    else {
      return
          this.rest.allMeetConditionsHelper(
              depth+1,
              cond1 || this.first % 2 == 0,
              cond2,
              cond3) ||
          this.rest.allMeetConditionsHelper(
              depth+1,
              cond1,
              cond2 || this.first > 0 && this.first % 2 == 1,
              cond3) ||
          this.rest.allMeetConditionsHelper(
              depth+1,
              cond1,
              cond2,
              cond3 || this.first >= 5 && this.first <= 10);
    }
  }
}

// represents examples and tests of ILoNumber
class ExamplesNumber {
  ExamplesNumber() {
  }
  
  ILoNumber mt = new MtLoNumber();
  ILoNumber list1 = new ConsLoNumber(6,
      new ConsLoNumber(5, this.mt));
  ILoNumber list2 = new ConsLoNumber(4,
      new ConsLoNumber(3, this.mt));
  ILoNumber list3 = new ConsLoNumber(6,
      new ConsLoNumber(5,
          new ConsLoNumber(6, this.mt)));
  ILoNumber list4 = new ConsLoNumber(6,
      new ConsLoNumber(5,
          new ConsLoNumber(42,
              new ConsLoNumber(6, this.mt))));
  
  // tests
  boolean testMeetsConditions(Tester t) {
    return
        t.checkExpect(this.mt.meetsConditions(), false) &&
        t.checkExpect(this.list1.meetsConditions(), true) &&
        t.checkExpect(this.list2.meetsConditions(), false) &&
        t.checkExpect(this.list3.meetsConditions(), true) &&
        t.checkExpect(this.list4.meetsConditions(), true);
  }
  boolean testMeetsConditionsUnique(Tester t) {
    return
        t.checkExpect(this.mt.meetsConditionsUnique(), false) &&
        t.checkExpect(this.list1.meetsConditionsUnique(), false) &&
        t.checkExpect(this.list2.meetsConditionsUnique(), false) &&
        t.checkExpect(this.list3.meetsConditionsUnique(), true) &&
        t.checkExpect(this.list4.meetsConditionsUnique(), true);
  }
  boolean testAllMeetConditions(Tester t) {
    return
        t.checkExpect(this.mt.allMeetConditions(), false) &&
        t.checkExpect(this.list1.allMeetConditions(), false) &&
        t.checkExpect(this.list2.allMeetConditions(), false) &&
        t.checkExpect(this.list3.allMeetConditions(), true) &&
        t.checkExpect(this.list4.allMeetConditions(), false);
  }
}