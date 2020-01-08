package CompetitiveProgrammingQuestions.dynamicProgramming1;
import java.util.Scanner;
/*
Hasan and Trip
Send Feedback
Hasan has finally finished his final exams and he decided to go in a trip among cities in Syria.
There are N cities in Syria and they are numbered from 1 to N, each city has coordinates on plane, i-th city is in (Xi, Yi).
Hasan is in first city and he wants to visit some cities by his car in the trip but the final destination should be N-th city and the sequence of cities he will visit should be increasing in index (i.e. if he is in city i he can move to city j if and only if i < j ).
Visiting i-th city will increase Hasan's happiness by Fi units (including first and last cities), also Hasan doesn't like traveling too much, so his happiness will decrease by total distance traveled by him.
Help Hasan by choosing a sequence of cities to visit which maximizes his happiness.
Input format:
First line contain integer N.
Next N lines contains three integers each, i-th line contains coordinates of i-th city Xi, Yi and Fi.
Output format:
Output one number rounded to 6 digits after floating point, the maximum possible happiness Hasan can get. Note: If answer is 2 print 2.000000
Constraints:
1 <= N <= 3,000
0 <= Xi, Yi, Fi <= 100,000
Sample Input
3
0 0 1
3 1 1
6 0 9
Sample Output
4.675445
* */
public class HasanAndTrip {

    static class city{
        int happy;
        int x;
        int y;
        city(int x,int y,int happiness){
            this.happy=happiness;
            this.x=x;
            this.y=y;
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        city[] c = new city[n];
        for(int i =0;i<n;i++){
            c[i] = new city(sc.nextInt(),sc.nextInt(),sc.nextInt());
        }
        System.out.format("%.6f",maxHappiness(c,n));
    }

    private static double maxHappiness(city[] array, int n) {
        //first city is 1

        //last city is n

        //let T[i][j] represent the maximum happiness reaching from i to j
        //lets say T[1][n] is the amount of happiness i have already, if going via i makes me more happy, then i will take the route
        //T[i][j] =Math.max(T[i][j] , T[i][k] + T[k][j])
        //lets fill the arr with its actual values of happiness
        // float[][] a = new float[n][n];
//         for(int i =0;i<n;i++){
//             for(int j =i+1;j<n;j++){
//                 a[i][j] = (float)(c[i].happiness + c[j].happiness) - distance(c[i],c[j]);
//             }
//         }
//        // float[][] T = new float[n][n];
//         //floyd warshall algo,
//         for(int i =0;i<n;i++){
//             for(int j =i+1;j<n;j++) {
//                 for(int k =i+1;k<j;k++) {
//                         a[i][j] =Math.max(a[i][j] ,a[i][k] + a[k][j] -c[k].happiness);
//                     }
//                 }
//             }

//         return a[0][n-1];
        double[] dp = new double[n];

        dp[0] = array[0].happy;


        for(int i=1;i<n;i++){
            double max = -100000000;
            for(int j=0;j<i;j++){
                double distance = Math.sqrt(Math.pow((array[i].x-array[j].x),2) + Math.pow((array[i].y-array[j].y),2));
                double ans = array[i].happy - distance + dp[j];

                if(ans > max){
                    max = ans;
                }
            }
            dp[i] = max;
        }
        return dp[n-1];
    }
    private static float distance(city a,city b){

        //sqrt((y2-y1)^2+(x2-x1)^2)
        return (float)Math.sqrt(Math.pow(b.y-a.y,2)+Math.pow(b.x-a.x,2));
    }
}
