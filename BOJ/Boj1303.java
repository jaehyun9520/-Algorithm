package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1303 {

	static int m, n; // 세로, 가로
	static int map[][];
	static boolean visited[][];
	static int white, blue;
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[m + 1][n + 1];
		visited = new boolean[m + 1][n + 1];

		for (int i = 1; i <= m; i++) {
			String input = in.readLine();

			for (int j = 1; j <= n; j++) {
				map[i][j] = input.charAt(j - 1);
			}
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {

				if (map[i][j] == 'B' && visited[i][j] == false) {
					blue += score(i, j, 'B');
				}

				else if (map[i][j] == 'W' && visited[i][j] == false) {
					white += score(i, j, 'W');
				}

			}
		}

		System.out.println(white + " " + blue);

	}

	static int score(int i, int j, int color) {

		Queue<int[]> q = new LinkedList<>();

		visited[i][j] = true;
		int count = 1;

		q.add(new int[] { i, j });

		while (!q.isEmpty()) {
			int[] val = q.poll();

			int x = val[0];
			int y = val[1];

			for (i = 0; i <= 3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 1 || nx > m || ny < 1 || ny > n)
					continue;

				if (map[nx][ny] == color && visited[nx][ny] == false) {
					count++;
					visited[nx][ny] = true;

					q.add(new int[] { nx, ny });
				}

			}

		}

		return (int) Math.pow(count, 2);

	}

}
