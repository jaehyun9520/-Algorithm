package a0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Boj3678_카탄의개척자 {

	static int dx[] = { -1, -2, -1, 1, 2, 1 }; // 중앙 정육면체 기준 주변 6개의 정육면체 위치
	static int dy[] = { 1, 0, -1, -1, 0, 1 };

	static int preLoc[] = { 5, 0, 1, 2, 3, 4 }; // 주변 정육면체의 이전 위치 (중앙 정육면체와 계산하면 알 수 있다)

	static int map[][] = new int[3001][3001];

	static int tileNum[][] = new int[15001][2]; // 1번 타일 넘버가 무엇인지?
	static boolean filled[] = new boolean[10001]; // 주변 6면이 다 채워졌는지?
	static int usedNumberCnt[] = new int[6];
	static int n = 3;

	static class Resource implements Comparable<Resource> {
		int number, usedCnt;

		Resource(int number, int usedCnt) {
			this.number = number;
			this.usedCnt = usedCnt;
		}

		@Override
		public int compareTo(Resource o) {
			if (this.usedCnt == o.usedCnt) {
				return this.number - o.number;

			}

			return this.usedCnt - o.usedCnt;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		simulation();

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(in.readLine());

		for (int i = 1; i <= t; i++) {
			int num = Integer.parseInt(in.readLine());

			sb.append(map[tileNum[num][0]][tileNum[num][1]] + "\n");
		}

		System.out.println(sb.toString());
	}

	static void simulation() {
		int nowTileNum = 1;

		tileNum[1][0] = 1501;
		tileNum[1][1] = 1501;
		usedNumberCnt[1] = 1;
		usedNumberCnt[2] = 1;
		tileNum[2][0] = 1500;
		tileNum[2][1] = 1502;

		map[1501][1501] = 1;
		map[1500][1502] = 2;

		while (nowTileNum <= 10000 && n <= 10000) {
			fill(nowTileNum);
			nowTileNum++;
		}

	}

	static void fill(int tile) {

		int x = tileNum[tile][0];
		int y = tileNum[tile][1];
		while (true) {
			boolean flag = true;

			for (int i = 0; i <= 5; i++) {

				int nx = x + dx[i];
				int ny = y + dy[i];

				if (map[nx][ny] == 0) {
					flag = false;

					int prex = x + dx[preLoc[i]];
					int prey = y + dy[preLoc[i]];

					if (map[prex][prey] != 0) // 뒤에 칸이 채워져있다면?
					{

						//System.out.println("현재 채워질 번호="+n);
						boolean used[] = new boolean[6]; // 인접한 6면을 확인해서 쓰인 숫자와 안쓰인 숫자를 구분

						for (int j = 0; j <= 5; j++) {
							int nnx = nx + dx[j];
							int nny = ny + dy[j];
							
							if (map[nnx][nny] != 0) {
								
								//System.out.println("사용된 번호들="+map[nnx][nny]);
								used[map[nnx][nny]] = true;

							}

						}
						ArrayList<Resource> list = new ArrayList<>();

						for (int j = 1; j <= 5; j++) {
							if (used[j] == false) {
								list.add(new Resource(j, usedNumberCnt[j]));

							}
						}

						Collections.sort(list);

						int number = list.get(0).number;

						
						map[nx][ny] = number;
						usedNumberCnt[number]++;

						tileNum[n][0] = nx;
						tileNum[n][1] = ny;
						n++;
					}

				}

			}

			if (flag)
				break;

		}

	}

}
