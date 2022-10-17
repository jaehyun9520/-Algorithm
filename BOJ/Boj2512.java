package study59;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2512 {

	static int n, m;
	static int[] list;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());

		list = new int[n + 1];
		int max = 0;
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			list[i] = Integer.parseInt(st.nextToken());
			max = Integer.max(list[i], max);
		}
		m = Integer.parseInt(in.readLine());

		System.out.println(binarySearch(max));
		
	}

	static int binarySearch(int max) {
		int ans = 0;
		int low = 1, high = max, mid = 0;

		while (low <= high) {

			mid = (low + high) / 2;

			// 해당 상한액으로 모든 지방에 배정이 가능한 경우
			if (check(mid)) {
				low = mid + 1;
				ans = Integer.max(ans, mid);
			}

			else {
				high = mid - 1;
			}

		}
			return ans;
	}

	static boolean check(int val) {

		int sum = 0;
		for (int i = 1; i <= n; i++) {
			// 상한액 이상이라면?
			if (list[i] >= val) {
				sum += val;
			}
			// 상한액 이하라면?
			else {
				sum += list[i];
			}
			// 해당 상한액으로 예산을 초과한 경우
			if (sum > m) {
				return false;
			}
		}
		return true;
	}

}
