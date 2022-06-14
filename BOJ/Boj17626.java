package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj17626 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int input = Integer.parseInt(in.readLine());

		int dp[] = new int[input + 1];

		for (int i = 1; i <= input; i++) {
			int val = 1;

			while (true) {
				int result = (int) Math.pow(val, 2);

				if (result == i) {
					dp[i] = 1;
					break;
				}

				else if (result < i) {
					if (dp[i] == 0) {
						dp[i] = 1 + dp[i - result];
					} else {
						

						
						dp[i] = Math.min(dp[i], 1 + dp[i - result]);
					}
				}

				else {
					break;
				}

				val++;
			}

		}
		
		System.out.println(dp[input]);

	}
}
