package CompetitiveProgrammingQuestions.advanceGraphs;
/*
* FILLMTR
Send Feedback
Fill The Matrix
A matrix B (consisting of integers) of dimension N × N is said to be good if there exists an array A (consisting of integers) such that B[i][j] = |A[i] - A[j]|, where |x| denotes absolute value of integer x.

You are given a partially filled matrix B of dimension N × N. Q of the entries of this matrix are filled by either 0 or 1. You have to identify whether it is possible to fill the remaining entries of matrix B (the entries can be filled by any integer, not necessarily by 0 or 1) such that the resulting fully filled matrix B is good.
Input
The first line of the input contains an integer T denoting the number of test cases.

The first line of each test case contains two space separated integers N, Q.

Each of the next Q lines contain three space separated integers i, j, val, which means that B[i][j] is filled with value val.
Output
For each test case, output "yes" or "no" (without quotes) in a single line corresponding to the answer of the problem.
Constraints
1 ≤ T ≤ 10^6
2 ≤ N ≤ 10^5
1 ≤ Q ≤ 10^6
1 ≤ i, j ≤ N
0 ≤ val ≤ 1
Sum of each of N, Q over all test cases doesn't exceed 106
Input
4
2 2
1 1 0
1 2 1
2 3
1 1 0
1 2 1
2 1 0
3 2
2 2 0
2 3 1
3 3
1 2 1
2 3 1
1 3 1
Output
yes
no
yes
no
* */
public class FillTheMatrix {
/*
* #include <bits/stdc++.h>
using namespace std;
#define int long long
#define FastIO ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);

vector <pair <int, int> > g[100005];
int diff[100005];
int mark[100005];
int ans;

int dfs(int u)
{
    mark[u] = 1;

    for(auto &x : g[u])
    {
        if(mark[x.first] == 0)
        {
            diff[x.first] = diff[u] ^ x.second;
            dfs(x.first);
        }
        else if(mark[x.first] == 1)
        {
            if(diff[u] ^ x.second != diff[x.first])
                ans = 0;
        }
    }

    mark[u] = 2;
}

signed main()
{
    FastIO;
    int T;
    cin>>T;
    while(T--)
    {
        ans = 1;
        int n,q;
        int a,b,c;
        map <pair <int, int>, int> h;
        cin>>n>>q;
        for(int i=0; i<=n; i++)
        {
            g[i].clear();
            mark[i] = 0;
            diff[i] = 0;
        }

        for(int i=0; i<q; i++)
        {
            cin>>a>>b>>c;
            g[a].push_back({b, c});
            g[b].push_back({a, c});

            if(a > b) swap (a, b);
            if(a == b && c)
                ans = 0;
            else if(h[{a, b}])
            {
//                cout<<h[{a, b}]<<" ";
                if(h[{a, b}] != c+1)
                    ans=0;
            }
            h[{a, b}] = c+1;
        }

        for(int i=1; i<=n; i++)
        {
            if(mark[i] == 0)
                dfs(i);
        }

        if(ans)
            cout<<"yes\n";
        else
            cout<<"no\n";
    }
}

* */
}
