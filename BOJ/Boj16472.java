package study66;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj16472 {

	public static void main(String[] args) throws IOException {

		int n;
		String input;

		int used[] = new int[26]; // 0~25 (a~z)
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());
		input = in.readLine();
		int s = 0, e = 0, cnt = 1;
		used[input.charAt(0) - 'a']++;
		int ans = 0;
		while (s <= e) {

			ans = Integer.max(ans, e - s + 1);

			e++;

			if (e == input.length())
				break;

			// 처음 나오는 알파벳 종류라면?
			if (used[input.charAt(e) - 'a'] == 0) {

				// 이미 인식할 수 있는 개수를 채웠다면? 이전의 종류들을 제거
				if (cnt == n) {

					while (cnt == n) {

						used[input.charAt(s) - 'a']--;

						if (used[input.charAt(s) - 'a'] == 0) {

							cnt--;
						}
						s++;
					}

				}

				cnt++;
				used[input.charAt(e) - 'a']++;
			}

			else {

				used[input.charAt(e) - 'a']++;

			}

		}

		System.out.println(ans);

	}
}
