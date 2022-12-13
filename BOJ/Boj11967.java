package study67;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj11967 {

	static int n, m,ans;
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };
	static boolean visited[][]; // 방문 정보
	static List<int[]> switchInfo[][]; // 각 방의 스위치 정보
	static boolean switchOn[][]; // 불이 켜져 있는지 여부

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		visited = new boolean[n + 1][n + 1];
		switchOn = new boolean[n + 1][n + 1];
		switchInfo = new ArrayList[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				switchInfo[i][j] = new ArrayList<>();
			}
		}

		int x1, y1, x2, y2;
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(in.readLine(), " ");

			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());

			switchInfo[x1][y1].add(new int[] { x2, y2 });

		}
		solution();
		System.out.println(ans);
		

	}

	static void solution() {

		ans=1;
		visited[1][1] = true;
		switchOn[1][1] = true;

		Queue<int[]> q = new LinkedList<>();

		switchOn(1, 1, q); // 해당 방의 스위치 켜기

		while (!q.isEmpty()) {

			int[] now = q.poll();

			int x = now[0];
			int y = now[1];
			
			
			boolean connect = false; // (1.1)과 연결여부 확인

			for (int i = 0; i <= 3; i++) {

				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 1 || nx > n || ny < 1 || ny > n || !visited[nx][ny]||visited[x][y]) continue;

				
				// 연결되어 있고 처음 방문한다면?
				connect = true;

				visited[x][y] = true; // 방문처리
				switchOn[x][y] = true; // 불켜기

				// 해당 방에 존재하는 스위치를 다 작동
				switchOn(x, y, q);
				break;

			}

			// (1.1)과 연결되어 있다면?
			if (connect) {

				// 상하좌우를 확인하며 불이 켜져 있고 방문되지 않은 방을 큐에 넣어준다.

				for (int i = 0; i <= 3; i++) {

					int nx = x + dx[i];
					int ny = y + dy[i];

					if (nx < 1 || nx > n || ny < 1 || ny > n || visited[nx][ny] || !switchOn[nx][ny])
						continue;

					q.add(new int[] { nx, ny });
				}

			}

		}
	}

	static void switchOn(int x, int y, Queue<int[]> q) {

		for (int i = 0; i < switchInfo[x][y].size(); i++) {

			int[] info = switchInfo[x][y].get(i);

			int nx = info[0];
			int ny = info[1];

			if (!switchOn[nx][ny]) {
				ans++;
				switchOn[nx][ny] = true;

				q.add(new int[] { nx, ny });

			}

		}
	}
}
