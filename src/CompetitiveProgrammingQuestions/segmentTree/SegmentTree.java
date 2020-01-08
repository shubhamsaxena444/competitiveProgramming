package CompetitiveProgrammingQuestions.segmentTree;

import java.util.Scanner;

public class SegmentTree {
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
