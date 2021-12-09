package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj1525_퍼즐 {

	
	static int dx[]= {1,-1,0,0};
	static int dy[]= {0,0,-1,1};
	static int[] ansArr= {1,2,3,4,5,6,7,8,0};
	static int[][] array;
	static Set<Integer> check;
	static int ans=-1;
	
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		check=new HashSet<>();
		array=new int[3][3];
		
		for(int i=0; i<3; i++)
		{
			st=new StringTokenizer(in.readLine()," ");
			for(int j=0; j<3; j++)
			{
				array[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
		
		System.out.println(ans);
	}
	
	static void bfs()
	{
		Queue<puzzle> Q=new LinkedList<>();
		
		valueCheck(array);
		
		
		Q.add(new puzzle(array,0));
		
		
		while(!Q.isEmpty())
		{
			puzzle now=Q.poll();
			
			int x=0,y=0;
			boolean flag=true;
			int cnt=0;
			for(int i=0; i<3; i++)
			{
				for(int j=0; j<3; j++)
				{
					if(ansArr[cnt]!=now.state[i][j])
					{
						flag=false;
					}
					cnt++;
					
					if(now.state[i][j]==0)
					{
						x=i; y=j;
						
					}
				}
			}
			
			
			if(flag)  // 정렬이 된 상태라면 종료 
			{
				ans=now.cnt;
				break;
			}
			
			for(int i=0; i<=3; i++)
			{
			
				int nx=x+dx[i];
				int ny=y+dy[i];
				
				
				if(nx<0||nx>=3||ny<0||ny>=3) continue;
				
				
				
				
				now.state[x][y]=now.state[nx][ny];
				now.state[nx][ny]=0;
				
				if(valueCheck(now.state))
				{
					int[][] copy=new int[3][3];
					
					for(int j=0; j<=2; j++)
					{
						for(int k=0; k<=2; k++)
						{
							copy[j][k]=now.state[j][k];
						}
					}
					
					
					Q.add(new puzzle(copy,now.cnt+1));
					
					
					
				}
				
				
				now.state[nx][ny]=now.state[x][y];
				now.state[x][y]=0;
				
				
			}
			
				
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
	}
	
	
	
	static boolean valueCheck(int[][] array)
	{
		int number=0;
		int cnt=0;
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
			{
				cnt++;
				
				number=number+array[i][j]*(int)Math.pow(10,cnt);
			}
		
		
		if(check.contains(number))  
		{
			return false;
		}
		
		else {
			check.add(number);
			return true;
			
		}
		
		
		
		
	}
	
	
	
	static class puzzle {
		
		int[][] state; int cnt;
		
		puzzle(int[][] state,int cnt)
		{
			this.state=state;
			this.cnt=cnt;
		}
		
	}
	
}
