package CompetitiveProgrammingQuestions.dynamicProgramming2;

import java.util.Scanner;

public class TripHappiness {
    static class city{
        int happiness;
        int x;
        int y;
        city(int x,int y,int happiness){
            this.happiness=happiness;
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
        System.out.println(maxHappiness(c,n));
    }

    private static float
 maxHappiness(city[] c, int n) {
        //first city is 1

        //last city is n

        //let T[i][j] represent the maximum happiness reaching from i to j
        //lets say T[1][n] is the amount of happiness i have already, if going via i makes me more happy, then i will take the route
        //T[i][j] =Math.max(T[i][j] , T[i][k] + T[k][j])
        //lets fill the arr with its actual values of happiness
        float[][] a = new float[n][n];
        for(int i =0;i<n;i++){
            for(int j =i+1;j<n;j++){
                a[i][j] = (float)(c[i].happiness + c[j].happiness) - distance(c[i],c[j]);
            }
        }
       // float[][] T = new float[n][n];
        //floyd warshall algo,
        for(int i =0;i<n;i++){
            for(int j =i+1;j<n;j++) {
                for(int k =i+1;k<j;k++) {
                        a[i][j] =Math.max(a[i][j] ,a[i][k] + a[k][j] -c[k].happiness);
                    }
                }
            }

        return a[0][n-1];
    }
    private static float distance(city a,city b){

        //sqrt((y2-y1)^2+(x2-x1)^2)
        return (float)Math.sqrt(Math.pow(b.y-a.y,2)+Math.pow(b.x-a.x,2));
    }
}
