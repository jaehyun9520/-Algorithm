package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj1799_비숍 {

	static boolean visited[][];
	static ArrayList<int[]> list[];
	static int map[][];
	static int n;
	static int ans[];
	static int dx[] = { 1, -1, 1, -1 };
	static int dy[] = { 1, -1, -1, 1 };
	static int count=0;
	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		list = new ArrayList[2]; // 0 1 두개의 구역

		n = Integer.parseInt(in.readLine());

		map = new int[n + 1][n + 1];
		visited = new boolean[n + 1][n + 1];
		
		ans = new int[2];
		int flag1 = 0, flag2 = 1;

			for(int i=0; i<=1; i++)
			{
				list[i]=new ArrayList<>();
			}
		
		for (int i = 1; i <= n; i++) {
			flag1++;
			flag2++;

			if (flag1 == 2)
				flag1 = 0;
			if (flag2 == 2)
				flag2 = 0;

			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= n; j++) {
				int val = Integer.parseInt(st.nextToken());

				map[i][j] = val;

				if (val == 1) // 비숍을 놓을 수 있는 곳이라면?
				{
					if (j % 2 == 1) // 홀수번째라면 해당 구역에 추가
					{
						list[flag1].add(new int[] { i, j });
					}

					else {
						list[flag2].add(new int[] { i, j });
					}
				}

			}
		}

		dfs(0, 0, 0); // 0번째 구역 비숍 놓기 시작
		dfs(1, 0, 0); // 1번째 구역 비숍 놓기 시작
		
		System.out.println(ans[0]+ans[1]);
		System.out.println(count);
	}

	static void dfs(int group, int now, int cnt) // 그룹 번호 , 현재 리스트 진행 번호 , 몇개의 비숍을 놓았는지?
	{

		
		if (cnt > ans[group]) {
			ans[group] = cnt;
		}

		for (int i = now; i < list[group].size(); i++) {
			int x = list[group].get(i)[0];
			int y = list[group].get(i)[1];

				
			if (check(x, y)) {

				count++;
				visited[x][y]=true;
				dfs(group,i+1,cnt+1);
				visited[x][y]=false;
			}

		}

	}

	static boolean check(int x, int y) {

		for (int i = 0; i <= 3; i++) {
			int tx = x;
			int ty = y;
			boolean flag = true;

			while (true) {
				tx = tx + dx[i];
				ty = ty + dy[i];

				if (tx < 1 || tx > n || ty < 1 || ty > n) {
					break;
				}

				if (visited[tx][ty] == true) {
					flag = false;
					break;
				}

			}

			if (!flag)
				return false;

		}
		return true;

	}
}
