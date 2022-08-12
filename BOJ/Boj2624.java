package a0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2624_동전바꿔주기 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		int k = Integer.parseInt(in.readLine());

		int coin[][] = new int[k + 1][2]; // 0에는 동전의 금액, 1에는 동전의 개수

		for (int i = 1; i <= k; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");

			coin[i][0] = Integer.parseInt(st.nextToken());
			coin[i][1] = Integer.parseInt(st.nextToken());

		}

		int dp[][] = new int[k + 1][T + 1];

		for (int i = 1; i <= k; i++) {
			for (int j = 1; j <= T; j++) {
				dp[i][j] = dp[i - 1][j]; // 현재 동전을 쓰지 않는 경우
				for (int cnt = 1; cnt <= coin[i][1]; cnt++) {
					int sum = cnt * coin[i][0];
					if (j < sum) {
						break;
					}

					else if (sum < j) {
						dp[i][j] += dp[i - 1][j - sum];
					}

					else if (sum == j) {
						dp[i][j]++;
					}

				}
			}
		}

		System.out.println(dp[k][T]);
	}
}
