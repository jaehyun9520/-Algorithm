package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1520 {

	static int n, m;
	static int[][] map;
	static int[][] dp;

	static int[] dx= {1,-1,0,0};
	static int[] dy= {0,0,1,-1};
	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n + 1][m + 1];
		dp = new int[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {

			st = new StringTokenizer(in.readLine(), " ");

			for (int j = 1; j <= m; j++) {

				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1; // 한번도 방문하지 않은 상태
			}
		}
		
		dp[1][1]=0;
		
		dfs(1,1);
		

		
		System.out.println(dp[1][1]);
	}
	
	static int dfs(int x, int y) {
		
		
		if(x==n&&y==m) return dp[x][y]=1;
		
		for(int i=0; i<=3; i++) {
			
			int nx=x+dx[i];
			int ny=y+dy[i];
			
			if(nx<1||nx>n||ny<1||ny>m) continue;
			
			if(map[x][y]>map[nx][ny]) {
						
				if(dp[nx][ny]==-1) {
					
					dp[nx][ny]=0;
					
					dp[x][y]+=dfs(nx,ny);
				}
				else {
					dp[x][y]+=dp[nx][ny];
				}
				
			}
			
			
			
		}
		
		return dp[x][y];
	} 
}
