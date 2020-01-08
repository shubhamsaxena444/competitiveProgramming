package CompetitiveProgrammingQuestions.segmentTree;

import java.util.Scanner;
/*
* 2 vs 3
Send Feedback
The fight for the best number in the globe is going to finally come to an end.The top two contenders for the best number are number 2 and number 3.It's the final the entire world was waiting for. Expectorates from all across the globe came to witness the breath taking finals.
The finals began in an astonishing way.A common problem was set for both of them which included both these number.The problem goes like this.
Given a binary string (that is a string consisting of only 0 and 1). They were supposed to perform two types of query on the string.
Type 0: Given two indices l and r.Print the value of the binary string from l to r modulo 3.

Type 1: Given an index l flip the value of that index if and only if the value at that index is 0.
The problem proved to be a really tough one for both of them.Hours passed by but neither of them could solve the problem.So both of them wants you to solve this problem and then you get the right to choose the best number in the globe.
Input:
The first line contains N denoting the length of the binary string .
The second line contains the N length binary string.Third line contains the integer Q indicating the number of queries to perform.
This is followed up by Q lines where each line contains a query.
Output:
For each query of Type 0 print the value modulo 3.
Constraints:
1<= N <=10^5

1<= Q <= 10^5

0 <= l <= r < N
Sample Input
5
10010
6
0 2 4
0 2 3
1 1
0 0 4
1 1
0 0 3
Sample Output
2
1
2
1*/
public class TwoVsThree {
    static int arr[] = new int[100000];

   static void  filleArr(int[] arr) {
       arr[0] =1;
        for(int i =1;i<arr.length;i++){
            arr[i] = (arr[i-1]*2)%3;
        }
    }

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

        int n = Integer.parseInt(sc.nextLine());
        String s = sc.nextLine();
        int q = Integer.parseInt(sc.nextLine());
        Query[] list =new Query[(int) q];
        for(int i =0;i<q;i++) {
            int type = sc.nextInt();
            if (type == 0) {
                list[(int) i] = new Query(type, sc.nextInt(), sc.nextInt());
            } else {
                list[(int) i] = new Query(type, sc.nextInt(),-1);
            }
        }
        //build a segment tree of size 4n
        int[] tree = new int[(int) (4*n)];
        build(tree,s,0,n-1,1);
        filleArr(arr);
      /*  for(int i=0;i<4*n;i++){
            System.out.println(tree[i]);
        }*/
        for(int i =0;i<q;i++){
            if(list[(int) i].type == 1){
                //update
                update(tree,0,n-1,1,list[(int) i].l,1);
            }else{
                System.out.println(query(tree,0,n-1,1,list[ i].l,list[ i].r));
            }
        }
    }

    private static int query(int[] tree, int start, int end, int curTreeNode, int left, int right) {

        //base case is when start == end

        //we are completely inside the range [left,right]
        if(left <= start && right >= end){

            int right_bits = right - end;

            return ((tree[curTreeNode]%3)*arr[right_bits])%3;
//            return tree[curTreeNode];
        }

        //completely outside
        if(right < start || left > end){
            return 0;
        }
        int mid = start +(end-start)/2;
        // partially in and partially out
        int leftVal = query(tree,start,mid,2*curTreeNode,left,right);
        int rightVal =  query(tree,mid+1,end,2*curTreeNode+1,left,right);
//        int newVal = ((leftVal*arr[end-mid])%3 +rightVal%3)%3;
//        return newVal;
        return (leftVal+rightVal)%3;
    }

    private static void update(int[] tree,int start,int end, int curTreeNode, int index, int value) {
        //start index from 1...n in tree
        //update only if present bit is 0 .. set to 1;

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
        int numberOfShiftedBits = arr[end-mid];
        int newNumber = ((numberOfShiftedBits* tree[curTreeNode*2]%3)%3 + tree[curTreeNode*2+1]%3)%3; //combining left and right

        tree[ curTreeNode] = newNumber;
    }



    private static void build(int[] tree,String a,int start,int end, int curTreeNode) {
        int n = a.length();
        //start index from 1...n in tree

        //base case is when start == end
        if(start ==end){
            tree[ curTreeNode] = a.charAt(start) -'0';
            return;
        }
        int mid = start +(end-start)/2;
        // build left and right
        build(tree,a,start,mid,2*curTreeNode);
        build(tree,a,mid+1,end,2*curTreeNode+1);
        int numberOfShiftedBits = arr[end-mid];
        int newNumber = ((numberOfShiftedBits* tree[curTreeNode*2]%3)%3 + tree[curTreeNode*2+1]%3)%3; //combining left and right

        tree[ curTreeNode] = newNumber;

    }
}