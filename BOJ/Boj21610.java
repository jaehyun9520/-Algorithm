package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj21610_마법사상어와비바라기 {

	static int dx[] = { 0, 0, -1, -1, -1, 0, 1, 1, 1 }; // 8 방향
	static int dy[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

	static int map[][];
	static int n, m;
	static int[][] move;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n + 1][n + 1];
		move = new int[m + 1][2]; // 이동명령 저장 ( 방향, 거리 )
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			move[i][0] = Integer.parseInt(st.nextToken());
			move[i][1] = Integer.parseInt(st.nextToken());
		}

		System.out.println(simul());

	}

	static int simul() {

		// 처음 4칸을 구름 리스트에 추가

		int initS[][] = { { n, 1 }, { n, 2 }, { n - 1, 1 }, { n - 1, 2 } };

		ArrayList<int[]> cloud = new ArrayList<>(); // 비구름 리스트
		ArrayList<int[]> copy = new ArrayList<>(); // 물복사 마법 사용할 위치
		boolean check[][] = new boolean[n + 1][n + 1];
		int sum[][] = new int[n + 1][n + 1];
		for (int i = 0; i <= 3; i++) {
			cloud.add(new int[] { initS[i][0], initS[i][1] });

		}

		for (int i = 1; i <= m; i++) // 이동명령 시작
		{
			int d = move[i][0];
			int s = move[i][1] % n; // 실제 이동횟수

			for (int j = 0; j < cloud.size(); j++) // 1. 모든 구름이 d방향으로 s칸 이동한다
			{
				int x = cloud.get(j)[0];
				int y = cloud.get(j)[1];
				x = x + dx[d] * s;
				y = y + dy[d] * s;

				if (x > n) {
					x = x - n;
				}
				if (x <= 0) {
					x = n + x;
				}

				if (y > n) {
					y = y - n;
				}
				if (y <= 0) {
					y = n + y;
				}

				map[x][y]++; // 이동 후 해당위치 물 +1 증가
				check[x][y] = true;
				copy.add(new int[] { x, y });

			}

			for (int j = 0; j < copy.size(); j++) { // 4번
				int x = copy.get(j)[0];
				int y = copy.get(j)[1];

				int cnt = 0;
				for (int k = 2; k <= 8; k += 2) {
					int nx = x + dx[k];
					int ny = y + dy[k];

					if (nx < 1 || nx > n || ny < 1 || ny > n || map[nx][ny] <= 0)
						continue;

					cnt++;

				}
				sum[x][y] = cnt;
			}

			// 5번

			cloud.clear();

			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {

					map[j][k] += sum[j][k];

					if (map[j][k] >= 2 && check[j][k] == false) {

						map[j][k] -= 2;

						cloud.add(new int[] { j, k });

					}

				}
			}
			sum = new int[n + 1][n + 1];
			copy.clear();
			check = new boolean[n + 1][n + 1];
		}

		int ans = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				ans += map[i][j];
			}
		}

		return ans;

	}

}
