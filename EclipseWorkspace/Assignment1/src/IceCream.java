import tester.*;

/*                    +-----------+
 *                    | IIceCream |<------------------+
 *                    +-----------+                   |         
 *                    +-----------+                   |          
 *                          |                         |
 *                         / \                        |
 *                         ---                        |
 *                          |                         |
 *          ---------------------------------         |     
 *          |                               |         |
 * +-----------------+              +---------------+ |
 * | EmptyServing    |              | Scooped       | |
 * +-----------------+              +---------------+ |
 * | boolean hasCone |              | IceCream more |-+
 * +-----------------+              | String flavor |
 *                                  +---------------+
 */                                 

// To represent an ice cream cone or cup
interface IIceCream {
}

// To represent a scoop of ice cream, with the rest of the ice cream,
// and its flavor
class Scooped implements IIceCream {
  IIceCream more;
  String flavor;
  
  Scooped(IIceCream more, String flavor) {
    this.more = more;
    this.flavor = flavor;
  }
}

// To represent the end of an ice cream, with either a cone or a cup
class EmptyServing implements IIceCream {
  boolean hasCone;
  
  EmptyServing(boolean hasCone) {
    this.hasCone = hasCone;
  }
}

// To represent examples of IceCream
class ExamplesIceCream {
  ExamplesIceCream() {
  }
  
  IIceCream cone = new EmptyServing(true);
  IIceCream cup = new EmptyServing(false);
  
  IIceCream ic0 = new Scooped(
      new Scooped(
          new Scooped(
              new Scooped(this.cup,
                  "mint chip"),"coffee"), "black raspberry"), "caramel swirl");
  IIceCream ic1 = new Scooped(
      new Scooped(
          new Scooped(this.cone,
              "chocolate"),"vanilla"), "strawberry");
  IIceCream ic2 = new Scooped(this.cone,"cookies & cream");
}