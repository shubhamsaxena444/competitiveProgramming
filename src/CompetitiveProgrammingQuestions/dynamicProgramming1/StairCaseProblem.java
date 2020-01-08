package CompetitiveProgrammingQuestions.dynamicProgramming1;
/*StairCase Problem
Send Feedback
A child is running up a staircase with n steps and can hop either 1 step, 2 steps or 3 steps at a time. Implement a method to count how many possible ways the child can run up to the stairs. You need to return all possible number of ways.
Time complexity of your code should be O(n).
Input format :
Integer n (No. of steps)
Constraints :
n <= 70
Sample Input 1:
4
Sample Output 1:
7*/
public class StairCaseProblem {


    public static long staircase(int n){

        /* Your class should be named Solution.
         * Don't write main() function.
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
//create an array of size n+1
        long m[] = new long[n+1];
        //if n ==0 or 1, then number of ways =1
        if(n ==0 || n==1){

            return 1;
        }
        m[0] =1;
        m[1] =1;
        m[2] =2;
        for(int i=3;i<n+1;i++){
            m[i] = m[i-1] + m[i-2] +m[i-3];
        }

        return m[n];
    }

}
