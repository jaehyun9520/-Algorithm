package a0717;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj15970_화살표그리기 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(in.readLine());
		int ans = 0;
		ArrayList<Integer> list[] = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int index = Integer.parseInt(st.nextToken());
			int color = Integer.parseInt(st.nextToken());

			list[color].add(index);
		}

		for (int i = 1; i <= n; i++) {
			Collections.sort(list[i]);

			System.out.println("컬러="+i);
			
			for (int j = 0; j < list[i].size(); j++) {
				int index = list[i].get(j);

				System.out.println("현재 위치="+index);
				int val = Integer.MAX_VALUE;

				if (j - 1 >= 0) {
					val = index - list[i].get(j - 1);
				}

				if (j + 1 < list[i].size()) {
					val = Integer.min(val, list[i].get(j + 1) - index);
				}

				ans += val;
			}

		}

		System.out.println(ans);
	}
}
