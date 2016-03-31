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
 *
 * @author Jordan
 */
public class IndexGraph {
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        String delimiter = args[1];
        Graph G = new Graph(in, delimiter);
        while (!StdIn.isEmpty()) {
            String v = StdIn.readLine();
            if (v.equals("quit")) {
                break;
            }//if
            for (String w : G.adjacentTo(v)) {
                StdOut.println(" " + w);
            }//for
        }//while
    }//main
    
    
}// IndexGraph
