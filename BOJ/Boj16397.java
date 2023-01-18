package 알고리즘스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16397 {

	static int n, t, g;
	static boolean visited[] = new boolean[100001];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());

		int ans=bfs();

		if(ans==-1) {
			System.out.println("ANG");
		}
		
		else {
			System.out.println(ans);
		}
	}

	static int bfs() {

		int ans = -1;
		Queue<int[]> q = new LinkedList<>();

		visited[n] = true;
		q.add(new int[] { n, 0 });

		while (!q.isEmpty()) {

			int[] now = q.poll();

			int val = now[0];
			int cnt = now[1];
			int next = 0;


			
			if (val == g) {
				ans = cnt;
				break;
			}

			for (int i = 1; i <= 2; i++) {

				if (i == 1)
					next = val + 1;

				else
					next = val * 2;

				// 99,999를 넘어가는 경우
				
				if (next > 99999) continue;
				
				// b버튼인 경우 *2에서 0이 아닌 가장 높은 자릿수의 숫자 -1 을 해야한다.
				if (i == 2&& next!=0) {

					int length = ("" + next).length();

					next = (int) (next - Math.pow(10, length - 1));

				}

				
				
				if (!visited[next]&&cnt<t) {
					
					visited[next] = true;

					q.add(new int[] { next, cnt + 1 });

				}

			}

		}
		return ans;
	}
}
