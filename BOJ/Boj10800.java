package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj10800 {

	static class ColorBall implements Comparable<ColorBall> {

		int color, weight, i;

		ColorBall(int color, int weight, int i) {
			this.color = color;
			this.weight = weight;
			this.i = i;

		}

		@Override
		public int compareTo(ColorBall o) {

			if (this.weight == o.weight) {
				return this.color - o.color;
			}

			return this.weight - o.weight;
		}

	}

	static int n;
	static ArrayList<ColorBall> list = new ArrayList<>();
	static int w[] = new int[2001];
	static int ans[];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(in.readLine());
		ans = new int[n + 1];
		int colorWeight[] = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int color = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			list.add(new ColorBall(color, weight, i));
		}

		int total = 0;

		Collections.sort(list);
		ColorBall now = list.get(0);

		ans[now.i] = 0;
		colorWeight[now.color] = now.weight;
		total = now.weight;
		w[now.weight] = 1;

		for (int i = 1; i < n; i++) {
			ColorBall pre = list.get(i - 1);
			now = list.get(i);
			// 내 앞에 나와 같은 경우가 있는가?
			if (pre.color == now.color && pre.weight == now.weight) {
				ans[now.i] = ans[pre.i];
				total += now.weight;
				colorWeight[now.color] += now.weight;
				w[now.weight]++;
			} else {

				ans[now.i] = total - colorWeight[now.color] - (w[now.weight] * now.weight);

				total += now.weight;
				colorWeight[now.color] += now.weight;
				w[now.weight]++;
				// 전체에서 나와 같은색을 빼고 같은 무게인 애들을 뺀다

			}

		}

		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= n; i++) {

			sb.append(ans[i] + "\n");

		}
		System.out.println(sb.toString());

	}
}
