package study48;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj16939_큐브 {

	static int list[][] = { { 1, 3, 5, 7, 9, 11, 24, 22 }, { 2, 4, 6, 8, 10, 12, 23, 21 },
			{ 13, 14, 5, 6, 17, 18, 21, 22 }, { 15, 16, 7, 8, 19, 20, 23, 24 }, { 3, 4, 17, 19, 10, 9, 16, 14 } 
			,{1,2,18,20,12,11,15,13}};

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int[] color = new int[25];

		for (int i = 1; i <= 24; i++) {
			color[i] = Integer.parseInt(st.nextToken());
		}

		int ans = 0;
		for (int i = 0; i <=5; i++) {


			if (left(color, list[i])) {
				ans = 1;
				break;
			}

			if (right(color, list[i])) {
				ans = 1;
				break;
			}

		}

		System.out.println(ans);

	}

	static boolean left(int[] input, int list[]) {
		int tmp[] = arrayCopy(input);


		int tmp1 = tmp[list[6]]; // 24
		int tmp2 = tmp[list[7]]; // 22

		for (int i = 7; i >= 2; i--) {

			tmp[list[i]] = tmp[list[i - 2]];
		}
		tmp[list[0]] = tmp1;


		tmp[list[1]] = tmp2;

		return colorCheck(tmp);

	}

	static boolean right(int[] input, int list[]) {
		int tmp[] = arrayCopy(input);
		int tmp1 = tmp[list[0]];
		int tmp2 = tmp[list[1]];

		for (int i = 0; i <= 5; i++) {
			tmp[list[i]] = tmp[list[i + 2]];
		}
		tmp[list[6]] = tmp1;
		tmp[list[7]] = tmp2;
		return colorCheck(tmp);
	}

	static int[] arrayCopy(int[] input) {
		int tmp[] = new int[25];

		for (int i = 1; i <= 24; i++) {
			tmp[i] = input[i];
		}
		return tmp;
	}

	static boolean colorCheck(int[] input) {
		boolean flag = true;

		int cnt = 1;
		int color = input[1];
		for (int i = 2; i <= 24; i++) {
			if (cnt < 4 && color != input[i]) {
				flag = false;
				break;
			} else if (cnt < 4 && color == input[i]) {
				cnt++;
			}

			else if (cnt == 4) {
				cnt = 1;
				color = input[i];
			}

		}

		return flag;
	}
}
