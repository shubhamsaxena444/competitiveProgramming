package CompetitiveProgrammingQuestions.dynamicProgramming2;

import java.util.Scanner;
/*Edit Distance - Problem
Send Feedback
Given two strings s and t of lengths m and n respectively, find the Edit Distance between the strings. Edit Distance of two strings is minimum number of steps required to make one string equal to other. In order to do so you can perform following three operations only :
1. Delete a character

2. Replace a character with another one

3. Insert a character
Note - Strings don't contain spaces
Input Format :
Line 1 : String s
Line 2 : String t
Output Format :
Line 1 : Edit Distance value
Constraints
1<= m,n <= 20
Sample Input 1 :
abc
dc
Sample Output 1 :
2*/
public class EditDistance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for(int i =0;i<s1.length()+1;i++){
            for(int j =0;j<s2.length()+1;j++){
                dp[i][j] =-1;
            }
        }
        System.out.println(editDistance(new StringBuilder(s1),new StringBuilder(s2),s1.length(),s2.length(),dp));
    }
    //recursive
    private static int editDistance(StringBuilder s1, StringBuilder s2,int m,int n,int[][] dp) {
        if(s1.length() == s2.length() && s1 == s2){
            return 0;
        }
        if(m == 0 ){
            return n;
        }else if(n == 0){
            return m;
        }


        //whichever string is shorter, we will make it s1 make s1 equal to s2, which means all operations should be performed on s1
       /* if(n<m){
            StringBuilder temp = s1;
            s1= s2;
            s2 = temp;
            int tem = m;
            m = n;
            n = tem;
        }*/
        //if length is same, then i can try replacing,insert,delete
        //abcdef
        //bcdefg
        //actual me nahi karna... pretend karo
        if(dp[m][n] >-1){
            return dp[m][n];
        }


        if(s1.charAt(m-1) == s2.charAt(n-1)){
            dp[m][n] = editDistance(s1,s2,m-1,n-1,dp);
            return dp[m][n];
        }
        //insert
        int insert = 1+editDistance(s1,s2,m,n-1,dp);
         //delete
        int delete = 1+editDistance(s1,s2,m-1,n,dp);
        //replace
        int replace = 1+editDistance(s1,s2,m-1,n-1,dp);
        dp[m][n] = Math.min(insert,Math.min(delete,replace));
        return dp[m][n];
    }
}
