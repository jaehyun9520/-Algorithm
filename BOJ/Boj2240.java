package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2240_자두나무 {

	public static void main(String[] args) throws IOException {

		int t, w;
		int input[][];
		int dp[][];  // dp[현재까지 사용한 이동횟수][현재 시간초]가 가지는 최댓값 
		
		/*
		 * 
		 * dp[x][y]=  max(dp[x-1][y-1],dp[x][y-1])
		 * 
		 */

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		t = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		input = new int[t + 1][2];
		dp = new int[w + 1][t + 1]; 

		for (int i = 1; i <= t; i++) {
			int val = Integer.parseInt(in.readLine());
			input[i][val - 1] = 1;
		}


		
		for (int i = 1; i <= t; i++) {
	
			for(int j=0; j<=w; j++)  // 현재 내 위치는 j값을 통해 알 수 있다  현재 시간에 어디 나무에서
				// 사과가 떨어지는지는 input[i]를 통해 알 수 있다
			{
				if(j==0)
				{
					dp[j][i]=dp[j][i-1]+input[i][j%2];
				}
				else {
					
					dp[j][i]=Integer.max(dp[j-1][i-1],dp[j][i-1])+input[i][j%2];
				}
				
				
			}

		}

		

		int ans=0;
		for(int i=0; i<=w; i++)
		{
			ans=Integer.max(ans,dp[i][t]);
		}
		System.out.println(ans);
	}
}
