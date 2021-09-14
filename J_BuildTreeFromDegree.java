package Algorithms2;

import java.util.Arrays;

public class J_BuildTreeFromDegree {
    public static int[] buildTreeFromDegree(int[] degree){
        int sum = 0;
        int[] tree = new int[degree.length];
        for(int i=0 ; i < degree.length ; i++){
            sum += degree[i];
        }
        if((sum/2 + 1) != degree.length) return null;
        int j = 0;
        Arrays.sort(degree);

        for(int i=0 ; i < degree.length ; i++){
            if(degree[i] > 1) {
                j = i;
                break;
            }
        }
        for(int i=0 ; i < degree.length-2 ; i++){
            tree[i] = j;
            degree[j]--;
            if(degree[j] == 1) j++;
        }
        tree[degree.length-2] = degree.length-1;

        return tree;
    }

    public static void main(String[] args){
        int[] degrees = {1,1,1,1,2,2,3,3};
        System.out.println("Tree - (present by parents array: "+Arrays.toString(buildTreeFromDegree(degrees)));
    }
}
