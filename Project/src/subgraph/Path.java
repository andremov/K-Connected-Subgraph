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

    private ArrayList<Integer> idHistory;
    
    public Path() {
        this.idHistory = new ArrayList<>();
    }
    
    public Path(int i) {
        this.idHistory = new ArrayList<>();
	this.idHistory.add(i);
    }
    
    public Path(Path stitch1, Path stitch2) {
        this.idHistory = new ArrayList<>();
        
        for (int i = 0; i < stitch1.size(); i++) {
            this.idHistory.add(stitch1.get(i));
        }
        
        for (int i = 0; i < stitch2.size(); i++) {
            this.idHistory.add(stitch2.get(i));
        }
    }
    
    public int getEnd() {
	return this.get(this.size()-1);
    }
    
    public int getLength() {
	return this.idHistory.size();
    }
    
    public int get(int i) {
	return this.idHistory.get(i);
    }
    
    public int size() {
	return this.idHistory.size();
    }
    
}
