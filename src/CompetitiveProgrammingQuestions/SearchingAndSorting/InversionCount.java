package CompetitiveProgrammingQuestions.SearchingAndSorting;

/*Inversion Count
        Send Feedback
        Let A[0 ... n-1] be an array of n distinct positive integers. If i < j and A[i] > A[j] then the pair (i, j) is called an inversion of A (where i and j are indexes of A). Given an integer array A, your task is to find the number of inversions in A.
        Input format :
        Line 1 : n, array size
        Line 2 : Array elements (separated by space).
        Output format :
        Count of inversions
        Constraints :
        1 <= n <= 10^5
        1 <= A[i] <= 10^9
        Sample Input 1 :
        3
        3 2 1
        Sample Output 1 :
        3
        Sample Input 2 :
        5
        2 5 1 3 4
        Sample Output 1 :
        4*/
public class InversionCount {


    long solve(int[] A,int n){

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
            if(l_temp[i] <= r_temp[j]){
                arr[k++]=l_temp[i++];
            }else{
                arr[k++] = r_temp[j++];
                count+=ln-i;
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
