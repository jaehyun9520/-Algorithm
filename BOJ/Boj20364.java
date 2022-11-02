package study62;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj20364 {

static boolean visited[];
static int n,q;	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		StringBuilder sb=new StringBuilder();
		n=Integer.parseInt(st.nextToken());
		q=Integer.parseInt(st.nextToken());
		
		visited=new boolean[n+1];
		
		for(int i=1; i<=q; i++) {
			
			int number=Integer.parseInt(in.readLine());
			int val=number;
			int first=0;
			
			while(val!=1) {
				
				if(visited[val]) {
					first=val;
				}
				
				val/=2;
			}
			
			if(first==0) {
				visited[number]=true;
				
			}
			sb.append(first+"\n");
			
		}
		
		System.out.println(sb.toString());
		
		
	}
}
