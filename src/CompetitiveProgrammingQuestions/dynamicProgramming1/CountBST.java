package CompetitiveProgrammingQuestions.dynamicProgramming1;
/*Count BSTs
Send Feedback
Given an integer N, find and return the count of unique Binary search trees (BSTs) are possible with nodes valued from 1 to N.
Output count can be very large, so return the count modulo 10^9+7.
Input Format :
Integer n
Output Format :
Count of BSTs
Contraints :
1<= N <=1000
Sample Input 1:
8
Sample Output 1:
1430
Sample Input 2:
3
Sample Output 2:
5*/
public class CountBST {
    public static int countTrees(int n) {
	/* Your class should be named Solution
		 * Don't write main().
		 * Don't read input, it is passed as function argument.
		 * Return output and don't print it.
	 	 * Taking input and printing output is handled automatically.
         //shuru me left wala 0 hoga, last me right wala 0 hoga
		*/
        int mod =1000000007;
        int T[] = new int[n+1];
        T[0] =1;
        T[1] =1;
        for(int i =2;i<=n;i++){
            for(int j=0;j<i;j++){
                //long product =T[j] * T[i-j-1];
                //int productInt = (T[j] * T[i-j-1])%mod;
                // long sum = T[i] + productInt;
                //   int sumInt = (T[i] +  (T[j] * T[i-j-1])%mod)%mod;
                T[i]= (T[i] +  (T[j] * T[i-j-1])%mod)%mod;
            }
        }
        return (int)T[n];
    }
}
