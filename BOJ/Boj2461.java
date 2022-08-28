package a0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj2461_대표선수 {

	static int n, m;
	static int array[][]; // 각 학급

	static class Number implements Comparable<Number> {
		int num, classN;

		Number(int num, int classN) {
			this.num = num;
			this.classN = classN;
		}

		@Override
		public int compareTo(Number o) {
			return this.num - o.num;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		array = new int[n + 1][m];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < m; j++) {

				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= n; i++) // 오름차순으로 정렬
		{
			Arrays.sort(array[i]);
		}

		System.out.println(solution());

	}

	static int solution() {
		int ans = Integer.MAX_VALUE;
		// 최솟값과 최댓값 그리고 최솟값의 반 최댓값의 반
		int min = Integer.MAX_VALUE;
		int max = 0;
		int minClass = 0;

		int classIndex[] = new int[n + 1]; // 현재 각 클래스의 시작 인덱스
		Number represen[] = new Number[n + 1];

		represen[0] = new Number(0, 0);
		for (int i = 1; i <= n; i++) {

			represen[i] = new Number(array[i][0], i);

		}

		Arrays.sort(represen);
		min = represen[1].num;
		max = represen[n].num;
		minClass = represen[1].classN;

		while (true) {
			ans = Integer.min(ans, max - min); // 값을 갱신할 수 있으면 갱신

			int index = ++classIndex[minClass];

			if (index == m) { // 이미 끝까지 왔다면?
				break;
			}

			else {
				represen[1].num = array[minClass][index]; // 값 갱신

				Arrays.sort(represen);
				min = represen[1].num;
				max = represen[n].num;

				minClass = represen[1].classN;

			}

		}

		return ans;
	}
}
