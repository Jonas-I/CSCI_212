/** Jonas Improgo
 *  This program is an ATM simulation that uses an Account class to create 10 different accounts, in which you can
 *  check your balance, withdraw, and deposit money, as well as alternate between the accounts.
 *  
 *  People who assisted me: Josh Improgo
 *  
 *  Krishna CSCI 212 Fall 2018
 *  Assignment 3
 */
import java.time.LocalDate;
import java.util.Scanner;

public class ATM9615
{
	static class Account
	{
		// Instance Variables
		private int id;
		private double balance;
		private double annualInterestRate;
		private LocalDate dateCreated;
	
		/**
		 * Default Constructor, no parameters
		 */
		Account()
		{
			id = -1;
			balance = 100.0;
			annualInterestRate = 0;
			dateCreated = LocalDate.now();
		}
		
		// Parameterized Constructors
		/**
		 * Constructor w/ 1 Parameter for id
		 * @param id Sets this.id into the id from parameter
		 */
		Account(int id)
		{
			this.id = id;
			balance = 100.0;
			annualInterestRate = 0;
			dateCreated = LocalDate.now();
		}
		
		/**
		 * Constructor w/ 2 Parameters for id and balance
		 * @param id Sets this.id into int id
		 * @param bal Sets balance to double bal
		 */
		Account(int id, double bal)
		{
			this.id = id;
			balance = bal;
			annualInterestRate = 0;
			dateCreated = LocalDate.now();
		}
		
		/**
		 * Constructor w/ 3 parameters for id, balance, and annualInterestRate
		 * @param id Sets this.id into int id
		 * @param bal Sets balance into double bal
		 * @param anIntR Sets annualInterestRate to double anIntR
		 */
		Account(int id, double bal, double anIntR)
		{
			this.id = id;
			balance = bal;
			annualInterestRate = anIntR;
			dateCreated = LocalDate.now();
		}
		
		/**
		 * Constructor w/ 6 parameters for id, balance, annualInterestRate, and dateCreated
		 * @param id Sets this.id into int id
		 * @param bal Sets balance into double bal
		 * @param anIntR Sets annualInterestRate to anIntR
		 * @param year Sets the year of dateCreated to int year
		 * @param month Sets the month of dateCreated to int month
		 * @param day Sets the day of dateCreated to int day
		 */
		Account(int id, double bal, double anIntR, int year, int month, int day)
		{
			this.id = id;
			balance = bal;
			annualInterestRate = anIntR;
			dateCreated = LocalDate.of(year, month, day);
		}
		
		//Accessor Methods
		/**
		 * Gets the id of the account
		 * @return Returns an int
		 */
		public int getID() {
			return id;
		}
		
		/**
		 * Gets the balance of the account
		 * @return Returns a double
		 */
		public double getBalance() {
			return balance;
		}
		
		/**
		 * Gets the annualInterestRate of the account
		 * @return Returns a double
		 */
		public double getAnnualInterestRate() {
			return annualInterestRate;
		}
		
		/**
		 * Gets the dateCreated of the account
		 * @return Returns LocalDate
		 */
		public LocalDate getDate() {
			return dateCreated;
		}
		
		//Mutator Methods
		
		/**
		 * Allows changing the id
		 * @param i Sets id to int i
		 */
		public void setID(int i) {
			id = i;
		}
		
		/**
		 * Allows changing the balance
		 * @param i Sets balance to double i
		 */
		public void setBalance(double i) {
			balance = i;
		}
		
		/**
		 * Allows changing the annualInterestRate
		 * @param i Sets annualInterestRate to double i
		 */
		public void setAnnualInterestRate(double i) {
			annualInterestRate = i;
		}
		
		/**
		 * Allows changing the date
		 * @param date Sets dateCreated to LocalDate date
		 */
		public void setDate(LocalDate date) {
			dateCreated = date;
		}
		
		/**
		 * Allows changing the date
		 * @param year Sets the year of dateCreated to int year
		 * @param month Sets the month of dateCreated to int month
		 * @param day Sets the day of dateCreated to int day
		 */
		public void setDate(int year, int month, int day) {
			dateCreated = LocalDate.of(year, month, day);
		}
		
		
		/**
		 * A method to calculate the monthly interest rate from the percentage annualInterestRate
		 * @return Returns a double
		 */
		public double getMonthlyInterestRate() {
			return (annualInterestRate/100)/12;
		}
		
		/**
		 * A method to get the amount of money earned per month according to the balance
		 * @return Returns a double
		 */
		public double getMonthlyInterest() {
			return getMonthlyInterestRate()*balance;
		}
		
		/**
		 * A method to subtract money from the balance, if less than 0, does nothing
		 * @param i Subtracts a double i from balance
		 */
		public void withdraw(double i) {
			if (balance - i >= 0) balance -=i;
		}
		
		/**
		 * A method to add money from the balance, if less than 0, does nothing
		 * @param i Adds a double i to balance
		 */
		public void deposit(double i) {
			if (i >= 0) balance += i;
		}
		
		/**
		 * A toString method that prints the balance, monthly interest, and date the account was created
		 */
		public String toString() {
			return "ID: " + id + "\nBalance: $" + String.format("%.2f", balance) + "\nMonthly Interest: $" + 
					String.format("%.2f", getMonthlyInterest()) + "\nDate Created: " + dateCreated;
		}
		
		/**
		 * An equals method that returns true if the id, balance, annualInterestRate, and dateCreated is equal to
		 * the Object, Account other and their instance variables
		 * @param other An Account other that is used to compare other's instance variables
		 * @return Returns a boolean, if all instance variables are equal to each other
		 */
		public boolean equals(Account other)
		{
			return (this.id == other.id && this.balance == other.balance && 
					this.annualInterestRate == other.annualInterestRate && 
					this.dateCreated.equals(other.dateCreated));
		}
		
	}
	
	public static void main(String[] args)
	{
		System.out.println("==========TEST==========");
		Account test = new Account(1122, 20000.0, 4.5);
		System.out.println(test);
		test.withdraw(2500);
		System.out.println("\n" + test);
		test.deposit(3000);
		System.out.println("\n" + test);
		System.out.println("==========TEST==========");
		
		//Creates a 1D array of 10 accounts
		Account[] tenAcc = new Account[10];
		
		//A for loop used to set the Account's IDs
		for (int i = 0; i < 10; i++) {
			tenAcc[i] = new Account(i);
		}
		
		
		Scanner input = new Scanner(System.in);
		
		// A while loop used to keep the user in an ATM Simulation
		boolean inATM = true;
		while (inATM)
		{
			System.out.println("Enter an id: ");
			int idInput = input.nextInt();
			
			// A while loop to check if the user's input is valid, a # 1-9
			while (!(idInput >= 0 && idInput <= 9))
			{
				System.out.println("Invalid ID.\nEnter an id: ");
				idInput = input.nextInt();
			}
			
			// A while loop for the Main Menu
			boolean exit = false;
			while (!exit)
			{
	
				// Prints the Main Menu
				System.out.println("Main Menu: \n1: Check Balance\n2: Withdraw\n3: Deposit\n4: Exit\nEnter a Number: ");
				int choice = input.nextInt();
				
				//If the user's input is not between 1 and 4, prompts the user to enter the values again
				while (!(choice >= 1 && choice <= 4))
				{
					System.out.println("Invalid Number.\n");
					System.out.println("Main Menu: \n1: Check Balance\n2: Withdraw\n3: Deposit\n4: Exit\nEnter a Number: ");
					choice = input.nextInt();
				}
				
				
				//If the user's choice is equal to 1, then prints balance
				if (choice == 1)
				{
					System.out.println("Your current balance is: $" + String.format("%.2f", tenAcc[idInput].getBalance()));
				}
				
				//If the user's choice is equal to 2, prompts the user to enter an amount to withdraw
				//and prints amount withdrawn
				if (choice == 2)
				{
					System.out.println("Enter the amount you want to withdraw: ");
					double amnt = input.nextDouble();
					
					//If the amnt is less than or equal to the account balance, then withdraw is possible
					if (amnt <= tenAcc[idInput].getBalance()) {
						tenAcc[idInput].withdraw(amnt);
						System.out.println("Withdrew $" + String.format("%.2f", amnt));
					}
					
					//If the amnt is greater than the balance, prints an ERROR that withdraw is not possible
					else {
						System.out.println("ERROR: Cannot withdraw money you don't have.");
					}
				}
				
				//If the user's choice is equal to 3, prompts the user to enter an amount to deposit
				//and prints amount deposited
				if (choice == 3)
				{
					System.out.println("Enter the amount you want to deposit: ");
					double amnt = input.nextDouble();
					
					//If the amnt is less than or equal to 0, then states that you cannot deposit
					if (amnt <= 0) System.out.println("ERROR: Cannot deposit $0.00 or less.");
					
					//If the amnt is greater than 0, then prints the amount deposited
					else {
						tenAcc[idInput].deposit(amnt);
						System.out.println("Deposited $" + String.format("%.2f", amnt));
					}
				}
				
				//If the user's choice is equal to 4, exits the Main Menu, and prompts the user
				//to enter an Account ID.
				if (choice == 4)
				{
					exit = true;
				}
				
				//Resets choice to 0 after the end of the while loop
				choice = 0;
			}
		}
	}
}