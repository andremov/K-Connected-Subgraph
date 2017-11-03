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
    private GraphSetup[] allSetups;
    private GraphSetup currentSetup;
	
	// DISPLAY
	private int locatingV;
    
    public Graph(Vertex[] V, int[][] A, GraphSetup[] setups) {
		this.V = V;
		this.A = A;
		this.N = V.length;
		this.allSetups = setups;
		this.currentSetup = setups[setups.length-1];
		this.locatingV = 0;
		
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
				currentSetup.setK(findPathsForVertex(0)? -1 : 0);
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
	
	public void locateVertex(int x, int y) {
		V[locatingV].locate(x,y);
		locatingV = (locatingV+1)%V.length;
	}
    
    public void minimumGraphFor(int K) {
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
		if (index != -1) {
		    this.setup(index);
		} else {
		    this.setup(0);
		}
    }
    
    public void maximumGraphFor(int K) {
		int maximumC = -1;
		int index = -1;
		for (int i = 0; i < this.allSetups.length; i++) {
			GraphSetup setup = this.allSetups[i];
			if (setup.getK() == K) {
				if (setup.getC() > maximumC) {
					maximumC = setup.getC();
					index = i;
				}
			}
		}
		if (index != -1) {
		    this.setup(index);
		} else {
		    this.setup(0);
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
			return findPathsForVertex(a+1);
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
	
	public Vertex getVertex(int id) {
		return V[id];
	}
	
	public boolean getConnection(int i, int j) {
		return A[i][j] == 1;
	}
    
    public int numVertex() {
		return N;
    }
}
