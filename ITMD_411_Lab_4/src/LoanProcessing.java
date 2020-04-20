import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanProcessing extends BankRecords {
	
	public static void main(String[] args) throws SQLException {
		
		BankRecords br = new BankRecords();
		br.readData();
		Dao dao = new Dao(); // Instantiate a Dao object
		//dao.createTable(); // Trigger createTable()
		//dao.insertRecords(robjs); // Perform inserts
		ResultSet rs = dao.retrieveRecords();
		
		// Create heading for display
		System.out.println("Loan Analysis Report");
		System.out.println("ID" + "\tINCOME" + "\t\tPEP");
		
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
		} catch (SQLException e) {
			// Print an error message
			System.out.println("ERROR: " + e.getMessage());
		}
		rs.close(); // closes result set object
	} // End main()

} // End class LoanProcessing
