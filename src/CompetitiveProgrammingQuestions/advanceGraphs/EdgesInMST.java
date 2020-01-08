package CompetitiveProgrammingQuestions.advanceGraphs;
/*
* Edges in MST
Send Feedback
Edges in MST
You are given a connected weighted undirected graph without any loops and multiple edges.

Let us remind you that a graph's spanning tree is defined as an acyclic connected subgraph of the given graph that includes all of the graph's vertexes. The weight of a tree is defined as the sum of weights of the edges that the given tree contains. The minimum spanning tree (MST) of a graph is defined as the graph's spanning tree having the minimum possible weight. For any connected graph obviously exists the minimum spanning tree, but in the general case, a graph's minimum spanning tree is not unique.

Your task is to determine the following for each edge of the given graph: whether it is either included in any MST, or included at least in one MST, or not included in any MST.
Input
The first line contains two integers n and m (2 ≤ n ≤ 105, ) — the number of the graph's vertexes and edges, correspondingly. Then follow m lines, each of them contains three integers — the description of the graph's edges as "ai bi wi" (1 ≤ ai, bi ≤ n, 1 ≤ wi ≤ 106, ai ≠ bi), where ai and bi are the numbers of vertexes connected by the i-th edge, wi is the edge's weight. It is guaranteed that the graph is connected and doesn't contain loops or multiple edges.
Output
Print m lines — the answers for all edges. If the i-th edge is included in any MST, print "any"; if the i-th edge is included at least in one MST, print "at least one"; if the i-th edge isn't included in any MST, print "none". Print the answers for the edges in the order in which the edges are specified in the input.
Sample input 1
4 5
1 2 101
1 3 100
2 3 2
2 4 2
3 4 1
sample output 1
none
any
at least one
at least one
any
Sample input 2
3 3
1 2 1
2 3 1
1 3 2
sample output 2
any
any
none
sample input 3
3 3
1 2 1
2 3 1
1 3 1
sample output 3
at least one
at least one
at least one
Note
In the second sample the MST is unique for the given graph: it contains two first edges.

In the third sample any two edges form the MST for the given graph. That means that each edge is included at least in one MST.*/
public class EdgesInMST {
    /*
    * #include<bits/stdc++.h>
#define F first
#define S second
#define pb push_back
#define Maede main
using namespace std;
typedef pair <int, int> pii;
typedef pair <int, pii> pip;
const int Max=1e5+9, Mc=20;
vector <pip> E;
vector <pii> T[Max];
vector <int> V[Max];
map <int, int> mp[Max];
int we[Max], dsu[Max], h[Max], par[Max][Mc], edg[Max][Mc], ans[Max];
bool mark[Max];

bool fsort (const pip &A, const pip &B) {return (we[A.F]<we[B.F]);}
int wiw (int x) {if (dsu[x]<0) return x;return dsu[x]=wiw (dsu[x]);}
bool merge_ (int u, int v)
{
    u=wiw(u), v=wiw(v);
    if (u==v) return 0;
    if (dsu[u]<dsu[v]) //just imp who has smaller sz...
        swap (u, v);
    dsu[v]+=dsu[u];
    dsu[u]=v;
    return 1;
}

void dfs (int sv, int p, int id)
{
    h[sv]=h[p]+1;
    par[sv][0]=p;
    edg[sv][0]=we[id];
    for (int i=1; i<Mc; i++)
    {
        par[sv][i]=par[par[sv][i-1]][i-1];
        edg[sv][i]=max (edg[sv][i-1], edg[par[sv][i-1]][i-1]);
    }
    for (int i=0; i<T[sv].size(); i++) if (T[sv][i].F!=p)
        dfs (T[sv][i].F, sv, T[sv][i].S);
}

int PAR (int x, int k)
{
    for (int i=Mc-1; i>=0; i--) if ((1<<i)&k) x=par[x][i];
    return x;
}

int mx_edg (int x, int k)
{
    int t=0;
    for (int i=0; i<Mc; i++) if ((1<<i)&k)
        t=max (t, edg[x][i]), x=par[x][i];
    return t;
}

int LCA (int x, int y)
{
    if (h[x]<h[y]) swap (x, y);
    x=PAR (x, h[x]-h[y]);
    for (int i=Mc-1; i>=0; i--) if (par[x][i]!=par[y][i])
        x=par[x][i], y=par[y][i];
    return ((x==y) ? x : par[x][0]);
}

void dfs2 (int sv, int p, int id)
{
    int mx=0, mxu=0;
    for (int i=0; i<T[sv].size(); i++) if (T[sv][i].F!=p)
    {
        int u=T[sv][i].F, id=T[sv][i].S;
        dfs2 (u, sv, id);
        if (mp[u]. size()>mx) mx=mp[u].size(), mxu=u;
    }
    for (int i=0; i<T[sv].size(); i++) if (T[sv][i].F!=p) if (T[sv][i].F!=mxu)
    {
        int u=T[sv][i].F;
        while (mp[u]. size())
        {
            map <int, int> :: iterator it=mp[u]. begin();
            mp[mxu][it->F]+=it->S;
            mp[u]. erase (it);
        }
    }
    if (mxu) swap (mp[sv], mp[mxu]);
    for (int i=0; i<V[sv]. size(); i++)
    {
        int w=V[sv][i];
        if (w>0) mp[sv][w]++;
        else
        {
            mp[sv][-w]--;
            if (mp[sv][-w]==0)
            mp[sv]. erase (-w);
        }
    }
    if (mp[sv]. find(we[id])!=mp[sv]. end()) ans[id]=1;
}

int Maede()
{
    ios::sync_with_stdio(0), cin.tie(0), cout.tie(0);
    int n, m;
    cin>> n>> m;
    for (int i=1, u, v; i<=m; i++)
    {
        cin>> u>> v>> we[i];
        E. pb (pip(i, pii(u, v)));
    }
    sort (E. begin(), E. end(), fsort);
    memset (dsu, -1, sizeof dsu);
    for (int i=0; i<m; i++)
    {
        int id=E[i].F, u=E[i].S.F, v=E[i].S.S;
        if (merge_(u, v))
        {
            mark[id]=1;
            T[u]. pb (pii(v, id));
            T[v]. pb (pii(u, id));
        }
    }
    dfs (1, 0, 0);
    for (int i=0; i<m; i++)
    {
        int id=E[i].F, u=E[i].S.F, v=E[i].S.S;
        if (mark[id]) continue;
        int lca=LCA (u, v);
        int t1=mx_edg (u, h[u]-h[lca]), t2=mx_edg (v, h[v]-h[lca]);
        if (we[id]==t1 || we[id]==t2) ans[id]=1;
        else ans[id]=2;
        if (u!=lca) V[u]. pb (we[id]), V[lca]. pb (-we[id]);
        if (v!=lca) V[v]. pb (we[id]), V[lca]. pb (-we[id]);
    }
    dfs2 (1, 0, 0);
    string ANS[3]={"any", "at least one", "none"};
    for (int i=1; i<=m; i++) cout<< ANS[ans[i]]<< '\n';
}*/

}
