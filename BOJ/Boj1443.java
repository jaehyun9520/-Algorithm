package study50;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1443_망가진계산기 {

	static int d,p,ans=-1;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		d=Integer.parseInt(st.nextToken());
		p=Integer.parseInt(st.nextToken());
		
		dfs(9,1,0);
		System.out.println(ans);
	}
	
	
	static void dfs(int index, int val, int cnt)
	{
		String length=val+"";
		
		if(length.length()>d)
		{
			return;
		}
		
		else if(cnt==p)
		{
			ans=Integer.max(ans,val);
		}
		
		
		else {
			
			for(int i=index; i>=2; i--)
			{
				dfs(i,val*i,cnt+1);
			}
			
			
			
		}
		
	}
}
