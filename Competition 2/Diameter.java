
//ID: 315814475

import java.util.*;

public class Diameter {

    boolean neighbor_matrix[][];
    int length_matrix;
    boolean calculated;
    int diameter;

    //Constructor
    public Diameter(boolean adj_matrix[][]){
        this.neighbor_matrix = adj_matrix;
        this.length_matrix = adj_matrix.length;
        this.calculated = false;
        this.diameter = 0;
    }

    private int get_diameter(){
        //Auxiliary array for contain the degrees of each vertex
        int[] vertex_degree = new int[this.length_matrix];
        //Auxiliary queue to contain all the leaves
        Queue<Integer> leaves = new LinkedList<Integer>();

        //O((n^2)/2)
        for(int i=0 ; i < this.length_matrix ; i++){
            for(int j=i ; j < this.length_matrix ; j++){
                if(this.neighbor_matrix[i][j] == true){
                    vertex_degree[i]++;
                    vertex_degree[j]++;
                }
            }
            if(vertex_degree[i] == 1){
                leaves.add(i);
            }
        }

        int radius = 0;
        int counter_of_leaves;
        //O(n*(2n))
        while (leaves.size() > 1){

            //Checks of case odd diameter
            if(leaves.size() == 2) {
                int counter = 0;
                //O(n)
                for (int i = 0; i < length_matrix; i++) {
                    if (vertex_degree[i] > 0) {
                        counter++;
                    }
                    if(counter == 3) break;
                }
                if (counter == 2) return (radius * 2) + 1;
            }

            counter_of_leaves = leaves.size();

            //O(1)
            for(int j = 0 ; j < counter_of_leaves ; j++){
                int vertex = leaves.remove();
                //O(n)
                for(int i=0 ; i < this.length_matrix ; i++){
                    if(this.neighbor_matrix[vertex][i] == true && vertex_degree[i] != 0){
                        vertex_degree[vertex]--;
                        vertex_degree[i]--;
                        if(vertex_degree[i] == 1){
                            leaves.add(i);
                        }
                    }
                }
            }
            radius++;
        }

        calculated = true;
        this.diameter = radius*2;

        return diameter;
    }

    private HashMap<Integer, HashSet<Integer>> build_tree(){
        HashMap<Integer, HashSet<Integer>> tree = new HashMap<>();
        for(int i=0 ; i < this.length_matrix ; i++){
            tree.put(i,new HashSet<>());
            for(int j=0 ; j < this.length_matrix ; j++){
                if(this.neighbor_matrix[i][j] == true){
                    tree.get(i).add(j);
                    if(!tree.containsKey(j)) {
                        tree.put(j, new HashSet<>());
                    }
                    tree.get(j).add(i);
                }
            }
        }
        return tree;
    }

    public int fire(){

        HashMap<Integer, HashSet<Integer>> tree = new HashMap<>();
        Queue<Integer> leaves = new LinkedList<Integer>();
        int[] degrees = new int[length_matrix];
        // initialization, n - number of vertices
        for (int i = 0; i<length_matrix; i++){
            tree.put(i,new HashSet<>());
            for(int j=0 ; j < this.length_matrix ; j++){
                if(this.neighbor_matrix[i][j] == true){
                    tree.get(i).add(j);
                    if(!tree.containsKey(j)) {
                        tree.put(j, new HashSet<>());
                    }
                    tree.get(j).add(i);
                }
            }

            degrees[i] = tree.get(i).size();
            if (degrees[i] == 1){
                leaves.add(i);
            }
        }

        // calculations: Radius and Diameter
        int leaf;
        int radius = 0;
        int counter_of_leaves;
        while (leaves.size() > 1){

            //Checks of case odd diameter
            if(leaves.size() == 2) {
                int counter = 0;
                //O(n)
                for (int i = 0; i < length_matrix; i++) {
                    if (degrees[i] > 0) {
                        counter++;
                    }
                    if(counter == 3) break;
                }
                if (counter == 2) return (radius * 2) + 1;
            }

            counter_of_leaves = leaves.size();
            for (int i=0; i < counter_of_leaves; i++){
                leaf = leaves.poll();
                degrees[leaf] = 0;
                for(Integer dest : tree.get(leaf)){
                    if(degrees[dest] > 0){
                        degrees[dest]--;
                    }
                    if(degrees[dest] == 1) leaves.add(dest);
                }
                tree.remove(leaf);
            }
            radius++;
        }

        diameter = radius*2;
        calculated = true;

        return diameter;
    }

    //Calculate the diameter of the graph
    //Complexity - O((n^2)/2 + 2n^2 + n) = O(n^2)
    public int get_diam(){
        if(calculated){
            return this.diameter;
        }

        return get_diameter();

    }

    public int get_diam2(){
        if(calculated){
            return this.diameter;
        }
        return fire();
    }
}
