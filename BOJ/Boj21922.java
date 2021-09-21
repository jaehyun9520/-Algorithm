package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Boj21922 {

	static int dx[] = { -1, 0, 1, 0 }; // 북동남서 (0 1 2 3 )
	static int dy[] = { 0, 1, 0, -1 };
	static int box3[] = { 1, 0, 3, 2 }; // 물건 3번 방향
	static int box4[] = { 3, 2, 1, 0 }; // 물건 4번 방향

	static int N, M;
	static int map[][]; //입력값
	static int check[][][];  // 방향별로 방문체크 
	static int wind[][];  // 바람이 지나가는곳 1로 체크 

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int ans = 0;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		List<int[]> aircon = new ArrayList<>();
		map = new int[N + 1][M + 1];
		check = new int[4][N + 1][M + 1];
		wind = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= M; j++) {

				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 9) {
					aircon.add(new int[] { i, j });
				}

			}
		}

		// 에어컨 한개씩 BFS로 확인
		Queue<int[]> Q = new LinkedList<>();

		for (int i = 0; i < aircon.size(); i++) {
			int[] now = aircon.get(i);

			wind[now[0]][now[1]] = 1; // 에어컨 

			for (int j = 0; j <= 3; j++) {
				check[j][now[0]][now[1]] = 1; // 상하좌우 바람 체크

				Q.offer(new int[] { now[0], now[1], j }); // 에어컨의 좌표와 방향

			}

			while (!Q.isEmpty()) {
				int list[] = Q.poll();

				int x = list[0];
				int y = list[1];
				int dir = list[2];

				int nx = x + dx[dir];
				int ny = y + dy[dir];

				if (nx < 1 || nx > N || ny < 1 || ny > M)
					continue;

				if (check[dir][nx][ny] == 0) {
					check[dir][nx][ny] = 1; // 해당 방향으로 방문한적이 있음
					wind[nx][ny] = 1; // 바람 부는곳으로 변경

					// 물건 1 2 3 4 에 따라서 다름

					if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 3 || map[nx][ny] == 4) 
					{

						if (map[nx][ny] == 1) {
							if (dir == 1 || dir == 3)// 1 3(동, 서 방향 ) 일경우
							{
								Q.offer(new int[] { nx, ny, 4 - dir });
							}

							else {
								Q.offer(new int[] { nx, ny, dir });
							}

						}

						else if (map[nx][ny] == 2) {
							if (dir == 0 || dir == 2) {  //0 2(북,남 방향)일 경우 
								Q.offer(new int[] { nx, ny, 2 - dir });

							}

							else {
								Q.offer(new int[] { nx, ny, dir });
							}

						}

						else if (map[nx][ny] == 3) {

							Q.offer(new int[] { nx, ny, box3[dir] });

						}

						else {

							Q.offer(new int[] { nx, ny, box4[dir] });

						}

					}

					else // 에어컨이거나 빈공간일 경우
					{
						Q.offer(new int[] { nx, ny, dir });

					}

				}

			}

		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {

				if (wind[i][j] == 1) {
					ans++;
				}

			}

		}

		System.out.println(ans);

	}

}
