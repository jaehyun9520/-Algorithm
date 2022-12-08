package study67;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj17085 {

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };
	static int n, m, ans;
	static char map[][];
	static List<int[]> list = new ArrayList<>();
	static int crossInfo[] = new int[2];
	static int crossArea[] = { 1, 5, 9, 13, 17, 21, 25,29};

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {

			String input = in.readLine();

			for (int j = 1; j <= m; j++) {
				map[i][j] = input.charAt(j - 1);

				if (map[i][j] == '#') {
					list.add(new int[] { i, j });
				}
			}
		}

		crossCreate(0, 0);

		System.out.println(ans);
	}

	static void crossCreate(int order, int cnt) {

		// 두개를 다 만든경우
		if (cnt == 2) {
			ans = Integer.max(ans, crossInfo[0] * crossInfo[1]);
			return;
		}

		for (int i = order; i < list.size(); i++) {

			int x = list.get(i)[0];
			int y = list.get(i)[1];

			// 십자가를 놓을 공간이 있는 경우
			if (map[x][y] == '#') {

				int size = 0;

				loop: for (size = 0; size <= 7; size++) {

					// 해당 사이즈 위치에 놓을 수 있는지?
					for (int k = 0; k <= 3; k++) {

						int nx = x + dx[k] * size;
						int ny = y + dy[k] * size;

						// 범위를 벗어났거나 #모양이 아니라면 해당 사이즈로는 십자가를 만들 수 없으니 중단
						if (nx < 1 || nx > n || ny < 1 || ny > m || map[nx][ny] != '#') {
							break loop;
						}
					}

					// 만들수 있다면? 실제로 맵의 값을 변환
					for (int k = 0; k <= 3; k++) {

						int nx = x + dx[k] * size;
						int ny = y + dy[k] * size;

						map[nx][ny] = '*';
					}

					crossInfo[cnt] = crossArea[size];

					// 다음 십자가 생성
					crossCreate(i + 1, cnt + 1);

				}

				// 만든 십자가 제거
				crossDelete(x, y, size-1);

			}

		}

	}

	static void crossDelete(int i, int j, int size) {

		map[i][j] = '#';
		for (int s = 1; s <= size; s++) {

			for (int k = 0; k <= 3; k++) {

				int nx = i + dx[k] * s;
				int ny = j + dy[k] * s;
				map[nx][ny] = '#';
			}

		}

	}

}
