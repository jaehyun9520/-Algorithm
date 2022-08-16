package a0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj7453_합이0인네정수 {

	static int[] list;
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());
		long ans = 0;
		int array[][] = new int[4][n + 1];

		StringTokenizer st;

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j <= 3; j++) {
				array[j][i] = Integer.parseInt(st.nextToken());
			}
		}

		list=new int[n*n];
		int cnt=0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				list[cnt]=(array[2][i] + array[3][j]);
				cnt++;
			}
		}
		
		Arrays.sort(list);

		
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				int val = -1 * (array[0][i] + array[1][j]);
				int s = lowerbound(val);
				int e = upperbound(val);

				ans += (e - s);

			}
		}
		System.out.println(ans);
	}

	static int lowerbound(int val) {
		int loc = -1;

		int s = 0;
		int e = list.length - 1;
		int mid = 0;

		while (s <= e) {
			mid = (s + e) / 2;

			if (list[mid] >= val) {
				loc = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}

		}

		if(loc==-1) loc=list.length;
		
		return loc;
	}

	static int upperbound(int val) {
		int loc = -1;

		int s=0;
		int e = list.length- 1;
		int mid = 0;

		while (s <= e) {
			mid = (s + e) / 2;

			if (list[mid] > val) {
				loc = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}

		}

		if(loc==-1) loc=list.length;
		
		
		return loc;
	}
}
