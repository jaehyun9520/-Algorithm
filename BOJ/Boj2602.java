package a0724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2602_돌다리건너기 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		char[] list = in.readLine().toCharArray();
		int length = list.length;

		char[] devilBridge = in.readLine().toCharArray();
		char[] angelBridge = in.readLine().toCharArray();

		int bLength = devilBridge.length;

		long dp[][][] = new long[2][bLength][length]; // 0은 천사 , 1은 악마

		for (int i = 0; i < length; i++) {
			char alphabet = list[i];

			if (i == 0) // 첫번째 문자열 무조건 해당 문자 1
			{
				for (int j = 0; j <= 1; j++) {
					for (int k = 0; k < bLength; k++) {
						if (j == 0 && devilBridge[k] == alphabet) {
							dp[j][k][0] = 1;
						}

						else if (j == 1 && angelBridge[k] == alphabet) {
							dp[j][k][0] = 1;
						}
					}
				}

			}

			else {

				for (int j = 0; j <= 1; j++) {
					for (int k = 0; k < bLength; k++) {
						if (j == 0 && devilBridge[k] == alphabet) {

							for (int z = 0; z < k; z++) {

								dp[0][k][i] += dp[1][z][i - 1];

							}

						}

						else if (j == 1 && angelBridge[k] == alphabet) {
							for (int z = 0; z < k; z++) {

								dp[1][k][i] += dp[0][z][i - 1];

							}
						}
					}
				}

			}

		}
		long ans = 0;
		for (int i = 0; i <= 1; i++) {
			for (int j = 0; j < bLength; j++) {
				ans += dp[i][j][length - 1];

			}

		}

		System.out.println(ans);
	}
}
