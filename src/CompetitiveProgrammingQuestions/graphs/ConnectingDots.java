package CompetitiveProgrammingQuestions.graphs;

import java.util.Scanner;
/*Connecting Dots
Send Feedback
Gary has a board of size NxM. Each cell in the board is a coloured dot. There exist only 26 colours denoted by uppercase Latin characters (i.e. A,B,...,Z). Now Gary is getting bore and wants to play a game. The key of this game is to find a cycle that contain dots of same colour. Formally, we call a sequence of dots d1, d2, ..., dk a cycle if and only if it meets the following condition:
1. These k dots are different: if i ≠ j then di is different from dj.
2. k is at least 4.
3. All dots belong to the same colour.
4. For all 1 ≤ i ≤ k - 1: di and di + 1 are adjacent. Also, dk and d1 should also be adjacent. Cells x and y are called adjacent if they share an edge.
Since Gary is colour blind, he wants your help. Your task is to determine if there exists a cycle on the board.
Assume input to be 0-indexed based.
Input Format :
Line 1 : Two integers N and M, the number of rows and columns of the board
Next N lines : a string consisting of M characters, expressing colors of dots in each line. Each character is an uppercase Latin letter.
Output Format :
Return 1 if there is a cycle else return 0
Constraints :
2 ≤ N, M ≤ 50
Sample Input :
3 4
AAAA
ABCA
AAAA
Sample Output :
1*/
public class ConnectingDots {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine());
        int m = Integer.parseInt(s.nextLine());

        String board[] = new String[n];
        for (int i = 0; i < n; i++) {
            board[i] = s.nextLine();
        }

        System.out.println(solve(board, n, m));

    }

    static int solve(String[] board, int n, int m) {
        boolean visited[][] = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                //for every unvisited cell, check if there exists a cycle and calculate the length in the traversal
                if (!visited[i][j]) {

                    DFS(board, n, m, -1, -1, i, j, board[i].charAt(j), visited);

                }
            }
        }
        return hasCycle?1:0;
    }

    static int[] dx = new int[]{0, 1, -1, 0};
    static int[] dy = new int[]{1, 0, 0, -1};
    static boolean hasCycle = false;

    private static void DFS(String[] am, int n, int m, int fromX, int fromY, int x, int y, char color, boolean[][] vis) {

        if (x < 0 || x >= n || y < 0 || y >= m) return ;
        if (am[x].charAt(y) != color ) return ;
        if (vis[x][y]) {
            //if it is already visited , then cycle exists, return true;
            hasCycle = true;
            return ;
        }
        vis[x][y] = true;
        //to find adj node
        for (int dir = 0; dir < 4; dir++) {
            //for each adj and not visited
            int nextX = x+dx[dir];
            int nextY = y+dy[dir];
            if(nextX == fromX && nextY== fromY)continue;
            DFS(am, n, m, x, y, nextX, nextY, color, vis);
        }
    }
}