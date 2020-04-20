/**
 * This program performs data analysis 
 * and stores the result in the database.
 * 
 * @author Yulia Thonippara (A20411313)
 * ITMD 411 Spring 2020 Lab 4
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoanProcessing extends BankRecords {
	
	static Dao dao = new Dao(); // Instantiate a Dao object
	
	public static void menu() {
		String menuItems = "1. Create table"
				+ "\n2. Insert records"
				+ "\n3. Update records"
				+ "\n4. Delete records"
				+ "\n5. Display records to Console"
				+ "\n6. Display records in GUI"
				+ "\n7. Exit";
		System.out.println(menuItems);
	}
	
	public static void consoleDisplay() {
		
		ResultSet rs = dao.retrieveRecords();
		
		// Create heading for display
		System.out.println("Loan Analysis Report");
		System.out.println("\nID" + "\tINCOME" + "\t\tPEP");
		
		// Extract data from result set
		try {
			while (rs.next()) {
				// Retrieve data by column name (i.e., for id, income, pep)
				String id = rs.getString("id");
				double income = Double.parseDouble(rs.getString("income")); //there can be an error here
				String pep = rs.getString("pep");
				
				// Display values for id, income, pep
				System.out.println(id + "\t$" + income + "\t" + pep);
			}
			// Clean up environment
			rs.close();
		} catch (SQLException e) {
			// Print an error message
			System.out.println("ERROR: " + e.getMessage());
		}
	} // End consoleDisplay()
	
	public static void guiDisplay() {
		
		try {
			// Get data from result set object
			ResultSet rs = dao.retrieveRecords();
			new TableViewer().runView(rs); // display records in window
			rs.close();
		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	} // End guiDisplay()
	
	public static void main(String[] args) throws SQLException {
		
		BankRecords br = new BankRecords();
		br.readData();
		
		Scanner sc = new Scanner(System.in);
		do {
			menu();
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				dao.createTable(); // Trigger createTable()
				break;
			case 2:
				dao.insertRecords(robjs); // Perform inserts
				break;
			case 3:
				dao.updateRecords("NO", 25000);
				break;
			case 4:
				System.out.println("Please enter a record you want to delete: ");
				int rcrd = sc.nextInt();
				dao.deleteRecords(rcrd); // Pass a value of a pid column
				break;
			case 5:
				consoleDisplay(); // Display records to console
				break;
			case 6:
				guiDisplay(); // Display records in GUI
				break;
			case 7:
				System.out.println("Exiting...");
				System.exit(0);
			}
		    System.out.println();
		    //sc.close();
		} while (true);
	} // End main()

} // End class LoanProcessing
