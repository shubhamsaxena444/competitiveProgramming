package CompetitiveProgrammingQuestions.dynamicProgramming1;

import java.util.Scanner;
/*Maximum Sum Rectangle
Send Feedback
Given a 2D array, find the maximum sum rectangle in it. In other words find maximum sum over all rectangles in the matrix.
Input
First line contains 2 numbers n and m denoting number of rows and number of columns. Next n lines contain m space separated integers denoting elements of matrix nxm.
Output
Output a single integer, maximum sum rectangle.
Constraints
1<=n,m<=100
Sample Input
4 5
1 2 -1 -4 -20
-8 -3 4 2 1
3 8 10 1 3
-4 -1 1 7 -6
Sample Output
29*/
public class MaximumSumRectangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int a[][] = new int[m][n];
        for(int i =0;i<m;i++){
            for(int  j =0;j<n;j++){
                a[i][j] = sc.nextInt();
            }
        }
        System.out.println(maxSum(a,m,n));
    }

    private static int maxSum(int[][] a, int m, int n) {
        //fix i and j
        //int s[] = new int[m];
        /*for(int i =0;i<m;i++){
            s[i] = a[i][0];
        }*/
        int max =Integer.MIN_VALUE;
        for(int i =0;i<n;i++){
            int s[] = new int[m];
            for(int j =i;j<n;j++){

                //for each window of i, j, i need to find max sum subarray in s0...sn
                for(int k =0;k<m;k++) {
                    s[k] += a[k][j];
                }
                //find max sumContiguos sum using kadane's
                max = Math.max(max,maxSubArraySum(s));
            }

        }
            return max;
    }
    static int maxSubArraySum(int a[])
    {
        int size = a.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;

        for (int i = 0; i < size; i++)
        {
            max_ending_here = max_ending_here + a[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
    }
}
