package study43;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1261_알고스팟 {

	static class State implements Comparable<State> {
		int x, y, cnt;

		State(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(State o) {
			// TODO Auto-generated method stub
			return this.cnt - o.cnt;
		}

	}

	static int map[][];
	static int n, m;
	static int visited[][];

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		map = new int[n + 1][m + 1];
		visited = new int[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {
			String input = in.readLine();

			for (int j = 1; j <= m; j++) {
				visited[i][j] = -1; 

				map[i][j] = input.charAt(j - 1) - 48;

			}

		}

		System.out.println(bfs());
	}

	static int bfs() {

		PriorityQueue<State> pq = new PriorityQueue<>();
		int ans = 0;
		visited[1][1] = 0;
		pq.add(new State(1, 1, 0));

		while (!pq.isEmpty()) {
			State now = pq.poll();

			int x = now.x;
			int y = now.y;
			int cnt = now.cnt;

			if (x == n && y == m) {
				ans = cnt;
				break;
			}

			for (int i = 0; i <= 3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 1 || nx > n || ny < 1 || ny > m)
					continue;

				if (map[nx][ny] == 0 && visited[nx][ny] == -1) {
					visited[nx][ny] = cnt;

					pq.add(new State(nx, ny, cnt));
				}

				else if (map[nx][ny] == 0 && visited[nx][ny] > cnt) {
					visited[nx][ny] = cnt;
					pq.add(new State(nx, ny, cnt));
				}

				else if (map[nx][ny] == 1) {
					if (visited[nx][ny] == -1) {
						visited[nx][ny] = cnt + 1;
						pq.add(new State(nx, ny, cnt + 1));

					} else if (visited[nx][ny] > cnt + 1) {
						visited[nx][ny] = cnt + 1;
						pq.add(new State(nx, ny, cnt + 1));
					}
				}
			}

		}
		return ans;

	}
}