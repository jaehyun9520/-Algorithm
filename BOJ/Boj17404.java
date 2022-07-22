package a0722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17404_RGB거리2 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(in.readLine());
		int ans = Integer.MAX_VALUE;
		int input[][] = new int[n + 1][3];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			input[i][0] = Integer.parseInt(st.nextToken());
			input[i][1] = Integer.parseInt(st.nextToken());
			input[i][2] = Integer.parseInt(st.nextToken());

		}

		for (int i = 0; i <= 2; i++) // 1번집이 빨간색일 경우, 초록색일 경우, 파랑색일 경우의 최솟값
		{
			int dp[][] = new int[n + 1][3];

			dp[1][0] = input[1][0];
			dp[1][1] = input[1][1];
			dp[1][2] = input[1][2];

			for (int j = 0; j <= 2; j++) {
				if (n >= 2) {
					if (i == j)
						dp[2][j] = Integer.MAX_VALUE;

					else
						dp[2][j] = dp[1][i] + input[2][j];
				}

			}

			for (int j = 3; j <= n; j++) {

				if (i == 0 && j == n) {
					dp[j][0] = Integer.MAX_VALUE;
				} else {
					dp[j][0] = Integer.min(dp[j - 1][1], dp[j - 1][2]) + input[j][0];
				}

				if (i == 1 && j == n) {
					dp[j][1] = Integer.MAX_VALUE;
				} else {
					dp[j][1] = Integer.min(dp[j - 1][0], dp[j - 1][2]) + input[j][1];
				}

				if (i == 2 && j == n) {
					dp[j][2] = Integer.MAX_VALUE;
				}

				else {
					dp[j][2] = Integer.min(dp[j - 1][0], dp[j - 1][1]) + input[j][2];
				}

			}

			ans = Integer.min(ans, Integer.min(dp[n][0], Integer.min(dp[n][1], dp[n][2])));

		}
		System.out.println(ans);

	}
}
