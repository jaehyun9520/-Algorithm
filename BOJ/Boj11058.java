package a0715;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj11058_크리보드 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());

		long dp[] = new long[n + 1]; // 4는 4번을 선택했을때의 화면갯수, 5는 4번 선택했을때의 버퍼드리더 개수

		dp[1] = 1;

		for (int i = 2; i <= n; i++) {

			dp[i] = dp[i - 1] + 1;

			for (int j = 1; j <= i - 3; j++) {
				dp[i] = Long.max(dp[i], dp[j] + dp[j] * (i - (j + 2)));

			}

		}

		System.out.println(dp[n]);

	}
}
