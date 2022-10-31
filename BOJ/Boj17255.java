package study61;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Boj17255 {

	static int[] numberCnt = new int[10]; // n에 쓰인 각 숫자의 개수

	static List<Integer> list = new ArrayList<>();

	static int length;
	static int ans = 0;
	static String input;
	static Set<String> set = new HashSet<>();

	public static void main(String[] args) throws IOException {

		boolean[] check = new boolean[10];
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		input = in.readLine();

		length = input.length();
		int count = 0;
		for (int i = 0; i < input.length(); i++) {

			int val = input.charAt(i) - 48;
			numberCnt[val]++;

			if (!check[val]) {
				count++;
				check[val] = true;
				list.add(val);
			}

		}
		// 이게 굳이 필요할까?
		Collections.sort(list);
		// 사용된 숫자가 하나일 경우
		if (count == 0)
			System.out.println(1);

		else {
			dfs(0, "");
			System.out.println(ans);

		}

	}

	static void dfs(int cnt, String order) {

		// 지금까지 숫자가 제대로 만들어지지 않고 있다면 진행 x
		if (!input.contains(order.substring(order.length() - cnt, order.length())))
			return;

		if (cnt == length) {
			if (!set.contains(order)) {
				set.add(order);
				ans++;
			}

		}

		else {

			for (int i = 0; i < list.size(); i++) {

				int num = list.get(i);
				if (numberCnt[num] > 0) {
					numberCnt[num]--;
					String number = order.substring(order.length() - cnt, order.length());
					// 왼쪽에 붙이기
					dfs(cnt + 1, order + num + number);
					// 오른쪽에 붙이기
					dfs(cnt + 1, order + number + num);

					numberCnt[num]++;

				}

			}

		}

	}
}
