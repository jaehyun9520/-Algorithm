package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2344_거울 {

	static int n, m;
	static int map[][];
	static int dx[] = { 1, 0, -1, 0 };// 아래 오른쪽 위 왼쪽 순서
	static int dy[] = { 0, 1, 0, -1 };

	static int mirror[] = { 3, 2, 1, 0 }; // 거울에 반사되는 방향

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		int ans[] = new int[2 * n + 2 * m + 1];
		map = new int[n + 2][m + 2];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 1; j <= m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		numbering();

		int number = 1;
		int dir = 0;
		int x = 0, y = 0;
		while (number <= 2 * n + 2 * m) {
			// 상하좌우 확인해서 1<= x <=n 1<= y <= m 의 조건을 가지면 해당 위치에 방향으로 빛 쏘기

			for (int i = 0; i <= 3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (1 <= nx && nx <= n && 1 <= ny && ny <= m) {
					int des = simul(x, y, i); // 현재 방향으로 시뮬레이션

					ans[number] = des;
					number++;

				}
			}
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (nx < 0 || nx > n + 1 || ny < 0 || ny > m + 1) {
				dir++;
				if (dir == 4)
					dir = 0;

				nx = x + dx[dir];
				ny = y + dy[dir];
			}

			x = nx;
			y = ny;

		}
		
		for(int i=1; i<=2*n+2*m; i++)
		{
			System.out.print(ans[i]+" ");
		}

	}

	// 시작 좌표와 방향
	private static int simul(int x, int y, int i) {

		while (true) {
			x = x + dx[i];
			y = y + dy[i];

			if (x == 0 || x == n + 1 || y == 0 || y == m + 1) {
				return map[x][y];
			}

			if (map[x][y] == 1) {
				i = mirror[i];
			}

		}

		
	}

	private static void numbering() {

		int n1 = 1;

		for (int i = 1; i <= n; i++) // 왼쪽
		{
			map[i][0] = n1; // 왼쪽 넘버링
			map[n + 1 - i][m + 1] = n + m + n1; // 오른쪽

			n1++;
		}
		n1 = 1;
		for (int i = 1; i <= m; i++) {
			map[n + 1][i] = n + n1;
			map[0][m - i + 1] = n * 2 + m + n1;
			n1++;
		}

	}
}
