package CompetitiveProgrammingQuestions.segmentTree;

import java.util.Scanner;
/*
* Horrible Queries
Send Feedback
World is getting more evil and it's getting tougher to get into the Evil League of Evil. Since the legendary Bad Horse has retired, now you have to correctly answer the evil questions of Dr. Horrible, who has a PhD in horribleness (but not in Computer Science). You are given an array of N elements, which are initially all 0. After that you will be given C commands. They are -
0 p q v - you have to add v to all numbers in the range of p to q (inclusive), where p and q are two indexes of the array.

1 p q - output a line containing a single integer which is the sum of all the array elements between p and q (inclusive)
Input
In the first line you'll be given T, number of test cases.

Each test case will start with N (N <= 100 000) and C (C <= 100 000). After that you'll be given C commands in the format as mentioned above. 1 <= p, q <= N and 1 <= v <= 10^7.
Output
Print the answers of the queries.
Input:
1
8 6
0 2 4 26
0 4 8 80
0 4 5 20
1 8 8
0 5 7 14
1 4 8
Output:
80
508*/
public class Horrible {
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
            for (int i = 0; i < c; i++) {
                int type = sc.nextInt();
                if (type == 0) {
                    tlist[j].list[i] = new Query(type, sc.nextInt(), sc.nextInt(), sc.nextInt());
                } else {
                    //type ==1 means fetch
                    tlist[j].list[i] = new Query(type, sc.nextInt(), sc.nextInt(), -1);
                }
            }
        }

        for (int j = 0; j < t; j++) {
            //build a segment tree of size 4n
            long[] tree = new long[4 * tlist[j].n];
            long[] lazy = new long[4 * tlist[j].n];
            build(tree, tlist[j].a, 0, tlist[j].n - 1, 1);
      /*  for(int i=0;i<4*n;i++){
            System.out.println(tree[i]);
        }*/
            for (int i = 0; i < tlist[j].c; i++) {
                if (tlist[j].list[i].type == 0) {
                    update(tree,lazy, 0, tlist[j].n - 1, 1, tlist[j].list[i].l - 1, tlist[j].list[i].r-1,tlist[j].list[i].v);
                } else {
                    System.out.println(fetch(tree,lazy, 0, tlist[j].n - 1, 1, tlist[j].list[i].l - 1, tlist[j].list[i].r - 1));
                }
            }
        }
    }

    private static long fetch(long[] seg,long[] lazy, int start, int end, int node, int left, int right) {
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
        int mid=start + (end-start)/2;
        return fetch(seg,lazy,start,mid,2*node,left,right)+
                fetch(seg,lazy,mid+1,end,2*node+1,left,right);
    }

    private static void update(long[] tree, long[] lazy, int start, int end, int node, int left, int right, int val) {
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
        int mid=start + (end-start)/2;
        update(tree,lazy,start,mid,2*node,left,right,val);
        update(tree,lazy,mid+1,end,2*node+1,left,right,val);
        tree[node]=tree[2*node]+tree[2*node+1];
    }


    private static void build(long[] tree,int[] a,int start,int end, int curTreeNode) {
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

        tree[curTreeNode] = tree[curTreeNode*2] + tree[curTreeNode*2+1];

    }
}
