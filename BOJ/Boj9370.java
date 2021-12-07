package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj9370_미확인도착지 {

	static class Node implements Comparable<Node> {

		int number, cost, use; // use 0이면 사용x 1이면 사용O

		Node(int number, int cost, int use) {
			this.number = number;
			this.cost = cost;
			this.use = use;
		}

		@Override
		public int compareTo(Node o) {

			return this.cost - o.cost;
		}

	}

	static int n, m, t, s, g, h; // s는 시작지점
	static ArrayList<Node> list[];
	static int candidate[];
	static int cost[];
	static ArrayList<Integer> ans;
	static int check[][];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int test = 1; test <= T; test++) {

			st = new StringTokenizer(in.readLine(), " ");

			n = Integer.parseInt(st.nextToken());

			list = new ArrayList[n + 1];
			cost = new int[n + 1];
			check = new int[2][n + 1];
			for (int i = 1; i <= n; i++) {
				list[i] = new ArrayList<>();
				cost[i] = Integer.MAX_VALUE;
			}

			m = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(in.readLine(), " ");
			s = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			for (int i = 1; i <= m; i++) {
				int a, b, cost;
				st = new StringTokenizer(in.readLine(), " ");
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());
				list[a].add(new Node(b, cost, 0));
				list[b].add(new Node(a, cost, 0));

			}

			candidate = new int[n + 1];

			for (int i = 1; i <= t; i++) {
				int number = Integer.parseInt(in.readLine());
				candidate[number] = 1; // 후보 교차로를 표시

			}
			ans = new ArrayList<>();

			dijkstra();

			Collections.sort(ans);

			for (int i = 0; i < ans.size(); i++) {
				sb.append(ans.get(i) + " ");
			}
			sb.append("\n");

		}
		System.out.println(sb.toString());

	}

	static void dijkstra() {
		cost[s] = 0;

		PriorityQueue<Node> PQ = new PriorityQueue<>();

		PQ.add(new Node(s, 0, 0));

		while (!PQ.isEmpty()) {
			Node now = PQ.poll();

			if (cost[now.number] == now.cost) {
				if (now.use == 1 && check[1][now.number] == 0) {
					check[1][now.number] = 1;
				}

				else if (now.use == 0 && check[0][now.number] == 0) {
					check[0][now.number] = 1;
				}

				else
					continue;

				if (candidate[now.number] == 1 && now.use == 1) {
					ans.add(now.number); // 추가
				}

				for (int i = 0; i < list[now.number].size(); i++) {
					Node next = list[now.number].get(i);

					if (cost[next.number] > now.cost + next.cost)

					{
						cost[next.number] = now.cost + next.cost;

						if ((g == next.number && h == now.number) || (h == next.number && g == now.number)) {
							if (check[1][next.number] == 0)
								PQ.add(new Node(next.number, now.cost + next.cost, 1));
						} else {
							if (check[0][next.number] == 0)
								PQ.add(new Node(next.number, now.cost + next.cost, now.use));
						}

					}

					else if (cost[next.number] == now.cost + next.cost) {

						if (now.use == 1
								|| ((g == next.number && h == now.number) || (h == next.number && g == now.number))) {
							if (check[1][next.number] == 0)
								PQ.add(new Node(next.number, now.cost + next.cost, 1));
						}

					}

				}

			}

		}

	}

}
