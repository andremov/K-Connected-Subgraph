/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subgraph;

import javax.swing.JFrame;

/**
 *
 * @author Andres
 */
public class DisplayWindow extends JFrame {

    public static int CANVAS_X = 8;
    public static int CANVAS_Y = 31;
    public static int WINDOW_X = 800;
    public static int WINDOW_Y = 800;

    static Display screen;
    static City[] cities;
    public static int MAX_CITIES;
    private static int currentCity;
    static int totalCost;

    public DisplayWindow() {
	setLayout(null);
	setSize(WINDOW_X+CANVAS_X,WINDOW_Y+CANVAS_Y);
	setLocationRelativeTo(null);
	setTitle("Aeropuertos [AMovilla & AVasquez]");
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setResizable(false);

	init();

	setVisible(true);

	new Thread(screen).start();
    }
	
    /**
     * recibe las ciudades entregadas poor parametro
     * @param pCities 
     */
    public static void setCities(City[] pCities) {
	if (cities != null) {
	    for (int i = 0; i < Integer.min(pCities.length, cities.length); i++) {
		pCities[i].setX(cities[i].getCenterX());
		pCities[i].setY(cities[i].getCenterY());
	    }
	}
	cities = pCities;
	MAX_CITIES = cities.length;
    }
	
    /**
     * ubica una ciudad en el x y y pasado por parametro
     * @param x
     * @param y 
     */
    public static void locateCity(int x, int y) {
	if (cities != null) {
	    cities[currentCity%cities.length].setX(x);
	    cities[currentCity%cities.length].setY(y);
	    currentCity++;
	}
    }
	

    /**
     * inicializa la interfaz de esta ventana
     */
    private void init() {
	MAX_CITIES = 0;
	totalCost = 0;

	screen = new Display();
	screen.setSize(WINDOW_X,WINDOW_Y);
	screen.setLocation(1,1);
	add(screen);

    }
	
}