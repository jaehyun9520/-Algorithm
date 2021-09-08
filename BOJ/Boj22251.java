package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj22251_빌런호석 {
	static int N,K,P,ans=0;
	static String X;
	static int find[]=new int[1000001];
	static int newlist[];
	static int nowlist[];
	static int count[][]=new int[10][10]; 
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb=new StringBuilder();
	    StringTokenizer st=new StringTokenizer(in.readLine()," ");  
	    N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		P=Integer.parseInt(st.nextToken());
		X= st.nextToken();
		newlist=new int[K];  
		nowlist=new int[K];
		int tmp=K-X.length();
		for(int i=0; i<X.length(); i++)  
		{
			nowlist[i+tmp]=X.charAt(i)-48;	
		}
		int number[][]=   // 0번~9번 LED등의 위치
			   {{1,1,1,1,1,1,0},
				{0,0,0,0,1,1,0},
				{1,0,1,1,0,1,1},
				{1,0,0,1,1,1,1},
				{0,1,0,0,1,1,1},
				{1,1,0,1,1,0,1},
				{1,1,1,1,1,0,1},
				{0,0,0,1,1,1,0},
				{1,1,1,1,1,1,1},
				{1,1,0,1,1,1,1}
		                 };
		for(int i=0; i<=9; i++)  //i번 번호가 j번 번호가 되기위해서 몇개의 LED등을 반전시켜야하는지 확인 
		{
			for(int j=i+1; j<=9; j++)
			{
			   int cnt=0;
				for(int k=0; k<=6; k++)
				{
					
					if(number[i][k]!=number[j][k])
					{
						cnt++;
					}
					
				}
				count[i][j]=cnt;  
				count[j][i]=cnt;	
			}
		}
		combination(0,0,P);
		System.out.println(ans);
	}
	static void combination(int index, int cnt, int p)
	{
		if(p!=P&&cnt==K)  
		{
			int number=0;
			for(int i=0; i<K; i++)
			{
				number*=10;
				number+=newlist[i];
			}
			find[number]=1;
			if(number<=N&&number!=0)
			{
			
				ans++;
			}
		}
		
	else if(cnt!=K) {  
		int now=nowlist[index];
			for(int i=0; i<=9; i++)
			{
		          if(p>=count[now][i])
		          {
		        	  newlist[cnt]=i;
		        	  combination(index+1,cnt+1,p-count[now][i]);	        	  
		          }
				
			}

	}

	}
}
