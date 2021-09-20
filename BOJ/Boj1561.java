package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1561_놀이공원 {

	static class State implements Comparable<State> {

		int index;
		long time;

		State(int index, long time) {
			this.index = index;
			this.time = time;
		}

		@Override
		public int compareTo(State o) {

			if (this.time < o.time) // 시칸이 큰 순으로 정렬 시간이 같다면 인덱스가 큰 순으로 정렬
			{
				return 1;
			} else if (this.time == o.time) {

				return o.index - this.index;

			}

			return -1;

		}

	}

	static int N, M; // 아이들, 놀이기구 개수
	static int input[]; // 놀이기구 운행시간(입력값)
	static long list[];
	static long anslist[]; // 각 놀이기구의 마지막 탑승시간 (분)

	static long simul(long time) {
		long cnt = 0; // 현재 주어진시간(time)으로 태울 수 있는 사람수
		list = new long[M + 1];
		for (int i = 1; i <= M; i++) {

			long val = (time / input[i]);

			list[i] = input[i] * val; // 각 놀이기구가 마지막으로 사람을 태운 시간
			cnt = cnt + (val + 1); // 0분일때도 포함해야하니 +1

		}
		return cnt;

	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		input = new int[M + 1];
		anslist = new long[M + 1];
		long val = 0, count = 0, ans = 0; // count는 mid의 시간으로 시뮬레이션 돌렸을때 태울수있는 사람수
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= M; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			val = Math.max(val, input[i]); // 운행시간이 가장 긴 놀이기구 찾기
		}
		long low = 0, high = val * (N - 1), mid = 0; // 이분탐색 범위
		while (low <= high) {
			mid = (low + high) / 2;

			count = simul(mid);

			if (count == N) {
				ans = count;
				anslist = list;
				break;
			}
			if (count >= N) {
				ans = count;
				high = mid - 1;
				anslist = list;
			}

			else {
				low = mid + 1;

			}
		}
		int index = 0;
		long time = 0; // 마지막에 사람을 태우는 놀이기구의 번호와 마지막 탑승 시간

		PriorityQueue<State> PQ = new PriorityQueue<>();

		for (int i = 0; i < anslist.length; i++) {
			PQ.add(new State(i, anslist[i]));

		}
		while (true) {
			State now = PQ.poll();
			index = now.index;
			time = now.time;
			if (N == ans)
				break;
			else {
				ans--;
				PQ.add(new State(index, time - input[index]));
			}
		}
		if (N <= M) {
			System.out.println(N);
		}

		else
			System.out.println(index);

	}
}
