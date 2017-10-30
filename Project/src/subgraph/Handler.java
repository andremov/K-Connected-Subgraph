/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subgraph;

import java.util.ArrayList;
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
	
	int maxVertex = V.length;
	int max = (int) Math.pow(2,maxVertex);
	GraphSetup[] setups = new GraphSetup[ max ];
	setups[0] = new GraphSetup(maxVertex);
	int i = 0;
	while (setups[i].canNext()) {
	    i++;
	    setups[i] = setups[i-1].next();
	}
	
	Graph G = new Graph(V, C, setups);
	
	
	
	
    }
}
