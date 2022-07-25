package a0725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16932_모양만들기 {

	static int n, m;
	static int map[][];
	static int area[][];
	static int areaSize[]; // 번호별로 영역의 크기

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int ans = 0;
		map = new int[n + 1][m + 1];
		area = new int[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		areaCheck();

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (map[i][j] == 0) {
					HashSet<Integer> set = new HashSet<>();
					int cnt = 1;
					for (int k = 0; k <= 3; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];

						if (nx < 1 || nx > n || ny < 1 || ny > m || map[nx][ny] == 0)
							continue;

						if (!set.contains(area[nx][ny])) {
							set.add(area[nx][ny]);

							cnt += areaSize[area[nx][ny]];
						}

					}
					ans = Integer.max(ans, cnt);

				}
			}
		}

		System.out.println(ans);

	}

	static void areaCheck() {

		int num = 0;

		ArrayList<int[]> areaList = new ArrayList<>();
		Queue<int[]> q = new LinkedList<>();

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (map[i][j] == 1 && area[i][j] == 0) {
					num++;
					area[i][j] = num;
					int cnt = 1;

					q.add(new int[] { i, j });

					while (!q.isEmpty()) {
						int now[] = q.poll();
						int x = now[0];
						int y = now[1];

						for (int k = 0; k <= 3; k++) {
							int nx = x + dx[k];
							int ny = y + dy[k];

							if (nx < 1 || nx > n || ny < 1 || ny > m || map[nx][ny] == 0 || area[nx][ny] != 0)
								continue;

							area[nx][ny] = num;
							cnt++;

							q.add(new int[] { nx, ny });

						}

					}

					areaList.add(new int[] { num, cnt });

				}
			}
		}

		areaSize = new int[num + 1];

		for (int i = 0; i < areaList.size(); i++) {
			int number = areaList.get(i)[0];
			int size = areaList.get(i)[1];

			areaSize[number] = size;
		}

	}
}
