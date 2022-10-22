package study60;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj1253 {

	static List<Integer> list = new ArrayList<>();
	static int n;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(list);
		int ans = 0;

		for (int i = 0; i < n; i++) {

			loop: for (int j = 0; j < n; j++) {

				if (i != j) {
					int num = list.get(i) - list.get(j);
					int s = lowerBound(num);
					int e = upperBound(num);

					for (int k = s; k < e; k++) {

						if (k != i && k != j) {

							ans++;
							break loop;

						}

					}
				}

			}
		}

		System.out.println(ans);

	}

	static int lowerBound(int val) {

		int low = 0, high = n, mid = 0;

		while (low < high) {

			mid = (low + high) / 2;

			//
			if (list.get(mid) >= val) {
				high = mid;
			} else {
				low = mid + 1;
			}

		}
		return high;

	}

	static int upperBound(int val) {
		int low = 0, high = n, mid = 0;

		while (low < high) {

			mid = (low + high) / 2;

			//
			if (list.get(mid) > val) {
				high = mid;
			} else {
				low = mid + 1;
			}

		}
		return high;
	}

}
