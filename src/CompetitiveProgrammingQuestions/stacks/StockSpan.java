package CompetitiveProgrammingQuestions.stacks;

import java.util.Scanner;
import java.util.Stack;

public class StockSpan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for(int i =0;i<n;i++){
            a[i] =sc.nextInt();
        }
        System.out.println(maxSpan(a,n));
    }

    private static int maxSpan(int[] a, int n) {
        //we have to find the max span in which the price has remained lower than a perticular day's price for continuous days
        //maintain a stack for index
        int maxSpan =Integer.MIN_VALUE;
        Stack<Integer> s = new Stack<Integer>();
        //if we find the position wise greatest element's index on the left for each element, then span = cur_element_index-pwge_index
        for(int i =0;i<n;i++) {
            //if there is no pwge or stack is empty , then span = i+1;
        int element = a[i];
        while(!s.isEmpty() && a[s.peek()] <= element){
            s.pop();
        }
        int span = s.isEmpty() ? i+1 : i - s.peek();
        maxSpan = Math.max(maxSpan,span);
        //push it to stack
            s.push(i);
        }
        return maxSpan;
    }
}
