package study44;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1981_배열에서이동 {

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	static int map[][];
	static boolean visited[][];
	static int n;

	static class Value {
		int max, min, x, y;

		Value(int max, int min, int x, int y) {
			this.max = max;
			this.min = min;
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());
		map = new int[n + 1][n + 1];

		StringTokenizer st;

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;
		int low = 0, high = 200, mid = 0;

		while (low <= high) {
			mid = (low + high) / 2;

			if (simulation(mid)) {

				ans = mid;
				high = mid - 1;
			}

			else {
				low = mid + 1;
			}

		}

		System.out.println(ans);

	}

	static boolean simulation(int val) {
		boolean flag = false;
		for (int max = val; max <= 200; max++) {
			int min = max - val;

			flag = bfs(max, min);

			if (flag)
				return flag;

		}

		return flag;

	}

	static boolean bfs(int high, int low) {
		Queue<Value> q = new LinkedList<>();
		visited = new boolean[n + 1][n + 1];
		boolean flag = false;

		if (low <= map[1][1] && map[1][1] <= high) {

			visited[1][1] = true;

			q.add(new Value(map[1][1], map[1][1], 1, 1));

		}
		while (!q.isEmpty()) {
			Value now = q.poll();

			if (now.x == n && now.y == n) {

				flag = true;
				break;
			}

			for (int i = 0; i <= 3; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 1 || nx > n || ny < 1 || ny > n)
					continue;

				int max = Integer.max(now.max, map[nx][ny]);
				int min = Integer.min(now.min, map[nx][ny]);
				if (low <= min && max <= high && visited[nx][ny] == false) {

					visited[nx][ny] = true;
					q.add(new Value(max, min, nx, ny));
				}

			}

		}

		return flag;
	}
}
