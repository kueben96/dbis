package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.CSVHandler;
import model.DBConnection;
import model.DBConnectionDriver;
import model.Date_Format;
import model.ImportRoutine;


public class ImportWindow implements ActionListener{

	// Database Connection


	private DBConnection dbcon;

	private Connection con;
	
	private Statement statement;
	
	//for CSV Testing
	
	private CSVHandler csvRead;

	// mit driver
	private DBConnectionDriver db_con = null;


	//LoginFenster
	public JFrame frmLoginWindow;

	//Logger

	private static final Logger LOG = Logger.getGlobal();

	//Labels for Connection Screen
	private JLabel userL;
	private JLabel passwordL;
	private JLabel databaseL;
	private JLabel serverL;
	private JLabel portL;
	private JLabel fileL;

	//Inputs in Connection Screen 
	private JTextField userF;
	private JPasswordField passwordF;
	private JTextField databaseF;
	private JTextField fileF;
	private JTextField serverF;
	private JTextField portF;

	//Buttons for Screens
	private JButton btnImport; 
	private JButton btnFile;
	private JButton btnCancel;
	private JButton btnProtocol;

	//Protocol

	private String protocol_content;

	//Constructor for starting Application Window	

	public ImportWindow() {
		init();
	}


	public void init() {
		frmLoginWindow = new JFrame("Select CSV data for Product Import");
		frmLoginWindow.setBounds(100,100,550,300);
		frmLoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginWindow.setLocationRelativeTo(null);
		frmLoginWindow.setResizable(false);

		userL = new JLabel("User");
		passwordL = new JLabel("Password");
		databaseL = new JLabel("Database");
		serverL = new JLabel("Server String");
		portL = new JLabel("Port No.");
		fileL = new JLabel("CSV-File");


		//Buttons
		btnImport = new JButton("Import");
		btnImport.addActionListener(this);
		btnFile = new JButton("Browse File");
		btnFile.addActionListener(this);
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnProtocol = new JButton("Protocol");
		btnProtocol.addActionListener(this);

		// Input Fields

		userF = new JTextField();
		userF.setText("DB6013868");

		passwordF = new JPasswordField();
		passwordF.setText("3utbve");

		databaseF = new JTextField();
		databaseF.setText("DB6013868");

		serverF = new JTextField();
		serverF.setText("whv-fbmit3.hs-woe.de");

		portF= new JTextField();
		portF.setText("1433");

		fileF = new JTextField();
		fileF.setText(System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "imp"
				+ System.getProperty("file.separator") + "PRODUKTE1.csv");

		//JPanel for Labels 

		JPanel labelPane = new JPanel(new GridLayout(6, 1));
		labelPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		labelPane.add(userL);
		labelPane.add(passwordL);
		labelPane.add(databaseL);
		labelPane.add(serverL);
		labelPane.add(portL);
		labelPane.add(fileL);

		// JPanel for Inputs

		JPanel fieldPane = new JPanel(new GridLayout(6, 1));
		fieldPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		fieldPane.add(userF);
		fieldPane.add(passwordF);
		fieldPane.add(databaseF);
		fieldPane.add(serverF);
		fieldPane.add(portF);
		fieldPane.add(fileF);

		// JPanel for Buttons

		JPanel btnPane = new JPanel(new GridLayout(1,4));
		btnPane.add(btnFile);
		btnPane.add(btnImport);
		btnPane.add(btnProtocol);
		btnPane.add(btnCancel);

		//Container
		Container container = frmLoginWindow.getContentPane();
		container.setBackground(Color.lightGray);

		//Button Default 
		frmLoginWindow.getRootPane().setDefaultButton(btnImport);

		//place panels
		container.add(labelPane, BorderLayout.CENTER);
		container.add(fieldPane, BorderLayout.LINE_END);
		container.add(btnPane, BorderLayout.SOUTH);

		frmLoginWindow.setVisible(true);
	}

	// 

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnFile){
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(fc);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				//This is where a real application would open the file.
				fileF.setText(file.getAbsolutePath());
				LOG.info("File selected with success");
			}
			System.out.println("Button geklickt!");
		}
		else if(e.getSource() == btnCancel) {
			System.exit(0);
			System.out.println("Canceled");
			LOG.info("Canceled");
		}
		else if(e.getSource() == btnProtocol) {

			JTextArea outputArea = new JTextArea(20, 40);
			// Header and append
			String header = getHeader();
			String foot = getFoot();
			protocol_content = header + protocol_content + foot;
			outputArea.setText(protocol_content);
			outputArea.setEditable(false);
			JScrollPane scrollPane = new JScrollPane(outputArea);

			JOptionPane.showMessageDialog(new JFrame(), scrollPane, "Protokoll",
					JOptionPane.INFORMATION_MESSAGE);
		}
		// DB Import CSV
		else if(e.getSource() == btnImport) {

			LOG.info("Import clicked..");

			try {
				LOG.info("Connecting...");
				dbcon = new DBConnection(serverF.getText(),
						portF.getText(), databaseF.getText(),
						userF.getText(), new String(
								passwordF.getPassword()));
				con = dbcon.getConnection();
				
				// Testen der Verbindung 
				
				boolean isOk = dbcon.testConnection();
				
				//Test if connection is established --> true!!
				System.out.println("Connection established"+ isOk);
				protocol_content+= "Connection established to" + " "+serverF.getText();
				
				
				if (!isOk) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Die Anmeldung konnte nicht durchgef�hrt werden!"
									+ "\n\nBitte �berpr�fen Sie Ihre Angaben!",
							"Anmeldung fehlgeschlagen!", JOptionPane.ERROR_MESSAGE);
				} else {
					
					// Das Hauptfenster f�r die Systemverwaltung wird ge�ffnet.
					ImportRoutine dbisImport = new ImportRoutine(con, fileF
							.getText());
					try {
						// Datemimportieren
						protocol_content = dbisImport.startImport();
						
						if (dbisImport.isImportOk())
							JOptionPane.showMessageDialog(new JFrame(),
									"Datenimport wurde erfolgreich durchgef�hrt!", "Datenimport",
									JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(new JFrame(),
									"Datenimport wurde abgebrochen!",
									"Datenimport fehlgeschlagen!", JOptionPane.ERROR_MESSAGE);
					} catch (IOException e1) {
						JOptionPane
								.showMessageDialog(
										new JFrame(),
										"Die Datei konnte nicht gefunden oder nicht gelesen werden!"
												+ "\n\nBitte �berpr�fen Sie Pfad, Name und Rechte der Importdatei!",
										"Import fehlgeschlagen!", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
					//****************!!!**************
					dbcon.freeConnection();

				}
				
			} catch (SQLException e1) {
				LOG.log(Level.SEVERE, "Fehler im Datensatz.", e1);
				JOptionPane.showMessageDialog(new JFrame(),
						"Keine Verbindung zur Datenbank!\n" +
								"Bitte \u00FCberprüfen Sie Ihre Angaben!",
								"Datenimport", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
	private String getHeader() {
		return "**************************************\n" + "Start: "
				+ Date_Format.getDdMMyyyyHHMi(System.currentTimeMillis())
				+ " Uhr \n" + "Benutzer: " + userF.getText() + "\n" + "Database: "
				+ databaseF.getText() + "\n"
				+ "**************************************\n\n";
	}
	private String getFoot() {
		return "\n\n**************************************\n" + "End: "
				+ Date_Format.getDdMMyyyyHHMi(System.currentTimeMillis())
				+ " Uhr \n" + "**************************************\n\n";
	}

//	private boolean testConnection(String user, String passwort,
//			String datenbank, String server, int port) {
//		boolean istVerbunden = false;
//		try {
//			LOG.info("Testing Connection in ImportWindow");
//			db_con = new DBConnectionDriver("com.inet.tds.TdsDriver",
//					"jdbc:inetdae:" + server + ":" + port, datenbank, user, passwort);
//			istVerbunden = db_con.testConnection();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			istVerbunden = false;
//		}
//		return istVerbunden;
//	}



}
