package CompetitiveProgrammingQuestions.advanceGraphs;

import java.util.ArrayList;
import java.util.*;
/*Connected horses
Send Feedback
You all must be familiar with the chess-board having
8*8 squares of alternate black and white cells. Well, here
we have for you a similar
N*M size board with similar arrangement of black and white
cells.
A few of these cells have Horses placed over them.
Each horse is unique. Now these horses are not the
usual horses which could jump to any of the
8 positions they usually jump in. They can move only if there is another horse on one of the 8-positions that it can     go to usually and then both the horses will swap their positions. This swapping can happen infinitely times.
A photographer was assigned to take a picture of all the different ways that the horses occupy the board! Given the state of the board, calculate answer. Sincethis answer may be quite large, calculate in modulo
10^9+7
Input:
First line contains
T which is the number of test cases.
T test cases follow first line of each containing three integers
N, M and Q where
N,M is the size of the board and
Q is the number of horses on it.

Q lines follow each containing the 2 integers
X and Y which are the coordinates of the Horses.
Output:
For each test case, output the number of photographs taken by photographer.
Constraints:
 1<=T<=10
 1<=N,M<=1000
 1<=Q<=N*M
SAMPLE INPUT
2
4 4 4
1 1
1 2
3 1
3 2
4 4 4
1 1
1 2
3 1
4 4
SAMPLE OUTPUT
4
2*/
public class ConnectedHorses {

    static class vertex{
        int i ,j;

        public vertex(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            vertex vertex = (vertex) o;
            return i == vertex.i &&
                    j == vertex.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        List<Integer> res = new ArrayList<>();
        //horse moves
        int dx[] = new int[]{2,2,1,-1,-2,-2,1,-1};
        int dy[] = new int[]{1,-1,2,2,1,-1,-2,-2};
        while(t-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int q = sc.nextInt();
            //it will just contain the dest vertex
            Map<vertex,List<vertex>> graph= new HashMap<>();


            boolean board[][] = new boolean[n][m];
            vertex[] v= new vertex[q];
            for(int i =0;i<q;i++){
                int x= sc.nextInt()-1;
                int y= sc.nextInt()-1;
                v[i] =new vertex(x,y);
                graph.put(v[i],new ArrayList<vertex>());

                board[x][y] = true;
                //check if it can move in any of the 8 directions(i.e, if there is already a horse, if yes, add edge
                for(int d =0;d<8;d++){
                    int xx = x +dx[d];
                    int yy = y +dy[d];
                    if(xx<0||xx>=n ||yy<0||yy>=m)continue;
                    if(board[xx][yy]) {
                        //add edge

                            vertex adj = new vertex(xx, yy);
                            graph.get(v[i]).add(adj);
                            graph.get(adj).add(v[i]);

                    }
                }

            }
            res.add(solve(graph,n,m,q,v));
        }
        res.stream().forEach(x-> System.out.println(x));
    }

    private static int solve(Map<vertex, List<vertex>> graph, int n, int m,int q,vertex[] vertices) {
        //need to find the connected components of the indexes from al, once we have that, find the elements present at those i
        //indices in p and 1 , if they are same, return yes else no.
        List<List<vertex>> allComponenets = new ArrayList<>();
//        BFS(am,v,e);
        boolean vis[][] =new boolean[n][m];
        for(int i =0;i<q;i++) {
                vertex v= vertices[i];
                if (vis[v.i][v.j] == false) {
                    List<vertex> path = new ArrayList<>();
                    DFS(graph, v, vis, path);
                    allComponenets.add(path);
                }
            }
        long ans=1;
            int[] dp =new int[q+1];
        for(List<vertex> component :allComponenets ){
            int fact = factorial(component.size(),dp)%mod;
            ans = ((ans%mod) * fact)%mod;
        }
        return (int)ans%mod;
    }
    static int mod =1000000007;
    static int factorial(int n,int[] dp)
    {
        if (n == 0)
            return 1;
        if(dp[n] !=0)return dp[n];
        long temp = factorial(n-1,dp);
        int intTemp = (int)(temp%mod);
        long temp2 = (long)n * (long)intTemp;
        dp[n] = (int)(temp2%mod);
        //dp[n] = (n*(factorial(n-1,dp)%mod))%mod;
        return dp[n];
    }
    private static void DFS( Map<vertex,List<vertex>> graph,  vertex node, boolean[][] vis, List<vertex> path) {
        vis[node.i][node.j] =true;
        path.add(node);
        for(vertex e: graph.get(node)){
            //for each adj and not visited
            if( !vis[e.i][e.j] ){
                DFS(graph,e,vis,path);
            }
        }
        return;
    }
}
