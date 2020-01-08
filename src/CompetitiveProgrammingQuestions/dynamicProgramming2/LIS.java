package CompetitiveProgrammingQuestions.dynamicProgramming2;

import java.util.Arrays;
import java.util.Scanner;

public class LIS {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a [] = new int[n];
        for(int i =0;i<n;i++){
            a[i] =sc.nextInt();
        }
        System.out.println(vanyaVasya(a,n));
    }

    private static int lis(int[] a, int n) {
        int[] m = new int[n];
        //initialise with 1 since for each i , m[i] i.e, LIS ending at i would be at least 1, ie itself
        for(int i =0;i<n;i++)
            m[i] =1;

        for(int i=1;i<n;i++){

            //i need to check for j =0 till i-1, if there is any element a[j] smaller than a[i], if so and m[j]+1 > m[i], then update m[i]
            for(int j =0;j<i;j++){
                if(a[j] < a[i] && (m[j] + 1) > m[i]){
                    m[i] =m[j] +1;
                }
            }

        }
       return Arrays.stream(m).max().getAsInt();
    }
    private static int vanyaVasya(int[] a, int n) {
        //let m[i][g] represent the number of inc subsequences ending at i with gcd g
        int[][] m = new int[n][101];
        int mod =1000000007;
        //initialise with 1 since for each i with GCD=1, m[i] i.e, LIS ending at i would be at least 1, ie itself
        for(int i =0;i<n;i++) {
            // if(a[i] ==1 )
            m[i][a[i]] = 1;
        }
        for(int i=1;i<n;i++){

            //i need to check for j =0 till i-1, if there is any element a[j] smaller than a[i], if so and m[j]+1 > m[i], then update m[i]
            for(int j =0;j<i;j++){
                if(a[j] < a[i]) {
                    for(int k =1;k<101;k++){
                        if(m[j][k] >0 && isGCD(a[i],a[j])==1)
                            m[i][1] = (m[i][1] + m[j][k])%mod;
                    }
                }
            }

        }
        int sum=0;
        for(int i =0;i<n;i++)
        {
            sum = (sum + m[i][1])%mod;
        }
        return sum;
    }
    private static int isGCD(int a, int b) {
        if(b ==0){
            return a;
        }
        return isGCD(b,a%b);
    }
}
