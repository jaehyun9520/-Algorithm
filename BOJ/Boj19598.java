package study69;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj19598 {

	static class State implements Comparable<State> {

		int time, roomCnt;

		State(int time, int roomCnt) {

			this.time = time;
			this.roomCnt = roomCnt;
		}

		@Override
		public int compareTo(State o) {

			return this.time - o.time;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(in.readLine());
		List<State> list = new ArrayList<>();

		for (int i = 1; i <= n; i++) {

			st = new StringTokenizer(in.readLine(), " ");

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			list.add(new State(start, 1));
			list.add(new State(end, -1));
		}

		Collections.sort(list);

		int ans = 1;

		State pre = list.get(0);

		for (int i = 1; i < list.size(); i++) {

			State now = list.get(i);

			now.roomCnt += pre.roomCnt;

			
			
			if(i!=list.size()-1&&now.time!=list.get(i+1).time) {
			
			ans = Integer.max(ans, now.roomCnt);
			}
			pre = now;

		}
		
		System.out.println(ans);

	}
}
