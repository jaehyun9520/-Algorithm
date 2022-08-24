package a0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj15681_트리와쿼리 {

	static int dp[];
	static ArrayList<Integer> node[];
	static int n, r, q;
	static boolean visited[];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb=new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

		visited = new boolean[n + 1];
		node = new ArrayList[n + 1];
		dp = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			node[i] = new ArrayList<>();
		}

		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			node[a].add(b);
			node[b].add(a);
		}

		visited[r] = true;
		dfs(r);

		
		for(int i=1; i<=q; i++)
		{
			int queryNum=Integer.parseInt(in.readLine());
			
			sb.append(dp[queryNum]+"\n");
		}
		
		System.out.println(sb.toString());
	}

	static int dfs(int now) {

		for (int i = 0; i < node[now].size(); i++) {
			int next = node[now].get(i);

			if (!visited[next]) {
				visited[next] = true;

				dp[now] += dfs(next);
			}

		}
		
		return ++dp[now];
	}
}
