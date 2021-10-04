package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj19237 {

	static int dx[] = { 0, -1, 1, 0, 0 }; // 위 아래 왼쪽 오른쪽
	static int dy[] = { 0, 0, 0, -1, 1 };

	static class Smell {
		int count, number;

		Smell(int count, int number) {
			this.count = count;
			this.number = number;
		}

	}

	static class Shark {

		int x, y, dir, state; // 좌표 , 방향 , 번호 상태 0이면 살아있는거 1이면 죽은거

		Shark(int x, int y, int dir, int state) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.state = state;

		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		Smell check[][] = new Smell[n + 1][n + 1];
		int map[][] = new int[n + 1][n + 1];
		int direct[][][] = new int[m + 1][5][4];
		Shark list[] = new Shark[m + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine(), " ");

			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				check[i][j] = new Smell(0, 0);
				if (map[i][j] != 0) {

					check[i][j].count = k;
					check[i][j].number = map[i][j];

					int val = map[i][j];
					list[val] = new Shark(i, j, 0, 0);

				}
			}

		}

		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= m; i++) {
			list[i].dir = Integer.parseInt(st.nextToken());

		}

		for (int i = 1; i <= m; i++) {
			// 위 아래 왼쪽 오른쪽을 보고 있을때 방향의 우선순위
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(in.readLine(), " ");

				for (int z = 0; z <= 3; z++) {
					direct[i][j][z] = Integer.parseInt(st.nextToken());
				}

			}

		}

		int cnt = m; // 격자에 남아있는 상어의 개수 ( 1번 상어만 격자에 남아있는 경우 1이 됨)
		int time = 0;

		for (time = 1; time <= 1000; time++) {
			// 상어가 이동

			for (int i = 1; i <= m; i++) {
				if (list[i].state == 0) // 해당 상어가 존재한다면?
				{

					int x = list[i].x;
					int y = list[i].y;
					int dir = list[i].dir;

					map[x][y] = 0; // 움직이니 원래 위치에서 제거

					// 현재 보고있는 방향에 따른 우선순위대로 확인

					int flag = 0; // 이동할수있는 빈칸이 있는지?
					for (int j = 0; j <= 3; j++) {
						int nx = x + dx[direct[i][dir][j]];
						int ny = y + dy[direct[i][dir][j]];

						if (nx < 1 || nx > n || ny < 1 || ny > n || check[nx][ny].count != 0)
							continue;

						// 빈칸이 존재한다면?
						flag = 1;

						list[i].x = nx;
						list[i].y = ny;
						list[i].dir = direct[i][dir][j];
						break;
					}

					if (flag == 0) // 빈칸을 발견못했다면?
					{
						for (int j = 0; j <= 3; j++) {
							int nx = x + dx[direct[i][dir][j]];
							int ny = y + dy[direct[i][dir][j]];

							if (nx < 1 || nx > n || ny < 1 || ny > n || check[nx][ny].number != i)
								continue;

							// 내가 남긴 냄새가 있는칸으로 간다

							list[i].x = nx;
							list[i].y = ny;
							list[i].dir = direct[i][dir][j];
							break;
						}

					}

				}

			}

			// 기존에 기록되어있던 냄새를 -1씩
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (check[i][j].count >= 1) {
						check[i][j].count--;

						if (check[i][j].count == 0) {
							check[i][j].number = 0;
						}
					}

				}
			}

			// 새로운 냄새 추가 및 한곳에 겹치는 상어들 제거

			for (int i = 1; i <= m; i++) {
				if (list[i].state == 0) // 살아있다면?
				{
					int x = list[i].x;
					int y = list[i].y;

					if (map[x][y] == 0) // 해당 격자판에 아무도 없다면? 나를 기록
					{
						map[x][y] = i;

						check[x][y].count = k;
						check[x][y].number = i;

					}

					else // 누군가 있다면?
					{
						if (map[x][y] < i) // 나보자 작다면?
						{
							cnt--;
							list[i].state = 1;
						}

						else if (i < map[x][y]) // 내가 더 작다면?
						{
							cnt--;
							list[map[x][y]].state = 1;
							map[x][y] = i;
							check[x][y].count = k;
							check[x][y].number = i;
						}

					}

				}

			}

			if (cnt == 1) {
				break;
			}

		}

		if (cnt != 1) {
			System.out.println(-1);
		}

		else {
			System.out.println(time);
		}
	}

}
