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
			System.out.println("Connecting to a selected database to create table...");
			stmt = conn.connect().createStatement();
			System.out.println("Connected database successfully...");

			// Execute create query
			System.out.println("Creating table in given database...");
			String sql = "CREATE TABLE y_thon_tab_test " + 
			"(pid INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
					" id VARCHAR(10), " +
			" income NUMERIC(8, 2), " + 
					" pep VARCHAR(3))";

			stmt.executeUpdate(sql);
			System.out.println("Created table in given database...");
			conn.connect().close(); // Close DB connection 
		} catch (SQLException e) {
			// Handle errors for JDBC
			System.out.println("ERROR: " + e.getMessage());
		}
	} // End createTable()
	
	// Insert into method
	public void insertRecords(BankRecords[] robjs) {
		try {
			// Execute a query
			System.out.println("Inserting records into the table...");
			stmt = conn.connect().createStatement();
			String sql = null;
		
	        // Include all object data to the database table
			for (int i = 0; i < robjs.length; ++i) {
				// finish string assignment to insert all object data
				// (id, income, pep) into your database table
				sql = "INSERT INTO y_thon_tab_test(id, income, pep) " +
						"VALUES (' "+robjs[i].getId()+" ', ' "+ robjs[i].getIncome()+" ', ' "+ robjs[i].getPep()+" ' )";
				stmt.executeUpdate(sql);
			}
			conn.connect().close();
		} catch (SQLException e) { 
			System.out.println("ERROR: " + e.getMessage());
		}
	} // End insertRecords()
	
	public void updateRecords(String pep, double income) {
		String sql = "UPDATE y_thon_tab_test SET pep = ? WHERE income < ?";
		// use prepared statement
		try (PreparedStatement pstmt = conn.connect().prepareStatement(sql)) {
			pstmt.setString(1, pep); // # represents order of parameters
			pstmt.setDouble(2, income); // # represents order of parameters
			pstmt.executeUpdate();
			System.out.println("Record/s updated");
		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	
	public void deleteRecords(int pid) {
		
		String sql = "DELETE FROM y_thon_tab_test WHERE pid = ?";
		// use prepared statement
		try (PreparedStatement pstmt = conn.connect().prepareStatement(sql)) {
			pstmt.setInt(1, pid); // @param columns #, column name
			pstmt.executeUpdate();
			System.out.println("Record deleted");
		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
	
	// Retrieve record method
	public ResultSet retrieveRecords() {
		ResultSet rs = null;
		try {
			System.out.println("Connecting to a selected database for record retrieval...");
			stmt = conn.connect().createStatement();
			System.out.println("Connected database successfully...");
			String sql = "SELECT id, income, pep from y_thon_tab_test ORDER BY pep DESC"; // Sort pep field in descending order
			System.out.println("Creating SELECT statement...");
			rs = stmt.executeQuery(sql);
			conn.connect().close();
		} catch (SQLException e){
			System.out.println("ERROR: " + e.getMessage());
		}
		return rs;
	} // End retrieveRecords()

} // End class Dao
