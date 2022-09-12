package study54;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj18500 {

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };

	static int r, c;
	static int map[][];
	static int visited[][];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new int[r + 1][c + 1];

		for (int i = 1; i <= r; i++) {
			String input = in.readLine();
			for (int j = 1; j <= c; j++) {
				map[i][j] = input.charAt(j - 1);
			}
		}

		int cnt = Integer.parseInt(in.readLine());

		st = new StringTokenizer(in.readLine(), " ");

		for (int i = 1; i <= cnt; i++) {
			int height = r + 1 - Integer.parseInt(st.nextToken());
			int dir = i % 2; // 홀수면 왼쪽, 짝수면 오른쪽
			int loc[] = throwing(dir, height);

			if (loc[0] != 0) // 부숴진 미네랄이 있다면? 공중에 떠 있는 클러스터가 있는지 확인
			{
				visited = new int[r + 1][c + 1];
				for (int j = 0; j <= 3; j++) {

					int nx=loc[0]+dx[j];
					int ny=loc[1]+dy[j];
					
					if(nx<1||nx>r||ny<1||ny>c||nx==r) continue;
					
					List<int[]> cluster = clusterCheck(nx, ny, j + 1);

					if (cluster != null) // 공중에 떠있는 클러스터가 있다면?
					{
						clusterDown(cluster);
						break;
					}

				}

			}

		}
		
		for(int i=1; i<=r; i++)
		{
			for(int j=1; j<=c; j++)
			{
				System.out.print((char)map[i][j]);
			}
			System.out.println();
		}
		

	}

	static void clusterDown(List<int[]> cluster) {

		
		for (int i = 0; i < cluster.size(); i++) {
			int x = cluster.get(i)[0];
			int y = cluster.get(i)[1];
			
			map[x][y]='.';
		}
		
		int cnt = 0;
		loop: for (int i = 1; i <= 100; i++) {
			for (int j = 0; j < cluster.size(); j++) {
				int x=cluster.get(j)[0];
				int y=cluster.get(j)[1];
				if (map[x + i][y] == 'x' ) {
					cnt = i-1;
					break loop;
				}
				else if(x+i == r) {
					cnt = i;
					break loop;
				}
			}

		}
		
		for (int i = 0; i < cluster.size(); i++) {
			int x = cluster.get(i)[0];
			int y = cluster.get(i)[1];
			
			map[x+cnt][y]='x';
		}

	}

	static List<int[]> clusterCheck(int x, int y, int color) {
		List<int[]> cluster = new ArrayList<>();

		if (visited[x][y] == 0&&map[x][y]=='x') {
			cluster.add(new int[] { x, y });
			visited[x][y] = color;

			Queue<int[]> q = new LinkedList<>();

			q.add(new int[] { x, y });

			while (!q.isEmpty()) {
				int now[] = q.poll();

				for (int i = 0; i <= 3; i++) {
					int nx = now[0] + dx[i];
					int ny = now[1] + dy[i];

					if (nx < 1 || nx > r || ny < 1 || ny > c || visited[nx][ny] == color)
						continue;

					if (visited[nx][ny] == 0&&map[nx][ny]=='x') {

						if (nx == r) // 땅과 닿아있는 클러스터라면?
						{
							return null;
						}

						visited[nx][ny] = color;
						int loc[] = new int[] { nx, ny };
						cluster.add(loc);
						q.add(loc);
					}

					// 다른 클러스터와 연결되어있다면?
					else if (visited[nx][ny] != color&&map[nx][ny]=='x') {
						return null;
					}

				}

			}
			return cluster;

		}
		return null;

	}

	static int[] throwing(int dir, int height) {
		int loc[] = { 0, 0 };

		if (dir == 1) // 왼쪽
		{
			for (int i = 1; i <= c; i++) {
				if (map[height][i] == 'x') {
					map[height][i] = '.';
					loc[0] = height;
					loc[1] = i;
					break;
				}
			}
		}

		else {
			for (int i = c; i >= 1; i--) {
				if (map[height][i] == 'x') {
					map[height][i] = '.';
					loc[0] = height;
					loc[1] = i;
					break;
				}
			}
		}

		return loc;

	}
}
