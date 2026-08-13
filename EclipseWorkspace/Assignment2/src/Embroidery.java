import tester.*;

// represents an embroidery piece with its name and motif
class EmbroideryPiece {
  String name;
  IMotif motif;

  EmbroideryPiece(String name, IMotif motif) {
    this.name = name;
    this.motif = motif;
  }

  // returns the average difficulty of every motif in this piece
  double averageDifficulty() {
    return this.motif.averageDifficulty();
  }

  // returns a description of this embroidery, including each
  // of the motifs description, and type
  String embroideryInfo() {
    return this.name + ": " + this.motif.motifInfo() + ".";
  }
}

// represents a motif, or a design for an embroidery piece
interface IMotif {
  // returns the average difficulty of the whole motif
  double averageDifficulty();

  // returns the sum of all difficulties of the whole motif
  double sumDiff();

  // returns the number of motifs of the whole motif
  int count();

  // returns motif name(s) and type(s)
  String motifInfo();
}

// represents a cross-stitch motif with its description and
// difficulty represented by a number [0, 5], where 5
// the is most difficult
class CrossStitchMotif implements IMotif {
  String description;
  double difficulty;

  CrossStitchMotif(String description, double difficulty) {
    this.description = description;
    this.difficulty = difficulty;
  }

  // returns the average difficulty of the whole motif
  public double averageDifficulty() {
    return this.sumDiff();
  }

  // returns the sum of all difficulties of the whole motif
  public double sumDiff() {
    return this.difficulty;
  }

  // returns the number of motifs of the whole motif
  public int count() {
    return 1;
  }

  // returns motif name(s) and type(s)
  public String motifInfo() {
    return this.description + " (cross stitch)";
  }
}

// represents a chain-stitch motif with its description and
// difficulty represented by a number [0, 5], where 5
// the is most difficult
class ChainStitchMotif implements IMotif {
  String description;
  double difficulty;

  ChainStitchMotif(String description, double difficulty) {
    this.description = description;
    this.difficulty = difficulty;
  }

  // returns the average difficulty of the whole motif
  public double averageDifficulty() {
    return this.sumDiff();
  }

  // returns the sum of all difficulties of the whole motif
  public double sumDiff() {
    return this.difficulty;
  }

  // returns the number of motifs of the whole motif
  public int count() {
    return 1;
  }

  // returns motif name(s) and type(s)
  public String motifInfo() {
    return this.description + " (chain stitch)";
  }
}

//represents a list of motifs
interface ILoMotif {
// returns the sum of the difficulties in this list
  double sumDiff();

//returns the sum of the difficulties next
  double sumDiffHelper(double curr);

// returns the number of motifs in this list
  int count();

// returns the number of motifs next
  int countHelper(int curr);

// returns the info of all motifs in this list
  String motifInfoAll();
}

//represents an empty list of motifs
class MtLoMotif implements ILoMotif {
  MtLoMotif() {
  }

// returns the sum of the difficulties in this list
  public double sumDiff() {
    return 0.0;
  }

//returns the sum of the difficulties next
  public double sumDiffHelper(double curr) {
    return 0.0;
  }

// returns the number of motifs in this list
  public int count() {
    return 0;
  }

// returns the number of motifs next
  public int countHelper(int curr) {
    return 0;
  }

// returns the info of all motifs in this list
  public String motifInfoAll() {
    return "";
  }
}

//a non-empty list of motifs
class ConsLoMotif implements ILoMotif {
  IMotif first;
  ILoMotif rest;

  ConsLoMotif(IMotif first, ILoMotif rest) {
    this.first = first;
    this.rest = rest;
  }

// returns the sum of the difficulties in this list
  public double sumDiff() {
    return this.sumDiffHelper(0.0);
  }

//returns the sum of the difficulties next
  public double sumDiffHelper(double curr) {
    return curr + this.first.sumDiff() + this.rest.sumDiff();
  }

// returns the number of motifs in this list
  public int count() {
    return this.countHelper(0);
  }

// returns the number of motifs next
  public int countHelper(int curr) {
    return curr + this.first.count() + this.rest.count();
  }

// returns the info of all motifs in this list
  public String motifInfoAll() {
    if (rest.count() > 0) {
      return this.first.motifInfo() + ", " + this.rest.motifInfoAll();
    }
    else {
      return this.first.motifInfo() + this.rest.motifInfoAll();
    }
  }
}

// represents a group motif, with and its description
// and a list of its motifs
class GroupMotif implements IMotif {
  String description;
  ILoMotif motifs;

  GroupMotif(String description, ILoMotif motifs) {
    this.description = description;
    this.motifs = motifs;
  }

  // returns the average difficulty of the whole motif
  public double averageDifficulty() {
    if (this.count() == 0) {
      return 0;
    }
    else {
      return this.sumDiff() / this.count();
    }
  }

  // returns the sum of all difficulties of the whole motif
  public double sumDiff() {
    return this.motifs.sumDiff();
  }

  // returns the number of motifs of the whole motif
  public int count() {
    return this.motifs.count();
  }

  // returns motif name(s) and type(s)
  public String motifInfo() {
    return this.motifs.motifInfoAll();
  }
}

// represents examples of EmbroideryPiece
class ExamplesEmbroidery {
  ExamplesEmbroidery() {
  }

  EmbroideryPiece mt = new EmbroideryPiece("Empty", new GroupMotif("empty", new MtLoMotif()));
  EmbroideryPiece blanket = new EmbroideryPiece("Blanket", new ChainStitchMotif("dog", 4.8));
  EmbroideryPiece carpet = new EmbroideryPiece("Carpet", new CrossStitchMotif("cat", 3.9));
  EmbroideryPiece shirt = new EmbroideryPiece("Shirt",
      new GroupMotif("keyboard & mouse", new ConsLoMotif(new CrossStitchMotif("keyboard", 5.0),
          new ConsLoMotif(new ChainStitchMotif("mouse", 2.0), new MtLoMotif()))));
  EmbroideryPiece pillowCover = new EmbroideryPiece("Pillow Cover",
      new GroupMotif("nature",
          new ConsLoMotif(new CrossStitchMotif("bird", 4.5),
              new ConsLoMotif(new ChainStitchMotif("tree", 3.0),
                  new ConsLoMotif(
                      new GroupMotif("flowers",
                          new ConsLoMotif(new CrossStitchMotif("rose", 5.0),
                              new ConsLoMotif(new ChainStitchMotif("poppy", 4.75), new ConsLoMotif(
                                  new CrossStitchMotif("daisy", 3.2), new MtLoMotif())))),
                      new MtLoMotif())))));

  // tests
  boolean testAverageDifficulty(Tester t) {
    return t.checkInexact(this.mt.averageDifficulty(), 0.0, 0.01)
        && t.checkInexact(this.blanket.averageDifficulty(), 4.8, 0.01)
        && t.checkInexact(this.carpet.averageDifficulty(), 3.9, 0.01)
        && t.checkInexact(this.shirt.averageDifficulty(), 3.5, 0.01)
        && t.checkInexact(this.pillowCover.averageDifficulty(), 4.09, 0.01);
  }

  boolean testEmbroideryInfo(Tester t) {
    return t.checkExpect(this.mt.embroideryInfo(), "Empty: .")
        && t.checkExpect(this.blanket.embroideryInfo(), "Blanket: dog (chain stitch).")
        && t.checkExpect(this.carpet.embroideryInfo(), "Carpet: cat (cross stitch).")
        && t.checkExpect(this.shirt.embroideryInfo(),
            "Shirt: keyboard (cross stitch), mouse (chain stitch).")
        && t.checkExpect(this.pillowCover.embroideryInfo(),
            "Pillow Cover: bird (cross stitch), tree (chain stitch), rose (cross stitch), poppy (chain stitch), daisy (cross stitch).");
  }
}