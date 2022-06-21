package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1039_교환 {
	static class State {
		int array[];
		int cnt;

		State(int[] a, int cnt) {
			this.array = a;
			this.cnt = cnt;
		}

	}

	static int visited[] = new int[1000001];
	static int n, k, m = 1;
	static int ans = -1;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		int val = 10;

		while (true) {
			if (n / val != 0) {
				val *= 10;
				m++;
			} else {
				break;
			}
		}


		bfs();

		System.out.println(ans);
	}

	static void bfs() {

		int start[] = new int[m + 1];

		Queue<State> q = new LinkedList<>();

		int tmp = n;
		int val = (int) Math.pow(10, m - 1);
		for (int i = 1; i <= m; i++) {
			start[i] = tmp / val;
			tmp = tmp % val;
			val /= 10;
		}

		q.add(new State(start,0));
		
		
		while(!q.isEmpty())
		{
			State now=q.poll();
			int array[]=now.array;
			int cnt=now.cnt;
			
			if(cnt==k) continue;
			for(int i=1; i<m; i++) 
			{
				for(int j=i+1; j<=m; j++)
				{
					tmp=array[i];
					array[i]=array[j];
					array[j]=tmp;
					
					int value=array[1];
					for(int k=2; k<=m; k++)
					{
						if(value==0) break;
						
						value*=10;
						
						value+=array[k];
					}
					if(value!=0&&(visited[value]==0||(visited[value]%2 != (cnt+1)%2)))
					{
						visited[value]=cnt+1;
						
						System.out.println(value+" cnt="+visited[value]);
						
						if((cnt+1)%2==0&&k%2==0||(cnt+1)%2!=0&&k%2!=0)
						{
							
							ans=Integer.max(value,ans);
						}
						int[] arr2=new int[m+1];
						for(int k=1; k<=m; k++)
						{
							arr2[k]=array[k];
						}
						q.add(new State(arr2,cnt+1));		
					}
					tmp=array[i];
					array[i]=array[j];
					array[j]=tmp;		
				}
			}
		}
	}
}