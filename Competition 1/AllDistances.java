
//ID: 315814475
//NAME: LIOR DAVID ATIYA

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AllDistances {

    final int inf = 1000000;
    int vertices_length;
    boolean distance_matrix_calculated;

    private int[] verticesWeight; //Vertices weight from the input
    private int[][] edgesWeight; //Edges weight from the input
    int[][] distance_matrix; //Min distance from each vertex to each vertex
    boolean[][] specific_calculated;
    String[][] shortestPath_matrix; //Paths of min distance from each vertex to each vertex

    //For Dijkstra
    boolean[] visited;
    int[] parents; //Parent of each vertex

    public AllDistances(int[] vertices_weights, int[][] edges_weights) {
        this.verticesWeight = vertices_weights;
        this.edgesWeight = edges_weights;
        this.vertices_length = verticesWeight.length;

        //Init arrays
        this.distance_matrix = new int[vertices_length][vertices_length];
        this.specific_calculated = new boolean[vertices_length][vertices_length];
        this.distance_matrix_calculated = false;

        this.shortestPath_matrix = new String[vertices_length][vertices_length];
        parents = new int[vertices_length];
        visited = new boolean[vertices_length];
    }

    //O(n^3 + n^2)
    private void Floyd_Warshall() {

        //Convert to weight on edges
        for (int i = 0; i < vertices_length; i++) {
            for (int j = 0; j < vertices_length; j++) {
                if (edgesWeight[i][j] != inf) {
                    if (i == j) {
                        distance_matrix[i][j] = verticesWeight[i];
                    } else {
                        distance_matrix[i][j] = edgesWeight[i][j] + verticesWeight[i] + verticesWeight[j];
                    }
                }else{
                    distance_matrix[i][j] = inf;
                }
            }
        }

        for (int k = 0; k < vertices_length; k++) {
            for (int i = 0; i < vertices_length; i++) {
                for (int j = 0; j < vertices_length; j++) {
                    if ((distance_matrix[i][k] != inf && distance_matrix[k][j] != inf && i != j)) {
                        int distance = distance_matrix[i][k] + distance_matrix[k][j] - verticesWeight[k];
                        if(distance < distance_matrix[i][j]){
                            distance_matrix[i][j] = distance;
                        }
                    }
                }
            }
        }
        distance_matrix_calculated = true;
    }

    private void dijkstra_with_dest(int src, int dest) {
        //Init all the weight from source to infinity
        for (int i = 0; i < vertices_length; i++) {
            distance_matrix[src][i] = inf;
            parents[i] = inf;
            visited[i] = false;
        }

        //Comparator for PriorityQueue
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer v1, Integer v2) {
                return distance_matrix[src][v1] - distance_matrix[src][v2];
            }
        };

        distance_matrix[src][src] = verticesWeight[src];

        PriorityQueue<Integer> vertices_queue = new PriorityQueue<>(1, comparator);
        vertices_queue.add(src);

        int current, distance;
        while (!vertices_queue.isEmpty() && !visited[dest]) {
            current = vertices_queue.remove();
            for (int i = 0; i < vertices_length; i++) {
                if (distance_matrix[src][current] != inf) {
                    distance = distance_matrix[src][current] + edgesWeight[current][i] + verticesWeight[i];
                } else {
                    distance = verticesWeight[current] + edgesWeight[current][i] + verticesWeight[i];
                }
                //Have a neighbor && smaller distance && Not visited
                if (edgesWeight[current][i] != inf && distance < distance_matrix[src][i] && !visited[i]) {
                    distance_matrix[src][i] = distance;
                    distance_matrix[i][src] = distance;
                    specific_calculated[src][i] = true;
                    specific_calculated[i][src] = true;
                    parents[i] = current;
                    vertices_queue.add(i);
                }
            }
            visited[current] = true;
        }

    }

    //Complexity - O(N^3)
    public int[][] distance_matrix() {
        //Already calculated
        if(distance_matrix_calculated) return distance_matrix;
        Floyd_Warshall();
        return distance_matrix;
    }

    public int distance(int u, int v) {
        u--; v--;
        //Already calculated
        if (specific_calculated[u][v]) return distance_matrix[u][v];
        dijkstra_with_dest(u, v);
        return distance_matrix[u][v];
    }

    public String path(int u, int v) {
        u--; v--;
        if(u == v) return (u+1)+"";
        String shortest_path = "";
        //Already calculated
        if(shortestPath_matrix[u][v] != null) return shortestPath_matrix[u][v];
        dijkstra_with_dest(u, v);
        //No have path
        if(parents[v] == inf) return "";

        int current = v;
        while (current != u) {
            shortest_path = (current + 1) + "-" + shortest_path;
            current = parents[current];
        }
        shortest_path = (u + 1) + "-" + shortest_path.substring(0,shortest_path.length()-1);
        shortestPath_matrix[u][v] = shortest_path;

        return shortest_path;
    }

    public static void main(String[] args) {
        final int inf = 1000000;
        int[] vertices_weight = {2,4,3,6};
        int[][] edges_weight = {{0,18,5,inf},
                                {18,0,2,3},
                                {5,2,0,inf},
                                {inf,3,inf,0}};
//
        AllDistances a = new AllDistances(vertices_weight,edges_weight);
//        long startTime = System.nanoTime();
        int [][] ans = a.distance_matrix();
//        long endTime = System.nanoTime();
//        long timeElapsed = endTime - startTime;
//        System.out.println("Execution time in nanoseconds: " + timeElapsed);
//        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
        for(int i=0 ; i < vertices_weight.length; i++){
            System.out.println(Arrays.toString(ans[i]));
        }
//        System.out.println("Distance(1,4): " + a.distance(0,3));
//        System.out.println("Path(1,4): " +a.path(0,3));

        int vertices_w[] = {1, 3, 15, 2, 8, 8, 5, 6};

        int[][] edges_w = {
                {0, 5, inf, inf, inf, inf, 4, inf},
                {5, 0, inf, inf, inf, 6, 8, inf},
                {inf, inf, 0, 6, inf, inf, 2, 5},
                {inf, inf, 6, 0, 5, inf, 18, inf},
                {inf, inf, inf, 5, 0, 4, 3, inf},
                {inf, 6, inf, inf, 4, 0, inf, inf},
                {4, 8, 2, 18, 3, inf, 0, 7},
                {inf, inf, 5, inf, inf, inf, 7, 0}
        };
        AllDistances ad = new AllDistances(vertices_w,edges_w);
        // result:
        /*
        [1, 9, 27, 28, 21, 23, 10, 23]
        [9, 3, 33, 34, 27, 17, 16, 29]
        [27, 33, 15, 23, 33, 45, 22, 26]
        [28, 34, 23, 2, 15, 27, 23, 34]
        [21, 27, 33, 15, 8, 20, 16, 29]
        [23, 17, 45, 27, 20, 8, 28, 41]
        [10, 16, 22, 23, 16, 28, 5, 18]
        [23, 29, 26, 34, 29, 41, 18, 6]
        */

//        for(int i=0 ; i < ad.vertices_length; i++){
//            System.out.println(Arrays.toString(ad.distance_matrix()[i]));
//        }

//        for(int i=0 ; i < ad.vertices_length; i++){
//            for(int j=0 ; j < ad.vertices_length; j++){
//                System.out.print(ad.distance(i+1,j+1) +", ");
//            }
//            System.out.println();
//        }
//        System.out.println(ad.distance(1,1));
//        System.out.println(ad.distance(1,4));
//        System.out.println(ad.distance(4,1));



//        //------------------------------Simulation--------------------------------
        //---------------distance()-----------------------------
        long startTime = System.nanoTime();
        int x = 0;
        for(int i=0 ; i < 10000000 ; i++){
            x = ad.distance(1,4);
        }
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("distance(1,4): Dijkstra - Execution time in milliseconds:  "+ timeElapsed / 1000000);

        ////---------------distance_matrix()-----------------------------
        startTime = System.nanoTime();
        //Floyd Warshall
        for(int i=0 ; i < 10000000 ; i++){
            ad.distance_matrix();
        }
        endTime = System.nanoTime();
        timeElapsed = endTime - startTime;

        System.out.println("distance_matrix(): Floyd Warshall - Execution time in milliseconds: " + timeElapsed / 1000000);

        for(int i=0 ; i < ad.vertices_length ; i++){
            System.out.println(Arrays.toString(ad.distance_matrix[i]));
        }
        //---------------path()-----------------------------
        System.out.println("ad.path(1,1): " + ad.path(4,1));
        System.out.println("ad.path(1,1): " + ad.path(4,1));

        System.out.println(ad.path(1, 4)); // 1-7-5-4
        System.out.println(ad.path(2, 5)); // 2-7-5
        System.out.println(ad.path(5, 4)); // 5-4
        System.out.println(ad.path(1, 5)); // 1-7-5
        System.out.println(ad.path(6, 2)); // 6-2
        System.out.println(ad.path(5, 8)); // 5-7-8
        System.out.println(ad.path(4, 8)); // 4-3-8

        ////////////////////////////////////////////////////////////////
        int[] vertices_weight2 = {1,0,1,0,1,1};
        int[][] edges_weight2 = {{1,inf,1,inf,inf,inf},
        {inf,0,inf,3,10,inf},
        {1,inf,1,inf,inf,1},
        {inf,inf,inf,0,inf,inf},
        {10,inf,inf,inf,1,1},
        {inf,inf,1,inf,1,1}};

        AllDistances ad2 = new AllDistances(vertices_weight2,edges_weight2);
        System.out.println(ad2.distance(1,4));

        }
}