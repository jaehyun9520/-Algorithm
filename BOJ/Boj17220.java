package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17220 {

	static int n, m; // 정점과 간선의 수

	public static void main(String[] args) throws IOException {

		int check[] = new int[27];
		int visited[][] = new int[27][2]; 
		ArrayList<Integer> list[] = new ArrayList[27];
		Queue<Integer> q = new LinkedList<>();

		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 1; i <= m; i++) {
			int node1, node2;
			st = new StringTokenizer(in.readLine(), " ");
			node1 = st.nextToken().charAt(0) - 64;
			node2 = st.nextToken().charAt(0) - 64;

			visited[node2][0]++; // 나한테 마약을 공급해주는 루트가 몇개인지 카운트
			list[node1].add(node2);
		}

		st = new StringTokenizer(in.readLine(), " ");
		int cnt = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= cnt; i++) {
			int val = st.nextToken().charAt(0) - 64;

			check[val] = 1;

			q.add(val);

			while (!q.isEmpty()) {
				int now = q.poll();

				for (int j = 0; j < list[now].size(); j++) {
					int next = list[now].get(j);

					visited[next][1]++;

					if (visited[next][1] == visited[next][0]) {
						check[next] = 1;
						q.add(next);
					}

				}

			}

		}

		int ans = 0;

		for (int i = 1; i <= n; i++) {
			if (check[i] == 0&&visited[i][0]!=0) {


				ans++;
			}
		}



		System.out.println(ans);

	}
}
