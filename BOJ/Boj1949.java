import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj1949 {

	static int n;
	static ArrayList<Integer> node[];
	static int population[];
	static ArrayList<Integer> child[];
	static int dp[][]; // 0은 해당 마을이 우수마을일때, 1은 해당마을이 우수마을이 아닐때 현재까지의 최대주민수
	static boolean visited[];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());
		population = new int[n + 1];
		node = new ArrayList[n + 1];
		child = new ArrayList[n + 1];
		visited = new boolean[n + 1];
		dp = new int[n + 1][3];
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			node[i] = new ArrayList<>();
			child[i] = new ArrayList<>();
			population[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			node[a].add(b);
			node[b].add(a);

		}

		visited[1] = true;
		dfs(1);

		System.out.println(Integer.max(dp[1][0], dp[1][1]));
	}

	static void dfs(int now) {

		for (int i = 0; i < node[now].size(); i++) {
			int next = node[now].get(i);

			if (!visited[next]) {

				child[now].add(next);

				visited[next] = true;

				dfs(next);

				dp[now][0] += Integer.max(dp[next][1], dp[next][2]); // 우수마을일때는 자식노드들은 다 우수마을이 아니여야한다

				dp[now][2] += dp[next][1];

			}

		}

		int sum = 0;

		for (int i = 0; i < child[now].size(); i++) {
			int select = child[now].get(i);
			int initVal = dp[select][0]; // 무조건 자식노드들 중 하나는 우수마을이여야 한다

			for (int j = 0; j < child[now].size(); j++) {
				int next = child[now].get(j);
				if (j != i)
					initVal += Integer.max(dp[next][0], dp[next][1]);
			}

			sum = Integer.max(sum, initVal);
		}

		dp[now][1] += sum;

		dp[now][0] += population[now];

	}
}
