/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subgraph;


/**
 *
 * @author Andres
 */
public class Vertex {

    private int id;
    private Path[] paths;

    public Vertex(int id, int maxPaths) {
	this.id = id;
	this.paths = new Path[maxPaths];
    }
    
    public void establishConnections(boolean[] directConnections) {
	for (int i = 0; i < directConnections.length; i++) {
	    paths[i] = directConnections[i]? new Path(i): new Path();
	}
    }
    
    public void setPathTo(int id, Path p) {
	paths[id] = p;
    }

    public double getLengthTo(int id) {
	return paths[id].getLength();
    }

    public Path getPathTo(int id) {
	return paths[id];
    }
    
}
