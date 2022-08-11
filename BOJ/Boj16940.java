package study50;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16940_BFS스페셜저지 {

	static int parent[];
	static int children[];
	static ArrayList<Integer> node[]; 
	static int n;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());

		node = new ArrayList[n + 1];
		children = new int[n + 1];
		parent = new int[n + 1];

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

		bfs();

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int ans = 0;
		int order[] = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			order[i] = Integer.parseInt(st.nextToken());

		}

		if (order[1] == 1) {
			int index = 2;
			Queue<Integer> q = new LinkedList<>();

			q.add(1);

			loop: while (!q.isEmpty()) {
				int now = q.poll();

				for (int i = 1; i <= children[now]; i++) {
					if (parent[order[index]] == now) {
						q.add(order[index]);
						index++;
					} else {
						break loop;
					}

				}

			}
			
			if(index==n+1)
				ans=1;

		}
System.out.println(ans);
	}

	static void bfs() {

		Queue<Integer> q = new LinkedList<>();

		boolean visited[] = new boolean[n + 1];
		visited[1] = true;

		q.add(1);

		while (!q.isEmpty()) {
			int now = q.poll();

			for (int i = 0; i < node[now].size(); i++) {
				int next = node[now].get(i);

				if (visited[next] == false) {

					children[now]++;
					parent[next] = now;

					visited[next] = true;
					q.add(next);
				}

			}

		}

	}
}
