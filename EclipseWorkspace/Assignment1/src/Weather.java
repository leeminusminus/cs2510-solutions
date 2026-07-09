import tester.*;

// To represent a weather record, with the date, the range today, the typical range,
// and the record range.
class WeatherRecord {
	Date d;
	TemperatureRange today;
	TemperatureRange normal;
	TemperatureRange record;
	double precip;
	
	WeatherRecord(Date d, TemperatureRange today, TemperatureRange normal, TemperatureRange record, double precip) {
		this.d = d;
		this.today = today;
		this.normal = normal;
		this.record = record;
		this.precip = precip;
	}
	
	// returns true if today's range is within a given range; otherwise returns false
	boolean withinRange() {
		return this.today.withinRange(this.normal);
	}
	
	// returns true if today's precipitation is higher than a given value; otherwise returns false
	boolean rainyDay(double that) {
		return this.precip > that;
	}
	
	// returns true if today's range breaks the record range (either high or low); otherwise returns false
	boolean recordDay() {
		if (this.today.withinRange(this.record)) {
			return false;
		}
		else {
			return true;
		}
	}
}

// To represent the date with the day, month, and year
class Date {
	int day;
	int month;
	int year;
	
	Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
}

// To represent a temperature range from high to low
class TemperatureRange {
	int high; // in Fahrenheit
	int low;  // in Fahrenheit
	
	TemperatureRange(int high, int low) {
		this.high = high;
		this.low = low;
	}
	
  // returns true if this range is within a given range; otherwise returns false
	boolean withinRange(TemperatureRange that) {
		if (this.low < that.low) {
			return false;
		}
		else if (this.high > that.high) {
			return false;
		}
		else {
			return true;
		}
	}
}

// To represent examples of WeatherRecord
class ExamplesWeatherRecord {
	ExamplesWeatherRecord() {
	}
	
	Date date0 = new Date(14, 7, 2005);
	Date date1 = new Date(1, 1, 1970);
	Date date2 = new Date(29, 2, 2000);
	
	TemperatureRange tr0 = new TemperatureRange(81, 59);
	TemperatureRange tr1 = new TemperatureRange(107, 68);
	TemperatureRange tr2 = new TemperatureRange(22, -10);
	
	TemperatureRange normal0 = new TemperatureRange(82,48);
	TemperatureRange normal1 = new TemperatureRange(90,50);
	TemperatureRange normal2 = new TemperatureRange(23,4);
	
	double precip0 = 1.8;
	double precip1 = 0;
	double precip2 = 0.5;
	
	TemperatureRange record = new TemperatureRange(106, -9);
	
  WeatherRecord wr0 = new WeatherRecord(this.date0, this.tr0, this.normal0, this.record, this.precip0);
  WeatherRecord wr1 = new WeatherRecord(this.date1, this.tr1, this.normal1, this.record, this.precip1);
  WeatherRecord wr2 = new WeatherRecord(this.date2, this.tr2, this.normal2, this.record, this.precip2);
  
  // Tests
  boolean testWithinRange(Tester t) {
  	return t.checkExpect(wr0.withinRange(), true)
  			&& t.checkExpect(wr1.withinRange(), false)
  			&& t.checkExpect(wr2.withinRange(), false);
  }
  
  boolean testRainyDay(Tester t) {
  	return t.checkExpect(wr2.rainyDay(0), true)
  			&& t.checkExpect(wr2.rainyDay(0.5), false)
  			&& t.checkExpect(wr2.rainyDay(1.0), false);
  }
  
  boolean testRecordDay(Tester t) {
  	return t.checkExpect(wr0.recordDay(), false)
  			&& t.checkExpect(wr1.recordDay(), true)
  			&& t.checkExpect(wr2.recordDay(), true);
  }
}