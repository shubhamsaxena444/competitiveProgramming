package CompetitiveProgrammingQuestions.dynamicProgramming1;

import java.util.Scanner;
/*
Maximum Square Matrix With All Zeros
Send Feedback
Given a n*m matrix which contains only 0s and 1s, find out the size of maximum square sub-matrix with all 0s. You need to return the size of square with all 0s.
Input format :
Line 1 : n and m (space separated positive integers)
Next n lines : m elements of each row (separated by space).
Output Format:
Line 1 : Size of maximum square sub-matrix
Sample Input :
3 3
1 1 0
1 1 1
1 1 1
Sample Output :
1
* */
public class MaxSquareSubMatrix {
    /*
    Given a n*m matrix which contains only 0s and 1s, find out the size of
    maximum square sub-matrix with all 0s. You need to return the size of square with all 0s.*/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m =sc.nextInt();
        int[][] a = new int[n][m];
        for(int i =0;i<n;i++){
            for(int j =0;j<m;j++){
                a[i][j] =sc.nextInt();

            }
        }

        System.out.println(findMaxSquareWithAllZeros(a));

    }

    private static int findMaxSquareWithAllZeros(int[][] a) {
        int n = a.length;
        int m =a[0].length;

        //transpose the matrix and find the max subarray of 1s
        for(int i =0;i<n;i++){
            for(int j =0;j<m;j++){
                a[i][j] ^= 1;
            }
        }
        // create another array such that m[i][j] represents sum of 1s subarray ending at i,j
        int[][] memo = new int[n][m];
        //first row and first col can be filled with a[i][j]
        for(int i =0;i<n;i++){
            memo[i][0] =a[i][0];
        }

        for(int i =0;i<m;i++){
            memo[0][i] =a[0][i];
        }

        for(int i =1;i<n;i++){
            for(int j =1;j<m;j++){
                //if left, diagonally left and up are all 1
                if(a[i][j]==1) {
                    memo[i][j] = Math.min(memo[i-1][j] , Math.min(memo[i-1][j-1] , memo[i][j-1]))+1;
                }else{
                    //even if 1 is 0
                    memo[i][j] = a[i][j];
                }
            }
        }
        int max=Integer.MIN_VALUE;
        //return max element
        for(int i =0;i<n;i++){
            for(int j =0;j<m;j++){
                max = Math.max(max,memo[i][j]);
            }
        }
        return max;
    }
}
