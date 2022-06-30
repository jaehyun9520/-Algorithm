package study44;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj4811_알약 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			int n = Integer.parseInt(in.readLine());

			if (n == 0)
				break;

			long dp[][] = new long[2 * n + 1][n + 1];
			int cnt = n * 10 - 10;
			dp[1][cnt / 10] = 1;

			for (int i = 2; i <= 2 * n; i++) {
				int max = cnt / 10;
				int min = n - i;

				if (min < 0)
					min = 0;

				for (int j = min; j <= max; j++) {
					dp[i][j] = dp[i - 1][j] + dp[i - 1][j + 1];

				}

				cnt-=5;
			}
			
			sb.append(dp[2*n][0]+"\n");

		}

		System.out.println(sb.toString());
		
		
		
	}
}
