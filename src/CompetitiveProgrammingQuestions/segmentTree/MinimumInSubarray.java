package CompetitiveProgrammingQuestions.segmentTree;
/*Minimum In SubArray
Send Feedback
Range Minimum Query
Given an array A of size N, there are two types of queries on this array.
1) q l r: In this query you need to print the minimum in the sub-array A[l:r].
2) u x y: In this query you need to update A[x]=y.
Input:
First line of the test case contains two integers, N and Q, size of array A and number of queries.
Second line contains N space separated integers, elements of A.
Next Q lines contain one of the two queries.
Output:
For each type 1 query, print the minimum element in the sub-array A[l:r].
Contraints:
1≤N,Q,y≤10^5
1≤l,r,x≤N
Sample Input :
5 5
1 5 2 4 3
q 1 5
q 1 3
q 3 5
u 3 6
q 1 5
Sample Output :
1
1
2
1*/
import java.util.Scanner;

public class MinimumInSubarray {

    static class Query{
        char type;
        int l;
        int r;
        public Query(char type,int l,int r){
            this.type=type;
            this.l = l;
            this.r=r;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] a = new int[n];
        for(int i =0;i<n;i++){
            a[i] =sc.nextInt();
        }
        Query[] list =new Query[q];
        for(int i =0;i<q;i++){
            list[i] =new Query(sc.next().charAt(0),sc.nextInt(),sc.nextInt());
        }
        //build a segment tree of size 4n
        int[] tree = new int[4*n];
        build(tree,a,0,n-1,1);
      /*  for(int i=0;i<4*n;i++){
            System.out.println(tree[i]);
        }*/
        for(int i =0;i<q;i++){
            if(list[i].type == 'u'){
                update(tree,0,n-1,1,list[i].l-1,list[i].r);
            }else{
                System.out.println(getMin(tree,0,n-1,1,list[i].l-1,list[i].r-1));
            }
        }
    }

    private static int getMin(int[] tree,int start,int end, int curTreeNode, int left, int right) {

        //base case is when start == end

        //we are completely inside the range [left,right]
        if(left <= start && right >= end){
            return tree[curTreeNode];
        }

        //completely outside
        if(right < start || left > end){
            return Integer.MAX_VALUE;
        }
        int mid = start +(end-start)/2;
        // partially in and partially out

        return Math.min(getMin(tree,start,mid,2*curTreeNode,left,right),
                getMin(tree,mid+1,end,2*curTreeNode+1,left,right));

    }

    private static void update(int[] tree,int start,int end, int curTreeNode, int index, int value) {
        //start index from 1...n in tree

        //base case is when start == end
        if(start == end && start ==index){
            tree[curTreeNode] = value;
            return;
        }
        int mid = start +(end-start)/2;
        // find index in left or right
        if(index <= mid) {
            update(tree, start, mid, 2 * curTreeNode, index, value);
        }else {
            update(tree, mid+1, end, 2 * curTreeNode+1, index, value);
        }
        //update for upper node

        tree[curTreeNode] = Math.min(tree[curTreeNode*2],tree[curTreeNode*2+1]);
    }



    private static void build(int[] tree,int[] a,int start,int end, int curTreeNode) {
        int n = a.length;
        //start index from 1...n in tree

        //base case is when start == end
        if(start ==end){
            tree[curTreeNode] = a[start];
            return;
        }
        int mid = start +(end-start)/2;
        // build left and right
        build(tree,a,start,mid,2*curTreeNode);
        build(tree,a,mid+1,end,2*curTreeNode+1);

        tree[curTreeNode] = Math.min(tree[curTreeNode*2],tree[curTreeNode*2+1]);

    }
}
