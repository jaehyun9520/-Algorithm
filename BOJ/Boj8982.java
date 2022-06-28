package study44;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj8982_수족관1 {

	static class Line implements Comparable<Line> {
		int x, y, length;

		Line(int x, int y, int length) {
			this.x = x;
			this.y = y;
			this.length = length;
		}

		@Override
		public int compareTo(Line o) {

			return this.x - o.x;
		}

	}

	static int n = 0, k = 0;
	static int water = 0;
	static int pointer[][];
	static int max[];
	static ArrayList<Line> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());
		pointer = new int[(n - 2) / 2 + 1][3]; // 0은 세로줄 번호, 1은 가로줄 번호, 길이 (수평선분 개수)
		max = new int[(n - 2) / 2 + 1]; // 각 수평선분에서 빠져나가는 물의 양
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int a, b;
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			if (i != 1 && i != n) {

				if (i % 2 == 0) {
					pointer[i / 2][0] = a;
					pointer[i / 2][1] = b;

				}

				if (i % 2 == 1) {
					pointer[i / 2][2] = a - pointer[i / 2][0];

					water += (pointer[i / 2][1] * pointer[i / 2][2]);
				}

			}
		}

		k = Integer.parseInt(in.readLine());

		for (int i = 1; i <= k; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int a, b, c, d;

			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());

			list.add(new Line(a, b, c - a));

		}

		Collections.sort(list); // 왼쪽부터 시작하게 정렬

		for (int i = 0; i < list.size(); i++) {

			int index = 0;
			int leftX = 0, leftY = 0;
			int rightX = 0;
			int rightY = 0;

			int nowX = list.get(i).x;
			int nowY = list.get(i).y;
			int length = list.get(i).length;

			if (i != 0) {
				leftX = list.get(i - 1).x;
				leftY = list.get(i - 1).y;
			}

			if (i != (k - 1)) {
				rightX = list.get(i + 1).x;
				rightY = list.get(i + 1).y;

			}

			// 해당 선분의 위치를 찾는다
			for (int j = 1; j <= (n - 2) / 2; j++) {

				if (pointer[j][0] == nowX && pointer[j][1] == nowY) {
					index = j;
					break;
				}

			}

			max[index] = length * nowY;

			// 오른쪽 확인
			int height = nowY;
			for (int j = index + 1; j <= (n - 2) / 2; j++) {
				if (pointer[j][0] == rightX && pointer[j][1] == rightY) {
					break;
				}

				// 오른쪽 옆의 수평선분
				int nextH = pointer[j][1]; // 높이
				int nextL = pointer[j][2]; // 길이

				if (height <= nextH) {
					max[j] = Integer.max(max[j], height * nextL);
				}

				else {
					height = nextH;
					max[j] = Integer.max(max[j], height * nextL);

				}

			}

			// 왼쪽 확인
			height = nowY;
			for (int j = index - 1; j >= 1; j--) {
				if (pointer[j][0] == leftX && pointer[j][1] == leftY) {
					break;
				}

				// 오른쪽 옆의 수평선분
				int nextH = pointer[j][1]; // 높이
				int nextL = pointer[j][2]; // 길이

				if (height <= nextH) {
					max[j] = Integer.max(max[j], height * nextL);
				}

				else {
					height = nextH;
					max[j] = Integer.max(max[j], height * nextL);

				}

			}

		}

		for (int i = 1; i <= (n - 2) / 2; i++) {
			water -= max[i];
		}

		System.out.println(water);

	}

}
