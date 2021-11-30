package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2660_회장뽑기 {

	static int n;
	static ArrayList<Integer> list[];
	static int score = Integer.MAX_VALUE;
	static ArrayList<Integer> ans = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		n = Integer.parseInt(in.readLine());

		list = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) // 각 후보들의 연결리스트 생성
		{
			list[i] = new ArrayList<>();
		}

		int val1 = 0, val2 = 0;

		while (true) {
			st = new StringTokenizer(in.readLine(), " ");

			val1 = Integer.parseInt(st.nextToken());
			val2 = Integer.parseInt(st.nextToken());

			if (val1 == -1 && val2 == -1) {
				break;
			} else {

				list[val1].add(val2);
				list[val2].add(val1);

			}
		}

		for (int i = 1; i <= n; i++) {

			BFS(i);
		}

		Collections.sort(ans);

		sb.append(score).append(" ");
		sb.append(ans.size()).append("\n");

		for (int i = 0; i < ans.size(); i++) {
			sb.append(ans.get(i)).append(" ");
		}

		System.out.println(sb.toString());

	}

	static void BFS(int start) {
		int check[] = new int[n + 1];

		check[start] = 1;

		Queue<int[]> queue = new LinkedList<>();

		queue.offer(new int[] { start, 0 });
		int val = 0;

		while (!queue.isEmpty()) {

			int now[] = queue.poll();

			int number = now[0];
			val = now[1];

			for (int i = 0; i < list[number].size(); i++) {
				int next = list[number].get(i);

				if (check[next] == 0) {

					check[next] = 1;
					queue.offer(new int[] { next, val + 1 });
				}
			}

		}

		if (val < score) // 이전까지의 점수보다 현재 점수가 더 작다면 갱신
		{
			score = val;
			ans.clear();
			ans.add(start);
		}

		else if (val == score) {
			ans.add(start);
		}

	}

}
