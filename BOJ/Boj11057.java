package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj11057_오르막수 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(in.readLine());
		
		int dp[][]=new int[n+1][10];
		
		
		
		for(int i=1; i<=n; i++)
		{
			for(int j=0; j<=9; j++)
			{
			if(i==1) dp[i][j]=1;
			
			
			else {
				
				for(int k=0; k<=j; k++)
				{
					dp[i][j]=(dp[i][j]+dp[i-1][k])%10007;
				}
			}
			}
		}
		
		int ans=0;
		for(int i=0; i<=9; i++)
		{
			ans= (ans+dp[n][i])%10007;
			
		}
		System.out.println(ans);
		
	}
}
