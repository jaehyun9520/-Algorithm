package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj3363_동전 {

	static int[] coin = new int[13]; // 1번 ~ 12번 코인의 무게
	static String[][] input = new String[3][9];
	static int ans = 0; // 0그대로 라면 impossible , -1이라면 indefinite , 그외의 다른 숫자라면 그대로 출력
	static char type;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i <= 2; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int j = 0;
			while (st.hasMoreTokens()) {
				input[i][j] = st.nextToken();
				j++;
			}

		}

		if (input[0][8] != null && input[1][8] != null && input[2][8] != null) {
			for (int i = 1; i <= 12; i++) {

				simulation(i, 0); // -
				simulation(i, 1); // +

			}
		}

		if (ans == 0) {
			System.out.println("impossible");
		}

		else if (ans == -1) {
			System.out.println("indefinite");
		}

		else {

			System.out.println(ans + "" + type);
		}

	}

	static void simulation(int coinNum, int state) {

		if (state == 0)
			coin[coinNum]--;

		else
			coin[coinNum]++;

		int cnt = 0;

		for (int i = 0; i <= 2; i++) {
			int sum1 = 0;
			int sum2 = 0;

			for (int j = 0; j <= 3; j++) {
				sum1 += coin[Integer.parseInt(input[i][j])];
			}
			for (int j = 5; j <= 8; j++) {
				sum2 += coin[Integer.parseInt(input[i][j])];
			}

			if (input[i][4].equals("<") && sum1 < sum2)
				cnt++;

			else if (input[i][4].equals(">") && sum1 > sum2)
				cnt++;
			else if (input[i][4].equals("=") && sum1 == sum2)
				cnt++;

		}

		if (cnt == 3) {
			if (ans == 0) {
				ans = coinNum;

				if (state == 0) {
					type = '-';
				}

				else {
					type = '+';
				}
			}

			else {
				ans = -1;
			}
		}

		if (state == 0)
			coin[coinNum]++;

		else
			coin[coinNum]--;

	}
}
