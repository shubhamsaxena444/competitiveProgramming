package CompetitiveProgrammingQuestions.advanceGraphs;

import java.util.*;
import java.util.Scanner;
/*Permutation Swaps
Send Feedback
Kevin has a permutation P of N integers 1, 2, ..., N, but he doesn't like it. Kevin wants to get a permutation Q.

Also he believes that there are M good pairs of integers (ai , bi). Kevin can perform following operation with his permutation:

Swap Px and Py only if (x, y) is a good pair.
Help him and tell if Kevin can obtain permutation Q using such operations.
Input format:
The first line of input will contain an integer T, denoting the number of test cases.

Each test case starts with two space-separated integers N and M. The next line contains N space-separated integers Pi. The next line contains N space-separated integers Qi. Each of the next M lines contains two space-separated integers ai and bi.
Output format:
For every test case output "YES" (without quotes) if Kevin can obtain permutation Q and "NO" otherwise.
Constraints:
1 ≤ T ≤ 10
2 ≤ N ≤ 100000
1 ≤ M ≤ 100000
1 ≤ Pi, Qi ≤ N. Pi and Qi are all distinct.
1 ≤ ai < bi ≤ N
SAMPLE INPUT
2
4 1
1 3 2 4
1 4 2 3
3 4
4 1
1 3 2 4
1 4 2 3
2 4
SAMPLE OUTPUT
NO
YES*/
public class PermutationSwaps {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        int t = sc.nextInt();
        List<String> res= new ArrayList<>();
        while(t-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int p[] =new int[n];
            int q[] =new int[n];
            List<List<edge>> al =new ArrayList<>(m);
            for(int i =0;i<n;i++) {
                al.add(new ArrayList<>());
            }
            for(int i =0;i<n;i++){
                p[i] =sc.nextInt();
            }
            for(int i =0;i<n;i++){
                q[i] =sc.nextInt();
            }

            for(int i =0;i<m;i++){
                int u = sc.nextInt()-1;
                int v = sc.nextInt()-1;
                al.get(u).add(new edge(u,v));
                al.get(v).add(new edge(v,u));
            }

            res.add(solve(al,n,m,p,q));
        }
        for(String r:res){
            System.out.println(r);
        }
    }

    private static String solve(List<List<edge>> al, int n, int m, int[] p, int[] q) {
        //need to find the connected components of the indexes from al, once we have that, find the elements present at those i
        //indices in p and 1 , if they are same, return yes else no.
        List<List<Integer>> allPaths = new ArrayList<>();
//        BFS(am,v,e);
        boolean possible =true;
        boolean vis[] =new boolean[n];
        for(int i =0;i<n;i++){
            if(vis[i]  == false) {
                List<Integer> path = new ArrayList<>();
                DFS(al, n, i, vis, path);
                allPaths.add(path);
            }
        }
        //for each disconnected component
        for(List<Integer> path : allPaths){
            //get all elements at indices l(elements) from p and q and compare
            TreeSet<Integer> pElements = new TreeSet<>();
            TreeSet<Integer> qElements = new TreeSet<>();
            for(Integer index:path){
                pElements.add(p[index]);
                qElements.add(q[index]);
            }
           if(!pElements.equals(qElements)){
               possible=false;
               break;
           }
        }
        return possible?"YES":"NO";
    }

    private static void DFS(List<List<edge>> am, int v, int node, boolean[] vis, List<Integer> path) {
        vis[node] =true;
        path.add(node);
        for(edge e: am.get(node)){
            //for each adj and not visited
            if( !vis[e.dest] ){
                DFS(am,v,e.dest,vis,path);
            }
        }
        return;
    }
    private static class edge {
        int src,dest;
        public edge(int u, int v) {
            src=u;
            dest=v;
        }
    }
}
