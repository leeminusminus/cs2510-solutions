import tester.*;

/* +-----------------+
 * | Estate          |
 * +-----------------+
 * | String type     |
 * | int rooms       |
 * | int price       |
 * | Address address +----------
 * +-----------------+         |
 *                             v
 *                      +---------------+
 *                      | Address       |
 *                      +---------------+
 *                      | int number    |
 *                      | String street |
 *                      | String city   |
 *                      +---------------+
 */

// to represent an Estate with it's type, number of rooms, price,
// and address
class Estate {
	String type;
	int rooms;
	int price; // in dollars
	Address address;
	
	Estate(String type, int rooms, int price, Address address) {
		this.type = type;
		this.rooms = rooms;
		this.price = price;
		this.address = address;
	}
}

// to represent an address with street number, street name, and city
class Address {
	int number;
	String street;
	String city;
	
	Address(int number, String street, String city) {
		this.number = number;
		this.street = street;
		this.city = city;
	}
}

interface ILoListing {
}

// To represent a list of Estate listings
class ConsLoListing implements ILoListing {
	Estate first;
	ILoListing rest;
	
	ConsLoListing(Estate first, ILoListing rest) {
		this.first = first;
		this.rest = rest;
	}
}

// To represent an empty list of Estate listings
class MtListing implements ILoListing {
	MtListing() {
	}
}

// to represents examples of Estate and ILoListing
class ExamplesEstateILoListing {
	ExamplesEstateILoListing() {
	}
	
	Address addr0 = new Address(23, "Maple Street", "Brookline");
	Address addr1 = new Address(5, "Joye Road", "Newton");
	Address addr2 = new Address(83, "Winslow Road", "Waltham");
	
	Estate estate0 = new Estate("Ranch", 7, 375000, this.addr0);
	Estate estate1 = new Estate("Colonial", 9, 450000, this.addr1);
	Estate estate2 = new Estate("Cape", 6, 235000, this.addr2);
	
	ILoListing listing0 = new MtListing();
	ILoListing listing1 = new ConsLoListing(this.estate0,
			new ConsLoListing(this.estate1,
					new ConsLoListing(this.estate2,
							this.listing0)));
}