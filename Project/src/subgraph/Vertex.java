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

    private String N;
    private Path[] P;
    private boolean active;
    private int grade;

//    public static Vertex[] createVertexArray(String[] names) {
    public static Vertex[] createVertexArray(String... names) {
	Vertex[] created = new Vertex[names.length];
	for (int i = 0; i < names.length; i++) {
	    created[i] = new Vertex(names[i],names.length);
	}
	return created;
    }
    
    public Vertex(String name, int maxVertex) {
	this.N = name;
	this.P = new Path[maxVertex];
	this.grade = 0;
    }
    
    public boolean isActive() {
	return this.active;
    }
    
    public void switchState(boolean newState) {
	this.active = newState;
    }
    
    public void getPaths(Path[] P) {
	this.P = P;
	recalculateGrade();
    }
    
    
    public void recalculateGrade() {
	this.grade = 0;
	for (int i = 0; i < P.length; i++) {
	    if (P[i].getLength() == 2) {
		this.grade++;
	    }
	}
    }

    /**
     * @return the N
     */
    public String getN() {
	return N;
    }

    /**
     * @return the P
     */
    public Path[] getP() {
	return P;
    }

    /**
     * @return the grade
     */
    public int getGrade() {
	return grade;
    }
    
}
