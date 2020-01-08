package CompetitiveProgrammingQuestions.dynamicProgramming2;

import java.util.Scanner;

public class BalancingBrackets {
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
