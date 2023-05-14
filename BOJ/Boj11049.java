package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11049 {

	public static void main(String[] args) throws Exception {

        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));

        int n=Integer.parseInt(in.readLine());
        int[][] input=new int[n+1][2]; //입력값
        int[][] dp=new int[n+1][n+1];


        for(int i=1; i<=n; i++) {
            StringTokenizer st=new StringTokenizer(in.readLine()," ");

            input[i][0]=Integer.parseInt(st.nextToken());
            input[i][1]=Integer.parseInt(st.nextToken());
        }


        for(int i=2; i<=n; i++) {

            for(int j=i; j<=n; j++) {

                int s=j-i+1; int e=j;


                for(int k=s; k<=e-1; k++) {

                    int s1=s;
                    int e1=k;

                    int s2=k+1;
                    int e2=e;

                    if(dp[s][e]==0)
                    dp[s][e]=dp[s1][e1]+dp[s2][e2]+(input[s1][0]*input[e1][1]*input[e2][1]);

                    else {
                        dp[s][e]=Integer.min(dp[s][e],dp[s1][e1]+dp[s2][e2]+(input[s1][0]*input[e1][1]*input[e2][1]));
                    }

                }

            }

        }
        System.out.println(dp[1][n]);
    }
}