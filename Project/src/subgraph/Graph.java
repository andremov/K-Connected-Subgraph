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
    private int[] X;
    private GraphSetup[] allSetups;
    private GraphSetup currentSetup;
    
    
    public Graph(Vertex[] V, int[][] A, GraphSetup[] setups) {
	this.V = V;
	this.A = A;
	this.N = V.length;
	this.X = new int[N];
	this.allSetups = setups;
	this.currentSetup = setups[setups.length-1];
	
	createBasicPaths();
	solveAll();
    }
    
    public void setup(int id) {
	this.currentSetup = allSetups[id];
	for (int i = 0; i < N; i++) {
	    V[i].switchState(currentSetup.getVertexState(i));
	}
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
    
    private void solveAll() {
	for (int i = 0; i < this.allSetups.length; i++) {
	    if (this.allSetups[i].getK() == -1) {
		this.setup(i);
		doBellmanFord();
	    }
	}
	for (int i = 0; i < this.allSetups.length; i++) {
	    GraphSetup set = this.allSetups[i];
	    if (set.getK() == -1) {
		int minDist = N;
		for (int j = 0; j < this.allSetups.length; j++) {
		    GraphSetup other = this.allSetups[j];
		    if (i != j && !other.isConnected()) {
			minDist = Integer.min(minDist, set.distanceTo(other));
		    }
		}
		set.setK(minDist);
	    }
	}
    }
    
    public String minimumGraphFor(int K) {
	int minimumC = N;
	int index = -1;
	for (int i = 0; i < this.allSetups.length; i++) {
	    GraphSetup setup = this.allSetups[i];
	    if (setup.getK() == K) {
		if (setup.getC() < minimumC) {
		    minimumC = setup.getC();
		    index = i;
		}
	    }
	}
	return this.allSetups[index].toString();
    }
    
    private void doBellmanFord() {
	int i = 0;
	while (i < N) {
	    X[i] = 0;
	    i++;
	}
	
	i = 0;
	boolean connected = true;
        while (i < N && connected) {
	    connected = findPathsForVertex(i);
	    i++;
	}
	currentSetup.setK(connected? -1 : 0);
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
		int currentLength = N+1;
		for (int j = 0; j < N; j++) {
		    if (start.getP()[j].getGoal() == V[j]) {
			Vertex midVertex = start.getP()[j].getGoal();
			if (midVertex.isActive() && midVertex.getP()[goalID].getGoal() == goal) {
			    int possibleLength = start.getP()[j].getLength() + midVertex.getP()[goalID].getLength() - 1;
			    if (possibleLength < currentLength) {
				currentPathIndex = j;
				currentLength = possibleLength;
			    }
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
	return currentSetup.isConnected();
    }
    
    public String vertexName(int i) {
	return V[i]+"";
    }
    
    public int whatIsKFor(String code) {
	int i = 0;
	while (!this.allSetups[i].isSetup(code)) {
	   i++;
	}
	return this.allSetups[i].getK();
    }
    
    public int numVertex() {
	return N;
    }
}
