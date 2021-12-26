package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1915 {

	static int array[][];
	static int n,m;
	static int ans=0;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");	
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		array=new int[n+1][m+1];
	
		for(int i=1; i<=n; i++)
		{
			String input=in.readLine();
			
			for(int j=1; j<=m; j++)
			{
				array[i][j]=input.charAt(j-1)-48;
			
				
				if(array[i][j]==1) ans=1;
			}
		}
		
		
		for(int i=2; i<=n; i++)
		{
			for(int j=2; j<=m; j++)
			{
				
				if(array[i][j]==1)
				{

					int value=Integer.min(array[i-1][j-1],Integer.min(array[i][j-1],array[i-1][j]));
					array[i][j]=value+1;
					ans=Integer.max(ans,array[i][j]*array[i][j]);
						
					
					
				}
			}
		}
		
		System.out.println(ans);
		
		
		
		
		
		
		
		
	}


}
