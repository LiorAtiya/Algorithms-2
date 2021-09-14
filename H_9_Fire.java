package Algorithms2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class H_9_Fire {

        public static int fire(ArrayList<Integer>[] tree){
            int n = tree.length;
            Queue<Integer> leaves = new LinkedList();
            int radius = 0, diameter = 0, numCenters = 0;
            int[] degrees = new int[n];
            // initialization, n - number of vertices
            for (int i = 0; i<n; i++){
                degrees[i] = tree[i].size();
                if (degrees[i] == 1){
                    leaves.add(i);
                }
            }
            // calculations: find Centers, Radius and Diameter
            int vertex = 0, leaf = 0;
            while (n > 2){

                for (int i=0; i <= leaves.size(); i++) {
                    leaf = leaves.remove();
                    degrees[leaf] = 0;
                    for (int j = 0; j < tree[leaf].size() ; j++) {
                        vertex = tree[leaf].get(j);
                        if (degrees[vertex] > 0) {
                            degrees[vertex]--;
                            if (degrees[vertex] == 1) leaves.add(vertex);
                        }
                    }
                    n--;
                }
                radius++;
            }
            if (leaves.size()==2){
                radius++;
                diameter = radius*2 - 1;
            }
            else diameter = radius*2;
            numCenters = leaves.size();
            System.out.println("radius = " + radius + ", diameter = " + diameter + ", centers: " + leaves.toString()
                    +", numCenters = "+numCenters);
            return diameter;
        }

        public static void main(String[] args) {
            ArrayList<Integer>[] tree = new ArrayList[9];
            tree[0] = new ArrayList<>();
            tree[1] = new ArrayList<>();
            tree[2] = new ArrayList<>();
            tree[3] = new ArrayList<>();
            tree[4] = new ArrayList<>();
            tree[5] = new ArrayList<>();
            tree[6] = new ArrayList<>();
            tree[7] = new ArrayList<>();
            tree[8] = new ArrayList<>();

            tree[0].add(1);
            tree[1].add(0); tree[1].add(2); tree[1].add(3);
            tree[2].add(1);
            tree[3].add(1); tree[3].add(4); tree[3].add(5); tree[3].add(6);
            tree[4].add(3);
            tree[5].add(3);
            tree[6].add(3); tree[6].add(7);
            tree[7].add(6); tree[7].add(8);
            tree[8].add(7);

            fire(tree);
        }

}
