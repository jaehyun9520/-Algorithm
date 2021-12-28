package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16509 {

	static class State {
		int x, y, cnt;

		State(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };

	static int direct[][] = { { 2, 3 }, { 0, 1 }, { 1, 3 }, { 0, 2 } };
	static int dx2[] = { -1, 1, -1, 1 };
	static int dy2[] = { -1, -1, 1, 1 };	
	static int ans = 0;

	static boolean visited[][] = new boolean[10][9];
	static int map[][] = new int[10][9];
	static int sx, sy, ex, ey;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(in.readLine(), " ");
		ex = Integer.parseInt(st.nextToken());
		ey = Integer.parseInt(st.nextToken());

		map[ex][ey] = 1;
		bfs();
		System.out.println(ans);
	}

	static void bfs() {
		Queue<State> q = new LinkedList<>();

		visited[sx][sy] = true;

		q.add(new State(sx, sy, 0));

		while (!q.isEmpty()) {
			State now = q.poll();
			int x = now.x;
			int y = now.y;
			int cnt = now.cnt;
			for (int i = 0; i <= 3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || nx > 9 || ny < 0 || ny > 8 || map[nx][ny] == 1)
					continue;

				for (int j = 0; j <= 1; j++) 
				{
					int dir = direct[i][j];
					int nx2 = nx;
					int ny2 = ny;

					for (int k = 1; k <= 2; k++) {

						nx2 = nx2 + dx2[dir];
						ny2 = ny2 + dy2[dir];

						if (nx2 < 0 || nx2 > 9 || ny2 < 0 || ny2 > 8 || (k == 1 && map[nx2][ny2] == 1))
							break;

						if (nx2 == ex && ny2 == ey && k == 2) {

							ans = cnt + 1;
							return;
						}

						else if (k == 2 && visited[nx2][ny2] == false) {

							visited[nx2][ny2] = true;
							q.add(new State(nx2, ny2, cnt + 1));

						}

					}

				}

			}
		}
	}
}
