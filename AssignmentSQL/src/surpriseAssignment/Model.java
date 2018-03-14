package surpriseAssignment;

import java.util.Scanner;

public class Model {

	Scanner sc = new Scanner(System.in);
	DAO nDao = new DAO();
	String passWord;
	String choice;
	String qA;
	String qB;
	String qC;
	String aA;
	String aB;
	String aC;

	public void newSecure() {	// Input Security Question method	
		System.out.println("Please choose a Security Question A");
		System.out.println("A) What is your favourite fruit?");
		System.out.println("B) What is your favourite colour?");
		System.out.println("C) What is your Mother's name?");
		String choice = sc.nextLine(); 
		this.choice=choice;
		
		// choose and store security questions into database - from SQL
		
		if (choice.equals("a") || choice.equals("A")) {
			newSecureA(choice); } // If choice is "a" or "A", goes to method optionA()
		else if (choice.equals("b") || choice.equals("B")) {	
			newSecureB(choice); } // If choice is "b" or "B", goes to method optionB()
		else if (choice.equals("c") || choice.equals("C")) {
			newSecureC(choice); // If choice is "c" or "C", goes to method optionC()
		} else {
			System.out.println("Please choose either option (A), (B) or (C)");
			System.out.println("");
			newSecure(); // Goes back to newSecure() method
	}
}
	public void newSecureA(String choice) {	// Input Security Question method	
		System.out.println("Please enter answer for Security Question A (1 Word)");
		String aA = sc.nextLine();
		if (aA.matches("([\\w\\-.]+)")) {
		this.aA=aA;
		new Controller().secure(choice, aA); // sends to new Controller().secure() method to update into database
		} else { 
		System.out.println("Answer for Security Question A has to be 1 Word only. Please try again");
		newSecureA(choice);		
	}
}
	public void newSecureB(String choice) {	// Input Security Question method	
		System.out.println("Please enter answer for Security Question B (1 Word)");
		String aB = sc.nextLine();
		if (aB.matches("([\\w\\-.]+)")) {
		this.aB=aB;
		new Controller().secure(choice, aB); // sends to new Controller().secure() method to update into database
		} else { 
		System.out.println("Answer for Security Question C has to be 1 Word only. Please try again");
		newSecureB(choice);		
	}
}
	public void newSecureC(String choice) {	// Input Security Question method	
		System.out.println("Please enter answer for Security Question C (1 Word)");
		String aC = sc.nextLine();
		if (aC.matches("([\\w\\-.]+)")) {
		this.aC=aC;
		new Controller().secure(choice, aC); // sends to new Controller().secure() method to update into database
		} else { 
		System.out.println("Answer for Security Question C has to be 1 Word only. Please try again");
		newSecureC(choice);		
	}
}
	
	public void security() {	// Security Question method
		System.out.println("Please enter your Email Address (Login ID): ");
		String enEm = sc.nextLine(); 
		
		// Checks with database to check previous Security Questions
		
	}
	
	public void optionA() {		
		System.out.println("A) What is your favourite fruit?");
		System.out.println("Your answer? : ");
		String ansA = sc.nextLine();
		if (ansA.equals(aA)) {
			System.out.println("Please proceed to your account");
			newPw(); // proceeds to new password entry
		} else {
			System.out.println("Wrong answer. Please try again");
			System.out.println("");
			security();
		}
	}
	public void optionB() {
		System.out.println("B) What is your favourite colour?");
		System.out.println("Your answer? : ");
		String ansB = sc.nextLine();
		if (ansB.equals(aB)) {
			System.out.println("Please proceed to your account");
			newPw(); // proceeds to new password entry
		} else {
			System.out.println("Wrong answer. Please try again");
			System.out.println("");
			security();
		}
	}	
	public void optionC() {
		System.out.println("C) What is your Mother's name?");
		System.out.println("Your answer? : ");
		String ansC = sc.nextLine();
		if (ansC.equals(aC)) {
			System.out.println("Please proceed to your account");
			newPw(); // proceeds to new password entry
		} else {
			System.out.println("Wrong answer. Please try again.");
			System.out.println("");
			security();
		}
	}
	public void newPw() { // Enter new password
		System.out.println("Please enter your new password (alphanumeric only) ");
		String passWord = sc.nextLine();
		if (passWord.matches("^[a-zA-Z0-9]*$")) { // Password has to be alphanumeric 
			rePw(passWord); // If password is alphanumeric, proceed to rePw()
		} else {
			System.out.println("New password has to be alphanumeric only. Please try again ");
			System.out.println("");
			newPw();
		} // If password is not alphanumeric only, retry
		}
	public void rePw(String passWord) {
		System.out.println("Please reenter your password");
		String rePw = sc.nextLine();
		if (rePw.equals(passWord)) {
		this.passWord=passWord;
		System.out.println("*Password updated*");
		System.out.println("*Details updated into the system*");
		new Controller().newPassw(passWord);
		// Update new password to system
		// Number of attempts reset to 0
		
		System.out.println("*Back to Login Menu*");
		new Controller().login();
	} else {
		System.out.println("Invalid entry. Please try again");
		System.out.println("");
		newPw();
	}
}
}