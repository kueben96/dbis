package dw;

import java.util.logging.Level;
import java.util.logging.Logger;

import view.ImportWindow;

public class Main {
	
	private final static Logger LOG = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		
		
		try {
			ImportWindow win = new ImportWindow();
		}catch(Exception e) {
			LOG.log(Level.SEVERE, "Window Cant'open", e);
		}
		
		
	
	}

}
