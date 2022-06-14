package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1806 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int n, s, ans = Integer.MAX_VALUE;

		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());

		int input[] = new int[n + 1];

		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		int start = 1, end = 1;
		int sum = input[1];
		int length = 1;

		while (start <= end&&end<=n) {

			if (sum >= s) // 주어진 값 이상이라면?
			{
				ans = Integer.min(ans, length);
				sum -= input[start];
				start++;
				length--;

			}
			
			else {
				end++;
				if(end>n) break;
				
				sum+=input[end];
				length++;
				
			}

		}

		if(ans==Integer.MAX_VALUE) ans=0;
		System.out.println(ans);
	}
}
