package CompetitiveProgrammingQuestions.dynamicProgramming2;

import java.util.Scanner;

public class Stock {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
     while(q-->0){
        int k = sc.nextInt();
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
         int MaxProfit = Integer.MIN_VALUE;
        int dp[][] = new int[n][k+1];
         for (int i = 0; i < n; i++) {
             for (int j = 0; j <= k; j++) {
                 dp[i][j] = -1;
             }
         }
         System.out.println(function(a,0,k,dp));
     }

    }

    private static int function(int[] a,int startingIndex,  int k,int[][] dp ) {
        int n =a.length;
        if(k<=0){
            return 0;
        }
        if(startingIndex>=n-1 || n<2){
            return 0;
        }

        if( dp[startingIndex][k] >-1 ){
            return dp[startingIndex][k];
        }
        int MaxProfit = Integer.MIN_VALUE;
        //for every possible buy sell, will recusre with k-1 transactions left
        for(int buy = startingIndex;buy<n-1;buy++){
            for(int sell = buy+1;sell<n;sell++){
                if(a[buy]<a[sell]){
                    int buySellDone =a[sell]-a[buy] + function(a,sell+1,k-1,dp);
                   // int buySellNotDone =function(a,sell+1,k,dp);
                    MaxProfit = Math.max(MaxProfit,buySellDone);
                }
            }
        }
        dp[startingIndex][k]=  MaxProfit == Integer.MIN_VALUE ? 0 : MaxProfit;
        return dp[startingIndex][k];
    }
}
