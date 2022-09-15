package study55;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj2143 {

	static int t;
	static int n, m;
	static ArrayList<Integer> listA;
	static ArrayList<Integer> listB;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		long ans = 0;
		t = Integer.parseInt(in.readLine());
		n = Integer.parseInt(in.readLine());
		int a[] = new int[n];

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		m = Integer.parseInt(in.readLine());
		int b[] = new int[m];

		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < m; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}

		listA = sum(a, n);
		listB = sum(b, m);
		Collections.sort(listB);
		for (int i = 0; i < listA.size(); i++) {
			int val = t - listA.get(i);
			int start = lowerbound(val);
			int end = upperbound(val);
			ans += (end - start);
		}
		System.out.println(ans);
	}

	// val와 같거나 큰 첫번째 수의 위치를 반환
	static int lowerbound(int val) {
		int low = 0, high = listB.size(), mid = 0;

		while (low < high) {
			mid = (low + high) / 2;

			if (listB.get(mid) >= val) {
				high = mid;
			}
			else {
				low = mid + 1;
			}
		}
		return high;

	}
	static int upperbound(int val) {
		int low = 0, high = listB.size(), mid = 0;

		while (low < high) {
			mid = (low + high) / 2;
			if (listB.get(mid) > val) {
				high = mid;
			}

			else {
				low = mid + 1;
			}

		}
		return high;
	}

	static ArrayList<Integer> sum(int array[], int length) {
		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 0; i < length; i++) {
			list.add(array[i]);
			int sum = array[i];
			for (int j = i + 1; j < length; j++) {
				sum += array[j];
				list.add(sum);
			}
		}

		return list;
	}
}
