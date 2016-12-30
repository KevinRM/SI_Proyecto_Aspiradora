package es.ull.etsii.ssii;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.put("Slider.paintValue", false);
	    	new MainWindow();
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