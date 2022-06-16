package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Boj2295_세수의합 {

	static int n;
	static List<Integer> list = new ArrayList<>();
	static HashSet<Integer> set = new HashSet<>();

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());

		for (int i = 1; i <= n; i++) {
			list.add(Integer.parseInt(in.readLine()));
		}

		Collections.sort(list);

		comb(0, 0, 0);

		loop: for (int i = list.size() - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				int val = list.get(i) - list.get(j);

				if (set.contains(val)) {
					System.out.println(list.get(i));
					break loop;
				}

			}

		}

	}

	static void comb(int index, int cnt, int sum) {
		if (cnt == 2) {
			set.add(sum);
		}

		else {

			for (int i = index; i < list.size(); i++) {
				comb(i, cnt + 1, sum + list.get(i));

			}

		}

	}
}
