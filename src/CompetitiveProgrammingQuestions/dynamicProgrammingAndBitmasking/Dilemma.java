package CompetitiveProgrammingQuestions.dynamicProgrammingAndBitmasking;

import java.util.Scanner;
/*
* Dilemma
Send Feedback
Abhishek, a blind man recently bought N binary strings all of equal length .A binary string only contains '0's and '1's . The strings are numbered from 1 to N and all are distinct, but Abhishek can only differentiate between these strings by touching them. In one touch Abhishek can identify one character at a position of a string from the set. Find the minimum number of touches T Abhishek has to make so that he learn that all strings are different .
Constraints :
2<=N<=10
1<=|L|<=100 , L is length of the strings .
Input Format:
Line 1 : An Integer N, denoting the number of binary strings .
Next N lines : strings of equal length
Output Format:
Return the minimum number of touches
Sample Input :
2
111010
100100
Sample Output :
2*/
public class Dilemma {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String a[] =new String[n];
        for(int i =0;i<n;i++){
            a[i] =sc.nextLine();
        }
        System.out.println(minimumTouchRequired(n,a));
    }
    public static int minimumTouchRequired(int n, String[] input) {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, they are passed as function arguments.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        int pos =input[0].length()-1;
        int mask = (1<<n)-1;
        int[][] dp = new int[input[0].length()][1<<(n+1)];
        for(int i=0;i<input[0].length();i++){
            for(int j=0;j<1<<(n+1);j++){
                dp[i][j]=-1;
            }
        }
        return findTouches(pos,mask,input,dp);
    }

    private static int findTouches(int pos, int mask, String[] input, int[][] dp) {
        //base case is when there is only one string is left. i.e, there is only 1 which is power of 2
        if((mask &(mask-1))==0){
            return 0;  //there are 0 toches required now
        }
        //if pos is <0 , return max as it can not be determined
        if(pos<0){
            return 10000;
        }
        if(dp[pos][mask] != -1){
            return dp[pos][mask];
        }
        int newmask1 =0;
        int newmask2= 0;
        int touches =0;

        for(int i =0;i<input.length;i++){
            //check for bits that are set as 1 only
            if((mask & (1<<i)) >0){
                touches++;
                if(input[i].charAt(pos) =='0'){
                    //if string char at pos is 0--> add it to newmask1
                    newmask1 |= 1<<i;
                }else{
                    newmask2 |= 1<<i;
                }
            }
        }
        int ans1 = findTouches(pos-1,newmask1,input,dp) +findTouches(pos-1,newmask2,input,dp) +touches;
        int ans2 = findTouches(pos-1,mask,input,dp);
        dp[pos][mask] =Math.min(ans1,ans2);
        return dp[pos][mask];
    }
}
