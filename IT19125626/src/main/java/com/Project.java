//ITNumber - IT19125626
//Name - Dulanjaya L.A.P.S
//Function - Project Management

package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Project {

public Connection getConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/inventory", "root", "");
			return conn;
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}
		
		
	}
public String insertItem(String itemcode , String itemname , String quantity , String description , String supemail)
{
	String output ="";
	try 
	{
		Connection conn = getConnection(); 
		if(conn == null)
		{
			return "Error conncetion db";
		}
		//Create a prepared statement
		
		
		String query = " insert into inventory      (`itemid`,`itemcode`,`itemname`,`quantity`,`description`,`supemail`)"  + " values (?, ?, ?, ?, ?,?)"; 
		 
		 
		
		//String query = "insert into inventory('itemid','itemcode','itemname','quantity','description','supemail') values (?,?,?,?,?,?)";
		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		
		
		preparedStmt.setInt(1,0);
		preparedStmt.setString(2, itemcode);
		preparedStmt.setString(3, itemname);
		preparedStmt.setString(4, quantity);
		preparedStmt.setString(5, description);
		preparedStmt.setString(6, supemail);
		
		//execute the statement 
		preparedStmt.execute();
		conn.close();
		
		String newProjects = readItems();
		
		output = "{\"status\":\"success\", \"data\": \"" + newProjects + "\"}"; 
		
		
		
		
	}
	catch(Exception e)
	{
		output = "{\"status\":\"error\", \"data\": \"Error while inserting the Project.\"}"; 
		System.err.println(e.getMessage());
	}
	
	return output;

}


public String readItems()
{
	
	String output = "";
	try
	{
		Connection conn = getConnection(); 
		if(conn == null)
		{
			return "Error conncetion db";
		}
		
		output =  "<table border='1'><tr><th>Item Code</th>"   
				+"<th>Item Name</th>"
				+ "<th>Quantity</th>"   
				+ "<th>Item Description</th>"   
				+ "<th>Supplier Email</th>" 
				+ "<th>Update</th>"
				+ "<th>Remove</th></tr>"; 
		
		String query = "select * from inventory"; 
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next())
		{
			String itemid = Integer.toString(rs.getInt("itemid"));
			String itemcode = rs.getString("itemcode");
			String itemname = rs.getString("itemname");
			String quantity = rs.getString("quantity");
			String description = rs.getString("description");
			String supemail = rs.getString("supemail");
			
			
			
			//output += "<tr><td><input id='hidProjectIDUpdate' name='hidProjectIDUpdate' type='hiddne' values='"+itemid+"'>" +itemcode+ "</td>";
						
					
			
			
			output += "<tr><td>" + itemcode + "</td>"; 
			output += "<td>" + itemname + "</td>"; 
			output += "<td>" + quantity + "</td>"; 
			output += "<td>" + description + "</td>"; 
			output += "<td>" + supemail + "</td>"; 
			
			//buttons
			
			output += "<td><input name='btnUpdate' " 
					+ " type='button' value='Update' class='btnUpdate btn btn-secondary' data-itemid='"+itemid+"' ></td>" 
					
					+ "<td><input name='btnRemove' " 
					+ " type='button' value='Remove'  class='btnRemove btn btn-danger' data-itemid='"+itemid+"' ></td></tr>"; 
					
					
			
			
			
			
			
		}
		conn.close();
		output += "</table>";
	}
	catch(Exception e)
	{
		output = "Error read";
		System.err.println(e.getMessage());
	}
	return output;




}	

public String updateItem(String itemid, String itemcode, String itemname, String quantity, String description , String supemail) 
{
	String output = ""; 
	
	try 
	{
		Connection conn = getConnection(); 
		 
		   if (conn == null)   
		   {
			   return "Error while connecting to the database for deleting."; 
			   
		   } 
		   String query = "UPDATE inventory SET itemcode=?,itemname=?,quantity=?,description=?,supemail=?      WHERE itemid =?"; 
		   
		   PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			
			
			preparedStmt.setString(1, itemcode);
			preparedStmt.setString(2, itemname);
			preparedStmt.setString(3, quantity);
			preparedStmt.setString(4, description);
			preparedStmt.setString(5, supemail);
			preparedStmt.setInt(6, Integer.parseInt(itemid));
			
			//execute the statement 
			preparedStmt.execute();
			conn.close();
			
			String newProjects = readItems();
			
			output = "{\"status\":\"success\", \"data\": \"" + newProjects + "\"}"; 
			
	}
	catch (Exception e) 
	{
		output = "{\"status\":\"error\", \"data\": \"Error while Updating the Project.\"}"; 
		System.err.println(e.getMessage());   
		   
	}
	return output; 

}

public String deleteItem(String itemid )
{
	String output = "";
	
	try 
	{
		Connection conn = getConnection(); 
		if(conn == null)
		{
			return "Error conncetion db in delete";
			
		}
		
		String query = "delete from inventory where itemid=?"; 
		PreparedStatement preparedStmt = conn.prepareStatement(query); 
		
		preparedStmt.setInt(1, Integer.parseInt(itemid));
		
		preparedStmt.execute();   
		conn.close(); 
		
		String newProjects = readItems();
		
		output = "{\"status\":\"success\", \"data\": \"" + newProjects + "\"}"; 
	}
	catch(Exception e)
	{
		output = "{\"status\":\"error\", \"data\": \"Error while Updating the Project.\"}"; 
		System.err.println(e.getMessage());
	}
	
	return output;
	


}


}
