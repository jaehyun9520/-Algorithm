package study57;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1774 {

	static class Edge implements Comparable<Edge> {

		int num1, num2;
		double length;

		Edge(int num1, int num2, double length) {
			this.num1 = num1;
			this.num2 = num2;
			this.length = length;
		}

		@Override
		public int compareTo(Edge o) {

			if (this.length < o.length)
				return -1;
			else
				return 1;
		}
	}

	static int n, m; // 우주신들의 개수 ,이미 연결된 통로의 개수
	static int parent[];
	static int xy[][]; // 좌표
	static double ans;
	static int connect=0;
	static int find(int x) {

		if (parent[x] == 0)
			return x;

		else {
			return parent[x] = find(parent[x]);
		}

	}

	static void union(int x, int y) {

		int p1 = find(x);
		int p2 = find(y);

		if (p1 != p2) {
			
			parent[p1] = p2;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		parent = new int[n + 1];
		xy = new int[n + 1][2];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			xy[i][0] = Integer.parseInt(st.nextToken());
			xy[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			int p1=find(a);
			int p2=find(b);
			if(p1!=p2) {
				connect++;
				union(a, b);
			}
			
		}

		kruskal();
		System.out.printf("%.2f",Math.round(ans*100.0)/100.0);
	}

	static void kruskal() {

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		for (int i = 1; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				long x=Math.abs(xy[i][0] - xy[j][0]);
				long y=Math.abs(xy[i][1] - xy[j][1]);
				double val = x*x+y*y;
				val = Math.sqrt(val);

				pq.add(new Edge(i, j, val));

			}

		}
		int cnt = 0;

		while (cnt < (n - 1 - connect)) {

			Edge now = pq.poll();
			int p1 = find(now.num1);
			int p2 = find(now.num2);
		
			if (p1 != p2) {
				cnt++;
				ans += now.length;

				union(p1, p2);

			}

		}

	}
}
