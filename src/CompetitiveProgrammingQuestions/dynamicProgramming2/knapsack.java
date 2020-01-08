package CompetitiveProgrammingQuestions.dynamicProgramming2;

import java.util.Scanner;
/*
* Knapsnack - Problem
Send Feedback
A thief robbing a store and can carry a maximal weight of W into his knapsack. There are N items and ith item weigh wi and is of value vi. What is the maximum value V, that thief can take ?
Space complexity should be O(n).
Input Format :
Line 1 : N i.e. number of items
Line 2 : N Integers i.e. weights of items separated by space
Line 3 : N Integers i.e. values of items separated by space
Line 4 : Integer W i.e. maximum weight thief can carry
Output Format :
Line 1 : Maximum value V
Constraints
1 <= N <= 10^4
1<= wi <= 100
1 <= vi <= 100
Sample Input 1 :
4
1 2 4 5
5 4 8 6
5
Sample Output :
13*/
public class knapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int w[] =new int[n];
        int v[] =new int[n];
        for(int i =0;i<n;i++){
            w[i] =sc.nextInt();
        }
        for(int i =0;i<n;i++){
            v[i] =sc.nextInt();
        }
        int W = sc.nextInt();
        System.out.println(function(n,W,w,v));
    }

    private static int function(int n, int W, int[] w, int[] v) {
        int dp[][] =new int[n+1][W+1];
        //if w==0, first col will be 0, n==0, first row would be 0
        //rest dp[i][j] represent max value with i elements and j weights
        for(int i =1;i<=n;i++){
            for(int j =1;j<=W;j++){
                //if weight of cur element is <= W (remaining weight), then there will be tow secenes
                if(w[i-1] <= j){
                    dp[i][j] = Math.max(dp[i-1][j] , v[i-1] + dp[i-1][j-w[i-1]]);
                }else{
                    dp[i][j] =dp[i-1][j];
                }
            }
        }
    return dp[n][W];
    }
}
