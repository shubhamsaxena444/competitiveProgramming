package CompetitiveProgrammingQuestions.graphs;

import java.util.HashSet;
import java.util.*;
import java.util.stream.Collectors;
/*
* Prim's Algorithm
Send Feedback
Given an undirected, connected and weighted graph G(V, E) with V number of vertices (which are numbered from 0 to V-1) and E number of edges.
Find and print the Minimum Spanning Tree (MST) using Prim's algorithm.
For printing MST follow the steps -
1. In one line, print an edge which is part of MST in the format -
v1 v2 w
where, v1 and v2 are the vertices of the edge which is included in MST and whose weight is w. And v1 <= v2 i.e. print the smaller vertex first while printing an edge.
2. Print V-1 edges in above format in different lines.
Note : Order of different edges doesn't matter.
Input Format :
Line 1: Two Integers V and E (separated by space)
Next E lines : Three integers ei, ej and wi, denoting that there exists an edge between vertex ei and vertex ej with weight wi (separated by space)
Output Format :
MST
Constraints :
2 <= V, E <= 10^5
Sample Input 1 :
4 4
0 1 3
0 3 5
1 2 1
2 3 8
Sample Output 1 :
0 1 3
1 2 1
0 3 5
* */
public class prims {
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
            if(weight==0)continue;
            al.get(src).add(new edge(dest,weight));
            al.get(dest).add(new edge(src,weight));
        }
        edge[] output = primsAlgo( v, e,al);
    }

    private static edge[] primsAlgo(int v, int e, List<List<edge>> al) {
        edge[] output =new edge[v-1];

        //we need 5 things to apply prims algorithm
        //a set of visited vertex, unvisited vertex, parent, vertex and weight
        HashSet<Integer> visited =new HashSet<>();
        HashSet<Integer> unvisited =new HashSet<>();
        int parent[] = new int[v];
        int vertex[] = new int[v];
        int weight[] = new int[v];
        //initial setup
        for(int i =0;i<v;i++) {
            vertex[i] = i;
            parent[i] = -2;  //representing none
            weight[i] = Integer.MAX_VALUE;
            unvisited.add(i);
        }
        // initialize everything by taking out 0th vertex as source vertex
        weight[0] = 0;
        PriorityQueue<edge> pq = new PriorityQueue<edge>(new Comparator<edge>() {

            @Override
            public int compare(edge o1, edge o2) {
                return o1.w-o2.w;
            }
        });
        pq.add(new edge(0,0));
        parent[0] =-1;


        while(!unvisited.isEmpty()) {

            int minVertex =-1;
//            int min = Integer.MAX_VALUE;
            //find next unvisited vertex based on the smallest weighted edge
          /* for(int i =0;i<v;i++){
               if(unvisited.contains(vertex[i]) && weight[i] < min){
                   min = weight[i];
                   minVertex = i;
               }
           }*/
          minVertex = pq.poll().d;
           if(minVertex==-1){
               //mean there is no unvisited element left
               break;
           }
            // find all adjacent and non visited of this minVertex, in this case 0, update the weight of adject only if this is smaller than existing weight.
            List<edge> adj = al.get(minVertex).stream().filter(x->unvisited.contains(x.d)).collect(Collectors.toList());
            for (edge ed : adj) {
                //if ed.weight is smaller for weight[destination],then update
                if (ed.w < weight[ed.d]) {
                    //update parent and weight,visited and unvisited
                    weight[ed.d] =ed.w;
                    pq.add(ed);
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
            if(parent[i] == -1)continue;

            if(parent[i] < vertex[i]){
            System.out.println(parent[i] + " "+ vertex[i] + " " +weight[i]);
            }else{
                System.out.println( vertex[i] + " " +parent[i] + " "+weight[i]);
            }
        }

        return null;
    }
}
