package CompetitiveProgrammingQuestions.BitManipulation;

import java.util.Scanner;

/*Unset ith bit
Send Feedback
You are given two integers N and i. You need to make ith bit of binary representation of N to 0 and return the updated N.
Counting of bits start from 0 from right to left.
Input Format :
Two integers N and i (separated by space)
Output Format :
Updated N
Sample Input 1 :
7 2
Sample Output 1 :
3
Sample Input 2 :
12 1
Sample Output 2 :
12*/
public class UnsetithBit {
    public static void main(String[] args) {
        /* Your class should be named Main
         * Read input as specified in the question.
         * Print output as specified in the question.
         */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int i =sc.nextInt();

        System.out.println( n & (~(1 << i)));
    }
}
