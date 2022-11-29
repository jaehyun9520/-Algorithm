package study65;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj16202 {

	static int n, m, k; // 정점, 간선, 턴의 개수
	static int parent[];
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static List<Edge> list = new ArrayList<>();

	static class Edge implements Comparable<Edge> {

		int node1, node2, weight;

		Edge(int node1, int node2, int weight) {

			this.node1 = node1;
			this.node2 = node2;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		parent = new int[n + 1];

		for (int i = 1; i <= m; i++) {

			st = new StringTokenizer(in.readLine(), " ");

			int node1, node2;

			node1 = Integer.parseInt(st.nextToken());
			node2 = Integer.parseInt(st.nextToken());

			pq.add(new Edge(node1, node2, i));

		}
int pre=-1,val=0;
		// k번 진행
		for (int i = 1; i <= k; i++) {

			parentInit();

			if(pre!=0) {
			val=mstFind();
			pre=val;
			}
			System.out.print(val+" ");
			
		}

	}

	static int mstFind() {

		int cnt = 0;
		int sum = 0;

		
		
		
		while (!pq.isEmpty()) {

			Edge now = pq.poll();

			int n1 = now.node1;
			int n2 = now.node2;
			int w = now.weight;

			// 같은 집합에 속해있지 않다면?
			if (find(n1) != find(n2)) {

				if (cnt != 0) {
					list.add(now);
				}
				
				union(n1, n2);
				sum += w;
				cnt++;

				

				// 최소스패닝 트리가 완성되었다면 break;
				if (cnt == (n - 1)) {
					break;
				}

			}
			
			else {
				list.add(now);
			}

		}
		
		for(int i=0; i<list.size(); i++) {
			pq.add(list.get(i));
		}
		list.clear();
		
		
		if(cnt==(n-1))  return sum;
		
		else return 0;
		
		
		

	}

	static int find(int a) {
		
		
		if (parent[a] == a) {
			return a;
		}

		else
			return parent[a] = find(parent[a]);

	}

	static void union(int a, int b) {

		int p1 = find(a);
		int p2 = find(b);

		if (p1 != p2) {

			parent[p1] = p2;
		}
	}

	static void parentInit() {

		for (int i = 1; i <= n; i++) {

			parent[i] = i;
		}

	}
}
