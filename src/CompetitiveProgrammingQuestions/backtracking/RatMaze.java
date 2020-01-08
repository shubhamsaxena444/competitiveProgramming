package CompetitiveProgrammingQuestions.backtracking;
/*
Rat In A Maze Problem
Send Feedback
You are given a N*N maze with a rat placed at maze[0][0]. Find and print all paths that rat can follow to reach its destination i.e. maze[N-1][N-1]. Rat can move in any direc­tion ( left, right, up and down).
Value of every cell in the maze can either be 0 or 1. Cells with value 0 are blocked means rat can­not enter into those cells and those with value 1 are open.
Input Format
Line 1 : Integer N
Next N Lines : Each line will contain ith row elements (separated by space)
Output Format :
One Line for every possible solution.
Every line will have N*N maze elements printed row wise and are separated by space. Only cells that are part of solution path should be 1, rest all cells should be 0.
Sample Input 1 :
3
1 0 1
1 0 1
1 1 1
Sample Output 1 :
1 0 0 1 0 0 1 1 1
Sample Output 1 Explanation :
Only 1 path is possible
Sample Input 2 :
3
1 0 1
1 1 1
1 1 1
Sample Output 2 :
1 0 0 1 1 1 1 1 1
1 0 0 1 0 0 1 1 1
1 0 0 1 1 0 0 1 1
1 0 0 1 1 1 0 0 1
Sample Output 2 Explanation :
4 paths are possible
* */
import java.util.Arrays;

public class RatMaze {
    static  int a[][] = new int[][]{{1 ,0 ,1},{1 ,0 ,1},{1 ,1, 1}};
    public static void main(String[] args) {

        ratInAMaze(
                a
        );
    }
    public static void ratInAMaze(int maze[][]){

        /* Your class should be named Solution.
         * Don't write main() function.
         * Don't read input, it is passed as function argument.
         * Print output as specified in the question
         */
        int n = maze.length;
        int[][] board = new int[n][n];
        board[0][0]= 1;
        //start from begining, place q in 0,0
        sol(maze,board,n,0,0);
    }

    private static void sol(int[][] maze,int[][] board,int n, int i, int j) {
        if(i == n-1 && j == n-1){
            System.out.println();
            Arrays.stream(board).forEach(x->    Arrays.stream(x).forEach(y->   System.out.print(y+" ")));
            return;
        }
        //for second row, search for a possible plcae, if found place and recurse , else backtrack

        //up
        if(j-1  >=0 && isPossible(maze,board,n,i,j-1)){
            board[i][j-1] =1 ;
            sol(maze,board,n, i,   j-1  );
            //backtrack
            board[i][j-1] =0 ;
        }

        //down
        if(j+1  <n && isPossible(maze,board,n,i,j+1)){

            board[i][j+1] =1 ;
            sol(maze,board,n, i,   j+1  );
            //backtrack
            board[i][j+1] =0 ;

        }
        //left
        if(i-1 >=0 && isPossible(maze,board,n,i-1,j)){
            board[i-1][j] =1 ;
            sol(maze,board,n, i-1,   j  );
            //backtrack
            board[i-1][j] =0 ;
        }
        //right
        if(i+1 <n && isPossible(maze,board,n,i+1,j)){
            board[i+1][j] =1 ;
            sol(maze,board,n, i+1,   j  );
            //backtrack
            board[i+1][j] =0 ;
        }
        return;
    }

    static boolean isPossible(int[][] maze,int[][] x ,int n, int i, int j){
        if(maze[i][j] == 1 && x[i][j]!=1){
            return true;
        }
        return false;
    }

}
