package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj20164 {

	static int ans=0,max=-1,min=214700000;

	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in)); 
		
		String input=in.readLine(); 
		recursive(input, 0);//  입력값으로 들어온 문자열 ,만들수있는 개수 
		System.out.print(min+" ");
		System.out.println(max);
	}

	static void recursive(String s, int count)  
	{

		for(int i=0; i<s.length(); i++) //수의 각 자리 숫자 중에서 홀수의 개수를 종이에 적는다.
		{
			int number=s.charAt(i)-48;  
			if(number%2==1)
			{
				count++;
			}
			
		}
		if(s.length()==1) // 한자리수이면 아무것도 하지못하고 종료한다.
		{
			max=Math.max(max, count);
			min=Math.min(min, count);	
		}	
		else if(s.length()==2) //수가 두자리이면 2개로 나눠서 합을 구하여 새로운 수로 생각한다.  
		{
			int tmp=0;
			for(int i=0; i<s.length(); i++)
			{
				tmp+=(s.charAt(i)-48);	
			}		
			recursive(Integer.toString(tmp),count);
		}
		
		else  // 3개라면  임의의 위치에서 끊어서 3개의 수로 분할 
		{	
			for(int i=0; i<s.length()-2; i++)   
			{
				for(int j=i+1; j<s.length()-1; j++)
				{
					int num1=0; int num2=0; int num3=0;
					for(int k=0; k<=i; k++) //첫번째 수 
					{
						num1*=10;
						num1+=(s.charAt(k)-48);
					}
					for(int k=i+1; k<=j; k++)  //두번째 수 
					{
						num2*=10;
						num2+=(s.charAt(k)-48);
					}
					
					for(int k=j+1; k<s.length(); k++) //세번째 수 
					{
						num3*=10;
						num3+=(s.charAt(k)-48);
					}
					recursive(Integer.toString(num1+num2+num3),count);
					
				}
			}
		
		}
	}

}
