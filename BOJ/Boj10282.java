package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj10282_해킹 {

	static class Computer implements Comparable<Computer> {

		int number, val;

		Computer(int number, int val) {
			this.number = number;
			this.val = val;
		}

		@Override
		public int compareTo(Computer o) { // 가중치값 오름차순 정렬

			return this.val - o.val;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();

		int testcase = Integer.parseInt(st.nextToken());
		PriorityQueue<Computer> PQ = new PriorityQueue<>();

		for (int t = 1; t <= testcase; t++) {
			int n, d, c; // 컴퓨터 개수 , 의존성 개수 , 해킹당한 컴퓨터 번호
			st = new StringTokenizer(in.readLine());

			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			ArrayList<Computer> node[] = new ArrayList[n + 1];

			for (int i = 1; i <= n; i++) {
				node[i] = new ArrayList<Computer>();
			}

			// a b s ( b가 감염되면 s초 후 컴퓨터 a도 감염된다 )
			for (int i = 1; i <= d; i++) {
				int a, b, s;
				st = new StringTokenizer(in.readLine());

				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				s = Integer.parseInt(st.nextToken());

				node[b].add(new Computer(a, s));
			}

			boolean check[] = new boolean[n + 1]; // 최단

			// 맨 처음 해킹당한 컴퓨터 c
			PQ.add(new Computer(c, 0));

			int cnt = 0, value = 0;

			while (!PQ.isEmpty()) {
				Computer now = PQ.poll();

				if (check[now.number] == false) {
					cnt++;
					value = now.val;
					check[now.number] = true;

					for (int i = 0; i < node[now.number].size(); i++) {

						Computer next = node[now.number].get(i);

						if(check[next.number]==false)
						PQ.add(new Computer(next.number, next.val + now.val));

					}

				}

			}
			
			
			sb.append(cnt+" "+value+"\n");
			
			
			

		}
		
		System.out.println(sb.toString());

	}
}
