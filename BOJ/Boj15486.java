package a0723;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15486_퇴사2 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(in.readLine());
		int dp[] = new int[n + 2];
		int input[][] = new int[n + 1][2];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine());
			int t, val;
			t = Integer.parseInt(st.nextToken());
			val = Integer.parseInt(st.nextToken());
			input[i][0] = t;
			input[i][1] = val;
		}
		int ans = 0;
		int max = 0;
// dp[i]= 현재 i일에 가질 수 있는 최대이익인데 현재 날부터 시작하는 일은 아직 하지 않은 상태?	
		for (int i = 1; i <= n; i++) {
			max = Integer.max(dp[i], max);
			if (i + input[i][0] <= n + 1) {

				dp[i + input[i][0]] = Integer.max(dp[i + input[i][0]], max + input[i][1]);
				ans = Integer.max(dp[i + input[i][0]], ans);
			}

		}

		System.out.println(ans);
	}
}
