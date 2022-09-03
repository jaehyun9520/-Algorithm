import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj20304 {

	static int n, m;
	static boolean visited[];
	static Queue<int[]> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());

		visited = new boolean[n + 1];
		m = Integer.parseInt(in.readLine());

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= m; i++) {
			int password = Integer.parseInt(st.nextToken());
			visited[password] = true;
			q.add(new int[] { password, 0 });
		}

		System.out.println(bfs());

	}

	static int bfs() {
		int ans = 0;

		while (!q.isEmpty()) {
			int now[] = q.poll();
			int number = now[0];
			int cnt = now[1];

			ans = Integer.max(ans, cnt);

			for (int i = 0; i <= 19; i++) {
				int val = (1 << i);
				int next = 0;
				if ((number & val) == val) // 해당 비트가 1이라면? 0으로 바꾼다
				{
					next = (number & (~val));
				} else { // 해당 비트가 0이면이라면 1로 바꾼다
					next = number | val;
				}

				if (next <= n&&visited[next] == false ) {
					visited[next] = true;
					q.add(new int[] { next, cnt + 1 });
				}

			}

		}
		return ans;

	}
}
