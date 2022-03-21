package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj24230 {

	static int n, ans = 0;
	static ArrayList<Integer> node[];
	static int color[];
	static boolean visited[];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());
		color = new int[n + 1];
		node = new ArrayList[n + 1];
		visited = new boolean[n + 1];

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			color[i] = Integer.parseInt(st.nextToken());
			node[i] = new ArrayList<>();
		}

		for (int i = 1; i < n; i++) {
			int n1, n2;
			st = new StringTokenizer(in.readLine(), " ");
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());

			node[n1].add(n2);
			node[n2].add(n1);
		}

		int val = 0;
		visited[1] = true;
		if (color[1] != 0) {
			ans++;
			val = color[1];
		}

		dfs(1, val);

		System.out.println(ans);
	}

	static void dfs(int num, int col) {

		for (int i = 0; i < node[num].size(); i++) {
			int next = node[num].get(i);

			if (visited[next] == false) {
				visited[next] = true;

				if (color[next] != col) {
					ans++;
					dfs(next, color[next]);
				}

				else {
					dfs(next, col);
				}

			}

		}

	}
}
