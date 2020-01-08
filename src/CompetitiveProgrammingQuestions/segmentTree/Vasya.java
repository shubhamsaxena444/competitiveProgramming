package CompetitiveProgrammingQuestions.segmentTree;

import java.util.Scanner;
/*
* Vasya vs Rhezo
Send Feedback
Queen Vasya is preparing for a war against Rhezo. She has N warriors in total arranged in a line. Let us number the warriors by numbers from 1 to N. She will fight Rhezo's army for Q days, and each day she can choose only one warrior.
For each warrior, we know 2 values associated with him, let us call these A and B. Each day Vasya can choose her warrior from a range Li to Ri, she must choose the warrior with maximum A value. If there is more than 1 warrior having the same maximum A value, she chooses the warrior with minimum B value. If still there is more than 1 warrior with same maximum A value and same minimum B value, she chooses the one with lower index in line.
You being the hand of Queen Vasya, need to help her in choosing the warrior for each day.
Input:
First line contains a single integer N, denoting the number of warriors Queen Vasya has.
Second line contains N space separated integers Ai. Third line contains N space separated integers Bi.
Next line contains a single integer Q, denoting the number of days Queen Vasya chooses a warrior.
Each of the next Q lines contains 2 integers Li and Ri.
Output:
For each Li and Ri, print the index of the warrior that Queen Vasya should choose.
Constraints:
1≤ N,Q ≤10^6
1≤ Ai,Bi ≤10^9
1≤Li≤Ri
Sample Input
5
1 8 4 6 8
4 8 6 3 7
4
1 4
2 4
3 4
1 5
Sample Output
2
2
4
5*/
public class Vasya {

    static class Query{
        int l;
        int r;
        public Query(int l,int r){
            this.l = l;
            this.r=r;
        }
    }
    static int[] a ;
    static int[] b ;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        a = new int[n];
        for(int i =0;i<n;i++){
            a[i] =sc.nextInt();
        }
        b=  new int[n];
        for(int i =0;i<n;i++){
            b[i] =sc.nextInt();
        }
        int q = sc.nextInt();
        Query[] list =new Query[q];
        for(int i =0;i<q;i++){
            list[i] =new Query(sc.nextInt(),sc.nextInt());
        }
        //build a segment tree of size 4n
        int[] tree = new int[4*n];
        //initialise with -1;
        for(int i=0;i<4*n;i++){
            tree[i] =-1;
        }
        build(tree,a,b,0,n-1,1);
//        for(int i=0;i<4*n;i++){
//            System.out.println(tree[i]);
//        }
        for(int i =0;i<q;i++){
               int ans = fetch(tree,0,n-1,1,list[i].l-1,list[i].r-1);
                System.out.println(ans+1);
        }
    }



    private static int fetch(int[] tree, int start, int end, int curTreeNode, int left, int right) {
//
//        //base case is when start == end
//        if(left>right) return new Node(Integer.MIN_VALUE,Integer.MIN_VALUE);

        //we are completely inside the range [left,right]
        if(left <= start && right >= end){
            return tree[curTreeNode];
        }

        //completely outside
        if(right < start || left > end){
            return 0;
        }
        int mid = start +(end-start)/2;
        // partially in and partially out
        int leftNode = fetch(tree,start,mid,2*curTreeNode,left,right);
        int rightNode = fetch(tree,mid+1,end,2*curTreeNode+1,leftNode,right);

//        if(leftNode ==-1 && rightNode ==-1){
//            return -1;
//        }else if(leftNode ==-1){
//            return rightNode;
//        }else if(rightNode == -1){
//            return leftNode;
//        }
        int ans;
        if (a[leftNode] > a[rightNode]){
            ans = leftNode;
        }else if(a[leftNode] < a[rightNode]) {
            ans= rightNode;
        }else{
            if (b[leftNode] > b[rightNode]){
                ans= rightNode;
            }else if(b[leftNode] < b[rightNode]) {
                ans = leftNode;
            }else{
                // if a and b are same
                ans = leftNode;
            }
        }
        return ans;
    }



    private static void build(int[] tree, int[] a,int[] b, int start, int end, int curTreeNode) {
        int n = a.length;
        //start index from 1...n in tree

        //base case is when start == end
        if(start ==end){
            tree[curTreeNode] = start;
            return;
        }
        int mid = start +(end-start)/2;
        // build left and right
        build(tree,a,b,start,mid,2*curTreeNode);
        build(tree,a,b,mid+1,end,2*curTreeNode+1);
        int left = tree[2*curTreeNode];
        int right = tree[2*curTreeNode+1];

        if (a[left] > a[right]){
            tree[curTreeNode] = left;
        }else if(a[left] < a[right]) {
            tree[curTreeNode] = right;
        }else{
            if (b[left] > b[right]){
                tree[curTreeNode] = right;
            }else if(b[left] < b[right]) {
                tree[curTreeNode] = left;
            }else{
                // if a and b are same
                tree[curTreeNode] = left;
            }
        }
        return;
    }
}