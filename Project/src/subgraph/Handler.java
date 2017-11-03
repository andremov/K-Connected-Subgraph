/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subgraph;

/**
 *
 * @author movillaf
 */
public abstract class Handler {
	
//    static Vertex[] vertexes;
//    static int NUM_VERTEX;
//    static Scanner in;
    
	public static Graph G;
	
    public static void init() {
		new MatrixWindow();
		new DisplayWindow();
    }
	
	public static void createGraph(Vertex[] V, int[][] C) {
		int numVertex = V.length;
		int max = (int) Math.pow(2,numVertex);
		GraphSetup[] setups = new GraphSetup[ max ];
		setups[0] = new GraphSetup(numVertex);
		int i = 0;
		while (setups[i].canNext()) {
			i++;
			setups[i] = setups[i-1].next();
		}
                System.out.println("Creando grafo...");
		G = new Graph(V, C, setups);
                
	}
}
