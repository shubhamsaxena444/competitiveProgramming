package CompetitiveProgrammingQuestions.segmentTree;

import java.util.Scanner;
/*
* Maximum Sum In Subarray
Send Feedback
You are given a sequence A[1], A[2], ..., A[N] . ( |A[i]| ≤ 15007 , 1 ≤ N ≤ 50000 ). A query is defined as follows:
Query(x,y) = Max { a[i]+a[i+1]+...+a[j] ; x ≤ i ≤ j ≤ y }.
Given M queries, your program must output the results of these queries.
Input
The first line of the input file contains the integer N.
In the second line, N numbers follow.
The third line contains the integer M.
M lines follow, where line i contains 2 numbers xi and yi.
Output
Your program should output the results of the M queries, one
query per line.
Sample Input:
3
-1 2 3
1
1 2
Sample Output:
2*/
public class MaxSubArraySum {
    static class Node{
        int maxPrefixSum;
        int maxSuffixSum;
        int totalSum;
        int maxSubarraySum;
        public Node(int maxPrefixSum,int maxSuffixSum,int totalSum,int maxSubarraySum ){
            this.maxPrefixSum =maxPrefixSum;
            this.maxSuffixSum =maxSuffixSum;
            this.totalSum =totalSum;
            this.maxSubarraySum =maxSubarraySum;
        }
    }
    static class Query{
        int l;
        int r;
        public Query(int l,int r){
            this.l = l;
            this.r=r;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i =0;i<n;i++){
            a[i] =sc.nextInt();
        }
        int q = sc.nextInt();
        Query[] list =new Query[q];
        for(int i =0;i<q;i++){
            list[i] =new Query(sc.nextInt(),sc.nextInt());
        }
        //build a segment tree of size 4n
        Node[] tree = new Node[4*n];
        build(tree,a,0,n-1,1);
//        for(int i=0;i<4*n;i++){
//            System.out.println(tree[i]);
//        }
        for(int i =0;i<q;i++){
                Node ans = query(tree,0,n-1,1,list[i].l-1,list[i].r-1);
                System.out.println(ans.maxSubarraySum);
            }
    }



    private static Node query(Node[] tree, int start, int end, int curTreeNode, int left, int right) {

        //base case is when start == end
        if(left>right) return new Node(0,0,0,0);

        //we are completely inside the range [left,right]
        if(left <= start && right >= end){
            return tree[curTreeNode];
        }

        //completely outside
        if(right < start || left > end){
            return new Node(0,0,0,0);
        }
        int mid = start +(end-start)/2;
        // partially in and partially out
        Node leftChild = query(tree,start,mid,2*curTreeNode,left,right);
        Node rightChild = query(tree,mid+1,end,2*curTreeNode+1,left,right);


        int maxPrefixSum = Math.max(leftChild.maxPrefixSum,
                leftChild.totalSum +
                        rightChild.maxPrefixSum);

        int maxSuffixSum = Math.max(rightChild.maxSuffixSum,
                rightChild.totalSum +
                        leftChild.maxSuffixSum);

        int totalSum = leftChild.totalSum +
                rightChild.totalSum;

        int maxSubarraySum = Math.max(Math.max(leftChild.maxSubarraySum,
                rightChild.maxSubarraySum),
                leftChild.maxSuffixSum +
                        rightChild.maxPrefixSum);
        return new Node(maxPrefixSum,maxSuffixSum,totalSum,maxSubarraySum );


    }



    private static void build(Node[] tree, int[] a, int start, int end, int curTreeNode) {
        int n = a.length;
        //start index from 1...n in tree

        //base case is when start == end
        if(start ==end){
            if(a[start] >0) {
                tree[curTreeNode] = new Node(a[start],a[start],a[start], a[start]);
            }else{
                tree[curTreeNode] = new Node(a[start],a[start],a[start], a[start]);
            }
            return;
        }
        int mid = start +(end-start)/2;
        // build left and right
        build(tree,a,start,mid,2*curTreeNode);
        build(tree,a,mid+1,end,2*curTreeNode+1);
        Node leftChild = tree[2*curTreeNode];
        Node rightChild = tree[2*curTreeNode+1];

        int maxPrefixSum = Math.max(leftChild.maxPrefixSum,
                leftChild.totalSum +
                        rightChild.maxPrefixSum);

        int maxSuffixSum = Math.max(rightChild.maxSuffixSum,
                rightChild.totalSum +
                        leftChild.maxSuffixSum);

        int totalSum = leftChild.totalSum +
                rightChild.totalSum;

        int maxSubarraySum = Math.max(Math.max(leftChild.maxSubarraySum,
                rightChild.maxSubarraySum),
                leftChild.maxSuffixSum +
                        rightChild.maxPrefixSum);
        tree[curTreeNode] = new Node(maxPrefixSum,maxSuffixSum,totalSum,maxSubarraySum );
        return;
    }
}