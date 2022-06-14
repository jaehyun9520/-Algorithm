package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2251 {

	static int visited[][];
	static int used[];
	static int A, B, C;
	static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		visited = new int[A + 1][B + 1];
		used = new int[C + 1];

		Queue<int[]> q = new LinkedList<>();

		q.add(new int[] { 0, 0, C });

		while (!q.isEmpty()) {
			int[] now = q.poll();

			int a = now[0];
			int b = now[1];
			int c = now[2];
			if (visited[a][b] == 0) {
				visited[a][b] = 1;

				if (a == 0 && used[c] == 0) {
					list.add(c);
					used[c] = 1;
				}

				if (a != 0) // A물통의 물을 옮기는 경우
				{

					// 1. B물통이 가득차지 않은경우 B로 옮긴다
					if (b < B) {
						int dif = B - b;

						if (a <= dif)
							q.add(new int[] { 0, b + a, c });

						else {
							q.add(new int[] { a - dif, b + dif, c });
						}
					}

					// 2. c물통으로 옮긴다
					q.add(new int[] { 0, b, c + a });
				}
				if (b != 0) {
					// 1. c물통으로 옮긴다
					q.add(new int[] { a, 0, c + b });

					if (a < A) // 2. a물통으로 옮긴다
					{
						int dif = A - a;

						if (b <= dif)
							q.add(new int[] { a + b, 0, c });

						else {
							q.add(new int[] { a + dif, b - dif, c });
						}

					}

				}

				if (c != 0) {
					if (a < A) {
						int dif = A - a;

						if (c <= dif) {
							q.add(new int[] { a + c, b, 0 });
						}

						else {

							q.add(new int[] { a + dif, b, c - dif });
						}

					}

					else if (b < B) {
						int dif = B - b;

						if (c <= dif) {
							q.add(new int[] { a, b + c, 0 });
						} else {
							q.add(new int[] { a, b + dif, c - dif });
						}

					}

				}

			}

		}

		Collections.sort(list);

		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}

	}
}
