package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj9084 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(in.readLine());

		for (int t = 1; t <= tc; t++) {
			int n = Integer.parseInt(in.readLine());

			int list[] = new int[n + 1];

			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 1; i <= n; i++) {
				list[i] = Integer.parseInt(st.nextToken());
			}

			int cost = Integer.parseInt(in.readLine());

			int dp[][] = new int[n + 1][cost + 1];

			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= cost; j++) {
					if (list[i] > j) // 현재 만들려는 금액(j)보다 동전의 금액이 더 크다면
					{
						dp[i][j] = dp[i - 1][j];
					} else // 현재 만들려는 금액(j)이 동전의 값보다 크거나 같다면
					{
						dp[i][j] = dp[i][j] + dp[i][j - list[i]];
						if ((j % list[i]) == 0) // 해당 동전의 배수 금액이라면
						{
							dp[i][j]++;
						}

						dp[i][j] = dp[i][j] + dp[i - 1][j];
						if (j - list[i] != 0 && (j - list[i]) % list[i] == 0) {
							dp[i][j]--;
						}
					}
				}
			}
			System.out.println(dp[n][cost]);
		}
	}
}
