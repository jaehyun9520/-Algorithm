package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj4991_로봇청소기 {

	static int w, h, sx, sy, cnt;
	static int map[][];
	static boolean visited[][][];
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {

		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while (true) {
			st = new StringTokenizer(in.readLine());

			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			if (w == 0 && h == 0)
				break;

			cnt = 0; // 더러운 칸의 개수
			map = new int[h + 1][w + 1];
			for (int i = 1; i <= h; i++) {
				String input = in.readLine();
				for (int j = 1; j <= w; j++) {

					int val = input.charAt(j - 1);

					if (val == 'o') {
						sx = i;
						sy = j;
						map[i][j] = '*';
					}

					else if (val == '*') {
						cnt++;
						map[i][j] = cnt;
					}

					else {
						map[i][j] = val;
					}

				}
			}

			
			// 방문을 위한 3차원 배열 생성
			visited = new boolean[1 << cnt][h + 1][w + 1];

			if (cnt != 0) {

				sb.append(bfs() + "\n");
			} else
				sb.append(0 + "\n");
		}

		System.out.println(sb.toString());

	}

	static int bfs() {

		Queue<int[]> q = new LinkedList<>();

		visited[0][sx][sy] = true;

		q.add(new int[] { sx, sy, 0, 0 });

		while (!q.isEmpty()) {
			int[] now = q.poll();

			int x = now[0];
			int y = now[1];
			int state = now[2];
			int count = now[3];
			
			
			for (int i = 0; i <= 3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 1 || nx > h || ny < 1 || ny > w || visited[state][nx][ny] == true || map[nx][ny] == 'x')
					continue;

				if (1 <= map[nx][ny] && map[nx][ny] <= 10) {
					visited[state | (1<<map[nx][ny]-1)][nx][ny] = true;

					if ((state | (1<<map[nx][ny]-1)) == (1 << cnt) - 1) {
						return count + 1;
					}

					q.add(new int[] { nx, ny, (state |(1<<map[nx][ny]-1)), count + 1 });

				}

				else {
					visited[state][nx][ny] = true;
					q.add(new int[] { nx, ny, state, count + 1 });
				}

			}

		}

		return -1;

	}
}
