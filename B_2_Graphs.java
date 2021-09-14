package Algorithms2;

import java.util.Arrays;
import java.util.HashMap;

public class B_2_Graphs {
    //O(n^3) | Floyd Warshall algorithm
    public static void pathsMatrix(int[][] neighborMatrix) {
        int[][] pathMatrix = neighborMatrix;
        for (int k = 0; k < pathMatrix.length; k++) {
            for (int i = 0; i < pathMatrix.length; i++) {
                for (int j = 0; j < pathMatrix.length; j++) {
                    if ((pathMatrix[i][k] == 1 && pathMatrix[k][j] == 1)) {
                        pathMatrix[i][j] = 1;
                    }
                }
            }
        }
    }

    //Checks if have path between 2 vertices | O(1)
    public static boolean havePath(int[][] pathsMatrix, int src, int dest) {
        if (pathsMatrix[src][dest] == 1) return true;
        return false;
    }

    public static boolean isConnected(int[][] pathsMatrix) {
        for (int i = 0; i < pathsMatrix.length; i++) {
            if (pathsMatrix[0][i] == 0) return false;
        }
        return true;
    }

    //O(n^2)
    public static int numOfConnectedComponent(int[][] pathsMatrix) {
        int counter = 0;
        int[] checkComponents = new int[pathsMatrix.length];
        for (int i = 0; i < checkComponents.length; i++) {
            if (checkComponents[i] == 0) {
                counter++;
                for (int j = 0; j < pathsMatrix.length; j++) {
                    if (pathsMatrix[i][j] == 1)
                        checkComponents[j] = counter;
                }
            }
        }

        HashMap<Integer, String> components = new HashMap<>();
        for (int i = 0; i < checkComponents.length; i++) {
            if (components.get(checkComponents[i]) == null) components.put(checkComponents[i], "");
            components.put(checkComponents[i], components.get(checkComponents[i]) + ", " + (i + 1));
        }
        System.out.println("Connected Components: ");
        for (Integer comp : components.keySet()) {
            System.out.println(comp + ": " + components.get(comp));
        }
        return counter;
    }

    //O(N^2) + O(N^3)
    public static String[][] stringPathsMatrix(int[][] neighborMatrix) {
        String[][] pathMatrix = new String[neighborMatrix.length][neighborMatrix.length];

        //Fill the direct string path between 2 vertices. - O(N^2)
        for (int i = 0; i < pathMatrix.length; i++) {
            pathMatrix[i][i] = "" + i;
            for (int j = 0; j < pathMatrix.length; j++) {
                if (neighborMatrix[i][j] == 1 && i != j) {
                    pathMatrix[i][j] = i + "->" + j;
                } else if (i != j) {
                    pathMatrix[i][j] = "";
                }
            }
        }

        //Fill the indirect string path between 2 vertices. - O(N^3)
        for (int k = 0; k < pathMatrix.length; k++) {
            for (int i = 0; i < pathMatrix.length; i++) {
                for (int j = 0; j < pathMatrix.length; j++) {
                    if ((i != j) && (neighborMatrix[i][k] == 1 && neighborMatrix[k][j] == 1) && neighborMatrix[i][j] != 1) {
                        pathMatrix[i][j] += pathMatrix[i][k] + "->" + pathMatrix[k][j].substring(3);
                        neighborMatrix[i][j] = 1;
                    }
                }
            }
        }

        return pathMatrix;
    }

    public static String BottlesPaths(int srcX, int srcY, int desX, int desY, String[][] pathMatrix, int bigBottle) {
        int kSrc = (bigBottle + 1) * srcX + srcY;
        int kDes = (bigBottle + 1) * desX + desY;

        if (pathMatrix[kSrc][kDes] == "") return "No path";

        String bottlePath = "";
        String verticesPath = pathMatrix[kSrc][kDes];
        for (int i = 0; i < verticesPath.length(); i++) {
            if (verticesPath.charAt(i) != '-' && verticesPath.charAt(i) != '>') {
                String number = "";
                while (i < verticesPath.length() && verticesPath.charAt(i) != '-') {
                    number += verticesPath.charAt(i);
                    i++;
                }
                int leftBottle = Integer.parseInt(number + "") / (bigBottle + 1);
                int rightBottle = Integer.parseInt(number + "") % (bigBottle + 1);
                bottlePath += "[" + leftBottle + "," + rightBottle + "] => ";
            }
        }

        bottlePath = bottlePath.substring(0, bottlePath.length() - 3);
        return bottlePath;
    }

    public static void main(String[] args) {
        //Before Algorithm Floyd-Warshall
        int[][] matNeibhoor = {{0, 1, 1, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 1, 0}};
//
        String[][] matStringPaths = stringPathsMatrix(matNeibhoor);
        for (int i = 0; i < matStringPaths.length; i++) {
            System.out.println(Arrays.toString(matStringPaths[i]));
        }
//
        System.out.println("\n-----------------------------------------------\n");
////
//              0---1
//             /     \
//            5       2
//             \     /
//              4---3
        int[][] matPaths3 = {{1,1,0,0,0,1},
                             {1,1,1,0,0,0},
                             {0,1,1,1,0,0},
                             {0,0,1,1,1,0},
                             {0,0,0,1,1,1},
                             {1,0,0,0,1,1}};

        matStringPaths = stringPathsMatrix(matPaths3);
        for(int i=0 ; i < matStringPaths.length ; i++){
            System.out.println(Arrays.toString(matStringPaths[i]));
        }
//
        System.out.println("\n-----------------------------------------------\n");

        //After Algorithm Floyd-Warshall
        pathsMatrix(matNeibhoor);

        //After Algorithm Floyd-Warshall
        int[][] matPaths = {{1,0,0,1,0,1,0},
                            {0,1,1,0,1,0,1},
                            {0,1,1,0,1,0,1},
                            {1,0,0,1,0,1,0},
                            {0,1,1,0,1,0,1},
                            {1,0,0,1,0,1,0},
                            {0,1,1,0,1,0,1}};

        //After Algorithm Floyd-Warshall
        int[][] matPaths2 = {{1,0,0},
                            {0,1,0},
                            {0,0,1}};

        System.out.println(numOfConnectedComponent(matNeibhoor));

        //Print matrix - have path
        for (int i = 0; i < matNeibhoor.length; i++) {
            System.out.println(Arrays.toString(matNeibhoor[i]));
        }

        int matrix[][] = A_1_BottlesProblem.optionsOfOneAction(1,4);
//        System.out.println("\n----Neighbor matrix-----\n");
//        for(int i=0 ; i < matrix.length ; i++){
//            System.out.println(Arrays.toString(matrix[i]));
//        }
//
//        System.out.println("\n----String path matrix-----\n");
        matStringPaths = stringPathsMatrix(matrix);
//        for(int i=0 ; i < matStringPaths.length ; i++){
//            System.out.println(Arrays.toString(matStringPaths[i]));
//        }

        System.out.println(BottlesPaths(1,3,0,2,matStringPaths,4));

    }
}
