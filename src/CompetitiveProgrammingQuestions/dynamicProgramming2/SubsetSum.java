package CompetitiveProgrammingQuestions.dynamicProgramming2;

import java.util.Scanner;
/*
* Subset Sum - Problem
Send Feedback
Given a set of n integers, find if a subset of sum k can be formed from the given set. Print Yes or No.

Input Format
First line contains a single integer n (1<=n<=1000)
Second line contains n space separated integers (1<=a[i]<=1000)
Last line contains a single positive integer k (1<=k<=1000)
Output Format
Output Yes if there exists a subset whose sum is k, else output No.
Sample Input
3
1 2 3
4
Sample Output
YES*/
public class SubsetSum {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int w[] = new int[n];
            for (int i = 0; i < n; i++) {
                w[i] = sc.nextInt();
            }

            int W = sc.nextInt();
            if (function2(n, W, w) == true){
                System.out.println("Yes");
        }else{
           System.out.println("No");
       }

    }

    private static boolean function(int n, int W, int[] w) {
        boolean dp[][] =new boolean[n+1][W+1];
        //if w==0, first col will be 1, n==0, first row would be 0

        //rest dp[i][j] represent max value with i elements and j weights

            for(int i =0;i<=n;i++)
                dp[i][0]=true;
        for(int i =1;i<=n;i++){
            for(int j =1;j<=W;j++){
                //if weight of cur element is <= W (remaining weight), then there will be tow secenes
                if(w[i-1] <= j){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-w[i-1]];
                }else{
                    dp[i][j] =dp[i-1][j];
                }
            }
        }
        return dp[n][W];
    }

    private static boolean function2(int n, int W, int[] w) {
        boolean dp[][] =new boolean[2][W+1];
        //if w==0, first col will be 1, n==0, first row would be 0

        //rest dp[i][j] represent max value with i elements and j weights


        for(int i =0;i<=1;i++)
            dp[i][0]=true;
        int flag =0;
        for(int i =1;i<=n;i++){
            for(int j =1;j<=W;j++){
                //if weight of cur element is <= W (remaining weight), then there will be tow secenes
                if(w[i-1] <= j){
                    dp[1-flag][j] = dp[flag][j] || dp[flag][j-w[i-1]];
                }else{
                    dp[1-flag][j] =dp[flag][j];
                }
            }
            flag = 1-flag;
        }
        return dp[flag][W];
    }
}
