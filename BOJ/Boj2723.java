package a0919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2723 {

	static int comb[][] = new int[12][12];
	static int n;
	static long cnt[][] = new long[12][12];
	static long ans[]=new long[12];
	public static void main(String[] args) throws NumberFormatException, IOException {

		StringBuilder sb=new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(in.readLine());
		
		combInit();	
		buttonComb();

		for(int i=1; i<=n; i++)
		{
			int val=Integer.parseInt(in.readLine());
			
			sb.append(ans[val]+"\n");
		}
		System.out.println(sb.toString());
	}

	static void buttonComb() {

		for (int i = 1; i <= 11; i++) // 주어진 버튼의 개수
		{
			for (int j = 1; j <= i; j++) // 만들수 있는 버튼 조합의 개수 (최소 1개 ~ 최대 버튼의 개수만큼)
			{

				for (int k = 1; k <= i + 1 - j; k++) {
					if (j == 1) {
						cnt[i][j] += comb[i][k];
					}

					else {
						cnt[i][j] += (comb[i][k] * cnt[i - k][j - 1]);
					}
				}

				ans[i]+=cnt[i][j];

			}

		}

	}

	static void combInit() {

		for (int i = 1; i <= 11; i++) {
			comb[i][0] = 1;
			comb[i][i] = 1;

			for (int j = 1; j <= i - 1; j++) {
				comb[i][j] = comb[i - 1][j] + comb[i - 1][j - 1];
			}
		}

	}
}
