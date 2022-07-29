package a0729;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj17370_육각형우리속의개미 {

	static int dx[] = { -1, 1, -1, 1, -1, 1 }; // 방향 설정
	static int dy[] = { 0, 0, -1, 1, 1, -1 };

	static int dir[][] = { { 2, 4 }, { 3, 5 }, { 0, 5 }, { 1, 4 }, { 0, 3 }, { 2, 1 } };
	static int n;
	static int map[][] = new int[1001][1001];
	static int ans = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());

		map[500][500] = 1;
		map[499][500] = 1;
		dfs(499, 500, 0, 0);

		System.out.println(ans);
	}

	static void dfs(int x, int y, int direct, int cnt) {

		for (int i = 0; i <= 1; i++) {
			int nx = x + dx[dir[direct][i]];
			int ny = y + dy[dir[direct][i]];

			if (map[nx][ny] == 1) {
				if (cnt + 1 == n) {

					ans++;
				}
			}

			else if (cnt + 1 < n) {
				map[nx][ny] = 1;
				dfs(nx, ny, dir[direct][i], cnt + 1);
				map[nx][ny] = 0;
			}

		}

	}

}
