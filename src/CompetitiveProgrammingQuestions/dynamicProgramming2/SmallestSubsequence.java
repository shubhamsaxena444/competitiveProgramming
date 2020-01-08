package CompetitiveProgrammingQuestions.dynamicProgramming2;

import java.util.Scanner;
/*Smallest Super-Sequence
Send Feedback
Given two strings S and T, find and return the length of their smallest super-sequence.
A shortest super sequence of two strings is defined as the shortest possible string containing both strings as subsequences.
Note that if the two strings do not have any common characters, then return the sum of lengths of the two strings.
Input Format:
Line 1 : A string
Line 2: Another string
Output Format:
Length of the smallest super-sequence of given two strings.
Sample Input:
ab
ac
Sample Output:
3
Sample Output Explanation:
Their smallest super-sequence can be "abc" which has length=3.*/
public class SmallestSubsequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        int dp[][] = new int[s1.length()+1][s2.length()+1];
        for(int i =0;i<=s1.length();i++){
            for(int j =0;j<=s2.length();j++){
                dp[i][j] = -1;
            }
        }
        System.out.println(smallestSubsequenceNotInOther(s1,0,s1.length(),s2,0,s2.length(),dp));

    }

    private static int smallestSubsequence(String s1, int l1, String s2, int l2,int[][] dp) {
        //base case
        if(l1 == 0 && l2 == 0)return 0;
        if(l1 == 0) return l2;
        if(l2 == 0)return l1;

        if(dp[l1][l2]>-1)return dp[l1][l2];
        if(s1.charAt(l1-1) ==s2.charAt(l2-1)){
            dp[l1][l2]= 1 + smallestSubsequence(s1,l1-1,s2,l2-1,dp);
            return dp[l1][l2];
        }
            dp[l1][l2]= Math.min(1+ smallestSubsequence(s1,l1-1,s2,l2,dp),1+ smallestSubsequence(s1,l1,s2,l2-1,dp));
        return dp[l1][l2];
    }

    private static int smallestSubsequenceNotInOther(String s1, int i1,int l1, String s2,int i2, int l2,int[][] dp) {
        //base case
        if(l1 == 0)return 1000;
        if( l2 <= 0)return 1;
//        if(l1 == 0) return 1;
//        if(l2 == 0)return 1;

        if(dp[l1][l2]>-1)return dp[l1][l2];

        int k;
        for (k=0; k < l2; k++)
            if (s2.charAt(k) == s1.charAt(l1-1))
                break;

        if(k == -1){

            return 1;
        }else {
            dp[l1][l2] = Math.min(1 + smallestSubsequence(s1, l1 - 1, s2, l2 - k -1, dp),
                     smallestSubsequence(s1, l1 - 1, s2, l2 , dp));
        }
        return dp[l1][l2];
    }

    private static int find(String s2, int n, char c) {
        while(s2.charAt(n) != c)n--;
        return n;
    }
}
