package CompetitiveProgrammingQuestions.stacks;

import java.util.Scanner;
import java.util.Stack;

public class AreaOfHistogram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for(int i =0;i<n;i++){
            a[i] =sc.nextInt();
        }
        System.out.println(histogram(a,n));
    }

    //e.g   2, 1 ,2 , 3, 1
    private static int histogram(int[] a, int n) {
        //we have to find the max span in which the price has remained lower than a perticular day's price for continuous days
        //maintain a stack for index
        int maxArea =Integer.MIN_VALUE;
        Stack<Integer> s = new Stack<Integer>();
        //if we find the  position wise smaller or equal element's index on the left for each element, then span = cur_element_index-pwge_index
        int i =0;
        for(;i<n;i++) {
            //if there is no position wise smaller or equal element or stack is empty , then span = i+1;
            int element = a[i];
         while(!s.isEmpty() && a[s.peek()] > element){
               //calculate area every time on removal
                int top =  s.pop();
                int area = a[top] * (s.isEmpty() ? i : (i - s.peek()-1)) ;
                maxArea = Math.max(maxArea,area);

            }
             //push it to stack
             s.push(i);
        }
        //while stack is not empty
        while(!s.isEmpty() ){
            //calculate area every time on removal
            int top =  s.pop();
            int area = a[top] * (s.isEmpty() ? i : (i - s.peek()-1)) ;
            maxArea = Math.max(maxArea,area);
        }
        return maxArea;

    }

}
