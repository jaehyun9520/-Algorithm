package a0813;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj2479_경로찾기 {

	static class Code {
		int num;
		String path;

		Code(int num, String path) {
			this.num = num;
			this.path = path;
		}

	}

	static int n, k;
	static ArrayList<Integer> list[];
	static int start, end;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		list = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();

		}

		int infor[][] = new int[n + 1][k];

		for (int i = 1; i <= n; i++) {
			String input = in.readLine();

			for (int j = 0; j < k; j++) {

				infor[i][j] = input.charAt(j) - 48;
			}

		}

		st = new StringTokenizer(in.readLine(), " ");
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= n - 1; i++) {

			for (int j = i + 1; j <= n; j++) {

				int cnt = 0;
				for (int z = 0; z < k; z++) {
					if (infor[i][z] != infor[j][z]) {
						cnt++;
						if (cnt >= 2)
							break;

					}

				}
				
				if(cnt==1)
				{
					list[i].add(j);
					list[j].add(i);
				}

			}

		}

		bfs();

	}

	static void bfs() {

		boolean visited[] = new boolean[n + 1];
		String path = null;
		visited[start] = true;

		Queue<Code> q = new LinkedList<>();

		q.add(new Code(start, "" + start));

		loop: while (!q.isEmpty()) {

			Code now = q.poll();
			int num = now.num;


			for (int i = 0; i < list[num].size(); i++) {
				int next = list[num].get(i);

				if (visited[next] == false) {
					visited[next] = true;

					if (next == end) {
						path = now.path + " " + end;
						break loop;
					}

					q.add(new Code(next, now.path + " " + next));

				}

			}

		}

		if (path == null) {
			System.out.println(-1);
		} else {

			System.out.println(path);
		}

	}
}
