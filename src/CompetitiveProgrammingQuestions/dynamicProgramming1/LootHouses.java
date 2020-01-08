package CompetitiveProgrammingQuestions.dynamicProgramming1;

import java.util.Scanner;
/*Loot Houses
Send Feedback
A thief wants to loot houses. He knows the amount of money in each house. He cannot loot two consecutive houses. Find the maximum amount of money he can loot.
Input Format
Line 1 : An integer N
Line 2 : N spaced integers denoting money in each house
Output Format
 Line 1 : Maximum amount of money looted
Input Constraints
1 <= n <= 10^4
1 <= A[i] < 10^4
Sample Input :
6
5 5 10 100 10 5
Sample Output 1 :
110*/
public class LootHouses {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n =s.nextInt();
        int a[] = new int[n];
        for(int i =0;i<n;i++){
            a[i] =s.nextInt();
        }
        System.out.println(maxContiguousSubarrayWithAlteration(a,n));
    }

    private static int maxContiguousSubarrayWithAlteration(int[] a, int n) {
    //lets create a memo array m of size n+1, such that m[i] represents maximus sum till i

       if(n==0){
           return 0;
       }
        int m[] = new int[n+1];
        //i can either chose current element in my result, if i do so, then i can't use m[i-1], else i can
        m[0] = 0;
        m[1] = a[0];
        for(int i =2;i<=n;i++){
            m[i] = Math.max(a[i-1]+m[i-2],m[i-1]);
        }
        return m[n];
    }
}
