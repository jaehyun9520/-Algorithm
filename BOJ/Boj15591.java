package 알고리즘스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15591 {

	static List<int[]>[] list;
	static int n, q;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

		list = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a, b, cost;

			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());

			list[a].add(new int[] { b, cost });
			list[b].add(new int[] { a, cost });

		}

		for (int i = 1; i <= q; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());

			sb.append(solution(start, k) + "\n");

		}

		System.out.println(sb.toString());

	}

	static int solution(int start, int k) {

		int ans = 0;
		boolean[] visited = new boolean[n + 1];

		visited[start] = true;

		Queue<int[]> q = new LinkedList<>();

		q.add(new int[] { start, Integer.MAX_VALUE });

		while (!q.isEmpty()) {

			int[] now = q.poll();

			int num = now[0];
			int cost = now[1];

			// 현재 비디오에 연결되어 있는 다른 비디오
			for (int i = 0; i < list[num].size(); i++) {

				int[] next = list[num].get(i);

				int min = Integer.min(next[1], cost);

				// USADO가 K이상이면서 한번도 방문한적이 없는경우
				if (min >= k && !visited[next[0]]) {

					visited[next[0]] = true;
					ans++;

					q.add(new int[] { next[0], min });
				}

			}

		}

		return ans;
	}
}
