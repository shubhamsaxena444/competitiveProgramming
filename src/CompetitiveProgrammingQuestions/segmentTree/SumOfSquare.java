package CompetitiveProgrammingQuestions.segmentTree;

import java.util.Scanner;
/*
* Sum Of Squares
Send Feedback
Segment trees are extremely useful. In particular "Lazy Propagation" (i.e. see here, for example) allows one to compute sums over a range in O(lg(n)), and update ranges in O(lg(n)) as well. In this problem you will compute something much harder:
The sum of squares over a range with range updates of 2 types:
1) increment in a range
2) set all numbers the same in a range.
Input
There will be T (T <= 25) test cases in the input file. First line of the input contains two positive integers, N (N <= 100,000) and Q (Q <= 100,000).
The next line contains N integers, each at most 1000. Each of the next Q lines starts with a number, which indicates the type of operation:

2 st nd -- return the sum of the squares of the numbers with indices in [st, nd] {i.e., from st to nd inclusive} (1 <= st <= nd <= N).

1 st nd x -- add "x" to all numbers with indices in [st, nd] (1 <= st <= nd <= N, and -1,000 <= x <= 1,000).

0 st nd x -- set all numbers with indices in [st, nd] to "x" (1 <= st <= nd <= N, and -1,000 <= x <= 1,000).
Output
For each test case output the “Case <caseno>:” in the first line and from the second line output the sum of squares for each operation of type 2. Intermediate overflow will not occur with proper use of 64-bit signed integer.
Input:
2
4 5
1 2 3 4
2 1 4
0 3 4 1
2 1 4
1 3 4 1
2 1 4
1 1
1
2 1 1
Output:
Case 1:
30
7
13
Case 2:
1*/
public class SumOfSquare {

    static class Query{
        int type;
        int l;
        int r;
        int v;
        public Query(int type,int l,int r,int v){
            this.type=type;
            this.l = l;
            this.r=r;
            this.v=v;
        }
    }
    static class test{
        int n;
        int c;
        int a[];
        Query[] list;
        public test(int n,int c){
            this.n=n;
            this.c=c;
            a =new int[n];
            list = new Query[c];
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        test[] tlist =new test[t];
        for (int j = 0; j < t; j++) {
            int n = sc.nextInt();
            int c = sc.nextInt();
            tlist[j] = new test(n, c);

            for (int i = 0; i < n; i++) {
                tlist[j].a[i] =sc.nextInt();
            }
                for (int i = 0; i < c; i++) {
                int type = sc.nextInt();
                if (type == 0 ||type == 1) {
                    tlist[j].list[i] = new Query(type, sc.nextInt(), sc.nextInt(), sc.nextInt());
                } else {
                    //type ==2 means fetch
                    tlist[j].list[i] = new Query(type, sc.nextInt(), sc.nextInt(), -1);
                }
            }
        }

        for (int j = 0; j < t; j++) {
            //build a segment tree of size 4n
            int[] tree = new int[4 * tlist[j].n];
            int[] lazy = new int[4 * tlist[j].n];
            build(tree, tlist[j].a, 0, tlist[j].n - 1, 1);
      /*  for(int i=0;i<4*n;i++){
            System.out.println(tree[i]);
        }*/
            for (int i = 0; i < tlist[j].c; i++) {
                if (tlist[j].list[i].type == 0) {
                    update0(tree,lazy, 0, tlist[j].n - 1, 1, tlist[j].list[i].l - 1, tlist[j].list[i].r-1,tlist[j].list[i].v);
                }else  if (tlist[j].list[i].type == 1) {
                    update1(tree,lazy, 0, tlist[j].n - 1, 1, tlist[j].list[i].l - 1, tlist[j].list[i].r-1,tlist[j].list[i].v);
                } else {
                    System.out.println(fetch(tree,lazy, 0, tlist[j].n - 1, 1, tlist[j].list[i].l - 1, tlist[j].list[i].r - 1));
                }
            }
        }
    }

    private static int fetch(int[] seg,int[] lazy, int start, int end, int node, int left, int right) {
        if(lazy[node]!=0)
        {
            seg[node]+=(end-start+1)*lazy[node];
            if(end!=start)
            {
                lazy[2*node]+=lazy[node];
                lazy[2*node+1]+=lazy[node];
            }
            lazy[node]=0;
        }
        if(start>right || end<left)
            return 0;
        if(left<=start && end<=right)
        {
            return seg[node];
        }
        int mid=(start+end)>>1;
        return fetch(seg,lazy,start,mid,2*node,left,right)+
                fetch(seg,lazy,mid+1,end,2*node+1,left,right);
    }

    private static void update0(int[] tree, int[] lazy, int start, int end, int node, int left, int right, int val) {
        if(lazy[node]!=0)
        {
            //update tree based on the current value of lazy
            tree[node]+=(end-start+1)*lazy[node];
            if(end!=start)
            {
                //update lazy for child updates when required
                lazy[2*node]+=lazy[node];
                lazy[2*node+1]+=lazy[node];
            }
            //since work at current node is doen, unmark lazy
            lazy[node]=0;
        }
        //if we are completely outside
        if(start>right || end<left)
            return;

        //if we are completely inside
        if(left<=start && end<=right)
        {
            tree[node]+=(end-start+1)*val;
            if(end!=start)
            {
                lazy[2*node]+=val;
                lazy[2*node+1]+=val;
            }
            return;
        }
        int mid=(start+end)>>1;
        update0(tree,lazy,start,mid,2*node,left,right,val);
        update0(tree,lazy,mid+1,end,2*node+1,left,right,val);
        tree[node]=tree[2*node]+tree[2*node+1];
    }

    private static void update1(int[] tree, int[] lazy, int start, int end, int node, int left, int right, int val) {
        if(lazy[node]!=0)
        {
            //update tree based on the current value of lazy
            tree[node]+=(end-start+1)*lazy[node];
            if(end!=start)
            {
                //update lazy for child updates when required
                lazy[2*node]+=lazy[node];
                lazy[2*node+1]+=lazy[node];
            }
            //since work at current node is doen, unmark lazy
            lazy[node]=0;
        }
        //if we are completely outside
        if(start>right || end<left)
            return;

        //if we are completely inside
        if(left<=start && end<=right)
        {
            tree[node]+=(end-start+1)*val;
            if(end!=start)
            {
                lazy[2*node]+=val;
                lazy[2*node+1]+=val;
            }
            return;
        }
        int mid=(start+end)>>1;
        update1(tree,lazy,start,mid,2*node,left,right,val);
        update1(tree,lazy,mid+1,end,2*node+1,left,right,val);
        tree[node]=tree[2*node]+tree[2*node+1];
    }

    private static void build(int[] tree,int[] a,int start,int end, int curTreeNode) {
        int n = a.length;
        //start index from 1...n in tree

        //base case is when start == end
        if(start ==end){
            tree[curTreeNode] = a[start]*a[start];
            return;
        }
        int mid = start +(end-start)/2;
        // build left and right
        build(tree,a,start,mid,2*curTreeNode);
        build(tree,a,mid+1,end,2*curTreeNode+1);

        tree[curTreeNode] = tree[curTreeNode*2] + tree[curTreeNode*2+1];

    }
}
