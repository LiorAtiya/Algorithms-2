//package Algorithms2;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Stack;
//
//public class EulerPath {
//
//    // the function to print euler path or circuit
//    String EulerCycle(Integer v,Graph g){
//
//        Stack<Integer> cpath = new Stack<>();	// current path
//        Stack<Integer> epath = new Stack<>();	// euler path
//
//        cpath.push(v);		// euler path starts from v
//
//        while(!cpath.empty()){
//            int u = cpath.peek();
//
//            if(g.adj.get(u).size() == 0){
//                // if all edges from u are visited
//                // pop u and push it to euler path
//                epath.push(u);
//                cpath.pop();
//            }
//            else{
//                // if all edges from u are not visited
//                // select any edge (u, v)
//                // push v to current path and remove edge (u, v) from the graph
//                cpath.push(g.adj.get(u).iterator().next());
//                g.removeEdge(u,g.adj.get(u).iterator().next());
//            }
//        }
//
//        String path = "";
//        path += epath.pop();
//        while(!epath.empty()){
//            path += "->" + epath.peek();
//            epath.pop();
//        }
//        return path;
//    }
//
//    //------------------Class Graph------------------//
//    class Graph{
//
//        // adjacency list
//        Map<Integer, HashSet<Integer>> adj;
//
//        // initialize adjacency map for v nodes
//        public Graph(){
//            adj = new HashMap<>(length);
//        }
//
//        // add edge (u, v) to graph
//        void addEdge(int u, int v){
//            if(!adj.containsKey(u)){
//                this.adj.put(u,new HashSet<>());
//            }
//            this.adj.get(u).add(v);
//            if(!adj.containsKey(v)){
//                this.adj.put(v,new HashSet<>());
//            }
//            this.adj.get(v).add(u);
//        }
//
//        // remove edge (u, v) from the graph
//        void removeEdge(int v,int u){
//            adj.get(v).remove(u);
//            adj.get(u).remove(v);
//        }
//
//        void print(){
//            for (Integer x : this.adj.keySet()){
//                System.out.println(x + ": " + adj.get(x));
//            }
//        }
//    }
//}
