package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj12851 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int n, k;
		int visited[] = new int[100001];
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		int ans = 0;
		int move[] = { 1, -1, 2 };
		Queue<int[]> q = new LinkedList<>();

		if (n == k) {

			ans = 1;
		}

		else {

			visited[n] = 1;

			q.add(new int[] { n, 0 });

			while (!q.isEmpty()) {
				int now[] = q.poll();
				int loc = now[0];
				int cnt = now[1];

				for (int i = 0; i <= 2; i++) {
					int next = 0;
					if (i <= 1) {
						next = loc + move[i];

					}

					else {
						next = loc * move[i];
					}

					if (next < 0 || next > 100000 || (visited[next] != 0 && visited[next] < cnt + 1))
						continue;

					if (next == k) {
						if (visited[k] == 0) {
							visited[k] = cnt + 1;
							ans = 1;
						}

						else if (visited[k] == (cnt + 1)) {
							ans++;
						}

						else if (visited[k] < cnt + 1)
							continue;

						q.add(new int[] { next, cnt + 1 });

					}

					else {
						visited[next] = cnt + 1;

						q.add(new int[] { next, cnt + 1 });

					}

				}

			}

		}
		System.out.println(visited[k]);
		System.out.println(ans);

	}

}
