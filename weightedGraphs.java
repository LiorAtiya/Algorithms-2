package Algorithms2;

import java.util.Arrays;

public class weightedGraphs {
    //O(n^3) | Floyd Warshall algorithm
    public static void pathsMatrix(int[][] neighborMatrix) {
        int[][] pathMatrix = neighborMatrix;
        for (int k = 0; k < pathMatrix.length; k++) {
            for (int i = 0; i < pathMatrix.length; i++) {
                for (int j = 0; j < pathMatrix.length; j++) {
                    if ((pathMatrix[i][k] != Integer.MAX_VALUE && pathMatrix[k][j] != Integer.MAX_VALUE)) {
                        pathMatrix[i][j] = Math.min((pathMatrix[i][k] + pathMatrix[k][j]), pathMatrix[i][j]);
                    }
                }
            }
        }
    }

    //O(N^2) + O(N^3)
    public static String[][] stringPathsMatrix(int[][] neighborMatrix) {
        String[][] pathMatrix = new String[neighborMatrix.length][neighborMatrix.length];

//      Fill the direct string path between 2 vertices. - O(N^2)
        for (int i = 0; i < pathMatrix.length; i++) {
            for (int j = 0; j < pathMatrix.length; j++) {
                if (neighborMatrix[i][j] != Integer.MAX_VALUE) {
                    pathMatrix[i][j] = (i + 1) + "->" + (j + 1);
                }
            }
        }

        //Fill the indirect string path between 2 vertices. - O(N^3)
        for (int k = 0; k < pathMatrix.length; k++) {
            for (int i = 0; i < pathMatrix.length; i++) {
                for (int j = 0; j < pathMatrix.length; j++) {
                    if (neighborMatrix[i][k] != Integer.MAX_VALUE && neighborMatrix[k][j] != Integer.MAX_VALUE) {
                        int min = Math.min(neighborMatrix[i][k] + neighborMatrix[k][j], neighborMatrix[i][j]);
                        if (neighborMatrix[i][k] + neighborMatrix[k][j] < neighborMatrix[i][j]) {
                            neighborMatrix[i][j] = min;
                            pathMatrix[i][j] = pathMatrix[i][k] + "->" + pathMatrix[k][j].substring(3);
                        }
                    }
                }
            }
        }

        return pathMatrix;
    }


    private static void convert2weightOnEdges(int[] vertices, int[][] neighborMatrix) {
        for (int i = 0; i < neighborMatrix.length; i++) {
            for (int j = 0; j < neighborMatrix.length; j++) {
                if (neighborMatrix[i][j] == 1) {
                    neighborMatrix[i][j] = vertices[i] + vertices[j];
                }
            }
        }
    }

    private static void fixPathsMatrix(int[] vertices, int[][] neighborMatrix) {
        for (int i = 0; i < neighborMatrix.length; i++) {
            for (int j = 0; j < neighborMatrix.length; j++) {
                if (i != j) {
                    neighborMatrix[i][j] = (neighborMatrix[i][j] + vertices[i] + vertices[j]) / 2;
                }
            }
        }
    }

    public static void pathsMatrix_WeightOnVertices(int[] vertices, int[][] neighborMatrix) {
        convert2weightOnEdges(vertices, neighborMatrix);
        pathsMatrix(neighborMatrix);
        fixPathsMatrix(vertices, neighborMatrix);
    }

    public static boolean negativeCycle(int[][] matrix){
        for(int i=0 ; i < matrix.length ; i++){
            if(matrix[i][i] < 0) return true;
        }
        return false;
    }

    public static void main(String[] args) {
////        Matrix with weight on the edges
//        int mat[][] = {{0, 4, Integer.MAX_VALUE, 3},
//                {4, 0, 15, Integer.MAX_VALUE},
//                {Integer.MAX_VALUE, 15, 0, 1},
//                {3, Integer.MAX_VALUE, 1, 0}};
//        pathsMatrix(mat);
//        for (int i = 0; i < mat.length; i++) {
//            System.out.printf(Arrays.toString(mat[i]) + '\n');
//        }
//            String matPath[][] = stringPathsMatrix(mat);
//            for (int i = 0; i < matPath.length; i++) {
//                System.out.printf(Arrays.toString(matPath[i]) + '\n');
//            }
//
//        //Matrix with weight on the vertices
//        int mat2[][] = {{0, 1, Integer.MAX_VALUE, 1},
//                {1, 0, 1, Integer.MAX_VALUE},
//                {Integer.MAX_VALUE, 1, 0, 1},
//                {1, Integer.MAX_VALUE, 1, 0}};
//
//        int vertices[] = {4, 3, 2, 5};
//        pathsMatrix_WeightOnVertices(vertices, mat2);
//        for (int i = 0; i < mat2.length; i++) {
//            System.out.printf(Arrays.toString(mat2[i]) + '\n');
//        }

        int mat3[][] = {{-4,4,2},
                        {-8,-4,2},
                        {-10,-6,-4}};
        System.out.println(negativeCycle(mat3));

        //         4
        //    V0---→--V1
        //      |     |
        //   3  ↑     ↓ -9
        //	    |__←__|
        //     V3  1  V2
        //
        int[][] arr = {{0,4,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,0,-9,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,0,1},
                {3,Integer.MAX_VALUE,Integer.MAX_VALUE,0}};

        pathsMatrix(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.printf(Arrays.toString(arr[i]) + '\n');
        }

    }
}
