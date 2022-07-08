package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10835_카드게임 {

	public static void main(String[] args) throws IOException {
	
		
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int ans=0;
		int n=Integer.parseInt(in.readLine());
		int dp[][]=new int[n+1][n+1];
		int left[]=new int[n+1];
		int right[]=new int[n+1];
		boolean visited[][]=new boolean[n+1][n+1];
		
		visited[1][1]=true;
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		for(int i=1; i<=n; i++)
		{
			left[i]=Integer.parseInt(st.nextToken());
		}
		
	 st=new StringTokenizer(in.readLine()," ");
		for(int i=1; i<=n; i++)
		{
			right[i]=Integer.parseInt(st.nextToken());
		}
		
		
		for(int i=1; i<=n; i++)
		{
			for(int j=1; j<=n; j++)
			{
				if(visited[i-1][j])
				{
					dp[i][j]=Integer.max(dp[i-1][j],dp[i][j]);
				    visited[i][j]=true;
				}
				if(visited[i-1][j-1])
				{
					dp[i][j]=Integer.max(dp[i-1][j-1],dp[i][j]);
				    visited[i][j]=true;
				}
				
				if(visited[i][j-1]&&left[i]>right[j-1])
				{
					dp[i][j]=Integer.max(dp[i][j],dp[i][j-1]+right[j-1]);
				    visited[i][j]=true;
				}
				
		
				if(i==n||j==n)
				{
					if(visited[i][j])
					{
					ans=Integer.max(ans,dp[i][j]);
					
				
						if(left[i]>right[j])
						{
							ans=Integer.max(ans,dp[i][j]+right[j]);
						}
					}
					
				}
				
			}
		}
		
		System.out.println(ans);
		
	}
}
