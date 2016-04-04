/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smallworld;

import edu.princeton.cs.In;
import edu.princeton.cs.StdOut;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Jordan
 */
public class ActorGraph {


    public static void main(String[] args) {
        In filename = new In(args[0]);
        String delimiter = args[1];
        Graph G = new Graph(filename, delimiter);
        String actor = args[2];
        PathFinder pf = new PathFinder(G, actor);
        Graph BaconGraph = new Graph();
        System.out.println("Enter an actor name: ");
        Scanner name = new Scanner(System.in);
        String firstActor = name.nextLine();
        String[] actorPath = pf.reportArray(firstActor);
        System.out.println(Arrays.toString(actorPath));
        for (int i = 0; i < actorPath.length - 1; i++) {
            BaconGraph.addEdge(actorPath[i], actorPath[i + 1]);
        }
        for (int i = 0; i < actorPath.length - 1; i++) {
            for (String w : G.adjacentTo(actorPath[i])) {
                if (BaconGraph.hasVertex(w) == false) {
                    BaconGraph.addVertex(w);
                }
                if (BaconGraph.hasEdge(w, actorPath[i]) == false) {
                    BaconGraph.addEdge(w, actorPath[i]);
                }//if
            }//for
        }//for
        //StdOut.println(BaconGraph);
        BaconGraph.writeNeatoFile(actorPath);

    }//main
}//ActorGraph
