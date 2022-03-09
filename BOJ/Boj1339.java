package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boj1339 {

	static int visited[][] = new int[101][2];
	static int n, ans = 0;
	static String input[];
	static int used[] = new int[10]; // 0~9까지 사용했는지?
	static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine()); // 단어의 개수

		input = new String[n + 1];

		for (int i = 1; i <= n; i++) // N개의 단어 입력
		{
			input[i] = in.readLine();

			for (int j = 0; j < input[i].length(); j++) {
				int val = input[i].charAt(j);

				if (visited[val][0] == 0) {
					visited[val][0] = 1;
					list.add(val);
				}

			}

		}

		simul(0);
		
		System.out.println(ans);

	}

	static void simul(int index) {

		if (index == list.size()) {

			int total = 0;

			for (int i = 1; i <= n; i++) {
				int sum = 0;
				for (int j = 0; j < input[i].length(); j++) {
					int val =visited[input[i].charAt(j)][1];
					sum += val;

					if (j< input[i].length()-1) {
						sum *= 10;
					}

				}

				total += sum;

			}

			if (ans < total)
				ans = total;

		}

		else {

			for (int i = 0; i <= 9; i++) {
				if (used[i] == 0) {
					used[i] = 1;
					visited[list.get(index)][1] = i;
					simul(index + 1);
					used[i] = 0;

				}

			}
		}

	}

}
