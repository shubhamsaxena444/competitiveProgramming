package CompetitiveProgrammingQuestions.SearchingAndSorting;
import java.util.*;
import java.util.stream.Collectors;

/*Momos Market
Send Feedback
Shreya loves to eat momos. Her mother gives her money to buy vegetables but she manages to save some money out of it daily. After buying vegetables, she goes to "Momos Market", where there are ‘n’ number of shops of momos. Each of the shops of momos has a rate per momo. She visits the market and starts buying momos (one from each shop) starting from the first shop. She will visit the market for ‘q’ days. You have to tell that how many momos she can buy each day if she starts buying from the first shop daily. She cannot use the remaining money of one day on some other day. But she will save them for other expenses in the future, so, you also need to tell the sum of money left with her at the end of each day.
Input Format:
First line will have an integer ‘n’ denoting the number of shops in market.
Next line will have ‘n’ numbers denoting the price of one momo of each shop.
Next line will have an integer ‘q’ denoting the number of days she will visit the market.
Next ‘q’ lines will have one integer ‘X’ denoting the money she saved after buying vegetables.
Constraints:
1 <= n <= 10^5
1 <= q <= 10^5
1 <= X <= 10^9
Output:
There will be ‘q’ lines of output each having two space separated integers denoting number of momos she can buy and amount of money she saved each day.
Sample Input:
4
2 1 6 3
1
11
Sample Output:
3 2
Explanation:
Shreya visits the "Momos Market" for only one day. She has 11 INR to spend. She can buy 3 momos, each from the first 3 shops. She would 9 INR (2 + 1 + 6) for the same */
public class MomosMarket {

    public static void main(String[] args) {        //   int t = in.nextInt();
        Scanner in = new Scanner(System.in);

        // while(t-->0) {
        int n = in.nextInt();
        //  int c = in.nextInt();
        int[] price = new int[n];
        for (int i = 0; i < n; i++) {
            price[i] = in.nextInt();
        }

        int q = in.nextInt();
        //  int c = in.nextInt();
        int[] day = new int[q];
        for (int i = 0; i < q; i++) {
            day[i] = in.nextInt();
        }
        momos(price,n,day,q);
        //    System.out.println(inversionCount(price,0,price.length-1));
        //Arrays.stream(price).forEach(x-> {System.out.print(x+",");});
        //}
    }

    private static void momos(int[] price, int n, int[] day, int q) {
        //calc the prefix sum of price array
        for(int i =1;i<n;i++){
            price[i] += price[i-1];
        }
        List<Integer> l = new ArrayList<Integer>();
        l= Arrays.stream(price).boxed().collect(Collectors.toList());
        //for each day , find the lower bound in price array with logn complexity and print the output
        //https://stackoverflow.com/questions/15603832/java-equivalent-of-c-equal-range-or-lower-bound-upper-bound
        for(int i =0;i<q;i++){
            int ind = Collections.binarySearch(l, day[i]);
            //if element is found , then the position of the element in price is the total momos had and money remaining is 0
            if(ind>=0){
                System.out.println(ind+1 +" "+ 0);
            }else if (ind == -1) {
                //there may be edge cases while calc lower_bound index, when ind = -1, in such case, momos had = 0, money left = day[i]
                System.out.println(0 +" "+ day[i]);
            }else
                //else, position of th e lower bound is the total momos had and q-lower_index is the money remaining.

                System.out.println(Math.abs(ind)-1 +" "+ (day[i] - price[Math.abs(ind)-2]));
        }
    }
}
