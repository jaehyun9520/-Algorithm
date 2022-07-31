package study49;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj23978_급상승 {

	static long n, k;
	static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		n = Long.parseLong(st.nextToken());
		k = Long.parseLong(st.nextToken());

		st = new StringTokenizer(in.readLine());
		for (int i = 1; i <= n; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}

		long low = 1, high = 1414213562, mid;
		long ans = 0;

		while (low <= high) {
			mid = (low + high) / 2;

			if (check(mid)) {
				ans = mid;

				high = mid - 1;

			}

			else {

				low = mid + 1;
			}

		}

		System.out.println(ans);

	}

	static boolean check(long val) {

		boolean flag = false;
		long sum = (val * (val + 1) / 2);

		for (int i = 0; i < list.size(); i++) {
			int now = list.get(i);

			if (i + 1 < list.size()) {
				int next = list.get(i + 1);

				if (next - now >= val) {
					sum += (val * (val + 1) / 2);
				}

				else {

					sum += (val * (next - now));
					long tmp = next - now;
					sum -= ((tmp - 1) * (tmp) / 2);

				}

			}

			if (sum >= k) {
				flag = true;
				break;
			}
		}

		return flag;
	}
}
