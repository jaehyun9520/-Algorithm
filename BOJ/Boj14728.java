package a0714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14728_벼락치기 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		int dp[][] = new int[n + 1][t + 1];
		int unit[][] = new int[n + 1][2]; // 0은 공부시간, 1은 배점

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			unit[i][0] = Integer.parseInt(st.nextToken());
			unit[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= n; i++) {
			int score = unit[i][1];
			int time = unit[i][0];

			for (int j = 1; j <= t; j++) {

				if (time > j) {
					dp[i][j] = dp[i - 1][j];
				}

				else {

					dp[i][j] = Integer.max(dp[i - 1][j], score + dp[i - 1][j - time]);

				}

			}

		}

		
		System.out.println(dp[n][t]);
	}
}
