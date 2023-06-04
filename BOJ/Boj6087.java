package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj6087 {

	static class State implements Comparable<State> {
		int x, y, dir, cnt;

		State(int x, int y, int dir, int cnt) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}

		// 거울 사용횟수 오름차순으로 정렬
		@Override
		public int compareTo(State o) {

			return this.cnt - o.cnt;

		}
	}

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int w, h;
	static int[][] map;
	static boolean[][][] visited;
	static int[] mirror = { -1, 1 };
	static int sx, sy, ans = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		map = new int[h + 1][w + 1];
		visited = new boolean[4][h + 1][w + 1];

		for (int i = 1; i <= h; i++) {

			String input = in.readLine();

			for (int j = 1; j <= w; j++) {

				map[i][j] = input.charAt(j - 1);

				if (map[i][j] == 'C') {
					sx = i;
					sy = j;
				}

			}
		}

		bfs();

		System.out.println(ans);
	}

	static void bfs() {

		PriorityQueue<State> pq = new PriorityQueue<>();

		for (int i = 0; i <= 3; i++) {

			visited[i][sx][sy] = true;
			pq.add(new State(sx, sy, i, 0));
		}

		while (!pq.isEmpty()) {

			State now = pq.poll();

			int nx = now.x + dx[now.dir];
			int ny = now.y + dy[now.dir];

			if (nx < 1 || nx > h || ny < 1 || ny > w || visited[now.dir][nx][ny] || map[nx][ny] == '*')
				continue;

			visited[now.dir][nx][ny] = true;

			if (map[nx][ny] == 'C') {
				ans = now.cnt;
				return;
			}

			pq.add(new State(nx, ny, now.dir, now.cnt));

			for (int i = 0; i <= 1; i++) {

				int newDir = now.dir + mirror[i];

				if (newDir == 4)
					newDir = 0;
				if (newDir == -1)
					newDir = 3;

				pq.add(new State(nx, ny, newDir, now.cnt + 1));

			}

		}

	}

}
