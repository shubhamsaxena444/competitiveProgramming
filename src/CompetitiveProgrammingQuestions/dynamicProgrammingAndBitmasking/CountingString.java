package CompetitiveProgrammingQuestions.dynamicProgrammingAndBitmasking;
/*
* Counting Strings
Send Feedback
Given a string 's' consisting of upper case alphabets, i.e. from 'A' to 'Z'. Your task is to find how many strings 't' with length equal to that of 's', also consisting of upper case alphabets are there satisfying the following conditions:
-> String 't' is lexicographical larger than string 's'.
-> When you write both 's' and 't' in the reverse order, 't' is still lexicographical larger than 's'.
Find out number of such strings 't'. As the answer could be very large, take modulo 10^9 + 7.*/
public class CountingString {
    /*
    #include<bits/stdc++.h>
#define FAST ios_base::sync_with_stdio(0),cin.tie(0),cout.tie(0)
#define dofloat cout<<fixed<<setprecision(8)
#define pb push_back
#define mp make_pair
#define fi first
#define se second
#define bitcount __builtin_popcount
#define all(vec) vec.begin(),vec.end()
#define rall(vec) vec.rbegin(),vec.rend()
using namespace std;
typedef long long ll;
typedef long double ld;
typedef vector<long long> vl;
typedef pair<long long,long long> pll;
typedef vector<pair<long long,long long> > vll;
typedef vector<pair<int,int> > vii;
typedef vector<int> vi;
typedef pair<int,int> ii;
const long long MOD=1000000007;
const long long MAX=100005;
const long double PI=3.14159265359;
const long double G=9.807;
const long long INF=1e18;
const long double EPS=1e-6;
ll gcd(ll a,ll b){return b?gcd(b,a%b):a;}
bool isprime(ll a){
  if(a==2)return 1;
  if(!(a&1))return 0;
  for(ll i=3;i*i<=a;i+=2)
    if(a%i==0)return 0;
  return 1;
}
ll mpow(ll base,ll exponent,ll modulus){
  if(modulus==1)return 0;
  long long result = 1;
  base=base%modulus;
  while(exponent){
    if(exponent%2 == 1)
    result=(result*base)%modulus;
    exponent=exponent>>1;
    base=(base*base)%modulus;
  }
  return result;
}
ll minv(ll a,ll mod){
  ll _gcd=gcd(a,mod);
  assert(_gcd==1);
  return mpow(a,mod-2,mod);
}
string s;
int countStrings(char* str) {

FAST;
    s.assign(str,strlen(str));
//cin>>s;
s.pb('Z');
int n = s.length();
ll ways=0,lexi=0;
for(int i=n-2;i>=0;--i){
  ll upfront = 'Z'-s[i];
  ll upback = 'Z'-s[i+1];
  lexi=(lexi*26)%MOD;
  lexi=(lexi+upback)%MOD;
  ways=(ways+(upfront*(lexi+1))%MOD)%MOD;
}
//cout<<ways<<'\n';
return ways;
}

    * */
}
