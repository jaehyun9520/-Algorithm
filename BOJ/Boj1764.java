package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj1764 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		ArrayList<String> list=new ArrayList<>();
		StringBuilder sb=new StringBuilder();
		
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		
		Set<String> set=new HashSet<>();		
		
		for(int i=1; i<=n; i++)
		{
			String input=in.readLine();
			
			set.add(input);
			
		}
		
		
		for(int i=1; i<=m; i++)
		{
			String input=in.readLine();
			
			if(set.contains(input))
			{
				list.add(input);
			}
			
			
		}
		
		Collections.sort(list);
		
		sb.append(list.size()+"\n");
		for(int i=0; i<list.size(); i++)
		{
		sb.append(list.get(i)+"\n");	
			
		}
		
		System.out.println(sb.toString());
		
	}
	
}
