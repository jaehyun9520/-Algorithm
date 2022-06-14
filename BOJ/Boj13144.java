package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj13144 {

	public static void main(String[] args) throws IOException {

		long answer=0;
		int n=0;
		int p1 = 1;
		int p2 = 2;

		boolean used[];
		int list[];
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());

		used = new boolean[100001];
		list = new int[n + 1];

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		for (int i = 1; i <= n; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}

		used[list[1]] = true;
		answer = 1;

		while (p2 <= n) {

			// 새로추가된 p2가 기존 리스트의 값을 가지고 있지 않은 경우
			if (used[list[p2]] == false) {
				answer++;
				used[list[p2]] = true;
				// 1 2 3 4 5 (5)

				p2++;
				if (p2 > n) {
					while (true) {
						p1++;

						if (p1 > n)
							break;

						answer += (p2 - p1);

					}
					break;
				}

			}

			// 이미 기존리스트에서 가지고 있는 경우
			else if (used[list[p2]] == true) {

				while (true) {
					used[list[p1]] = false;
					p1++;

					if (used[list[p2]] == false) {
						answer += (p2 + 1 - p1);
						used[list[p2]] = true;
						break;
					}
					
					else {
						
						answer+=(p2-p1);
						
					}
				}

				p2++;
				if (p2 > n) {
					while (true) {
						p1++;

						if (p1 > n)
							break;

						answer += (p2 - p1);

					}
					break;
				}

			}

		}

		System.out.println(answer);

	}
}
