package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1495_기타리스트 {

	static int n, s, m; // 곡의 개수, 시작볼륨값, 최대볼륨값
	static int dp[][];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		dp = new int[n + 1][m + 1];

		dp[0][s] = 1;

		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			int val = Integer.parseInt(st.nextToken());
			for (int j = 0; j <= m; j++) {
				if (dp[i - 1][j] == 1) {
					if (val + j <= m) {
						dp[i][val + j] = 1;
					}

					if (j-val >= 0) {
						dp[i][j-val] = 1;
					}

				}

			}

		}
			
		int ans=-1;
		for(int i=0; i<=m; i++ )
		{
			if(dp[n][i]==1)
			ans=Integer.max(ans,i);
		}
		
		if(ans==-1)
		{
			System.out.println(-1);
		}
		else {
			System.out.println(ans);
		}
	}
}
