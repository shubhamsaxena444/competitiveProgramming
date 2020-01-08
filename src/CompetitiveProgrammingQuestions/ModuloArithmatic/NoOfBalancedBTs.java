package CompetitiveProgrammingQuestions.ModuloArithmatic;
/*Number Of Balanced BTs
Send Feedback
Given an integer h, find the possible number of balanced binary trees of height h. You just need to return the count of possible binary trees which are balanced.
This number can be huge, so return output modulus 10^9 + 7.
Write a simple recursive solution.
Input Format :
Integer h
Output Format :
Count % 10^9 + 7
Input Constraints :
1 <= h <= 40
Sample Input 1:
3
Sample Output 1:
15
Sample Input 2:
4
Sample Output 2:
315*/
public class NoOfBalancedBTs {

    public static int balancedTreesOfHeightH(int height){
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        if(height == 0){
            return 1;
        }else if(height ==1){
            return 1;
        }
        int m = (int)Math.pow(10,9) +7;

        int x = balancedTreesOfHeightH(height-1);
        int y = balancedTreesOfHeightH(height-2);
        //x^2 +2x^y is the answer;
        long r1 = (long)x*x;
        long r2 =  (long)x*y*2;
        int a1= (int)(r1%m);
        int a2= (int)(r2%m);

        return ( a1+a2 )%m;
    }
}
