package smallworld;

import edu.princeton.cs.In;
import edu.princeton.cs.StdOut;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * ****************************************************************************
 * Compilation: javac PathFinder.java Execution: java Pathfinder input.txt
 * delimiter source Dependencies: Queue.java Stack.java Graph.java
 *
 * Runs breadth first search algorithm from source s on a graph G. After
 * preprocessing the graph, can process shortest path queries from s to any
 * vertex t.
 *
 * % java PathFinder routes.txt " " JFK LAX JFK ORD PHX LAX distance 3 MCO JFK
 * MCO distance 1 DFW JFK ORD DFW distance 2
 *
 *****************************************************************************
 */
public class PathFinder {

    // prev[v] = previous vertex on shortest path from s to v
    // dist[v] = length of shortest path from s to v
    private ST<String, String> prev = new ST<String, String>();
    private ST<String, Integer> dist = new ST<String, Integer>();

    // run BFS in graph G from given source vertex s
    public PathFinder(Graph G, String s) {

        // put source on the queue
        Queue<String> q = new Queue<String>();
        q.enqueue(s);
        dist.put(s, 0);

        // repeated remove next vertex v from queue and insert
        // all its neighbors, provided they haven't yet been visited
        while (!q.isEmpty()) {
            String v = q.dequeue();
            for (String w : G.adjacentTo(v)) {
                if (!dist.contains(w)) {
                    q.enqueue(w);
                    dist.put(w, 1 + dist.get(v));
                    prev.put(w, v);
                }
            }
        }
    }

    // is v reachable from the source s?
    public boolean hasPathTo(String v) {
        return dist.contains(v);
    }

    // return the length of the shortest path from v to s
    public int distanceTo(String v) {
        if (!hasPathTo(v)) {
            return Integer.MAX_VALUE;
        }
        return dist.get(v);
    }

    // return the shortest path from v to s as an Iterable
    public Iterable<String> pathTo(String v) {
        Stack<String> path = new Stack<String>();
        while (v != null && dist.contains(v)) {
            path.push(v);
            v = prev.get(v);
        }
        return path;
    }

    public static void main(String[] args) {
        String filename = "movies.txt";
        String delimiter = "/";

        System.out.println(filename);
        System.out.println(">" + delimiter + "<");
        In in = new In(filename);
        Graph G = GraphGenerator.read(in, delimiter);
        Scanner findName = new Scanner(System.in);
        System.out.println("Enter an actor name. Last name, First name: ");
        String s = findName.nextLine();
        PathFinder pf = new PathFinder(G, s);
        System.out.println("Use all actors or one actor? 1 for one, 2 for all");
        int answer = findName.nextInt();
        switch (answer) {
            case 1:
                Scanner otherName = new Scanner(System.in);
                System.out.println("Enter another actor name. Last name, First name: ");
                String actor = otherName.nextLine();
                pf.reportOne(actor);
                break;
            case 2:
                System.out.println("Enter the maximum distance between the actors: ");
                int distance = findName.nextInt();
                In in2 = new In(filename);
                List<String> usedNames = new ArrayList<>();
                while (in2.hasNextLine()) {
                    String line = in2.readLine();
                    String[] names2 = line.split(delimiter);
                    for (int i = 1; i < names2.length; i++) {
                        if (!usedNames.contains(names2[i])) {
                            pf.reportAll(names2[i], distance);
                            usedNames.add(names2[i]);
                        }//if
                    }//for
                }//while
                break;
        }//switch
    }//main

        /* TEST NAMES
        Smith, Will (I);
        Radcliffe, Daniel;
        Cage, Nicolas; 
        Jackson, Samuel L.
        Bloom, Orlando
        Wood, Elijah
        */

    private void reportAll(String actor, int goodDistance) {

        if (this.distanceTo(actor) / 2 <= goodDistance) {

            for (String v : this.pathTo(actor)) {
                StdOut.println("   " + v);
            }//for
            StdOut.println("distance " + (this.distanceTo(actor) / 2));
        }//if
    } // report( PathFinder, String )

    public void reportOne(String actor) {

        for (String v : this.pathTo(actor)) {
            StdOut.println("   " + v);
        }//for
        StdOut.println("distance " + (this.distanceTo(actor) / 2));
    } // report( PathFinder, String )

} // PathFinder
