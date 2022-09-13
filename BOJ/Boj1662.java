package study54;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj1662 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String input = in.readLine();
		Stack<int[]> s = new Stack<>();
		for (int i = 0; i < input.length(); i++) {
			char val = input.charAt(i);

			if (val == ')') {
				int length = 0;
				while (true) {
					int[] next = s.pop();

					if (next[0] == 0) // 일반값
					{
						if ('0' <= next[1] && next[1] <= '9') {
							length++;
						}

						else if (next[1] == '(') {

							s.add(new int[] { 1, (s.pop()[1] - '0') * length });
							break;
						}
					} else {
						length += next[1];
					}
				}

			} else {
				s.add(new int[] { 0, val });
			}

		}
		int ans = 0;
		while (!s.empty()) {

			int val[] = s.pop();

			if (val[0] == 0) {
				ans++;
			}

			else {
				ans += val[1];
			}

		}

		System.out.println(ans);

	}
}
