package CompetitiveProgrammingQuestions.dynamicProgrammingAndBitmasking;

import java.util.Scanner;
/*
* String Maker
Send Feedback
According to Ancient Ninjas , string making is an art form . There are various methods of string making , one of them uses previously generated strings to make the new one . Suppose you have two strings A and B , to generate a new string C , you can pick a subsequence of characters from A and a subsequence of characters from B and then merge these two subsequences to form the new string.
Though while merging the two subsequences you can not change the relative order of individual subsequences. What this means is - suppose there two characters Ai and Aj in the subsequence chosen from A , where i < j , then after merging if i acquires position k and j acquires p then k<p should be true and the same apply for subsequence from C.
Given string A , B , C can you count the number of ways to form string C from the two strings A and B by the method described above. Two ways are different if any of the chosen subsequence is different .
As the answer could be large so return it after modulo 10^9+7 .
Input Format :
Line 1 : String A
Line 2 : String B
Line 3 : String C
Output Format :
The number of ways to form string C
Constraints :
1 <= |A|, |B|, |C| <= 50
Sample Input :
abc
abc
abc
Sample Output :
8*/
public class StringMaker {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String b = sc.nextLine();
        String c = sc.nextLine();
        StringMaker sm = new StringMaker();
        System.out.println(sm.solve(a,b,c));
    }
      int[][][] dp;
    public  int solve(String a,String b,String c){
        //write your code here
        //let i ,j ,k be the indices representing the string a,b,c respectively
        int x =0,y=0,z=0;
        dp = new int[a.length()+1][b.length()+1][c.length()+1];
        for(int i =0;i<=a.length();i++){
            for(int j =0;j<=b.length();j++){
                for(int k =0;k<=c.length();k++){
                        dp[i][j][k] =-1;
                }
            }
        }
        return  aux(a,x,b,y,c,z);
    }
     int count =0;
    private  int aux(String a, int x, String b, int y, String c, int z) {
        //base case is if c has reached its length
        if(z>=c.length() ){
            return 1;
        }

        if(x>=a.length() && y>=b.length()){
            return 0;
        }

        if(dp[x][y][z]!=-1){
            return dp[x][y][z];
        }

        //find c[k] in a , and every time you find inc the count;
        for(int i =x;i<a.length();i++){
            if(c.charAt(z) == a.charAt(i)){
                count += aux(a,i+1,b,y,c,z+1);
            }
        }

        //find c[k] in b , and every time you find inc the count;
        for(int j =y;j<b.length();j++){
            if(c.charAt(z) == b.charAt(j)){
                count += aux(a,x,b,j+1,c,z+1);
            }
        }
        dp[x][y][z] = count;
        return count;
    }
}
