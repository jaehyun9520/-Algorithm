package a0916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11660 {

	static int n,m;
	static int psum[][];
	static int array[][];
	public static void main(String[] args) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine());
		StringBuilder sb=new StringBuilder();
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		psum=new int[n+1][n+1];
		array=new int[n+1][n+1];
		
		for(int i=1; i<=n; i++)
		{
			st=new StringTokenizer(in.readLine());
			for(int j=1; j<=n; j++)
			{
				array[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		prefixSum();
		
		for(int i=1; i<=m; i++)
		{
			st=new StringTokenizer(in.readLine());
			int x1,y1,x2,y2;
			
			x1=Integer.parseInt(st.nextToken());
			y1=Integer.parseInt(st.nextToken());
			x2=Integer.parseInt(st.nextToken());
			y2=Integer.parseInt(st.nextToken());
			
			sb.append(gridSum(x1,y1,x2,y2)+"\n");
		}

		System.out.println(sb.toString());

	}
	
	
	static int gridSum(int x1,int y1, int x2, int y2)
	{
		int result=psum[x2][y2];
		
		result-=psum[x1-1][y2];
		result-=psum[x2][y1-1];
		result+=psum[x1-1][y1-1];
		
		return result;
	}
	
	
	static void prefixSum() {
		
		for(int i=1; i<=n; i++)
		{
			for(int j=1; j<=n; j++)
			{
				psum[i][j]=psum[i-1][j]+psum[i][j-1]-psum[i-1][j-1]+array[i][j];
			}
		}
		
	}
}
