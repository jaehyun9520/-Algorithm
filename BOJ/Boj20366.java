package study51;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj20366_같이눈사람만들래 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		ArrayList<Integer> list = new ArrayList<>();
		int ans = Integer.MAX_VALUE;
		boolean used[] = new boolean[n];
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}

		Collections.sort(list);
		
		for (int i = 0; i < n - 1; i++) {
			used[i] = true;
			for (int j = i + 1; j < n; j++) {
				used[j] = true;
				int val = list.get(i) + list.get(j);

				int s = 0;
				int e = list.size() - 1;

				while (s < e) {
					int sum = list.get(s) + list.get(e);

					if (!used[s] && !used[e]) {
						ans=Integer.min(Math.abs(val - sum), ans);
					}

					if (val < sum) {
						e--;
					}

					else {
						s++;
					}

				}

				used[j] = false;
			}
			used[i] = false;

		}

		System.out.println(ans);
	}
}
