package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11578 {
	static int student[]; // 학생별로 풀수있는 문제 번호(비트마스킹 형태로 저장)
	static int total, N, M, ans = 11;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 문제의 수
		M = Integer.parseInt(st.nextToken()); // 학생 수

		for (int i = 1; i <= N; i++) {
			total = (total | (1 << i)); // 전체 문제개수
		}
		student = new int[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int cnt = Integer.parseInt(st.nextToken());
			int flag = 0;
			for (int j = 1; j <= cnt; j++) {
				int value = Integer.parseInt(st.nextToken());
				flag = (flag | (1 << value));

			}
			student[i] = flag; // 풀수있는 문제 번호들
		}
		combination(1, 0, 0);
		if (ans != 11)
			System.out.println(ans);
		else
			System.out.println(-1);
	}

	static void combination(int index, int cnt, int flag) {
		if (flag == total)// 팀원이 모든 문제를 풀수있다면?
		{
			ans = Math.min(ans, cnt);
		} else {
			for (int i = index; i <= M; i++) {
				combination(i + 1, cnt + 1, (flag | student[i]));
			}
		}

	}

}