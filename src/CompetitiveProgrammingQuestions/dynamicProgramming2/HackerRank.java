package CompetitiveProgrammingQuestions.dynamicProgramming2;

import java.util.*;

public class HackerRank {

    static String findQualifiedNumbers(int[] numberArray) {

        List<Integer> res = new ArrayList<>();
        for(int i =0;i<numberArray.length;i++){
            boolean isP[] = new boolean[3];
            if(check(numberArray[i],isP)){
                res.add(numberArray[i]);
            }

        }
        StringBuilder s = new StringBuilder("");
        Collections.sort(res);
        res.forEach(x->s.append(x).append(","));
        return s.length()>0  ? s.toString().substring(0,s.length()-1): "-1";
    }

    private static boolean check(int n, boolean[] isP) {
        while(n>0){
            int r = n%10;
            if(r == 1){
                isP[0] =true;
            }else if(r ==2){
                isP[1] =true;
            }else if(r ==3){
                isP[2] =true;
            }
            n = n/10;
        }
        if(isP[0] && isP[1] && isP[2]){
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
      //  System.out.println(findQualifiedNumbers(new int[]{123,123,546213,23435,1,316}));

        String a ="2112";

        System.out.println(calculatePossibleCombinations(a));
    }

    public static long calculatePossibleCombinations(String s) {

        int n = s.length();
        //30,40,...90 can not be handled
        //no more than one 0 can exist
        //recusively i can either pick first char or if it is 1,2(1-6) , I can pick first two of them


       // return count(s,s.length());
        //lets create an arr m of size n+1 with m[0] as the answer
        long m[] = new long[n+1];
        //initiLISE WITH -1
        for(int i =0;i<n+1;i++){
            m[i] =-1;
        }
        return function(s,0,m);
    }

    private static long function(String s, int index, long m[]) {
        int n =s.length();

        //whenever calling functon, check if already exists, if yes return else put
        if(index >=n-1){
            return 1;
        }

        if(m[index]>-1){
            return m[index];
        }
        if(s.charAt(index) =='0'){
            m[index] = 0;
            return 0;
        }

        if(index+1 <n && s.charAt(index) =='1' || (s.charAt(index) =='2' && s.charAt(index+1) >='0'
                && s.charAt(index+1) <='6')){
            m[index] = function(s,index+1,m) + function(s,index+2,m);
            return m[index];
        }
        m[index] =function(s,index+1,m);
         return m[index];
    }

    static long count(String s,
                               int n)
    {
        //create a n sized memo table and initialize with -1 such that m[i]
        // represents count of words possible starting after i (inclusive) till n-1
        long m[] = new long[n + 1];
        m[0] = 1; //size == 0
        m[1] = 1;
        if(s.charAt(0)=='0')
            return 0;
        for (int i = 2; i <= n; i++)
        {
            m[i] = 0;


            if (s.charAt(i - 1) > '0')
                m[i] = m[i - 1];


            if (s.charAt(i - 2) == '1' ||
                    (s.charAt(i - 2) == '2' &&
                            s.charAt(i - 1) < '7'))
                m[i] += m[i - 2];
        }
        return m[n];
    }
}
