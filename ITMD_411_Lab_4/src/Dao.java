/**
 * This program performs data analysis 
 * and stores the result in the database.
 * 
 * @author Yulia Thonippara (A20411313)
 * ITMD 411 Spring 2020 Lab 4
 */

//import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
	
	// Declare DB objects 
	static DbConnect conn = null;
	Statement stmt = null;
	
	// Constructor
	public Dao() { 
		// Create DbConnect object instance
		conn = new DbConnect();
	}
	
	// Create table method
	public void createTable() {
		try {
			// Open a connection
			System.out.println("Connecting to 411labs to create a table...");
			Thread.sleep(5000); // Sleep for 5 seconds
			stmt = conn.connect().createStatement();
			System.out.println("Connected to 411labs successfully...");

			// Execute create query
			System.out.println("Creating a table in 411labs...");
			String sql = "CREATE TABLE y_thon_tab "
					+ "(pid INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, " 
					+ "id VARCHAR(10), " 
					+ "income NUMERIC(8, 2), " 
					+ "pep VARCHAR(4))";
			
			stmt.executeUpdate(sql);
			
			System.out.println("Table created successfully...");
			conn.connect().close(); // Close DB connection 
		} catch (SQLException e) {
			// Handle errors for JDBC
			System.out.println("ERROR: " + e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // End createTable()
	
	// Insert into method
	public void insertRecords(BankRecords[] robjs) {
		try {
			// Execute a query
			System.out.println("Connecting to 411labs to insert records...");
			Thread.sleep(5000); // Sleep for 5 seconds
			stmt = conn.connect().createStatement();
			System.out.println("Connected to 411labs successfully...");
			
			String sql = null;
		
	        // Include all object data to the database table
			System.out.println("Inserting records into the table...");
			for (int i = 0; i < robjs.length; ++i) {
				// String assignment to insert all object data into a database table
				sql = "INSERT INTO y_thon_tab(id, income, pep) " +
						"VALUES (' "+robjs[i].getId()+" ', ' "+ robjs[i].getIncome()+" ', ' "+ robjs[i].getPep()+" ' )";
				stmt.executeUpdate(sql);
			}
			System.out.println("Records inserted successfully...");
			conn.connect().close();
		} catch (SQLException e) { 
			System.out.println("ERROR: " + e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // End insertRecords()
	
	public void updateRecords(String pep, double income) {
		String sql = "UPDATE y_thon_tab SET pep = ? WHERE income < ?";
		// use prepared statement
		try (PreparedStatement pstmt = conn.connect().prepareStatement(sql)) {
			pstmt.setString(1, pep); // # represents order of parameters
			pstmt.setDouble(2, income); // # represents order of parameters
			pstmt.executeUpdate();
			System.out.println("Record/s updated...");
		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	
	public void deleteRecords(int pid) {
		
		String sql = "DELETE FROM y_thon_tab WHERE pid = ?";
		// use prepared statement
		try (PreparedStatement pstmt = conn.connect().prepareStatement(sql)) {
			pstmt.setInt(1, pid); // @param columns #, column name
			pstmt.executeUpdate();
			System.out.println("Record deleted...");
		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
	
	// Retrieve record method
	public ResultSet retrieveRecords() {
		ResultSet rs = null;
		try {
			System.out.println("Connecting to 411labs for record retrieval...");
			Thread.sleep(5000); // Sleep for 5 seconds
			stmt = conn.connect().createStatement();
			System.out.println("Connected to 411labs successfully...");
			
			System.out.println("Creating SELECT statement...");
			String sql = "SELECT id, income, pep from y_thon_tab ORDER BY pep DESC"; // Sort pep field in descending order
			rs = stmt.executeQuery(sql);
			conn.connect().close();
		} catch (SQLException e){
			System.out.println("ERROR: " + e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	} // End retrieveRecords()

} // End class Dao
