package study60;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2589 {

	static int map[][];
	static int n, m;
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };
	static int ans = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {

			String input = in.readLine();

			for (int j = 1; j <= m; j++) {

				if (input.charAt(j - 1) == 'W') {
					map[i][j] = 1;
				} else {
					map[i][j] = 0;
				}

			}

		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {

				if (map[i][j] == 0) {

					bfs(i, j);
				}
			}
		}
		
		System.out.println(ans);

	}

	static void bfs(int x, int y) {

		boolean visited[][] = new boolean[n + 1][m + 1];
		Queue<int[]> q = new LinkedList<>();

		visited[x][y] = true;

		q.add(new int[] { x, y, 0 });

		while (!q.isEmpty()) {

			int now[] = q.poll();
			x = now[0];
			y = now[1];
			int time = now[2];

			ans = Integer.max(ans, time);

			for (int i = 0; i <= 3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 1 || nx > n || ny < 1 || ny > m || map[nx][ny] == 1 || visited[nx][ny])
					continue;

				visited[nx][ny] = true;
				q.add(new int[] { nx, ny, time + 1 });
			}

		}

	}
}
