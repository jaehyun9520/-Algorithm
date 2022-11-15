package study63;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj23295 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int n, t; // 참가자 수 , 스터디 시간
		int[] array1, array2;

		n = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		array1 = new int[100001];
		array2 = new int[100001];

		for (int i = 1; i <= n; i++) {

			int k = Integer.parseInt(in.readLine());

			for (int j = 1; j <= k; j++) {
				st = new StringTokenizer(in.readLine(), " ");
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());

				array1[s]++;
				array1[e]--;

			}

		}

		for (int i = 1; i <= 100000; i++) {
			array1[i] += array1[i - 1];

		}

		array2[0] = array1[0];
		for (int i = 1; i <= 100000; i++) {
			array2[i] += (array2[i - 1] + array1[i]);
		}

		int ans = array2[t - 1];
		int start = 0;

		for (int i = 1; i <= 100000 - t; i++) {

			int s = i;
			int e = i + t;
			int sum = 0;

			sum -= array2[s - 1];
			sum += array2[e - 1];
			if (ans < sum) {
				start = s;
				ans = sum;
			}

		}

		System.out.println(start + " " + (start + t));

	}
}
