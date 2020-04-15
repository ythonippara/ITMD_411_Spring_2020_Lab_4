import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
	
	//Declare DB objects 
	DbConnect conn = null;
	Statement stmt = null;
	
	// constructor
	public Dao() { //create db object instance
		conn = new DbConnect();
	}
	
	// CREATE TABLE METHOD
	public void createTable() {
		try {
			// Open a connection
			System.out.println("Connecting to a selected database to create Table...");
			System.out.println("Connected database successfully...");

			// Execute create query
			System.out.println("Creating table in given database...");

			stmt = conn.connect().createStatement();

			String sql = "CREATE TABLE yourTableName_tab " + 
			"(pid INTEGER not NULL AUTO_INCREMENT, " +
					" id VARCHAR(10), " +
			" income numeric(8,2), " + 
					" pep VARCHAR(4), " + 
			" PRIMARY KEY ( pid ))";

			stmt.executeUpdate(sql);
			System.out.println("Created table in given database...");
			conn.connect().close(); //close db connection 
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
	} // End createTable()
	
	// INSERT INTO METHOD
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
				
				//sql = "INSERT INTO yourTableName_tab(field 1,field 2, field n) " +
				//"VALUES (' "+value 1+" ', ' "+value 2+" ', ' "+value n+" ' )";

				sql = "                  ";
				stmt.executeUpdate(sql);
			}
			conn.connect().close();
		} catch (SQLException se) { se.printStackTrace(); }
	} // End insertRecords()
	
	public ResultSet retrieveRecords() throws SQLException {
		ResultSet rs = null;
		
		stmt = conn.connect().createStatement();
		String sql = "SELECT * from yourTableName_tab";
		rs = stmt.executeQuery(sql);
		conn.connect().close();
		return rs;
	}


} // End class Dao
