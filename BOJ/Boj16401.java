package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj16401_과자나눠주기 {

	static int M, N;
	static int input[];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		input = new int[N + 1];

		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		int low = 1, high = 1000000000, mid = 0;
		int ans = 0;

		while (low <= high) {
			mid = (low + high) / 2;

			if (check(mid)) {
				ans = mid;
				low = mid + 1;

			}

			else {

				high = mid - 1;
			}

		}
		System.out.println(ans);

	}

	static boolean check(int val) {
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			cnt += (input[i] / val);

			if (cnt >= M)
				return true;
		}

		return false;

	}

}
