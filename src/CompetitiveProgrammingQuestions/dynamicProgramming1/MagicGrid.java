package CompetitiveProgrammingQuestions.dynamicProgramming1;

import java.util.Scanner;
/*Magic Grid Problem
Send Feedback
You are given a magrid S ( a magic grid ) having R rows and C columns. Each cell in this magrid has either a Hungarian horntail dragon that our intrepid hero has to defeat, or a flask of magic potion that his teacher Snape has left for him. A dragon at a cell (i,j) takes away |S[i][j]| strength points from him, and a potion at a cell (i,j) increases Harry's strength by S[i][j]. If his strength drops to 0 or less at any point during his journey, Harry dies, and no magical stone can revive him.
Harry starts from the top-left corner cell (1,1) and the Sorcerer's Stone is in the bottom-right corner cell (R,C). From a cell (i,j), Harry can only move either one cell down or right i.e., to cell (i+1,j) or cell (i,j+1) and he can not move outside the magrid. Harry has used magic before starting his journey to determine which cell contains what, but lacks the basic simple mathematical skill to determine what minimum strength he needs to start with to collect the Sorcerer's Stone. Please help him once again.
Input (STDIN)
The first line contains the number of test cases T. T cases follow. Each test case consists of R C in the first line followed by the description of the grid in R lines, each containing C integers. Rows are numbered 1 to R from top to bottom and columns are numbered 1 to C from left to right. Cells with S[i][j] < 0 contain dragons, others contain magic potions.
Output (STDOUT):
Output T lines, one for each case containing the minimum strength Harry should start with from the cell (1,1) to have a positive strength through out his journey to the cell (R,C).
Constraints:
1 ≤ T ≤ 5

2 ≤ R, C ≤ 500

-10^3 ≤ S[i][j] ≤ 10^3

S[1][1] = S[R][C] = 0
Sample Input
3
2 3
0 1 -3
1 -2 0
2 2
0 1
2 0
3 4
0 -2 -3 1
-1 4 0 -2
1 -2 -3 0
Sample Output
2
1
2*/
public class MagicGrid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0){
            int m = sc.nextInt();
            int n = sc.nextInt();
            int[][] a = new int[m][n];
            for(int i =0;i<m;i++){
                for(int j =0;j<n;j++){
                    a[i][j] = sc.nextInt();
                }
            }
            System.out.println(calculateMinHealth(a,m,n));

        }
    }

    private static int calculateMinHealth(int[][] a, int m, int n) {
        int memo[][] = new int[m][n];
        memo[m-2][n-1] =1;
        memo[m-1][n-2] =1;
        //can find last row
        for(int i = n-3;i>=0;i--){
            memo[m-1][i] = memo[m-1][i+1] - a[m-1][i+1];
        }
        //can find last col
        for(int i = m-3;i>=0;i--){
            memo[i][n-1] = memo[i+1][n-1] - a[i+1][n-1];
        }

        for(int i = m-2;i>=0;i--){
            for(int j =n-2;j>=0;j--){
                //memo[i][j] = Math.min(m[i+1][j]-a[i+1][j],m[i][j+1]-a[i][j+1]); if min is >1 else take max value
                memo[i][j] = Math.min(memo[i+1][j]-a[i+1][j],memo[i][j+1]-a[i][j+1]) > 0 ?  Math.min(memo[i+1][j]-a[i+1][j],memo[i][j+1]-a[i][j+1]): 1;
            }
        }
        return memo[0][0];
    }
}
