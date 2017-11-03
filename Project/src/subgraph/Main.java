/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subgraph;

import java.io.File;
import java.nio.file.Files;

/**
 *
 * @author Andres
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	Handler.init();
        
        try {
            File archivo = new File("adyacencia.txt");
            
            boolean created = false;
            Vertex[] V = null;
            int[][] C = null;
            for (int i = 0; i < Files.readAllLines(archivo.toPath()).size(); i++) {
                String[] line = Files.readAllLines(archivo.toPath()).get(i).split(" ");
                if (!created) {
                    created = true;
                    V = Vertex.createVertexArray(line.length);
                    C = new int[line.length][line.length];
                }
                for (int j = 0; j < line.length; j++) {
                    C[j][i] = Integer.parseInt(line[j]);
                }
            }
            Handler.createGraph(V,C);
        } catch (Exception ex) { }
    }
	
}
