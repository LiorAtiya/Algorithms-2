// ID: 315814475
import java.util.*;

public class Euler {

    boolean isConnected;
    boolean isCalculated;
    int odd_count;
    int index_odd_vertex;
    int length;
    String path;
    Graph g;

    public Euler (boolean[][] adj_matrix){
        this.length = adj_matrix.length;
        isConnected = false;
        odd_count = 0;
        index_odd_vertex = 0;
        this.path = null;

        //Convert adj_matrix to class Graph
        this.g = new Graph();
        for(int i=0 ; i < length ; i++){
            for(int j=i+1 ; j < length ; j++){
                if(adj_matrix[i][j] == true){
                    this.g.addEdge(i,j);
                }
            }
        }

    }

    //O(|V|+|E|)
    public boolean[] BFS(int s){
        boolean visited[] = new boolean[this.length];

        Queue<Integer> vertices = new LinkedList<>();
        vertices.add(s);
        visited[s] = true;

        while(!vertices.isEmpty()){
            int src = vertices.remove();

            for(Integer neighbor : g.adj.get(src)){
                if(!visited[neighbor]){
                    vertices.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
        return visited;
    }


    public boolean has_euler_path(){
        //Empty graph
        if(length == 0) return false;
        //One vertex
        if(length == 1) return true;
        //First time to calculate
        if(!isCalculated) {
            isCalculated = true;
            boolean visited[] = BFS(0);
            odd_count = 0;
            for (int i = 0; i < length; i++) {
                //Checks disconnected graph
                if (visited[i] == false) {
                    isConnected = false;
                    return false;
                }
                //Checks even degrees of all vertices
                if (g.adj.get(i).size() % 2 != 0) {
                    odd_count++;
                    index_odd_vertex = i;
                }
                //more then 2 odd vertices - No euler path
                if (odd_count > 2) {
                    isConnected = false;
                    return false;
                }
            }
            //If exist Euler path or Euler circle
            if(odd_count == 0 || odd_count == 2) isConnected = true;
        }

        return isConnected;
    }

    public String euler_path(){

        //with one vertex graph
        if(length == 1) return "0";
        //Empty graph or is disconnected
        else if(length == 0 || !isConnected) return "";

        //Is not calculated
        if(!isCalculated) has_euler_path();
        //Has already calculated
        if(this.path != null) return path;

        //----Euler Circuit-----
        if(odd_count == 0){
            // if the number of odd degree vertices is 0
            // the graph contains a Euler Circuit
            // we can use any vertex as the starting vertex
            return printEuler(0);
        }

        //----Euler Path-----
        else if(odd_count == 2){
            // if the number of odd degree vertices is 0
            // the graph contains a Euler Path
            // starting vertex should be of odd degree
            return printEuler(index_odd_vertex);
        }

        return "";
    }

    // the function to print euler path or circuit
    String printEuler(Integer v){

        Stack<Integer> cpath = new Stack<>();	// current path
        this.path = "";

        cpath.push(v);// euler path starts from v

        while(!cpath.empty()){
            int u = cpath.peek();

            if(g.adj.get(u).size() == 0){
                // if all edges from u are visited
                // pop u and push it to euler path
                path += "->" + u;
                cpath.pop();
            }
            else{
                // if all edges from u are not visited
                // select any edge (u, v)
                // push v to current path and remove edge (u, v) from the graph
                cpath.push(g.adj.get(u).iterator().next());
                g.removeEdge(u,g.adj.get(u).iterator().next());
            }
        }
        path = path.substring(2);
        return path;
    }

    //------------------Class Graph------------------//
    class Graph{

        // adjacency list
        Map<Integer, HashSet<Integer>> adj;

        // initialize adjacency map for v nodes
        public Graph(){
            adj = new HashMap<>(length);
        }

        // add edge (u, v) to graph
        void addEdge(int u, int v){
            if(!adj.containsKey(u)){
                this.adj.put(u,new HashSet<>());
            }
            this.adj.get(u).add(v);
            if(!adj.containsKey(v)){
                this.adj.put(v,new HashSet<>());
            }
            this.adj.get(v).add(u);
        }

        // remove edge (u, v) from the graph
        void removeEdge(int v,int u){
            adj.get(v).remove(u);
            adj.get(u).remove(v);
        }
    }

}
