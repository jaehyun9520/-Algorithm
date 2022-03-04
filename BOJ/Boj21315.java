package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj21315 {

	static int n;
	static int input[]; // 입력값 ( (2,k) 섞기를 2번 한 결과
	static int ans[] = new int[3]; // 출력값
	static int k;
	static ArrayList<Integer> p = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());
		input = new int[n + 1];

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		for (int i = 1; i <= n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		// k의 최대 개수
		for (k = 1; k <= 9; k++) {
			if ((int) Math.pow(2, k) < n) {
				p.add(k);
			}

			else {
				k--;
				break;
			}

		}

		if (k == 10)
			k = 9;

		// 2개의 순열 만들기

		permutation(0);

	}

	static void simulation(int[] test, int k) {

		int end = n; // 현재 맨 밑(처음에는 n 그다음에는 직전에 맨 위로 올린 카드 중 맨 밑 )

		int copy[] = new int[n + 1]; // 카드를 이동시킬 때 사용할 배열

		// 1~ k+1번 단계
		for (int i = 1; i <= k + 1; i++) {

			int cnt = (int) Math.pow(2, k - i + 1); // 현재 단계에서 위로 올릴 카드 개수

			int point = 0;
			for (int j = end - cnt; j >= 1; j--) // 밑에 카드가 올라오면서 오른쪽으로 이동할 카드들
			{

				copy[end - point] = test[j];
				point++;
			}
			// 더미들이 위로 올라감
			point = 1;
			for (int j = end - cnt + 1; j <= end; j++) // ex end=4 cnt=2
			{
				copy[point] = test[j];
				point++;
			}

			end = cnt;

			for (int v = 1; v <= n; v++) {
				test[v] = copy[v];

			}

		}

	}

	static void permutation(int cnt) {
		if (cnt == 2) // 시뮬레이션 시작
		{

			int test[] = new int[n + 1];

			for (int i = 1; i <= n; i++) { // 초기상태로 세팅 (1~n)
				test[i] = i;
			}

			simulation(test, ans[1]); // (2,고른 숫자 첫번째) 섞기

			simulation(test, ans[2]); // (2,고른 숫자 두번째) 섞기

			// input과 똑같은지 비교
			boolean flag = true;

			for (int i = 1; i <= n; i++) {

				if (input[i] != test[i]) {
					flag = false;

				}

			}

			if (flag) {
				System.out.println(ans[1] + " " + ans[2]);
			}

		}

		else {

			for (int i = 0; i < k; i++) {

				ans[cnt + 1] = p.get(i);
				permutation(cnt + 1);

			}

		}

	}
}
