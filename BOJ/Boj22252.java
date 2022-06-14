package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj22252 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		long ans = 0;
		int n = Integer.parseInt(in.readLine());

		HashMap<String, PriorityQueue<Integer>> g = new HashMap<>();

		// 시간 순이기때문에 입력값을 받으면서 처리해나가자
		for (int i = 1; i <= n; i++) {

			StringTokenizer st = new StringTokenizer(in.readLine(), " ");

			int type = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			PriorityQueue<Integer> q;

			if (g.containsKey(name)) {
				q = g.get(name);
			}

			else {

				q = new PriorityQueue<>(Collections.reverseOrder());

				g.put(name, q);

			}

			if (type == 2) {

				int cnt = Integer.parseInt(st.nextToken());

				while (!q.isEmpty() && cnt != 0) {

					ans += q.poll();

					cnt--;

				}

			}

			else if (type == 1) {

				int cnt = Integer.parseInt(st.nextToken());
				for (int j = 1; j <= cnt; j++) {
					int val = Integer.parseInt(st.nextToken());

					q.add(val);

				}

			}

		}

		System.out.println(ans);

	}
}
