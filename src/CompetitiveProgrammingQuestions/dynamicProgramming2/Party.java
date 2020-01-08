package CompetitiveProgrammingQuestions.dynamicProgramming2;

import java.util.Scanner;

/*
* PARTY - Problem
Send Feedback
You just received another bill which you cannot pay because you lack the money.
Unfortunately, this is not the first time to happen, and now you decide to investigate the cause of your constant monetary shortness. The reason is quite obvious: the lion's share of your money routinely disappears at the entrance of party localities.
You make up your mind to solve the problem where it arises, namely at the parties themselves. You introduce a limit for your party budget and try to have the most possible fun with regard to this limit.
You inquire beforehand about the entrance fee to each party and estimate how much fun you might have there. The list is readily compiled, but how do you actually pick the parties that give you the most fun and do not exceed your budget?
Write a program which finds this optimal set of parties that offer the most fun. Keep in mind that your budget need not necessarily be reached exactly. Achieve the highest possible fun level, and do not spend more money than is absolutely necessary.
Input
The first line of the input specifies your party budget and the number n of parties.

The following n lines contain two numbers each. The first number indicates the entrance fee of each party. Parties cost between 5 and 25 francs. The second number indicates the amount of fun of each party, given as an integer number ranging from 0 to 10.

The budget will not exceed 500 and there will be at most 100 parties. All numbers are separated by a single space.

There are many test cases. Input ends with 0 0.
Output
For each test case your program must output the sum of the entrance fees and the sum of all fun values of an optimal solution. Both numbers must be separated by a single space.
Sample Input
50 10
12 3
15 8
16 9
16 6
10 2
21 9
18 4
12 4
17 8
18 9

50 10
13 8
19 10
16 8
12 9
10 2
12 8
13 5
15 5
11 7
16 2

0 0
Sample Output
49 26
48 32*/
public class Party {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int W ;
        int n ;
        do {
            W = sc.nextInt();
            n = sc.nextInt();

            int w[] = new int[n];
            int v[] = new int[n];
            for (int i = 0; i < n; i++) {
                w[i] = sc.nextInt();
                v[i] = sc.nextInt();
            }
            if(W !=0 && n!= 0)
           function(n, W, w, v);
        }while(W !=0 && n!= 0);
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
            int maxW = W;
            while(dp[n][W] == dp[n][maxW-1]){maxW--;}
           // printDP(dp,n,W);
            System.out.println(maxW+" " + dp[n][W]);
            return dp[n][W];
        }

    private static void printDP(int[][] dp,int n,int w) {
        for(int i =0;i<=n;i++){
            for(int j =0;j<=w;j++){
                System.out.print(dp[i][j] + "    ");
            }
            System.out.println();
        }
    }
}
