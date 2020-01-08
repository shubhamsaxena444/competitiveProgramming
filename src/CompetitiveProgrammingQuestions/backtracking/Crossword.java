package CompetitiveProgrammingQuestions.backtracking;

import java.util.Scanner;
/*
* Crossword Problem
Send Feedback
CompetitiveProgrammingQuestions has provided a crossword of 10*10 grid. The grid contains '+' or '-' as its cell values. Now, you are also provided with a word list that needs to placed accurately in the grid. Cells marked with '-' are to be filled with word list.
For example, The following is an example for the input crossword grid and the word list.
+-++++++++
+-++-+++++
+-------++
+-++-+++++
+-++-+++++
+-++-+++++
++++-+++++
++++-+++++
++++++++++
----------
CALIFORNIA;NIGERIA;CANADA;TELAVIV
Output for the given input should be:
+C++++++++
+A++T+++++
+NIGERIA++
+A++L+++++
+D++A+++++
+A++V+++++
++++I+++++
++++V+++++
++++++++++
CALIFORNIA
Note: We have provided such test cases that there is only one solution for the given input.
Input format:
The first 10 lines of input contain crossword. Each of 10 lines has a character array of size 10. Input characters are either '+' or '-'.
The next line of input contains the word list, in which each word is separated by ';'.
Output format:
Print the crossword grid, after placing the words of word list in '-' cells.
Sample string.Test Cases:
Sample Input 1:
+-++++++++
+-++-+++++
+-------++
+-++-+++++
+-++-+++++
+-++-+++++
++++-+++++
++++-+++++
++++++++++
----------
CALIFORNIA;NIGERIA;CANADA;TELAVIV
Sample Output 1:
+C++++++++
+A++T+++++
+NIGERIA++
+A++L+++++
+D++A+++++
+A++V+++++
++++I+++++
++++V+++++
++++++++++
CALIFORNIA*/
public class Crossword {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n =10;
        char[][] in = new char[n][n];
        for(int i =0;i<n;i++){
            String s = sc.next();
            for(int j =0;j<n;j++){
                in[i][j] = s.charAt(j);
            }
        }
        sc.nextLine();
        String[] wl = sc.nextLine().split(";");
        solve(in,wl,0);
    }

    private static boolean solve(char[][] in, String[] wl,int index) {
        //if there is no - left , then base case has hit
        if(index == wl.length){
            printArr(in);
            return true;
        }
        int n = in.length;
        //check isSafe, whether there is enough space in the row/col for all words
        //for each position check if it is valid to fill the word
        for(int i =0;i<n;i++){
            for(int j =0;j<n;j++) {
                //struct res = isSafe(wl[i], in);
                if((in[i][j] == '-' || in[i][j] == wl[index].charAt(0) ) && isValidHorizontal(wl[index],in,i,j)){
                    //setHorizontally and maintain a bool arr to keep track of what we intserted
                    boolean arr[] = new boolean[wl[index].length()];
                    setHorizontally(wl[index],in,i,j,arr);
                    //recurse, if solved then return else reset and check
                    if(solve(in,wl,index+1) ){
                        //print

                        return true;
                    }
                    //resetHorizontally
                    resetHorizontally(wl[index],in,i,j,arr);
                }
                if((in[i][j] == '-' || in[i][j] == wl[index].charAt(0) ) && isValidVertical(wl[index],in,i,j)){
                    //setHorizontally and maintain a bool arr to keep track of what we intserted
                    boolean arr[] = new boolean[wl[index].length()];
                    setVertically(wl[index],in,i,j,arr);
                    //recurse, if solved then return else reset and check
                    if(solve(in,wl,index+1) ){
                        //print

                        return true;
                    }
                    //resetHorizontally
                    resetVertically(wl[index],in,i,j,arr);
                }
            }
        }
        return  false;
    }

    private static void resetVertically(String s, char[][] in, int x, int y, boolean[] arr) {
        int l = s.length();
        for(int i = x ;i< x+l ;i++){
            if(arr[i-x]){
                in[i][y] = '-';
            }
        }
    }

    private static void resetHorizontally(String s, char[][] in, int x, int y, boolean[] arr) {
        int l = s.length();
        for(int j = y ;j< y+l ;j++){
            if(arr[j-y]){
                in[x][j] = '-';
            }
        }
    }

    private static void setVertically(String s, char[][] in, int x, int y, boolean[] arr) {
        int l = s.length();
        for(int i = x ;i< x+l ;i++){
            if(in[i][y] == '-'){
                arr[i-x] =true;
            }
            in[i][y] = s.charAt(i-x);
        }
    }


    private static void setHorizontally(String s, char[][] in, int x, int y, boolean[] arr) {
        int l = s.length();
        for(int j = y ;j< y+l ;j++){
            if(in[x][j] == '-'){
                arr[j-y] =true;
            }
            in[x][j] = s.charAt(j-y);
        }
    }
    private static boolean isValidVertical(String s, char[][] in, int x, int y) {
        int l = s.length();
        //see if row has the l length slot
        if(10-x < l){
            return false;
        }
        //check if the char is + for l length or any other char than it should be in s
        for(int i =x;i<x+l;i++){
            if(in[i][y] == '+' || (in[i][y] !='-' && in[i][y] != s.charAt(i-x))){
                return false;
            }
        }
        return true;
    }


    private static boolean isValidHorizontal(String s, char[][] in, int x, int y) {
        int l = s.length();
        //see if row has the l length slot
        if(10-y < l){
            return false;
        }
        //check if the char is + for l length or any other char than it should be in s
        for(int j =y;j<y+l;j++){
            if(in[x][j] == '+' || (in[x][j] !='-' && in[x][j] != s.charAt(j-y))){
                return false;
            }
        }
        return true;
    }



    private static void printArr(char[][] in) {
        int n = in.length;
        for(int i =0;i<n;i++){
            for(int j =0;j<n;j++){
                System.out.print(in[i][j]);
            }
            System.out.println();
        }
    }
}
