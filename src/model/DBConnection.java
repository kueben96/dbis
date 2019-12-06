package model;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataSource;
//import javax.activation.DataSource;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.inet.tds.TdsDataSource;

public class DBConnection {

	// Establish Connection via DataSource --> "mittlerweile bevorzugt"
	// i-net MERLIA.jar is JDBC 3.0 / 4.0 driver for MS SQL Server
	// Quelle 6. Dezember 2019: https://www.inetsoftware.de/products/jdbc-driver/ms-sql/features
	// Class.forName("com.inet.tds.TdsDriver") - MS-SQL-Server
	// This class is an implementation of a simple javax.sql.DataSource for the driver i-net ...
	// ... OPTA-xs and MERLIA-xs. For application servers you should use the PDataSource or DTCDataSource.
	// Quelle 6. Dezember TDSDataSource: https://www.inetsoftware.de/documentation/jdbc-driver/ms-sql/apispec/index.html?com/inet/tds/TdsDataSource.html
	// public TdsDataSource () Methods:
	// getConnection(username, password) throws java.sql.SQLException returns a Connection to database
	// setUrl(java.lang.String.jdbcUrl) and getUrl()
	// setServerName(String serverName)
	// getInstanceName()
	// setDatabaseName(String databaseName) etc ....
	// setPort(String port) --> cast from Textfield not necessary, if not set, default ist 1143
	// setUser and getUser as well as getPassword set Password both String


	private final String serverName;
	private final String port;
	private final String databaseName;
	private final String username;
	private final String password;
	private Connection con;
	private static final Logger log = Logger.getGlobal();

	// Constructor for DB-Connection-Object
	public DBConnection(String serverName, String port, String databaseName, String username, String password) {

		this.serverName = serverName;
		this.port = port;
		this.databaseName = databaseName;
		this.username = username;
		this.password = password;
	}
	
	public synchronized Connection getConnection() throws SQLException{
		
		//int port_no = Integer.parseInt(port);
		
			try {
				if (con == null || con.isClosed()) {
					DataSource ds = (DataSource) new TdsDataSource();
					((TdsDataSource) ds).setServerName(serverName);
					((TdsDataSource) ds).setPortNumber(Integer.parseInt(port));
					con = ((TdsDataSource) ds).getConnection(username, password);
					con.setCatalog(databaseName);
				}
		} catch (SQLException e) {
			log.log(Level.WARNING, "DB Connection failed.", e);
			JOptionPane.showMessageDialog(new JFrame(),
							" DB Connection failed!\n" +
							"Check your connection input",
							"CSV Import", JOptionPane.ERROR_MESSAGE);
		}
		return con;
		
	}



}
