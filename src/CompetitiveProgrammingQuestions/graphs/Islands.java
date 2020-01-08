package CompetitiveProgrammingQuestions.graphs;

import java.util.*;
/*Islands
Send Feedback
An island is a small piece of land surrounded by water . A group of islands is said to be connected if we can reach from any given island to any other island in the same group . Given N islands (numbered from 1 to N) and two lists of size M (u and v) denoting island u[i] is connected to island v[i] and vice versa . Can you count the number of connected groups of islands.
Constraints :
1<=N<=100
1<=M<=(N*(N-1))/2
1<=u[i],v[i]<=N
Input Format :
Line 1 : Two integers N and M
Line 2 : List u of size of M
Line 3 : List v of size of M
Output Return Format :
The count the number of connected groups of islands
Sample Input :
2 1
1
2
Sample Output :
1
*/
public class Islands {

        public static void main(String[] args) {
            Scanner s = new Scanner(System.in);
            int n = s.nextInt();
            int m = s.nextInt();
            int am[][] =new int[n][n];
            /* Write Your Code Here
             * Complete the Rest of the Program
             * You have to take input and print the output yourself
             */
            int u[] =new int[m];

            int v[] =new int[m];
            for(int i =0;i<m;i++){
                u[i] = s.nextInt()-1;
                // int b = s.nextInt();
                // am[a][b] =1;
                // am[b][a] =1;
            }
            for(int i =0;i<m;i++){
                v[i] = s.nextInt()-1;
                // int b = s.nextInt();
                // am[a][b] =1;
                // am[b][a] =1;
            }
            for(int i =0;i<m;i++){

                am[u[i]][v[i]] =1;
                am[v[i]][u[i]] =1;
            }
//        BFS(am,v,e);
            int count =1;
            boolean vis[] =new boolean[n];
            DFS(am,n,0,vis);
            //if all vis then connected else false
            boolean isCon =true;
            for(int i =0;i<n;i++){
                if(vis[i]  == false){
                    count++;
                    isCon=false;
                    DFS(am,n,i,vis);
                }
            }
            System.out.println(count);
        }

        private static void DFS(int[][] am, int v, int node, boolean[] vis) {
            vis[node] =true;
            for(int i=0;i<v;i++){
                //for each adj and not visited
                if(am[node][i] == 1 && !vis[i] ){
                    DFS(am,v,i,vis);
                }
            }
        }

        private static void BFS(int[][] am, int v, int e) {
            Queue<Integer> q =new LinkedList<>();
            boolean vis[] =new boolean[v];
            for(int i =0;i<v ;i++) {
                if(!vis[i]) {
                    q.add(i);
                    vis[i] = true;
                    while (!q.isEmpty()) {
                        int temp = q.poll();
                        System.out.print(temp + " ");
                        for (int adj = 0; adj < v; adj++) {
                            //for each adj and not visited
                            if (am[temp][adj] == 1 && !vis[adj]) {
                                q.add(adj);
                                vis[adj] = true;
                            }
                        }
                    }
                }
            }
        }

    }
