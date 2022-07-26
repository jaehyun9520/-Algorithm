package a0726;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj10711_모래성 {

	static int dx[] = { 1, -1, 0, 0, 1, 1, -1, -1 }; // 8방 탐색
	static int dy[] = { 0, 0, 1, -1, 1, -1, 1, -1 };

	static int map[][]; // 모래성의 튼튼함 (1~9)
	static int count[][]; // 모래성 주변의 모래가 없는곳의 개수
	static boolean remove[][];
	static int h, w;
	static ArrayList<int[]> list[];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		map = new int[h + 1][w + 1];
		count = new int[h + 1][w + 1];
		remove = new boolean[h + 1][w + 1];
		list = new ArrayList[2];

		for (int i = 1; i <= h; i++) {
			String input = in.readLine();
			for (int j = 1; j <= w; j++) {

				char val = input.charAt(j - 1);

				if (val == '.') {
					map[i][j] = 0;
				} else {
					map[i][j] = val - 48;
				}

			}
		}

		for (int i = 0; i <= 1; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 1; i <= h; i++) {
			for (int j = 1; j <= w; j++) {
				if (map[i][j] >= 1 && map[i][j] <= 8) {

					int cnt = 0;

					for (int k = 0; k <= 7; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];

						if (nx < 1 || nx > h || ny < 1 || ny > w)
							continue;

						if (map[nx][ny] == 0)
							cnt++;
					}

					count[i][j] = cnt;

					if (count[i][j] >= map[i][j]) // 모래성이 무너지는 경우
					{
						remove[i][j] = true;
						list[1].add(new int[] { i, j });
					}
				}
			}
		}

		System.out.println(simulation());

	}

	static int simulation() {
		int cnt = 0; // 파도 횟수
		while (true) {
			cnt++;

			list[(cnt + 1) % 2].clear();
			for (int i = 0; i < list[cnt % 2].size(); i++) {

				int[] loc = list[cnt % 2].get(i);

				for (int j = 0; j <= 7; j++) {
					int nx = loc[0] + dx[j];
					int ny = loc[1] + dy[j];

					if (nx < 1 || nx > h || ny < 1 || ny > w)
						continue;

					count[nx][ny]++;
					if (remove[nx][ny] == false && map[nx][ny] >= 1 && count[nx][ny] >= map[nx][ny]) {
						map[nx][ny] = 0;
						remove[nx][ny] = true;
						list[(cnt + 1) % 2].add(new int[] { nx, ny });
					}
				}
			}

			if (list[(cnt + 1) % 2].size() == 0) {
				break;
			}

		}
		return cnt;
	}
}
