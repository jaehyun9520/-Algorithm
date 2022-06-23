package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15685_드래곤커브 {

	static int map[][] = new int[101][101]; // y, x
	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, -1, 0, 1 };

	static int dirChange[] = { 3, 0, 1, 2 }; // 방향 변환 (시계방향 90도 회전)

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());

		StringTokenizer st;
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			int idx = 2;
			int record[][] = new int[1025][3]; // 0 y좌표, x좌표 방향
			record[1][0] = y + dy[d];
			record[1][1] = x + dx[d];
			record[1][2] = d;
			map[y][x] = 1;

			map[y + dy[d]][x + dx[d]] = 1;

			// 세대별로 증가시키면서 진행

			for (int j = 1; j <= g; j++) {
				int cnt = 1;

				for (int k = idx / 2 + 1; k <= idx; k++) {
					int preY = record[k - 1][0];
					int preX = record[k - 1][1];
					int preD = record[k - 1][2];

					map[preY][preX] = 1;

					int dir = record[idx / 2 + 1 - cnt][2];

					dir = (dir + 2) % 4;
					dir = dirChange[dir];

					int ny = preY + dy[dir];
					int nx = preX + dx[dir];

					map[ny][nx] = 1;

					record[k][0] = ny;
					record[k][1] = nx;
					record[k][2] = dir;

					cnt++;
				}

				idx *= 2;
			}

		}

		int ans = 0;

		for (int i = 0; i <= 99; i++) {
			for (int j = 0; j <= 99; j++) {
				if (map[i][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j + 1] == 1 && map[i + 1][j] == 1) {

					ans++;
				}

			}
		}

		System.out.println(ans);
	}

}
