package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2023_신기한소수 {

	static int n;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());

		permutation(0, 0);

		System.out.println(sb.toString());
	}

	static void permutation(int number, int cnt) {

		if (cnt == n) {
			sb.append(number + "\n");
			return;
		}

		for (int i = 1; i <= 9; i++) {
			int tmp = number * 10 + i;

			if (tmp == 1)
				continue;

			boolean flag = true;

			for (int j = 2; j <= (int) Math.sqrt(tmp); j++) {
				if (tmp % j == 0) {
					flag = false;
					break;

				}
			}

			if (flag) {
				permutation(tmp, cnt + 1);
			}

		}

	}

}
