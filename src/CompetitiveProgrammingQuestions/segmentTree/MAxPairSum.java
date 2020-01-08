package CompetitiveProgrammingQuestions.segmentTree;

import java.util.Scanner;
/*
* Maximum Pair Sum
Send Feedback
You are given a sequence A[1], A[2], ..., A[N], ( 0 ≤ A[i] ≤ 10^8 , 2 ≤ N ≤ 10^5 ). There are two types of operations and they are defined as follows:
Update:
This will be indicated in the input by a 'U' followed by space and then two integers i and x.
U i x, 1 ≤ i ≤ N, and x, 0 ≤ x ≤ 10^8.
This operation sets the value of A[i] to x.
Query:
This will be indicated in the input by a 'Q' followed by a single space and then two integers i and j.
Q x y, 1 ≤ x < y ≤ N.
You must find i and j such that x ≤ i, j ≤ y and i != j, such that the sum A[i]+A[j] is maximized. Print the sum A[i]+A[j].
Input
The first line of input consists of an integer N representing the length of the sequence.
Next line consists of N space separated integers A[i]. Next line contains an integer Q, Q ≤ 10^5, representing the number of operations. Next Q lines contain the operations.
Output
 Output the maximum sum mentioned above, in a separate line, for each Query.
Input:
5
1 2 3 4 5
6
Q 2 4
Q 2 5
U 1 6
Q 1 5
U 1 7
Q 1 5
Output:
7
9
11
12*/
public class MAxPairSum {
    static class Node{
        int max;
        int secMax;
        public Node(int max,int secMax){
            this.max =max;
            this.secMax =secMax;
        }

        @Override
        public String toString() {
            return max +","+ secMax;
        }
    }
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
            int[] a = new int[n];
            for(int i =0;i<n;i++){
                a[i] =sc.nextInt();
            }
            int q = sc.nextInt();
            Query[] list =new Query[q];
            for(int i =0;i<q;i++){
                list[i] =new Query(sc.next().charAt(0),sc.nextInt(),sc.nextInt());
            }
            //build a segment tree of size 4n
            Node[] tree = new Node[4*n];
            build(tree,a,0,n-1,1);
//        for(int i=0;i<4*n;i++){
//            System.out.println(tree[i]);
//        }
            for(int i =0;i<q;i++){
                if(list[i].type == 'U'){
                    update(tree,0,n-1,1,list[i].l-1,list[i].r);
                }else{
                    Node ans = getMax(tree,0,n-1,1,list[i].l-1,list[i].r-1);
                    System.out.println(ans.max+ans.secMax);
                }
            }
        }

/*
    private static int getMaxPairSum(int[] tree, int start, int end, int curTreeNode, int left, int right) {
            int mid = left +(right-left)/2;
            return getMax(tree,start,end,curTreeNode,left,mid)+getMax(tree,start,end,curTreeNode,mid+1,right);
    }
*/

    private static Node getMax(Node[] tree, int start, int end, int curTreeNode, int left, int right) {

            //base case is when start == end
        if(left>right) return new Node(Integer.MIN_VALUE,Integer.MIN_VALUE);

            //we are completely inside the range [left,right]
            if(left <= start && right >= end){
                return tree[curTreeNode];
            }

            //completely outside
            if(right < start || left > end){
                return new Node(Integer.MIN_VALUE,Integer.MIN_VALUE);
            }
            int mid = start +(end-start)/2;
            // partially in and partially out
        Node leftNode = getMax(tree,start,mid,2*curTreeNode,left,right);
        Node rightNode = getMax(tree,mid+1,end,2*curTreeNode+1,left,right);


        return new Node( Math.max(leftNode.max,rightNode.max),
                Math.min(Math.max(leftNode.max,rightNode.secMax), Math.max(leftNode.secMax,rightNode.max)));

/*

            return Math.max(getMax(tree,start,mid,2*curTreeNode,left,right),
                    getMax(tree,mid+1,end,2*curTreeNode+1,left,right));
*/

        }

        private static void update(Node[] tree,int start,int end, int curTreeNode, int index, int value) {
            //start index from 1...n in tree

            //base case is when start == end
            if(start == end && start ==index){
                tree[curTreeNode].max = value;
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

            Node left = tree[2*curTreeNode];
            Node right = tree[2*curTreeNode+1];

            tree[curTreeNode].max =  Math.max(left.max,right.max);
            tree[curTreeNode].secMax = Math.min(Math.max(left.max,right.secMax), Math.max(left.secMax,right.max));
        }



        private static void build(Node[] tree,int[] a,int start,int end, int curTreeNode) {
            int n = a.length;
            //start index from 1...n in tree

            //base case is when start == end
            if(start ==end){
                tree[curTreeNode] = new Node(a[start],Integer.MIN_VALUE);
                return;
            }
            int mid = start +(end-start)/2;
            // build left and right
            build(tree,a,start,mid,2*curTreeNode);
            build(tree,a,mid+1,end,2*curTreeNode+1);
            Node left = tree[2*curTreeNode];
            Node right = tree[2*curTreeNode+1];


            tree[curTreeNode] = new Node( Math.max(left.max,right.max),
                    Math.min(Math.max(left.max,right.secMax), Math.max(left.secMax,right.max)));
            return;
        }
    }

