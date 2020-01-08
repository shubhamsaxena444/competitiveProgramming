package CompetitiveProgrammingQuestions.graphs;
import java.util.*;
import java.util.LinkedList;

 class Graph
    {
        private int V;   // No. of vertices
        public LinkedList<Integer>[] adj; //Adjacency List

        //Constructor
        Graph(int v)
        {
            V = v;
            adj = new LinkedList[v];
            for (int i=0; i<v; ++i)
                adj[i] = new LinkedList();
        }

        //Function to add an edge into the graph
        void addEdge(int v, int w)  { adj[v].add(w); }

        // A recursive function to print DFS starting from v
        void DFSUtil(int v,boolean visited[],List<Integer> component)
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
        Graph getTranspose()
        {
            Graph g = new Graph(V);
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

            // Create a reversed graph
            Graph gr = getTranspose();

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
            // Create a graph given in the above diagram
//            Graph g = new Graph(5);
//            g.addEdge(1, 0);
//            g.addEdge(0, 2);
//            g.addEdge(2, 1);
//            g.addEdge(0, 3);
//            g.addEdge(3, 4);
            Scanner sc = new Scanner(System.in);
            int t = sc.nextInt();
            List<Integer> count = new ArrayList<>();
            while(t-->0){
                int n = sc.nextInt();
                Graph g = new Graph(n);
                int m =sc.nextInt();
                for(int i =0;i<m;i++){
                    int u = sc.nextInt()-1;
                    int v = sc.nextInt()-1;
                    g.addEdge(u,v);
//                    g.addEdge(v,u);
                }
                List<List<Integer>> res = g.printSCCs();
//                res.stream().forEach(x->{
//               x.stream().forEach(y-> System.out.print(y+" "));
//               System.out.println();
//           });
                count.add(res.size());
            }
            count.stream().forEach(x-> System.out.println(x));
//
//           res.stream().forEach(x->{
//               x.stream().forEach(y-> System.out.print(y+" "));
//               System.out.println();
//           });
        }
    }
