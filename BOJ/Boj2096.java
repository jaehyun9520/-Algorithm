package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2096_내려가기 {

	public static void main(String[] args) throws IOException {

		int input[][];
		int dp1[][];
		int dp2[][];
		int n;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());

		input = new int[n + 1][4];
		dp1 = new int[2][4];
		dp2 = new int[2][4];

		StringTokenizer st;

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= 3; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		dp1[1][1]=dp2[1][1]=input[1][1];
		dp1[1][2]=dp2[1][2]=input[1][2];
		dp1[1][3]=dp2[1][3]=input[1][3];
		

		
		for(int i=2; i<=n; i++)
		{
			for(int j=1; j<=3; j++)
			{
				if(j==1)
				{
				dp1[i%2][j]=Integer.max(dp1[(i-1)%2][j]+input[i][j],dp1[(i-1)%2][j+1]+input[i][j]);
				dp2[i%2][j]=Integer.min(dp2[(i-1)%2][j]+input[i][j],dp2[(i-1)%2][j+1]+input[i][j]);
				
				}
				
				else if(j==2)
				{
					dp1[i%2][j]=Integer.max(dp1[(i-1)%2][j-1]+input[i][j],Integer.max(dp1[(i-1)%2][j]+input[i][j],dp1[(i-1)%2][j+1]+input[i][j]));
					dp2[i%2][j]=Integer.min(dp2[(i-1)%2][j-1]+input[i][j],Integer.min(dp2[(i-1)%2][j]+input[i][j],dp2[(i-1)%2][j+1]+input[i][j]));
				}
				
				else if(j==3)
				{
					dp1[i%2][j]=Integer.max(dp1[(i-1)%2][j]+input[i][j],dp1[(i-1)%2][j-1]+input[i][j]);
					dp2[i%2][j]=Integer.min(dp2[(i-1)%2][j]+input[i][j],dp2[(i-1)%2][j-1]+input[i][j]);
					
				}
				
			}
			
			
		}
		int ans1=Integer.max(dp1[n%2][1],Integer.max(dp1[n%2][2],dp1[n%2][3]));
		int ans2=Integer.min(dp2[n%2][1],Integer.min(dp2[n%2][2],dp2[n%2][3]));
		System.out.println(ans1+" "+ans2);
		
		
		
	}
}
