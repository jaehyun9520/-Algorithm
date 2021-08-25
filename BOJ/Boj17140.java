package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj17140 {

	static class State implements Comparable<State>{
		
		int count, number;
		
		State(int count, int number)
		{
			this.count=count; this.number=number;
		}

		@Override
		public int compareTo(State o) {  //등장횟수 오름차순 정렬 
		                                 //등장횟수가 같다면  수가 커지는 순으로 오름차순 정렬 
			if(this.count>o.count)
			{
				return 1;
			}
			
			
			else if(this.count==o.count)
			{
				if(this.number>o.number)
				{
					return 1;
				}
				
				else {
					return -1;
				}
				
			}
	
			return -1;
		}


		@Override
		public boolean equals(Object obj) {
			
			State other = (State) obj;
			
			if(this.number==other.number)
			{
				return true;
			}
		   
			return false;
		}
	}

	static int R,C,K,ANS=0;
	static int Rlength=3, Clength=3;
	static int array[][]=new int[101][101];
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
	    
		R=Integer.parseInt(st.nextToken());  //array[R][C]가 K의 값을 가지고 있는지?
		C=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		
		for(int i=1; i<=3; i++)  //3*3배열로 시작 
		{
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=1; j<=3; j++)
			{
				array[i][j]=Integer.parseInt(st.nextToken());
			}
			
			
		}
		
		if(array[R][C]==K)  //A[r][c]에 k가 들어있는지?(기본 입력상태를 확인)
		{
			System.out.println(0);
		}
		
		else {
			int second=1;
			while(second<=100) // 1초~100초 
			{
				List <Integer> length=new ArrayList<>();
				if(Rlength>=Clength) //R연산 수행 
				{
					for(int i=1; i<=Rlength; i++)
					{
						//1행부터 정렬연산 
						
						List <State> list=new ArrayList<>();
						
						for(int j=1; j<=Clength; j++)
						{
							int now=array[i][j];
							
							if(now==0) continue; // 숫자가 0이면 생략 
							
							State s=new State(1,now);
							int index=list.indexOf(s); //now값을 가진 숫자가 이미 리스트에 들어있는가?
							if(index==-1) //처음이라면 리스트에 추가 
							{
								list.add(s);
							}
							else {  // 이미 있다면 개수만 +1
								list.get(index).count++; 
							}
							
							
							
						}
						Collections.sort(list);  //수의 등장횟수가 커지는순으로  등장횟수가 같다면 수가 커지는 순으로 정렬 
                           Clength=Math.max(list.size()*2,Clength);  //열의 길이가 가장 큰 열을 기준으로 변한다 
                           length.add(list.size()*2);

                           //정렬 결과를 배열에 넣는다.
					      for(int j=1; j<=list.size()*2&&j<=100; j+=2) // 길이가 100이 넘어갈 경우 처음 100개를 제외한 나머지는 버린다.
					      {
					    	  //수를 먼저 넣고 등장횟수를 넣어준다.
					    	  array[i][j]=list.get(j/2).number;
					    	  array[i][j+1]=list.get(j/2).count;
					 
					      }
                           
						
					}
					
					
					// 가장 긴열을 기준으로 빈곳에는 0을 채워준다.
					
					for(int i=1; i<=Rlength; i++)
					{
						for(int j=length.get(i-1)+1; j<=Clength; j++)
						{
								array[i][j]=0;	
						}
					}
					
					
				}
				
				else //C연산 수행  
				{
					for(int i=1; i<=Clength; i++)
					{
						//1열부터 정렬연산 
						
						List <State> list=new ArrayList<>();
						
						for(int j=1; j<=Rlength; j++)
						{
							int now=array[j][i];
							
							if(now==0) continue; // 숫자가 0이면 생략 
							
							State s=new State(1,now);
							int index=list.indexOf(s);
							if(index==-1) //이 숫자가 처음 보인거라면? 리스트에 추가 
							{
								list.add(s);
							}
							else {
								list.get(index).count++; // 이미 존재하면 개수만 +1
							}
							
							
							
						}
						Collections.sort(list);
						
                           Rlength=Math.max(list.size()*2,Rlength);  
                           length.add(list.size()*2);

                           
					      for(int j=1; j<=list.size()*2&&j<=100; j+=2) //100개를 제외한 나머지는 버린다.
					      {
					    	  //수를 먼저 넣고 등장횟수를 넣어준다.
					    	  array[j][i]=list.get(j/2).number;
					    	  array[j+1][i]=list.get(j/2).count;
					    	  
					      }
                           
						
					}
					
					
					for(int i=1; i<=Clength; i++)
					{
						for(int j=length.get(i-1)+1; j<=Rlength; j++)
						{
								array[j][i]=0;
								
						}
					}
					
					
				}
				
 
				if(array[R][C]==K)  //연산 수행 후  A[r][c]에 k가 들어있는지 확인 
				{
					ANS=second;
					break;
				}
				
				
				second++;
			}
			if(ANS!=0)
			System.out.println(ANS);
			
			else {
				System.out.println(-1);
		}
	}
	
	
		
		
	}
}
