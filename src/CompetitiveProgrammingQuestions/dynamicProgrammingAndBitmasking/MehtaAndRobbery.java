package CompetitiveProgrammingQuestions.dynamicProgrammingAndBitmasking;
/*
* Mehta And Bank Robbery
Send Feedback
One fine day, when everything was going good, Mehta was fired from his job and had to leave all the work. So, he decided to become a member of gangster squad and start his new career of robbing. Being a novice, mehta was asked to perform a robbery task in which he was given a bag having a capacity W units. So, when he reached the house to be robbed, there lay N items each having particular weight and particular profit associated with it. But, theres a twist associated, He has first 10 primes with him, which he can use atmost once, if he picks any item x, then he can multiply his profit[x] with any of the first 10 primes and then put that item into his bag. Each prime can only be used with one particular item and one item can only have atmost one prime multiplied with its profit. Its not necessary to pick all the items. If he doesnt want to use a prime with any particular item, he can simply add the profit as it is, more specifically, 1*profit[x] for xth item will get added to its total profit, and that he can do with as many items as he wants. He cannot fill his bag more than weight W units. Each item should be picked with its whole weight, i.e it cannot be broken into several other items of lesser weight. So, now to impress his squad, he wishes to maximize the total profit he can achieve by robbing this wealthy house.
Input:
First Line of input containts two integers N and W. Next N lines contain information of ith item, describing profit and weight of the ith item, profit and weight are separated by single space.
Output:
In a single line, output the maximum profit obtainable.
Constraints:
1 <= N <= 2000, 1 <= W <= 2000

1 <= Profit[i] <= 10^9, 1 <= Weight[i] <= 2000

SAMPLE INPUT
3 4

1 1

2 1

3 1

SAMPLE OUTPUT
152

Explanation:
As all, the items can be picked, so maximum profit is equal to = 1 X 19 + 2 X 23 + 3 X 29 = 152*/
public class MehtaAndRobbery {
    /*
    *
    #include<bits/stdc++.h>
    using namespace std;
    #define ll long long

    class kanp{
    public:
        ll weight, profit;
    };

    bool cmp(kanp sack1, kanp sack2)
    {
        return sack1.profit < sack2.profit;
    }

    ll traditional_kanp_sack(kanp sack[], ll primes[], ll N, ll W)
    {
            //dp[N][P][W]

            ll ***dp = new ll**[2];

            for(ll i = 0; i < 2; i++)
               dp[i] = new ll*[11];

            for(ll i = 0; i < 2; i++)
               for(ll j = 0; j < 11; j++)
                  dp[i][j] = new ll[W + 1]();

            ll n, p, ans1, ans2;

            for(ll i = 1; i <= N; i++)
            {
               n = i & 1;

               for(ll w = 1; w <= W; w++)

               {
                   dp[n][0][w]=dp[n^1][0][w];
                   if(w >= sack[i].weight)
                       {

                           dp[n][0][w] = max(dp[n^1][0][w], sack[i].profit + dp[n^1][0][w- sack[i].weight]);
                       }

                   for(ll j = 1; j < 11; j++)
                   {
                       p = j;
                       //cout << n << "  " << p << "  " << w << "\n";

                       dp[n][p][w] = dp[n ^ 1][p][w];

                       ans1 = ans2 = INT_MIN;

                       if(w >= sack[i].weight)
                       {

                           ans2 = dp[n ^ 1][p - 1][w - sack[i].weight] + (sack[i].profit * primes[p]);
                       }

                       ans1 = max(ans1, ans2);

                       dp[n][p][w] = max(dp[n][p][w], ans1);
                   }
               }

            }

            ans1 = dp[N & 1][10][W];

            for(ll i = 0; i < 2; i++)
              for(ll j = 0; j < 11; j++)
                delete [] dp[i][j];

            for(ll i = 0; i < 2; i++)
               delete [] dp[i];

               delete [] dp;

        return ans1;
    }

    int main()
    {
       ll t;

       t = 1;

       while(t--)
       {

        ll N, W;

        cin >> N >> W;

        kanp sack[N + 1];

        sack[0].weight = sack[0].profit = 0;

        for(ll i = 1; i <= N; i++)
            cin >> sack[i].profit >> sack[i].weight;;

        sort(sack, sack + (N+1), cmp);

        ll primes[11] = {1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29};

        ll value = traditional_kanp_sack(sack, primes, N, W);

        cout << value << "\n";

      }

        return 0;
    }

 */
}
