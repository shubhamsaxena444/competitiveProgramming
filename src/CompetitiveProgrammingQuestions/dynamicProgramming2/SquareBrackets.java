package CompetitiveProgrammingQuestions.dynamicProgramming2;

import java.util.Scanner;

/*Square Brackets
Send Feedback
You are given:
a positive integer n,
an integer k, 1<=k<=n,
an increasing sequence of k integers 0 < s1 < s2 < ... < sk <= 2n.
What is the number of proper bracket expressions of length 2n with opening brackets appearing in positions s1, s2,...,sk?
Illustration
Several proper bracket expressions:
[[]][[[]][]]
[[[][]]][][[]]
An improper bracket expression:
[[[][]]][]][[]]
There is exactly one proper expression of length 8 with opening brackets in positions 2, 5 and 7.
Task
Write a program which for each data set from a sequence of several data sets:
1. reads integers n, k and an increasing sequence of k integers from input,
2. computes the number of proper bracket expressions of length 2n with opening brackets appearing at positions s1,s2,...,sk,
3. writes the result to output.
Input
The first line of the input file contains one integer d, 1 <= d <= 10, which is the number of data sets. The data sets follow. Each data set occupies two lines of the input file. The first line contains two integers n and k separated by single space, 1 <= n <= 19, 1 <= k <= n. The second line contains an increasing sequence of k integers from the interval [1;2n] separated by single spaces.
Output
The i-th line of output should contain one integer - the number of proper bracket expressions of length 2n with opening brackets appearing at positions s1, s2,...,sk.
Sample Input
5
1 1
1
1 1
2
2 1
1
3 1
2
4 2
5 7
Sample Output
1
0
2
3
2
* */
public class SquareBrackets {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            boolean a[] = new boolean[2 * n+1];
            for (int i = 0; i < k; i++) {
                a[sc.nextInt() -1] = true;
            }
            int openCount = 0;
            int closedCount = 0;
            int[][] dp =new int[n+1][n+1];
            for(int i =0;i<=n;i++){
                for(int j =0;j<=n;j++){
                    dp[i][j] =-1;
                }
            }
            System.out.println(count(a, k, n, openCount, closedCount,dp));
        }
    }
    private static int count(boolean[] a, int k, int n, int openCount, int closedCount,int[][] dp) {
        if(closedCount>n || openCount>n)return 0;

        if(dp[openCount][closedCount] > -1)return dp[openCount][closedCount];
        if( (openCount ==n && closedCount ==n)){
            return 1;
        }else if(openCount == closedCount  || a[openCount+closedCount]){
            //now i can only place open brack at cur index
            dp[openCount][closedCount]=  count(a,k,n,openCount+1,closedCount,dp);
        }else if(openCount == n){
            //now i cant place open brack at cur index
            dp[openCount][closedCount]=  count(a,k,n,openCount,closedCount+1,dp);
        }else{
            //i can place open or closed
            dp[openCount][closedCount]= count(a,k,n,openCount,closedCount+1,dp) +
                    count(a,k,n,openCount+1,closedCount,dp);
        }
        return dp[openCount][closedCount];
    }
}
