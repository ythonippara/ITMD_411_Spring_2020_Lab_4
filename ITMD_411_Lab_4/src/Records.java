/**
 * This program performs data analysis on bank-Detail.csv
 * and prints the result to bankrecords.txt.
 * 
 * @author Yulia Thonippara (A20411313)
 * Created for ITMD 411 Spring 2020
 */

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Records extends BankRecords {
	
	//create formatted object to write output directly to console & file
	static FileWriter fw = null;
	 
	public Records() {
		try {
			fw = new FileWriter("bankrecords.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void AverageStats() {
		
		Arrays.sort(robjs, new ComparatorBySex());

		// set up needed variables to gather counts & income by sex 
		// to determine average income by sex
		
		int maleCount = 0, femaleCount = 0;
		double maleIncome = 0, femaleIncome = 0;
		
		for (int i = 0; i < robjs.length; i++)
			if (robjs[i].getSex().equals("FEMALE")) {
				++femaleCount;
				femaleIncome += robjs[i].getIncome();
			}  
			else if (robjs[i].getSex().equals("MALE")) {
				++maleCount;
				maleIncome += robjs[i].getIncome();
			}
			 
		// display resulting averages to console and to file
		System.out.print("Data Analysis Results:\n");
		System.out.println();
		System.out.printf("Average income for Females -> $%.2f", (femaleIncome/femaleCount));
		System.out.printf("\nAverage income for Males -> $%.2f", (maleIncome/maleCount));
		System.out.println();

		try {
			fw.write("Data Analysis Results:\n");
			fw.write("\nAverage income for Females -> " + String.format("$%.2f", (femaleIncome/femaleCount)));
			fw.write("\nAverage income for Males -> " + String.format("$%.2f", (maleIncome/maleCount)) + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // End AverageStats()
	
	public static void StatsFemales() {
		
		// Call FemaleStatsComparator
		Arrays.sort(robjs, new FemaleStatsComparator());

		// set up needed variables to gather counts 
		int totalFemaleCount = 0;
		
		// Loop through robjs[] and gather results
		for (int i = 0; i < robjs.length; i++)
			if (robjs[i].getSex().equals("FEMALE") && robjs[i].getMortgage().equals("YES") 
					&& robjs[i].getSavingsAcct().equals("YES")) {
				++totalFemaleCount;
		}
		
		// Display the result to console
		System.out.printf("\nThe number of Females with mortgage and savings accounts -> %d", totalFemaleCount);
		System.out.println();
		
		// Write the results to the file
		try {
			fw.write("\nThe number of Females with mortgage and savings accounts -> " + totalFemaleCount + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} // End StatsFemale()
	
	public static void StatsMales() {
		
		// Call MaleStatsComparator
		Arrays.sort(robjs, new MaleStatsComparator());
		
		// Set up Date object
		Date dateNow = new Date( );
		SimpleDateFormat dateFormat = new SimpleDateFormat ("E MM.dd.yyyy 'at' hh:mm:ss a zzz");

		// set up needed variables to gather counts
		int innerCityMaleCount = 0, ruralMaleCount = 0, suburbanMaleCount = 0, townMaleCount = 0 ;
		
		// Loop through robjs[] and gather results
		for (int i = 0; i < robjs.length; i++)
			if (robjs[i].getSex().equals("MALE") && robjs[i].getRegion().equalsIgnoreCase("inner_city") 
					&& robjs[i].getCar().equals("YES") && robjs[i].getChildren() == 1) {
				++innerCityMaleCount;
			} 
		
			else if (robjs[i].getSex().equals("MALE") && robjs[i].getRegion().equalsIgnoreCase("rural") 
					&& robjs[i].getCar().equals("YES") && robjs[i].getChildren() == 1) {
				++ruralMaleCount;
			} 

			else if (robjs[i].getSex().equals("MALE") && robjs[i].getRegion().equalsIgnoreCase("suburban") 
					&& robjs[i].getCar().equals("YES") && robjs[i].getChildren() == 1) {
				++suburbanMaleCount;
			} 
			else if (robjs[i].getSex().equals("MALE") && robjs[i].getRegion().equalsIgnoreCase("town") 
					&& robjs[i].getCar().equals("YES") && robjs[i].getChildren() == 1) {
				++townMaleCount;
			} 
		
		// Display the results to console
		System.out.print("\nThe number of Males with a car and one child:");
		System.out.printf("\n- inner city region -> %d", innerCityMaleCount);
		System.out.printf("\n- rural region -> %d", ruralMaleCount); 
		System.out.printf("\n- suburban region -> %d", suburbanMaleCount); 
		System.out.printf("\n- town region -> %d", townMaleCount);
		
		// Write the results to the file
		try {
			fw.write("\nThe number of Males with a car and one child:");
			fw.write("\n- inner city region -> " + innerCityMaleCount);
			fw.write("\n- rural region -> " + ruralMaleCount);
			fw.write("\n- suburban region -> " + suburbanMaleCount);
			fw.write("\n- town region -> " + townMaleCount + "\n");
			fw.write("\n@author Yulia Thonippara");
			fw.write("\nCurrent Date: " + dateFormat.format(dateNow));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} // End StatsMales()
	
	public static void main(String[] args) {
		
		// Create Records object
		Records br = new Records();
		br.readData();
		
		// Call functions to perform analytics
		AverageStats(); // Analyze average income by sex
		StatsFemales(); // Display the number of females with mortgage and savings accounts 
		StatsMales(); // Display the number of males per region with a car and one child 

		// *** close out file object ***//

		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // End main()
	
} // End class Records
