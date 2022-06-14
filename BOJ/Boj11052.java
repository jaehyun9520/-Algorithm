package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11052 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());
		int cost[] = new int[n + 1];
		int dp[][] = new int[n + 1][n + 1];
		StringTokenizer st = new StringTokenizer(in.readLine());

		for (int i = 1; i <= n; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}

		// i 1~i 까지의 카드팩 사용 j는 카드수
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (j < i) {
					dp[i][j] = dp[i - 1][j];
				}

				else {
					dp[i][j] = Integer.max(dp[i - 1][j], dp[i][j - i] + cost[i]);
				}

			}

		}
		
		System.out.println(dp[n][n]);

	}

}
