package CompetitiveProgrammingQuestions.adhoc;
import java.util.*;
/*Light Up the Bulbs
Send Feedback
A bulb can be ‘ON’ or ‘OFF’. Mr. Navdeep got ‘n’ number of bulbs and their status, whether they are ‘ON’ or ‘OFF’. Their status is represented in a string of size ‘n’ consisting of 0’s and 1’s, where ‘0’ represents the bulb is in ‘OFF’ condition and ‘1’ represent the bulb is ‘ON’. Mr. Navdeep has been given the task to light up all the bulbs.
He can perform two operations.
First, chose any segment of bulbs and reverse them means chose any substring and reverse it. E.g. “0 110 001” -> “0 011 001”. Substring (1, 3) is reversed here. This operation will cost him Rs. ‘X’.
Second, chose any segment of bulbs and reverse their present condition. i.e. if the bulb is ‘ON’, make it ‘OFF’ and if it is ‘OFF’, make it ‘ON’. E.g. “0 011 001” -> “0 100 001”. Substring (1, 3) is complemented. This operation will cost him Rs. ‘Y’.
You need to help Mr. Navdeep that how much minimum amount it will require to make all the bulbs lightened. (or make all the characters as ‘1’ in the representation string)
Input Format:
The first line will contain three space separated integers: ‘n’, ‘X’, ‘Y’ denoting the number of bulbs, cost of first operation and cost of second operation respectively.
The second line contains a representation string of length ‘n’ consisting of 0’s and 1’s representing whether the bulb is ‘OFF’ or ‘ON’.
Output Format:
Print a single integer denoting the minimum cost required to light up all the bulbs.
Constraints:
1 <= n <= 3,00,000
0 <= X, Y <= 10^9
Time Limit: 1 second
Sample Input:
5 1 10
01000
Sample Output:
11
Explanation:
First, Reverse substring (0, 1): “01000” -> “10000”, COST = 1
Second, Invert substring (1, 4): “10000” -> “11111”, COST = 10
Total cost = 1+10 => 11*/
public class LightUpTheBulbs {

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            long x = in.nextLong(); //cost of reverse
            long y = in.nextLong(); //cost of flip
            in.nextLine();
            String s = in.nextLine();
            long cost=0;
            boolean allZero=true,allOne=true;
            for(int i =0;i<n;i++){
                if(s.charAt(i) == '1'){
                    allZero =false;
                }else{
                    allOne = false;
                }

            }
            if(allOne){
                System.out.println(0);
                return ;
            }else if(allZero){
                System.out.println(y);
                return;
            }
            if(x<=y){
                //count all the occurances of 01* in the string
                int cx=0;

                for(int i =1;i<n;i++){
                    if(s.charAt(i-1) == '0' && s.charAt(i) =='1'){
                        //increase cx
                        cx++;
                        //update next i
                        while(i<n && s.charAt(i) =='1'){
                            i++;
                        }
                    }
                }
                //if last char is 0* , then  //increase cx
                if(s.charAt(n-1) =='0'){
                    cx++;
                }
                cost = (cx-1) * x + y;
            }else{
                //count all the substrings of 0*
                int cy =0;
                for(int i =0;i<n;i++){
                    if(s.charAt(i) == '0' ){
                        //increase cx
                        cy++;
                        //update next i
                        while(i<n && s.charAt(i) =='0'){
                            i++;
                        }
                    }
                }

                cost =   cy * y;
            }
            System.out.println(cost);
        }
}
