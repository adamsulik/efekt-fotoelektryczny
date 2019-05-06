package Interfejs;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class BazaMaterialow {

	public BazaMaterialow() throws SQLException {
		
		
		
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Connection con = null;
		
		try {
		//Class.forName("com.h2.jdbc.Driver");
		 con = DriverManager.getConnection( "jdbc:h2:./metale", "sa", ""); //nie dziala, nie wiem jak odniesc sie do tej bazy
		
	Statement stmt = (Statement) con.createStatement();
	
      stmt.executeUpdate("DROP TABLE IF EXISTS METALE");
	  stmt.executeUpdate("CREATE TABLE METALE (ID INT, NAZWA VARCHAR(30), PRIMARY KEY (ID))");
	  stmt.executeUpdate("INSERT INTO METALE VALUES (0, 'Cs - W = 2,14 eV')");
	  stmt.executeUpdate("INSERT INTO METALE VALUES (1, 'Pb - W = 4,25 eV')");
	  stmt.executeUpdate("INSERT INTO METALE VALUES (2, 'Au - W = 5,2 eV')");
	  stmt.executeUpdate("INSERT INTO METALE VALUES (3, 'Mg - W = 3,66 eV')");
	  stmt.executeUpdate("INSERT INTO METALE VALUES (4, 'K - W = 2,29 eV')");
	  System.out.println("stworzono");
	  
	
	ResultSet materialy =  stmt.executeQuery("SELECT NAZWA FROM METALE"); 
	System.out.println("stworzono");
	ResultSetMetaData md =  materialy.getMetaData();
	System.out.println("stworzono");
	while (materialy.next()) {
		//for (int i = 1; i <= 5; i++) 
			System.out.println(materialy.getObject(1));
			
		
		
	}
		
		
		
	}finally {
		if (con != null) 
			
		{con.close();
			
			}
		
	}
	}
	
}

