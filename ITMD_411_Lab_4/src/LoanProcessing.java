/**
 * This program performs data analysis 
 * and stores the result in the database.
 * 
 * @author Yulia Thonippara (A20411313)
 * ITMD 411 Spring 2020 Lab 4
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

@SuppressWarnings("serial")
public class LoanProcessing extends BankRecords {
	
	static Dao dao = new Dao(); // Instantiate a Dao object
	
	public static void menu() {
		String menuItems = "\n1. Create table"
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
		System.out.println("\n\tLoan Analysis Report");
		System.out.println("\n ID" + "\t\tINCOME" + "\t\t PEP");
		
		// Extract data from result set
		try {
			while (rs.next()) {
				// Retrieve data by column name (i.e., for id, income, pep)
				String id = rs.getString("id");
				double income = Double.parseDouble(rs.getString("income")); //there can be an error here
				String pep = rs.getString("pep");
				
				// Display values for id, income, pep
				String s = String.format("%-2s\t\t$%6.2f\t%-2s", id, income, pep);
				System.out.println(s);
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
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws SQLException {
		
		BankRecords br = new BankRecords();
		br.readData();
		
		/**Extra credit: create and serialize a map to a file **/
		Map<Long, BankRecords> bankMap = new HashMap<Long, BankRecords>();
		// set timer variables (start and end)
		long i =0;
		for (BankRecords value: robjs) {
			bankMap.put(++i, value);
		}
		
		// Serialize object
		 FileOutputStream fos;
		try {
			fos = new FileOutputStream("map.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			 
			 // Start timer variable
			 oos.writeObject(bankMap);
			 
			 oos.flush();
			 oos.close();
			 fos.flush();
			 fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Sleep for 5 seconds
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Deserialize object
		FileInputStream fis;
		ObjectInputStream ois;

		
		try {
			fis = new FileInputStream("map.ser");
			ois = new ObjectInputStream(fis);
			bankMap = (Map<Long, BankRecords>) ois.readObject();
		
			// Print map
			//int index = 0;
			for (Entry<Long, BankRecords> mapEntry: bankMap.entrySet()) {
				Long key = (Long) mapEntry.getKey();
				String value = (String) mapEntry.getValue().getId();
				System.out.println("Data => " + "Key value: " + key 
						+ " ID value: " + value);
				//index++;
			} // End for loop
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// End timer variable
		// Show time lapse to console
			
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
				/**
				 * updateRecords() sets the values of pep column to "NO"
				 * if the corresponding values of income column are less than 25000
				**/
				dao.updateRecords("NO", 25000);
				break;
			case 4:
				System.out.println("Please enter the record you want to delete: ");
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
				sc.close();
				System.exit(0);
			}
		    System.out.println();
		} while (true);
	} // End main()

} // End class LoanProcessing
