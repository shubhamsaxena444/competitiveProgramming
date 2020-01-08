package CompetitiveProgrammingQuestions.dynamicProgramming1;

import java.util.Scanner;
/*
* Jon Snow and his favourite number
Send Feedback
Jon Snow now has to fight with White Walkers. He has n rangers, each of which has his own strength. Also Jon Snow has his favourite number x. Each ranger can fight with a white walker only if the strength of the white walker equals his strength. He however thinks that his rangers are weak and need to improve. Jon now thinks that if he takes the bitwise XOR of strengths of some of rangers with his favourite number x, he might get soldiers of high strength. So, he decided to do the following operation k times:
Arrange all the rangers in a straight line in the order of increasing strengths.
Take the bitwise XOR of the strength of each alternate ranger with x and update it's strength.
Suppose, Jon has 5 rangers with strengths [9, 7, 11, 15, 5] and he performs the operation 1 time with x = 2. He first arranges them in the order of their strengths, [5, 7, 9, 11, 15]. Then he does the following:
The strength of first ranger is updated to 5 xor 2, i.e. 7.
The strength of second ranger remains the same, i.e. 7.
The strength of third ranger is updated to 9 xor 2, i.e. 11.
The strength of fourth ranger remains the same, i.e. 11.
The strength of fifth ranger is updated to 15 xor 2, i.e. 13.
The new strengths of the 5 rangers are [7, 7, 11, 11, 13]
Now, Jon wants to know the maximum and minimum strength of the rangers after performing the above operations k times. He wants your help for this task. Can you help him?
Input
First line consists of three integers n, k, x (1 ≤ n ≤ 10^5, 0 ≤ k ≤ 10^5, 0 ≤ x ≤ 10^3) — number of rangers Jon has, the number of times Jon will carry out the operation and Jon's favourite number respectively.

Second line consists of n integers representing the strengths of the rangers a1, a2, ..., an (0 ≤ ai ≤ 10^3).
Output
Output two integers, the maximum and the minimum strength of the rangers after performing the operation k times.
Sample Input
5 1 2
9 7 11 15 5
Sample Output
13 7*/
public class JonSnow {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int x = sc.nextInt();
        int a [] = new int[n];
        for(int i =0;i<n;i++){
            a[i] =sc.nextInt();
        }
        //since numbers can be from 0 to 10^3 .. its a 10 bit number, and the max it can reach is 1023.. to store number fro
        //0 -1023.. array size should be 1024
        int f[] =new int[1024];

        for(int i =0;i<n;i++){
            f[a[i]]++;
        }

        for(int j =0;j<k;j++){
            int countOfProcessedNumbers=0;
             //to do xor with x of alternate numbers
            int t[] = new int[1024];
            for(int i =0;i<1024;i++) {
            if (f[i] > 0) {
                //we need to find that if f[i] is odd, then fi/2 elements will get xor or fi/2+1
                //we do this with the help of countOfProcessedNumbers, if this is even,then last number hasn't been processed
                //and we need to take xor with fi/2+1 starting with our first number
                if (f[i] % 2 == 0) {
                    //shift f[i]/2 to i^x index of t array and remaining to i index of t
                    t[i ^ x] = f[i] / 2;
                    t[i] = f[i] / 2;
                } else {
                    if (countOfProcessedNumbers % 2 == 0) {
                        //we do this with the help of countOfProcessedNumbers, if this is even,then last number hasn't been processed
                        //and we need to take xor with fi/2+1 starting with our first number
                        t[i ^ x] = f[i] / 2 + 1;
                        t[i] = f[i] / 2;
                    } else {
                        t[i ^ x] = f[i] / 2;
                        t[i] = f[i] / 2 + 1;
                    }
                }
                countOfProcessedNumbers += f[i];
            }
        }
            //make f=t
            f=t;
        }
        int min=0;
        int max=1023;
        while(min<1024 && f[min]==0)min++;
        while(max>=0 && f[max]==0)max--;
        System.out.println(max+" " +min);
    }
}
