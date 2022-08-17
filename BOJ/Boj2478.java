package a2478;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj2478_자물쇠 {

	// 내림차순 D, 오름차순 A
	static String type[] = {
			// 왼쪽 오름차순 구역 내에서 구간 뒤집기가 일어난 경우

			"DA", "DAA", "ADAA", "ADA"

			// 오른쪽 오름차순 구역 내에서 구간 뒤집기가 일어난 경우
			, "AD", "ADA", "AADA", "AAD"

			// 두 오름차순 구역을 포함해서 구간 뒤집기가 일어난 경우
			, "DD", "ADDA", "ADD", "DDA"

	};

	static int n;
	static int array[];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());
		array = new int[n + 1];

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		for (int i = 1; i <= n; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}

		simulation();

	}

	static void simulation() {

		loop: for (int i = 1; i < n; i++) {

			// 1. 1~n-1 번 오른쪽 밀기 후 1차원 배열 반환
			int result[] = rightPush(i);


			// 구역 개수 카운트
			ArrayList<Integer> start = sectionCount(result);

			for (int j = 0; j < type.length; j++) // 타입이 될수 있는 후보들을 확인하고 실제로 해당 타입이라고 가정하고 진행
			{
				if (start.size() == type[j].length()) // 같은 길이라면 해당 타입으로 진행
				{
					int s = Integer.MAX_VALUE;
					int e = 0;

					for (int k = 0; k < type[j].length(); k++) {

						if (type[j].charAt(k) == 'D') {
							s = Integer.min(start.get(k), s);

							if (k + 1 < type[j].length()) // 다음 구간이 있다면? 다음 구간의 시작점 -1 이 구간의 마지막 위치
							{

								e = Integer.max(e, start.get(k + 1) - 1);
							}

							else {

								e = Integer.max(e, n);
							}

						}

					}

					int tmp[] = new int[n + 1];

					for (int k = 1; k <= n; k++) {
						tmp[k] = result[k];
					}

					reverse(tmp, s, e); // 해당 범위로 구간 뒤집기

					int val = validCheck(tmp); // 오름차순, 오름차순의 형식인지 확인

					if (val != -1) {
						System.out.println(val);
						System.out.println(s + " " + e);
						System.out.println(i);
						break loop;

					}

				}

			}

		}

	}

	static int validCheck(int[] result) {

		boolean flag = true;
		int cnt = 0;
		int index = 0;
		for (int i = 1; i < n; i++) {

			if (result[i] > result[i + 1]) {
				cnt++;
				index = i + 1;

			}

			else if (result[i] + 1 != result[i + 1]) {
				cnt++;
			}

			if (cnt >= 2) {
				flag = false;
				break;
			}

		}
		if (cnt == 1) {
			return n - index + 1;
		}

		else {
			return -1;
		}

	}

	static void reverse(int[] result, int s, int e) {
		ArrayList<Integer> tmp = new ArrayList<>();

		for (int i = e; i >= s; i--) {
			tmp.add(result[i]);
		}

		for (int i = s; i <= e; i++) {
			result[i] = tmp.get(i - s);
		}

	}

	static ArrayList<Integer> sectionCount(int[] result) {

		ArrayList<Integer> start = new ArrayList<>();
		start.add(1);

		for (int i = 2; i <= n; i++) {

			if (result[i] != result[i - 1] + 1 && result[i] != result[i - 1] - 1) {
				start.add(i);
			}

		}

		return start;

	}

	static int[] rightPush(int cnt) {

		int newArray[] = new int[n + 1];
		int index = 1;

		for (int j = n + 1 - cnt; j <= n; j++) // 오른쪽으로 밀어지는 숫자들
		{
			newArray[index] = array[j];
			index++;
		}

		for (int j = 1; j <= n - cnt; j++) {
			newArray[index] = array[j];
			index++;
		}

		return newArray;

	}
}
