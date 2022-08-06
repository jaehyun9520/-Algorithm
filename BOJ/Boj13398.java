package a0806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj13398_연속합2 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());
		int dp[][] = new int[n + 1][2];
		int val[] = new int[n + 1];

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			val[i] = Integer.parseInt(st.nextToken());

		}
		int ans = dp[1][0] = dp[1][1] = val[1];

		for (int i = 2; i <= n; i++) {

			dp[i][0] = Integer.max(dp[i - 1][0] + val[i], val[i]);

			dp[i][1] = Integer.max(dp[i - 1][0], dp[i - 1][1] + val[i]);

			ans = Integer.max(Integer.max(dp[i][0], dp[i][1]), ans);
		}
		
		System.out.println(ans);
	}
}
