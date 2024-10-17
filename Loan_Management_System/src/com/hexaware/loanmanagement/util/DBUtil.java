
package com.hexaware.loanmanagement.util;
/* 
 * @Author:Rajeshwari
 * */
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
public class DBUtil {
	public static Connection getDBConnection() {
		Connection conn=null;
		try {
			FileReader reader=new FileReader("Resources/DBConfig.properties");
			Properties prop=new Properties();
			prop.load(reader);
			String driver = prop.getProperty("driver.classname");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");

			Class.forName(driver);

			conn = DriverManager.getConnection(url,username,password);
		}
		catch(Exception e) {
		
		e.printStackTrace();
		
	}
		return conn;

}
}
