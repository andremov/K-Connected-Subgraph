/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subgraph;

import java.util.Scanner;

/**
 *
 * @author movillaf
 */
public abstract class Handler {
	
    static Vertex[] vertexes;
    static int NUM_VERTEX;
    static Scanner in;
            
    public static void init() {
	int maxVertex = 8;
	Vertex[] V = Vertex.createVertexArray("A", "B", "C", "D", "E", "F", "G", "H");
	int[][] C = {
	    {0, 1, 1, 0, 0, 1, 0, 0}, // 0
	    {1, 0, 1, 0, 0, 0, 0, 0}, // 1
	    {1, 1, 0, 0, 0, 0, 0, 0}, // 2
	    {0, 0, 0, 0, 1, 1, 0, 1}, // 3
	    {0, 0, 0, 1, 0, 0, 1, 1}, // 4
	    {1, 0, 0, 1, 0, 0, 0, 1}, // 5
	    {0, 0, 0, 0, 1, 0, 0, 1}, // 6
	    {0, 0, 0, 1, 1, 1, 1, 0}, // 7
	};
	
	Graph G = new Graph(V, C);
	
	G.doBellmanFord();
	System.out.println("Given graph G is connected? "+G.isConnected());
	System.out.println("K of graph G: "+getK(G));
	
    }
    
    public static int getK(Graph g) {
	return getK(g, 0);
    }
    
    public static int getK(Graph g, int i) {
	g.switchVertexState(i, false);
	g.doBellmanFord();
//	System.out.println("Altered graph G with disabled vector "+g.vectorName(i)+" is connected? "+g.isConnected());
	boolean c = g.isConnected();
	g.switchVertexState(i, true);
	int returnK = c? (i+1==g.numVertex()? 0 : getK(g,i+1)) : 1;
	return returnK;
    }
}
