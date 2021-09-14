package Algorithms2;

import java.util.Arrays;

public class A_1_BottlesProblem {

    //calculation for convert from matrix to array
    private static int indexRow(int i, int j, int n){
        return (n + 1) * i + j;
    }

    public static int[][] optionsOfOneAction(int m,int n){
        int dim = (m+1)*(n+1);
        int matrix[][] = new int[dim][dim];

        for(int i=0 ; i <= m; i++){
            for(int j=0 ; j <= n; j++){
                int index = indexRow(i,j,n);
                matrix[index][indexRow(m,j,n)] = 1; //Fill bottle A
                matrix[index][indexRow(i,n,n)] = 1; //Fill bottle B
                matrix[index][indexRow(0,j,n)] = 1; //Empty bottle A
                matrix[index][indexRow(i,0,n)] = 1; //Empty bottle B

                //Move from m to n (n is the bigger)
                int indexTo = indexRow(Math.max(0,i+j-n), Math.min(i+j,n),n);
                matrix[index][indexTo] = 1;

                //Move from n to m
                indexTo = indexRow(Math.min(m,i+j), Math.max(0,j+i-m),n);
                matrix[index][indexTo] = 1;
            }
        }
//        //Print the matrix of the all the options to fill or empty.
//        for(int i=0 ; i < dim ; i++){
//            System.out.println(Arrays.toString(matrix[i]));
//        }
        return matrix;
    }

    public static void main(String[] args){

        int matrix[][] = optionsOfOneAction(1,2);
        //Print the matrix of the all the options to fill or empty.
        for(int i=0 ; i < matrix.length ; i++){
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

}
