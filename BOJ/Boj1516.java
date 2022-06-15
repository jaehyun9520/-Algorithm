package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1516_게임개발 {

	public static void main(String[] args) throws IOException {

		int n;
		List<Integer> list1[]; // 내 뒤에 오는 건물
		List<Integer> list2[]; // 내 전에 오는 건물
		int time[];
		int dp[];
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());
		list1 = new ArrayList[n + 1];
		list2 = new ArrayList[n + 1];
		time = new int[n + 1];
		dp = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			list1[i] = new ArrayList<>();
			list2[i] = new ArrayList<>();
		}

		ArrayList<Integer> start = new ArrayList<>();

		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int t = Integer.parseInt(st.nextToken());
			time[i] = t;
			while (st.hasMoreTokens()) {
				int num = Integer.parseInt(st.nextToken());

				if (num != -1) {
					list2[i].add(num);
					list1[num].add(i);
				}
			}

			if (list2[i].size() == 0) {
				start.add(i);
			}

		}
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < start.size(); i++) {
			int num = start.get(i);
			dp[num] = time[num];
			q.add(num);
		}

		while (!q.isEmpty()) {
			int now = q.poll();

			for (int i = 0; i < list1[now].size(); i++) {

				int next = list1[now].get(i);

				if (dp[next] < time[next] + dp[now]) {
					dp[next] = time[next] + dp[now];
				}
				list2[next].remove((Integer) now);

				if (list2[next].size() == 0) {
					q.add(next);
				}

			}

		}

		for (int i = 1; i <= n; i++) {
			System.out.println(dp[i]);
		}

	}
}
