package a0804;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj5582_공통부분문자열 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		
		char a[]=in.readLine().toCharArray();
		char b[]=in.readLine().toCharArray();
		
		int dp[][]=new int[a.length][b.length];
		int ans=0;
		for(int i=0; i<a.length; i++)
		{
			for(int j=0; j<b.length; j++)
			{
				
				if(a[i]==b[j])
				{
					if(i-1>=0&&j-1>=0&&dp[i-1][j-1]!=0)
					{
						dp[i][j]=1+dp[i-1][j-1];
					}
					else {
						dp[i][j]=1;
					}
					
					ans=Integer.max(ans,dp[i][j]);
					
				}
				
				
			}
		}
		
		System.out.println(ans);
		
	}
}
