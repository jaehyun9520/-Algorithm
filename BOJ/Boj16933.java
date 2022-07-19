package a0719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16933_벽부수고이동하기3 {

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };

	static class State {
		int r, c, moveCnt, skill, day; // day가 0 이면 낮 1이면 밤

		State(int r, int c, int moveCnt, int skill, int day) {
			this.r = r;
			this.c = c;
			this.moveCnt = moveCnt;
			this.skill = skill;
			this.day = day;
		}

	}

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
		Queue<State> q = new LinkedList<>();
		visited[0][1][1] = true;

		q.add(new State(1, 1, 1, 0, 0));

		while (!q.isEmpty()) {
			State now = q.poll();

			int r = now.r;
			int c = now.c;
			int moveCnt = now.moveCnt;
			int day = now.day;
			int skill = now.skill;

			if (r == n && c == m) {
				ans = moveCnt;
				break;
			}

			for (int i = 0; i <= 3; i++) {

				int nr = r + dx[i];
				int nc = c + dy[i];

				if (nr < 1 || nr > n || nc < 1 || nc > m || visited[skill][nr][nc] == true)
					continue;

				// 빈칸인 경우
				if (map[nr][nc] == 0) {
					visited[skill][nr][nc] = true;

					q.add(new State(nr, nc, moveCnt+1, skill, (day + 1) % 2));

				}

				else if (skill < k&&visited[skill+1][nr][nc]==false) // 벽인 경우 벽을 부술 수 있는 횟수가 아직 남아있다면?
				{
					// 낮인 경우
					if (day == 0) {
						visited[skill + 1][nr][nc] = true;
						q.add(new State(nr, nc, moveCnt+1, skill + 1, (day + 1) % 2));

					}

					else { // 밤인 경우

						q.add(new State(r, c, moveCnt + 1, skill, (day + 1) % 2));
					}

				}

			}

		}
		return ans;
	}
}
