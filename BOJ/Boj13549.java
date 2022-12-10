package study67;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj13549 {

	static boolean visited[] = new boolean[100001];
	static Queue<int[]> q1 = new LinkedList<>();
	static Queue<int[]> q2 = new LinkedList<>();
	static int n, k, ans = 0;
	static int move[] = { 1, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		bfs();
		System.out.println(ans);

	}

	static void bfs() {

		Queue<int[]> q = new LinkedList<>();

		// 시작지점에서 *2로 방문할 수 있는 곳을 모두 다 방문처리하고 큐에 넣어준다
		for (int i = n; i <= 100000; i *= 2) {

			if(!visited[i]) {
				
			visited[i] = true;
			q.add(new int[] { i, 0 });
			if(i==0) break;
			}
		}

		while (!q.isEmpty()) {

			int now[] = q.poll();

			
			int loc = now[0];
			int time = now[1];

			if (loc == k) {

				ans = time;
				break;
			}

			for (int i = 0; i <= 1; i++) {

				int next = loc + move[i];

				if (next < 0 || next > 100000)
					continue;

				// 한번도 방문한적이 없으면?
				if (!visited[next]) {

					for (int j = next; j <= 100000; j *= 2) {

						if (!visited[j]) {

							
							visited[j] = true;
							q.add(new int[] { j, time + 1 });
							
							if(j==0) break;

						}

					}

				}

			}

		}

	}
}
