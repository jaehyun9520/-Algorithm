package study66;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1461 {

	static boolean flag = true;
	static int n, m, sum;
	static List<Integer> minus = new ArrayList<>();
	static List<Integer> plus = new ArrayList<>();
	static List<Integer> answer = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= n; i++) {

			int val = Integer.parseInt(st.nextToken());

			if (val < 0) {
				minus.add(val);
			}

			else {
				plus.add(val);
			}

		}
		Collections.sort(minus);
		Collections.sort(plus);

		plus();
		minus();
		
		Collections.sort(answer);
		
		for(int i=0; i<answer.size()-1; i++) {
			
			sum+=(answer.get(i)*2);
		}

		sum+=(answer.get(answer.size()-1));
		System.out.println(sum);

	}

	static void plus() {

		int cnt = 0;
		int length = 0;
		for (int i = plus.size() - 1; i >= 0; i--) {

			// m권의 책을 다 제자리에 뒀다면?
			if (cnt == m) {

				cnt = 0;

				answer.add(length);

			}

			if (cnt == 0) {
				length = plus.get(i);
				cnt++;
			}

			else {
				cnt++;
			}

		}
		if (cnt != 0) {
			answer.add(length);
		}
	}

	static void minus() {

		int cnt = 0;
		int length = 0;

		for (int i = 0; i < minus.size(); i++) {

			// m권의 책을 다 제자리에 뒀다면?
			if (cnt == m) {

				cnt = 0;
				answer.add(length);

			}

			if (cnt == 0) {
				length = Math.abs(minus.get(i));
				cnt++;
			}

			else {
				cnt++;
			}

		}

		if (cnt != 0) {
			answer.add(length);
		}

	}
}
