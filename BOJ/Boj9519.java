package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9519 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int x = Integer.parseInt(in.readLine());

		String input = in.readLine();

		String now = input + "";

		boolean flag = false;
		int cnt = 0;
		for (int i = 1; i <= x; i++) {

			now = simulation(now);

			if (input.equals(now)) {
				flag = true;
				cnt = i;
				break;
			}
		}

		// x번을 다 수행하기전에 다시 원래의 단어로 돌아왔다면? (x%원래의 단어로 돌아오는데 걸린 횟수)로 줄여준다
		if (flag) {

			for (int i = 1; i <= x % cnt; i++) {
				now = simulation(now);
			}
		}

		System.out.println(now);

	}

	static String simulation(String now) {

		int length = now.length() / 2;

		if (now.length() % 2 == 0) {
			length--;
		}

		char[] value = new char[now.length()];

		// 1 3 5 자리에 위치
		int cnt = 0;
		for (int i = now.length() - 1; i > length; i--) {

			value[i] = now.charAt(cnt * 2 + 1);
			cnt++;
		}
		cnt = 0;
		// 0 2 4 자리에 위치
		for (int i = 0; i <= length; i++) {

			value[i] = now.charAt(cnt * 2);
			cnt++;
		}

		return String.copyValueOf(value);

	}
}
