package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2688_줄어들지않아 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(in.readLine());

		long dp[][] = new long[65][10];
		long ans[] = new long[65];
		ans[1]=10;
		for (int i = 0; i <= 9; i++) {
			dp[2][i] = i + 1;
		}

		for (int i = 3; i <= 64; i++) {
			for (int j = 0; j <= 9; j++) {
				dp[i][j] = dp[i - 1][j];

				if (j > 0) {
					dp[i][j] += dp[i][j - 1];
				}
			}

		}
		

		for (int i = 2; i <= 64; i++) {
			for (int j = 0; j <= 9; j++) {
				ans[i] += dp[i][j];
			}
		}

		for (int i = 1; i <= t; i++) {

			int n = Integer.parseInt(in.readLine());

			sb.append(ans[n] + "\n");

		}

		System.out.println(sb.toString());
	}
}
