package CompetitiveProgrammingQuestions.advanceRecursion;
/*Quick Sort Code
Send Feedback
Sort an array A using Quick Sort.
Change in the input array itself. So no need to return or print anything.
Input format :
Line 1 : Integer n i.e. Array size
Line 2 : Array elements (separated by space)
Output format :
Array elements in increasing order (separated by space)
Constraints :
1 <= n <= 1000
Sample Input:
6
2 6 8 5 4 3
Sample Output:
2 3 4 5 6 8*/
public class QuickSort {


    public static void quickSort(int[] input) {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * No need to print or return the output.
         * Taking input and printing output is handled automatically.
         */
        quickSort(input,0,input.length-1);

    }

    public static void quickSort(int[] input,int s, int e) {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * No need to print or return the output.
         * Taking input and printing output is handled automatically.
         */
        if(s >= e){
            return;
        }
        int p = partition(input,s,e);

        quickSort(input,s,p-1);
        quickSort(input,p+1,e);
    }
    private static int partition(int[] a, int s, int e) {
        if(s==e){
            return s;
        }
        int count =0;

        //count the number of elements smaller than a[pivot];
        for(int i =s;i<=e;i++){
            if(a[i] < a[s]){
                count++;
            }
        }
        int pivot = s+count;
        // place the pivot in its correct position
        swap(a,s,pivot);
        int i =s, j =e;
        while(i<pivot && j>pivot ){
            while(i<=e && a[i]<a[pivot]){
                i++;
            }
            while (j>=s && a[j]>=a[pivot]){
                j--;
            }
            if(i<pivot && j>pivot ){
                swap(a,i,j);
                i++;
                j--;
            }
        }
        return pivot;
    }

    private static void swap(int[] a, int pivot, int i) {
        int t = a[pivot];
        a[pivot]= a[i];
        a[i] = t;
    }
}
