package com.wipro.candidate.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static Connection getDBConn() throws ClassNotFoundException, SQLException
	{
		Class.forName("oracle.jdbc.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:ORCL" ;
		String user = "B44641345049" ;
		String pswd ="B44641345049";
		Connection conn = DriverManager.getConnection(url,user,pswd);
		return conn;
		
		
	}
	

}
