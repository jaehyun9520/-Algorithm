package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj20208_진우의민트초코우유 {

	static int n, m, h, sx, sy, ans = 0;
	static ArrayList<int[]> list = new ArrayList<>();
	static boolean visited[];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");

			for (int j = 1; j <= n; j++) {
				int val = Integer.parseInt(st.nextToken());

				if (val == 1) {
					sx = i;
					sy = j;
				}

				else if (val == 2) {
					list.add(new int[] { i, j });
				}
			}
		}

		visited = new boolean[list.size()];

		permutation(sx, sy, m, 0); // 현재 좌표, 현재 체력, 현재까지 마신 민트초코의 개수
		System.out.println(ans);

	}

	static void permutation(int x, int y, int hp, int cnt) {

		if (Math.abs(x - sx) + Math.abs(y - sy) <= hp) {
			ans = Math.max(ans, cnt);
		}

		for (int i = 0; i < list.size(); i++) {

			if (visited[i] == false) {

				int[] next = list.get(i);
				int nx = next[0];
				int ny = next[1];
				int distance = Math.abs(x - nx) + Math.abs(y - ny);

				if (distance <= hp) // 현재 위치에서 갈 수 있는 위치라면?
				{
					visited[i] = true;
					permutation(nx, ny, hp - distance + h, cnt + 1);
					visited[i] = false;
				}

			}

		}

	}
}
