package surpriseAssignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private static DatabaseConnection DBConnectionRef;
    private static Connection connectionRef ;
    	
	public DatabaseConnection() {
		// private constructor //
	}
	
	public static DatabaseConnection getInstance(){
	    if(DBConnectionRef==null){
	    	DBConnectionRef= new DatabaseConnection();
	    }
	    return DBConnectionRef;
	}
	
	public Connection getConnection() {
		try {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "batch5";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";

        Class.forName(driver).newInstance();
        connectionRef = DriverManager.getConnection(url + dbName, userName,password);
		}catch(Exception e) {
			e.printStackTrace();
		}
        return connectionRef;
    } // end of getConnection
	

	// to close connection
	public static void closeConnection(Connection conn) {

        try {
            conn.close();

        } catch (SQLException e) {
        	//
        }

    }

}


// Connection con = Database.getInstance().getConnection();




