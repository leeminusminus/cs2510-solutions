import tester.*;

// to represent a shape in a Cartesian plane
interface IShape {
	// to compute the area of this shape
	double area();
	
	// to compute the distance of
	// this shape to the origin
	double distTo0();
	
	// is the given point within
	// the bounds of this shape?
	boolean in(CartPt p);
	
	// compute the bounding box for this shape
	Square bb();
}

// to represent a point with its top-left point, and side length
class Square implements IShape {
	CartPt loc;
	int size;
	
	Square(CartPt loc, int size) {
		this.loc = loc;
		this.size = size;
	}
	
	// to compute the area of this shape
	public double area() {
		return this.size * this.size;
	}
	
	// to compute the distance of
	// this shape to the origin
	public double distTo0() {
		return this.loc.distTo0();
	}
	
	// is the given point within
	// the bounds of this shape?
	public boolean in(CartPt p) {
		return this.between(this.loc.x, p.x, this.size)
				&& this.between(this.loc.y, p.y, this.size);
	}
	
	// compute the bounding box for this shape
	public Square bb() {
		return this;
	}
	
	// is x in the interval [lft, lft + width]?
	boolean between(int lft, int x, int width) {
		return lft <= x
				&& x <= lft + width;
	}
}

// to represent a dot with its Cartesian location
class Dot implements IShape {
	CartPt loc;
	
	Dot(CartPt loc) {
		this.loc = loc;
	}
	
	// to compute the area of this shape
	public double area() {
		return 0;
	}
	
	// to compute the distance of
	// this shape to the origin
	public double distTo0() {
		return this.loc.distTo0();
	}
	
	// is the given point within
	// the bounds of this shape?
	public boolean in(CartPt p) {
		return this.loc.same(p);
	}
	
	// compute the bounding box for this shape
	public Square bb() {
		return new Square(this.loc, 1);
	}
}

// to represent a circle with its center point and radius
class Circle implements IShape {
	CartPt loc;
	int radius;
	
	Circle(CartPt loc, int radius) {
		this.loc = loc;
		this.radius = radius;
	}
	
	// to compute the area of this shape
	public double area() {
		return Math.PI * this.radius * this.radius;
	}
	
	// to compute the distance of
	// this shape to the origin
	public double distTo0() {
		return this.loc.distTo0() - radius;
	}
	
	// is the given point within
	// the bounds of this shape?
	public boolean in(CartPt p) {
		return this.loc.distanceTo(p) <= radius;
	}
	
	// compute the bounding box for this shape
	public Square bb() {
		return new Square(this.loc.translate(radius), 2 * radius);
	}
}

// to represent a point in a Cartesian plane
class CartPt {
	int x;
	int y;
	
	CartPt(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// to compute the distance of this point to the origin
	double distTo0() {
		return Math.sqrt((this.x * this.x) + (this.y * this.y));
	}
	
	// are this CartPt and p the same?
	boolean same(CartPt p) {
		return this.x == p.x && this.y == p.y;
	}
	
	// compute the distance between this CartPt and p
	double distanceTo(CartPt p) {
		return
				Math.sqrt((this.x - p.x) * (this.x - p.x) + 
						(this.y - p.y) * (this.y - p.y));
	}
	
	// create a point that is delta pixels (up,left) from this
	CartPt translate(int delta) {
		return new CartPt(this.x - delta, this.y - delta);
	}
}

// to represent and test examples of IShape
class ExamplesShape {
	ExamplesShape() {
	}
	
	// examples
	IShape dot = new Dot(new CartPt(4,3));
	IShape squ = new Square(new CartPt(4, 3), 3);
	IShape cir = new Circle(new CartPt(12, 5), 2);
	
	// tests
	boolean testArea(Tester t) {
		return
				t.checkInexact(this.dot.area(), 0.0, 0.1) &&
				t.checkInexact(this.squ.area(), 9.0, 0.1) &&
				t.checkInexact(this.cir.area(), 12.56, 0.01);
	}
	boolean testDistTo0(Tester t) {
		return
				t.checkInexact(this.dot.distTo0(), 5.0, 0.01) &&
				t.checkInexact(this.squ.distTo0(), 5.0, 0.01) &&
				t.checkInexact(this.cir.distTo0(), 11.0, 0.01);
	}
	boolean testIn(Tester t) {
		return
				t.checkExpect(new Dot(new CartPt(100,200)).in(new CartPt(100,200)), true) &&
				t.checkExpect(new Dot(new CartPt(100,200)).in(new CartPt(80,200)), false) &&
				t.checkExpect(new Square(new CartPt(100, 200), 40).in(new CartPt(120, 220)), true) &&
				t.checkExpect(new Square(new CartPt(100, 200), 40).in(new CartPt(80, 220)), false) &&
				t.checkExpect(new Circle(new CartPt(0, 0), 20).in(new CartPt(4, 3)), true) &&
				t.checkExpect(new Circle(new CartPt(0, 0), 10).in(new CartPt(12, 5)), false);
	}
	boolean testBb(Tester t) {
		return
				t.checkExpect(this.dot.bb(), new Square(new CartPt(4,3), 1)) &&
				t.checkExpect(this.squ.bb(), this.squ) &&
				t.checkExpect(this.cir.bb(), new Square(new CartPt(10,3), 4));
	}
}
