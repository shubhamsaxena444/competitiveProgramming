package CompetitiveProgrammingQuestions.greedy;

import java.util.*;

/*Fractional Knapsack
Send Feedback
You want to paint your house. The total area of your house is D units. There are a total of N workers. The ith worker is available after time Ti, has hiring cost Xi and speed Yi. This means he becomes available for hiring from time Ti and remains available after that. Once available, you can hire him with cost Xi, after which he will start painting the house immediately, covering exactly Yi units of house with paint per time unit. You may or may not hire a worker and can also hire or fire him at any later point of time. However, no more than 1 worker can be painting the house at a given time.
Since you want the work to be done as fast as possible, figure out a way to hire the workers, such that your house gets painted at the earliest possible time, with minimum cost to spend for hiring workers.
Note: You can hire a previously hired worker without paying him again.
Input
The first line of input contains two integers "N D", the number of workers and the area of your house respectively. The ith of the next N lines denotes the ith worker, and contains three integers "Ti Xi Yi", described in the statement.
Output
Output one integer, the minimum cost that you can spend in order to get your house painted at the earliest.
Constraints
1 ≤ N, T, X, Y ≤ 10^5
1 ≤ D ≤ 10^11
Sample Input
3 3
1 1 1
2 2 2
3 1 5
Sample Output
3*/
public class FractionalKnapsack {

    public static void main(String[] args) {
        // Write your code here

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int d = sc.nextInt();
//        int t[] =new int[n];
//        int x[] =new int[n];
//        int y[] =new int[n];
        labour l[] =new labour[n];
        for(int i  =0;i<n;i++){
//            t[i] = sc.nextInt();
//            x[i] = sc.nextInt();
//            y[i] = sc.nextInt();
            l[i] = new labour(sc.nextInt(),sc.nextInt(),sc.nextInt());
        }
        Arrays.sort(l, new Comparator<labour>() {
            @Override
            public int compare(labour o1, labour o2) {
                int res=  o2.y - o1.y;
                if(o2.y - o1.y ==0){
                    return o1.x - o2.x;
                }
                return res;
            }
        });


        // iterate from high y to low y, if avaialable, then use him(if two with equal speed are available, use one with low cost, else continue)
        int curArea=0;
        int time=0;
        int cost=0;
        while(curArea < d) {
            for (int i = 0; i < n; i++) {
                if (l[i].y <= d - curArea && l[i].t <= time) {
                    curArea += l[i].y;
                    cost+=l[i].x;
                }
            }
            time++;
        }
        System.out.println(cost);
    }
    private static class labour{
        int t;
        int x;
        int y;

        labour(int t, int x, int y){
            this.t=t;
            this.x=x;
            this.y=y;
        }

    }
}
