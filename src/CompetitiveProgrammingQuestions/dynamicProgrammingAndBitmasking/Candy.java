package CompetitiveProgrammingQuestions.dynamicProgrammingAndBitmasking;

import java.util.Scanner;
/**
 *Candy
 * Send Feedback
 * Gary is a teacher at XYZ school. To reward his N students he bought a packet of N candies all with different flavours. But the problem is some students like certain flavour while some doesn't. Now Gary wants to know the number of ways he can distribute these N candies to his N students such that every student gets exactly one candy he likes.
 * Input Format :
 * Line 1 : An integer N (1<= N <= 16) denoting number of students and candies.
 * Next N lines : N integers describing the preferences of one student. 1 at i'th (0 <= i < N) position denotes that this student likes i'th candy , 0 means he doesn't.
 * Assume input to be 0-indexed based.
 * Output Format :
 * Return the number of ways Gary can distribute these N candies to his N students such that every student gets exactly one candy he likes.``
 * Sample Input:
 * 3
 * 1 1 1
 * 1 1 1
 * 1 1 1
 * Sample Output:
 * 6
 */

public class Candy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in );
        int n = sc.nextInt();
        int like[][]=new int[n][n];
        //here i repesent the candy number, and j represent the student
        for(int i =0;i<n;i++){
            for(int j =0;j<n;j++){
                like[i][j] =sc.nextInt();
            }
        }
        System.out.println(solve(like,n));
    }

    private static long solve(int[][] like, int n) {
        //lets take a mask mask of n bits
        int mask = 0; //initialize all bits with 0
        // also take a dp[] of size 2^n and initialze with -1
        long[] dp = new long[1<<n];
        for(int i =0;i<1<<n;i++){
            dp[i] =-1;
        }

        return count(like,n,0,mask,dp);
    }

    private static long count(int[][] like, int n,int s, int mask, long[] dp) {
        //base case is if all the students have got candy,  then there are 0 ways possible now, s==0...n-1
        //here s represents the no of students who have got the candy
        if(s>=n){
            return 1;
        }
        if(dp[mask]!=-1){
            return dp[mask];
        }
        long count=0;
        for(int i =0;i<n;i++){
            //check if ith bit is unset and the student likes that candy ,
            if((mask & (1 << i)) == 0 && like[i][s]==1){
              count +=  count(like,n,s+1,mask|(1<<i),dp);
            }
        }
        dp[mask] =count;
        return dp[mask];

    }
}
