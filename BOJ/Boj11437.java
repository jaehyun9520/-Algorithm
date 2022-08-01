package study49;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj11437_LCA {

	static int n;
	static int parent[][];
	static int depth[];
	static ArrayList<Integer> list[];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(in.readLine());

		StringTokenizer st;

		parent = new int[n + 1][16];
		depth = new int[n + 1];
		list = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 1; i <= n - 1; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list[a].add(b);
			list[b].add(a);

		}

		depth[1] = 1;
		dfs(1);

		for (int i = 1; i <= 15; i++) {
			for (int j = 1; j <= n; j++) {
				if (parent[j][i - 1] != 0) {
					parent[j][i] = parent[parent[j][i - 1]][i - 1];

				}

			}

		}

		int m = Integer.parseInt(in.readLine());

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(in.readLine(), " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int tmp = 0;

			if (depth[a] < depth[b]) {

				tmp = a;
				a = b;
				b = tmp;
			}

			int diff = depth[a] - depth[b];

			int count = 0;

			while (diff != 0) {
				int val = diff % 2;

				if (val == 1) {
					a = parent[a][count];
				}

				diff = diff / 2;

				count++;

			}

			if (a != b) {

				for (int k = 15; k >= 0; k--) {
					if (parent[a][k] != 0 && parent[a][k] != parent[b][k]) {
						a = parent[a][k];
						b = parent[b][k];
					}

				}

				a = parent[a][0];

			}

			sb.append(a + "\n");

		}

		System.out.println(sb.toString());

	}

	static void dfs(int num) {

		for (int i = 0; i < list[num].size(); i++) {
			int next = list[num].get(i);

			if (depth[next] == 0) {
				depth[next] = depth[num] + 1;

				parent[next][0] = num;

				dfs(next);
			}
		}

	}
}
