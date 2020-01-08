package CompetitiveProgrammingQuestions.advanceRecursion;
/*Print Keypad Combinations Code
Send Feedback
Given an integer n, using phone keypad find out and print all the possible strings that can be made using digits of input n.
Note : The order of strings are not important. Just print different strings in new lines.
Input Format :
Integer n
Output Format :
All possible strings in different lines
Constraints :
1 <= n <= 10^6
Sample Input:
23
Sample Output:
ad
ae
af
bd
be
bf
cd
ce
cf*/

import java.util.HashMap;

public class PrintKeypadCombinationCode {


    public static void printKeypad(int input){
        // Write your code here
        keypad(input,"");
    }



    // Return a string array that contains all the possible strings
    public static void keypad(int n,String output){
        // Write your code here
        //maintsin a hashmap of integer,string

        HashMap<Integer,String> hm = new HashMap<>();


        hm.put(0,"");
        hm.put(1,"");
        hm.put(2,"abc");
        hm.put(3,"def");
        hm.put(4,"ghi");
        hm.put(5,"jkl");
        hm.put(6,"mno");
        hm.put(7,"pqrs");
        hm.put(8,"tuv");
        hm.put(9,"wxyz");
        //   List<String> res = new ArrayList<>();



        if(n == 0 || n == 1){
            System.out.println(output);
            // res.add("");
            // return (String[])res.toArray(new String[res.size()]);
        }
        //if i have the result of first n-1 digits , then i can increase the size 3-4(length of digit) times
        //String[] temp = keypad(n/10);
        String t = hm.get(n%10);
        for(int i =0 ;i<t.length();i++){
            Character c = t.charAt(i);
            keypad(n/10,c+output);
            //res.addAll(Arrays.stream(temp).map(x->x+c).collect(Collectors.toList()));
        }
        //return (String[])res.toArray(new String[res.size()]);
    }

}
