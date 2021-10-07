package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1932 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n=Integer.parseInt(in.readLine());
		
		int value[][]=new int[n+1][n+1];
		int dp[][]=new int[n+1][n+1];
		
		for(int i=1; i<=n; i++)
		{
			
			st=new StringTokenizer(in.readLine()," ");
			
			for(int j=1; j<=i; j++)
			{
				value[i][j]=Integer.parseInt(st.nextToken());
			}
			
		}
		dp[1][1]=value[1][1];
		
		for(int i=1; i<=n-1; i++)
		{
			for(int j=1; j<=i; j++)
			{
				dp[i+1][j]=Integer.max(dp[i+1][j],dp[i][j]+value[i+1][j] );
				dp[i+1][j+1]=Integer.max(dp[i+1][j+1],dp[i][j]+value[i+1][j+1]);
				
			}
			
		}
		int max=0;
		for(int i=1; i<=n; i++)
		{
			
			max=Integer.max(dp[n][i],max);
		}
		System.out.println(max);
		
	}
}
