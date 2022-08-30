package study52;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj15900_나무탈출 {

	static ArrayList<Integer> node[];
	static boolean visited[];
	static int sum = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());
		node = new ArrayList[n + 1];
		visited = new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			node[i] = new ArrayList<>();
		}

		for (int i = 1; i < n; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			node[a].add(b);
			node[b].add(a);
		}
		visited[1] = true;
		dfs(1, 0);
		if (sum % 2 == 1) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}

	static void dfs(int num, int depth) {
		int cnt = 0;

		for (int i = 0; i < node[num].size(); i++) {
			int next = node[num].get(i);

			if (!visited[next]) {
				cnt++;
				visited[next] = true;
				dfs(next, depth + 1);
			}

		}
		if (cnt == 0) {
			sum += depth;
		}

	}
}
