package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj19238 {

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };

	static class State implements Comparable<State> {

		int x, y, dis, fuel, flag; // 좌표, 움직인칸, 연료, 사람을 태우고있는지(0이면 x 1~m이면 O)

		State(int x, int y, int dis, int fuel, int flag) {
			this.x = x;
			this.y = y;
			this.dis = dis;
			this.fuel = fuel;
			this.flag = flag;

		}

		@Override
		// 최단거리(dis) -> 행번호가 작은순-> 열번호가 작은순
		public int compareTo(State o) {

			if (dis == o.dis) {
				if (x == o.x) {

					return y - o.y;

				}

				return x - o.x;

			}

			return dis - o.dis;

		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int ans = -1;
		int cnt = 0;
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int F = Integer.parseInt(st.nextToken());

		int map[][] = new int[n + 1][n + 1];
		boolean check[][] = new boolean[n + 1][n + 1];
		int people[][] = new int[m + 1][2]; // 도착지 좌표 출발지는 맵에 직접 기록

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					map[i][j] = -1;
			}
		}

		// 택시 시작위치 맨처음 시작위치에 바로 손님이 있을수있는가?
		st = new StringTokenizer(in.readLine(), " ");
		int sx = Integer.parseInt(st.nextToken());
		int sy = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			map[a][b] = i; // 출발지 기록

			people[i][0] = c;
			people[i][1] = d; // 도착지 기록

		}

		Queue<State> Q = new LinkedList<>();
		PriorityQueue<State> PQ = new PriorityQueue<>();

		check[sx][sy] = true;
		// 초기 출발지에 사람이 있는지 있다면 태운채로 시작
		if (map[sx][sy] != 0 && map[sx][sy] != -1) {
			// 태운채로

			Q.offer(new State(sx, sy, 0, F, map[sx][sy]));
			map[sx][sy] = 0;

		}

		else {
			Q.offer(new State(sx, sy, 0, F, 0)); // 좌표 움직인칸 , 연료, 사람을 태우고 있는지?
		}
		// 없다면 그냥 시작

		while (!PQ.isEmpty() || !Q.isEmpty()) {

			int first = 0; // 최초 승객

			while (!Q.isEmpty()) {
				State now = Q.poll();

				int x = now.x;
				int y = now.y;
				int dis = now.dis;
				int fuel = now.fuel;
				int flag = now.flag;

				if ((first != 0 && dis + 1 > first) || fuel == 0) // 맨처음 승객을 발견한 거리보다 더 가려고 한다면? 중단
					continue; // 또는 연료가 0이라면 중단

				loop: for (int i = 0; i <= 3; i++) {

					int nx = x + dx[i];
					int ny = y + dy[i];

					if (nx < 1 || nx > n || ny < 1 || ny > n || check[nx][ny] != false || map[nx][ny] == -1)
						continue;

					if (flag == 0) // 승객이 없다면?
					{
						if (map[nx][ny] == 0) // 빈곳
						{
							check[nx][ny] = true;

							Q.offer(new State(nx, ny, dis + 1, fuel - 1, flag));
						}

						else // 승객이 위치한곳
						{
							if (first == 0) // 맨처음 승객이라면?
							{
								PQ.offer(new State(nx, ny, dis + 1, fuel - 1, map[nx][ny]));
								first = dis + 1;

							}

							else {
								if (dis + 1 <= PQ.peek().dis) // 다음 승객이라면?
								{
									PQ.offer(new State(nx, ny, dis + 1, fuel - 1, map[nx][ny]));
								}
							}
						}
					}

					else // 사람이 타고있는경우
					{

						if (nx == people[flag][0] && ny == people[flag][1]) // 도착지?
						{

							cnt++;
							if (cnt == m) {
								ans = 2 * (dis + 1) + fuel - 1;
							}
							// 초기화도 해줘야함
							check = new boolean[n + 1][n + 1];
							check[nx][ny] = true;
							// 이전에 현재 타고있던 사람의 도착지를 찾기 위해 들어있던 큐의 내용들은 제거
							Q.clear();
							// 도착지이면서 다른사람의 출발지인 경우
							if (map[nx][ny] != 0) {
								Q.offer(new State(nx, ny, 0, fuel - 1 + 2 * (dis + 1), map[nx][ny]));
								map[nx][ny] = 0; // 태웠으니 0으로 표시

							}

							else {

								first = 0;
								Q.offer(new State(nx, ny, 0, fuel - 1 + 2 * (dis + 1), 0));
							}

							break loop;

						}

						else {
							check[nx][ny] = true;

							Q.offer(new State(nx, ny, dis + 1, fuel - 1, flag));
						}

					}

				}

			}

			if (!PQ.isEmpty()) {
				State next = PQ.poll();
				PQ.clear();

				int x = next.x;
				int y = next.y;

				int dis = 0;
				int fuel = next.fuel;
				int flag = next.flag;

				check = new boolean[n + 1][n + 1];
				check[x][y] = true;
				map[x][y] = 0; // 승객을 태웠으니 맵에서는 제거
				Q.offer(new State(x, y, 0, fuel, flag));
			}

		}

		System.out.println(ans);

	}

}
