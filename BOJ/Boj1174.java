package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1174_줄어드는수 {

	static int n,  count = 0, k = 0;
	static long ans=-1;
	static boolean flag = true;
	static int list[] = new int[11];

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());

		for (int i = 1; i <= 10; i++) {
			if (flag) {
				k = i;
				simul(0);
			}
		}
	
			System.out.println(ans);

	}

	static void simul(int cnt) {

		if (cnt == k) {

			count++;
			if (count == n) {
				flag = false;
				long val = 0;
				for (int i = 1; i <= cnt; i++) {
					val += list[i] * (Math.pow(10, cnt - i));
				}

				ans = val;
			}
			return;
		}

		for (int i = 0; i <= 9; i++) {

			if (list[cnt] > i || cnt == 0) {
				list[cnt + 1] = i;
				simul(cnt + 1);

			}

		}

	}
}
