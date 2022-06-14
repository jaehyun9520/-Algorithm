package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2502_떡먹는호랑이 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int ans1 = 0, ans2 = 0;
		int n = Integer.parseInt(st.nextToken());
		int val = Integer.parseInt(st.nextToken());

		int dp[][] = new int[n + 1][3]; // 첫번째 값이 몇개 있는지 2번째 값이 몇개 있는지 카운트

		dp[1][1] = 1;
		dp[2][2] = 1;
		for (int i = 3; i <= n; i++) {
			dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
			dp[i][2] = dp[i - 1][2] + dp[i - 2][2];
		}

		boolean flag = true;

		int num = 1, val1 = 0, val2 = 0;
		while (flag) {

			val1 = num * dp[n][1];

			if ((val - val1) % dp[n][2] == 0) {
				ans1 = num;
				ans2 = (val - val1) / dp[n][2];
				break;
			}

			num++;

		}

		System.out.println(ans1);
		System.out.println(ans2);

	}
}
