package study63;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj4920 {

	static int type[][] = { { 0, 0, 0, 1, 2, 3 }, { 1, 2, 3, 0, 0, 0 }, { 0, 1, 1, 1, 1, 2 }, { 1, 1, 2, 0, -1, -1 },
			{ 0, 0, 1, 1, 2, 2 }, { 1, 2, 2, 0, 0, -1 }, { 1, 1, 1, 0, 1, 2 }, { 0, 1, 2, 1, 0, 0 },
			{ 0, 1, 0, 1, 1, 2 }, { 0, -1, 0, 1, 1, 2 }, { 1, 1, 2, 0, -1, 0 }, { 1, 1, 2, 0, 1, 0 },
			{ 0, 1, 1, 1, 0, 1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int t = 0, n = 0;
		int map[][];

		while (true) {

			t++;
			int ans = Integer.MIN_VALUE;
			n = Integer.parseInt(in.readLine().trim());

			if (n == 0)
				break;

			map = new int[n + 1][n + 1];

			for (int i = 1; i <= n; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				for (int j = 1; j <= n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {

					int val = map[i][j];

					for (int k = 0; k <= 12; k++) {
						int sum = 0;
						int cnt = 0;
						for (int z = 0; z <= 2; z++) {

							int nx = i + type[k][z];
							int ny = j + type[k][z + 3];

							if (nx < 1 || nx > n || ny < 1 || ny > n)
								break;

							sum += map[nx][ny];
							cnt++;

						}

						if (cnt == 3) {
							sum += val;

							ans = Integer.max(ans, sum);
						}

					}

				}
			}

			sb.append(t + ". " + ans + "\n");

		}

		System.out.println(sb.toString());

	}
}
