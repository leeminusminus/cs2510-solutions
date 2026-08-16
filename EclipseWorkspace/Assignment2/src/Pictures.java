import tester.*;

// represents a picture
interface IPicture {
  // returns the overall width of this picture
  int getWidth();
  
  // returns the number of shapes of this picture
  int countShapes();
  
  // returns the max depth of operations of this picture
  int comboDepth();
  
  // returns a mirror version of this picture
  IPicture mirror();
  
  // returns a string, describing the contents of a picture, with verbosity
  // dependent on the given integer representing its depth
  String pictureRecipe(int depth);
}

// represents a picture with its kind, and size
class Shape implements IPicture {
  String kind;
  int size;

  Shape(String kind, int size) {
    this.kind = kind;
    this.size = size;
  }
  
  // returns the overall width of the picture
  public int getWidth() {
    return this.size;
  }
  
  // returns the number of shapes of the picture
  public int countShapes() {
    return 1;
  }
  
  // returns the max depth of operations of a picture
  public int comboDepth() {
    return 0;
  }
  
  // returns a mirror version of this picture
  public IPicture mirror() {
    return this;
  }
  
  // returns a string, describing the contents of a picture, with verbosity
  // dependent on the given integer representing its depth
  public String pictureRecipe(int depth) {
    return this.kind;
  }
}

// represents a combo of pictures, including the description,
// the first and second shapes, and the type of operation
class Combo implements IPicture {
  String name;
  IOperation operation;

  Combo(String name, IOperation operation) {
    this.name = name;
    this.operation = operation;
  }
  
  // returns the overall width of the picture
  public int getWidth() {
    return this.operation.getWidth();
  }
  
  // returns the number of shapes of the picture
  public int countShapes() {
    return this.operation.countShapes();
  }
  
  // returns the max depth of operations of a picture
  public int comboDepth() {
    return 1 + this.operation.comboDepth();
  }
  
  // returns a mirror version of this picture
  public IPicture mirror() {
    return new Combo(this.name, this.operation.mirror());
  }
  
  // returns a string, describing the contents of a picture, with verbosity
  // dependent on the given integer representing its depth
  public String pictureRecipe(int depth) {
    if (depth < 1) {
      return this.name;
    }
    else
      return this.operation.pictureRecipe(depth);
  }
}

// represents an operation done to one or more shapes
interface IOperation {
  // return the overall width of this operation
  int getWidth();
  
  // return the number of shapes in this operation
  int countShapes();
  
  // return the depth of combos of the picture in this operation
  int comboDepth();
  
  // return the mirror version of the operation
  IOperation mirror();
  
  // return the string describing the operation and the picture(s),
  // based on the given depth
  String pictureRecipe(int depth);
}

// represents scaling an image by a factor of 2
class Scale implements IOperation {
  IPicture picture;

  Scale(IPicture picture) {
    this.picture = picture;
  }
  
  // return the overall width of this operation
  public int getWidth() {
    return 2 * this.picture.getWidth();
  }
  
  // return the number of shapes in this operation
  public int countShapes() {
    return this.picture.countShapes();
  }
  
  // return the depth of combos of the picture in this operation
  public int comboDepth() {
    return this.picture.comboDepth();
  }
  
  // return the mirror version of the operation
  public IOperation mirror() {
    return new Scale(this.picture.mirror());
  }
  
  // return the string describing the operation and the picture(s),
  // based on the given depth
  public String pictureRecipe(int depth) {
    return "scale(" + this.picture.pictureRecipe(depth-1) + ")";
  }
}

// represents putting 2 images beside each other
class Beside implements IOperation {
  IPicture picture1;
  IPicture picture2;

  Beside(IPicture picture1, IPicture picture2) {
    this.picture1 = picture1;
    this.picture2 = picture2;
  }
  
  // return the overall width of this operation
  public int getWidth() {
    return this.picture1.getWidth() + this.picture2.getWidth();
  }
  
  // return the number of shapes in this operation
  public int countShapes() {
    return this.picture1.countShapes() + this.picture2.countShapes();
  }
  
  // return the depth of combos of the picture in this operation
  public int comboDepth() {
    return Math.max(this.picture1.comboDepth(), this.picture2.comboDepth());
  }
  
  // return the mirror version of the operation
  public IOperation mirror() {
    return new Beside(this.picture2.mirror(), this.picture1.mirror());
  }
  
  // return the string describing the operation and the picture(s),
  // based on the given depth
  public String pictureRecipe(int depth) {
    return
        "beside(" +
        this.picture1.pictureRecipe(depth-1) +
        ", " +
        this.picture2.pictureRecipe(depth-1) +
        ")";
  }
}

//represents putting one image on top of another
class Overlay implements IOperation {
  IPicture top;
  IPicture bottom;

  Overlay(IPicture top, IPicture bottom) {
    this.top = top;
    this.bottom = bottom;
  }
  
  // return the overall width of this operation
  public int getWidth() {
    return Math.max(this.top.getWidth(), this.bottom.getWidth()); 
  }
  
  // return the number of shapes in this operation
  public int countShapes() {
    return this.top.countShapes() + this.bottom.countShapes();
  }
  
  // return the depth of combos of the picture in this operation
  public int comboDepth() {
    return Math.max(this.top.comboDepth(), this.bottom.comboDepth());
  }
  
  // return the mirror version of the operation
  public IOperation mirror() {
    return new Overlay(this.top.mirror(), this.bottom.mirror());
  }
  
 //return the string describing the operation and the picture(s),
 // based on the given depth
 public String pictureRecipe(int depth) {
   return
       "overlay(" +
       this.top.pictureRecipe(depth-1) +
       ", " +
       this.bottom.pictureRecipe(depth-1) +
       ")";
 }
}

// represents examples of Picture
class ExamplesPicture {
  ExamplesPicture() {
  }
  
  IPicture circle = new Shape("circle", 20);
  IPicture square = new Shape("square", 40);
  IPicture bigCircle = new Combo("big circle",
      new Scale(this.circle));
  IPicture squareOnCircle = new Combo("square on circle",
      new Overlay(this.square, this.bigCircle));
  IPicture doubledSquareOnCircle = new Combo("doubled square on circle",
      new Beside(this.squareOnCircle, this.squareOnCircle));
  IPicture circleBesideSquareOnCircle = new Combo("circle beside square on circle",
      new Beside(this.circle, this.squareOnCircle));
  IPicture circleAndSquareOnCircle = new Combo("circle and square on circle",
      new Overlay(new Combo("circle and square",
          new Beside(this.circle, this.square)), this.bigCircle));
  
  // tests
  boolean testGetWidth(Tester t) {
    return
        t.checkExpect(this.circle.getWidth(), 20) &&
        t.checkExpect(this.square.getWidth(), 40) &&
        t.checkExpect(this.bigCircle.getWidth(), 40) &&
        t.checkExpect(this.squareOnCircle.getWidth(), 40) &&
        t.checkExpect(this.doubledSquareOnCircle.getWidth(), 80);
  }
  boolean testCountShapes(Tester t) {
    return
        t.checkExpect(this.circle.countShapes(), 1) &&
        t.checkExpect(this.square.countShapes(), 1) &&
        t.checkExpect(this.bigCircle.countShapes(), 1) &&
        t.checkExpect(this.squareOnCircle.countShapes(), 2) &&
        t.checkExpect(this.doubledSquareOnCircle.countShapes(), 4);
  }
  boolean testComboDepth(Tester t) {
    return
        t.checkExpect(this.circle.comboDepth(), 0) &&
        t.checkExpect(this.square.comboDepth(), 0) &&
        t.checkExpect(this.bigCircle.comboDepth(), 1) &&
        t.checkExpect(this.squareOnCircle.comboDepth(), 2) &&
        t.checkExpect(this.doubledSquareOnCircle.comboDepth(), 3);
  }
  boolean testMirror(Tester t) {
    return
        t.checkExpect(this.circle.mirror(), this.circle) &&
        t.checkExpect(this.bigCircle.mirror(), this.bigCircle) &&
        t.checkExpect(this.doubledSquareOnCircle.mirror(), this.doubledSquareOnCircle) &&
        t.checkExpect(this.circleBesideSquareOnCircle.mirror(),
            new Combo("circle beside square on circle",
                new Beside(this.squareOnCircle, this.circle))) &&
        t.checkExpect(this.circleAndSquareOnCircle.mirror(),
            new Combo("circle and square on circle",
                new Overlay(new Combo("circle and square",
                    new Beside(this.square, this.circle)), this.bigCircle)));
  }
  boolean testPictureRecipe(Tester t) {
    return
        t.checkExpect(this.circle.pictureRecipe(0), "circle") &&
        t.checkExpect(this.bigCircle.pictureRecipe(0), "big circle") &&
        t.checkExpect(this.bigCircle.pictureRecipe(1), "scale(circle)") &&
        t.checkExpect(this.doubledSquareOnCircle.pictureRecipe(2),
            "beside(overlay(square, big circle), overlay(square, big circle))") &&
        t.checkExpect(this.doubledSquareOnCircle.pictureRecipe(3),
            "beside(overlay(square, scale(circle)), overlay(square, scale(circle)))");
  }
}