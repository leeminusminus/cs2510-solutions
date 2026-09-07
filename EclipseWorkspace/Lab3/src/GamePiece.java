import tester.*;

// represents a game piece in 2048
interface IGamePiece {
	// returns the value of a game piece
	int getValue();
	// returns a merged game piece based off of a given piece
	MergeTile merge(IGamePiece piece);
	// is this game piece valid according to the rules of 2048?
	boolean isValid();
}

// represents a base tile
class BaseTile implements IGamePiece {
	int value;
	// Constructs a game piece based on piece1 and piece2
	BaseTile(int value) {
		this.value = value;
	}
	// returns the value of a game piece
	public int getValue() {
		return value;
	}
	//returns a merged game piece based off of a given piece
  public MergeTile merge(IGamePiece piece) {
    return new MergeTile(this, piece);
  }
  // is this game piece valid according to the rules of 2048?
  public boolean isValid() {
    return true;
  }
}
// represents a merger of two tiles
class MergeTile implements IGamePiece {
	IGamePiece piece1;
	IGamePiece piece2;
	// Constructs a game piece based on piece1 and piece2
	MergeTile(IGamePiece piece1, IGamePiece piece2) {
		this.piece1 = piece1;
		this.piece2 = piece2;
	}
	// returns the value of a game piece
	public int getValue() {
		return this.piece1.getValue() + this.piece2.getValue();
	}
	 //returns a merged game piece based off of a given piece
  public MergeTile merge(IGamePiece piece) {
    return new MergeTile(this, piece);
  }
  // is this game piece valid according to the rules of 2048?
  public boolean isValid() {
    return this.piece1.getValue() == this.piece2.getValue();
  }
}
// represents examples of game piece
class ExamplesGamePiece {
	ExamplesGamePiece() {
	}
	
	IGamePiece base1 = new BaseTile(2);
	IGamePiece merge1 = new MergeTile(this.base1, this.base1);
	
	// tests
	boolean testGetValue(Tester t) {
		return
		    t.checkExpect(this.base1.getValue(), 2) &&
		    t.checkExpect(this.merge1.getValue(), 4);
	}
	boolean testMerge(Tester t) {
	  return
	      t.checkExpect(this.base1.merge(this.base1), this.merge1) &&
	      t.checkExpect(this.merge1.merge(this.base1), new MergeTile(this.merge1, this.base1));
	}
	boolean testIsValid(Tester t) {
	  return
	      t.checkExpect(base1.isValid(), true) &&
	      t.checkExpect(merge1.isValid(), true) &&
	      t.checkExpect(this.merge1.merge(this.base1).isValid(), false);
	}
}