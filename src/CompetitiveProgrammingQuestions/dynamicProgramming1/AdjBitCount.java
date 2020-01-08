package CompetitiveProgrammingQuestions.dynamicProgramming1;
/*
* Adjacent Bit Counts
Send Feedback
For a string of n bits x1,x2,x3,...,Xn the adjacent bit count of the string (AdjBC(x)) is given by
X1*X2 + X2*X3 + X3*X4 + ... + Xn-1 * Xn
which counts the number of times a 1 bit is adjacent to another 1 bit. For example:
AdjBC(011101101) = 3
AdjBC(111101101) = 4
AdjBC(010101010) = 0
Write a program which takes as input integers n and k and returns the number of bit strings x of n bits (out of 2ⁿ) that satisfy AdjBC(x) = k. For example, for 5 bit strings, there are 6 ways of getting AdjBC(x) = 2:
11100, 01110, 00111, 10111, 11101, 11011
Input
The first line of input contains a single integer P, (1 ≤ P ≤ 1000), which is the number of data sets that follow. Each data set is a single line that contains the data set number, followed by a space, followed by a decimal integer giving the number (n) of bits in the bit strings, followed by a single space, followed by a decimal integer (k) giving the desired adjacent bit count. The number of bits (n) will not be greater than 100.
Output
For each data set there is one line of output. It contains the data set number followed by a single space, followed by the number of n-bit strings with adjacent bit count equal to k. As answer can be very large print your answer modulo 10^9+7.
Sample Input
10
1 5 2
2 20 8
3 30 17
4 40 24
5 50 37
6 60 52
7 70 59
8 80 73
9 90 84
10 100 90
Sample Output
1 6
2 63426
3 1861225
4 168212501
5 44874764
6 160916
7 22937308
8 99167
9 15476
10 23076518*/
import java.util.Scanner;

public class AdjBitCount {
    static int MOD = 1000000007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t =sc.nextInt();
        while(t-->0){
            int i =sc.nextInt();
            int n = sc.nextInt();
            int k =sc.nextInt();
            System.out.println(i+" "+function(n,k));
        }
    }

    private static int function(int n, int k) {
        int[][][] dp = new int[n+1][k+1][2];
        for(int i =0;i<=n;i++){
            for(int j=0;j<=k;j++) {
                for(int l=0;l<=1;l++) {
            dp[i][j][l] =-1;
                }
            }
        }
        return (f(n,k,0,dp)+f(n,k,1,dp))%MOD;
    }
//recursive approach
    private static int f(int n, int k, int bit,int[][][] dp) {
        if(n==1){
            if( k==0) {
                return 1;
            }else{
                return 0;
            }
        }
        if(k<0)return 0;
        if(dp[n][k][bit] == -1){
            if(bit == 1){

                 dp[n][k][bit] = (f(n-1,k,0,dp)+f(n-1,k-1,1,dp))%MOD;

             }else{
                 dp[n][k][bit] =( f(n-1,k,0,dp)+f(n-1,k,1,dp))%MOD;
             }
        }

        return dp[n][k][bit];
    }

    /*private static int f(int n, int k, int firstBit) {
        //lets keep a 3-d array of n*k*2, which stores the number of ways
        int[][][] dp = new int[n+1][k+1][2];
        //if n ==1 && k ==0 , return 1
        if(n==1){
            if( k==0) {
                return 1;
            }else{
                return 0;
            }
        }
        if(k<0)return 0;
        dp[1][0][0] =1;
        dp[1][0][1] =1;

        for(int i =1;i<=n;i++){
            for(int j=1;j<=k;j++) {
                if (firstBit == 1) {
                    dp[i][j][firstBit] = dp[i - 1][j][0] + dp[i - 1][j - 1][1];
                }else{
                    dp[i][j][firstBit] = dp[i - 1][j][0] + dp[i - 1][j][1];
                }
            }
        }
        print(dp,firstBit,n,k);
        return dp[n][k][firstBit];
    }*/
    static void print(int[][][] dp,int bit,int n,int k){
        System.out.println("bit is "+ bit );
        for(int i =0;i<=n;i++){
            for(int j =0;j<=k;j++){
                System.out.print(dp[i][j][bit] + " ");
            }
            System.out.println();
        }
    }
}
