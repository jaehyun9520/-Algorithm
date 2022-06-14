package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1010_다리놓기 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		int n, m;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine());

			long output = 1;
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			int val=n;
			for (int i = m; i > m-n; i--) {
				output *= i;
				while(val!=0&&output%val==0)
				{
					output=output/val;
							val--;
				}
			}
			sb.append(output + "\n");

		}
		
		System.out.println(sb.toString());

	}
}
