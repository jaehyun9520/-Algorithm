package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5557_1학년 {

	static int n;
	static long dp[][];
	static int list[];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());
		dp = new long[n + 1][21];
		list = new int[n + 1];
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		for (int i = 1; i <= n; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}

		dp[1][list[1]] = 1;

		for (int i = 2; i <n; i++) {
			int val = list[i];

			for (int j = 0; j <= 20; j++) {

				if (dp[i - 1][j] > 0) // 해당 숫자가 만들어졌다면?
				{
					if (j + val <= 20) {
						dp[i][j + val] += dp[i - 1][j];
					}

					if (j - val >= 0) {
						dp[i][j - val] += dp[i - 1][j];
					}

				}

			}

		}
		
		System.out.println(dp[n-1][list[n]]);

	}
}
