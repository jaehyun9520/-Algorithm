package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj9944_보드완주하기 {

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };
	static int totalCnt = 0, n, m, ans = Integer.MAX_VALUE;
	static char map[][];
	static boolean visited[][];

	public static void main(String[] args) throws IOException {

		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int t = 0;

		while (true) {
			
              t++;
			String input = in.readLine();
			
			if (input==null) {
				break;
			}
			StringTokenizer st = new StringTokenizer(input, " ");

			
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			ans = Integer.MAX_VALUE;
			totalCnt = 0;
			map = new char[n + 1][m + 1];
			visited = new boolean[n + 1][m + 1];

			for (int i = 1; i <= n; i++) {
				input = in.readLine();

				for (int j = 1; j <= m; j++) {

					map[i][j] = input.charAt(j - 1);

					if (map[i][j] == '.')
						totalCnt++;

				}

			}

			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					if (map[i][j] == '.') {

						if (totalCnt == 1)
							ans = 0;

						else {

							for (int k = 0; k <= 3; k++) {
								int nx = i + dx[k];
								int ny = j + dy[k];

								if (nx < 1 || nx > n || ny < 1 || ny > m || map[i][j] == '*')
									continue;

								visited[i][j] = true;
								dfs(i, j, k, 1, 1);
								visited[i][j] = false;

							}

						}

					}

				}
			}
			sb.append("Case "+t+": ");
			
			
			if (ans == Integer.MAX_VALUE)
				sb.append(-1+"\n");

			else
				sb.append(ans+"\n");

		}
		System.out.println(sb.toString());
	}

	static void dfs(int x, int y, int dir, int dirChangeCnt, int visitedB) {
		if (ans < dirChangeCnt)
			return;

		if (visitedB == totalCnt) {
			ans = Integer.min(ans, dirChangeCnt);
			return;
		}

		else {

			//현재 주어진 방향으로 더 이동할 수 있는지 확인

			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (nx >= 1 && nx <= n && ny >= 1 && ny <= m && visited[nx][ny] == false && map[nx][ny] == '.') {
				visited[nx][ny] = true;
				dfs(nx, ny, dir, dirChangeCnt, visitedB + 1);
				visited[nx][ny] = false;
			}

			else { // 주어진 방향으로 안되는 경우 다른방향으로 되는지 확인하고 되면 더 진행

				for (int i = 0; i <= 3; i++) {
					nx = x + dx[i];
					ny = y + dy[i];

					if (nx < 1 || nx > n || ny < 1 || ny > m || visited[nx][ny] == true || map[nx][ny] == '*')
						continue;

					visited[nx][ny] = true;
					dfs(nx, ny, i, dirChangeCnt + 1, visitedB + 1);
					visited[nx][ny] = false;

				}
			}

		}

	}
}
