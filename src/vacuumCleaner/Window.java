package vacuumCleaner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;

public class Window extends JFrame {
	private int WIDTH = 1000;						// Width of the frame in pixels
	private int HEIGHT = 700;						// height of the frame in pixels
	private Color COLOR_BACKGROUND = Color.BLACK;	// Color of the frame background
	private Floor floor = new Floor();
	private Vacuum vacuum = new Vacuum(floor);
	private Menu menu = new Menu(floor, vacuum);
	
	Window() {
		buildWindow();
		setLayout(new BorderLayout());
		add(menu, BorderLayout.WEST);
		add(floor, BorderLayout.CENTER);
	}
	
	private void buildWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Simulador aspiradora");
		setSize(WIDTH, HEIGHT);
		//setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(new GridBagLayout());
		getContentPane().setBackground(COLOR_BACKGROUND);
	}
}