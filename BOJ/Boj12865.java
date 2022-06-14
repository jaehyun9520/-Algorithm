package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj12865 {

	public static void main(String[] args) throws IOException {

		int n, k;
		int dp[][];
		int weight[];
		int value[];

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine()," ");

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		
		
		dp = new int[n + 1][k + 1];
		weight = new int[n + 1];
		value = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");

			int v, w;

			w = Integer.parseInt(st.nextToken()); // 무게
			v = Integer.parseInt(st.nextToken()); // 가치

			weight[i] = w;
			value[i] = v;
		}

		for (int i = 1; i <= n; i++) {

			for (int j = 1; j <= k; j++) {

				if (j < weight[i]) {
					dp[i][j] = dp[i - 1][j];

				}

				else {

					dp[i][j] = Integer.max(dp[i - 1][j - weight[i]] + value[i], dp[i - 1][j]);

				}

			}

		}

		
		System.out.println(dp[n][k]);
	}
}
