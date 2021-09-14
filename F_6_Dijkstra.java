package Algorithms2;

import java.util.Comparator;
import java.util.PriorityQueue;

class Vertex{
    int id;
    double distance;
    int father;
    boolean visited;
}

class vertexComparator implements Comparator<Vertex> {
    @Override
    public int compare(Vertex x, Vertex y) {
        if (x.distance < y.distance) { return -1; }
        if (x.distance > y.distance) { return 1; }
        return 0;
    }
}

class G_Dijkstra {

    public static void dijkstra(Vertex[] vertices, double[][] edges, int src){
        for(int i=0 ; i < vertices.length ; i++){
            vertices[i].id = i;
            vertices[i].distance = Double.MAX_VALUE;
            vertices[i].father = -1;
            vertices[i].visited = false;
        }
        vertices[src].distance = 0;

        Comparator<Vertex> comparator = new vertexComparator();
        PriorityQueue<Vertex> queue = new PriorityQueue<>(1, comparator);

        queue.add(vertices[src]);
//        for(int i=0 ; i < vertices.length ; i++){
//            queue.add(vertices[i]);
//        }

        while(!queue.isEmpty()){
            Vertex v = queue.remove();
            for(int i=0; i < edges.length ; i++){
                if(edges[v.id][i] != -1 && vertices[i].visited == false){
                    double weight = v.distance + edges[v.id][i];
                    if(vertices[i].distance >= weight){
                        vertices[i].distance = weight;
                        vertices[i].father = v.id;
                        queue.add(vertices[i]);
                    }
                }
            }
            v.visited = true;
        }
    }



    public static void main(String[] args){
        Vertex v[] = new Vertex[5];
        for(int i=0 ; i < v.length ; i++){
            v[i] = new Vertex();
        }
        double edges[][] = {{-1,4,2,-1,-1},
                            {4,-1,-1,1,2},
                            {2,-1,-1,6,-1},
                            {-1,1,6,-1,3},
                            {-1,2,-1,3,-1}};


        dijkstra(v,edges,0);
        for(int i=0 ; i < v.length ; i++){
            System.out.println(v[i].distance);
        }
//        System.out.println(v[4].visited);
//        System.out.println(v[3].visited);
//        System.out.println(v[2].visited);
//        System.out.println(v[1].visited);
//        System.out.println(v[0].visited);
    }
}
