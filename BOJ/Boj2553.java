import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj2553 {

	static int dp[][]; // 0은 해당 노드가 얼리어답터일때
	static int n;
	static ArrayList<Integer> node[];
	static boolean visited[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		
		n=Integer.parseInt(in.readLine());
		dp=new int[n+1][2];
		
		node=new ArrayList[n+1];
		visited=new boolean[n+1];
		
		for(int i=1; i<=n; i++)
		{
			node[i]=new ArrayList<>();
		}
		
		
		for(int i=1; i<n; i++)
		{
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			node[a].add(b);
			node[b].add(a);
			
		}
		visited[1]=true;
		dfs(1);
		
		
		System.out.println(Integer.min(dp[1][0],dp[1][1]));
		
	}
	
	static void dfs(int num)
	{

		for(int i=0; i<node[num].size(); i++)
		{
			int next=node[num].get(i);
			
			if(!visited[next]) 
			{
				visited[next]=true;
				
				dfs(next);
				
				//현재 정점이 리프노드일때 처리
				
				dp[num][0]+=Integer.min(dp[next][0],dp[next][1]); 
				dp[num][1]+=dp[next][0];
			}
		}
		
		dp[num][0]++;
	
		
		
	}
}
