package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj23290_마법사상어와복제 {

	// 물고기 클래스
	static class Fish {

		int x, y, dir;

		Fish(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

	}

	static int dx[] = { 0, 0, -1, -1, -1, 0, 1, 1, 1 }; // 물고기 방향 이동
	static int dy[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

	// 상어이동 상 좌 하 우 (1 ,2 ,3, 4)
	static int dx2[] = { 0, -1, 0, 1, 0 };
	static int dy2[] = { 0, 0, -1, 0, 1 };

	static ArrayList[][] map = new ArrayList[5][5]; // 물고기 개수 체크
	static int[][] smell = new int[5][5]; // 물고기 냄새 기록용
	static ArrayList<Fish> fish; // 복제될 물고기 관리
	static int sx, sy; // 상어의 위치
	static int m, s;// 물고기수와 진행될 횟수
	static int check[][] = new int[5][5];

	// 0은 후보들
	static int smove[][] = new int[2][4]; // 1은 현재까지 가능한 이동방법중에서 제외되는 물고기의 수가 가장 많은 방법

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		m = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());

		fish = new ArrayList<>();

		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				map[i][j] = new ArrayList<Integer>();

			}
		}

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(in.readLine(), " ");

			int x, y, dir;

			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());

			fish.add(new Fish(x, y, dir));
		}
		st = new StringTokenizer(in.readLine(), " ");

		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= s; i++) {

			// 2.모든 물고기가 한칸씩 이동
			fishMove();

			for (int j = 0; j <= 3; j++) {
				smove[1][j] = 0;
				smove[0][j] = 0;
			}

			sharkMove(sx, sy, 0, 0);

			remove(); // 냄새 제거

			for (int j = 1; j <= 3; j++) {
				sx = sx + dx2[smove[1][j]];
				sy = sy + dy2[smove[1][j]];

				System.out.println("sx=" + sx + "sy=" + sy);

				if (map[sx][sy].size() != 0) {
					smell[sx][sy] = 2;

					map[sx][sy].clear();
				}
			}

			// 살아남은 물고기들은 fish 리스트에 넣어주기

			for (int j = 1; j <= 4; j++) {
				for (int k = 1; k <= 4; k++) {
					if (map[j][k].size() != 0) {

						for (int z = 0; z < map[j][k].size(); z++) {
							int dir = (int) map[j][k].get(z);

							fish.add(new Fish(j, k, dir));
						}
						map[j][k].clear();

					}
				}
			}

		}

		System.out.println(fish.size());

	}

	static void fishMove() {
		for (int i = 0; i < fish.size(); i++) {

			Fish now = fish.get(i);

			int x = now.x;
			int y = now.y;
			int dir = now.dir;
			int cnt = 0;
			// 현재 방향부터 반시계방향으로 돌면서 이동가는한 공간이 있는지 확인

			while (true) {

				int nx = x + dx[dir];
				int ny = y + dy[dir];

				if (nx >= 1 && nx <= 4 && ny >= 1 && ny <= 4 && smell[nx][ny] == 0 && !(nx == sx && ny == sy)) // 가려는
																												// 위치에
																												// 물고기
																												// 냄새도
																												// 없고
																												// 상어도
																												// 위치하지
																												// 않는다면?
				{

					x = nx;
					y = ny;
					break;
				}

				else {
					dir--;
					cnt++;

					if (dir == 0)
						dir = 8;

					if (cnt == 8)
						break; // 다시 한바퀴 돌아서 원래 방향으로 온것

				}
			}

			map[x][y].add(dir);

		}
	}

	static void sharkMove(int x, int y, int move, int cnt) { // 이동횟수 (최대 3번) , 현재까지 제거한 상어의 개수

		if (move == 3) {

			if (smove[1][0] < cnt) // 제거한 횟수가 더 많다면 갱신
			{
				smove[1][0] = cnt;

				for (int i = 1; i <= 3; i++) {
					smove[1][i] = smove[0][i];

				}

			}

			else if (smove[1][0] == cnt) {
				int pre = smove[1][1] * 100 + smove[1][2] * 10 + smove[1][3];
				int now = smove[0][1] * 100 + smove[0][2] * 10 + smove[0][3];

				if ((pre > now) || pre == 0) // 이전값보다 현재값이 사전순으로 앞선다면 갱신
				{

					for (int i = 1; i <= 3; i++) {
						smove[1][i] = smove[0][i];
					}

				}

			}

		}

		else {

			for (int i = 1; i <= 4; i++) {
				int nx = x + dx2[i];
				int ny = y + dy2[i];

				if (nx < 1 || nx > 4 || ny < 1 || ny > 4)
					continue;

				System.out.println("방향은?" + i);
				System.out.println("nx=" + nx + "ny=" + ny);

				smove[0][move + 1] = i;
				if (check[nx][ny] == 0) {
					check[nx][ny] = 1;
					sharkMove(nx, ny, move + 1, cnt + map[nx][ny].size());
					check[nx][ny] = 0;
				}

				else {

					sharkMove(nx, ny, move + 1, cnt);
				}

			}

		}

	}

	static void remove() {

		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				if (smell[i][j] != 0) {
					smell[i][j]--;
				}
			}
		}

	}

}