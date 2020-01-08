package CompetitiveProgrammingQuestions.backtracking;

import java.util.Arrays;
/*
* N-Queen Problem
Send Feedback
You are given N, and for a given N x N chessboard, find a way to place N queens such that no queen can attack any other queen on the chess board. A queen can be killed when it lies in the same row, or same column, or the same diagonal of any of the other queens. You have to print all such configurations.
Input Format :
Line 1 : Integer N
Output Format :
One Line for every board configuration.
Every line will have N*N board elements printed row wise and are separated by space
Note : Don't print anything if there isn't any valid configuration.
Constraints :
1<=N<=10
Sample Input 1:
4
Sample Output 1 :
0 1 0 0 0 0 0 1 1 0 0 0 0 0 1 0
0 0 1 0 1 0 0 0 0 0 0 1 0 1 0 0 */
public class NQueen {


    public static void main(String[] args) {
        placeNQueens(
                5
        );
    }
        public static void placeNQueens(int n){

            /* Your class should be named Solution.
             * Don't write main() function.
             * Don't read input, it is passed as function argument.
             * Print output as specified in the question
             */
            int[][] board = new int[n][n];
            //start from begining, place q in 0,0
            sol(board,n,0);
        }

    private static void sol(int[][] board, int n, int row) {
        if(row == n){
            System.out.println();
            Arrays.stream(board).forEach(     x->    Arrays.stream(x).forEach(y->   System.out.print(y+" ")));
            return;
        }
        //for second row, search for a possible plcae, if found place and recurse , else backtrack
            for(int j =0;j<n;j++){
                    if(isPossible(board,n,row,j)){
                    board[row][j] =1 ;
                    sol(board, n,   row+1  );
                        //backtrack
                        board[row][j] =0 ;
                    }
            }
            return;
    }

    private static boolean isPossible(int[][] board,int n, int i, int j) {
        //check row
        for(int x =0;x<n;x++){
            if(board[i][x] == 1){
                return false;
            }
        }
        //check col
        for(int x =0;x<n;x++){
            if(board[x][j] == 1){
                return false;
            }
        }
        //check diagonal
        for(int x =0;x<n;x++){
            if(i-x>=0 && j-x >=0 && board[i-x][j-x] == 1){
                return false;
            }
            if(i+x<n && j+x<n  && board[i+x][j+x] == 1){
                return false;
            }
            if(i-x>=0 && j+x <n && board[i-x][j+x] == 1){
                return false;
            }
            if(i+x<n && j-x>=0  && board[i+x][j-x] == 1){
                return false;
            }

        }
        return true;
    }


}
