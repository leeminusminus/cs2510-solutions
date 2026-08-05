import tester.*;

// a river system
interface IRiver {
  // count the number of sources
  // for this river system
  int sources();
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
}

//the source of a river
class Source implements IRiver {
  Location loc;

  Source(Location loc) {
    this.loc = loc;
  }

  // count the number of sources
  // for this river system
  public int sources() {
    return 1;
  }
}

// a confluence of two rivers
class Confluence implements IRiver {
  Location loc;
  IRiver left;
  IRiver right;

  Confluence(Location loc, IRiver left, IRiver right) {
    this.loc = loc;
    this.left = left;
    this.right = right;
  }

  // count the number of sources
  // for this river system
  public int sources() {
    return this.left.sources() + this.right.sources();
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

  IRiver z = new Source(this.lz);
  IRiver y = new Source(this.ly);
  IRiver x = new Source(this.lx);
  IRiver w = new Source(this.lw);
  IRiver v = new Confluence(this.lv, this.y, this.x);
  IRiver u = new Confluence(this.lu, this.w, this.v);
  IRiver t = new Source(this.lt);
  IRiver r = new Source(this.lr);
  IRiver q = new Confluence(this.lq, this.t, this.r);
  IRiver p = new Source(this.lp);
  IRiver o = new Source(this.lo);
  IRiver n = new Source(this.ln);
  IRiver l = new Confluence(this.ll, this.p, this.o);
  IRiver k = new Confluence(this.lk, this.n, this.l);

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
}