package CompetitiveProgrammingQuestions.SearchingAndSorting;
import java.util.*;

/*
* Sorting the Skills
Send Feedback
There is a company named James Peterson & Co. The company has ‘n’ employees. The employees have skills from 0 to n-1. All the employees have distinct skills. The manager of James Peterson & Co. wants to sort the employees on the basis of their skills in ascending order. He is only allowed to swap two employees which are adjacent to each other. He is given the skills of employees in an array of size ‘n’. He can swap the skills as long as the absolute difference between their skills is 1. You need to help the manager out and tell whether it is possible to sort the skills of employees or not.
Input Format:
First Line will have an integer ‘t’ denoting the no. of test cases.
First line of each test case contains an integer ‘n’ denoting the no. of employees in the company.
Second line of each test case contains ‘n’ distinct integers in the range [0, n-1].
Output Format:
For each test case, print “Yes” if it is possible to sort the skills otherwise “No”.
Constraints:
1 <= t <= 10
1 <= n <= 10^5
Sample Input:
2
4
1 0 3 2
3
2 1 0
Sample Output:
Yes
No
Explanation:
In first T.C., [1, 0, 3, 2] -> [0, 1, 3, 2] -> [0, 1, 2, 3]
In second T.C., [2, 1, 0] -> [1, 2, 0]  OR  [2, 1, 0] -> [2, 0, 1] So, it is impossible to sort.*/
public class SortingTheSkills {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-->0){
            int n = in.nextInt();
            int a[] = new int[n];
            for(int i =0;i<n;i++){
                a[i] = in.nextInt();
            }
            System.out.println(solve(a,n));
        }
    }
    static String solve(int[] a, int n){
        //in one pass of bubble sort check if the arr is sorted or not
        for(int i =0;i<n-1;i++){
            if(a[i] > a[i+1]){
                int t = a[i];
                a[i]= a[i+1];
                a[i+1] =t;
            }
        }

        //check if it is sorted
        for(int i =0;i<n-1;i++){
            if(a[i] > a[i+1]){
                return "No";
            }

        }
        return "Yes";
    }
}
