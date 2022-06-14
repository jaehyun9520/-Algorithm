package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj18430_무기공학 {

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };

	static int sList[][] = { { 0,2 }, { 0, 3 }, { 1, 2 }, { 1, 3 } }; // 4가지 모양

	static ArrayList<int[]> list = new ArrayList<>();
	static int n, m, ans = 0;
	static int map[][];
	static boolean visited[][];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n + 1][m + 1];
		visited = new boolean[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				list.add(new int[] { i, j });
			}
		}

		dfs(0, 0);

		System.out.println(ans);

	}

	static void dfs(int index, int sum) {

	
		ans = Integer.max(ans, sum);

		for (int i = index; i < list.size(); i++) {
			int[] now = list.get(i);

			int x = now[0];
			int y = now[1];

			if (!visited[x][y]) // 사용된적이 없다면?
			{
				visited[x][y] = true;
				ArrayList<int[]> usedList = new ArrayList<>();

				for (int j = 0; j <= 3; j++) {
					boolean flag = true; // 내가 고른 위치에서 부메랑 모양을 만들수 있는가?
					int total = map[x][y] * 2;
					for (int k = 0; k <= 1; k++) {
						int nx = x + dx[sList[j][k]];
						int ny = y + dy[sList[j][k]];

						if (nx < 1 || nx > n || ny < 1 || ny > m || visited[nx][ny]) {
							flag = false;
							break;
						}

						visited[nx][ny] = true;

						total += map[nx][ny];

						usedList.add(new int[] { nx, ny });

					}

					if (flag) {
						dfs(i + 1, sum + total);
					}

					for (int k = 0; k < usedList.size(); k++) {
						int used[] = usedList.get(k);
						visited[used[0]][used[1]] = false;
					}
					usedList.clear();
				}
				visited[x][y] = false;
			}

		}

	}
}
