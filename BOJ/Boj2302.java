package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2302_극장좌석 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int n, m;
		int fix[];
		int dp[][];
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());
		m = Integer.parseInt(in.readLine());
		dp = new int[n + 1][2]; // [0]= 현재 자리에 고정 , [1] = 앞자리와 바꿈
		fix = new int[n + 1];

		for (int i = 1; i <= m; i++) {
			int number = Integer.parseInt(in.readLine());
			fix[number] = 1;
		}

		dp[1][0] = 1;

		for (int i = 2; i <= n; i++) {
			if (fix[i] == 1 || fix[i - 1] == 1) {
				dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
				dp[i][1] = 0;
			}

			else {
				dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
				dp[i][1] = dp[i - 2][0] + dp[i - 2][1];

				if (dp[i][1] == 0)
					dp[i][1] = 1;

			}

		}

		System.out.println(dp[n][0]+dp[n][1]);
		
	}
}
