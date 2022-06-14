package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1005 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");

			int n = Integer.parseInt(st.nextToken()); // 건물 총 개수
			int k = Integer.parseInt(st.nextToken());
			int w = 0, ans = 0; // 승리하기 위해 지어야하는 건물
			int dp[] = new int[n + 1]; // 현재 건물을 완성시키는데 걸리는 최소 시간
			ArrayList<Integer> list[] = new ArrayList[n + 1]; // 해당 건물이 가리키는 건물
			int cnt[] = new int[n + 1]; // 자신을 가리키는 건물의 개수 (0)이 돼야 지을수있다
			int cost[] = new int[n + 1]; // 건물을 짓는데 드는 비용

			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 1; i <= n; i++) {
				list[i] = new ArrayList<>();
				cost[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= k; i++) {
				st = new StringTokenizer(in.readLine(), " ");

				// x-> y
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				list[x].add(y);
				cnt[y]++;
			}

			w = Integer.parseInt(in.readLine());

			Queue<Integer> q = new LinkedList<>();

			for (int i = 1; i <= n; i++) {

				if (cnt[i] == 0) {
					q.add(i);
					dp[i] = cost[i]; // 선행 건물이 없으므로 자기 건설 비용만 가진다
				}
			}


			while (!q.isEmpty()) {
				int now = q.poll();

				if (now == w) {

					ans = dp[now];
					break;
				}

				for (int i = 0; i < list[now].size(); i++) {
					int next = list[now].get(i);
					cnt[next]--;
					if (dp[next] == 0) {
						dp[next] = cost[next] + dp[now];
					} else {
						dp[next] = Integer.max(dp[next], cost[next] + dp[now]);
					}

					if (cnt[next] == 0) {

						q.add(next);
					}

				}

			}

			sb.append(ans + "\n");

		}

		System.out.println(sb.toString());

	}
}
