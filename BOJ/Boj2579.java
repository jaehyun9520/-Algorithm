package study44;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2579_계단오르기 {

	public static void main(String[] args) throws IOException {

		int n;
		int input[];
		int dp[][];

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());
		input = new int[n + 1];
		dp = new int[n + 1][2];

		for (int i = 1; i <= n; i++) {
			input[i] = Integer.parseInt(in.readLine());
		}
		dp[1][0] = input[1];
		dp[1][1] = input[1];
	
		for (int i = 2; i <= n; i++) {
			dp[i][0] = input[i] + dp[i - 1][1];
			dp[i][1] = input[i] + Integer.max(dp[i - 2][0], dp[i - 2][1]);
		}
		System.out.println(Integer.max(dp[n][0], dp[n][1]));

	}
}
