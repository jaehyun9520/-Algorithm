package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj3184 {

	public static void main(String[] args) throws IOException {

		int r, c; // 행 열
		int dx[] = { 1, -1, 0, 0 };
		int dy[] = { 0, 0, 1, -1 };
		int map[][];
		boolean visited[][];
		int o = 0, v = 0; // 살아남은 양 , 늑대
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new int[r + 1][c + 1];
		visited = new boolean[r + 1][c + 1];

		for (int i = 1; i <= r; i++) {
			String input = in.readLine();
			for (int j = 1; j <= c; j++) {
				map[i][j] = input.charAt(j - 1);
			}
		}

		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {

				if ((map[i][j] == 'v' || map[i][j] == 'o') && visited[i][j] == false) {
					int sheep = 0, wolf = 0;

					if (map[i][j] == 'v')
						wolf++;
					else if (map[i][j] == 'o')
						sheep++;

					visited[i][j] = true;

					Queue<int[]> q = new LinkedList<>();

					q.add(new int[] { i, j });

					while (!q.isEmpty()) {
						int[] val = q.poll();

						int x = val[0];
						int y = val[1];

						for (int k = 0; k <= 3; k++) {
							int nx = x + dx[k];
							int ny = y + dy[k];

							if (nx < 0 || nx > r || ny < 0 || ny > c || visited[nx][ny] == true || map[nx][ny] == '#')
								continue;

							if (map[nx][ny] == 'o')
								sheep++;
							else if (map[nx][ny] == 'v')
								wolf++;

							visited[nx][ny] = true;

							q.add(new int[] { nx, ny });

						}

					}

					if (sheep > wolf) {
						o += sheep;
					} else {
						v += wolf;
					}

				}

			}
		}

		System.out.println(o + " " + v);

	}
}
