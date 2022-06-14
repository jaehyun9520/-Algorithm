package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15649_n과m1 {

	static int n,m;
	static int used[];
	static int list[];
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws IOException {
		
	BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st= new StringTokenizer(in.readLine());
		
	n=Integer.parseInt(st.nextToken());
	m=Integer.parseInt(st.nextToken());
	
	used=new int[n+1];
	list=new int[m+1];
	
	p(1);
	System.out.println(sb.toString());
	}
	
	static void p(int cnt)
	{
		if(cnt==(m+1))
		{
			for(int i=1; i<=m; i++)
			{
				sb.append(list[i]+" ");
			}
			sb.append("\n");
			
			return;
		}
		
		for(int i=1; i<=n; i++)
		{
			if(used[i]==0)
			{
				used[i]=1;
				list[cnt]=i;
				p(cnt+1);
				used[i]=0;
			}
			
			
		}
		
		
		
		
		
	}
	
}
