package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1912 {

	public static void main(String[] args) throws IOException {

		int n, ans = 0;
		int dp[];
		int input[];
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());

		dp = new int[n + 1];

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		dp[1] = Integer.parseInt(st.nextToken());
		ans = dp[1];
		for (int i = 2; i <= n; i++) {
			int val = Integer.parseInt(st.nextToken());

			if (val + dp[i - 1] <= val) {
				dp[i] = val;
			}

			else {
				dp[i] = dp[i - 1] + val;
			}

			ans = Integer.max(dp[i], ans);

		}

		System.out.println(ans);

	}
}
