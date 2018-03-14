package surpriseAssignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DAO {
	
	DatabaseConnection refdata = new DatabaseConnection();
	private Connection con = DatabaseConnection.getInstance().getConnection();
	private Statement statement;
	private PreparedStatement preparedStatement =null;
	public static boolean authenticate;
	String passWord;
	String na;
	String icno;
	String dno;
	String em;
	String mno;
	String loginid;
	String firstLog;

	Scanner sc = new Scanner(System.in);
	
	public boolean loginAuthenticate(String loginid,String password) {
		
		 try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from users where email=\'" + loginid + "\'");
			
			while(rs.next()) {
				
				String loginData = rs.getString("email");
				String passData = rs.getString("password");
				
				if(loginData.equals(loginid)&&passData.equals(password)) {
					return true;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 	return false;
   	}
	public void registerUser(String name,String nric,String dob,String email,String mobile,String password) {
		
		 try {
			 preparedStatement = con.prepareStatement("insert into users(name,nric,email,date_of_birth,mobile,password,role,first_login,status,no_attempts) "
			 		+ "values (?, ?, ?, ?, ?, ?, 'user',1,1,?)");
			 
			 preparedStatement.setString(1, name);
			 preparedStatement.setString(2, nric);
			 preparedStatement.setString(3, email);
			 preparedStatement.setString(4, dob);
			 preparedStatement.setInt(5, Integer.parseInt(mobile));
			 preparedStatement.setString(6, password);
			 preparedStatement.setInt(7, 0);
			 preparedStatement.executeUpdate();
			}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getLoginAttempts(String loginid, int noAttempts) { // update the no of login attempts
		
		 try {
			 preparedStatement = con.prepareStatement("UPDATE users no_attempts="+noAttempts+ " WHERE email=\'" + loginid + "\'");
			 preparedStatement.executeUpdate();
			}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
	}
	
	public void lockAcct(String loginid, String lockUnlock) { // update the lock / unlock status
		 try {
			 preparedStatement = con.prepareStatement("UPDATE users SET status= "+lockUnlock+ " WHERE email=\'" + loginid + "\'");
			 preparedStatement.executeUpdate();
			}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
	}
	
	public void secQn(String loginid, String secureQn, String secureAn) { // Insert Security Questions
		
		 try {
			 preparedStatement = con.prepareStatement("UPDATE users security question="
			 +secureQn+ "users security answer="+secureAn+ " WHERE email=\'" + loginid + "\'");
			 preparedStatement.executeUpdate();
			}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
	}
	
	public void newPassWord(String loginid, String newPass, int noAttempts) { // Insert Security Questions
		
		 try {
			 preparedStatement = con.prepareStatement("UPDATE users password="
			 +newPass+ "no_attempts="+noAttempts+ " WHERE email=\'" + loginid + "\'");
			 preparedStatement.executeUpdate();
			}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
	}
	
	public void userList() { 
			
			 try {
				statement = con.createStatement();
				ResultSet rs = statement.executeQuery("select * from users");
				
				while(rs.next()) {
					
					String loginData = rs.getString("email");
					String statusData = rs.getString("status");
					System.out.println(loginData + " " + statusData);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   	
		// Extracts current data in the user list - from SQL
			 
	}
		
	public void name() {	// Input Name
		System.out.println("Please enter your Name: ");
		String na = sc.nextLine();
		this.na=na;
		nric(); // after name input, proceed to nric method
	}
	public void nric() {	// Input NRIC
		System.out.println("Please enter your NRIC: ");
		String icno = sc.nextLine();
		if (icno.matches("[a-zA-Z][0-9]{7}[a-zA-Z]")) { // 1 alphabet, 7 numbers & 1 alphabet
			this.icno=icno;
			dob(); // after name input, proceed to dob method
		} else {
			System.out.println("Invalid input. Retry");	
			System.out.println("");
			nric(); // return to start of method
		}
	}
	public void dob() {		// Input DOB
		System.out.println("Please enter your DOB (DD/MM/YYYY): ");
		String dno = sc.nextLine();     
		if (dno.matches("(([0-3]{1}[0-9]{1})\\/([0-1]{1}[0-9]{1})\\/([1-2]{1}[0-9]{1}[0-9]{1}[0-9]{1}))")) { 
			// Restrict date formatting
			this.dno=dno;
			email(); 
		} else { 
			System.out.println("Invalid input. Retry");
			System.out.println("");
			dob(); // return to start of method
		}
	}
	public void email() {	// Input Email Address	
		System.out.println("Please enter your Email Address (Login ID): ");
		String em = sc.nextLine();
		if (em.matches("([\\w\\-.]+)@([\\w\\-.]+\\.[a-zA-Z]+)")) {  
/*		At least 1 Character at the start followed by any number of characters before "@"
		At least 1 Character after "@" and before "."
		At least 1 Alphabet after the "."*/
			this.em=em;
			mobile(); // after name input, proceed to dob method
		} else { 
			System.out.println("Invalid input. Retry");
			System.out.println("");
			email(); // return to start of method
		}
	}

	public void mobile() {	// Input Mobile Number
		System.out.println("Please enter your Mobile number: ");
		String mno = sc.nextLine();
		if (mno.matches("[\\d]{8}")) { // Phone number has to be 8 digits 
			this.mno=mno;
			validation(); 
		} else {
			System.out.println("Invalid input. Retry");
			System.out.println("");
			mobile();
		}
	}
	public void validation() { // Displays all input details
		System.out.println("");
		System.out.println("Name: " + na);
		System.out.println("NRIC: " + icno);
		System.out.println("D.O.B: " + dno);
		System.out.println("Email: " + em);
		System.out.println("Mobile: " + mno);
		System.out.println("");
		System.out.println("Details correct?");
		System.out.println("(Y) to confirm, (N) to redo input");
		String vans = sc.nextLine();
		if (vans.equals("y") || vans.equals("Y")) {
			System.out.println("*Details updated into the system*");
			passW(); // Goes to passW
		} else if (vans.equals("n") || vans.equals("N")) {			
			System.out.println("Please fill in the details again");
			name(); // Goes back to name() method
		} else {  
			System.out.println("Invalid selection. Please fill in the details again");
			name(); // Goes back to name() method
		}
	}
	public void passW() { // Creates temporary password
		String subStrIc = icno.substring(1, 5);
/*		System.out.println("substring IC No = " + subStrIc);		*/
		String subStrNo = mno.substring(4, 8);
/*		System.out.println("substring Mobile No = " + subStrNo);	*/
		passWord = subStrIc + subStrNo;
/*		System.out.println("Temporary password is " + passWord);	*/
		registerUser(na,icno,dno,em,mno,passWord);
		//sendEmail();
		System.out.println("*Back to Admin options*");
		System.out.println("");
		new Utility().hiAdmin();
	}
	
	public String getEm() {
		return em; }
		
	public String getNa() {
		return na; }
		
	public String getPassWord() {
		return passWord; }
	
	}