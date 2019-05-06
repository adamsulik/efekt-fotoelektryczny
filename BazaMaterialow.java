package Interfejs;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class BazaMaterialow {

	public BazaMaterialow() throws SQLException {
		
		
		
	}

	public static void main(String[] args) throws SQLException {
		Connection con = null;
		
		try {
		
		 con = DriverManager.getConnection( "jdbc:h2:metale", "sa", ""); //nie dziala, nie wiem jak odniesc sie do tej bazy
		
	Statement stmt = (Statement) con.createStatement();
	
	((java.sql.Statement) stmt).executeUpdate("DROP TABLE IF EXISTS METALE");
	 ((java.sql.Statement) stmt).executeUpdate("CREATE TABLE METALE ( IN INT PRIMARY KEY, NAZWA VARCHAR(30))");
	 ((java.sql.Statement) stmt).executeUpdate("INSERT INTO METALE VALUES (1, 'Cs - W = 2,14 eV')");
	 ((java.sql.Statement) stmt).executeUpdate("INSERT INTO METALE VALUES (2, 'Pb - W = 4,25 eV')");
	 ((java.sql.Statement) stmt).executeUpdate("INSERT INTO METALE VALUES (3, 'Au - W = 5,2 eV')");
	 ((java.sql.Statement) stmt).executeUpdate("INSERT INTO METALE VALUES (4, 'Mg - W = 3,66 eV')");
	 ((java.sql.Statement) stmt).executeUpdate("INSERT INTO METALE VALUES (5, 'K - W = 2,29 eV')");
	
	ResultSet materialy = ((java.sql.Statement) stmt).executeQuery(" SELECT NAZWA FROM METALE"); 
	
	ResultSetMetaData md = (ResultSetMetaData) ((Connection) stmt).getMetaData();
	
	while (materialy.next()) {
		for (int i = 0; i <= md.getColumnCount(); i++) System.out.print(materialy.getObject(i) + ",");
		
		
	}
		
		
		// TODO Auto-generated method stub

	}finally {
		if (con != null) 
			
		{con.close();
			
			}
		
	}
	}
	
}

