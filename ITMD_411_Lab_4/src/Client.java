/**
 * This program performs data analysis on bank-Detail.csv
 * and prints the result to bankrecords.txt.
 * 
 * @author Yulia Thonippara (A20411313)
 * Created for ITMD 411 Spring 2020
 */

public abstract class Client {
	
	public abstract void readData(); // Read file detail
	
	public abstract void processData(); // Process file detail
	
	public abstract void printData(); // Print file detail

} // End class Client
