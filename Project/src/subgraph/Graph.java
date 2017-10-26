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
    int[] X;
    
    public Graph(Vertex[] V, int[][] A) {
	this.V = V;
	this.A = A;
	this.N = V.length;
	this.X = new int[N];
	
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
    
    public boolean doBellmanFord() {
	int i = 0;
	while (i < N) {
	    X[i] = 0;
	    i++;
	}
	
	boolean connected = true;
        while (i < N && connected) {
	    connected = findPathsForVertex(i);
	    i++;
	}
	
	return connected;
    }

    private boolean findPathsForVertex(int a) {
	boolean connected = false;
	
	boolean[] donePaths = new boolean[N];
	for (int j = 0; j < N; j++) {
	    donePaths[j] = false;
	}

	boolean done = false;
	int goalID = 0;
	Vertex goal = V[goalID];
	Vertex start = V[a];
	
	if (!start.isActive())
	    return true;

	while (!done) {
	    
	    int changes = 0;
	    
	    if (start.getP()[goalID].getGoal() != goal && goal.isActive()) {
		int currentPathIndex = -1;
		int currentLength = N;

		for (int j = 0; j < N; j++) {
		    if (start.getP()[j].getGoal() == V[j]) {
			Vertex midVertex = start.getP()[j].getGoal();
			int possibleLength = start.getP()[j].getLength() + midVertex.getP()[goalID].getLength();
			if (midVertex.getP()[goalID].getGoal() == goal && midVertex.isActive() && possibleLength < currentLength) {
			    currentPathIndex = j;
			    currentLength = possibleLength;
			}
		    }
		}
		if (currentPathIndex != -1) {
		    X[currentPathIndex]++;
		    donePaths[goalID] = true;
		    changes++;
		}
	
	    }

	    goalID++;
	    if (goalID == N) {
		goalID = 0;
		done = true;
		for (int k = 0; k < N; k++) {
		    if (!donePaths[k]) {
			done = false;
		    }
		}

		if (done) {
		    connected = true;
		} else {
		    if (changes == 0)
			break;
			
		    for (int k = 0; k < N; k++) {
			donePaths[k] = false;
		    }
		}
	    }
	    goal = V[goalID];
	}
	return connected;
    }
    
}
