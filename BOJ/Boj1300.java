package study56;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1300 {

	static int n, k, ans;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());
		k = Integer.parseInt(in.readLine());

		binarySearch();
		System.out.println(ans);
	}

	static void binarySearch() {
		int low = 1, high = (int)Long.min(1000000000,(long)n * n), mid = 0;


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
	}

	static boolean check(int val) {
		long cnt = 0;
		for (int i = 1; i <= n; i++) {
			long max = (long)i * n;

			if (max < val) {
			
				cnt += n;
			}
			else {

				if (val % i == 0) {

					cnt += (val / i - 1);
				} else {
					cnt += (val / i);
				}
			}
		}

		if(cnt<=(k-1)) return true;
		else return false;
	}
}
