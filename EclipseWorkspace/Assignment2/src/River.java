import tester.*;

// a river system
interface IRiver {
  // count the number of sources
  // for this river system
  int sources();
  
  // return true if a location is on this river;
  // otherwise return false
  boolean onRiver(Location aloc);
  
  // compute the total length of the
  // waterways that flow into this point
  int length();
  
  //return true if a location is within a given radius;
  // on this river; otherwise return false
  boolean inRadius(Location aloc, int r);
  
  // finds the longest path on this stream of rivers
  int maxLength();
  
  //finds the number of confluences on this stream of rivers
  int confluences();
  
  // returns a list of all rivers on this stream of rivers
  ILoLocation locations();
}

// the end of a river
class Mouth {
  Location loc;
  IRiver river;

  Mouth(Location loc, IRiver river) {
    this.loc = loc;
    this.river = river;
  }

  // count the number of sources
  // that feed this mouth
  int sources() {
    return this.river.sources();
  }
  
 // return true if a location is on this river system;
 // otherwise return false
  public boolean onRiver(Location aloc) {
  	return
  			this.loc.same(aloc) ||
  			this.river.onRiver(aloc);
  }
  // the total length of the river system
  int length() {
  	return this.river.length();
  }
  
  //return true if a location is within a given radius;
  // on this river system; otherwise return false
  boolean inRadius(Location aloc, int r) {
  	return
  			this.loc.inRange(aloc, r) ||
  			this.river.inRadius(aloc, r);
  }
  
  // finds the longest river path on this river system
  int maxLength() {
  	return this.river.maxLength();
  }
  
  // finds the number of confluences in this river system
  int confluences() {
  	return this.river.confluences();
  }
  
  // returns a list of all locations
  ILoLocation locations() {
  	return new ConsLoLocation(this.loc, this.river.locations());
  }
}

// location on a river
class Location {
  int x;
  int y;
  String name;

  Location(int x, int y, String name) {
    this.x = x;
    this.y = y;
    this.name = name;
  }
  
  // return true if this location is the same as this one;
  // otherwise return false
  boolean same(Location aloc) {
  	return
  			this.x == aloc.x &&
  			this.y == aloc.y;
  }
  
  // return true if this location within a given range of
  // a given location; else return false
  boolean inRange(Location aloc, int r) {
  	return this.dist(aloc) <= r;
  }
  
  // returns the distance of this location from a given location
  double dist(Location aloc) {
  	return 
  			Math.sqrt((this.x - aloc.x) * (this.x - aloc.x) +
  					(this.y - aloc.y) * (this.y - aloc.y));
  }
}

// a list of locations
interface ILoLocation {
	// to append two lists together
	ILoLocation append(ILoLocation that);
}

// an empty list of locations
class MTLoLocation implements ILoLocation {
	MTLoLocation() {
	}
	// to append two lists together
	public ILoLocation append(ILoLocation that) {
		return that;
	}
}

// a non-empty list of locations
class ConsLoLocation implements ILoLocation {
	Location first;
	ILoLocation rest;
	
	ConsLoLocation(Location first, ILoLocation rest) {
		this.first = first;
		this.rest = rest;
	}
	
	// to append two lists together
	public ILoLocation append(ILoLocation that) {
		return new ConsLoLocation(this.first, this.rest.append(that));
	}
}

//the source of a river
class Source implements IRiver {
	int miles;
  Location loc;

  Source(int miles, Location loc) {
  	this.miles = miles;
    this.loc = loc;
  }

  // count the number of sources
  // for this river system
  public int sources() {
    return 1;
  }
  
  // return true if a location is on this river;
  // otherwise return false
  public boolean onRiver(Location aloc) {
  	return this.loc.same(aloc);
  }
  
  // compute the total length of the
  // waterways that flow into this point
  public int length() {
  	return this.miles;
  }
  
  //return true if a location is within a given radius;
  // on this river; otherwise return false
  public boolean inRadius(Location aloc, int r) {
  	return this.loc.inRange(aloc, r);
  }
  
  // finds the longest river on this stream of rivers
  public int maxLength() {
  	return this.miles;
  }
  
  //finds the number of confluences on this river
  public int confluences() {
  	return 0;
  }
  
  // returns a list of all rivers on this stream of rivers
  public ILoLocation locations() {
  	return new ConsLoLocation(this.loc, new MTLoLocation());
  }
}

// a confluence of two rivers
class Confluence implements IRiver {
	int miles;
  Location loc;
  IRiver left;
  IRiver right;

  Confluence(int miles, Location loc, IRiver left, IRiver right) {
  	this.miles = miles;
    this.loc = loc;
    this.left = left;
    this.right = right;
  }

  // count the number of sources
  // for this river system
  public int sources() {
    return this.left.sources() + this.right.sources();
  }
  
  // return true if a location is on this river;
  // otherwise return false
  public boolean onRiver(Location aloc) {
  	return
  			this.loc.same(aloc) ||
  			this.left.onRiver(aloc) ||
  			this.right.onRiver(aloc);	
  }
  
  // compute the total length of the
  // waterways that flow into this point
  public int length() {
  	return this.miles + this.left.length() + this.right.length();
  }
  
  //return true if a location is within a given radius
  // on this river; otherwise return false
  public boolean inRadius(Location aloc, int r) {
  	return
  			this.loc.inRange(aloc, r) ||
  			this.left.inRadius(aloc, r) ||
  			this.right.inRadius(aloc, r);
  }
  
  // finds the longest path on this stream of rivers
  public int maxLength() {
  	return this.miles + Math.max(left.maxLength(), right.maxLength());
  }
  
  //finds the number of confluences on this river
  public int confluences() {
  	return 1 + this.left.confluences() + this.right.confluences();
  }
  
  // returns a list of all rivers on this stream of rivers
  public ILoLocation locations() {
  	return this.left.locations().append(new ConsLoLocation(this.loc, this.right.locations()));
  }
}

// examples of IRiver
class ExamplesRiver {
  ExamplesRiver() {
  }

  Location ls = new Location(1, 2, "a");
  Location la = new Location(4, 8, "b");
  Location lb = new Location(16, 32, "c");
  Location lm = new Location(64, 128, "d");

  Location lz = new Location(2, 3, "e");
  Location ly = new Location(5, 7, "f");
  Location lx = new Location(11, 13, "g");
  Location lw = new Location(17, 19, "e");
  Location lv = new Location(23, 29, "f");
  Location lu = new Location(31, 37, "g");
  Location lt = new Location(41, 43, "h");
  Location lr = new Location(47, 53, "i");
  Location lq = new Location(57, 59, "j");
  Location lp = new Location(61, 67, "k");
  Location lo = new Location(71, 73, "l");
  Location ln = new Location(79, 83, "m");
  Location ll = new Location(91, 97, "n");
  Location lk = new Location(101, 103, "n");

  IRiver z = new Source(1, this.lz);
  IRiver y = new Source(2, this.ly);
  IRiver x = new Source(3, this.lx);
  IRiver w = new Source(4, this.lw);
  IRiver v = new Confluence(5, this.lv, this.y, this.x);
  IRiver u = new Confluence(6, this.lu, this.w, this.v);
  IRiver t = new Source(7, this.lt);
  IRiver r = new Source(8, this.lr);
  IRiver q = new Confluence(9, this.lq, this.t, this.r);
  IRiver p = new Source(10, this.lp);
  IRiver o = new Source(11, this.lo);
  IRiver n = new Source(12, this.ln);
  IRiver l = new Confluence(13, this.ll, this.p, this.o);
  IRiver k = new Confluence(14, this.lk, this.n, this.l);

  Mouth s = new Mouth(this.ls, this.z);
  Mouth a = new Mouth(this.la, this.u);
  Mouth b = new Mouth(this.lb, this.q);
  Mouth m = new Mouth (this.lm, this.k);
  
  // tests
  boolean testSources(Tester t) {
    return
        t.checkExpect(this.s.sources(), 1) &&
        t.checkExpect(this.a.sources(), 3) &&
        t.checkExpect(this.b.sources(), 2) &&
        t.checkExpect(this.m.sources(), 3);
  }
  boolean testOnRiver(Tester t) {
  	return
  			t.checkExpect(this.s.onRiver(new Location(1, 2, "x")), true) &&
  			t.checkExpect(this.s.onRiver(new Location(1,3, "a")), false) &&
  			t.checkExpect(this.a.onRiver(new Location(5,7, "y")), true) &&
  			t.checkExpect(this.a.onRiver(new Location(6,7, "z")), false);
  }
  boolean testLength(Tester t) {
  	return
  			t.checkExpect(this.s.length(), 1) &&
  			t.checkExpect(this.a.length(), 20) &&
  			t.checkExpect(this.b.length(), 24) &&
  			t.checkExpect(this.m.length(), 60);
  }
  boolean testInRadius(Tester t) {
  	return
  			t.checkExpect(this.s.inRadius(new Location(1,1,"q"), 5), true) &&
  			t.checkExpect(this.s.inRadius(new Location(8,9,"w"), 5), false) &&
  			t.checkExpect(this.a.inRadius(new Location(2,7,"e"), 5), true) &&
  			t.checkExpect(this.a.inRadius(new Location(32,27,"r"), 5), false) &&
  			t.checkExpect(this.b.inRadius(new Location(50,50,"t"), 5), true) &&
  			t.checkExpect(this.b.inRadius(new Location(50,50,"y"), 1), false);
  }
  boolean testMaxLength(Tester t) {
  	return
  			t.checkExpect(this.s.maxLength(), 1) &&
  			t.checkExpect(this.a.maxLength(), 14) &&
  			t.checkExpect(this.b.maxLength(), 17) &&
  			t.checkExpect(this.m.maxLength(), 38);
  }
  boolean testConfluences(Tester t) {
  	return
  			t.checkExpect(this.s.confluences(), 0) &&
  			t.checkExpect(this.a.confluences(), 2) &&
  			t.checkExpect(this.b.confluences(), 1) &&
  			t.checkExpect(this.m.confluences(), 2);
  }
  boolean testLocations(Tester t) {
  	return
  			t.checkExpect(this.s.locations(), new ConsLoLocation(this.ls,
  					new ConsLoLocation(this.lz,
  							new MTLoLocation()))) &&
  			t.checkExpect(this.a.locations(), new ConsLoLocation(this.la,
  					new ConsLoLocation(this.lw,
  							new ConsLoLocation(this.lu,
  									new ConsLoLocation(this.ly,
  											new ConsLoLocation(this.lv,
  													new ConsLoLocation(this.lx,
  															new MTLoLocation()))))))) &&
  			t.checkExpect(this.b.locations(), new ConsLoLocation(this.lb,
  					new ConsLoLocation(this.lt,
  							new ConsLoLocation(this.lq,
  									new ConsLoLocation(this.lr,
  											new MTLoLocation()))))) &&
  			t.checkExpect(this.m.locations(), new ConsLoLocation(this.lm,
  					new ConsLoLocation(this.ln,
  							new ConsLoLocation(this.lk,
  									new ConsLoLocation(this.lp,
  											new ConsLoLocation(this.ll,
  													new ConsLoLocation(this.lo,
  															new MTLoLocation())))))));
  }
}