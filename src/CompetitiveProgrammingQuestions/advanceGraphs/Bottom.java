package CompetitiveProgrammingQuestions.advanceGraphs;


import java.util.*;
/*
* BOTTOM
Send Feedback
We will use the following (standard) definitions from graph theory. Let V be a non-empty and finite set, its elements being called vertices (or nodes). Let E be a subset of the Cartesian product V×V, its elements being called edges. Then G=(V,E) is called a directed graph.

Let n be a positive integer, and let p=(e1,…,en) be a sequence of length n of edges ei∈E such that ei=(vi,vi+1)for a sequence of vertices (v1,…,vn+1). Then p is called a path from vertex v1 to vertex vn+1 in G and we say that vn+1 is reachable from v1, writing (v1→vn+1).

Here are some new definitions. A node v in a graph G=(V,E) is called a sink, if for every node w in G that is reachable from v, v is also reachable from w. The bottom of a graph is the subset of all nodes that are sinks, i.e., bottom(G)={v∈V∣∀w∈V:(v→w)⇒(w→v)}. You have to calculate the bottom of certain graphs.
Input Specification
The input contains several test cases, each of which corresponds to a directed graph G. Each test case starts with an integer number v, denoting the number of vertices of G=(V,E) where the vertices will be identified by the integer numbers in the set V={1,…,v}. You may assume that 1≤v≤5000. That is followed by a non-negative integer e and, thereafter, e pairs of vertex identifiers v1,w1,…,ve,we with the meaning that (vi,wi)∈E. There are no edges other than specified by these pairs. The last test case is followed by a zero.
Output Specification
For each test case output the bottom of the specified graph on a single line. To this end, print the numbers of all nodes that are sinks in sorted order separated by a single space character. If the bottom is empty, print an empty line.
Sample Input
3 3
1 3 2 3 3 1
2 1
1 2
0
Sample Output
1 3
2*/
class Main {
    private int V;   // No. of vertices
     LinkedList<Integer> adj[]; //Adjacency List

    //Constructor
    Main(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    //Function to add an edge into the graph
    void addEdge(int v, int w)  { adj[v].add(w); }

    // A recursive function to print DFS starting from v
    void DFSUtil(int v, boolean visited[], List<Integer> component)
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        component.add(v);
        int n;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i =adj[v].iterator();
        while (i.hasNext())
        {
            n = i.next();
            if (!visited[n])
                DFSUtil(n,visited,component);
        }
    }

    // Function that returns reverse (or transpose) of this graph
    Main getTranspose()
    {
        Main g = new Main(V);
        for (int v = 0; v < V; v++)
        {
            // Recur for all the vertices adjacent to this vertex
            Iterator<Integer> i =adj[v].listIterator();
            while(i.hasNext())
                g.adj[i.next()].add(v);
        }
        return g;
    }

    void fillOrder(int v, boolean visited[], Stack stack)
    {
        // Mark the current node as visited and print it
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj[v].iterator();
        while (i.hasNext())
        {
            int n = i.next();
            if(!visited[n])
                fillOrder(n, visited, stack);
        }

        // All vertices reachable from v are processed by now,
        // push v to Stack
        stack.push(new Integer(v));
    }

    // The main function that finds and prints all strongly
    // connected components
    List<List<Integer>> printSCCs()
    {
        Stack stack = new Stack();

        // Mark all the vertices as not visited (For first DFS)
        boolean visited[] = new boolean[V];
        for(int i = 0; i < V; i++)
            visited[i] = false;

        // Fill vertices in stack according to their finishing
        // times
        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                fillOrder(i, visited, stack);

        // Create a reversed Main
        Main gr = getTranspose();

        // Mark all the vertices as not visited (For second DFS)
        for (int i = 0; i < V; i++)
            visited[i] = false;
        List<List<Integer>>  stronglyConnectedComponents = new ArrayList<>();
        // Now process all vertices in order defined by Stack
        while (stack.empty() == false)
        {
            // Pop a vertex from stack
            int v = (int)stack.pop();

            // Print Strongly connected component of the popped vertex
            if (visited[v] == false)
            {
                List<Integer> component = new ArrayList<>();
                gr.DFSUtil(v, visited,component);
                stronglyConnectedComponents.add(component);
            }
        }
        return stronglyConnectedComponents;
    }

    // Driver method
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
//        int t = sc.nextInt();
        int n = sc.nextInt();
        LinkedHashSet<TreeSet<Integer>> bottoms = new LinkedHashSet<>();
        do{

            Main g = new Main(n);
            int m =sc.nextInt();
            for(int i =0;i<m;i++){
                int u = sc.nextInt()-1;
                int v = sc.nextInt()-1;
                g.addEdge(u,v);
            }
            List<List<Integer>> res = g.printSCCs();

            fillBottoms(n, bottoms, g, res);
            n= sc.nextInt();
    }while(n!=0);
        bottoms.stream().forEach(x->{
               x.stream().forEach(y-> System.out.print(y+1+" "));
               System.out.println();
           });
    }

    private static void fillBottoms(int n, LinkedHashSet<TreeSet<Integer>> bottoms, Main g, List<List<Integer>> res) {
        TreeSet<Integer>  bottom = new TreeSet<>();

        //create a Hashmap<int,int> mapping vertex to component number
        HashMap<Integer,Integer> map =new HashMap<>();
        for(int componentNumber =0;componentNumber<res.size();componentNumber++){
            for(int vertex: res.get(componentNumber)){
                map.put(vertex,componentNumber);
            }
        }
        for(int i =0;i<n;i++){
            //for every vertex , check whether all its adjacents lie in the same component or not
            boolean isBottom =true;
            ListIterator<Integer> adjacentVertices = g.adj[i].listIterator();
            while(adjacentVertices.hasNext()){
                if(map.get(adjacentVertices.next()) != map.get(i)){
                    isBottom =false;
                    break;
                }
            }
            //if they lie in the same component , then it is a bottom node/sink node
            if(isBottom){
                bottom.add(i);
            }
        }
        bottoms.add(bottom);
    }
}