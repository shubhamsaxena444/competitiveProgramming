package CompetitiveProgrammingQuestions.SearchingAndSorting;
import java.util.Scanner;
/*
Murder
Send Feedback
Once detective Saikat was solving a murder case. While going to the crime scene he took the stairs and saw that a number is written on every stair. He found it suspicious and decides to remember all the numbers that he has seen till now. While remembering the numbers he found that he can find some pattern in those numbers. So he decides that for each number on the stairs he will note down the sum of all the numbers previously seen on the stairs which are smaller than the present number. Calculate the sum of all the numbers written on his notes diary.
Answer may not fit in integer . You have to take long.
Input
First line gives T, number of test cases.

2T lines follow.

First line gives you the number of stairs N

Next line gives you N numbers written on the stairs.
Output
For each test case output one line giving the final sum for each test case.
Constraints
T<=10

1<=N<=10^5

All numbers will be between 0 and 10^6.
Sample Input:
1
5
1 5 3 6 4
Sample Output:
15
Explanation:
For the first number, the contribution to the sum is 0.
For the second number, the contribution to the sum is 1.
For the third number, the contribution to the sum is 1.
For the fourth number, the contribution to the sum is 9 (1+5+3).
For the fifth number, the contribution to the sum is 4 (1+3).
Hence the sum is 15 (0+1+1+9+4).
* */
public class Murder {



    public static void main(String[] args) {
        // Write your code here

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-->0) {
            int n = in.nextInt();
            //  int c = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(solve(arr,n));
        }
    }


    static long solve(int[] A,int n){

        //Write your code here.
        return inversionCount(A,0,n-1);
    }


    private static long inversionCount(int[] arr,int s, int e) {
        //devide the arr in 2 parts
        if(s>=e){
            return 0;
        }
        int m = s + (e-s)/2 ;
        long leftCount= inversionCount(arr,s,m);
        long rightCount = inversionCount(arr,m+1,e);
        long mergeCount = merge(arr,s,m+1,e);
        return  leftCount+ rightCount +    mergeCount;

    }

    private static long merge(int[] arr, int s, int m, int e) {
        int l_temp[] = new int[m-s];
        int r_temp[] = new int[e-m+1];
        int ln = m-s;
        int rn = e-m+1;
        System.arraycopy(arr, s, l_temp, 0, ln);
        System.arraycopy(arr, m, r_temp, 0, rn);
        //count the inversions for r_temp elements, for each r_temp element(if it is smaller) , the no of inversion is l_temp = length(l_temp)-l_temp[i]
        int i =0,j=0,k=s;
        long count=0;
        while(i<ln && j < rn){
            //if element is smaller than current then add
            if(l_temp[i] < r_temp[j]){
                count+=(rn-j)*l_temp[i];
                arr[k++]=l_temp[i++];

            }else{
                arr[k++] = r_temp[j++];

            }
        }
        while (i<ln){
            arr[k++]=l_temp[i++];
        }
        while(j<rn){
            arr[k++] = r_temp[j++];
        }
        return count;
    }
}
