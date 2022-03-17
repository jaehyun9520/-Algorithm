package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj22234 {

	static class Client implements Comparable<Client> {
		int id, useTime, arrivalTime;

		Client(int id, int useTime, int arrivalTime) {
			this.id = id;
			this.useTime = useTime;
			this.arrivalTime = arrivalTime;
		}

		@Override
		public int compareTo(Client o) { // 도착시간이 빠른순으로 정렬
			return this.arrivalTime - o.arrivalTime;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
		int nowTime = 0; // 0초부터 시작
		int N, T, W, M; // 0초부터 기다린 고객수 , 직원이 처리할수있는 시간 , 목표시간
		Queue<Client> waitQ = new LinkedList<>(); // 은행 대기 큐
		PriorityQueue<Client> pq = new PriorityQueue<>();// 1초 이후에 들어온 고객들이 기다리는 큐

		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			int p, t;

			p = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());

			waitQ.add(new Client(p, t, 0));
		}

		M = Integer.parseInt(in.readLine());

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(in.readLine());
			int p, t, c;

			p = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			pq.add(new Client(p, t, c));

		}

		loop: while (!waitQ.isEmpty()) // 업무 시작
		{

			Client now = waitQ.poll();
			int id = now.id;
			int useTime = now.useTime;
			int nt = 0;

			boolean flag = false;

			if (useTime <= T) {
				nt = nowTime + useTime;
			} else {
				flag = true;
				nt = nowTime + T;
			}

			for (int i = nowTime; i < nt; i++) // 현재 시간에 누구의 업무를 수행했는지 기록
			{
				if (i == W) { // w-1초까지 채웠으면 종료
					break loop;
				}
				sb.append(id + "\n");
			}

			while (!pq.isEmpty()) {
				Client next = pq.peek();

				if (next.arrivalTime <= nt) {
					pq.poll();
					waitQ.add(next);
				} else {
					break;
				}

			}
			if (flag) {
				now.useTime = now.useTime - T;
				waitQ.add(now);
			}
			nowTime = nt;

		}

		System.out.println(sb.toString());
	}
}
