package CompetitiveProgrammingQuestions.BitManipulation;

import java.util.Scanner;

/*Turn off 1st set bit
Send Feedback
You are given an integer Ni. You need to make first set bit of binary representation of N to 0 and return the updated N.
Counting of bits start from 0 from right to left.
Input Format :
Integer N
Output Format :
Updated N
Sample Input 1 :
4
Sample Output 1 :
0
Sample Input 2 :
12
Sample Output 2 :
8*/
public class TurnOffFirstSetBit {
    public static void main(String[] args) {
        /* Your class should be named Main
         * Read input as specified in the question.
         * Print output as specified in the question.
         */

        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();

        System.out.println(n&(n-1));
    }
}
