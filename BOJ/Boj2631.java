package study58;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Boj2631 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(in.readLine());
		
		int dp[]=new int[n+1];
		int array[]=new int[n+1];
		
		for(int i=1; i<=n; i++)
		{
			array[i]=Integer.parseInt(in.readLine());
		}
		int ans=0;
		
		for(int i=1; i<=n; i++) {
			
			int cnt=0;
			
			for(int j=i-1; j>=1; j--)
			{
				if(array[j]<array[i])
				cnt=Integer.max(cnt,dp[j]);
			}
			
			dp[i]=cnt+1;
			
			ans=Integer.max(dp[i],ans);
		}
		
		System.out.println(n-ans);
		
		
		
	}
}
