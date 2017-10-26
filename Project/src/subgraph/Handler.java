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
	
	Graph G = new Graph(V,C);
	
	G.doBellmanFord();
	System.out.println("Given graph G is connected? "+G.isConnected());
	
//	for (int i = 0; i < maxVertex; i++) {
//	    G.switchVertexState(i, false);
//	    G.doBellmanFord();
//	    System.out.println("Altered graph G with disabled vector "+i+" is connected? "+G.isConnected());
//	    G.switchVertexState(i, true);
//	}
	
	
//		
//	in = new Scanner(System.in);
//	System.out.println("Digite numero de vertices.");
//	NUM_VERTEX = getInt();
//	
//        vertexes = new Vertex[NUM_VERTEX];
//		
//	consoleInput();
    }
    
//    public static void requestAdyacentMatrix() {
//	getIntRow(1);
//	boolean[][] adyacentMatrix = new boolean[NUM_VERTEX][NUM_VERTEX];
//	for (int i = 1; i < NUM_VERTEX; i++) {
//	    String presentColumns = "";
//	    for (int j = 0; j < i; j++) {
//		presentColumns += j+" ";
//	    }
//	    System.out.println("Digite conexiones para vertice "+i+" con los vertices:");
//	    System.out.println(presentColumns);
////	    System.out.print(i+" ");
//	    int[] intRow = getIntRow(i);
//	    for (int j = 0; j < intRow.length; j++) {
//		adyacentMatrix[i][j] = intRow[j] == 1;
//	    }
//	    
//	}
//	for (int i = 0; i < NUM_VERTEX; i++) {
//	    for (int j = 0; j <= i; j++) {
//		boolean value = adyacentMatrix[i][j] || adyacentMatrix[j][i] || i == j;
//		adyacentMatrix[i][j] = value;
//		adyacentMatrix[j][i] = value;
//	    }
//	}
//	
//	for (int i = 0; i < NUM_VERTEX; i++) {
//	    vertexes[i] = new Vertex(i, NUM_VERTEX);
//	    vertexes[i].establishConnections(adyacentMatrix[i]);
//	}
//    }
//    
//    public static void createMatrix() {
//        for (int i = 0; i < NUM_VERTEX; i++) {
//	    for (int j = 0; j < NUM_VERTEX; j++) {
//		
//	    }
//        }
//    }
//    
//    public static void solve() {
//		/*
//        int numBoxes = 0;
//        for (int i = 0; i < NUM_VERTEX; i++) {
//            if (places.get(i).isBox()) {
//                numBoxes++;
//            }
//        }
//		
//        while (numBoxes > 0) {
//            int lowestBoxID = -1;
//            int lowestPersonID = -1;
//            double lowestCost = -1;
//			
//            for (int boxID = 0; boxID < NUM_VERTEX; boxID++) {
//                if (places.get(boxID).isBox() && !places.get(boxID).isTaken()) {
//                    for (int personID = 0; personID < people.size(); personID++) {
//                        Vertex personStation = places.get(people.get(personID).getLast());
//                        double thisCost = personStation.getCostTo(boxID);
//                        if (thisCost < lowestCost || lowestCost == -1) {
//                            lowestCost = thisCost;
//                            lowestBoxID = boxID;
//                            lowestPersonID = personID;
//                        }
//                    }
//                }
//            }
//            
//            Person thisPerson = people.get(lowestPersonID);
//            Vertex personStation = places.get(thisPerson.getLast());
//            Path path = personStation.getPathTo(lowestBoxID);
//            places.get(lowestBoxID).takeBox();
//            for (int i = 0; i < path.size(); i++) {
//                thisPerson.history.add(path.get(i).getConnection());
//                thisPerson.distances.add(path.get(i).getCost());
//            }
//            numBoxes--;
//        }
//	*/
//    }
//    
//    private static int getInt() {
//	boolean acceptedValue = false;
//	int value = 0;
//	while (!acceptedValue) {
//	    try {
//		value = in.nextInt();
//		acceptedValue = true;
//	    } catch (Exception e) { }
//	}
//	return value;
//    }
//
//    private static int[] getIntRow(int numInfo) {
//	int[] constructedLine = new int[numInfo];
//	boolean accepted = false;
//	
//	while (!accepted) {
//	    try {
//		String line = in.nextLine();
//
//		if (line.isEmpty())
//		    break;
//
//		String[] splitLine = line.split(" ");
//		
//		if (splitLine.length != numInfo)
//		    throw new Exception();
//		
//		int[] attempt = new int[numInfo];
//		for (int i = 0; i < numInfo; i++) {
//		    attempt[i] = Integer.parseInt(splitLine[i]);
//		}
//		constructedLine = attempt;
//		accepted = true;
//	    } catch (Exception e) { }
//	}
//	
//	return constructedLine;
//    }
//    
//    public static void consoleInput() {
//	
//	requestAdyacentMatrix();
//	
////	System.out.println("So according to adyacency matrix...");
////	for (int i = 0; i < NUM_VERTEX; i++) {
////	    String summary = ("Vertex " + i + " has path to: ");
////	    for (int j = 0; j < NUM_VERTEX; j++) {
////		if (vertexes[i].getLengthTo(j) > 0 && i != j) {
////		    summary += j +", ";
////		}
////	    }
////	    summary += "and that's it.";
////	    System.out.println(summary);
////	}
//
//	createMatrix();
//	
///*
//	System.out.println("Digite numero de cajas a agregar.");
//	int numCajas = getInt();
//
//	while (numCajas <= 0 || numCajas >= NUM_VERTEX) {
//		System.out.println("Digite un numero valido.");
//		numCajas = getInt();
//	}
//
//	for (int i = 0; i < numCajas; i++) {
//		System.out.println("Donde desea agregar la caja "+(i+1)+"?");
//		System.out.println("Posibilidades:");
//		for (int j = 0; j < NUM_VERTEX; j++) {
//			if (!places.get(j).isBox()) {
//				System.out.println(j+": "+places.get(j).getName());
//			}
//		}
//		int newBox = getInt();
//
//		while (places.get(newBox).isBox() || newBox < 0 || newBox >= NUM_VERTEX) {
//			System.out.println("Digite un sitio valido.");
//			newBox = getInt();
//		}
//
//		places.get(newBox).setBox(true);
//		System.out.println("Se agregó la caja a "+places.get(newBox).getName()+".");
//	}
//
//	System.out.println("Digite numero de personas a agregar.");
//	int numPersonas = getInt();
//
//	while (numPersonas <= 0 || numPersonas >= NUM_VERTEX) {
//		System.out.println("Digite un numero valido.");
//		numPersonas = getInt();
//	}
//
//	for (int i = 0; i < numPersonas; i++) {
//		System.out.println("Donde desea agregar la persona "+(i+1)+"?");
//		for (int j = 0; j < NUM_VERTEX; j++) {
//			if (!hasPerson(j)) {
//				System.out.println(j+": "+places.get(j).getName());
//			}
//		}
//		int newPerson = getInt();
//
//		while (hasPerson(newPerson) || newPerson < 0 || newPerson >= NUM_VERTEX) {
//			System.out.println("Digite un sitio valido.");
//			newPerson = getInt();
//		}
//
//		people.add(new Person(newPerson));
//		System.out.println("Se agregó la persona a "+places.get(newPerson).getName()+".");
//	}
//
//
//	solve();
//
//	double totalCost = 0;
//	for (int i = 0; i < people.size(); i++) {
//		String start = places.get(people.get(i).history.get(0)).getName();
//		double thisPersonCost = 0;
//		System.out.println("La persona "+(i+1)+" empezó en "+start+".");
//		for (int j = 1; j < people.get(i).history.size(); j++) {
//			int id = people.get(i).history.get(j);
//			String name = places.get(id).getName();
//			double cost = people.get(i).distances.get(j);
//			System.out.println("Se movió a "+name+", costando "+(cost/100)+".");
//			if (places.get(id).isBox()) {
//				System.out.println(name+" tiene una caja.");
//			}
//			thisPersonCost = thisPersonCost + cost;
//		}
//		System.out.println("Esta persona gastó "+(thisPersonCost/100)+".");
//		totalCost = totalCost+thisPersonCost;
//	}
//	System.out.println("Se gastó en total "+(totalCost/100)+".");
//*/
//    }

}
