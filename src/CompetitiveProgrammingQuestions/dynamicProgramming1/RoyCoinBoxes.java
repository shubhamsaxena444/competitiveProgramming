package CompetitiveProgrammingQuestions.dynamicProgramming1;

import java.util.Arrays;
import java.util.Scanner;
/*Roy and Coin Boxes
Send Feedback
Roy has N coin boxes numbered from 1 to N.
Every day he selects two indices [L,R] and adds 1 coin to each coin box starting from L to R (both inclusive).
He does this for M number of days.

After M days, Roy has a query: How many coin boxes have atleast X coins.
He has Q such queries.
Input
First line contains N - number of coin boxes.
Second line contains M - number of days. Each of the next M lines consists of two space separated integers L and R. Followed by integer Q - number of queries.
Each of next Q lines contain a single integer X.a
Output
For each query output the result in a new line.
Constraints
1 ≤ N ≤ 1000000

1 ≤ M ≤ 1000000

1 ≤ L ≤ R ≤ N

1 ≤ Q ≤ 1000000

1 ≤ X ≤ N
Sample Input
7
4
1 3
2 5
1 2
5 6
4
1
7
4
2
Sample Output
6
0
0
4*/
public class RoyCoinBoxes
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        int m = sc.nextInt();
        int l[] = new int[m];
        int r[] = new int[m];
        for(int i =0;i<m;i++){
            l[i] = sc.nextInt();
            r[i] = sc.nextInt();
        }
        int q = sc.nextInt();
        int x[] =new int[q];
        for(int i =0;i<q;i++){
            x[i] = sc.nextInt();
        }

        int prefix[] =new int[n+2];  //index =0,n+1c are redundant
        for(int i =0;i<m;i++){
            //l[i]...r[i] ->inc 1
            prefix[l[i]]++; //enter
            prefix[r[i]+1]--; //exit
        }
        //create prefix array
        for(int i =1;i<n+2;i++){
            prefix[i]+=prefix[i-1];
        }
        //now prefix contains the freq corresponding to each box numbered from 1...N
        //sort the aaray
        Arrays.sort(prefix);
        for(int i =0;i<q;i++){
            int index = Arrays.binarySearch(prefix,x[i]); //find the lower bound
            //if element is found, then the function returns lower bound(first index of occurance
            if(index>=0){
                while(index-1>=0  && prefix[index]== prefix[index-1]) index--;
                System.out.println(n+2-index);
            }else{
                //to get the upper bound
                int upperIndex = Math.abs(index) - 1;
                //border case
                if( upperIndex > n+1 ){
                    System.out.println(0);
                    continue;
                }
                System.out.println(n+2-upperIndex);
            }
        }
    }
}
