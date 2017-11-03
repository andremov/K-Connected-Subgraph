/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subgraph;

import java.util.ArrayList;


/**
 *
 * @author Andres
 */
public class Vertex {

    private String N;
    private Path[] P;
    private boolean active;
    private int grade;
	
	
	// DISPLAY VARS
	private int x;
	private int y;

//    public static Vertex[] createVertexArray(String[] names) {
    public static Vertex[] createVertexArray(String... names) {
		Vertex[] created = new Vertex[names.length];
		for (int i = 0; i < names.length; i++) {
			created[i] = new Vertex(names[i],names.length);
		}
		return created;
    }
    
    private static String generateName(int id) {
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                                                "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String result = "";
        ArrayList<Integer> digits = new ArrayList<>();
        digits.add(0);
        int disposable = id;

        while (disposable > 0) {
            disposable--;
            digits.set(0, digits.get(0)+1);
            int i = 0;
            while (digits.get(i) >= 26) {
                digits.set(i, digits.get(i)-26);
                if (digits.size() == i+1) {
                    digits.add(0);
                }
                digits.set(i+1, digits.get(i+1)+1);
            }
        }

        for (int i = 0; i < digits.size(); i++) {
            result += letters[digits.get(i)];
        }

        return result;
    }
    
    public static Vertex[] createVertexArray(int length) {
            Vertex[] created = new Vertex[length];
            for (int i = 0; i < length; i++) {
                created[i] = new Vertex(generateName(i), length);
            }
            return created;
    }
    
    public Vertex(String name, int maxVertex) {
		this.N = name;
		this.P = new Path[maxVertex];
		this.grade = 0;
		this.active = true;
    }
    
    public boolean isActive() {
		return this.active;
    }
    
    public void switchState(boolean newState) {
		this.active = newState;
    }
    
    public void resetPaths() {
		for (int i = 0; i < P.length; i++) {
			if (P[i].getLength() > 2) {
			P[i] = new Path(this);
			}
		}
    }
    
    public void setPaths(Path[] P) {
		this.P = P;
		recalculateGrade();
    }
    
	public void locate(int x, int y) {
		this.x = x;
		this.y = y;
	}
    
    public void recalculateGrade() {
		this.grade = 0;
		for (int i = 0; i < P.length; i++) {
			if (P[i].getLength() == 2) {
			this.grade++;
			}
		}
    }

    /**
     * @return the N
     */
    public String getN() {
	return N;
    }

    /**
     * @return the P
     */
    public Path[] getP() {
		return P;
    }

    /**
     * @return the grade
     */
    public int getGrade() {
		return grade;
    }
    
    public String toString() {
		return getN();
    }

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
