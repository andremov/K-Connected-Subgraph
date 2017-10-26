/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subgraph;

import java.util.ArrayList;

/**
 *
 * @author movillaf
 */
public class Path {

    private ArrayList<Vertex> path;
    
    public Path(Vertex start) {
        this.path = new ArrayList<>();
	this.path.add(start);
    }
    
    public void add(Vertex V) {
        this.path.add(V);
    }
    
    public void add(Path end) {
        for (int i = 1; i < end.getLength(); i++) {
            this.path.add(end.get(i));
        }
    }
    
    public Vertex getGoal() {
	return this.get(this.getLength()-1);
    }
    
    public int getLength() {
	return this.path.size();
    }
    
    public Vertex get(int i) {
	return this.path.get(i);
    }
    
    public String toString() {
	String s = ""+path.get(0);
	for (int i = 1; i < path.size(); i++) {
	    s += " -> " + path.get(i);
	}
	return s;
    }
    
}
