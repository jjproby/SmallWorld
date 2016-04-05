/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smallworld;

import edu.princeton.cs.In;
import edu.princeton.cs.StdIn;
import edu.princeton.cs.StdOut;

/**
 * Finds the all the adjacent vertexes of a chosen vertex
 * @author Jordan
 */
public class IndexGraph {
    
    /**
     * Finds all the adjacent vertexes to a chosen vertex
     * @param G The graph being used
     * @param pf The Pathfinder being used
     */
    
    public static void findIndex(Graph G, PathFinder pf) {
        while (!StdIn.isEmpty()) {
            String v = StdIn.readLine();
            if (v.equals("quit")) {
                break;
            }//if
            for (String w : G.adjacentTo(v)) {
                StdOut.println(" " + w);
                //pf.reportOne(w);
            }//for
        }//while
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        String delimiter = args[1];
        Graph G = new Graph(in, delimiter);
        String s = args[2];
        PathFinder pf = new PathFinder(G, s);
        findIndex(G, pf);
    }//main
    
    
}// IndexGraph
