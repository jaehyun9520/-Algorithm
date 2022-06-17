package study43;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj10836_여왕벌2 {

	static int map[][];
	static int start[][];
	static int m, n;
	static int range[][];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		map = new int[m + 1][m + 1];

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= m; j++) {
				map[i][j] = 1;
			}
		}

		range = new int[2 * m + 1][2 * m + 1];
		start = new int[2 * m][2]; // x,y값

		int cnt = 1;
		for (int i = m; i >= 1; i--) {
			start[cnt][0] = i;
			start[cnt][1] = 1;
			cnt++;
		}

		for (int i = 2; i <= m; i++) {
			start[cnt][0] = 1;
			start[cnt][1] = i;
			cnt++;
		}

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int zero = Integer.parseInt(st.nextToken());

			int s = 1 + zero, e = 1 + zero; // 1 1 로 시작해서 4 1 ~4 0 5 5 부터 시작

			int one = Integer.parseInt(st.nextToken());

			if (one != 0) { // 5 5 에서 3 증가 5 8 5 6 7
				e = e + one;

//				System.out.println("1증가  좌표 s=" + s + " e=" + e);
				range[s][e - 1]++;

			}

			int two = Integer.parseInt(st.nextToken());

			if (two != 0) {
				s = e;
				e += two;
//				System.out.println("2증가  좌표 s=" + s + " e=" + e);
				range[s][e - 1] += 2;

			}
		}

//		for (int i = 1; i <= 2 * m - 1; i++) {
//			for (int j = 1; j <= 2 * m - 1; j++) {
//				System.out.print(range[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("------------------------------");

		for (int i = 1; i <= 2 * m - 1; i++) {
			int sum = 0;
			for (int j = 2 * m - 1; j >= i; j--) {
				sum += range[i][j];

				int x = start[j][0];
				int y = start[j][1];

				map[x][y] += sum;

			}
		}

		for (int i = 2; i <= m; i++) {
			for (int j = i; j <= m; j++) {

				int max = Integer.max(map[j - 1][i - 1], map[j][i - 1]);
				max = Integer.max(max, map[j - 1][i]);

				map[j][i] = max;

			}

			for (int j = i + 1; j <= m; j++) {
				int max = Integer.max(map[i][j - 1], map[i][j - 1]);
				max = Integer.max(max, map[i - 1][j]);

				map[i][j] = max;
			}

		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= m; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

	}

}
