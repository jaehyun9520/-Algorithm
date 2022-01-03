package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2252 {

	
	static int student[];
	static ArrayList<Integer> list[];
	static int n,m;

	static StringBuilder ans=new StringBuilder();
	public static void main(String[] args) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
	
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		student=new int[n+1];
		
		list=new ArrayList[n+1];
		
		for(int i=1; i<=n; i++)
		{
			list[i]=new ArrayList<Integer>();
		}
		
		
	
		for(int i=1; i<=m; i++)
		{
			int a, b;
			st=new StringTokenizer(in.readLine()," ");
			
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			student[b]++;
			
			list[a].add(b);

		}
	
		
		sort();
System.out.println(ans.toString());
	}
	
	
	static void sort()
	{
		
	Queue<Integer> Q=new LinkedList<>();
		
	for(int i=1; i<=n; i++)
	{
		if(student[i]==0)
		{
			Q.add(i);
		}
		
	}
	
	
	while(!Q.isEmpty())
	{
		int now=Q.poll();
		
		
		ans.append(now+" ");
		
		
		for(int i=0; i<list[now].size(); i++)
		{
			int next=list[now].get(i);
			
			student[next]--;
			
			if(student[next]==0)
			{
				Q.add(next);
			}
			
			
		}
		
		
		
		
	}
	
	
	
	}
}
