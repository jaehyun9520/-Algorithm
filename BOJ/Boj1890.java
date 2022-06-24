package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1890_점프 {

	public static void main(String[] args) throws IOException {
		int n;
		int map[][];
		long dp[][];

		BufferedReader in = new BufferedReader(new InputStreamReader((System.in)));

		n = Integer.parseInt(in.readLine());
		map = new int[n + 1][n + 1];
		dp = new long[n + 1][n + 1];
		dp[1][1]=1;
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				int val=map[i][j];			
				if(i+val<=n&&val!=0)
				{
					
					dp[i+val][j]+=dp[i][j];
				
				}
				if(j+val<=n&&val!=0)
				{
				
					dp[i][j+val]+=dp[i][j];
					
				}
			}
		}
		System.out.println(dp[n][n]);
		

	}
}
