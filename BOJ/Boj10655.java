package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10655_마라톤1 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;

		int n = Integer.parseInt(in.readLine());

		int length[] = new int[n]; // 1 ~ n-1
		int val[][] = new int[n + 1][2]; // x,y좌표
		int totalLength = 0;
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			val[i][0] = x;
			val[i][1] = y;

			if (i > 1) {
				length[i - 1] = Math.abs(val[i - 1][0] - val[i][0]) + Math.abs(val[i - 1][1] - val[i][1]);
				totalLength += length[i - 1];
			}

		}
		ans = totalLength;

		for (int i = 2; i <= n - 1; i++) {

			int tmp = totalLength;

			tmp = tmp - length[i - 1] - length[i];

			int value = Math.abs(val[i - 1][0] - val[i + 1][0]) + Math.abs(val[i - 1][1] - val[i + 1][1]);

			tmp += value;

			ans = Integer.min(ans, tmp);

		}
		
		System.out.println(ans);

	}
}
