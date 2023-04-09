package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1240 {

	static int n, m;
	static ArrayList<int[]> node[];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		StringBuilder sb=new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		node = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) {
			node[i] = new ArrayList<>();
		}

		for (int i = 1; i < n; i++) {

			int a, b, length;

			st = new StringTokenizer(in.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			length = Integer.parseInt(st.nextToken());

			node[a].add(new int[] { b, length });
			node[b].add(new int[] { a, length });

		}

		for (int i = 1; i <= m; i++) {

			int n1, n2;

			st = new StringTokenizer(in.readLine(), " ");

			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());

			sb.append(solution(n1, n2)+"\n");

		}
		
		System.out.println(sb.toString());

	}

	static int solution(int n1, int n2) {

		int ans=0;
		boolean[] visited = new boolean[n + 1];

		visited[n1] = true;

		Queue<int[]> q = new LinkedList<>();

		q.add(new int[] { n1, 0 });

		while (!q.isEmpty()) {

			int[] now = q.poll();

			for (int i = 0; i < node[now[0]].size(); i++) {

				int next = node[now[0]].get(i)[0];
				int length = node[now[0]].get(i)[1];

				if (!visited[next]) {
					visited[next] = true;

					if (next == n2) {
						ans= length + now[1];
						break;
					}

					q.add(new int[] { next, length + now[1] });

				}

			}

		}
		return ans;

	}
}
