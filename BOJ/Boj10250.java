package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10250_ACM호텔 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			int h, w, num;
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			num = Integer.parseInt(st.nextToken());

			int mok = num / h;
			int nmg = num % h;

			if(nmg==0)  {nmg=h;  mok--;}
			
			if ((mok + 1) / 10 == 0)
				sb.append(+nmg + "0" + (mok + 1) + "\n");

			else {
				sb.append(nmg + "" + (mok + 1) + "\n");
			}

		}
		System.out.println(sb.toString());

	}
}
