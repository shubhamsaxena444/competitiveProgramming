package CompetitiveProgrammingQuestions.backtracking;
/*Sudoku Solver
Send Feedback
Given a 9*9 sudoku board, in which some entries are filled and others are 0 (0 indicates that the cell is empty), you need to find out whether the Sudoku puzzle can be solved or not i.e. return true or false.
Input Format :
9 Lines where ith line contains ith row elements separated by space
Output Format :
 true or false
Sample Input :
9 0 0 0 2 0 7 5 0
6 0 0 0 5 0 0 4 0
0 2 0 4 0 0 0 1 0
2 0 8 0 0 0 0 0 0
0 7 0 5 0 9 0 6 0
0 0 0 0 0 0 4 0 1
0 1 0 0 0 5 0 8 0
0 9 0 0 7 0 0 0 4
0 8 2 0 4 0 0 0 6
Sample Output :
true*/
import java.util.Scanner;

public class SudokuSolver {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n =9;
            int[][] in = new int[n][n];
            for(int i =0;i<n;i++){
                for(int j =0;j<n;j++){
                    in[i][j] = sc.nextInt();
                }
            }

            //if sanity check for input fails then return false
//            if(!sanity(in)){
//                System.out.printf("false");
//                return;
//            }
            System.out.println( solve(in));
        }

    private static boolean sanity(int[][] in) {
            int n= in.length;
        for(int i =0;i<n;i++){
            for(int j =0;j<n;j++) {
                if(in[i][j] !=0){
                    //check for all numbers
                        if(!isSafeRow(in,i,in[i][j]) || !isSafeCol(in,j,in[i][j]) || !isSafeBox(in,i,j,in[i][j])){
                        return false;
                        }
                }}}
        return true;
    }

    private static boolean solve(int[][] in) {
            //if there is no 0 in the arr, if yes, print sol
            if(isComplete(in)){
                printArr(in);
                return true;
            }
            int n = in.length;
            //find a position with 0, when found check for all numbers from 1...9
            for(int i =0;i<n;i++){
                for(int j =0;j<n;j++) {
                    if(in[i][j] ==0){
                        //check for all numbers
                        for(int k=1;k<=n;k++){
                            if(isSafeRow(in,i,k) && isSafeCol(in,j,k) && isSafeBox(in,i,j,k)){
                                //setNum
                                in[i][j] =k;
                                
                                //call recursively  and return if true
                                if(solve(in)){
                                    return true;
                                }
                                //else reset
                                in[i][j] = 0 ;
                            }
                            
                        }
                        return false;
                    }
                }
            }
            return  false;
        }

    private static boolean isSafeBox(int[][] in, int x, int y, int k) {
            //we need to find the starting cordinates of the box by using x - x%3


        for(int i = x-x%3; i<3+ (x-x%3) ; i++){

            for(int j = y-y%3 ;j<3+(y-y%3) ;j++){

                if(in[i][j] ==k){
                    return false;
                }
            }
        }
            return true;

    }

    private static boolean isSafeCol(int[][] in, int y, int k) {
        for(int i =0;i<in.length;i++){
            if(in[i][y] == k){
                return false;
            }

        }
        return true;
    }

    private static boolean isSafeRow(int[][] in, int x,int k) {
            for(int j =0;j<in.length;j++){
                if(in[x][j] == k){
                    return false;
                }

            }
            return true;
    }

    private static boolean isComplete(int[][] in) {
        int n = in.length;
            for(int i =0;i<n;i++){
            for(int j =0;j<n;j++) {
                if(in[i][j] == 0){
                    return false;
                }
                
            }}
    return true;
        }



        private static void printArr(int[][] in) {
            int n = in.length;
            for(int i =0;i<n;i++){
                for(int j =0;j<n;j++){
                    System.out.print(in[i][j]);
                }
                System.out.println();
            }
        }
}

