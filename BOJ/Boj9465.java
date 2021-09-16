package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj9465 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb= new StringBuilder();
		int T= Integer.parseInt(in.readLine()); //테스트케이스 개수 
		
		int sticker[][];
		int DP[][];
		for(int t=1; t<=T; t++)
		{
			int N= Integer.parseInt(in.readLine()); // 열의 개수 
			
			sticker=new int[3][N+1];
			DP=new int[3][N+1];
			
			for(int i=1; i<=2; i++)
			{
				st=new StringTokenizer(in.readLine()," ");
				
				for(int j=1; j<=N; j++)
				{
					sticker[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			
			
			for(int i=1; i<=N; i++) // 1열 ~ N열
			{
				for(int j=1; j<=2; j++) //1행 2행
				{
					if(j==1)
					DP[j][i]=sticker[j][i]+Math.max(DP[2][i-1], DP[1][i-1]-sticker[1][i-1]);
					
					else
					{
						DP[j][i]=sticker[j][i]+Math.max(DP[1][i-1], DP[2][i-1]-sticker[2][i-1]);
					}
				}
			}
			
			
			sb.append(Math.max(DP[1][N],DP[2][N])).append("\n");
			
			
			
			
		}
		
		System.out.println(sb.toString());
		
		
	}
}
