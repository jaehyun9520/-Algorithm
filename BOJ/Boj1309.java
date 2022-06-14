package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1309_동물원 {

public static void main(String[] args) throws NumberFormatException, IOException {
	
	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
	
	int n=Integer.parseInt(in.readLine());
	
	int dp[][]=new int[n+1][3]; //왼쪽에 배치 , 오른쪽 배치 , 배치 x
	
	dp[1][0]=1; dp[1][1]=1; dp[1][2]=1; 
	
	for(int i=2; i<=n; i++)
	{
		dp[i][0]=(dp[i-1][1]+dp[i-1][2])%9901;
		dp[i][1]=(dp[i-1][0]+dp[i-1][2])%9901;
		dp[i][2]=(dp[i-1][0]+dp[i-1][1]+dp[i-1][2])%9901;
		
	}
	
	int ans=(dp[n][0]+dp[n][1]+dp[n][2])%9901;
	
	System.out.println(ans);
	
	
}
}
