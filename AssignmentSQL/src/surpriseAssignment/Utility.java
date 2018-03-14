package surpriseAssignment;

import java.util.Scanner;

public class Utility {
	
	DAO nDao = new DAO();
	
	Scanner sc = new Scanner(System.in);
	
	public void hiAdmin() {
		System.out.println("Welcome Admin! ");
		System.out.println("1) Register New User "); // Inputs new user into the database
		System.out.println("2) View Userlist "); // Lists all users on the list with their status
		System.out.println("3) Logout "); // Logout
		System.out.println("Please choose a question to answer (1), (2) or (3)): ");
		String adminOptions = sc.nextLine();
		if (adminOptions.equals("1")) {
			nDao.name();
		} else if (adminOptions.equals("2")) {			
			System.out.println("*Userlist as follows*");
			nDao.userList();
			System.out.println("1) Change user status");
			System.out.println("2) Back");
			String selection = sc.nextLine();
			if (selection.equals("1")) {
				System.out.println("Which user would you want to edit status? "); // Select user that you want to edit
				// change user status
				String loginid = sc.nextLine();
				System.out.println("0 to Lock or 1 to unlock?");
				String lockUnlock = sc.nextLine();
				nDao.lockAcct(loginid, lockUnlock);
				System.out.println("*status updated*\n");
				
				hiAdmin();
			} else if (selection.equals("2")) {
				hiAdmin();
			} else {
				System.out.println("Invalid option");
				hiAdmin();
			}
				
		} else if (adminOptions.equals("3")) {			
			System.out.println("Logout successful. Goodbye"); // If option is (3), Logout successful. Goodbye
		} else {  
			System.out.println("Invalid selection. Please select again");
			hiAdmin(); // Goes back to hiAdmin() method
	}
	}
	
		public void plsLogout() {
			System.out.println("Welcome! " + nDao.getNa());
			System.out.println("Logout? (Y/N) ");
			String logOut = sc.nextLine();
			if (logOut.equals("y") || logOut.equals("Y")) {
			System.out.println("Logout successful. Goodbye"); // If option is (Y), Goodbye
			} else if (logOut.equals("n") || logOut.equals("N")) {			
				new Controller().plsLogin(); // Goes back to plsLogin() method
			} else {  
				System.out.println("Invalid selection. Please select again");
				plsLogout(); // Goes back to plsLogout() method
		}
	}
		
		public void welcome() {
			System.out.println("Welcome! " + nDao.getNa());
			System.out.println("Please enter your temp password:"); // Prompts user to enter their temp password
			String tPwin = sc.nextLine();
			if (tPwin.equals(nDao.getPassWord())) {
			new Model().newPw();  // proceeds to new password entry
			} else {  
				System.out.println("Wrong password. Please try again");
				welcome(); // Goes back to welcome() method
			}
		}
	}