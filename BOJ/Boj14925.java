package a0814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14925_목장건설하기 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int map[][];
		int dp[][];
		int m, n,ans=0;

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		map = new int[m + 1][n + 1];
		dp = new int[m + 1][n + 1];

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (map[i][j] == 0) {
					dp[i][j] = Integer.min(dp[i - 1][j], Integer.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;

					ans=Integer.max(ans,dp[i][j]);
				}
			}
		}
		
		System.out.println(ans);

	}
}
