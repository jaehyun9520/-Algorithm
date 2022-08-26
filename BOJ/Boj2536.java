package study52;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2536 {
	static class Bus {
		int type, sr, er, sc, ec; // 0은 수평 1은 수직

		Bus(int type, int sr, int er, int sc, int ec) {
			this.type = type;
			this.sr = sr;
			this.er = er;
			this.sc = sc;
			this.ec = ec;
		}
	}

	static int m, n, k;
	static ArrayList<Integer> bus[];
	static Bus[] busInfor;
	static boolean connect[][];
	static int startR, startC, endR, endC;
	static boolean destination[];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		k = Integer.parseInt(in.readLine());

		connect = new boolean[k + 1][k + 1];
		busInfor = new Bus[k + 1];

		destination = new boolean[k + 1];

		for (int i = 1; i <= k; i++) {

			st = new StringTokenizer(in.readLine(), " ");

			int num = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			int sr = Integer.parseInt(st.nextToken());
			int ec = Integer.parseInt(st.nextToken());
			int er = Integer.parseInt(st.nextToken());
			int type = 0;

			if (sc == ec) {
				type = 1;
			}
			busInfor[num] = new Bus(type, Integer.min(sr, er), Integer.max(sr, er), Integer.min(sc, ec),
					Integer.max(sc, ec)); // 정보 저장
		}

		for (int i = 1; i < k; i++) {
			for (int j = i + 1; j <= k; j++) {

				if (connectCheck(i, j)) {

					connect[i][j] = true;
					connect[j][i] = true;

				}
			}
		}

		st = new StringTokenizer(in.readLine(), " ");
		startC = Integer.parseInt(st.nextToken());
		startR = Integer.parseInt(st.nextToken());
		endC = Integer.parseInt(st.nextToken());
		endR = Integer.parseInt(st.nextToken());

		System.out.println(bfs());

	}

	static int bfs() {
		int ans = 0;
		boolean visited[] = new boolean[k + 1];
		Queue<int[]> q = new LinkedList<>();

		for (int i = 1; i <= k; i++) {

			if (busInfor[i].sr <= startR && startR <= busInfor[i].er && busInfor[i].sc <= startC
					&& startC <= busInfor[i].ec) {
				visited[i] = true;
				q.add(new int[] { i, 1 });
			}

			if (busInfor[i].sr <= endR && endR <= busInfor[i].er && busInfor[i].sc <= endC
					&& endC <= busInfor[i].ec) {
				destination[i] = true;
			}
		}

		while (!q.isEmpty()) {
			int now[] = q.poll();
			int num = now[0];
			int cnt = now[1];

			if (destination[num] == true) {
				ans = cnt;
				break;
			}

			for (int i = 1; i <= k; i++) {
				if (connect[num][i] && !visited[i]) {
					visited[i] = true;
					q.add(new int[] { i, cnt + 1 });
				}
			}

		}

		return ans;
	}

	static boolean connectCheck(int bus1, int bus2) {
		boolean val = false;

		int type1 = busInfor[bus1].type;
		int type2 = busInfor[bus2].type;

		int sr1 = busInfor[bus1].sr;
		int er1 = busInfor[bus1].er;
		int sc1 = busInfor[bus1].sc;
		int ec1 = busInfor[bus1].ec;

		int sr2 = busInfor[bus2].sr;
		int er2 = busInfor[bus2].er;
		int sc2 = busInfor[bus2].sc;
		int ec2 = busInfor[bus2].ec;
		if (type1 == 0) // 수평
		{
			if (type2 == 0) // 수평
			{
				if (sr1 == sr2 // 열이 같은지
						&& ((sc1 <= sc2 && sc2 <= ec1) || (sc1 <= ec2 && ec2 <= ec1) || (sc2 <= sc1 && sc1 <= ec2))) {
					val = true;
				}
			}

			else { // 수평 수직
				if ((sc1 <= sc2 && sc2 <= ec1) && (sr2 <= sr1 && sr1 <= er2)) {
					val = true;
				}
			}
		} else {

			if (type2 == 1) { // 수직 수직

				if (sc1 == sc2
						&& ((sr1 <= sr2 && sr2 <= er1) || (sr1 <= er2 && er2 <= er1) || (sr2 <= sr1 && sr1 <= er2))) {
					val = true;
				}

			} else { // 수직 수평
				if ((sr1 <= sr2 && sr2 <= er1) && (sc2 <= sc1 && sc1 <= ec2)) {
					val = true;
				}
			}
		}
		return val;
	}

}