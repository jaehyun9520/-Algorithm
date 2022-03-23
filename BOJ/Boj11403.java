package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11403 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n=Integer.parseInt(in.readLine());
		int weight[][]=new int[n+1][n+1];
		
		
		for(int i=1; i<=n; i++)
		{
			st=new StringTokenizer(in.readLine()," ");
			for(int j=1; j<=n; j++)
			{
				weight[i][j]=Integer.parseInt(st.nextToken());
				if(weight[i][j]==0) weight[i][j]=10000;
			}
		}
		
		
		for(int k=1; k<=n; k++)
		{
			for(int i=1; i<=n; i++)
			{
				for(int j=1; j<=n; j++)
				{
					weight[i][j]=Integer.min(weight[i][j],weight[i][k]+weight[k][j]);
				}
			}
		}
		
		for(int i=1; i<=n; i++)
		{
			for(int j=1; j<=n; j++)
			{
				if(weight[i][j]==10000)
					weight[i][j]=0;
				
				else weight[i][j]=1;
				System.out.print(weight[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}
