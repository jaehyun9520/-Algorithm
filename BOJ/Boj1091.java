package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1091_카드섞기 {

	static int p[]; // 해당 카드가 어느 플레이어한테 가야하는지
	static int s[]; // 카드가 어디로 이동해야하는지
	static int n, ans = -1;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());

		int list[] = new int[n];
		p = new int[n];
		s = new int[n];

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		for (int i = 0; i < n; i++) {
			p[i] = Integer.parseInt(st.nextToken());
			list[i] = i;
		}

		st = new StringTokenizer(in.readLine(), " ");

		for (int i = 0; i < n; i++) {
			s[i] = Integer.parseInt(st.nextToken());
		}
		simulation(list, 0);
		System.out.println(ans);
	}

	static void simulation(int[] list, int cnt) {

		// 현재 배열이 수열 p의 조건을 만족하는지 확인
		if (check(list)) {

			ans = cnt;
		}

		else {

			if (!initValCompare(list) || cnt == 0) {

				shuffle(list);
				simulation(list, cnt + 1);

			}

		}

	}

	static void shuffle(int[] list) {

		int tmp[] = new int[n];

		for (int i = 0; i < n; i++) {
			tmp[s[i]] = list[i];

		}
		for (int i = 0; i < n; i++) {
			list[i] = tmp[i];
		}
	}

	static boolean initValCompare(int[] list) {
		boolean flag = true;
		for (int i = 0; i < n; i++) {

			if (list[i] != i) {
				flag = false;
				break;
			}

		}
		return flag;
	}

	static boolean check(int[] list) {
		boolean flag = true;

		for (int i = 0; i < n; i++) {

			int num = list[i];
			int player = 0;

			if (i % 3 == 0) {
				player = 0;
			} else if ((i - 1) % 3 == 0) {
				player = 1;
			} else {
				player = 2;
			}
			if (p[num] != player) {
				flag = false;
				break;
			}

		}

		return flag;
	}

}
