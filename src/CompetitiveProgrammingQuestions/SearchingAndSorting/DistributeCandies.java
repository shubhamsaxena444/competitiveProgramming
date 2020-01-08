package CompetitiveProgrammingQuestions.SearchingAndSorting;
import java.util.*;
/*Distribute Candies
Send Feedback
Shaky has N (1<=N<=50000) candy boxes each of them contains a non-zero number of candies (between 1 and 1000000000). Shaky want to distibute these candies among his K (1<=K<=1000000000) IIIT-Delhi students. He want to distibute them in a way such that:
1. All students get equal number of candies.
2. All the candies which a student get must be from a single box only.
As he want to make all of them happy so he want to give as many candies as possible. Help Shaky in finding out what is the maximum number of candies which a student can get.
Input
First line contains 1<=T<=20 the number of test cases. Then T test cases follow. First line of each test case contains N and K. Next line contains N integers, ith of which is the number of candies in ith box.
Output
For each test case print the required answer in a seperate line.
Sample Input:
2
3 2
3 1 4
4 1
3 2 3 9
Sample Output:
3
9*/
public class DistributeCandies {



    public static void main(String[] args) {
        // Write your code here
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-->0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(maximumCandies(arr, n,k));
        }
    }




    private static int maximumCandies(int[] arr, int n,int k) {
        int d; //d will range from 0 to max element.
        //do binary search for d =0 to max
        Arrays.sort(arr);
        //  int n = arr.length;
        return binarySearchArrangement(arr,0,arr[n-1],k);
    }

    private static int binarySearchArrangement(int[] arr, int s, int e,int k) {
        if(s>e){
            return -1;
        }
        int m = s+ (e-s)/2 ;
        //if m is possible && m+1 is not possible return m ,else if m is possible, search in m+1,e, else search in s,m-1
        boolean isPossible = isPossible(arr,m,k);
        if(isPossible && ( m+1>e || !isPossible(arr,m+1,k))){
            return m;
        }else if(isPossible){
            return binarySearchArrangement(arr,m+1,e,k);
        }else{
            return binarySearchArrangement(arr,s,m-1,k);
        }
    }

    private static boolean isPossible(int[] arr, int m,int k) {
        // it will be possible to distribute m candies among k students, only if the total students catered by summation(arr[i]/k) >=k
        int count = 0;
        // int lastCowPlace = 0;
        for(int i =arr.length-1;i>=0;i--){
            count+= arr[i]/m;
            if(count >= k){
                return true;
            }
        }
        return false;
    }
}
