package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1439 {

	public static void main(String[] args) throws IOException {
		
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		
		String input=in.readLine();
		
		int ans[]=new int[2]; // 0  과 1 의  개수 
		
		int pre=input.charAt(0)-48;
		
		for(int i=1; i<input.length(); i++)
		{
			int val=input.charAt(i)-48;

			
			if(pre!=val)
			{
				
				
				ans[pre]++;
				pre=val;
			}
			
			if(i==input.length()-1)
			{
				ans[val]++;
			}
			
		}

		
		
		
		
		
		
		
		System.out.println(Integer.min(ans[0],ans[1]));
		
	}
}
