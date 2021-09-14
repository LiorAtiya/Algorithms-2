//package Algorithms2;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.Map;
//
//public class MinSpanningTree {
//
//    public void Kruskal(Graph g) {
//        int vertices[] = new int[g.vertices];
//        for(int i=0 ; i < g.vertices ; i++){
//            vertices[i] = i;
//        }
//        DisjointSet d = new DisjointSet();
//        d.makeSet(vertices);
//
////        Arrays.sort(g.adjacencylist,);
//    }
//
//    // A class to represent a disjoint set
//    class DisjointSet {
//        private Map<Integer, Integer> parent = new HashMap();
//
//        // perform MakeSet operation
//        public void makeSet(int[] universe) {
//            // create `n` disjoint sets (one for each item)
//            for (int i : universe) {
//                parent.put(i, i);
//            }
//        }
//
//        // Find the root of the set in which element `k` belongs
//        public int Find(int k) {
//            // if `k` is root
//            if (parent.get(k) == k) {
//                return k;
//            }
//
//            // recur for the parent until we find the root
//            return Find(parent.get(k));
//        }
//
//        // Perform Union of two subsets
//        public void Union(int a, int b) {
//            // find the root of the sets in which elements
//            // `x` and `y` belongs
//            int x = Find(a);
//            int y = Find(b);
//
//            parent.put(x, y);
//        }
//    }
//
//    //-----------Graph with edges-----------
//    class Edge implements Comparable{
//        int source;
//        int destination;
//        int weight;
//
//        public Edge(int source, int destination, int weight) {
//            this.source = source;
//            this.destination = destination;
//            this.weight = weight;
//        }
//
//        @Override
//        public int compareTo(Object o) {
//            return this.weight - o.weight;
//        }
//    }
//
//    class Graph {
//        int vertices;
//        LinkedList<Edge>[] adjacencylist;
//
//        Graph(int vertices) {
//            this.vertices = vertices;
//            adjacencylist = new LinkedList[vertices];
//            //initialize adjacency lists for all the vertices
//            for (int i = 0; i < vertices; i++) {
//                adjacencylist[i] = new LinkedList<>();
//            }
//        }
//
//        public void addEdge(int source, int destination, int weight) {
//            Edge edge = new Edge(source, destination, weight);
//            adjacencylist[source].addFirst(edge); //for directed graph
//        }
//
//        public void printGraph() {
//            for (int i = 0; i < vertices; i++) {
//                LinkedList<Edge> list = adjacencylist[i];
//                for (int j = 0; j < list.size(); j++) {
//                    System.out.println("vertex-" + i + " is connected to " +
//                            list.get(j).destination + " with weight " + list.get(j).weight);
//                }
//            }
//        }
//    }
//}
