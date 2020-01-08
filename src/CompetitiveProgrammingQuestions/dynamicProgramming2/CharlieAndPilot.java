package CompetitiveProgrammingQuestions.dynamicProgramming2;

import java.util.Scanner;
/*
* 'Charlie and Pilots
Send Feedback
Charlie acquired airline transport company and to stay in business he needs to lower the expenses by any means possible. There are N pilots working for his company (N is even) and N/2 plane crews needs to be made. A plane crew consists of two pilots - a captain and his assistant. A captain must be older than his assistant. Each pilot has a contract granting him two possible salaries - one as a captain and the other as an assistant. A captain's salary is larger than assistant's for the same pilot. However, it is possible that an assistant has larger salary than his captain. Write a program that will compute the minimal amount of money Charlie needs to give for the pilots' salaries if he decides to spend some time to make the optimal (i.e. the cheapest) arrangement of pilots in crews.
Input
The first line of input contains integer N, 2 ≤ N ≤ 10,000, N is even, the number of pilots working for the Charlie's company. The next N lines of input contain pilots' salaries. The lines are sorted by pilot's age, the salaries of the youngest pilot are given the first. Each of those N lines contains two integers separated by a space character, X i Y, 1 ≤ Y < X ≤ 100,000, a salary as a captain (X) and a salary as an assistant (Y).
Output
The first and only line of output should contain the minimal amount of money Charlie needs to give for the pilots' salaries.
Sample Input
4
5000 3000
6000 2000
8000 1000
9000 6000
Sample Output
19000 */
public class CharlieAndPilot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p[] = new int[n];
        int a[] = new int[n];

        for(int i =0;i<n;i++){
            p[i] = sc.nextInt();
            a[i] = sc.nextInt();
        }
        //x = diff between p and ass
        int dp[][] = new int[n/2+1][n/2+1];
        for (int i = 0; i <= n/2; i++) {
            for (int j = 0; j <=n/2; j++) {
                dp[i][j] = -1;
            }
        }
    System.out.println(finMinSalary(p,a,0,n,0,dp));


    }



    private static int finMinSalary(int[] p, int[] a,int assCount, int n,int pilotCount,int[][] dp) {
        if( assCount>n/2 || pilotCount > n/2) return 0;

        if(  dp[assCount][pilotCount] > -1 ){
            return  dp[assCount][pilotCount];
        }

        if( (assCount ==n/2 && pilotCount ==n/2)){
            return 0;
        }

        int curIndex = assCount+pilotCount;
        if(assCount == pilotCount  ){

            //now i can only place ass  at cur index

            dp[assCount][pilotCount]= a[curIndex] + finMinSalary(p,a,assCount+1,n,pilotCount,dp);
        }else if(assCount == n/2){
            //now i cant place closed brack at cur index
            dp[assCount][pilotCount]= p[curIndex] + finMinSalary(p,a,assCount,n,pilotCount+1,dp);
        }else{
            //i can place open or closed
            dp[assCount][pilotCount]= Math.min(a[curIndex] + finMinSalary(p,a,assCount+1,n,pilotCount,dp),
                    p[curIndex] + finMinSalary(p,a,assCount,n,pilotCount+1,dp) );
        }
        return dp[assCount][pilotCount];
    }
    private static void printDP(int n, int[][] dp) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n/2; j++) {
                System.out.print(dp[i][j] +"  " );
            }
            System.out.println();
        }
    }
    static int var=0;
}
