package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2234 {

	static int N,M;
	static int map[][];
	static int check[][];
	static int roomnumber[]=new int[2501];
	
	static int dx[]= {0,-1,0,1};
	static int dy[]= {-1,0,1,0};
	//서쪽 1, 북쪽 2 동쪽 4 남쪽 8
	
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in)); 
		 StringTokenizer st=new StringTokenizer(in.readLine()," ");	
		 
		 M=Integer.parseInt(st.nextToken()); //열의 길이
		 N=Integer.parseInt(st.nextToken()); //행의 길이 	 
		 map=new int[N+1][M+1];  
		 check=new int[N+1][M+1];
		 Queue <State1> Q=new LinkedList<State1>();
		 for(int i=1; i<=N; i++)
		 {
			 st=new StringTokenizer(in.readLine()," ");	
			 for(int j=1; j<=M; j++)
			 {
				 map[i][j]=Integer.parseInt(st.nextToken());
			 }
		 }
		 int room=0; //방의 개수
		 int max=0; // 가장 넓은 방의 넓이 
		 for(int i=1; i<=N; i++)
		 {
			 for(int j=1; j<=M; j++)
			 {	 
				 if(check[i][j]==0)
				 {
					 int cnt=1; //방의 넓이 1로 시작 
					 room++; //방 하나 추가 	 
					 check[i][j]=room; //방 번호 부여 
					 Q.offer(new State1(i,j));
					 while(!Q.isEmpty())
					 {
						 State1 now=Q.poll();
						 int x=now.r; int y=now.c;
						 for(int k=0; k<=3; k++)
						 {
							 if((map[x][y]&1<<k)==0)  // 해당 방향에 벽이 없는지 확인 
							 {
								 int nx=x+dx[k];
								 int ny=y+dy[k];								 
								  if(check[nx][ny]==0)
								  {
									  cnt++;
									  check[nx][ny]=room;	  
									  Q.offer(new State1(nx,ny));
								  }				 
							 }
	 
						 }			 
					 }
					 roomnumber[room]=cnt;  // 각 방의 넓이 저장 
				 max=Math.max(max,cnt); //가장 넓은 방의 넓이 
				 }
				 
				 
			 }
		 }
		 // 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기 구하기 
		 int ans=0; 
		 for(int i=1; i<=N; i++)
		 {
			 for(int j=1; j<=M; j++)
			 {
				 for(int k=0; k<=3; k++)
				 {
					 int nx=i+dx[k];
					 int ny=j+dy[k];
					 
					 if(nx>=1&&nx<=N&&ny>=1&&ny<=M)
					 {
						 if(check[i][j]!=check[nx][ny]) //둘이 다른방인데 벽을 사이에 두고 붙어있다 
						 {
                                ans=Math.max(ans,roomnumber[check[i][j]]+roomnumber[check[nx][ny]]);
							 	 
						 }
						 
					 }
				 }
			 }
		 }

		 System.out.println(room);
		 System.out.println(max);
		 System.out.println(ans);
	}
}

class State1{
	
	int r, c;
	State1(int r, int c)
	{
		this.r=r; this.c=c;
	}
	
}
