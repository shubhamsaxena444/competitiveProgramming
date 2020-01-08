package CompetitiveProgrammingQuestions.graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*Coding Ninjas
Send Feedback
Given a NxM matrix containing Uppercase English Alphabets only. Your task is to tell if there is a path in the given matrix which makes the sentence “CODINGNINJA” .
There is a path from any cell to all its neighbouring cells. A neighbour may share an edge or a corner.
Input Format :
Line 1 : Two space separated integers N  and M, where N is number of rows and M is number of columns in the matrix.
Next N lines : N rows of the matrix. First line of these N line will contain 0th row of matrix, second line will contain 1st row and so on
Assume input to be 0-indexed based
Output Format :
Return 1 if there is a path which makes the sentence “CODINGNINJA” else return 0.
Constraints :
1 <= N <= 100
1 <= M <= 100
Sample Input :
2 11
CXDXNXNXNXA
XOXIXGXIXJX
Sample Output :
1
*/
public class CodingNinja {
    static String str = "CODINGNINJA";

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        char am[][] = new char[n][m];
        /* Write Your Code Here
         * Complete the Rest of the Program
         * You have to take input and print the output yourself
         */
        s.nextLine();
        for (int i = 0; i < n; i++) {
            am[i] = s.nextLine().toCharArray();
        }
        if (BFS(am, n, m)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
//        boolean vis[] =new boolean[v];
//        DFS(am,v,v1,vis);
    }

    private static void DFS(int[][] am, int v, int node, boolean[] vis) {
        vis[node] = true;
        System.out.print(node + " ");
        for (int i = 0; i < v; i++) {
            //for each adj and not visited
            if (am[node][i] == 1 && !vis[i]) {
                DFS(am, v, i, vis);
            }
        }
    }
static class NodeC{
        char data;
        int i,j;

    public NodeC(char data, int i, int j) {
        this.data = data;
        this.i = i;
        this.j = j;
    }
}
    private static boolean BFS(char[][] am, int n, int m) {



        // look for str[0], if found ,push in q and check for whole string
        boolean isFound =false;
        boolean visitedC[][] = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int it = 0;
                boolean vis[][] = new boolean[n][m];

                Queue<NodeC> q = new LinkedList<>();
                if (it < str.length() && !vis[i][j] && am[i][j] == str.charAt(it) && !visitedC[i][j]) {
                    //push first char
                    q.add(new NodeC(am[i][j],i,j));
                    vis[i][j] = true;
                    visitedC[i][j] =true;
                    it++;
                    if(found(am, n, m, q, it, vis,i,j)){
                       isFound =true;
                       break;
                    }
                }
            }
            if(isFound){
                break;
            }
        }
        return isFound;

    }

    private static boolean found(char[][] am, int n, int m, Queue<NodeC> q, int it, boolean[][] vis,int i,int j) {
        boolean isFound =false;
        while (!q.isEmpty()) {
            NodeC temp = q.poll();
//            System.out.print(temp.data + " ");
            if (it == str.length()) {
                isFound =true;
                break;
            }
            char compareChar =str.charAt(it);
            //find all adj chars == compareChar, push into q
            for (int adi = temp.i - 1; adi <= temp.i + 1; adi++) {
                for (int adj = temp.j - 1; adj <= temp.j + 1; adj++) {
                    //for each adj and not visited
                    if (adi >= 0 && adi < n && adj >= 0 && it < str.length() && adj < m && am[adi][adj] == compareChar && !vis[adi][adj]) {
                        //update i, j as the cordinates of current node
                        q.add(new NodeC(am[adi][adj],adi,adj));
                        vis[adi][adj] = true;


                    }
                }
            }
            it++;
        }
        return isFound;
    }
}