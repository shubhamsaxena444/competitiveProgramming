package CompetitiveProgrammingQuestions.SearchingAndSorting;

import java.util.Scanner;

/*Find power of a number
Send Feedback
Write a program to find x to the power n (i.e. x^n). Take x and n from the user. You need to print the answer.
Input format :
Two integers x and n (separated by space)
Output Format :
x^n (i.e. x raise to the power n)
Constraints:
0 <= x <= 8, 0 <= n <= 9
Sample Input 1 :
 3 4
Sample Output 1 :
81
Sample Input 2 :
 2 5
Sample Output 2 :
32*/
public class PowerOfANumber {


    public static void main(String[] args) {
        // Write your code here
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        System.out.println(power(x,y));
    }
//     static long power(int x,  int y)
//     {
//     long res = 1; // Initialize result

//     while (y > 0) {
//         // If y is odd, multiply x with result
//         if (y%2==  1) {
//             res = res * x;
//             y-=1;
//         }else{
//         // y must be even


//          // Change x to x^2
//             res*= x*x;
//             y = y >> 1; // y = y/2
//         }
//     }
//     return res;
// }

    static int power(int x, int y)
    {
        if (y == 0)
            return 1;
        else if (y % 2 == 0)
            return power(x, y / 2) * power(x, y / 2);
        else
            return x * power(x, y / 2) * power(x, y / 2);
    }
}
