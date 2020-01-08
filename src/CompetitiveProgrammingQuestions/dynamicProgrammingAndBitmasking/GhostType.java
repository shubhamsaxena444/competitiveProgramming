package CompetitiveProgrammingQuestions.dynamicProgrammingAndBitmasking;
/*
* Ghost Type
Send Feedback
Gengar has got an integer N. Now using his ghostly powers, he can create the permutation from 1 to N of this given number.
Since, he's a special kind of Poke'mon, so he thinks he deserves special permutations. He wants to find the total number of special permutations of length N, consisting of the integers from 1 to N
.

A permutation is called special if it satisfies following condition:
If Ap & Aq == Ap, then p < q, where p and q are two distinct indices of permutation and A is the permutation itself. "&" denotes the bitwise and operation.
Help Gengar in finding the number of such permutations.
Input format:
The only line of input will consist of a single integer N denoting the length of the permutation.
Output format:
Output the total number of special permutations of length N
.

Constraints:
1 ≤ N ≤ 20

SAMPLE INPUT
4

SAMPLE OUTPUT
8

Explanation
All the special permutations of length 4 are: 1 2 3 4

1 2 4 3

1 4 2 3

2 1 3 4

2 1 4 3

2 4 1 3

4 1 2 3

4 2 1 3*/
public class GhostType {
/*
* #include <iostream>
using namespace std;
#define ll long long
int main()
{
    int n;
    cin>>n;
    ll dp[(1<<n)]={0};
    dp[0]=1;
    for(int mask=0;mask<(1<<n);mask++)
    {
        for(int j=1;j<=n;j++)
        {
            int J=j-1;
            if(!((1<<J)&mask))
            {
                bool ch=true;
                for(int k=j+1;k<=n;k++)
                {
                    int K=k-1;
                    //cout<<j<<" "<<k<<endl;
                    if((mask&(1<<K))&&((j&k)==j))
                    {
                        ch=false;
                        break;
                    }
                }
                dp[mask|(1<<J)]+=dp[mask]*ch;
            }
        }
    }
    cout<<dp[(1<<n)-1]<<endl;


}
 */
}
