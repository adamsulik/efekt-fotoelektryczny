package zaliczenie;

import java.sql.Statement;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class BazaMaterialow {
	
	public String war = "";
	
	public String mat = "";
	
	public  String[] materials = null;
	public  String[] values = null;

	Connection con = null;

	public BazaMaterialow() throws SQLException {
		
		
		try {
			//Class.forName("com.h2.jdbc.Driver");
			 con = DriverManager.getConnection( "jdbc:h2:./metale", "sa", ""); //nie dziala, nie wiem jak odniesc sie do tej bazy
			
		Statement stmt = (Statement) con.createStatement();
		
	      stmt.executeUpdate("DROP TABLE IF EXISTS METALE");
		  stmt.executeUpdate("CREATE TABLE METALE (ID INT, NAZWA VARCHAR(30), WARTOSCI INT, PRIMARY KEY (ID))");
		  stmt.executeUpdate("INSERT INTO METALE VALUES (0, 'Cs-W=2,14eV', 214)");
		  stmt.executeUpdate("INSERT INTO METALE VALUES (1, 'Pb-W=4,25eV', 425)");
		  stmt.executeUpdate("INSERT INTO METALE VALUES (2, 'Au-W=5,2eV', 520)");
		  stmt.executeUpdate("INSERT INTO METALE VALUES (3, 'Mg-W=3,66eV',366)");
		  stmt.executeUpdate("INSERT INTO METALE VALUES (4, 'K-W=2,29eV', 229)");
		  System.out.println("stworzono");
		  
		
		ResultSet materialy =  stmt.executeQuery("SELECT NAZWA FROM METALE"); 
		
		System.out.println("stworzono");
		ResultSetMetaData md =  materialy.getMetaData();
		System.out.println("stworzono");
		
		materials = new String[md.getColumnCount()];
		values = new String[5];
		
		
		
		
		while (materialy.next()) {
			
				
				mat += " " + materialy.getObject(1);
				
				
				
				
			
				}
		System.out.println(mat);
		materials = mat.split(" ");
		for(int i = 0; i<=5; i++) System.out.println(materials[i]);
		ResultSet wartosci = stmt.executeQuery("SELECT WARTOSCI FROM METALE");
		
		while (wartosci.next()) {
			
				
				war += " " + wartosci.getObject(1);
				
				
			
				}
		System.out.println(war);
		
		
		
		
		 values = war.split(" ");
		
		 for(int i = 0; i<=5; i++) System.out.println(values[i]);
			
			
		
			

		}catch(SQLException e)
		 {
			   e.printStackTrace();
			 }
		finally {
			if (con != null) 
				
			{con.close();
				
				}
			
		}
		
		
		
		
		
		
	}
	
	public static void main(String[] args) throws SQLException {
		
	}

	}
	



