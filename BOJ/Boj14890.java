package study58;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14890 {

	static int n, l;
	static int map[][];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		int ans=0;
		map = new int[n*2+ 1][n + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=1; i<=n; i++)
		{
			for(int j=1; j<=n; j++)
			{
				map[i+n][j]=map[j][i];
			}
		}
		
		
		for (int i = 1; i <= n*2; i++) {
			boolean flag = true;
			boolean used[] = new boolean[n + 1];
			int preVal = map[i][1];
			for (int j = 2; j <= n; j++) {

				
				if (preVal > map[i][j]) {

					if (rightCheck(preVal, i, j, used)) {

						j = j + l - 1;
						preVal = map[i][j];
					}

					else {
						flag = false;
						break;
					}
				}

				else if (preVal < map[i][j]) {

					if (leftCheck(map[i][j], i, j - 1, used)) {
						preVal = map[i][j];
					}

					else {
						flag = false;
						break;
					}
				}

			}
			
			if(flag) ans++;

		}
		
		System.out.println(ans);

	}

	static boolean rightCheck(int val, int x, int y, boolean[] used) {

		// 2이상 차이가 나거나 길이가 안되는 경우 false return

		if (val - map[x][y] >= 2 || y + l - 1 > n) {
			return false;
		}
		int nextVal = val - 1;
		for (int i = y; i <= y + l - 1; i++) {

			used[i] = true;

			if (map[x][i] != nextVal) {
				return false;
			}

		}

		return true;

	}

	static boolean leftCheck(int val, int x, int y, boolean used[]) {

		// 차이가 2이상 나거나 길이가 안되는 경우 false return;

		if (val - map[x][y] >= 2 || y - l + 1 < 1)
			return false;

		int nextVal = val - 1;

		for (int i = y; i >= y - l + 1; i--) {

			// 이미 사용됐다면?
			if (used[i])
				return false;

			used[i] = true;

			if (map[x][i] != nextVal) {
				return false;
			}

		}
		return true;

	}
}
