package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj6087_레이저통신 {

	static class State implements Comparable<State> {
		
		int x, y,  dir, cost;
		
		State(int x, int y, int dir, int cost)
		{
			this.x=x;
			this.y=y;
			this.dir=dir;
			this.cost=cost;
		}

		@Override
		public int compareTo(State o) {  //오름차순 정렬 (작은게 먼저 나오게 )
			return this.cost-o.cost;
		}

	}
	
	
			// 상 우 하 좌 
	static int dx[]= {-1,0,1,0};
	static int dy[]= {0,1,0,-1};
	static int dc[]= {0,1,-1};
	static int map[][];
	static int W,H;
	static int ans=0;
	static int sx=0, sy=0;
	static int cost[][];
	public static void main(String[] args) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		
		W=Integer.parseInt(st.nextToken());
		H=Integer.parseInt(st.nextToken());
		
		cost=new int[H+1][W+1];
		map=new int[H+1][W+1];
		boolean flag=false;
		for(int i=1; i<=H; i++)
		{
			
			String input=in.readLine();
			
			
			for(int j=1; j<=W; j++)
			{

				cost[i][j]=-1;
				map[i][j]=input.charAt(j-1);
				
				if(!flag&&map[i][j]=='C')
				{
					flag=true;
					sx=i; sy=j;
				}
			}
		}
		
	
		
		dijkstra();
		
		System.out.println(ans);
		
		
		
	}
	
	
	static void dijkstra()
	{
		cost[sx][sy]=0;
	
	
		PriorityQueue<State> PQ=new PriorityQueue<>();		
		
		for(int i=0; i<=3; i++)
		{
			int nx=sx+dx[i];
			int ny=sy+dy[i];
			
			if(nx<1||nx>H||ny<1||ny>W||map[nx][ny]=='*') continue; 
			
			
			cost[nx][ny]=0;
			PQ.add(new State(nx,ny,i,0));
		}
		
		
		while(!PQ.isEmpty())
		{
			State now=PQ.poll();
			
			if(cost[now.x][now.y]==-1||cost[now.x][now.y]==now.cost)
			{
				cost[now.x][now.y]=now.cost;
				
				if(!(now.x==sx&&now.y==sy)&&map[now.x][now.y]=='C')
				{
					
					ans=now.cost;
					break;
				}
				int dir=now.dir;
				for(int i=0; i<=2; i++)
				{
					
					int next=dir+dc[i];
					
					if(next==-1) next=3;
					else if(next==4) next=0;
					
					int nx=now.x+dx[next];
					int ny=now.y+dy[next];
					
					//막혀있거나 맵을 벗어나는 경우 생략 
					if(nx<1||nx>H||ny<1||ny>W||map[nx][ny]=='*') continue;
					
					
					if(i!=0&&(cost[nx][ny]==-1||cost[nx][ny]==now.cost+1))
					{
						
						
						
						PQ.add(new State(nx,ny,next,now.cost+1));
					}
					
					else if(i==0&&(cost[nx][ny]==-1||cost[nx][ny]==now.cost)){
						
					
					
						PQ.add(new State(nx,ny,dir,now.cost));
					}
					
					
					
					
				}
				
				
				
			}
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
	}
	
	
}
