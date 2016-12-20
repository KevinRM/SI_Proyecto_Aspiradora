package vacuumCleaner;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	public static void main(String[] args) {
		try {
			// Set the system's Lookn'Feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    	new Window();
	    }
		catch (UnsupportedLookAndFeelException e) {
		       e.printStackTrace();
		    }
		    catch (ClassNotFoundException e) {
		    	e.printStackTrace();
		    }
		    catch (InstantiationException e) {
		    	e.printStackTrace();
		    }
		    catch (IllegalAccessException e) {
		    	e.printStackTrace();
		    }

			//Create and show the GUI.
		    new Main();
		}
}