package CompetitiveProgrammingQuestions.dynamicProgramming2;

import java.util.Scanner;

public class MibnCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp =new int[n+1];
        System.out.println(getMinSquares(n,dp));
    }
    // Returns count of minimum squares that sum to n
    static int getMinSquares(int n,int[] dp)
    {
        // base cases
        if (n <= 3)
            return n;

        // getMinSquares rest of the table using recursive
        // formula
        int res = n; // Maximum squares required is
        // n (1*1 + 1*1 + ..)

        // Go through all smaller numbers
        // to recursively find minimum
        if(dp[n] != 0){
            res = dp[n];
        }else {
            for (int x = 1; x * x <= n; x++) {
                res = Math.min(res, 1 + getMinSquares(n - x * x,dp));
            }
            dp[n] =res;
        }
        return res;
    }

    private static int mincount(int n) {
        //if  n is a perfect square then return 1
        if(isPerfectSquare(n) ){
            return 1;
        }
        int sqrt = (int)Math.sqrt((double) n);
        return 1+ mincount(n-sqrt*sqrt);
    }

    private static boolean isPerfectSquare(int n) {
        int sqrt = (int)Math.sqrt((double) n);
        if(sqrt*sqrt == n){
            return true;
        }
        return false;
    }
}
