package study46;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14442_벽부수고이동하기2 {

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };

	static int n, m, k;
	static int map[][];
	static boolean visited[][][];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n + 1][m + 1];
		visited = new boolean[k + 1][n + 1][m + 1];

		for (int i = 1; i <= n; i++) {
			String input = in.readLine();

			for (int j = 1; j <= m; j++) {
				map[i][j] = input.charAt(j - 1) - 48;
			}

		}

		System.out.println(bfs());

	}

	static int bfs() {
		int ans = -1;

		Queue<int[]> q = new LinkedList<>();

		visited[0][1][1] = true;

		q.add(new int[] { 1, 1, 1, 0 }); // 행,열 ,이동횟수 ,k사용 횟수

		while (!q.isEmpty()) {
			int now[] = q.poll();
			int x = now[0];
			int y = now[1];
			int moveCnt = now[2];
			int useCnt = now[3];

			if (ans != -1 && moveCnt > ans)
				continue;

			if (x == n && y == m) {
				if (ans == -1)
					ans = moveCnt;

				else
					ans = Integer.min(ans, moveCnt);
			}

			for (int i = 0; i <= 3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 1 || nx > n || ny < 1 || ny > m)
					continue;

				if (map[nx][ny] == 0 && visited[useCnt][nx][ny] == false) {
					visited[useCnt][nx][ny] = true;
					q.add(new int[] { nx, ny, moveCnt + 1, useCnt });
				}

				else if (map[nx][ny] == 1 && useCnt < k && visited[useCnt + 1][nx][ny] == false) {
					visited[useCnt + 1][nx][ny] = true;
					q.add(new int[] { nx, ny, moveCnt + 1, useCnt + 1 });
				}

			}

		}

		return ans;
	}
}
