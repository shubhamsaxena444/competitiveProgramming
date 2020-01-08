package CompetitiveProgrammingQuestions.greedy;
/*Activity Selection
Send Feedback
You are given n activities with their start and finish times. Select the maximum number of activities that can be performed by a single person, assuming that a person can only work on a single activity at a time.
Input
The first line of input contains one integer denoting N.
Next N lines contains two space separated integers denoting the start time and finish time for the ith activity.

Output
Output one integer, the maximum number of activities that can be performed
Constraints
1 ≤ N ≤ 10^6
1 ≤ ai, di ≤ 10^9
Sample Input
6
1 2
3 4
0 6
5 7
8 9
5 9
Sample Output
4*/
import java.util.Scanner;
import java.util.*;
public class ActivitySelectionProblem {


    private static class pair{


        int s;
        int e;
        pair(int s,int e){
            this.s=s;
            this.e=e;
        }
    }
    public static void main(String[] args) {
        // Write your code here
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        pair a[] =new pair[n];
        for(int i  =0;i<n;i++){
            a[i] =new pair(sc.nextInt(),sc.nextInt());
        }
        Arrays.sort(a, new Comparator<pair>() {
            @Override
            public int compare(pair o1, pair o2) {
                return o1.e-o2.e;
            }
        });
//        int m=0;
//        for(int i = 0;i<n;i++){
//            m+= Math.pow(2,i)*a[n-1-i];
//        }
        int count =1 ;
        int end_last_act = a[0].e;
        for(int i =1;i<n;i++){
            if(end_last_act < a[i].s){
                count++;
                end_last_act = a[i].e;
            }
        }
        System.out.println(count);
    }
}
