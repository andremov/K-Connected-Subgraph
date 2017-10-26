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
    
    public Path(Path start, Path end) {
        this.path = new ArrayList<>();
        
        for (int i = 0; i < start.getLength(); i++) {
            this.path.add(start.get(i));
        }
        
        for (int i = 0; i < end.getLength(); i++) {
            this.path.add(end.get(i));
        }
    }
    
    public void add(Vertex V) {
        this.path.add(V);
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
    
}
