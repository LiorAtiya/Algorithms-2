/**
 * Algorithms #2
 * Bonus assignment #2
 * Goal: find the diameter in a tree
 * ID: ***
 **/

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Diameter2 {

    //==================================================Variables=======================================================

    private boolean[][] adj_matrix;
    private int length; // a variable that represents the number of vertices in the graph(|V|)
    private int[] arr_degree;
    private Queue<Integer> queue;
    private int iterations; // burning rounds
    private boolean even = false; // true = there are two centers in the tree
    private int[] arr_level;
    private int max_level;

    //=================================================Constructor======================================================

    public Diameter2(boolean[][] adj_matrix) {
        this.adj_matrix = adj_matrix;
        this.length = adj_matrix.length;
        this.queue = new LinkedList<>();
        this.even = false;
        this.arr_degree = new int[length];
        this.iterations = 0;

        this.arr_level = new int[length];
        this.max_level = arr_level[0];
    }

    //================================================Main-Functions====================================================

    public int get_diam() {
        int ans;
        // checks edge case
        // tree with only one vertex
        if (length == 1) return 0;
        // creating an array that contains the degree of all the vertices on in the tree
        create_v_degrees_arr(adj_matrix);
        // removing all the leaves in the tree and adding them to the queue
        burn_leaves();
        int count = 0;
        boolean flag = true;
        // keep running till only 1 leaf left or number of leaves that remain is less than three
        while (flag && queue.size() > 1) {
            int queueSize = queue.size();
            for (int j = 0; j < queueSize; j++) {
                int current_leaf = queue.poll();
                count++; // count every leaf that we burned
                // stop burning if the number of leaves that remain is less than three
                if (length - count < 3 && length > 2){
                    flag = false;
                }
                // decrease the degree of the current leaf
                arr_degree[current_leaf]--;
                // decrease the degree of the leaf's neighbours
                decrease_neighbors_degree(current_leaf);
            }
            // increments the iterations sum by one
            iterations++;
        }
        // calculates the diameter of the tree
        ans = calculate_diameter();
//        System.out.println(Arrays.toString(this.arr_level));
        return ans;
    }


    //===============================================Utility-Functions==================================================

    private void create_v_degrees_arr(boolean[][] adj_matrix) {
        for (int i = 0; i < length; i++) {
            for (int j = 0 ; j < length; j++) {
                if (adj_matrix[i][j] == true) {
                    arr_degree[i]++;
//                    arr_degree[j]++;
                }
            }
        }
    }

    private void burn_leaves() {
        boolean is_burned = false;
        for (int i = 0; i < length; i++) {
            if (arr_degree[i] == 1) {
                queue.add(i);
                is_burned = true;
            }
        }
    }

    private void decrease_neighbors_degree(int current_leaf) {
        // checks which vertices are connected to the current leave
        for (int i = 0; i < length; i++) {
            if (adj_matrix[current_leaf][i] == true) {
                // if the degree of the neighbour is positive, then decrease by one
                if (arr_degree[i] > 0) {
                    arr_degree[i]--;
                    /* if the level of the current leave equals or greater than the level of its
                    neighbour, then increment the level of its neighbour by one */
                    if (arr_level[current_leaf] >= arr_level[i]) {
                        arr_level[i] = arr_level[current_leaf] + 1;
                        // updates the max level
                        if (arr_level[i] > max_level) {
                            max_level = arr_level[i];
                        }
                    }
                    // if the new degree is equals to 1, then add the vertex to the queue
                    if (arr_degree[i] == 1) {
                        queue.add(i);
                    }
                }
            }
        }
    }

    private int calculate_diameter() {
        int ans;

        if(length == 2){
            return iterations;
        }

        is_even();
        // if there is only one center
        if (!even) {
            ans = max_level * 2;
        }
        // there are two centers
        else {
            ans = max_level * 2 + 1;
        }
        return ans;
    }

    private void is_even() {
        boolean ans = false;
        int count = 0;
        // count how may vertices have max degree
        for (int i = 0; i < length; i++) {
            if (arr_level[i] == max_level) {
                count++;
            }
        }
        // update the number of centers to even(true)
        if (count == 2) {
            this.even = true;
        }
    }
}