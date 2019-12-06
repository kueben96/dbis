package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
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

//import model.DBConnection;
import model.DBConnectionDriver;
import model.Date_Format;


public class ImportWindow implements ActionListener{
	
	// Database Connection
	//private DBConnection db_con;
	//private Connection con; 
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
		portF.setText("1443");

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
			
			int port_no = Integer.parseInt(portF.getText());
			
			boolean isOk = testConnection(userF.getText(), new String(passwordF
					.getPassword()), databaseF.getText(), serverF.getText(), port_no);
			
			if(isOk) {
				LOG.info("Connection Established");
				JOptionPane.showMessageDialog(new JFrame(),
						"Connection Established" +
						"Connection",
						"Connection", JOptionPane.INFORMATION_MESSAGE);	
			}
			else {
				LOG.info("Error in Connection uups");
				JOptionPane.showMessageDialog(new JFrame(),
						"No DB Connection\n" +
						"Please check your entries",
						"Data import", JOptionPane.ERROR_MESSAGE);
			}
			
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
	
	private boolean testConnection(String user, String passwort,
			String datenbank, String server, int port) {
		boolean istVerbunden = false;
		try {
			LOG.info("Testing Connection in ImportWindow");
			db_con = new DBConnectionDriver("com.inet.tds.TdsDriver",
					"jdbc:inetdae:" + server + ":" + port, datenbank, user, passwort);
			istVerbunden = db_con.testConnection();

		} catch (Exception e) {
			e.printStackTrace();
			istVerbunden = false;
		}
		return istVerbunden;
	}
	
	

}