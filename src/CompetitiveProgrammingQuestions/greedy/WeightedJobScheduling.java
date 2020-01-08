package CompetitiveProgrammingQuestions.greedy;

import java.util.*;

/*Weighted Job Scheduling
Send Feedback
You are given N jobs where every job is represented as:
1.Start Time
2.Finish Time
3.Profit Associated
Find the maximum profit subset of jobs such that no two jobs in the subset overlap.
Input
The first line of input contains one integer denoting N.
Next N lines contains three space separated integers denoting the start time, finish time and the profit associated with the ith job.
Output
Output one integer, the maximum profit that can be achieved.
Constraints
1 ≤ N ≤ 10^6
1 ≤ ai, di, p ≤ 10^6
Sample Input
4
3 10 20
1 2 50
6 19 100
2 100 200
Sample Output
250*/
public class WeightedJobScheduling {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        job[] j = new job[n];

        for(int i =0;i<n;i++){
//            pq.add( new job(sc.nextInt(),sc.nextInt(),sc.nextInt()));
            j[i] = new job(sc.nextInt(),sc.nextInt(),sc.nextInt());
        }

        Arrays.sort(j, new Comparator<job>() {
            @Override
            public int compare(job o1, job o2) {
                return o1.e - o2.e;
            }
        });
        //let dp[i] denote the max profit till i
        //i can either consider i or not, if i consider i , then i will need to search for last job with end<my s
        int[] dp = new int[n];
        dp[0] = j[0].p;
        for(int i =1;i<n;i++){
            int lastJobProfit =0;
            // int jj =i-1;
            // while(jj>=0 && j[jj].e>j[i].s){
            //     jj--;
            // }
            int jj =  bs(j,0,i-1,j[i].s);
            if(jj>=0) {
                lastJobProfit = dp[jj];
            }
            dp[i] = Math.max(dp[i-1],j[i].p+lastJobProfit);
        }
        System.out.println(dp[n-1]);
    }

    private static int bs(job[] a, int i, int j,int k) {
        if(j<i){
            return -1;
        }else if(j==i && a[i].e <= k){
            return i;
        }
        int m = i + (j-i)/2;
        if(a[m].e <= k && a[m+1].e > k){
            return  m;
        }else if(a[m].e <= k && a[m+1].e <= k){
            return bs(a,    m+1,j,k);
        }else{
            return bs(a,i,m-1,k);
        }
    }

    static class job{
        int s;
        int e;
        int p;
        job(int s, int e, int p){
            this.s=s;
            this.e=e;
            this.p=p;
        }
    }
}
