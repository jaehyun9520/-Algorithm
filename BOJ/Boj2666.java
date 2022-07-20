package a0720;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2666_벽장문의이동 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int num1 = Integer.parseInt(st.nextToken());
		int num2 = Integer.parseInt(st.nextToken());

		int cnt = Integer.parseInt(in.readLine());

		int dp[][][] = new int[cnt + 1][n + 1][n + 1];

		int[] list = new int[cnt + 1];

		for (int i = 1; i <= cnt; i++) {
			list[i] = Integer.parseInt(in.readLine());

		}

		for (int i = 0; i <= cnt; i++) {

			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					dp[i][j][k] = 1000000;
				}
			}
		}

		if (num1 < num2)
			dp[0][num1][num2] = 0;

		else
			dp[0][num2][num1] = 0;

		for (int i = 1; i <= cnt; i++) {

			int num = list[i];

			for (int j = 1; j <= n; j++) {
				for (int k = j + 1; k <= n; k++) {
					if (dp[i - 1][j][k] != 1000000) {

						// 열고자 하는 벽장 번호보다 열려있는 두 벽장이 다 오른쪽에 있을때

						if (num < j && num < k) {
							dp[i][num][k] = Integer.min(dp[i][num][k], j - num + dp[i - 1][j][k]);

						}

						else if (j < num && k < num) {
							dp[i][j][num] = Integer.min(dp[i][j][num], num - k + dp[i - 1][j][k]);

						}

						else { // 중앙에 있는경우

							dp[i][num][k] = Integer.min(dp[i][num][k], num - j + dp[i - 1][j][k]);
							dp[i][j][num] = Integer.min(dp[i][j][num], k - num + dp[i - 1][j][k]);

						}

					}

				}
			}

		}
		int ans = Integer.MAX_VALUE;

		for (int i = 1; i <= n; i++) {
			for (int j = i + 1; j <= n; j++) {

				ans = Integer.min(ans, dp[cnt][i][j]);
			}
		}

		System.out.println(ans);
	}

}
