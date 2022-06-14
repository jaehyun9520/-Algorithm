package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj18405_경쟁적감염 {

	static class Virus implements Comparable<Virus> {
		int x, y, number, time;

		Virus(int x, int y, int number, int time) {
			this.x = x;
			this.y = y;
			this.number = number;
			this.time = time;
		}

		@Override
		public int compareTo(Virus o) {

			if (this.time == o.time) {
				return this.number - o.number;
			}

			return this.time - o.time;
		}

	}

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static int n, k, tx, ty, endTime, ans = 0;
	static int map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n + 1][n + 1];
		PriorityQueue<Virus> pq = new PriorityQueue<>();

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] != 0) {
					pq.add(new Virus(i, j, map[i][j], 0));
				}

			}
		}

		st = new StringTokenizer(in.readLine(), " ");
		endTime = Integer.parseInt(st.nextToken());
		tx = Integer.parseInt(st.nextToken());
		ty = Integer.parseInt(st.nextToken());

		bfs(pq);

		System.out.println(ans);
	}

	static void bfs(PriorityQueue<Virus> pq) {

		while (!pq.isEmpty()) {
			Virus now = pq.poll();

			int x = now.x;
			int y = now.y;
			int number = now.number;
			int time = now.time;

			if (time == endTime) {
				ans = map[tx][ty];
				break;
			}

			for (int i = 0; i <= 3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 1 || nx > n || ny < 1 || ny > n || map[nx][ny] != 0)
					continue;

				map[nx][ny] = number;

				pq.add(new Virus(nx, ny, number, time + 1));

			}

		

		}
		
			ans = map[tx][ty];
	}
}
