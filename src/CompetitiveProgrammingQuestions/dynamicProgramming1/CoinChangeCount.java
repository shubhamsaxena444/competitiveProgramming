package CompetitiveProgrammingQuestions.dynamicProgramming1;

import java.util.Scanner;
/*
* Coin Change Problem
Send Feedback
You are given an infinite supply of coins of each of denominations D = {D0, D1, D2, D3, ...... Dn-1}. You need to figure out the total number of ways W, in which you can make change for Value V using coins of denominations D.
Note : Return 0, if change isn't possible.
Input Format
Line 1 : Integer n i.e. total number of denominations
Line 2 : N integers i.e. n denomination values
Line 3 : Value V
Output Format
Line 1 :  Number of ways i.e. W
Constraints :
1<=n<=10
1<=V<=1000
Sample Input 1 :
3
1 2 3
4
Sample Output
4
Sample Output Explanation :
Number of ways are - 4 total i.e. (1,1,1,1), (1,1, 2), (1, 3) and (2, 2).*/
public class CoinChangeCount {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a [] = new int[n];
        for(int i =0;i<n;i++){
            a[i] =sc.nextInt();
        }
        int total = sc.nextInt();
        System.out.println(count(a,n,total));
    }
//iterative solution or bottom up solution
    private static int count(int[] a, int n, int total) {
        //create a 2d array
        int m[][] = new int[n+1][total+1];

        //mark first col emelements as 1 and first row elements as 0
        for(int i =0;i<=n;i++){
            m[i][0] =1;
        }
        for(int i =1;i<=n;i++){

            for(int j =1;j<=total;j++){

                //a[i-1] = value of denomination at 0
                if(a[i-1] <= j){
                    //i can either use this or skip this coin
                    m[i][j] = m[i][j-a[i-1]] + m[i-1][j];
                }else{
                    m[i][j] =m[i-1][j];
                }
            }

        }
        return m[n][total];
    }
}
