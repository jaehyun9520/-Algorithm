package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj14938 {

	static class Node implements Comparable<Node> {

		int number, val;

		Node(int number, int val) {
			this.number = number;
			this.val = val;
		}

		@Override
		public int compareTo(Node o) {

			return this.val - o.val;
		}
	}

	static int n, m, r; // 정점개수, 수색범위,간선 개수
	static ArrayList<Node> list[];
	static int distance[];
	static int item[];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		list = new ArrayList[n + 1];
		distance = new int[n + 1];
		item = new int[n + 1];

		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
			distance[i] = Integer.MAX_VALUE;

			item[i] = Integer.parseInt(st.nextToken());

		}

		for (int i = 1; i <= r; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a, b, val;

			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			val = Integer.parseInt(st.nextToken());

			list[a].add(new Node(b, val));
			list[b].add(new Node(a, val));

		}

		int ans = 0;
		for (int i = 1; i <= n; i++) {
			ans = Integer.max(ans, Dijkstra(i));

		}

		System.out.println(ans);
	}

	static int Dijkstra(int start) {

		int sum = 0;
		for (int i = 1; i <= n; i++) {
			distance[i] = Integer.MAX_VALUE;
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();

		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {

			Node now = pq.poll();

			int number = now.number;
			int val = now.val;

			if (distance[number] > val && val <= m) {
				distance[number] = val;
				sum += item[number];
				for (int i = 0; i < list[number].size(); i++) {
					Node next = list[number].get(i);
					pq.add(new Node(next.number, next.val + val));
				}
			}
		}
		return sum;

	}
}
