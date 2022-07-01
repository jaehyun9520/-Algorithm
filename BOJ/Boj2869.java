package study44;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2869_달팽이는올라가고싶다 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		
		long a,b,v,ans=0;
		
		a=Long.parseLong(st.nextToken());
		b=Long.parseLong(st.nextToken());
		v=Long.parseLong(st.nextToken());
		
		v-=b;
		long val=a-b;
		if(v%val==0)
		{
			ans=v/val;
		}
		else {
			ans=v/val+1;
		}
		
		System.out.println(ans);
	}
}
