import tester.*;

//to represent a geometric shape
interface IShape {
  // to compute the area of this shape
  double area();

  //to compute the distance from this shape to the origin
  double distanceToOrigin();

  //to increase the size of this shape by the given increment
  IShape grow(int inc);

  //is the area of this shape bigger than
  //the area of the given shape?
  boolean isBiggerThan(IShape that);
  
  // is the given point within the area of the shape?
  boolean contains(CartPt point);
}

/*
// represents a geometric point
interface IPoint {
}

// represents a Polar Point
class PolarPoint implements IPoint {
  int r;
  double theta; // in radians
  
  PolarPoint(int r, double theta) {
    this.r = r;
    this.theta = theta;
  }
}
*/

// represents a Cartesian point
class CartPt /* implements IPoint */ {
  int x;
  int y;
  
  CartPt(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  // produces distance to origin of the point
  double distanceToOrigin() {
    return Math.sqrt(this.x * this.x + this.y * this.y);
  }
  
  // produces the distance to another point from this point
  double distanceToPoint(CartPt that) {
    return Math.sqrt((this.x - that.x) * (this.x - that.x)
        + (this.y - that.y) * (this.y - that.y));
  }
}

//to represent a circle
class Circle implements IShape {
  CartPt center; // represents the center of the circle
  int radius;
  String color;

  Circle(CartPt point, int radius, String color) {
    this.center = point;
    this.radius = radius;
    this.color = color;
  }

  /* TEMPLATE
  FIELDS:
  ... this.point ...           -- CartPt
  ... this.size ...            -- int
  ... this.color ...           -- String
  METHODS:
  ... this.area() ...            -- double
  ... this.distanceToOrigin()    -- double
  ... this.grow(int)             -- Circle
  ... this.isBiggerThan(IShape)  -- boolean
  ... this.contains(CartPt)      -- boolean
  METHODS FOR FIELDS:
     ... this.center.distanceToOrigin() ... -- double
     ... this.center.distanceToPoint() ...  -- double
  */

  // to compute the area of this shape
  public double area() {
    return Math.PI * this.radius * this.radius;
  }
  
  //to compute the distance from this shape to the origin
  public double distanceToOrigin() {
    return this.center.distanceToOrigin();
  }
  
  //to increase the size of this shape by the given increment
  public IShape grow(int inc) {
    return new Circle(this.center, this.radius + inc, this.color);
  }
  
  //is the area of this shape bigger than
  //the area of the given shape?
  public boolean isBiggerThan(IShape that) {
    return this.area() > that.area();
  }
  
  //is the given point within the area of the shape?
  public boolean contains(CartPt point) {
    return point.distanceToPoint(this.center) <= this.radius;
  }
}

//to represent a square
class Square implements IShape {
  CartPt topLeft; // represents the top-left corner of the square
  int size;
  String color;

  Square(CartPt point, int size, String color) {
    this.topLeft = point;
    this.size = size;
    this.color = color;
  }

  /* TEMPLATE
  FIELDS:
  ... this.topLeft...            -- CartPt
  ... this.size ...              -- int
  ... this.color ...             -- String
  METHODS:
  ... this.area() ...            -- double
  ... this.distanceToOrigin()    -- double
  ... this.grow(int)             -- Square
  ... this.isBiggerThan(IShape)  -- boolean
  ... this.contains(CartPt)      -- boolean
  METHODS FOR FIELDS:
     ... this.topLeft.distanceToOrigin() ... -- double
     ... this.topLeft.distanceToPoint() ...  -- double
*/
  
  // to compute the area of this shape
  public double area() {
    return this.size * this.size;
  }
  
  //to compute the distance from this shape to the origin
  public double distanceToOrigin() {
    return this.topLeft.distanceToOrigin();
  }

  //to increase the size of this shape by the given increment
  public IShape grow(int inc) {
    return new Square(this.topLeft, this.size + inc, this.color);
  }

  //is the area of this shape bigger than
  //the area of the given shape?
  public boolean isBiggerThan(IShape that) {
    return this.area() > that.area();
  }
  
//is the given point within the area of the shape?
  public boolean contains(CartPt point) {
    return point.x >= this.topLeft.x && point.x <= this.topLeft.x + this.size
        && point.y >= this.topLeft.y && point.y <= this.topLeft.y + this.size;
  }
}

// to represent a combination of two shapes
class Combo implements IShape {
  IShape shape0;
  IShape shape1;
  
  Combo(IShape shape0, IShape shape1) {
    this.shape0 = shape0;
    this.shape1 = shape1;
  }
  
  /* TEMPLATE
  FIELDS:
  ... this.shape0...             -- IShape
  ... this.shape1...             -- IShape
  METHODS:
  ... this.area() ...            -- double
  ... this.distanceToOrigin()    -- double
  ... this.grow(int)             -- Square
  ... this.isBiggerThan(IShape)  -- boolean
  ... this.contains(CartPt)      -- boolean
  */
  
//to compute the area of this shape
 public double area() {
   return this.shape0.area() + this.shape1.area();
 }

 //to compute the distance from this shape to the origin
 public double distanceToOrigin() {
   return Math.min(this.shape0.distanceToOrigin(),
       this.shape1.distanceToOrigin());
 }

 //to increase the size of this shape by the given increment
 public IShape grow(int inc) {
   return new Combo(this.shape0.grow(inc), this.shape1.grow(inc));
 }

 //is the area of this shape bigger than
 //the area of the given shape?
 public boolean isBiggerThan(IShape that) {
   return this.area() > that.area();
 }
 
 // is the given point within the area of the shape?
 public boolean contains(CartPt point) {
   return this.shape0.contains(point) || this.shape1.contains(point);
 }
}

class ExamplesShapes {
  ExamplesShapes() {
  }
  // TODO: write tests for Combo
  CartPt origin = new CartPt(0,0);
  CartPt p1 = new CartPt(50,50);
  CartPt p2 = new CartPt(40, 50);
  CartPt p3 = new CartPt(40, 40);
  CartPt p4 = new CartPt(60, 60);

  IShape c1 = new Circle(this.p1, 10, "red");
  IShape s1 = new Square(this.p1, 30, "red");
  IShape cm1 = new Combo(c1, s1);

  // test the method area in the classes that implement IShape
  boolean testIShapeArea(Tester t) {
    return t.checkInexact(this.c1.area(), 314.15, 0.01)
        && t.checkInexact(this.s1.area(), 900.0, 0.01)
        && t.checkInexact(this.cm1.area(), 1214.15, 0.01);
  }
  
  //test the method distanceToOrigin in the classes that implement IShape
  boolean testIShapeDistanceToOrigin(Tester t) {
    return t.checkInexact(this.c1.distanceToOrigin(), 70.71, 0.01)
        && t.checkInexact(this.s1.distanceToOrigin(), 70.71, 0.01)
        && t.checkInexact(this.cm1.distanceToOrigin(), 70.71, 0.01);
  }
  
  //test the method grow in the classes that implement IShape
  boolean testIShapeGrow(Tester t) {
    return t.checkExpect(this.c1.grow(1), new Circle(this.p1, 11, "red"))
    && t.checkExpect(this.s1.grow(2), new Square(this.p1, 32, "red"))
    && t.checkExpect(this.cm1.grow(3),
        new Combo(new Circle(this.p1, 13, "red"), new Square(this.p1, 33, "red")));
  }
  
  //test the method isBiggerThan in the classes that implement IShape
  boolean testIShapeIsBiggerThan(Tester t) {
    return t.checkExpect(this.c1.isBiggerThan(c1), false)
        && t.checkExpect(this.c1.isBiggerThan(s1), false)
        && t.checkExpect(this.s1.isBiggerThan(c1), true)
        && t.checkExpect(this.cm1.isBiggerThan(cm1), false)
        && t.checkExpect(this.cm1.isBiggerThan(c1), true)
        && t.checkExpect(this.s1.isBiggerThan(cm1), false);
  }
  
  //test the method isPointWithin in the classes that implement IShape
  boolean testIShapeIsPointWithin(Tester t) {
    return t.checkExpect(c1.contains(origin), false)
        && t.checkExpect(c1.contains(p1), true)
        && t.checkExpect(c1.contains(p2), true)
        && t.checkExpect(c1.contains(p3), false)
        && t.checkExpect(s1.contains(p3), false)
        && t.checkExpect(s1.contains(p1), true)
        && t.checkExpect(s1.contains(p4), true)
        && t.checkExpect(cm1.contains(p2), true)
        && t.checkExpect(cm1.contains(p4), true);
  }
}