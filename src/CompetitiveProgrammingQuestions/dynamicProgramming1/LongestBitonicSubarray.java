package CompetitiveProgrammingQuestions.dynamicProgramming1;

import java.util.Arrays;
import java.util.Scanner;
/*Largest Bitonic Subarray
Send Feedback
You are given an array of positive integers as input. Write a code to return the length of the largest such sub-array in which the values are arranged first in strictly ascending order and then in strictly descending order.
Such a sub-array is known as bitonic sub-array. A purely increasing or purely decreasing subsequence will also be considered as a bitonic sequence with the other part empty.
Note that the elements in bitonic sub-array need not be consecutive in the given array but the order should remain same.
Input Format:
Line 1 : A positive Integer N, i.e., the size of array
Line 2 : N space-separated integers as elements of the array
Output Format:
Length of Largest Bitonic Sub-Array
Input Constraints:
1<= N <= 10^5
Sample Input 1:
6
15 20 20 6 4 2
Sample Output 1:
5
Sample Output 1 Explanation:
Here, longest Bitonic sub-array is {15, 20, 6, 4, 2} which has length = 5.
Sample Input 2:
2
1 5
Sample Output 2:
2
Sample Input 3:
2
5 1
Sample Output 3:
2*/
public class LongestBitonicSubarray {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a [] = new int[n];
        for(int i =0;i<n;i++){
            a[i] =sc.nextInt();
        }
        System.out.println(lbs(a,n));
    }

    private static int lbs(int[] a, int n) {
        int[] ml = new int[n];
        //initialise with 1 since for each i , m[i] i.e, LIS ending at i would be at least 1, ie itself
        for(int i =0;i<n;i++)
            ml[i] =1;

        for(int i=1;i<n;i++){

            //i need to check for j =0 till i-1, if there is any element a[j] smaller than a[i], if so and m[j]+1 > m[i], then update m[i]
            for(int j =0;j<i;j++){
                if(a[j] < a[i] && (ml[j] + 1) > ml[i]){
                    ml[i] =ml[j] +1;
                }
            }

        }
        //also find lis begining from right

        int[] mr = new int[n];
        //initialise with 1 since for each i , m[i] i.e, LIS ending at i would be at least 1, ie itself
        for(int i =0;i<n;i++)
            mr[i] =1;

        for(int i=n-2;i>=0;i--){

            //i need to check for j =0 till i-1, if there is any element a[j] smaller than a[i], if so and m[j]+1 > m[i], then update m[i]
            for(int j =n-1;j>i;j--){
                if(a[j] < a[i] && (mr[j] + 1) > mr[i]){
                    mr[i] =mr[j] +1;
                }
            }

        }
        //lbs = ml[i]+mr[i] -1
        for(int i =0;i<n;i++){
            ml[i] = ml[i] + mr[i] -1;
        }


        return Arrays.stream(ml).max().getAsInt();
    }
}
