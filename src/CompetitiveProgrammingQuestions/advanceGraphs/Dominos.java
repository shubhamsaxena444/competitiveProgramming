package CompetitiveProgrammingQuestions.advanceGraphs;

import java.util.*;
/*
* Dominos
Send Feedback
Domino
Dominos are lots of fun. Children like to stand the tiles on their side in long lines. When one domino falls, it knocks down the next one, which knocks down the one after that, all the way down the line.
However, sometimes a domino fails to knock the next one down. In that case, we have to knock it down by hand to get the dominos falling again.
Your task is to determine, given the layout of some domino tiles, the minimum number of dominos that must be knocked down by hand in order for all of the dominos to fall.
Input
The first line of input contains one integer specifying the number of test cases to follow.Each test case begins with a line containing two integers,each no larger than 100 000. The first integer n is the number of domino tiles and the second integer m is the number of lines to follow in the test case. The domino tiles are numbered from 1 to n.
Each of the following lines contains two integers x and y indicating that if domino number x falls, it will cause domino number y to fall as well.
Output
For each test case, output a line containing one integer, the minimum number of dominos that must be knocked over by hand in order for all the dominos to fall.
Sample Input
1
3 2
1 2
2 3
Sample Output
1*/
public class Dominos {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);


        int t = s.nextInt();
        List<Integer> count = new ArrayList<>();
        while(t-->0) {
            int v = s.nextInt();
            int e = s.nextInt();
            int am[][] = new int[v][v];
            /* Write Your Code Here
             * Complete the Rest of the Program
             * You have to take input and print the output yourself
             */
            for (int i = 0; i < e; i++) {
                int a = s.nextInt()-1;
                int b = s.nextInt()-1;
                am[a][b] = 1;
                am[b][a] = 1;
            }
            List<List<Integer>> allPaths = new ArrayList<>();
//        BFS(am,v,e);

            boolean vis[] = new boolean[v];
            for (int i = 0; i < v; i++) {
                if (vis[i] == false) {
                    List<Integer> path = new ArrayList<>();
                    DFS(am, v, i, vis, path);
                    allPaths.add(path);
                }
            }
            count.add(allPaths.size());
        }//if all vis then connected else false
        count.stream().forEach(x-> System.out.println(x));
//        System.out.println(allPaths);
//        allPaths.stream().forEach(x->{
//            x.stream().sorted().forEach(y-> System.out.print(y+" "));
//            System.out.println();
//        } );
    }

    private static void DFS(int[][] am, int v, int node, boolean[] vis, List<Integer> path) {
        vis[node] =true;
        path.add(node);
        for(int i=0;i<v;i++){
            //for each adj and not visited
            if(am[node][i] == 1 && !vis[i] ){
                DFS(am,v,i,vis,path);
            }
        }
        return;
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