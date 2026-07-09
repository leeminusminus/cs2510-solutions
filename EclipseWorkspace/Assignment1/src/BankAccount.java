import tester.*;

// To represent one of three types of bank accounts
interface IBankAccount {
}

// To represent a checking account, with the ID number,
// name, and minimum balance
class CheckingAccount implements IBankAccount {
	String name;
	int id;
	int balance; // in dollars
	int minBalance; // in dollars
	
	CheckingAccount(String name, int id, int balance, int minBalance) {
		this.name = name;
		this.id = id;
		this.balance = balance;
		this.minBalance = minBalance;
	}
}

// To represent a savings account, with the ID number,
// name, and interest rate (in percent)
class SavingsAccount implements IBankAccount {
	String name;
	int id;
	int balance; // in dollars
	double interestRate; // in percent
	
	SavingsAccount(String name, int id, int balance, double interestRate) {
		this.name = name;
		this.id = id;
		this.balance = balance;
		this.interestRate = interestRate;
	}
}

// To represent a certificate of deposit with the ID number,
// name, interest rate (in percent), and maturity date.
class CD implements IBankAccount {
	String name;
	int id;
	int balance;
	double interestRate;
	String maturityDate;
	
	CD(String name, int id, int balance, double interestRate, String maturityDate) {
		this.name = name;
		this.id = id;
		this.balance = balance;
		this.interestRate = interestRate; // in percent
		this.maturityDate = maturityDate;
	}
}

// To represent examples of IBankAccount
class ExamplesIBankAccount {
	ExamplesIBankAccount() {
	}
	
	IBankAccount acc0 = new CheckingAccount("Earl Gray", 1729, 1250, 500);
	IBankAccount acc1 = new CD("Ima Flatt", 4104, 10123, 4.0, "June 1, 2005");
	IBankAccount acc2 = new SavingsAccount("Annie Proulx", 2992, 800, 3.5);
}
