package CompetitiveProgrammingQuestions.dynamicProgramming2;

import java.util.Scanner;

/*
* Trader Profit
Send Feedback
Mike is a stock trader and makes a profit by buying and selling stocks. He buys a stock at a lower price and sells it at a higher price to book a profit. He has come to know the stock prices of a particular stock for n upcoming days in future and wants to calculate the maximum profit by doing the right transactions (single transaction = buying + selling). Can you help him maximize his profit?
Note: A transaction starts after the previous transaction has ended. Two transactions can't overlap or run in parallel.
The stock prices are given in the form of an array A for n days.
Given the stock prices and a positive integer k, find and print the maximum profit Mike can make in at most k transactions.
Input Format
The first line of input contains an integer q denoting the number of queries.

The first line of each test case contains a positive integer k, denoting the number of transactions.

The second line of each test case contains a positive integer n, denoting the length of the array A.

The third line of each test case contains n space-separated positive integers, denoting the prices of each day in the array A.
Constraints
1<=q<=100

0<k<10

2<=n<=30

0<=elements of array A<=1000
Output Format
For each query print the maximum profit earned by Mike on a new line.
Sample Input
3
2
6
10 22 5 75 65 80
3
4
20 580 420 900
1
5
100 90 80 50 25
Sample Output
87
1040
0*/
public class TraderProfit {

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
                    //int buySellNotDone =function(a,sell+1,k,dp);
                    MaxProfit = Math.max(MaxProfit,buySellDone);
                }
            }
        }
        dp[startingIndex][k]=  MaxProfit == Integer.MIN_VALUE ? 0 : MaxProfit;
        return dp[startingIndex][k];
    }

}
