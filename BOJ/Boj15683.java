package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15683 {

	static int N, M, cnt, sum, ans = 2147000, delete;
	static int map[][];
	static int cctv[][] = new int[9][4]; // 0 은 cctv종류 1 2 는 좌표 , 3은 방향
	static int tmp[][];
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		cnt = 1;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine(), " ");

			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 0)
					sum++;

				if (map[i][j] >= 1 && map[i][j] <= 5) {
					cctv[cnt][0] = map[i][j]; // cctv종류와 위치 기록
					cctv[cnt][1] = i;
					cctv[cnt][2] = j;
					cnt++;
				}

			}

		}
		cnt--; // cctv의 개수

		simulation(0, 1);
		System.out.println(ans);
		// 입력값 받기
	}

	static void simulation(int count, int index) {

	
		if (count == cnt) // cctv의 방향을 다 설정했으면 시뮬레이션 진행
		{

			
			tmp = new int[N + 1][M + 1];  //복사본 
			delete = 0; // 삭제된 갯수

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					tmp[i][j] = map[i][j];
				}
			}

			

			for (int i = 1; i <= cnt; i++) {
				int x = cctv[i][1];
				int y = cctv[i][2];
				if (cctv[i][0] == 1) // 1번 카메라
				{
					move(x, y, cctv[i][3]); 
				}

				else if (cctv[i][0] == 2) // 2번 카메라 
				{
					move(x, y, cctv[i][3]); 
					move(x, y, cctv[i][3] + 2); 
				}

				else if (cctv[i][0] == 3) // 3번 카메라
				{
					int case1 = cctv[i][3];
					int case2 = cctv[i][3] + 1;
					if (case2 == 4)
						case2 = 0;
					move(x, y, case1); 
					move(x, y, case2); 

				} else if (cctv[i][0] == 4) // 4번 카메라
				{
					for (int k = 0; k <= 3; k++) {
						if (k != cctv[i][3]) {
							move(x, y, k);
						}
					}
				}
				else // 5번 카메라
				{

					for (int k = 0; k <= 3; k++) {
						move(x, y, k); 
					}
				}
			}

			ans = Math.min(sum - delete, ans);  
		}

		else {  //cctv들의 방향 설정 
			for (int i = index; i <= cnt; i++) {

				if (cctv[i][0] == 1 || cctv[i][0] == 3 || cctv[i][0] == 4) //1 3 4번 cctv는 4가지 경우의수
				{
					for (int j = 0; j <= 3; j++) {
						cctv[i][3] = j;
						simulation(count + 1, i + 1);
					}
				} else if (cctv[i][0] == 2) {  //2번 cctv는 2가지 경우의수 
					for (int j = 0; j <= 1; j++) {
						cctv[i][3] = j;
						simulation(count + 1, i + 1);
					}
				}

				else {
					simulation(count + 1, i + 1);
				}

			}

		}

	}

	static void move(int x, int y, int direct) {  // x y 좌표를 direct방향으로 한칸씩 이동
		while (true) {
			x = x + dx[direct];
			y = y + dy[direct];
			if (x == 0 || x == N + 1 || y == 0 || y == M + 1) { 

				break;
			} else if (tmp[x][y] == 6) { 
				break;
			} else if (tmp[x][y] == 0) 
			{

				tmp[x][y] = -1;
				delete++;
			}
		}
	}

}
