package CompetitiveProgrammingQuestions.graphs;
/*3 Cycle
Send Feedback
Given a graph with N vertices (numbered from 1 to N) and Two Lists (U,V) of size M where (U[i],V[i]) and (V[i],U[i]) are connected by an edge , then count the distinct 3-cycles in the graph. A 3-cycle PQR is a cycle in which (P,Q), (Q,R) and (R,P) are connected an edge.
Input Format :
Line 1 : Two integers N and M
Line 2 : List u of size of M
Line 3 : List v of size of M
Return Format :
The count the number of 3-cycles in the given Graph
Constraints :
1<=N<=100
1<=M<=(N*(N-1))/2
1<=u[i],v[i]<=N
Sample Input:
3 3
1 2 3
2 3 1
Sample Output:
1*/
public class ThreeCycles {

    int V ;
    int count = 0;
    public int solve(int n,int m,int u[],int v[]) {
        // Number of vertices

        int graph[][] = new int[n][n];
        for(int i  =0;i<m;i++) {
            graph[u[i]-1][v[i]-1] =1;
            graph[v[i]-1][u[i]-1] =1;
        }
        V=n;
        return
                countCycles(graph, 3);
    }
    void DFS(int graph[][], boolean marked[],
             int n, int vert, int start) {

        // mark the vertex vert as visited
        marked[vert] = true;

        // if the path of length (n-1) is found
        if (n == 0) {

            // mark vert as un-visited to
            // make it usable again
            marked[vert] = false;

            // Check if vertex vert end
            // with vertex start
            if (graph[vert][start] == 1) {
                count++;
                return;
            } else
                return;
        }

        // For searching every possible
        // path of length (n-1)
        for (int i = 0; i < V; i++)
            if (!marked[i] && graph[vert][i] == 1)

                // DFS for searching path by
                // decreasing length by 1
                DFS(graph, marked, n-1, i, start);

        // marking vert as unvisited to make it
        // usable again
        marked[vert] = false;
    }

    // Count cycles of length N in an
    // undirected and connected graph.
    int countCycles(int graph[][], int n) {

        // all vertex are marked un-visited
        // initially.
        boolean marked[] = new boolean[V];

        // Searching for cycle by using
        // v-n+1 vertices
        for (int i = 0; i < V - (n - 1); i++) {
            DFS(graph, marked, n-1, i, i);

            // ith vertex is marked as visited
            // and will not be visited again
            marked[i] = true;
        }

        return count / 2;
    }
}
