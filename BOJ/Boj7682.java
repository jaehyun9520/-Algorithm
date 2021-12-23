package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj7682 {

	static int array[][];
	static boolean vaild;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			String input = in.readLine();

			if (input.equals("end"))
				break;

			int O = 0;
			int X = 0;
			int cnt = 0;
			array = new int[4][4];
			vaild = false;
			for (int i = 1; i <= 3; i++) {
				for (int j = 1; j <= 3; j++) {

					array[i][j] = input.charAt((i - 1) * 3 + (j - 1));

					if (array[i][j] == 'O') {
						O++;
						cnt++;
					} else if (array[i][j] == 'X') {
						X++;
						cnt++;
					}

				}
			}

			if (X == O) // 2번째 선수가 마지막에 놓고 끝난 상황
			{
				if (connectCheck()) // 있다면 되돌리면서 유효한지 확인
				{
					dfs('O');

					if (vaild)
						sb.append("valid").append("\n");
					else
						sb.append("invalid").append("\n");
				}

				else {
					sb.append("invalid").append("\n");
				}
			}

			else if (X == (O + 1)) // X가 마지막에 놓고 끝난 상황 (게임판이 가득차서 끝나는 경우도 포함)
			{

				// 3개가 연결된 경우가 있는지 확인
				if (connectCheck()) // 있다면 되돌리면서 유효한지 확인
				{

					dfs('X');
					if (vaild) {

						sb.append("valid").append("\n");
					} else {

						sb.append("invalid").append("\n");
					}

				}

				else {

					if (cnt == 9)
						sb.append("valid").append("\n");

					else
						sb.append("invalid").append("\n");
				}

			}

			else {
				sb.append("invalid").append("\n");
			}

		}

		System.out.println(sb.toString());

	}

	static void dfs(int val) {

		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				if (array[i][j] == val) {
					array[i][j] = '.';

					if (!connectCheck()) {
						vaild = true;
						return;
					}

					else {
						array[i][j] = val;
					}

				}

			}
		}

	}

	static boolean connectCheck() {

		// 1. 세로/가로 체크

		for (int i = 1; i <= 3; i++) {
			int val = array[i][1];

			if (val != '.') {
				for (int j = 2; j <= 3; j++) // 세로줄 체크
				{
					if (val == array[i][j]) {

						if (j == 3)
							return true;
					} else {
						break;
					}
				}
			}

			val = array[1][i];

			if (val != '.') {
				for (int j = 2; j <= 3; j++) {
					if (val == array[j][i]) {
						if (j == 3)
							return true;
					} else {
						break;
					}
				}
			}

		}

		// 2. 대각선 체크
		if (array[1][1] == array[2][2] && array[2][2] == array[3][3] && array[1][1] != '.') {
			return true;
		}

		if (array[1][3] == array[2][2] && array[2][2] == array[3][1] && array[1][3] != '.') {
			return true;
		}

		return false;

	}
}
