package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15565 {

	public static void main(String[] args) throws Exception {

		int n, k;
		int[] list;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		list = new int[n + 1];

		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= n; i++) {

			list[i] = Integer.parseInt(st.nextToken());

		}

		int left = 1, right = 1;

		int cnt = 0;
		int minSize = Integer.MAX_VALUE;

		if (list[1] == 1)
			cnt++;

		while (left <= right && right <= n) {
			
			if(cnt==k) {
				minSize=Integer.min(minSize,right-left+1);
			}
			
			
			// 1. 주어진 k보다 cnt가 작으면 right ++
			if (cnt < k) {
				right++;
				
				
				if(right<=n&&list[right]==1) cnt++;
			}

			// 2. 주어진 k와 cnt가 같으면 가장 작은 연속된 집합을 구하기 위해 left ++
			else if (cnt == k) {
				if(list[left]==1) cnt--;
				left++;
			}

		}

		
		if(minSize==Integer.MAX_VALUE) System.out.println("-1");
		
		else
		System.out.println(minSize);
	}
}
