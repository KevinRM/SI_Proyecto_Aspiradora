package vacuumCleaner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;

public class Window extends JFrame {
	private int WIDTH = 900;
	private int HEIGHT = 620;
	private Color COLOR_BACKGROUND = Color.BLACK;	
	private Floor2 floor2 = new Floor2(this);
	private Floor floor = new Floor();
	private Vacuum vacuum = new Vacuum(floor);
	private Menu menu = new Menu(floor2, vacuum);
	
	Window() {
		buildWindow();
		this.setResizable(true);
		setLayout(new BorderLayout());
		add(menu, BorderLayout.WEST);
		add(floor2, BorderLayout.CENTER);
		floor2.setVisible(true);
		// Estableciendo las dimensiones del mapa
		Dimension dim = menu.getSize();
		floor2.setSize((int) (this.getWidth()-dim.getWidth()), this.getHeight());
		floor2.setCels(20, 20);
	}
	
	private void buildWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Intelligent Vacuum Cleaner Simulator");
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(new GridBagLayout());
		getContentPane().setBackground(COLOR_BACKGROUND);
	}
}