package study47;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj1911_흙길보수하기 {

	static class Line implements Comparable<Line> {
		int s, e;

		Line(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Line o) {

			return this.s - o.s;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int cnt = 0;
		ArrayList<Line> list = new ArrayList<>();

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			list.add(new Line(s, e));
		}

		Collections.sort(list);

		int s = list.get(0).s;
		int e = list.get(0).e;

		for (int i = 1; i < list.size(); i++) {

			int ns = list.get(i).s;
			int ne = list.get(i).e;

			if (e == ns) {
				e = ne;
			}

			else { // 중간에 빈공간이 있다면?

				int empty = ns - e + 1; // 사이의 빈공간

				if ((e - s) % L != 0 && L - (e - s) % L >= empty) {

					e = ne;
				} else {

					cnt = cnt + (e - s) / L;

					if ((e - s) % L != 0)
						cnt++;

					s = ns;
					e = ne;

				}

			}

		}

		cnt = cnt + (e - s) / L;

		if ((e - s) % L != 0)
			cnt++;

		System.out.println(cnt);

	}
}
