package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2225_합분해 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		int n=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		
		int dp[][]=new int[k+1][n+1];
		
		for(int i=0; i<=n; i++)
		{
			dp[1][i]=1;
		}
		
		
		
		for(int i=2; i<=k; i++)
		{
			for(int j=0; j<=n; j++)
			{
				
				for(int z=0; z<=j; z++)
				{
					dp[i][j]+=dp[i-1][j-z];
					
					dp[i][j]%=1000000000;
				}
				
				
			}
		}

		System.out.println(dp[k][n]);
	}
}
