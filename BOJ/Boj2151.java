package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Boj2151_거울설치 {

	static class State implements Comparable<State> {
		int x, y, cnt, dir;

		State(int x, int y, int cnt, int dir) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
		}

		@Override
		public int compareTo(State o) {
			return this.cnt - o.cnt;
		}
	}

	static int dx[] = { -1, 0, 1, 0 }; // 북,동,남,서 (시계방향)
	static int dy[] = { 0, 1, 0, -1 };
	static int n;
	static int mirror1[] = { 1, 0, 3, 2 }; // /모양
	static int mirror2[] = { 3, 2, 1, 0 }; // \모양
	static int map[][];

	static boolean visited[][][];

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int sx = 0, sy = 0;
		n = Integer.parseInt(in.readLine());

		map = new int[n + 1][n + 1];
		visited = new boolean[4][n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			String input = in.readLine();
			for (int j = 1; j <= n; j++) {
				map[i][j] = input.charAt(j - 1);

				if (map[i][j] == '#') {
					sx = i;
					sy = j;
				}
			}
		}

		for (int i = 0; i <= 3; i++) // 시작지점 방문체크
		{
			visited[i][sx][sy] = true;
		}

		System.out.println(bfs(sx, sy));

	}

	static int bfs(int sx, int sy) {

		int ans = 0;
		PriorityQueue<State> q = new PriorityQueue<>();

		for (int k = 0; k <= 3; k++) {
			int nx = sx + dx[k];
			int ny = sy + dy[k];

			if (nx < 1 || nx > n || ny < 1 || ny > n || map[nx][ny] == '*')
				continue;

			q.add(new State(sx, sy, 0, k));
		}

		while (!q.isEmpty()) {
			State now = q.poll();
			int x = now.x;
			int y = now.y;
			int cnt = now.cnt;
			int dir = now.dir;

			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (nx < 1 || nx > n || ny < 1 || ny > n || map[nx][ny] == '*')
				continue;

			// .또는 ! 인데 그냥 기존 방향으로 이동하는 경우
			if ((map[nx][ny] == '.' || map[nx][ny] == '!') && visited[dir][nx][ny] == false) {
				visited[dir][nx][ny] = true;
				q.add(new State(nx, ny, cnt, dir));
			}

			// ! 이면서 / \ 거울을 설치하는 경우
			if (map[nx][ny] == '!') {
				int m1 = mirror1[dir];

				if (visited[m1][nx][ny] == false) {
					visited[m1][nx][ny] = true;

					q.add(new State(nx, ny, cnt + 1, m1));
				}

				int m2 = mirror2[dir];

				if (visited[m2][nx][ny] == false) {
					visited[m2][nx][ny] = true;

					q.add(new State(nx, ny, cnt + 1, m2));
				}

			}

			if (map[nx][ny] == '#' && visited[dir][nx][ny] == false) {
				ans = cnt;
				break;
			}

		}

		return ans;
	}
}
