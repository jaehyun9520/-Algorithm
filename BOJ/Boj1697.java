package study61;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1697 {

	static int list[] = { 1, -1, 2 };
	static int n, k, ans;
	static boolean[] visited = new boolean[100001];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		bfs();
		System.out.println(ans);
	}

	static void bfs() {

		Queue<int[]> q = new LinkedList<>();

		visited[n] = true;

		q.add(new int[] { n, 0 });

		while (!q.isEmpty()) {

			int now[] = q.poll();

			int loc = now[0];
			int cnt = now[1];

			if (loc == k) {
				ans = cnt;
				break;
			}

			for (int i = 0; i <= 2; i++) {
				int next = 0;
				if (i < 2)
					next = loc + list[i];
				else
					next = loc * list[i];

				if (next < 0 || next > 100000 || visited[next])
					continue;

				visited[next] = true;
				q.add(new int[] { next, cnt + 1 });

			}

		}

	}
}
