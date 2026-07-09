import tester.*;

// represent information about an image
class Image {
	int width; // in pixels
	int height; // in pixels
	String source;

	Image(int width, int height, String source) {
		this.width = width;
		this.height = height;
		this.source = source;
	}
	
	// return the area of the image (in pixels) given width and height
	int area() {
		return this.width * this.height;
	}
	
	// return the general size of the image
	String sizeString() {
		if (this.area() <= 10000) {
			return "small";
		}
		else if (this.area() > 10000 && this.area() <= 1000000) {
			return "medium";
		}
		else {
			return "large";
		}
	}
}

// represents examples of Image
class ExamplesImage {
	ExamplesImage() {
	}
	
	// examples
	Image img0 = new Image(50, 75, "img0.jpg");
	Image img1 = new Image(256, 512, "img1.jpg");
	Image img2 = new Image(1946, 2026, "img2.jpg");
	
	// tests
	boolean testSizeString(Tester t) {
		return t.checkExpect(img0.sizeString(), "small")
				&& t.checkExpect(img1.sizeString(), "medium")
				&& t.checkExpect(img2.sizeString(), "large");
	}
}