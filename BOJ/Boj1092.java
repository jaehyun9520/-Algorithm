package algo;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj1092 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(in.readLine());
		int cnt[] = new int[n];
		ArrayList<Integer> crane = new ArrayList<>();
		ArrayList<Integer> box = new ArrayList<>();
		st = new StringTokenizer(in.readLine(), " ");

		for (int i = 1; i <= n; i++) {

			int val = Integer.parseInt(st.nextToken());

			crane.add(val);
		}

		int m = Integer.parseInt(in.readLine());

		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= m; i++) {
			box.add(Integer.parseInt(st.nextToken()));
		}

		Collections.sort(crane, Collections.reverseOrder());
		Collections.sort(box, Collections.reverseOrder());

		boolean flag = true;

		for (int i = 0; i < m; i++) {

			int point = -1;
			int count = Integer.MAX_VALUE;

			for (int j = 0; j < crane.size(); j++) {
				int val = crane.get(j);

				if (val >= box.get(i) && cnt[j] < count) {
					count = cnt[j] + 1;
					point = j;

				}

			}

			if (point == -1) {
				flag = false;
				break;
			}

			else
				cnt[point]++;

		}

		int ans = 0;
		for (int i = 0; i < n; i++) {

			ans = Integer.max(ans, cnt[i]);
		}
		if (flag == false)
			ans = -1;

		System.out.println(ans);

	}
}
