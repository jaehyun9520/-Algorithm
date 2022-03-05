package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14267 {

	
	public static void main(String[] args) throws IOException {
		
		int n,m;
		int input[];
		int dp[];
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine(), " ");
		StringBuilder sb=new StringBuilder();
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		
		input=new int[n+1];
		dp=new int[n+1];
		
		
		st=new StringTokenizer(in.readLine(), " ");
		for(int i=1; i<=n; i++)
		{
			input[i]=Integer.parseInt(st.nextToken());
		}
		
		
		for(int i=1; i<=m; i++)
		{
			st=new StringTokenizer(in.readLine(), " ");
			
			int number=Integer.parseInt(st.nextToken());
			int value=Integer.parseInt(st.nextToken());
			dp[number]+=value;
		}
		
		
		
		for(int i=2; i<=n; i++)
		{
			
			dp[i]=dp[input[i]]+dp[i];
		}
		
		
		for(int i=1; i<=n; i++)
		{
			sb.append(dp[i]+" ");
		}
		
		
		System.out.println(sb.toString());
		
	}
}
