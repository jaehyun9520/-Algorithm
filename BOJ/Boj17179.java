package study62;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17179 {

	static int n, m, length;
	static int list[];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		length = Integer.parseInt(st.nextToken());

		list = new int[m + 1];

		for (int i = 1; i <= m; i++) {

			list[i] = Integer.parseInt(in.readLine());
		}

		for (int i = 1; i <= n; i++) {

			int cnt = Integer.parseInt(in.readLine());

			sb.append(binarySearch(cnt) + "\n");

		}

		System.out.println(sb.toString());

	}

	static int binarySearch(int cnt) {

		int low = 1, high = length, mid = 0;
		int ans = 0;

		while (low <= high) {

			mid = (low + high) / 2;

			// 가장 작은 조각의 길이가 mid이상이라면 더 큰 값이 가능한지 확인
			if (check(mid, cnt)) {

				ans = mid;
				low = mid + 1;

			} else {
				high = mid - 1;
			}

		}

		return ans;

	}

	static boolean check(int min, int cnt) {

		int preVal = 0, count = 0;

		for (int i = 1; i <= m; i++) {

			if (list[i] - preVal >= min) {

				count++;
				preVal = list[i];

				if (count == cnt)
					break;

			}

		}

		if (length - preVal >= min && count == cnt) {
			return true;
		}

		else
			return false;

	}
}
