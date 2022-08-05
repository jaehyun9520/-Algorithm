package a0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Boj2473_세용액 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		long ans[] = new long[3];
		long totalSum = Long.MAX_VALUE;

		int n = Integer.parseInt(in.readLine());

		ArrayList<Long> list = new ArrayList<>();

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			list.add(Long.parseLong(st.nextToken()));
		}

		Collections.sort(list);

		loop: for (int i = 0; i < list.size(); i++) {
			long val1 = list.get(i);

			long sum = val1 * -1;

			int L = 0;
			int R = list.size() - 1;

			while (L < R) {
				if (L == i)
					L++;
				if (R == i)
					R--;

				if (L == R)
					break;

				long val2 = list.get(L);
				long val3 = list.get(R);

				if (Math.abs(val1 + val2 + val3) < totalSum) {
					totalSum = Math.abs(val1 + val2 + val3);

					ans[0] = val1;
					ans[1] = val2;
					ans[2] = val3;
				}

				if (val2 + val3 > sum) {
					R--;
				}

				else if (val2 + val3 < sum) {
					L++;
				}

				else if (val2 + val3 == sum) {
					totalSum = 0;
					ans[0] = val1;
					ans[1] = val2;
					ans[2] = val3;

					break;
				}

			}

		}

		Arrays.sort(ans);

		for (int i = 0; i <= 2; i++) {
			System.out.print(ans[i] + " ");
		}

	}
}
