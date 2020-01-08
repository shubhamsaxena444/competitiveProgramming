package CompetitiveProgrammingQuestions.graphs;

import java.util.*;
import java.util.stream.Collectors;
/*
* Dijkstra's Algorithm
Send Feedback
Given an undirected, connected and weighted graph G(V, E) with V number of vertices (which are numbered from 0 to V-1) and E number of edges.
Find and print the shortest distance from the source vertex (i.e. Vertex 0) to all other vertices (including source vertex also) using Dijkstra's Algorithm.
Print the ith vertex number and the distance from source in one line separated by space. Print different vertices in different lines.
Note : Order of vertices in output doesn't matter.
Input Format :
Line 1: Two Integers V and E (separated by space)
Next E lines : Three integers ei, ej and wi, denoting that there exists an edge between vertex ei and vertex ej with weight wi (separated by space)
Output Format :
In different lines, ith vertex number and its distance from source (separated by space)
Constraints :
2 <= V, E <= 10^5
Sample Input 1 :
4 4
0 1 3
0 3 5
1 2 1
2 3 8
Sample Output 1 :
0 0
1 3
2 4
3 5
* */
public class dijktras {
        static class edge{
            int d;
            int w;
            public edge(int dest, int w) {
                this.d = dest;
                this.w = w;
            }
        }

        public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int v = s.nextInt();
        int e = s.nextInt();
        /* Write Your Code Here
         * Complete the Rest of the Program
         * You have to take input and print the output yourself
         */
        //Adjecency list representation
        List<List<edge>> al = new ArrayList<>(v);
        //initialize all arraylists
        for(int i =0;i<v;i++){
            al.add(i,new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            int src= s.nextInt();
            int dest= s.nextInt();
            int weight= s.nextInt();

            al.get(src).add(new edge(dest,weight));
            al.get(dest).add(new edge(src,weight));
        }
       edge[] output = dijktrasAlgo( v, e,al);
    }
private static class Dist{
            int s;
            int d;
            int minD;

    public Dist(int s, int d, int minD) {
        this.s = s;
        this.d = d;
        this.minD = minD;
    }
}
        private static edge[] dijktrasAlgo(int v, int e, List<List<edge>> al) {
        edge[] output =new edge[v-1];

        //we need 5 things to apply  algorithm
        //a set of visited vertex, unvisited vertex, parent, vertex and weight
        HashSet<Integer> visited =new HashSet<>();
        HashSet<Integer> unvisited =new HashSet<>();
        int parent[] = new int[v];
        int vertex[] = new int[v];
        int dist[] = new int[v];
        //initial setup
        for(int i =0;i<v;i++) {
            vertex[i] = i;
            parent[i] = -2;  //representing none
            dist[i] = Integer.MAX_VALUE;
            unvisited.add(i);
        }
        // initialize everything by taking out 0th vertex as source vertex
        dist[0] = 0;
        PriorityQueue<Dist> pq = new PriorityQueue<Dist>(new Comparator<Dist>() {

            @Override
            public int compare(Dist o1, Dist o2) {
                return o1.minD-o2.minD;
            }
        });
        pq.add(new Dist(0,0,0));
        parent[0] =-1;


        while(!unvisited.isEmpty()) {
        //get minDist unvisited vertex
            int minVertex=-1;
            int minDistanceTillminVertex= -1;
            while(!unvisited.contains( minVertex)) {
               Dist distt = pq.poll();
                minVertex=distt.d;
                minDistanceTillminVertex=distt.minD;
            }
            if(minVertex==-1){
                //means there is no unvisited element left
                break;
            }
            // find all adjacent and unvisited of this minVertex, in this case 0, update the dist of adject only if this is smaller than existing minD.
            List<edge> adj = al.get(minVertex).stream().filter(x->unvisited.contains(x.d)).collect(Collectors.toList());
            for (edge ed : adj) {
                //if ed.weight is smaller for weight[destination],then update
                int newDistance =   minDistanceTillminVertex + ed.w;
                if (newDistance < dist[ed.d]) {
                    //update parent and weight,visited and unvisited
                    dist[ed.d] =newDistance;
                    pq.add(new Dist(0,ed.d,newDistance));
                    parent[ed.d] = minVertex;
                }
            }
            //now next, we will mark minVertex as visited,
            visited.add(minVertex);
            unvisited.remove(minVertex);
        }

        //traverse to find the edge of MST
        //edge = = parent -vertex --weight
        for(int i =0;i<v ;i++){

                System.out.println(i + " " +dist[i]);

        }

        return null;
    }
}
