package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2931_가스관 {

	static char blockNumber[] = { '0', '1', '2', '3', '4', '|', '-', '+' };
	static int block[][] = { { 0, 0, 0, 0 }, { 1, 0, 0, 1 }, { 0, 1, 0, 1 }, { 0, 1, 1, 0 }, { 1, 0, 1, 0 },
			{ 1, 1, 0, 0 }, { 0, 0, 1, 1 }, { 1, 1, 1, 1 } }; // 각 블록별 가능한 이동방향

	// 남 북 서 동
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	static int connect[] = { 1, 0, 3, 2 }; // 연결가능한 방향

	static int visited[][];
	static int map[][];
	static int r, c;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		visited = new int[r + 1][c + 1];
		map = new int[r + 1][c + 1];
		int sx = 0, sy = 0;
		for (int i = 1; i <= r; i++) {
			String input = in.readLine();
			for (int j = 1; j <= c; j++) {
				int val = input.charAt(j - 1);

				if (val == '|')
					map[i][j] = 5;
				else if (val == '-')
					map[i][j] = 6;
				else if (val == '+')
					map[i][j] = 7;

				else if (val >= 48 && val <= 65) {
					map[i][j] = val - 48;
				} else
					map[i][j] = val;

				if (map[i][j] == 'M') {
					sx = i;
					sy = j;
				}
			}
		}

		// M에 연결되어 있는 블록 찾기 (딱 하나의 블록과만 인접해있다)
		for (int i = 0; i <= 3; i++) {
			int nx = sx + dx[i];
			int ny = sy + dy[i];

			if (nx < 1 || nx > r || ny < 1 || ny > c)
				continue;

			if (map[nx][ny] >= 1 && map[nx][ny] <= 7) {
				// 블록을 발견했다면? 깊이우선탐색 시작
				visited[nx][ny] = 1;
				DFS(nx, ny, i, 0, 0, 0);
				break;
			}

		}

	}

	static void DFS(int x, int y, int predir, int used, int usedx, int usedy) {

		predir = connect[predir]; // 이 방향은 바로 직전 칸이므로 다시 방문 x

		for (int i = 0; i <= 3; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			int dir = connect[i];
			if (nx < 1 || nx > r || ny < 1 || ny > c || predir == i || block[map[x][y]][i] == 0)
				continue;

			// 호환성이 맞는 블록이라면?
			if (map[nx][ny] >= 1 && map[nx][ny] <= 7 && block[map[nx][ny]][dir] == 1) {

				if (map[x][y] == 7 && connect[predir] != i) // 내가 +모양 블록인 경우 이전에 수직으로 흘렀다면 나도 수직으로만 갈수있다
					continue; // 그렇다면 생략

				visited[nx][ny]++;
				DFS(nx, ny, i, used, usedx, usedy);
			}

			else if (map[nx][ny] == 'Z') // 목적지라면?
			{
				System.out.print(usedx + " " + usedy + " ");
				System.out.println(blockNumber[used]);
			}

			else if (map[nx][ny] == '.') // 빈칸이라면?
			{
				// 현재 블록이 연결시켜야 하는 칸이 몇개인지 카운트
				int cnt = blockCount(nx, ny);

				int number = blockFind(cnt, nx, ny); // 어떤 블록의 모양이 맞는지 찾기

				map[nx][ny] = number;

				DFS(nx, ny, i, number, nx, ny);

			}

		}

	}

	static int blockCount(int x, int y) {
		int cnt = 0;
		for (int i = 0; i <= 3; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			int dir = connect[i];

			if (nx < 1 || nx > r || ny < 1 || ny > c)
				continue;

			if (map[nx][ny] >= 1 && map[nx][ny] <= 7 && block[map[nx][ny]][dir] == 1) {
				cnt++;
			}

		}

		return cnt;

	}

	static int blockFind(int cnt, int x, int y) {
		int number = 0;
		loop: for (int i = 1; i <= 7; i++) // 1번부터 7번 블록중에 어떤 블록이 충족하는지 확인
		{
			int count = 0;

			for (int j = 0; j <= 3; j++) {
				int nx = x + dx[j];
				int ny = y + dy[j];

				int dir = connect[j];

				if (nx < 1 || nx > r || ny < 1 || ny > c || block[i][j] == 0)
					continue;

				if (map[nx][ny] >= 1 && map[nx][ny] <= 7 && block[map[nx][ny]][dir] == 1) {
					count++;
				}

			}
			if (count == cnt) {

				number = i;

				break loop;
			}
		}

		return number;
	}

}
