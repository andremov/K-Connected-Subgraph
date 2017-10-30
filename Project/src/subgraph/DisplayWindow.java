/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subgraph;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

    public DisplayWindow() {
		setLayout(null);
		setSize(WINDOW_X+CANVAS_X,WINDOW_Y+CANVAS_Y);
		setLocationRelativeTo(null);
		setTitle("K-Conexos");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		init();

		setVisible(true);

		new Thread(screen).start();
    }
	
    /**
     * ubica una ciudad en el x y y pasado por parametro
     * @param x
     * @param y 
     */
    public static void locateVertex(int x, int y) {
		if (Handler.G != null) {
			Handler.G.locateVertex(x,y);
		}
    }

    /**
     * inicializa la interfaz de esta ventana
     */
    private void init() {

		screen = new Display();
		screen.setSize(WINDOW_X,WINDOW_Y);
		screen.setLocation(1,1);
		screen.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) { locateVertex(e.getX(), e.getY()); }
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		add(screen);

    }
	
}