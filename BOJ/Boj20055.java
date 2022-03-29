package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj20055_컨베이어벨트위의로봇 {

	static int n, k, cnt = 0;
	static int conveyor[][];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		conveyor = new int[2 * n + 1][2]; // 0은 내구도 1은 로봇이 위치하고 있는지?

		st = new StringTokenizer(in.readLine(), " ");

		for (int i = 1; i <= 2 * n; i++) {
			conveyor[i][0] = Integer.parseInt(st.nextToken());
		}

		int ans = 0;
		while (cnt < k) // 내구도가 0인 칸의 개수가 k개 이상이 될때까지 반복
		{
			ans++;
			process1();
			process2();
			process3();

		}

		System.out.println(ans);

	}

	static void process1() {
		int end = conveyor[2 * n][0];// 마지막 칸 내구도
		for (int i = 2 * n; i >= 2; i--) {
			conveyor[i][0] = conveyor[i - 1][0];

			if (i < n)
				conveyor[i][1] = conveyor[i - 1][1];

		}
		conveyor[1][0] = end;
		conveyor[1][1] = 0;
	}

	static void process2() {
		for (int i = n - 1; i >= 1; i--) {
			if (conveyor[i][1] == 1 && conveyor[i + 1][0] >= 1 && conveyor[i + 1][1] == 0) {
				conveyor[i][1] = 0;
				conveyor[i + 1][0]--;

				if (i + 1 != n)
					conveyor[i + 1][1] = 1;

				if (conveyor[i + 1][0] == 0)
					cnt++; // 개수 카운트

			}

		}

	}

	static void process3() {
		if (conveyor[1][0] > 0) {
			conveyor[1][0]--;

			if (conveyor[1][0] == 0)
				cnt++;
			conveyor[1][1] = 1;

		}
	}

}
