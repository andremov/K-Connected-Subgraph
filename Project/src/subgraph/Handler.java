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
	
    static ArrayList<Vertex> places;
    static ArrayList<Person> people;
    static int NUM_VERTEX;
            
    public static void init() {
		
        places = new ArrayList<>();
        people = new ArrayList<>();
		
	consoleInput();
    }
    
    public static void createMatrix() {
		/*
        for (int i = 0; i < NUM_VERTEX; i++) {
			
            boolean[] allDone = new boolean[NUM_VERTEX];
            for (int k = 0; k < NUM_VERTEX; k++) {
                allDone[k] = false;
            }
			
            boolean done = false;
            int search = 0;
			
            while (!done) {
                allDone[search] = true;
                if (search != i) {
                    Place place = places.get(i);
                    int currentPathIndex = -1;
                    double currentCost = 0.0;
                    for (int k = 0; k < place.getConnections().size(); k++) {
                        if (place.getConnections().get(k).getGoal() == search) {
                            currentPathIndex = k;
                            currentCost = place.getConnections().get(k).getTotal();
                        }
                    }
					
                    for (int k = 0; k < place.getConnections().size(); k++) {
                        Path fromPlaceToMiddle = place.getConnections().get(k);
                        if (fromPlaceToMiddle.getGoal() != search && fromPlaceToMiddle.getGoal() != i) {
                            Place middle = places.get(fromPlaceToMiddle.getGoal());
                            for (int l = 0; l < middle.getConnections().size(); l++) {
                                if (middle.getConnections().get(l).getGoal() != fromPlaceToMiddle.getGoal() && middle.getConnections().get(l).getGoal() == search) {
                                    Path fromMiddleToGoal = middle.getConnections().get(l);
                                    if (currentPathIndex != -1) {
										double newCost = fromPlaceToMiddle.getTotal() + fromMiddleToGoal.getTotal();
										if (newCost < currentCost) {
											place.getConnections().set(currentPathIndex, new Path(fromPlaceToMiddle,fromMiddleToGoal));
											allDone[search] = false;
										}
                                    } else {
                                        place.getConnections().add(new Path(fromPlaceToMiddle,fromMiddleToGoal));
                                        currentPathIndex = place.getConnections().size()-1;
                                        currentCost = place.getConnections().get(place.getConnections().size()-1).getTotal();
                                        allDone[search] = false;
                                    }
                                }
                            }
                        }
                    }
                }
                
				
                search++;
                if (search == NUM_VERTEX) {
                    search = 0;
                    done = true;
                    for (int k = 0; k < NUM_VERTEX; k++) {
                        if (!allDone[k]) {
                            done = false;
                        }
                    }
					
                    if (!done) {
                        for (int k = 0; k < NUM_VERTEX; k++) {
                            allDone[k] = false;
                        }
                    }
                }
            }
        }
*/
    }
    
    public static void solve() {
		
        int numBoxes = 0;
        for (int i = 0; i < NUM_VERTEX; i++) {
            if (places.get(i).isBox()) {
                numBoxes++;
            }
        }
		
        while (numBoxes > 0) {
            int lowestBoxID = -1;
            int lowestPersonID = -1;
            double lowestCost = -1;
			
            for (int boxID = 0; boxID < NUM_VERTEX; boxID++) {
                if (places.get(boxID).isBox() && !places.get(boxID).isTaken()) {
                    for (int personID = 0; personID < people.size(); personID++) {
                        Vertex personStation = places.get(people.get(personID).getLast());
                        double thisCost = personStation.getCostTo(boxID);
                        if (thisCost < lowestCost || lowestCost == -1) {
                            lowestCost = thisCost;
                            lowestBoxID = boxID;
                            lowestPersonID = personID;
                        }
                    }
                }
            }
            
            Person thisPerson = people.get(lowestPersonID);
            Vertex personStation = places.get(thisPerson.getLast());
            Path path = personStation.getPathTo(lowestBoxID);
            places.get(lowestBoxID).takeBox();
            for (int i = 0; i < path.size(); i++) {
                thisPerson.history.add(path.get(i).getConnection());
                thisPerson.distances.add(path.get(i).getCost());
            }
            numBoxes--;
        }
    }
    
    public static void bestConnection(int index) {
        Vertex thisPlace = places.get(index);
        if (thisPlace.isBox() && !thisPlace.isTaken()) {
            if (hasPerson(index)) {
                thisPlace.takeBox();
            }
        }
    }
    
    private static int getInt() {
	boolean acceptedValue = false;
	int value = 0;
	Scanner in = new Scanner(System.in);
	while (!acceptedValue) {
	    try {
		value = in.nextInt();
		acceptedValue = true;
	    } catch (Exception e) { }
	}
	return value;
    }

    public static void consoleInput() {
	System.out.println("Digite numero de vertices.");
	NUM_VERTEX = getInt();

	createMatrix();
	System.out.println("Digite numero de cajas a agregar.");
	int numCajas = getInt();

	while (numCajas <= 0 || numCajas >= NUM_VERTEX) {
		System.out.println("Digite un numero valido.");
		numCajas = getInt();
	}

	for (int i = 0; i < numCajas; i++) {
		System.out.println("Donde desea agregar la caja "+(i+1)+"?");
		System.out.println("Posibilidades:");
		for (int j = 0; j < NUM_VERTEX; j++) {
			if (!places.get(j).isBox()) {
				System.out.println(j+": "+places.get(j).getName());
			}
		}
		int newBox = getInt();

		while (places.get(newBox).isBox() || newBox < 0 || newBox >= NUM_VERTEX) {
			System.out.println("Digite un sitio valido.");
			newBox = getInt();
		}

		places.get(newBox).setBox(true);
		System.out.println("Se agregó la caja a "+places.get(newBox).getName()+".");
	}

	System.out.println("Digite numero de personas a agregar.");
	int numPersonas = getInt();

	while (numPersonas <= 0 || numPersonas >= NUM_VERTEX) {
		System.out.println("Digite un numero valido.");
		numPersonas = getInt();
	}

	for (int i = 0; i < numPersonas; i++) {
		System.out.println("Donde desea agregar la persona "+(i+1)+"?");
		for (int j = 0; j < NUM_VERTEX; j++) {
			if (!hasPerson(j)) {
				System.out.println(j+": "+places.get(j).getName());
			}
		}
		int newPerson = getInt();

		while (hasPerson(newPerson) || newPerson < 0 || newPerson >= NUM_VERTEX) {
			System.out.println("Digite un sitio valido.");
			newPerson = getInt();
		}

		people.add(new Person(newPerson));
		System.out.println("Se agregó la persona a "+places.get(newPerson).getName()+".");
	}


	solve();

	double totalCost = 0;
	for (int i = 0; i < people.size(); i++) {
		String start = places.get(people.get(i).history.get(0)).getName();
		double thisPersonCost = 0;
		System.out.println("La persona "+(i+1)+" empezó en "+start+".");
		for (int j = 1; j < people.get(i).history.size(); j++) {
			int id = people.get(i).history.get(j);
			String name = places.get(id).getName();
			double cost = people.get(i).distances.get(j);
			System.out.println("Se movió a "+name+", costando "+(cost/100)+".");
			if (places.get(id).isBox()) {
				System.out.println(name+" tiene una caja.");
			}
			thisPersonCost = thisPersonCost + cost;
		}
		System.out.println("Esta persona gastó "+(thisPersonCost/100)+".");
		totalCost = totalCost+thisPersonCost;
	}
	System.out.println("Se gastó en total "+(totalCost/100)+".");

    }
}
