package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2018_수들의합5 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());
		int ans = 0;

		int s = 1, e = 1, sum = 1;

		while (s <= e) {
			if (sum >= n) {
				if (sum == n)
					ans++;

				sum -= s;
				s++;
			}

			else if (sum < n) {
				e++;
				sum += e;
			}

		}

		System.out.println(ans);
	}
}
