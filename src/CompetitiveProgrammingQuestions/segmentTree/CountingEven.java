package CompetitiveProgrammingQuestions.segmentTree;

import java.util.Scanner;
/*
* Counting Even/Odd
Send Feedback
Ashu and Shanu are best buddies. One day Shanu gives Ashu a problem to test his intelligence.He gives him an array of N natural numbers and asks him to solve the following queries:-
Query 0 :- modify the element present at index i to x.
Query 1:- count the number of even numbers in range l to r inclusive.
Query 2:- count the number of odd numbers in range l to r inclusive.
Input:
First line of the input contains the number N. Next line contains N natural numbers.
Next line contains an integer Q followed by Q queries.

0 x y - modify the number at index x to y.

1 x y - count the number of even numbers in range l to r inclusive.

2 x y - count the number of odd numbers in range l to r inclusive.
Constraints:
1<=N,Q<=10^5

1<=l<=r<=N

0<=Ai<=10^9

1<=x<=N

0<=y<=10^9
Note:-
indexing starts from 1.
Sample Input
6
1 2 3 4 5 6
4
1 2 5
2 1 4
0 5 4
1 1 6
Sample Output
2
2
4*/
public class CountingEven{
    static class Query{
        int type;
        int l;
        int r;
        public Query(int type,int l,int r){
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
            list[i] =new Query(sc.nextInt(),sc.nextInt(),sc.nextInt());
        }
        //build a segment tree of size 4n
        int[] tree = new int[4*n];
        build(tree,a,0,n-1,1);
//        for(int i=0;i<4*n;i++){
//            System.out.println(tree[i]);
//        }
        for(int i =0;i<q;i++){
            if(list[i].type == 0){
                update(tree,0,n-1,1,list[i].l-1,list[i].r);
            }else if(list[i].type == 1){
                System.out.println(getEvenCount(tree,0,n-1,1,list[i].l-1,list[i].r-1));
            }else{
                System.out.println(list[i].r - list[i].l+1 - getEvenCount(tree,0,n-1,1,list[i].l-1,list[i].r-1));
            }
        }
    }

    private static int getEvenCount(int[] tree, int start, int end, int curTreeNode, int left, int right) {

        //base case is when start == end

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


        return getEvenCount(tree,start,mid,2*curTreeNode,left,right)+
                getEvenCount(tree,mid+1,end,2*curTreeNode+1,left,right);

    }

    private static void update(int[] tree,int start,int end, int curTreeNode, int index, int value) {
        //start index from 1...n in tree

        //base case is when start == end
        if(start == end && start ==index){

            if (value % 2 == 0){
                tree[curTreeNode] = 1;
            }else{
                tree[curTreeNode] = 0;
            }
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

        tree[curTreeNode] = tree[curTreeNode*2]+tree[curTreeNode*2+1];
    }



    private static void build(int[] tree,int[] a,int start,int end, int curTreeNode) {
        int n = a.length;
        //start index from 1...n in tree

        //base case is when start == end
        if(start ==end) {
            if (a[start] % 2 == 0){
                tree[curTreeNode] = 1;
            }else{
                tree[curTreeNode] = 0;
            }
            return;
        }
        int mid = start +(end-start)/2;
        // build left and right
        build(tree,a,start,mid,2*curTreeNode);
        build(tree,a,mid+1,end,2*curTreeNode+1);

        tree[curTreeNode] = tree[curTreeNode*2]+tree[curTreeNode*2+1];

    }
}