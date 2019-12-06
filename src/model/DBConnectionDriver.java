package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

// ****************** TRASH Klasse fur DB-Connection ************************** /////////

public class DBConnectionDriver {
	
	private static final Logger LOG = Logger.getGlobal();

	private String driver;
	private String url;
	private String database;
	private String username;
	private String password;
	private Connection con;

	//driver: "com.inet.tds.TdsDriver",
	// url : "jdbc:inetdae:"


	public DBConnectionDriver(String driver, String url, String database, String username, String password) {

		this.driver = driver; 
		this.url = url;
		this.database = database;
		this.username = username;
		this.password = password;
	}

	public synchronized Connection getConnection() throws SQLException ,ClassNotFoundException{

		// Wenn der DbConnectionZentrale bereits freigegeben wurde,
		// dann k�nnen daraus keine Sessions bezogen werden.


		if (con == null || con.isClosed()) {
			try {
				LOG.info("Trying to make new Instance of Driver");
				Class.forName(driver).newInstance();
			} catch (Exception e) {
				throw new ClassNotFoundException();
			}
			DriverManager.setLoginTimeout(30);
			con = DriverManager.getConnection(url, username, password);
			con.setCatalog(database);
		}
		return con;
	}

	/**
	 * Gibt ein Connection aus dem Pool wieder frei.
	 */
	public synchronized void freeConnection() {
		try {
			System.out.println("Active DB-Verbindung wird geschlossen... ");
			con.close();
			con = null;
		} catch (Exception e) {

		}
	}

	public synchronized boolean testConnection() {
		try {
			// Die Verbindung hat geklappt.
			LOG.info("Testing Connection in DBConnectionDriver");
			if (getConnection() != null)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Gibt den Datenbank-Driver zurueck
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * Gibt den Datenbank-Name zurueck
	 */
	public String getDatenbank() {
		return database;
	}

	/**
	 * Gibt den Schemanamen zurueck
	 */
	public String getSchemaname() {
		return "dbo";
	}

	/**
	 * Gibt das Datenbank-Protokol zurueck
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Gibt den Datenbank-Usernamen zurueck
	 */
	public String getUserName() {
		return username;
	}

	/**
	 * Setzt den Datenbank-User
	 */
	public void setUserName(String user) {
		this.username = user;
	}


	// ********************************************************************				

	//			boolean isOk = testConnection(userF.getText(), new String(passwordF
	//					.getPassword()), databaseF.getText(), serverF.getText(), port_no);
	//			
	//			if(isOk) {
	//				LOG.info("Connection Established");
	//				JOptionPane.showMessageDialog(new JFrame(),
	//						"Connection Established" +
	//						"Connection",
	//						"Connection", JOptionPane.INFORMATION_MESSAGE);	
	//			}
	//			else {
	//				LOG.info("Error in Connection uups");
	//				JOptionPane.showMessageDialog(new JFrame(),
	//						"No DB Connection\n" +
	//						"Please check your entries",
	//						"Data import", JOptionPane.ERROR_MESSAGE);
	//			}

	//****************************************************************

	//			try {
	//				LOG.info("DB Verbindung wird hergestellt.");
	//				db_con = new DBConnectionDriver("com.inet.tds.TdsDriver",
	//						"jdbc:inetdae:",  databaseF.getText(),
	//						userF.getText(), new String(passwordF.getPassword()));
	//				con = db_con.getConnection();
	//				con.setAutoCommit(false);
	//				LOG.info("Connection Established");
	//				JOptionPane.showMessageDialog(new JFrame(),
	//						"Connection Established" +
	//						"Connection",
	//						"Connection", JOptionPane.INFORMATION_MESSAGE);
	//				
	//			} catch (SQLException ex) {
	//				LOG.log(Level.SEVERE, "Error in Data", ex);
	//				JOptionPane.showMessageDialog(new JFrame(),
	//						"No DB Connection\n" +
	//						"Please check your entries",
	//						"Data import", JOptionPane.ERROR_MESSAGE);
	//			} catch (ClassNotFoundException e1) {
	//				// TODO Auto-generated catch block
	//				e1.printStackTrace();
	//			}
	//			
	// port Int parsen: Integer.parseInt(portF.getText())
	// 1. boolean isOK = Test-Connection aufrufen
	// ImportRoutine Instanz erzeugen zum Importieren und dem Konstruktor die DB Connection und die Datei ubergeben
	//
	// Get Connection Details
	//db_con = new DBConnection(serverF.getText(), Integer.parseInt(portF.getText()), databaseF.getText(), userF.getText(), new String(passwordF.getPassword()));
	//con = db_con.getConnection();

}
