package CompetitiveProgrammingQuestions.greedy;

import java.util.*;
/*Perimeter with conditions
Send Feedback
Aahad gives an array of integers and asks Harshit to find which three elements form a triangle (non-degenerate). The task seems easy to Harshit.
So, Aahad adds some conditions to this task -
1. Find the triangle with maximum perimeter
2. If there are two or more combinations with same value of maximum perimeter, then find the one with the longest side.
3.If there are more than one combinations which satisfy all the above conditions the find with maximum longest minimum side.
Input Format
The First line contains no of elements of array: N
Each T lines contains N space-separated integers: A [i]
Output Format
The output contains three space-separated elements that denote the length of the sides of triangle. If no such triangle is possible, then print -1.
Constraints
1 =< N <= 10^5
1 <= A[i] <= 10^9
Time Limit: 1 second
Sample Input1:
5
1 1 1 3 3
Sample Output1:
1 3 3
Sample Input2:
3
2 2 4
Sample Output3:
-1
Explaination
In the First Sample case, the elements that form a triangle with maximum perimeter is 1,3,3.
In the Second Sample case, the elements that can form a triangle are degenerate, so, we printed -1.*/
public class PerimeterAndConditions {
    static class Triangle{
        int p;
        int l;
        int s;
        Triangle(int p, int l ,int s){
            this.p=p;
            this.l=l;
            this.s=s;
        }

        @Override
        public String toString() {
            return s+" "+(p-s-l)+" "+l;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for(int i =0;i<n;i++){
            a[i] = sc.nextInt();
        }
        PriorityQueue<Triangle> pq = new PriorityQueue<Triangle>(n, new Comparator<Triangle>() {
            @Override
            public int compare(Triangle o1, Triangle o2) {
                if(o2.p > o1.p){
                    return 1;
                }else if(o2.p < o1.p){
                    return -1;
                }else{
                    if(o2.l >o1.l){
                        return 1;
                    }else if(o2.l<o1.l){
                        return -1;
                    }else{
                        if(o2.s >o1.s){
                            return 1;
                        }else if(o2.s<o1.s){
                            return -1;
                        }else{
                            return 0;
                        }
                    }
                }
            }
        });
        Arrays.sort(a);
        //iterate through array, if a triplet forms triangle then add to pq
        for(int i =2 ;i<n;i++){

            if(formTriangle(a[i-2],a[i-1],a[i])){
                //add to pq;
                pq.add(new Triangle(a[i-2]+a[i-1]+a[i],Math.max(a[i-2],Math.max(a[i-1],a[i])),Math.min(a[i-2],Math.min(a[i-1],a[i]))));
            }
        }
        Iterator<Triangle> it = pq.iterator();
        if(it.hasNext()){
        System.out.println(it.next());
        }
    }

    private static boolean formTriangle(int a, int b, int c) {
        //if sum of 2 sides < 3rd side, return false
        if(a+b <=c || a+c <=b ||b+c <=a){return false;}
        return true;
    }
}
