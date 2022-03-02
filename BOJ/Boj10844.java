package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj10844 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(in.readLine());
		
		
		int dp[][]=new int[n+1][10];
		
		for(int i=1; i<=9; i++)
		{
			dp[1][i]=1;
		}
		
		for(int i=2; i<=n; i++)
		{
			
			for(int j=0; j<=9; j++)
			{
				
				if(j>0&&j<9)
				{
					dp[i][j]=(dp[i-1][j-1]+dp[i-1][j+1])%1000000000;
				}
				
				else if(j==0)
				{
					dp[i][j]=dp[i-1][1];
				}
				else if(j==9)
				{
					dp[i][j]=dp[i-1][8];
				}
			}
			
			
		}
	int ans=0;	
		for(int i=0; i<=9; i++)
		{
			ans+=dp[n][i];
			
			ans=ans%1000000000;
					
		}
		
		System.out.println(ans);
		
	}
}
