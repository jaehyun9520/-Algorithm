package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj21278 {

	static int N,M;
	static List<Integer> node[];
	static int length[][];
	static int ans1=0,ans2=0;  
	static int list[]=new int[2];
	static int min=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
			
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		length=new int[N+1][N+1];
	     node=new ArrayList[N+1];
		for(int i=1; i<=N; i++)
		{
			node[i]=new ArrayList<>();
		}
			
		
		for(int i=1; i<=M; i++)
		{
			st=new StringTokenizer(in.readLine()," ");
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			node[a].add(b);
			node[b].add(a);	
		}
		
		for(int i=1; i<=N ; i++)  // 각 정점에서 다른 정점까지 걸리는 시간 
		{
			int check[]=new int[N+1];
			check[i]=1;
			int[] a= {i,0};
			Queue <int[]> Q= new LinkedList<>();
			
			Q.offer(a);
			while(!Q.isEmpty())
			{
				int[] now=Q.poll();
			for(int j=0; j<node[now[0]].size(); j++)
			{
				int next=node[now[0]].get(j);
				if(check[next]==0)
				{
					check[next]=1;
					length[i][next]=(now[1]+1)*2;
					int [] c= {next, now[1]+1};
					Q.offer(c);	
				}
			}

			}
			
		}
		simul(0,1); // N개의 건물중에서 2개의 건물 뽑기 	
		System.out.println(ans1+" "+ans2+" "+min);
	}
	static void simul(int cnt, int index) 
	{
		if(cnt==2)
		{
			int sum=0;

			
			for(int i=1; i<=N; i++)
			{
				if(i!=list[0]&&i!=list[1])  // 치킨집으로 뽑힌 두개의 건물을 제외한 다른건물들이
				{                           // 가까운 치킨집까지 왕복하는 최단시간 
					sum+= Math.min(length[list[0]][i],length[list[1]][i]);
				}
			}
			if(sum<min)  
			{
				min=sum;	
				ans1=list[0];
				ans2=list[1];
			}
			else if(sum==min)
			{
				if(ans1>list[0])
				{
					ans1=list[0];
					ans2=list[1];
				}
				
				else if(ans1==list[0])
				{
					if(ans2>list[1])
					{
						ans2=list[1];
					}
				}
			}
		}
		else {
			
			for(int i=index; i<=N; i++)
			{
				list[cnt]=i;
				simul(cnt+1,i+1);

			}
		}	
	}	
}
