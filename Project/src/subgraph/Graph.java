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
    
    private Vertex[] V;
    private int[][] A;
    private int N;
    private int K;
    private boolean connected;
    private int[] X;
    
    public Graph(Vertex[] V, int[][] A) {
	this.V = V;
	this.A = A;
	this.N = V.length;
	this.X = new int[N];
	
	createBasicPaths();
    }
    
    public void switchVertexState(int id, boolean state) {
	V[id].switchState(state);
	for (int i = 0; i < N; i++) {
	    V[i].resetPaths();
	}
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
	    V[i].setPaths(vertexPaths);
	}
    }
    
    public void doBellmanFord() {
	int i = 0;
	while (i < N) {
	    X[i] = 0;
	    i++;
	}
	
	i = 0;
	this.connected = true;
        while (i < N && connected) {
	    connected = findPathsForVertex(i);
	    i++;
	}
    }

    private boolean findPathsForVertex(int a) {
	boolean checkConnection = false;
	
	boolean[] donePaths = new boolean[N];
	for (int j = 0; j < N; j++) {
	    donePaths[j] = false;
	}

	boolean done = false;
	int goalID = 0;
	int changes = 0;
	Vertex goal = V[goalID];
	Vertex start = V[a];
	
	if (!start.isActive()) {
	    return true;
	}

	while (!done) {
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
		    start.getP()[goalID].add(start.getP()[currentPathIndex]);
		    start.getP()[goalID].add(start.getP()[currentPathIndex].getGoal().getP()[goalID]);
		    X[currentPathIndex]++;
		    donePaths[goalID] = true;
		    changes++;
		}
	
	    } else {
		donePaths[goalID] = true;
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
		    checkConnection = true;
		} else {
		    if (changes == 0) {
			break;
		    }
		    changes = 0;
		    for (int k = 0; k < N; k++) {
			donePaths[k] = false;
		    }
		}
	    }
	    goal = V[goalID];
	}
	return checkConnection;
    }
    
    public boolean isConnected() {
	return connected;
    }
}
