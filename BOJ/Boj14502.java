package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14502 {
	static int n, m; // 세로 , 가로
	static int map[][];
	static int ans = 0;
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };
	static List<int[]> virus = new ArrayList<>();
	static int count = 0; // 입력값으로 주어지는 빈공간의 개수

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= m; j++) {

				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virus.add(new int[] { i, j });
				}

				if (map[i][j] == 0) {
					count++;
				}

			}
		}

		combin(1, 1, 0);//
		System.out.println(ans);

	}

	static void combin(int x, int y, int cnt) {
		int arr[][] = new int[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				arr[i][j] = map[i][j];   //배열 복사 
			}
		}

		if (cnt == 3) {
			int tmp = count;
			// 안전영역 최대 크기 구하기
			Queue<int[]> Q = new LinkedList<>();

			for (int i = 0; i < virus.size(); i++) {
				Q.offer(virus.get(i));
			}
			while (!Q.isEmpty()) {
				int[] val = Q.poll();
				int a = val[0];
				int b = val[1];

				for (int k = 0; k <= 3; k++) {
					int nx = dx[k] + a;
					int ny = dy[k] + b;

					if (nx < 1 || nx > n || ny < 1 || ny > m)
						continue;

					if (arr[nx][ny] == 0) { 
						tmp--;
						arr[nx][ny] = 2;
						Q.offer(new int[] { nx, ny });
					}
				}

			}

			ans = Math.max(ans, tmp - 3);

		}

		else {

			for (int i = x; i <= n; i++) {   // 기둥 3개 세우기
				for (int j = 1; j <= m; j++) {
					if (j >= y || i >= x + 1) {
						if (map[i][j] == 0) {
							map[i][j] = 1;
							combin(i, j + 1, cnt + 1);
							map[i][j] = 0;

						}

					}

				}

			}

		}

	}
}
