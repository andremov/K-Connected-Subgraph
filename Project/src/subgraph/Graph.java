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
public class Graph {
    
    Vertex[] V;
    int[][] A;
    int N;
    int K;
    boolean isConnected;
    
    public Graph(Vertex[] V, int[][] A) {
	this.V = V;
	this.A = A;
	this.N = V.length;
	
	createBasicPaths();
    }
    
    private void createBasicPaths() {
	for (int i = 0; i < N; i++) {
	    int[] vertexConnections = A[i];
	    Path[] vertexPaths = new Path[N];
	    for (int j = 0; j < N; j++) {
		vertexPaths[j] = new Path(V[i]);
		if (vertexConnections[j] == 1) {
		    vertexPaths[j].add(V[j]);
		}
	    }
	}
    }
    
    public void doBellmanFord() {
	
    }
    
}
