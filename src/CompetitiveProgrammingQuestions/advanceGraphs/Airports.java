package CompetitiveProgrammingQuestions.advanceGraphs;

import java.util.*;

/*
* AIRPORTS
Send Feedback
AIRPORTS
The government of a certain developing nation wants to improve transportation in one of its most inaccessible areas, in an attempt to attract investment. The region consists of several important locations that must have access to an airport.
Of course, one option is to build an airport in each of these places, but it may turn out to be cheaper to build fewer airports and have roads link them to all of the other locations. Since these are long distance roads connecting major locations in the country (e.g. cities, large villages, industrial areas), all roads are two-way. Also, there may be more than one direct road possible between two areas. This is because there may be several ways to link two areas (e.g. one road tunnels through a mountain while the other goes around it etc.) with possibly differing costs.
A location is considered to have access to an airport either if it contains an airport or if it is possible to travel by road to another location from there that has an airport. You are given the cost of building an airport and a list of possible roads between pairs of locations and their corresponding costs. The government now needs your help to decide on the cheapest way of ensuring that every location has access to an airport. The aim is to make airport access as easy as possible, so if there are several ways of getting the minimal cost, choose the one that has the most airports.
Input
The first line of input contains the integer T (T < 25), the number of test cases. The rest of the input consists of T cases. Each case starts with two integers N, M and A (0 < N ≤ 10, 000, 0 ≤ M ≤ 100, 000, 0 < A ≤ 10, 000) separated by white space. N is the number of locations, M is the number of possible roads that can be built, and A is the cost of building an airport.
The following M lines each contain three integers X, Y and C (1 ≤ X, Y ≤ N, 0 < C ≤ 10, 000), separated by white space. X and Y are two locations, and C is the cost of building a road between X and Y .
Output
Your program should output exactly T lines, one for each case. Each line should be of the form ‘Case #X: Y Z’, where X is the case number Y is the minimum cost of making roads and airports so that all locations have access to at least one airport, and Z is the number of airports to be built. As mentioned earlier, if there are several answers with minimal cost, choose the one that maximizes the number of airports.
Sample Input
2
4 4 100
1 2 10
4 3 12
4 1 41
2 3 23
5 3 1000
1 2 20
4 5 40
3 2 30
Sample Output
Case #1: 145 1
Case #2: 2090 2*/
public class Airports {
    static class edge {
        int a;
        int b;
        int w;

        public edge(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }
    }
static class Answer{
        int numberOfAirports;
        int cost;

    public Answer(int numberOfAirports, int cost) {
        this.numberOfAirports = numberOfAirports;
        this.cost = cost;
    }
}
static int A;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        List<Answer> res =new ArrayList<>();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            A = sc.nextInt();
            int[][] am= new int[n][n];
            List<edge> edges = new ArrayList<>();
//
            for (int i = 0; i < m; i++) {
                int a = sc.nextInt()-1;
                int b = sc.nextInt()-1;
                int w = sc.nextInt();
               // if (w == 0) continue;
                edges.add(new edge(a, b, w));
                edges.add(new edge( b,a, w));

                am[a][b] =w;
                am[b][a] =w;
            }
                    res.add(kruskalsAlgo(n, edges.size(), edges));
        }
        for(int i =0;i<res.size();i++){
            System.out.println("Case #"+(i+1)+ ": "+res.get(i).cost+ " "+res.get(i).numberOfAirports );
        }
    }

    //when this count reaches v-1 we stop.
    static int count = 0;

    private static Answer kruskalsAlgo(int v, int e, List<edge> edges) {
        //sort the edges by weight
        Collections.sort(edges, new Comparator<edge>() {
            @Override
            public int compare(edge o1, edge o2) {
                return o1.w - o2.w;
            }
        });
        //To apply unionfind algorithm , we need to maintain a parent array
        //this will be initialised with same vertex
        int[] p = new int[v];
        for (int i = 0; i < v; i++) {
            p[i] = i;
        }
        //keep a boolean visited array
        boolean[] vis = new boolean[v];
        int cost=0;
        //iterate though edges 1 by 1 and check for cycle detection either by hasPath method or by unionFindalgorithm
        for (int i = 0; i < e; i++) {
            edge edge = edges.get(i);

            int p1 = findTopmostParent(edge.a, p);
            int p2 = findTopmostParent(edge.b, p);

            if (p1!=p2 && edges.get(i).w <A ) {
                cost+=edges.get(i).w;
                p[p2] = p1;

            }
        }
        int c =0;
        for(int i =0;i<v;i++){
            if(p[i]==i){
                c++;
            }
        }
        cost+=c*A;
        return new Answer(c,cost );
    }

  /*  private static boolean detectCycleUsingUnionFindAlgorithm(edge edge, int p[], boolean[] vis) {
        //if the two verteces have the same topmost parent , then return true, because they are in the same component,
        int p1 = findTopmostParent(edge.a, p);
        int p2 = findTopmostParent(edge.b, p);
        if (p1 == p2) {
            return true;

        } else {
            //we return false(no cycle) only when the vertices are unvisited or they have different topmost parent.
            //when we reutrn false(no cycle) , we also update the parent array with the topmost vertex

            //mark verteces visited
            vis[edge.a] = true;
            vis[edge.b] = true;
            //update parent array, by updating vertex b's parent to vertex a's parent
            p[p2] = p1;

            //inc the count of edges
            count++;
            return false;
        }
    }*/

    private static int findTopmostParent(int vertex, int[] p) {
        int parent = p[vertex];
        while (parent != vertex) {
            vertex = parent;
            parent = p[vertex];
        }
        return parent;
    }

    private static void printEdge(edge edge) {
        if (edge.a < edge.b) {
            System.out.println(edge.a + " " + edge.b + " " + edge.w);
        } else {
            System.out.println(edge.b + " " + edge.a + " " + edge.w);
        }
    }
}