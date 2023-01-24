package 알고리즘스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2412 {

	static int n, t;
	static Map<String, Integer> visited = new HashMap<>();

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");

			String x = st.nextToken();
			String y = st.nextToken();

			visited.put("x=" + x + "y=" + y, 0);

		}

		System.out.println(solution());

	}

	static int solution() {

		int ans = -1;
		Queue<int[]> q = new LinkedList<>();

		q.add(new int[] { 0, 0, 0 });

		while (!q.isEmpty()) {

			int[] now = q.poll();

			int x = now[0];
			int y = now[1];
			int cnt = now[2];

			System.out.println("x=" + x + " y=" + y + " cnt=" + cnt);

			if (y == t) {
				ans = cnt;
				break;
			}
			for (int i = -2; i <= 2; i++) {

				for (int j = -2; j <= 2; j++) {

					int nx = x + i;
					int ny = y + j;

					// 범위를 벗어난 경우
					if (nx > 1000000 || ny > t)
						continue;

					// 해당 위치에 홈이 파져있는지 확인
					String loc = "x=" + nx + "y=" + ny;

					if (visited.containsKey(loc)) {

						// 홈이 파져있다면 이미 방문된 홈인지?
						int val = visited.get(loc);

						// 한번도 방문된 적이 없다면?
						if (val == 0) {

							visited.replace(loc, 1);
							q.add(new int[] { nx, ny, cnt + 1 });

						}

					}

				}

			}

		}

		return ans;
	}

}
