package study67;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj7562 {

	static int dx[] = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int dy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };

	static boolean map[][];
	static int n;
	static int knight[] = new int[4];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(in.readLine());

		for (int i = 1; i <= t; i++) {

			n = Integer.parseInt(in.readLine());
			map = new boolean[n][n];
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j <= 3; j++) {
				if (j == 2)
					st = new StringTokenizer(in.readLine(), " ");
				knight[j] = Integer.parseInt(st.nextToken());

			}
			sb.append(bfs() + "\n");
		}
		
		System.out.println(sb.toString());
		

	}

	static int bfs() {
		int answer = 0;

		Queue<int[]> q = new LinkedList<>();

		int sx = knight[0];
		int sy = knight[1];
		int ex = knight[2];
		int ey = knight[3];
		map[sx][sy] = true;
		q.add(new int[] { sx, sy, 0 });

		while (!q.isEmpty()) {

			int now[] = q.poll();
			int x = now[0];
			int y = now[1];
			int cnt = now[2];

			if (x == ex && y == ey) {
				answer = cnt;
				break;
			}

			for (int i = 0; i <= 7; i++) {

				int nx = x + dx[i];
				int ny = y + dy[i];

				// 범위를 벗어났거나 방문한적이 있다면 생략
				if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny])
					continue;

				map[nx][ny] = true;
				q.add(new int[] { nx, ny, cnt + 1 });

			}

		}
		return answer;
	}
}
