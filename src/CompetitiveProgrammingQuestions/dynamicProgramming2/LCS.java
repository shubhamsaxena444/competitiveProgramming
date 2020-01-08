package CompetitiveProgrammingQuestions.dynamicProgramming2;

import java.util.Scanner;
/*LCS - Problem
Send Feedback
Given 2 strings of S1 and S2 with lengths m and n respectively, find the length of longest common subsequence.
A subsequence of a string S whose length is n, is a string containing characters in same relative order as they are present in S, but not necessarily contiguous. Subsequences contain all the strings of length varying from 0 to n. E.g. subsequences of string "abc" are - "",a,b,c,ab,bc,ac,abc.
Input Format :
Line 1 : String S1
Line 2 : String s2
Output Format :
Line 1 : Length of lcs
Sample Input :
adebc
dcadb
Sample Output :
3*/
public class LCS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        System.out.println(lcs(s1,s2));
    }

    private static int lcs(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
//        System.out.println(s1);
//        System.out.println(s2);
        int dp[][] = new int[m+1][n+1];
        //let dp represent the first i chars of s1 and first j chars of s2
        //if i ==0 , or j==0 ,then dp[i][j]  =0;
        for(int i =1;i<=m;i++){
            for(int j =1;j<=n;j++){
                if(s1.charAt(m-i) == s2.charAt(n-j)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        print(dp,m,n);
        return dp[m][n];
    }

    private static void print(int[][] dp,int m,int n) {
        for(int i =0;i<=m;i++){
            for(int j =0;j<=n;j++) {
                System.out.print(dp[i][j] + " " );
            }
            System.out.println();
        }
    }
}
