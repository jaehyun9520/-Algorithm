package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5913_준규와사과 {

	static int ans=0;
	static int map[][]=new int[6][6]; // 0 은 사과 존재 ,  -1은 거대한 돌 or 빈 곳     3= 준규 현재 위치   4=해빈 현재 위치 
	static int dx[]= {1,-1,0,0};
	static int dy[]= {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		int k=Integer.parseInt(in.readLine());
		
	
		for(int i=1; i<=k; i++)
		{
			st =new StringTokenizer(in.readLine()," ");
			
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			
			map[x][y]=-1 ;
		}
		map[1][1]=3;
		map[5][5]=4;
		
		dfs(3,1,1,5,5,23-k);  // 현재 누구 차례인지(3은 준규 ,  4는 해빈 )  현재 차례인 사람의 좌표, 다음 사람의 좌표, 지금까지 남은 사과 개수 
		System.out.println(ans);
	}
	
	static void dfs(int now, int nowX, int nowY, int nextX, int nextY, int cnt)
	{
		int next=now+1;
		if(next==5) next=3;
		
		for(int i=0; i<=3; i++)
		{
			int nx=nowX+dx[i];
			int ny=nowY+dy[i];
			
			
			if(nx<1||nx>5||ny<1||ny>5) continue;
			
			
			
			if(map[nx][ny]==0) //시과가 존재한다면??
			{
				map[nowX][nowY]=-1;
				map[nx][ny]=now;
				
			
				
				dfs(next,nextX,nextY,nx,ny,cnt-1);
				
				map[nowX][nowY]=now;
				map[nx][ny]=0;
				
			}
			
			
			else if(cnt==0&&map[nx][ny]==next)
			{
				ans++;
			}
			
			
			
			
		}
		
		
		
		
	}
	
}
