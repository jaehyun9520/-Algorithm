package study57;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1717 {

	static int[] parent;

	public static void main(String[] args) throws IOException {

		int n, m;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		StringBuilder sb=new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		parent = new int[n + 1];

		// parent배열 초기화
		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}
		
		for(int i=1; i<=m; i++)
		{
			 st = new StringTokenizer(in.readLine(), " ");
			int type=Integer.parseInt(st.nextToken());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			if(type==0) {
				union(a,b);
			}
			else {
				int p1=find(a);
				int p2=find(b);
				
				if(p1==p2) {
					sb.append("YES\n");
				}
				else {
					sb.append("NO\n");
				}
			}
			
		}
		
		System.out.println(sb.toString());

	}

	static int find(int x) {

		if (parent[x] == x) {
			return x;
		} else {
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
}
