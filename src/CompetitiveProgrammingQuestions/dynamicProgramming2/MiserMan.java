package CompetitiveProgrammingQuestions.dynamicProgramming2;

import java.util.Arrays;
import java.util.Scanner;

public class MiserMan {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] a= new int[n][m];
        for(int i =0;i<n;i++){
            for(int j =0;j<m;j++){
                a[i][j] = sc.nextInt();
            }
        }

        System.out.println(function(a,n,m,Integer.MAX_VALUE));
    }

    private static int function(int[][] a, int n, int m,int lastBusFare) {
        if(n==0 || m ==0)
            return 0;
        if(n==1){
            //return min fare bus value
            return Arrays.stream(a[0]).min().getAsInt();
        }
        int minFare=Integer.MAX_VALUE;
        for(int i =0;i<m;i++){
            if(lastBusFare == a[n-1][i] || lastBusFare == a[n-1][i]+1 || lastBusFare == a[n-1][i]-1 || lastBusFare == Integer.MAX_VALUE ){
//                System.out.println(lastBusFare);

                minFare =Math.min(minFare,a[n-1][i] + function(a,n-1,m,a[n-1][i]));
            }
        }
        return minFare;
    }
}
