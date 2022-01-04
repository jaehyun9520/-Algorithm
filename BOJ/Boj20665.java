package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj20665 {

	static class People implements Comparable<People> {

		int startTime, endTime, useTime; // 입실시간, 퇴실시간 , 이용시간

		People(int startTime, int endTime, int useTime) {
			this.startTime = startTime;
			this.endTime = endTime;
			this.useTime = useTime;
		}

		@Override
		public int compareTo(People o) {
			if (this.startTime > o.startTime) {
				return 1;
			} else if (this.startTime == o.startTime) {
				return this.useTime - o.useTime;
			}
			return -1;

		}

	}

	static int N; // 좌석개수
	static int T; // 독서실 예약자 수
	static int P; // 민규가 좋아하는 좌석 번호
	static int array[];
	static boolean State = true; // 민규상태( true라면 선호좌석에 앉아있음)
	static int time = 540; // 앉은 시간 (분단위)
	static int Number = 0; // 현재 독서실의 인원수
	static int ans = 0; // 민수가 원하는 자리에 앉은 총 시간

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		array = new int[N + 1];
		PriorityQueue<People> pq = new PriorityQueue<>();

		for (int i = 1; i <= T; i++) {
			int startTime, endTime;
			st = new StringTokenizer(in.readLine(), " ");
			String s = st.nextToken();
			startTime = (s.charAt(0) - 48) * 600 + (s.charAt(1) - 48) * 60 + (s.charAt(2) - 48) * 10
					+ (s.charAt(3) - 48);
			s = st.nextToken();
			endTime = (s.charAt(0) - 48) * 600 + (s.charAt(1) - 48) * 60 + (s.charAt(2) - 48) * 10 + (s.charAt(3) - 48);
			pq.add(new People(startTime, endTime, endTime - startTime));
		}

		simulation(pq);

		System.out.println(ans);

	}

	static void simulation(PriorityQueue<People> pq) {

		while (!pq.isEmpty()) {

			int arrayNumber = 0; // 현재 좌석번호
			int cnt = 0; // 가장 가까운 사람이 몇칸 떨어져있는지?

			People people = pq.poll();
			int st = people.startTime;
			if (State == false && st >= array[P]) // 현재 시간으로 민규가 앉고자 하는 자리가 비었다면?
			{
				State = true;
				time = array[P];
			}
			for (int i = 1; i <= N; i++) // 현재 시간에 맞춰 좌석 자리를 갱신
			{
				if (array[i] != 0 && array[i] <= st) {
					array[i] = 0;
					Number--;
				}
			}

			if (Number == 0) // 독서실을 이용하는 사람이 없다면 1번 자리 배정
			{
				arrayNumber = 1;
			} else { // 아니라면 어디에 앉을지 찾기
				for (int i = 1; i <= N; i++) {
					if (array[i] == 0) {
						int length = Integer.MAX_VALUE;
						// 왼쪽 확인
						for (int j = i - 1; j >= 1; j--) {
							if (array[j] != 0) {

								length = Integer.min(i - j, length);
								break;
							}
						}
						// 오른쪽 확인
						for (int j = i + 1; j <= N; j++) {
							if (array[j] != 0) {
								length = Integer.min(j - i, length);
								break;
							}
						}
						if (cnt < length) {
							arrayNumber = i;
							cnt = length;
						}
					}
				}
			}
			Number++;
			array[arrayNumber] = people.endTime;
			// 앉을 자리 선택 완료
			if (arrayNumber == P && State == true) {
				State = false;
				ans += (st - time);
			}
		}
		if (State == true) // 앉아있던 채로 독서실 종료
			ans += (21 * 60 - time);
		else if (State == false)
			ans += (21 * 60 - array[P]);

	}
}
