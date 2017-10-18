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

    public Vertex(int id) {
	this.id = id;
    }

    public double getLengthTo(int id) {
	return paths[id].getLength();
    }

    public Path getPathTo(int id) {
	return paths[id];
    }
    
}
