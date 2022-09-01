package study53;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

public class Boj13701_중복제거 {

	public static void main(String[] args) throws IOException {
		
		BitSet b=new BitSet();
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		StringBuilder sb=new StringBuilder();

		while(st.hasMoreTokens())
		{
			int val=Integer.parseInt(st.nextToken());
			
			if(!b.get(val))
			{
				b.set(val);
				sb.append(val+" ");
			}
			
		}
		
		System.out.println(sb.toString());
		
		
		
		
	}
}
