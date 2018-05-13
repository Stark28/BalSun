package trial1;

import java.sql.*;

public class DBSQL 
{
	 String sql = null;
	 Connection con = null;
	 Statement statement = null;
	   
	DBSQL(String root, String balsun)
	{
    	
	}
	
	
	public void Initiate()
	{
		
		final String driver = "com.mysql.cj.jdbc.Driver";  
		final String url = "jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		final String gridurl = "jdbc:mysql://localhost/MicroGrid?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		
	
		String USER = "root"; 
    	String PASS = "1008615szy";

		
	

	    try
	    {
		      
		      Class.forName(driver);
		      con = DriverManager.getConnection(url, USER, PASS);
		      statement = con.createStatement(); 
		      sql = "DROP DATABASE IF EXISTS MicroGrid";
		      statement.executeUpdate(sql);
		      sql = "CREATE DATABASE MicroGrid"; 
		      statement.executeUpdate(sql);
      
		      System.out.println("Database created successfully..."); 
		     
		      con = DriverManager.getConnection(gridurl, USER, PASS);
		      sql = "USE MicroGrid"; 
		      statement.executeUpdate(sql) ; 
	    }
	    
	    catch(SQLException se)
	    {
		
		se.printStackTrace();
		}
	    catch(Exception e)
	    {
		
		e.printStackTrace();
		}
	   
	   }
	
	
	public void createTables(){
		
		try{
			
			sql = "CREATE TABLE IF NOT EXISTS BaseVoltage"
					+ "(rdfID VARCHAR(40) NOT NULL, NominalValue DOUBLE, PRIMARY KEY (rdfID))";
			statement.executeUpdate(sql) ; 

			sql = "CREATE TABLE IF NOT EXISTS Substation"
					+ "(rdfID VARCHAR(40) NOT NULL, Name VARCHAR(40), Region_rdfID VARCHAR(40),"
					+ "PRIMARY KEY (rdfID))";
			statement.executeUpdate(sql) ;

			sql = "CREATE TABLE IF NOT EXISTS VoltageLevel"
					+ "(rdfID VARCHAR(40) NOT NULL, Name VARCHAR(40), Substation_rdfID VARCHAR(40),"
					+ "BaseVoltage_rdfID VARCHAR(40), PRIMARY KEY (rdfID),"
					+ "FOREIGN KEY (Substation_rdfID) REFERENCES Substation(rdfID),"
					+ "FOREIGN KEY (BaseVoltage_rdfID) REFERENCES BaseVoltage(rdfID))";
			statement.executeUpdate(sql) ; // execute query
			
			sql = "CREATE TABLE IF NOT EXISTS GeneratingUnit"
					+ "(rdfID VARCHAR(40) NOT NULL, Name VARCHAR(40), MaxP DOUBLE, MinP DOUBLE,"
					+ "EquipmentContainer_rdfID VARCHAR(40), PRIMARY KEY (rdfID),"
					+ "FOREIGN KEY (EquipmentContainer_rdfID) REFERENCES Substation(rdfID))";
			statement.executeUpdate(sql) ; // execute query
			
			sql = "CREATE TABLE IF NOT EXISTS SynchronousMachine"
					+ "(rdfID VARCHAR(40) NOT NULL, Name VARCHAR(40), RatedS DOUBLE, P DOUBLE, Q DOUBLE,"
					+ "GenUnit_rdfID VARCHAR(40), RegControl_rdfID VARCHAR(40),"
					+ "EquipmentContainer_rdfID VARCHAR(40),  BaseVoltage_rdfID VARCHAR(40), PRIMARY KEY (rdfID),"
					+ "FOREIGN KEY (GenUnit_rdfID) REFERENCES GeneratingUnit(rdfID),"
					+ "FOREIGN KEY (EquipmentContainer_rdfID) REFERENCES VoltageLevel(rdfID))";
			statement.executeUpdate(sql) ; // execute query
			
			sql = "CREATE TABLE IF NOT EXISTS RegulatingControl"
					+ "(rdfID VARCHAR(40) NOT NULL, Name VARCHAR(40), TargetValue DOUBLE,"
					+ "PRIMARY KEY (rdfID))";
			statement.executeUpdate(sql) ; // execute query
			
			sql = "CREATE TABLE IF NOT EXISTS PowerTransformer"
					+ "(rdfID VARCHAR(40) NOT NULL, Name VARCHAR(40),"
					+ "EquipmentContainer_rdfID VARCHAR(40), PRIMARY KEY (rdfID),"
					+ "FOREIGN KEY (EquipmentContainer_rdfID) REFERENCES Substation(rdfID))";
			statement.executeUpdate(sql) ; // execute query
			
			sql = "CREATE TABLE IF NOT EXISTS EnergyConsumer"
					+ "(rdfID VARCHAR(40) NOT NULL, Name VARCHAR(40), P DOUBLE, Q DOUBLE,"
					+ "EquipmentContainer_rdfID VARCHAR(40),  BaseVoltage_rdfID VARCHAR(40), PRIMARY KEY (rdfID),"
					+ "FOREIGN KEY (EquipmentContainer_rdfID) REFERENCES VoltageLevel(rdfID))";
			statement.executeUpdate(sql) ; // execute query
			
			sql = "CREATE TABLE IF NOT EXISTS TransformerWinding"
					+ "(rdfID VARCHAR(40) NOT NULL, Name VARCHAR(40), TransformerR DOUBLE,"
					+ "TransformerX DOUBLE, Transformer_rdfID VARCHAR(40), BaseVoltage_rdfID VARCHAR(40),"
					+ "PRIMARY KEY (rdfID), FOREIGN KEY(Transformer_rdfID) REFERENCES PowerTransformer(rdfID),"
					+ "FOREIGN KEY(BaseVoltage_rdfID) REFERENCES BaseVoltage(rdfID))";
			statement.executeUpdate(sql) ; // execute query
			
			sql = "CREATE TABLE IF NOT EXISTS Breaker"
					+ "(rdfID VARCHAR(40) NOT NULL, Name VARCHAR(40), State BOOLEAN,"
					+ "EquipmentContainer_rdfID VARCHAR(40), BaseVoltage_rdfID VARCHAR(40),  PRIMARY KEY (rdfID))";
			statement.executeUpdate(sql) ; // execute query
						
			sql = "CREATE TABLE IF NOT EXISTS RatioTapChanger"
					+ "(rdfID VARCHAR(40) NOT NULL, Name VARCHAR(40), Step DOUBLE,"
					+ "PRIMARY KEY (rdfID))";					
			statement.executeUpdate(sql) ; // execute query
			
			
			System.out.println("Successfully created the tables for the sim objects");
		   }
		
		
		catch(SQLException se)
		{
		    
		    se.printStackTrace();
		}
		catch(Exception e)
		{
		    
		    e.printStackTrace();
		}
	}
	
	
	public void BaseVoltDB(String rdfID, double NominalValue)
	{
		try 
		{
			
			String query = "INSERT INTO BaseVoltage VALUES(?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1,rdfID);
			preparedStmt.setDouble(2,NominalValue);
			preparedStmt.executeUpdate(); 
			
		 }
		catch(SQLException se)
		{
		se.printStackTrace();
		}
		catch(Exception e)
		{
		e.printStackTrace();}			
	}
	
	public void SubstationDB(String rdfID, String Name,String Region_rdfID)
	{
		try 
		{
			
			String query = "INSERT INTO Substation VALUES(?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1,rdfID);
			preparedStmt.setString(2,Name);
			preparedStmt.setString(3,Region_rdfID);
			preparedStmt.executeUpdate();
		}
		catch(SQLException se)
		{
		se.printStackTrace();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}			
	}
	
	public void VoltLevelDB(String rdfID, String Name,String Substation_rdfID, String BaseVoltage_rdfID)
	{
		try 
		{
			String query = "INSERT INTO VoltageLevel VALUES(?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1,rdfID);
			preparedStmt.setString(2,Name);
			preparedStmt.setString(3,Substation_rdfID);
			preparedStmt.setString(4,BaseVoltage_rdfID);
			preparedStmt.executeUpdate();
		}
		catch(SQLException se)
		{
		se.printStackTrace();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}			
	}
	
	public void GeneratingDB(String rdfID, String Name, double MaxP, double MinP, String EquipmentContainer_rdfID)
	{
		try 
		{
			String query = "INSERT INTO GeneratingUnit VALUES(?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1,rdfID);
			preparedStmt.setString(2,Name);
			preparedStmt.setDouble(3,MaxP);
			preparedStmt.setDouble(4,MinP);
			preparedStmt.setString(5,EquipmentContainer_rdfID);
			preparedStmt.executeUpdate();
		}
		catch(SQLException se)
		{
		se.printStackTrace();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}			
	}
	
	public void SynMachDB(String rdfID, String Name, double RatedS, double P, double Q, String GenUnit_rdfID, String RegControl_rdfID, String EquipmentContainer_rdfID, String Basevoltage_rdfID)
	{

		try 
		{
			String query = "INSERT INTO SynchronousMachine VALUES(?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1,rdfID);
			preparedStmt.setString(2,Name);
			preparedStmt.setDouble(3,RatedS);
			preparedStmt.setDouble(4,P);
			preparedStmt.setDouble(5,Q);
			preparedStmt.setString(6,GenUnit_rdfID);
			preparedStmt.setString(7,RegControl_rdfID);
			preparedStmt.setString(8,EquipmentContainer_rdfID);
			preparedStmt.setString(9,Basevoltage_rdfID);
			preparedStmt.executeUpdate();
		}
		catch(SQLException se)
		{
		se.printStackTrace();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}			
	}
	
	public void RegDB(String rdfID, String Name, double TargetValue)
	{
		try 
		{
			String query = "INSERT INTO RegulatingControl VALUES(?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1,rdfID);
			preparedStmt.setString(2,Name);
			preparedStmt.setDouble(3,TargetValue);
			preparedStmt.executeUpdate();
		}
		catch(SQLException se)
		{
		se.printStackTrace();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}			
	}
	
	public void PowerTransDB(String rdfID, String Name, String EquipmentContainer_rdfID)
	{
		try
		{
			String query = "INSERT INTO PowerTransformer VALUES(?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1,rdfID);
			preparedStmt.setString(2,Name);
			preparedStmt.setString(3,EquipmentContainer_rdfID);
			preparedStmt.executeUpdate();
		}
		catch(SQLException se)
		{
		se.printStackTrace();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}			
	}
	
	public void EnergyDB(String rdfID, String Name, double P, double Q, String EquipmentContainer_rdfID, String Basevoltage_rdfID)
	{
		try
		{
			String query = "INSERT INTO EnergyConsumer VALUES(?,?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1,rdfID);
			preparedStmt.setString(2,Name);
			preparedStmt.setDouble(3,P);
			preparedStmt.setDouble(4,Q);
			preparedStmt.setString(5,EquipmentContainer_rdfID);
			preparedStmt.setString(6,Basevoltage_rdfID);
			preparedStmt.executeUpdate();
		}
		catch(SQLException se)
		{
		se.printStackTrace();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}			
	}
	
	public void PowerTransEndDB(String rdfID, String Name, double TransformerR, double TransformerX, String Transformer_rdfID, String BaseVoltage_rdfID)
	{
		try 
		{
			String query = "INSERT INTO TransformerWinding VALUES(?,?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1,rdfID);
			preparedStmt.setString(2,Name);
			preparedStmt.setDouble(3,TransformerR);
			preparedStmt.setDouble(4,TransformerX);
			preparedStmt.setString(5,Transformer_rdfID);
			preparedStmt.setString(6,BaseVoltage_rdfID);
			preparedStmt.executeUpdate();
		}
		catch(SQLException se)
		{
		se.printStackTrace();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}			
	}
	
	public void BreakerDB(String rdfID, String Name, boolean State, String EquipmentContainer_rdfID, String BaseVoltage_rdfID)
	{
		try 
		{
			String query = "INSERT INTO Breaker VALUES(?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1,rdfID);
			preparedStmt.setString(2,Name);
			preparedStmt.setBoolean(3,State);
			preparedStmt.setString(4,EquipmentContainer_rdfID);
			preparedStmt.setString(5,BaseVoltage_rdfID);
			preparedStmt.executeUpdate();
		}
		catch(SQLException se)
		{
		se.printStackTrace();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}			
	}	
	
	public void VoltLevelDB(String rdfID, String Name, double Step)
	{
		try 
		{
			String query = "INSERT INTO RatioTapChanger VALUES(?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1,rdfID);
			preparedStmt.setString(2,Name);
			preparedStmt.setDouble(3,Step);
			preparedStmt.executeUpdate();
		}
		catch(SQLException se)
		{
		se.printStackTrace();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}			
	}
	
}
	

