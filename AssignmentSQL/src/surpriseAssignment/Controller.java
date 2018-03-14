package surpriseAssignment;

import java.util.Scanner;

public class Controller {

	DAO nDao = new DAO();
	String enterLogin;
	String enterPwd;
	String loginid; 
	String firstLog;
	int noOfAttempts = 0;
	String lock = "1";

	Scanner sc = new Scanner(System.in);

	public void login() {
		System.out.println("Please select your option"); // Opening statement for users when they run
		System.out.println("1) Login");
		System.out.println("2) Forget Password");
		String option = sc.nextLine();
		if (option.equals("1")) {
			plsLogin(); } // If choice is 1, goes to method plsLogin()
		else if (option.equals("2")) {	
			new Model().security(); // If choice is 2, goes to method forgotPw()
		} else {
			System.out.println("Please choose either option (1) or (2)");
			System.out.println("");
			login(); // Goes back to login() method
		}
	}
	public void plsLogin() { // Login Page for all users
		System.out.println("Please enter your Email Address (Login ID):");
		String enterLogin = sc.nextLine();
		System.out.println("Login name is: " + enterLogin); // Displays login name for the user
		System.out.println("");
		System.out.println("Please enter your password:");
		String enterPwd = sc.nextLine();

		if(nDao.loginAuthenticate(enterLogin, enterPwd) == true) {
			if (enterLogin.equals("admin@theoptimum.net") && enterPwd.equals("root")) {
				new Utility().hiAdmin();
				// First time login - from SQL		
			} else {
					new Utility().welcome();
			}
		}

		else if(nDao.loginAuthenticate(enterLogin, enterPwd) == false) {
			noOfAttempts = noOfAttempts + 1;
			nDao.getLoginAttempts(enterLogin, noOfAttempts);
			if (noOfAttempts >= 3) {
				System.out.println("3 or more failed login attempts. Account is locked");
				System.out.println("Please contact your System Administrator");
				lock = "0"; // database update status to "lock" (0 instead of 1) - from SQL
				nDao.lockAcct(enterLogin, lock);
			} else {
				System.out.println("Login failed. Retry");
				System.out.println("");
				plsLogin(); // If login fails, go back to login method to redo login
			}
		}
	}
	public void secure(String sQ, String sA) {
		nDao.secQn(enterLogin, sQ, sA); // updating security question and answer to SQL based on the Login ID
	}
	public void newPassw(String nP) {
		noOfAttempts = 0;
		nDao.newPassWord(enterLogin, nP, noOfAttempts); // updating answer and number of attempts to SQL based on the Login ID
	}

}
