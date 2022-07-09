package study46;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj5214_환승 {

	static ArrayList<Integer> station[]; // 각 역이 쓸 수 있는 하이퍼 튜브
	static ArrayList<Integer> hyperTube[]; // 각 하이퍼 튜브에 연결되어 있는 역들
	static int n, k, m;
	static boolean svisited[];
	static boolean hvisited[];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		svisited = new boolean[n + 1];
		hvisited = new boolean[m + 1];
		station = new ArrayList[n + 1];
		hyperTube = new ArrayList[m + 1];
		for (int i = 1; i <= n; i++) {
			station[i] = new ArrayList<>();
		}

		for (int i = 1; i <= m; i++) {
			hyperTube[i] = new ArrayList<>();
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= k; j++) {
				int num = Integer.parseInt(st.nextToken());

				station[num].add(i);
				hyperTube[i].add(num);
			}
		}

		System.out.println(bfs());
	}

	static int bfs() {
		int ans = -1;

		Queue<int[]> q = new LinkedList<>();

		svisited[1] = true;

		q.add(new int[] { 1, 1 }); // 번호와 이동횟수

		while (!q.isEmpty()) {
			int now[] = q.poll();
			int num = now[0];
			int cnt = now[1];

			if (num == n) {
				ans = cnt;
				break;
			}

			for (int i = 0; i < station[num].size(); i++) {
				int tubeNum = station[num].get(i);

				if (hvisited[tubeNum] == false) {
					hvisited[tubeNum] = true;

					for (int j = 0; j < hyperTube[tubeNum].size(); j++) {
						int stationNum = hyperTube[tubeNum].get(j);
						if (svisited[stationNum] == false) {
							svisited[stationNum] = true;

							q.add(new int[] { stationNum, cnt + 1 });

						}

					}

				}

			}

		}

		return ans;

	}
}
