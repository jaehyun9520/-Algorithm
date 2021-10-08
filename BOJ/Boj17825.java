package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17825 {

	static class State {
		// 1 은 빨강 2는 블루 flag는 현재 위치에 말이 존재하는지 여부 1이면 존재 0이면 존재 x
		int color, score, flag, red, blue; // 최대 2개의 경로가 있다 파란색 화살표, 빨간색 화살표

		State(int color, int score, int flag, int red, int blue) {
			this.color = color;
			this.score = score;
			this.flag = flag;
			this.red = red;
			this.blue = blue;
		}
	}

	static int ans = 0;
	static State[] loc;
	static int[] horse;
	static int[] dice;
	static int[] finish = new int[5];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		loc = new State[33]; // 0번 ~ 32번 (0번 출발지 32번 도착지)

		loc[32] = new State(0, 0, 0, 0, 0);
		for (int i = 0; i <= 20; i++) {
			if (i == 5 || i == 10 || i == 15) // 파란색 칸
			{
				loc[i] = new State(2, i * 2, 0, i + 1, 0);
			} else
				// 색(1번 빨강 2번 파랑), 점수 , 말의 여부, 빨간색선이 가르키는 번호, 파란색선이 가르키는 번호
				loc[i] = new State(1, i * 2, 0, i + 1, 0);

			if (i == 20)
				loc[i].red = 32; // 마지막 20번째칸은 32번째칸을 가리킨다
		}

		int tmp[] = { 22, 24, 25, 30, 35 }; // 21 22 (23) 24 25

		for (int i = 21; i <= 25; i++) {
			loc[i] = new State(1, tmp[i - 21], 0, i + 1, 0);
			if (i == 25)
				loc[i].red = 20;// 25번(35)가 20번(40)을 가리킨다
		}

		tmp = new int[] { 13, 16, 19 };

		for (int i = 26; i <= 28; i++) {
			loc[i] = new State(1, tmp[i - 26], 0, i + 1, 0);

			if (i == 28)
				loc[i].red = 23;

		}
		tmp = new int[] { 28, 27, 26 }; // 29 30 31

		for (int i = 29; i <= 31; i++) {
			loc[i] = new State(1, tmp[i - 29], 0, i + 1, 0);

			if (i == 31)
				loc[i].red = 23;
		}

		// 각 파란색의 칸의 파란색 화살표가 누굴연결하는지
		tmp = new int[] { 26, 21, 29 }; // 인덱스번호
		int cnt = 0;
		for (int i = 5; i <= 15; i += 5) {

			loc[i].blue = tmp[cnt];
			cnt++;

		}

		// 다 연결..

		// 말들은 다 출발지
		horse = new int[5]; // 처음은 모두 0인 상태
		dice = new int[11];

		for (int i = 1; i <= 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken()); // 주사위에서 나올 수
		}

		simul(1, 0); // 주사위 1번부터 선택, 현재까지 누적점수
		System.out.println(ans);

	}

	static void simul(int cnt, int sum) {

		ans = Math.max(ans, sum);
		if (cnt == 11) 
		{

			ans = Math.max(ans, sum);

			return;
		}

		else {

			int move = dice[cnt]; //  움직일 횟수

			for (int i = 1; i <= 4; i++) // 아직 도착지에 도착하지 않은 말 중에 하나를 선택
			{

				if (finish[i] == 0) {
					// 이동을 다 하고나서 도착하는 칸에 이미 다른 말이 있는지 확인

					int now = horse[i];

					for (int j = 1; j <= move; j++) {

						if (now == 32)
							break; // 마지막 칸
						// 움직일때 파란색 칸인지 빨간색 칸인지 확인
						if (j == 1 && loc[now].color == 2) // 2파란색 칸 1은 빨간색
						{
							now = loc[now].blue; // 파란색 화살표를 따라간다
						}

						else {
							now = loc[now].red; //빨간색 화살표를 따라간다
						}
					}

					// 이미 해당 칸에 다른말이 있다면 놓는것은 불가능

					if (loc[now].flag == 1)
						continue; // 32번은 여러말이 올수있으니 flag를 항상 0으로 유지

					int tmp = horse[i]; // 이전의 위치 따로 저장
					loc[horse[i]].flag = 0; // 이동하니 출발한 칸에서는 말을 제거

					// 도착지 32번이라면?
					if (now == 32) {
						horse[i] = now; // 현재 위치로
						finish[i] = 1;
						simul(cnt + 1, sum); // 점수는 추가가 안됨
						finish[i] = 0;
						loc[tmp].flag = 1;
						horse[i] = tmp; // 이전 위치로

					}

					else // 도착지가 아닌 다른곳이라면?
					{
						horse[i] = now;
						loc[now].flag = 1;
						simul(cnt + 1, sum + loc[now].score);
						loc[now].flag = 0;
						horse[i] = tmp;
						loc[tmp].flag = 1;

					}

				}

			}

		}

	}

}
