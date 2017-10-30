/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subgraph;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Andres
 */
public class Display extends Canvas implements Runnable {

    /**
     * devuelve el color dado por parametros en HSB
     * @param h
     * @param s
     * @param b
     * @return 
     */
    private Color color(double h, double s, double b) {
	    return Color.getHSBColor((float)(h/360f),(float)(s/100f),(float)(b/100f));
    }

    /**
     * pinta el canvas
     */
    @Override
    public void run() {
		createBufferStrategy(2);
		setBackground(color(51,54.1,91.4));
		while(true){
			Graphics g = getBufferStrategy().getDrawGraphics();
			Graph graph = Handler.G;
			if (graph != null) {
				g.clearRect(0, 0, DisplayWindow.WINDOW_X, DisplayWindow.WINDOW_Y);
				g.setColor(Color.red);

				for (int i = 0; i < graph.numVertex(); i++) {
					for (int j = 0; j < graph.numVertex(); j++) {
						if (graph.getConnection(i, j)) {
							if (!graph.getVertex(i).isActive() || !graph.getVertex(j).isActive()) {
								g.setColor(Color.gray);
							} else {
								g.setColor(Color.BLUE);
							}
							int x1 = graph.getVertex(i).getX();
							int y1 = graph.getVertex(i).getY();
							int x2 = graph.getVertex(j).getX();
							int y2 = graph.getVertex(j).getY();
							g.drawLine(x1, y1, x2, y2);	
						}
					}
				}

				for (int i = 0; i < graph.numVertex(); i++) {
					if (!graph.getVertex(i).isActive()) {
						g.setColor(Color.gray);
					} else {
						g.setColor(Color.green);
					}

					int x = graph.getVertex(i).getX();
					int y = graph.getVertex(i).getY();

					g.fillOval(x-5, y-5, 10,10);
				}

				getBufferStrategy().show();
			}

			try {
				Thread.sleep(100);
			} catch(Exception e){ }
		}
    }
	
}