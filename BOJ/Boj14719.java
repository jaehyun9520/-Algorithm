package study62;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14719 {

	static int h,w;
	static int map[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		h=Integer.parseInt(st.nextToken());
		w=Integer.parseInt(st.nextToken());
		
		map=new int[w+1];
		
		st=new StringTokenizer(in.readLine()," ");
		for(int i=1; i<=w; i++) {
			map[i]=Integer.parseInt(st.nextToken());
		}
		
		System.out.println(solution());
		
	}
	
	static int solution() {
		int sum=0;
		
		for(int i=2; i<w; i++) {
			
			int left=0;
			int right=0;
			for(int j=i-1; j>=1; j--) {
				
				left=Integer.max(left,map[j]);
			}
			
			for(int j=i+1; j<=w; j++) {
				right=Integer.max(right,map[j]);
			}
			
			
			int min=Integer.min(left,right);
			
			if(min>map[i]) {
				sum+=(min-map[i]);
			}
			
		}
		
		
		return sum;
	}
}
