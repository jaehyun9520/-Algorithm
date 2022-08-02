package a0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1103_게임 {

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };
	static char map[][];
	static int moveCnt[][];
	static boolean visited[][];
	static int n, m, ans;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n + 1][m + 1];
		moveCnt = new int[n + 1][m + 1];
		visited = new boolean[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {
			String input = in.readLine();
			for (int j = 1; j <= m; j++) {
				map[i][j] = input.charAt(j - 1);
			}
		}

		visited[1][1] = true;

		dfs(1, 1);

		System.out.println(ans);
	}

	static void dfs(int x, int y) {
		if (map[x][y] == 'H') {
			ans = Integer.max(moveCnt[x][y], ans);
		}

		else {
			int count = map[x][y] - 48;

			for (int i = 0; i <= 3; i++) {
				int nx = x + dx[i] * count;
				int ny = y + dy[i] * count;

				if (nx < 1 || nx > n || ny < 1 || ny > m) {
					ans = Integer.max(moveCnt[x][y] + 1, ans);
				}

				else if (visited[nx][ny] == true) {
					System.out.println(-1);
					System.exit(0);
				}

				else if (moveCnt[x][y] + 1 > moveCnt[nx][ny]) {
					moveCnt[nx][ny] = moveCnt[x][y] + 1;
					visited[nx][ny] = true;
					dfs(nx, ny);
					visited[nx][ny] = false;

				}
			}

		}

	}
}
