package study59;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15573 {

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };
	static int n, m, k; //가로 , 세로, 광물 개수
	static int map[][];
	static List<int[]> startLoc;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		startLocInit();

		System.out.println(binarySearch());

	}

	static int binarySearch() {
		int ans = 0;
		int low = 1, high = 1000000, mid = 0;

		while (low <= high) {

			mid = (low + high) / 2;

			if (bfs(mid)) {

				ans = mid;
				high = mid - 1;

			}

			else {
				low = mid + 1;
			}
		}

		return ans;
	}

	static boolean bfs(int val) {

		int visited[][] = new int[n + 1][m + 1]; // 방문체크 배열
		int totalCnt = 0;
		for (int i = 1; i < startLoc.size(); i++) {

			int x = startLoc.get(i)[0];
			int y = startLoc.get(i)[1];
			if (val >= map[x][y] && visited[x][y] == 0) {

				visited[x][y] = 1;
				totalCnt++;

				Queue<int[]> q = new LinkedList<>();
				q.add(new int[] { x, y });

				while (!q.isEmpty()) {

					int now[] = q.poll();
					x = now[0];
					y = now[1];

					for (int j = 0; j <= 3; j++) {
						int nx = x + dx[j];
						int ny = y + dy[j];

						if (nx < 1 || nx > n || ny < 1 || ny > m || map[nx][ny] > val)
							continue;

						// 처음 방문되는 곳이라면?
						if (visited[nx][ny] == 0) {
							totalCnt++;
							visited[nx][ny] = 1;
							q.add(new int[] { nx, ny });
						}

					}

				}

			}

			if (totalCnt >= k)
				return true;

		}

		return false;

	}

	static void startLocInit() {

		startLoc = new ArrayList<>();
		startLoc.add(new int[] { 0, 0 });
		for (int i = 1; i <= m; i++) {
			startLoc.add(new int[] { 1, i });
		}

		for (int i = 2; i <= n; i++) {
			startLoc.add(new int[] { i, 1 });
			startLoc.add(new int[] { i, m });
		}
	}
}
