package study55;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1081 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		String val1 = st.nextToken();
		String val2 = st.nextToken();
		long list2[] = new long[10];
		long list1[] = new long[10];

		int tmp = Integer.parseInt(val1);
		tmp -= 1;
		if (tmp >= 1) {
			numberCount(tmp + "", list1);
		}

		numberCount(val2, list2);

		long ans = 0;

		for (int i = 1; i <= 9; i++) {
			long cnt = list2[i] - list1[i];

			ans += (cnt * i);

		}
		System.out.println(ans);
	}

	static void numberCount(String val, long[] list) {
		char number[] = val.toCharArray();

		int firstNum = 0;
		for (int i = 0; i < number.length; i++) {
			int num = number[i] - 48;

			if (i == 0) {
				firstNum = num;
				int cnt = number.length - 1;
				for (int j = 1; j < firstNum; j++) {

					list[j] += Math.pow(10, cnt);
				}
			}

			else {

				int cnt = number.length - 2;
				for (int j = 1; j <= 9; j++) {
					list[j] += Math.pow(10, cnt) * firstNum;
				}

			}

		}

		boolean flag = false;
		int first = 0;
		int cnt = 0;
		for (int i = 1; i < number.length; i++) {
			int num = number[i] - 48;

			if (!flag && num != 0) {
				first = i;
				flag = true;
				break;
			}
		}

		// 내 뒤에 숫자가 있다면?
		if (flag) {
			cnt = Integer.parseInt(val.substring(first, val.length())) + 1;
			list[firstNum] += cnt;
			String next = val.substring(first, val.length());

			numberCount(next, list);

		} else {
			list[firstNum]++;
		}

	}

}
