package a0730;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj9328_열쇠 {

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };

	static int h, w;
	static int map[][];
	static int keyList[];
	static ArrayList<int[]> startPoint = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			int ans = 0;
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			startPoint.clear();
			keyList = new int[26];
			map = new int[h + 1][w + 1];

			for (int i = 1; i <= h; i++) {
				String input = in.readLine();
				for (int j = 1; j <= w; j++) {
					map[i][j] = input.charAt(j - 1);

					if (i == 1 || i == h || j == 1 || j == w) // 가장 자리이면서
					{
						if (map[i][j] != '*') {

							startPoint.add(new int[] { i, j });

							if (map[i][j] >= 'a' && map[i][j] <= 'z') // 열쇠라면 열쇠 보유현황에 기록해둘것
							{
								keyList[map[i][j] - 'a'] = 1;
							}

							if (map[i][j] == '$') {
								ans++;
								map[i][j] = '.';
							}

						}
					}

				}

			}

			String input = in.readLine();

			for (int i = 0; i < input.length(); i++) {
				if (input.charAt(i) != '0') {
					keyList[input.charAt(i) - 'a'] = 1;

				}

			}

			ans += bfs();

			sb.append(ans + "\n");

		}

		System.out.println(sb.toString());

	}

	static int bfs() {

		int cnt = 0;
		while (true) {

			cnt = 0;
			boolean visited[][] = new boolean[h + 1][w + 1];
			ArrayList<Integer> newKey = new ArrayList<>();
			Queue<int[]> q = new LinkedList<>();

			// 시작 포인트로 체크

			for (int i = 0; i < startPoint.size(); i++) {
				int x = startPoint.get(i)[0];
				int y = startPoint.get(i)[1];

				if (map[x][y] == '.' || (map[x][y] >= 'a' && map[x][y] <= 'z')) {
					visited[x][y] = true;
					q.add(new int[] { x, y });
				}

				else if ('A' <= map[x][y] && map[x][y] <= 'Z') {
					if (keyList[map[x][y] - 'A'] == 1) {
						visited[x][y] = true;
						q.add(new int[] { x, y });
					}

				}

			}

			while (!q.isEmpty()) {
				int now[] = q.poll();

				int x = now[0];
				int y = now[1];

				for (int i = 0; i <= 3; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];

					if (nx < 1 || nx > h || ny < 1 || ny > w || visited[nx][ny] == true || map[nx][ny] == '*')
						continue;

					if (map[nx][ny] == '.') {
						visited[nx][ny] = true;
						q.add(new int[] { nx, ny });
					}

					else if ('a' <= map[nx][ny] && map[nx][ny] <= 'z') {

						visited[nx][ny] = true;

						if (keyList[map[nx][ny] - 'a'] == 0) {

							newKey.add(map[nx][ny]);
						}

						q.add(new int[] { nx, ny });

					}

					else if ('A' <= map[nx][ny] && map[nx][ny] <= 'Z') {

						if (keyList[map[nx][ny] - 'A'] == 1) {

							visited[nx][ny] = true;

							q.add(new int[] { nx, ny });

						}

					}

					else if (map[nx][ny] == '$') {

						cnt++;
						visited[nx][ny] = true;

						q.add(new int[] { nx, ny });
					}

				}

			}

			if (newKey.size() == 0) {
				break;
			}

			else {

				for (int i = 0; i < newKey.size(); i++) {
					int key = newKey.get(i);

					keyList[key - 'a'] = 1;

				}
			}

		}

		return cnt;
	}
}
