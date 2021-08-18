package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj20166 {

	static int dx[]= {-1,1,0,0,-1,-1,1,1};  //상하좌우 대각선 방향 
	static int dy[]= {0,0,-1,1,-1,1,-1,1};
	static char map[][];
	static int N,M,K;
	static Map <String,Integer> hm=new HashMap<String,Integer>();
	static String [] list;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder sb=new StringBuilder();	
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		map=new char[N+1][M+1];
		list=new String[K+1]; 
		for(int i=1; i<=N; i++)
		{
			String input=in.readLine();
			for(int j=1; j<=M; j++)
			{
				map[i][j]=input.charAt(j-1);
	
			}
		}
		for(int i=1; i<=K; i++)  //신이 좋아하는 문자열 Map에 저장 
		{		
			String input=in.readLine();
			list[i]=input;
		    hm.put(input,0);  
		}
	    for(int i=1; i<=N; i++)
		{
			for(int j=1; j<=M; j++)
			{
				String s="";
				s+=map[i][j];  
				DFS(i,j,1,s); //좌표, 이동횟수 (최대 5회) , 현재까지 만들어진 문자열	
			}
		}
		for(int i=1; i<=K; i++)
		{
			sb.append(hm.get(list[i])).append("\n");
		}	
		System.out.println(sb.toString());
	}
	static void DFS(int r, int c, int cnt, String s)
	{	
		if(hm.containsKey(s))  // 현재까지 만들어진 문자열이 신이 좋아하는문자열인지 확인 
		{
			hm.put(s, hm.get(s)+1); // 좋아하는 문자열이라면 경우의 수에 추가 
		}	
		if(cnt<5)
		{
			for(int i=0; i<=7; i++)
			{
				int nr=r+dx[i];
				int nc=c+dy[i];			
				if(nr==N+1)  nr=1;   //환형 처리 
				if(nr==0) nr=N;
				if(nc==0) nc=M;
				if(nc==M+1)  nc=1;	
				DFS(nr, nc, cnt+1, s+map[nr][nc]);  // 좌표, 이동횟수, 현재까지 만들어진 문자열 
			}
		}
	}
}
