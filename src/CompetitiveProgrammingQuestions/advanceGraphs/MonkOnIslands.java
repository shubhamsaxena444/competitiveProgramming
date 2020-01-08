package CompetitiveProgrammingQuestions.advanceGraphs;

import java.util.*;
/*
* Monk and the Islands
Send Feedback
MONK AND THE ISLAND
Monk visits the land of Islands. There are a total of N islands numbered from 1 to N. Some pairs of islands are connected to each other by Bidirectional bridges running over water.
Monk hates to cross these bridges as they require a lot of efforts. He is standing at Island #1 and wants to reach the Island #N. Find the minimum the number of bridges that he shall have to cross, if he takes the optimal route.
Input:
First line contains T. T testcases follow.
First line of each test case contains two space-separated integers N, M.
Each of the next M lines contains two space-separated integers X and Y , denoting that there is a bridge between Island X and Island Y.
Output:
Print the answer to each test case in a new line.
Constraints:
1 ≤ T ≤ 10
1 ≤ N ≤ 10000
1 ≤ M ≤ 100000
1 ≤ X, Y ≤ N
SAMPLE INPUT
2
3 2
1 2
2 3
4 4
1 2
2 3
3 4
4 2
SAMPLE OUTPUT
2
2*/
public class MonkOnIslands {


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();
        List<Integer> res = new ArrayList<>();
        while (t-- > 0) {
            int v = s.nextInt();
            int e = s.nextInt();
            List<List<Integer>> edge = new ArrayList<>();
            for (int i = 0; i < v; i++) {
                edge.add(new ArrayList<>());
            }

            //add undirected edge
            for (int i = 0; i < e; i++) {
                int a = s.nextInt() - 1;
                int b = s.nextInt() - 1;
                edge.get(a).add(b);
                edge.get(b).add(a);
            }
//            int v1 = s.nextInt() - 1;
//            int v2 = s.nextInt() - 1;
            // getPathBFS(v, am, v1, v2).stream().forEach(x-> System.out.print(x+" "));
          res.add(getPathBFS(v, edge, 0, v-1));
        }
        res.stream().forEach(x-> System.out.println(x));
    }
    private static int getPathBFS(int v, List<List<Integer>> edge, int v1, int v2) {
        //kisne kisko dala
      /*  List<Integer> res = new ArrayList<>();
        HashMap<Integer,Integer> path= new HashMap<>();*/
        Found found =new Found(false);
       return BFS(edge,v1,v2,found,v);
       /* if(found.isFound) {
            //we have a map path which can tell usintr
            int cur = v2;
            while (cur != v1) {
                res.add(cur);
                //System.out.print(cur + " ");
                int parent = path.get(cur);
                cur=parent;
            }
            res.add(v1);
            //System.out.print(v1);
            return res;
        }
        return res;*/
    }


    static class Found{
        boolean isFound;
        Found(boolean is){
            isFound=is;
        }
    }
    private static int BFS(List<List<Integer>>  am, int startVertex, int endVertex, Found found,int v) {
        Queue<Integer> q =new LinkedList<>();
//        int v = am.length;
        boolean vis[] =new boolean[v];
        //add startVertex
        q.add(startVertex);
        vis[startVertex] =true;
        int level =1;
        q.add(null); //add marker
        //start ko to start ne hi dala
//        path.put(startVertex,startVertex);
        while (!q.isEmpty()) {
            if(found.isFound){
                break;
            }
            Integer temp = q.poll();
            if(temp == null){
                //end of level
                level++;
                 //mark end of level , if there are more unvisited vertex left
                if(!q.isEmpty())
                    q.add(null);
                continue;
            }

            for (int adj : am.get(temp)) {
                //for each adj and not visited
                if ( !vis[adj]) {
                    q.add(adj);
                    //adj is put by temp
//                    path.put(adj,temp);
                    vis[adj] = true;
                    //jese hi endvertex queue me gya , break everything.
                    if(adj == endVertex) {
                        found.isFound=true;
                        break;
                    }
                }
            }

        }
        return level;
    }

}