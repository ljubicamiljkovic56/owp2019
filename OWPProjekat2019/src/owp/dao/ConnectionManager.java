package owp.dao;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory;

public class ConnectionManager {

	private static DataSource dataSource;

	public static void open() {
		try {
			Properties dataSourceProperties = new Properties();
			dataSourceProperties.setProperty("driverClassName", "org.sqlite.JDBC");
			dataSourceProperties.setProperty("url",  "jdbc:sqlite:C:/sqlite/baza za owp/bazaowp.db");
			System.out.println("Da li je uspostavljena konekcija?");
			dataSource = BasicDataSourceFactory.createDataSource(dataSourceProperties);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			System.out.println("Da li je uspostavljena konekcija  2222?");
			return dataSource.getConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
}
