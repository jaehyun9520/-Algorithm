package a0715;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj9461_파도반수열 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(in.readLine());

		long dp[] = new long[101];

		dp[1] = 1;
		dp[2] = 1;

		for (int i = 3; i <= 100; i++) {
			dp[i] = dp[i - 2] + dp[i - 3];
		}

		for (int i = 1; i <= t; i++) {
			int n = Integer.parseInt(in.readLine());

			sb.append(dp[n] + "\n");

		}
		
		System.out.println(sb.toString());

	}
}
