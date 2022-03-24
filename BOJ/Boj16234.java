package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16234_인구이동 {

	public static void main(String[] args) throws IOException {

		int n, L, R, ans = 0;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		int map[][] = new int[n + 1][n + 1];
		int dx[] = { 1, -1, 0, 0 };
		int dy[] = { 0, 0, 1, -1 };

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Queue<int[]> q = new LinkedList<>();
		ArrayList<int[]> union = new ArrayList<>();
		boolean visited[][];
		boolean flag = true;
		while (flag) // 인구이동 과정
		{	
			visited = new boolean[n + 1][n + 1];
			flag = false;
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (visited[i][j] == false) {
						union.clear();
						int sum = map[i][j];
						visited[i][j] = true;
						union.add(new int[] { i, j });
						q.add(new int[] { i, j });

						while (!q.isEmpty()) {
							int[] now = q.poll();
							int x = now[0];
							int y = now[1];
							for (int k = 0; k <= 3; k++) {
								int nx = x + dx[k];
								int ny = y + dy[k];

								if (nx < 1 || nx > n || ny < 1 || ny > n || visited[nx][ny] == true)
									continue;

								int val = Math.abs(map[x][y] - map[nx][ny]);
								if (L <= val && val <= R) {
									visited[nx][ny] = true;
									sum += map[nx][ny];
									union.add(new int[] { nx, ny });
									q.add(new int[] {nx,ny});
								}
							}
						}

						if (union.size() >= 2) {
							flag = true;
							int val = sum / union.size();
							for (int k = 0; k < union.size(); k++) {
								int now[] = union.get(k);

								map[now[0]][now[1]] = val;
							}
						}

					}
				}
			}

			if (flag)
				ans++;

		}
		
		System.out.println(ans);

	}
}
