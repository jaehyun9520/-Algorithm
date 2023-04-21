package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2616 {

	public static void main(String[] args) throws IOException {

		int n, k;
		int[] input;
		int[][] dp;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());

		dp = new int[4][n + 1];
		input = new int[n + 1];
		StringTokenizer st = new StringTokenizer(in.readLine());

		for (int i = 1; i <= n; i++) {

			input[i] = Integer.parseInt(st.nextToken());

			input[i] += input[i - 1];

		}

		k = Integer.parseInt(in.readLine());
		for (int i = 1; i <= 3; i++) {

			for (int j = k; j <= n; j++) {

				dp[i][j] = input[j] - input[j - k] + dp[i - 1][j - k];

				dp[i][j] = Integer.max(dp[i][j - 1], dp[i][j]);
			}

		}

		System.out.println(dp[3][n]);
	}
}
