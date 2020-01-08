package CompetitiveProgrammingQuestions.BitManipulation;

import java.util.Scanner;

/*Find first set bit
Send Feedback
You are given an integer N. You need to return an integer M, in which only one bit is set which at position of lowest set bit of N (from right to left).
Input Format :
Integer N
Output Format :
Integer M
Sample Input 1 :
7
Sample Output 1 :
1
Sample Input 2 :
12
Sample Output 2 :
4*/
public class FindFirstSetBit {
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
