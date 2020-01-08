package CompetitiveProgrammingQuestions.dynamicProgramming2;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
/*
* Distinct Subsequences
Send Feedback
Given a string, count the number of distinct subsequences of it ( including empty subsequence ). For the uninformed, A subsequence of a string is a new string which is formed from the original string by deleting some of the characters without disturbing the relative positions of the remaining characters.
For example, "AGH" is a subsequence of "ABCDEFGH" while "AHG" is not.
Input
First line of input contains an integer T which is equal to the number of test cases. You are required to process all test cases. Each of next T lines contains a string s.
Output
Output consists of T lines. Ith line in the output corresponds to the number of distinct subsequences of ith input string. Since, this number could be very large, you need to output ans%1000000007 where ans is the number of distinct subsequences.
Constraints and Limits
T ≤ 100, length(S) ≤ 100000

All input strings shall contain only uppercase letters.
Sample Input
3
AAA
ABCDEFG
CODECRAFT
Sample Output
4
128
496*/
public class DistinctSubsequences {
    public static void main(String[] args) throws IOException {

//        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
//         sc.readLine();
        String[] s = new String[t];
        for (int i = 0; i < t; i++) {
            s[i] = sc.nextLine();
        }
        for (int i = 0; i < t; i++) {
            System.out.println(countSub(s[i]));
        }
    }
    static final int MAX_CHAR = 256;
    static final int mod = 1000000007;

    // Returns count of distinct sunsequences of str.
    static int countSub(String str)
    {
        // Create an array to store index
        // of last
        int[] last = new int[MAX_CHAR];
        Arrays.fill(last, -1);

        // Length of input string
        int n = str.length();

        // dp[i] is going to store count of distinct
        // subsequences of length i.
        int[] dp = new int[n+1];

        // Empty substring has only one subsequence
        dp[0] = 1;

        // Traverse through all lengths from 1 to n.
        for (int i=1; i<=n; i++)
        {
            // Number of subsequences with substring
            // str[0..i-1]
            dp[i] = (2*dp[i-1])%mod;

            // If current character has appeared
            // before, then remove all subsequences
            // ending with previous occurrence.
            if (last[(int)str.charAt(i-1)] != -1)
                dp[i] = (dp[i]%mod - dp[last[(int)str.charAt(i-1)]%mod ] +mod)%mod;

            // Mark occurrence of current character
            last[(int)str.charAt(i-1)] = (i-1);
        }

        return dp[n];
    }

    private static int count(String s, int i,int l) {
        if(i ==l)return 0;
        if(i ==l-1)return 2;
        int repitition = countOcc(s,s.charAt(i),0,i);
        return 2* count(s,i+1,l) - repitition;
    }

    private static int countOcc(String s, char charAt, int i, int i1) {
        int count =0;
        while(i!=i1){
            if(s.charAt(i) == charAt){
                count++;
            }
            i++;
        }
        return count;
    }
}
