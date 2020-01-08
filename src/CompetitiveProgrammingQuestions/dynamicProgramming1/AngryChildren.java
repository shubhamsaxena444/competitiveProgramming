package CompetitiveProgrammingQuestions.dynamicProgramming1;

import java.util.Arrays;
import java.util.Scanner;
/*
* Angry Children
Send Feedback
Bill Gates is on one of his philanthropic journeys to a village in Utopia. He has N packets of candies and would like to distribute one packet to each of the K children in the village (each packet may contain different number of candies). To avoid a fight between the children, he would like to pick K out of N packets such that the unfairness is minimized.
Suppose the K packets have (x1, x2, x3,....xk) candies in them, where xi denotes the number of candies in the ith packet, then we define unfairness as
unfairness=0;
for(i=0;i<n;i++)
for(j=0;j<n;j++)
    unfairness+=abs(xi-xj)
abs(x) denotes absolute value of x.
Input Format
The first line contains an integer N.
The second line contains an integer K.
N lines follow each integer containing the candy in the ith packet.
Output Format
A single integer which will be minimum unfairness.
Constraints
2<=N<=10^5

2<=K<=N

0<= number of candies in each packet <=10^9
Sample Input
7
3
10
100
300
200
1000
20
30
Sample Output
40
Explanation
Bill Gates will choose packets having 10, 20 and 30 candies.So unfairness will be |10-20| + |20-30| + |10-30| = 40. We can verify that it will be minimum in this way.*/
public class AngryChildren {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int a[] = new int[n];
        for(int  i =0;i<n;i++){
            a[i] = sc.nextInt();
        }
        //need to sort the array and consider sliding window for k

        Arrays.sort(a);  //nlogn
        int[] prefix = new int[n];
        //prefix array
        prefix = a.clone();
        for(int i =1;i<n;i++) {
            prefix[i]+=prefix[i-1];
        }
        //for first k elements, find target(x[i]) =  minimum unfairness.
        int x[] = new int[k];
        int target=0;
        int min = Integer.MAX_VALUE;
        //target+= (xi-x0)+(xi-x1)..xi-xi-1  == i*xi -(x0+...xi-1))
//        target = k*
        for(int i =1;i<k;i++) {
            target+= (i*(a[i]) - (prefix[i-1]));
        }
        for(int i =k,j=0;i<n;i++,j++) {
            min = Math.min(min,target);
            //target = target - 2* (x1...xk-1) +(x0+xk)(k-1)
            target= target - 2*(prefix[i-1] -prefix[j]) + (a[j]+a[i])*(k-1);
        }
        System.out.println(min);
    }
}
