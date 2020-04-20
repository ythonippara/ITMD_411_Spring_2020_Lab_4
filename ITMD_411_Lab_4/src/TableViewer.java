/**
 * This program performs data analysis 
 * and stores the result in the database.
 * 
 * @author Yulia Thonippara (A20411313)
 * ITMD 411 Spring 2020 Lab 4
 */

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableViewer {
	
	public void runView(ResultSet rs) {
		// Instantiate vector objects to hold column/row data for JTable
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		Vector<String> column = new Vector<String>();
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int columns = metaData.getColumnCount();

			// Get column names
			String cols = "";

		 	for (int i = 1; i <= columns; i++) {
		 		cols = metaData.getColumnName(i);
		 		column.add(cols);
		 	}
		 	
		 	// Get row data
		 	while (rs.next()) {
		 		Vector<Object> row = new Vector<Object>(columns);
		 		
		 		for (int i = 1; i <= columns; i++) {
		 			row.addElement(rs.getObject(i));
		 			data.addElement(row);
		 		}
		 	}
		 	
		 	DefaultTableModel model = new DefaultTableModel(data, column);

		 	JTable table = new JTable(model);
		 	JFrame frame = new JFrame("Record Details");
		 	frame.setSize(700, 200);
		 	frame.add(new JScrollPane(table));
		 	frame.setDefaultCloseOperation(0);
		 	frame.pack();
		 	frame.setVisible(true);
		 	
		 	// rs.close(); //close ResultSet instance 
		 	
		} catch (SQLException e) { 
			  e.printStackTrace(); 
		}
	} // End runView()
	
} // End class TableViewer