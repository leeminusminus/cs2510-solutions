import tester.*;

// represents a log of runner entries
interface ILog {
	// ??? nnn();
	// to compute the total number of miles recorded in this log
	double miles();
	
	// to extract those entries in this log for the given month and year
	ILog oneMonth(int month, int year);
	
	// to sum the distance of all running in this log for the given month and year
	double milesOneMonth(int month, int year);
	
	// to find the longest run in this log
	double maxRun();
	
	// to find the longest run in this log (so far)
	double maxRunHelper(double max);
	
	// are all runs in this shorter than the given length?
	boolean runsLessThan(double distance);
}

// represents an empty log of runner entries
class MTLog implements ILog {
	MTLog() {
	}
	/*
	??? nnn() {
		...
	}
	*/
	// to compute the total number of miles recorded in this log
	public double miles() {
		return 0.0;
	}
	
	// to find the longest run in this log
	public double maxRun() {
		return 0.0;
	}
	
  //to extract those entries in this log for the given month and year
	public ILog oneMonth(int month, int year) {
		return new MTLog();
	}
	
	// to sum the distance of all running in this log for the given month and year
	public double milesOneMonth(int month, int year) {
		return 0.0;
	}
	
	// to find the longest run in this log (so far)
	public double maxRunHelper(double max) {
		return max;
	}
	
	// are all runs in this shorter than the given length?
	public boolean runsLessThan(double distance) {
		return true;
	}
}

// represents a list of runner entries
class ConsLog implements ILog {
	Entry fst;
	ILog rst;
	
	ConsLog(Entry fst, ILog rst) {
		this.fst = fst;
		this.rst = rst;
	}
	/*
	??? nnn() {
		... this.fst.mmm() ...
		... this.rst.nnn() ...
	}
	*/
	
	// to compute the total number of miles recorded in this log
	public double miles() {
		return this.fst.distance + this.rst.miles();
	}
	
	// to extract those entries in this log for the given month and year
	public ILog oneMonth(int month, int year) {
		if (this.fst.sameMonthAndYear(month, year)) {
			return new ConsLog(this.fst, this.rst.oneMonth(month, year));
		}
		else {
			return this.rst.oneMonth(month, year);
		}
	}
	
	// to sum the distance of all running in this log for the given month and year
	/*
	public double milesOneMonth(int month, int year) {
		if (this.fst.sameMonthAndYear(month, year)) {
			return this.fst.distance + this.rst.milesOneMonth(month, year);
		}
		else {
			return this.rst.milesOneMonth(month, year);
		}
	}
	*/
	public double milesOneMonth(int month, int year) {
		return this.oneMonth(month, year).miles();
	}
	
	// to find the longest run in this log
	public double maxRun() {
		return this.maxRunHelper(0);
	}
	
	// to find the longest run in this log (so far)
	public double maxRunHelper(double max) {
		if (this.fst.distance > max) {
			return this.rst.maxRunHelper(this.fst.distance);
		}
		else {
			return this.rst.maxRunHelper(max);
		}
	}
	
	// are all runs in this shorter than the given length?
	public boolean runsLessThan(double distance) {
		return this.fst.distance < distance && this.rst.runsLessThan(distance);
	}
}

// represents an entry of a runner, including a date, the distance in miles,
// the duration in minutes, and a comment
class Entry {
	Date d;
	double distance; // in miles
	int duration; // in minutes
	String comment;
	
	Entry(Date d, double distance, int duration, String comment) {
		this.d = d;
		this.distance = distance;
		this.duration = duration;
		this.comment = comment;
	}
	
	/*
	??? mmm() {
		... this.d.lll() ...
		... this.distance ...
		... this.duration ...
		... this.comment ...
	}
	*/
	boolean sameMonthAndYear(int month, int year) {
		return this.d.sameMonthAndYear(month, year);
	}
}

// represents a date, with a day, month, and year
class Date {
	int day;
	int month;
	int year;
	
	Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	/*
	??? lll() {
		... this.day ...
		... this.month ...
		... this.year ...
	}
	*/
	//is this date in the given month and year?
	boolean sameMonthAndYear(int month, int year) {
		return (this.month == month && this.year == year);
	}
}

// represents examples ILog, Entry, and Date
class ExamplesComposite {
	ExamplesComposite() {
	}
	
	Date d1 = new Date(5, 5, 2003);
	Date d2 = new Date(6, 6, 2003);
	Date d3 = new Date(23, 6, 2003);
	
	Entry e1 = new Entry(this.d1, 5.0, 25, "Good");
	Entry e2 = new Entry(this.d2, 3.0, 24, "Tired");
	Entry e3 = new Entry(this.d3, 26.0, 156, "Great");
	
	ILog l1 = new MTLog();
	ILog l2 = new ConsLog(this.e1,this.l1);
	ILog l3 = new ConsLog(this.e2,this.l2);
	ILog l4 = new ConsLog(this.e3,this.l3);
	
	// tests
	boolean testMiles(Tester t) {
		return
				t.checkInexact(this.l1.miles(), 0.0, 0.1) &&
				t.checkInexact(this.l2.miles(), 5.0, 0.1) &&
				t.checkInexact(this.l3.miles(), 8.0, 0.1) &&
				t.checkInexact(this.l4.miles(), 34.0, 0.1);
	}
	boolean testOneMonth(Tester t) {
		return
				t.checkExpect(this.l1.oneMonth(6, 2003), new MTLog()) &&
				t.checkExpect(this.l2.oneMonth(6, 2003), new MTLog()) &&
				t.checkExpect(this.l3.oneMonth(6, 2003),
						new ConsLog(this.e2, new MTLog())) &&
				t.checkExpect(this.l4.oneMonth(6, 2003),
						new ConsLog(this.e3,
								new ConsLog(this.e2, new MTLog())));
	}
	boolean testMilesOneMonth(Tester t) {
		return
				t.checkInexact(this.l1.milesOneMonth(6, 2003), 0.0, 0.1) &&
				t.checkInexact(this.l2.milesOneMonth(6, 2003), 0.0, 0.1) &&
				t.checkInexact(this.l3.milesOneMonth(6, 2003), 3.0, 0.1) &&
				t.checkInexact(this.l4.milesOneMonth(6, 2003), 29.0, 0.1);
	}
	boolean testMaxRun(Tester t) {
		return
				t.checkInexact(this.l1.maxRun(), 0.0, 0.1) &&
				t.checkInexact(this.l2.maxRun(), 5.0, 0.1) &&
				t.checkInexact(this.l3.maxRun(), 5.0, 0.1) &&
				t.checkInexact(this.l4.maxRun(), 26.0, 0.1);
	}
	boolean testRunsLessThan(Tester t) {
		return
				t.checkExpect(this.l1.runsLessThan(10), true) &&
				t.checkExpect(this.l2.runsLessThan(10), true) &&
				t.checkExpect(this.l3.runsLessThan(10), true) &&
				t.checkExpect(this.l4.runsLessThan(10), false);
	}
}

