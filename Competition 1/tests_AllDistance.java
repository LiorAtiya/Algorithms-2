////import java.util.PriorityQueue;
////
////public class tests_AllDistance {
//
////    int weightFromSrc[] = new int[vertices_length];
////            int weightFromDest[] = new int[vertices_length];
////
////            //Comparator for PriorityQueue
////            Comparator<Integer> comparator1 = new Comparator<Integer>() {
////@Override
////public int compare(Integer v1, Integer v2) {
////        return weight_from_src[v1] - weight_from_src[v2];
////        }
////        };
////
////        //Comparator for PriorityQueue
////        Comparator<Integer> comparator2 = new Comparator<Integer>() {
////@Override
////public int compare(Integer v1, Integer v2) {
////        return weight_from_src[v1] - weight_from_src[v2];
////        }
////        };
//////
////    private void BiDijkstra(int s, int t){
////
////        for(int i=0 ; i < vertices_length; i++){
////            weightFromSrc[i] = inf;
////            weightFromDest[i] = inf;
////        }
////
////        boolean[] visited_s  = new boolean[vertices_length];
////        boolean[] visited_t  = new boolean[vertices_length];
////        weightFromSrc[src] = verticesWeight[src];
////        PriorityQueue<Integer> vertices_s = new PriorityQueue<>(1, comparator1);
////        vertices_s.add(s);
////
////        PriorityQueue<Integer> vertices_t = new PriorityQueue<>(1, comparator2);
////        vertices_t.add(t);
////        boolean inProcess = true;
////
////        int distance_s,distance_t;
////        while(inProcess){
////            int current_s = vertices_s.remove();
////            int current_t = vertices_t.remove();
////
////            for (int i = 0; i < vertices_length; i++) {
////                if (weightFromSrc[current_s] != inf) {
////                    distance_s = weightFromSrc[current_s] + edgesWeight[current_s][i] + verticesWeight[i];
////                } else {
////                    distance_s = verticesWeight[current_s] + edgesWeight[current_s][i] + verticesWeight[i];
////                }
////
////                if (weightFromDest[current_t] != inf) {
////                    distance_t = weightFromDest[current_t] + edgesWeight[current_t][i] + verticesWeight[i];
////                } else {
////                    distance_t = verticesWeight[current_t] + edgesWeight[current_t][i] + verticesWeight[i];
////                }
////
////                //Have a neighbor && smaller distance && Not visited
////                if (edgesWeight[current_s][i] != inf && distance_s < weightFromSrc[i] && !visited_s[i]) {
////                    weightFromSrc[i] = distance_s;
////                    distance_matrix[s][i] = distance_s;
////                    distance_matrix[i][s] = distance_s;
////                    parents[i] = current_s;
////                    vertices_s.add(i);
////                }
////
////                //Have a neighbor && smaller distance && Not visited
////                if (edgesWeight[current_t][i] != inf && distance_t < weightFromDest[i] && !visited_t[i]) {
////                    weightFromDest[i] = distance_t;
//////                    distance_matrix[s][i] = distance_s;
//////                    distance_matrix[i][s] = distance_s;
//////                    parents[i] = current_s;
////                    vertices_t.add(i);
////                }
////            }
////            visited_s[current_s] = true;
////            visited_t[current_t] = true;
////
////            if(visited_s[current_s] && visited_t[current_s] || visited_t[current_t] && visited_s[current_t]) inProcess = false;
////        }
////
//////        int min = weightFromSrc[0] + weightFromDest[0];
////        int min = weightFromSrc[0] + weightFromDest[0];
////        for(int i=1 ; i < vertices_length ; i++){
////            if(weightFromSrc[i] + weightFromDest[i] < min) min = weightFromSrc[i] + weightFromDest[i];
////        }
////
////        while(inProcess)
////        {
////
//////            if (inProcess == true && ((v_s = ExtractMin(dist_s, visited_s)) != -1))
//////            {
////////                for (int u : G.get(v_s)) {
//////                for(int i=0 ; i < vertices_length; i++){
//////                    if (visited_s[u] == false && (dist_s[u] > dist_s[v_s] + weightMatrix[v_s][u]))
//////                    {
//////                        dist_s[u] = dist_s[v_s] + weightMatrix[v_s][u];
//////                        prev_s[u] = v_s;
//////                    }
//////                }
//////                visited_s[v_s] = true;
//////                if (visited_s[v_s] == true &&  visited_t[v_s] == true)
//////                {
//////                    inProcess = false;
//////                }
//////            }
//////            if (inProcess == true && ((v_t = ExtractMin(dist_t, visited_t)) != -1))
//////            {
//////                for (int u : G.get(v_t)) {
//////                    if (visited_t[u] == false && (dist_t[u] > dist_t[v_t] + weightMatrix[v_t][u]))
//////                    {
//////                        dist_t[u] = dist_t[v_t] + weightMatrix[v_t][u];
//////                        prev_t[u] = v_t;
//////                    }
//////                }
//////                visited_t[v_t] = true;
//////                if (visited_s[v_t] == true &&  visited_t[v_t] == true)
//////                {
//////                    inProcess = false;
//////                }
//////            }
////        }
////
////        int min = inf;
////        int minIndex = -1;
////        for (int i = 0; i < vertices_length; i++) {
////            if ((visited_s[i] || visited_t[i]) && dist_s[i] != inf && dist_t[i] != inf)
////            {
////                if (min > dist_s[i] + dist_t[i])
////                {
////                    min = dist_s[i] + dist_t[i];
////                    minIndex = i;
////                }
////            }
////        }
////    }
////
////    private int ExtractMin(int[] dist, boolean[] visited) {
////        int minIndex = inf, MinValue = inf;
////        int V = visited.length;
////        for (int i = 0; i < V; i++) {
////            if (visited[i] == false && dist[i] < MinValue)
////            {
////                minIndex = i;
////                MinValue = dist[i];
////            }
////        }
////        return minIndex;
////    }
////}
//
////
////
////public static void main(String[] args) {
////final int inf = 1000000;
////        int[] vertices_weight = {2,4,3,6};
////        int[][] edges_weight = {{0,18,5,inf},
////        {18,0,2,3},
////        {5,2,0,inf},
////        {inf,3,inf,0}};
//////
////        AllDistances a = new AllDistances(vertices_weight,edges_weight);
//////        long startTime = System.nanoTime();
////        int [][] ans = a.distance_matrix();
//////        long endTime = System.nanoTime();
//////        long timeElapsed = endTime - startTime;
//////        System.out.println("Execution time in nanoseconds: " + timeElapsed);
//////        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
////        for(int i=0 ; i < vertices_weight.length; i++){
////        System.out.println(Arrays.toString(ans[i]));
////        }
//////        System.out.println("Distance(1,4): " + a.distance(0,3));
//////        System.out.println("Path(1,4): " +a.path(0,3));
////
////        int vertices_w[] = {1, 3, 15, 2, 8, 8, 5, 6};
////
////        int[][] edges_w = {
////        {0, 5, inf, inf, inf, inf, 4, inf},
////        {5, 0, inf, inf, inf, 6, 8, inf},
////        {inf, inf, 0, 6, inf, inf, 2, 5},
////        {inf, inf, 6, 0, 5, inf, 18, inf},
////        {inf, inf, inf, 5, 0, 4, 3, inf},
////        {inf, 6, inf, inf, 4, 0, inf, inf},
////        {4, 8, 2, 18, 3, inf, 0, 7},
////        {inf, inf, 5, inf, inf, inf, 7, 0}
////        };
////        AllDistances ad = new AllDistances(vertices_w,edges_w);
//        // result:
//        /*
//        [1, 9, 27, 28, 21, 23, 10, 23]
//        [9, 3, 33, 34, 27, 17, 16, 29]
//        [27, 33, 15, 23, 33, 45, 22, 26]
//        [28, 34, 23, 2, 15, 27, 23, 34]
//        [21, 27, 33, 15, 8, 20, 16, 29]
//        [23, 17, 45, 27, 20, 8, 28, 41]
//        [10, 16, 22, 23, 16, 28, 5, 18]
//        [23, 29, 26, 34, 29, 41, 18, 6]
//        */
//
////        for(int i=0 ; i < ad.vertices_length; i++){
////            System.out.println(Arrays.toString(ad.distance_matrix()[i]));
////        }
//
////        for(int i=0 ; i < ad.vertices_length; i++){
////            for(int j=0 ; j < ad.vertices_length; j++){
////                System.out.print(ad.distance(i+1,j+1) +", ");
////            }
////            System.out.println();
////        }
////        System.out.println(ad.distance(1,1));
////        System.out.println(ad.distance(1,4));
////        System.out.println(ad.distance(4,1));
//
////
////
//////        //------------------------------Simulation--------------------------------
////        //---------------distance()-----------------------------
////        long startTime = System.nanoTime();
////        int x = 0;
////        for(int i=0 ; i < 10000000 ; i++){
////        x = ad.distance(1,4);
////        }
////        long endTime = System.nanoTime();
////        long timeElapsed = endTime - startTime;
////        System.out.println("distance(1,4): Dijkstra - Execution time in milliseconds:  "+ timeElapsed / 1000000);
////
////        ////---------------distance_matrix()-----------------------------
////        startTime = System.nanoTime();
////        //Floyd Warshall
////        for(int i=0 ; i < 10000000 ; i++){
////        ad.distance_matrix();
////        }
////        endTime = System.nanoTime();
////        timeElapsed = endTime - startTime;
////
////        System.out.println("distance_matrix(): Floyd Warshall - Execution time in milliseconds: " + timeElapsed / 1000000);
////
////        for(int i=0 ; i < ad.vertices_length ; i++){
////        System.out.println(Arrays.toString(ad.distance_matrix[i]));
////        }
////        //---------------path()-----------------------------
//////        System.out.println("ad.path(1,1): " + ad.path(4,1));
//////        System.out.println("ad.path(1,1): " + ad.path(4,1));
//////
//////        System.out.println(ad.path(1, 4)); // 1-7-5-4
//////        System.out.println(ad.path(2, 5)); // 2-7-5
//////        System.out.println(ad.path(5, 4)); // 5-4
//////        System.out.println(ad.path(1, 5)); // 1-7-5
//////        System.out.println(ad.path(6, 2)); // 6-2
//////        System.out.println(ad.path(5, 8)); // 5-7-8
//////        System.out.println(ad.path(4, 8)); // 4-3-8
////
////        }
////        }
//
//
//
///////////////////////////////////////////
//
////    private void dijkstra(int src){
////
////        //Init all the weight from source to infinity
////        init_arrays(src);
////
////        PriorityQueue<Integer> vertices_queue = new PriorityQueue<>(1, comparator);
////        vertices_queue.add(src);
////
////        while(!vertices_queue.isEmpty()){
////            int current = vertices_queue.remove();
////            int distance;
////            for(int i = 0 ; i < vertices_length ; i++){
////                if(weight_from_src[current] != inf){
////                    distance = weight_from_src[current] + edgesWeight[current][i] + verticesWeight[i];
////                }else{
////                    distance = verticesWeight[current] + edgesWeight[current][i] + verticesWeight[i];
////                }
////                //Have a neighbor && smaller distance && Not visited
////                if (edgesWeight[current][i] != inf && distance < weight_from_src[i] && !visited[i]) {
////                    weight_from_src[i] = distance;
////                    shortestPath_matrix[src][i] = distance;
////                    parents[i] = current;
////                    vertices_queue.add(i);
////                }
////            }
////            visited[current] = true;
////        }
////        calculated = true;
////    }
//
//
//
//public static void main(String[] args) {
//final int inf = 1000000;
////        int[] vertices_weight = {2,4,3,6};
////        int[][] edges_weight = {{0,18,5,inf},
////                                {18,0,2,3},
////                                {5,2,0,inf},
////                                {inf,3,inf,0}};
//////
////        AllDistances a = new AllDistances(vertices_weight,edges_weight);
//////        long startTime = System.nanoTime();
////        int [][] ans = a.distance_matrix();
//////        long endTime = System.nanoTime();
//////        long timeElapsed = endTime - startTime;
//////        System.out.println("Execution time in nanoseconds: " + timeElapsed);
//////        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
////        for(int i=0 ; i < vertices_weight.length; i++){
////            System.out.println(Arrays.toString(ans[i]));
////        }
//////        System.out.println("Distance(1,4): " + a.distance(0,3));
//////        System.out.println("Path(1,4): " +a.path(0,3));
////
////        int vertices_w[] = {1, 3, 15, 2, 8, 8, 5, 6};
////
////        int[][] edges_w = {
////                {0, 5, inf, inf, inf, inf, 4, inf},
////                {5, 0, inf, inf, inf, 6, 8, inf},
////                {inf, inf, 0, 6, inf, inf, 2, 5},
////                {inf, inf, 6, 0, 5, inf, 18, inf},
////                {inf, inf, inf, 5, 0, 4, 3, inf},
////                {inf, 6, inf, inf, 4, 0, inf, inf},
////                {4, 8, 2, 18, 3, inf, 0, 7},
////                {inf, inf, 5, inf, inf, inf, 7, 0}
////        };
////        AllDistances ad = new AllDistances(vertices_w,edges_w);
////        // result:
////        /*
////        [1, 9, 27, 28, 21, 23, 10, 23]
////        [9, 3, 33, 34, 27, 17, 16, 29]
////        [27, 33, 15, 23, 33, 45, 22, 26]
////        [28, 34, 23, 2, 15, 27, 23, 34]
////        [21, 27, 33, 15, 8, 20, 16, 29]
////        [23, 17, 45, 27, 20, 8, 28, 41]
////        [10, 16, 22, 23, 16, 28, 5, 18]
////        [23, 29, 26, 34, 29, 41, 18, 6]
////        */
////
//////        for(int i=0 ; i < ad.vertices_length; i++){
//////            System.out.println(Arrays.toString(ad.distance_matrix()[i]));
//////        }
////
//////        for(int i=0 ; i < ad.vertices_length; i++){
//////            for(int j=0 ; j < ad.vertices_length; j++){
//////                System.out.print(ad.distance(i+1,j+1) +", ");
//////            }
//////            System.out.println();
//////        }
//////        System.out.println(ad.distance(1,1));
//////        System.out.println(ad.distance(1,4));
//////        System.out.println(ad.distance(4,1));
////
////
////
//////        //------------------------------Simulation--------------------------------
////        //---------------distance()-----------------------------
////        long startTime = System.nanoTime();
////        int x = 0;
////        for(int i=0 ; i < 10000000 ; i++){
////            x = ad.distance(1,4);
////        }
////        long endTime = System.nanoTime();
////        long timeElapsed = endTime - startTime;
////        System.out.println("distance(1,4): Dijkstra - Execution time in milliseconds:  "+ timeElapsed / 1000000);
////
////        ////---------------distance_matrix()-----------------------------
////        startTime = System.nanoTime();
////        //Floyd Warshall
////        for(int i=0 ; i < 10000000 ; i++){
////            ad.distance_matrix();
////        }
////        endTime = System.nanoTime();
////        timeElapsed = endTime - startTime;
////
////        System.out.println("distance_matrix(): Floyd Warshall - Execution time in milliseconds: " + timeElapsed / 1000000);
////
////        for(int i=0 ; i < ad.vertices_length ; i++){
////            System.out.println(Arrays.toString(ad.distance_matrix[i]));
////        }
//        //---------------path()-----------------------------
////        System.out.println("ad.path(1,1): " + ad.path(4,1));
////        System.out.println("ad.path(1,1): " + ad.path(4,1));
////
////        System.out.println(ad.path(1, 4)); // 1-7-5-4
////        System.out.println(ad.path(2, 5)); // 2-7-5
////        System.out.println(ad.path(5, 4)); // 5-4
////        System.out.println(ad.path(1, 5)); // 1-7-5
////        System.out.println(ad.path(6, 2)); // 6-2
////        System.out.println(ad.path(5, 8)); // 5-7-8
////        System.out.println(ad.path(4, 8)); // 4-3-8
//
//        ////////////////////////////////////////////////////////////////
//        int[] vertices_weight2 = {1,0,1,0,1,1};
//        int[][] edges_weight2 = {{1,inf,1,inf,inf,inf},
//        {inf,0,inf,3,10,inf},
//        {1,inf,1,inf,inf,1},
//        {inf,inf,inf,0,inf,inf},
//        {10,inf,inf,inf,1,1},
//        {inf,inf,1,inf,1,1}};
//
//        AllDistances ad2 = new AllDistances(vertices_weight2,edges_weight2);
//        System.out.println(ad2.path(1,4));
//
//
//
//        }