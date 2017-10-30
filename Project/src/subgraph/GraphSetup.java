/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subgraph;

/**
 *
 * @author Andrés Movilla
 */
public class GraphSetup {
    
    private boolean[] states;
    private int K;
    private String code;
    private int C;
    
    public GraphSetup(int length) {
	this.states = new boolean[length];
	for (int i = 0; i < length; i++) {
	    this.states[i] = false;
	}
	this.K = 0;
	createC();
	genCode();
    }

    public GraphSetup(boolean[] states) {
	this.states = states;
	createC();
	this.K = this.C == 1? 0 : -1;
	genCode();
    }
    
    private void createC() {
	this.C = 0;
	for (int i = 0; i < states.length; i++) {
	    if (states[i]) {
		this.C++;
	    }
	}
    }
    
    private void genCode() {
	String s = "";
	for (int i = 0; i < this.states.length; i++) {
	    s += (this.states[i]? "1" : "0");
	}
	this.code = s;
    }
    
    public boolean isSetup(String otherCode) {
	return this.code.equals(otherCode);
    }
    
    public boolean canNext() {
	int i = 0;
	while (i < states.length && states[i]) {
	    i++;
	}
	return i != states.length;
    }
    
    public GraphSetup next() {
	boolean[] newStates = new boolean[states.length];
	for (int i = 0; i < states.length; i++) {
	    newStates[i] = states[i];
	}
	
	int i = 0;
	while (newStates[i]) {
	    newStates[i] = !newStates[i];
	    i++;
	}
	
	newStates[i] = !newStates[i];
	return new GraphSetup(newStates);
    }
    
    public int distanceTo(GraphSetup other) {
	int dist = 0;
	for (int i = 0; i < states.length; i++) {
	    if (!other.getVertexState(i) && this.getVertexState(i)) {
		dist++;
	    }
	    if (other.getVertexState(i) && !this.getVertexState(i)) {
		return states.length+1;
	    }
	}
	return dist;
    }
    
    public boolean getVertexState(int i) {
	return this.states[i];
    }

    public boolean isConnected() {
	return K != 0;
    }

    public void setK(int K) {
	this.K = K;
    }

    public int getK() {
	return K;
    }

    public int getC() {
	return C;
    }
    
    @Override
    public String toString() {
	return this.code;
    }
}
