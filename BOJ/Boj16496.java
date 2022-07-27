package study48;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj16496_큰수만들기 {

	static class Number implements Comparable<Number> {

		String num;

		Number(String num) {
			this.num = num;
		}

		@Override
		public int compareTo(Number o) {

			String num1 = this.num + o.num;
			String num2 = o.num + this.num;

			int index=0;
			int length1 = this.num.length();
			int length2 = o.num.length();

			int val1, val2;
			while (true) {

				val1 = num1.charAt(index) - 48;
				val2 = num2.charAt(index) - 48;

				if (val1 > val2) {
					return -1;
				} else if (val1 < val2) {
					return 1;
				}

				else if (val1 == val2) {
					
				index++;
					
					if(index==(length1+length2))
					{
						return -1;
					}
				}

			}

		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());

		ArrayList<Number> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			String input = st.nextToken();

			list.add(new Number(input));
		}

		Collections.sort(list);

		if (list.get(0).num.equals("0")) {
			System.out.println(0);
		} else {

			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i).num);
			}
		}
	}
}
