package study53;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj24884 {

	static int move[] = { -1, 0, 1 };
	static int decreaseVal[] = { 3, 2, 1 };
	static int n, w, t, k; // 모닥불 개수, 시작 모닥불 번호, 모닥불 놀이 시간, 유지해야 하는 최소 모닥불 개수
	static int fire[]; //모닥불 화력
	static int ans=0;
	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		fire = new int[n]; // 0번 ~ n-1번

		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < n; i++) {
			fire[i] = Integer.parseInt(st.nextToken());
		}

		simulation(1, w); // 시간, 초기 알바생 위치
		System.out.println(ans);
	}

	static void simulation(int time, int loc) { //현재 시각, 알바생 위치
		
		
		if(time==t+1)
		{
			int cnt=0;
			for(int i=0; i<n; i++)
			{
				if(fire[i]>0) cnt++;
			}
			
			if(cnt>=k) ans++;
			
			
			return;
		}
		
		
		
		ArrayList<Integer> list = new ArrayList<>();

		if (time < t) { // 장작불을 넣을 수 있는 위치 기록
			for (int i = 0; i <= 2; i++) {
				int val = loc + move[i];
				if (0 <= val && val < n)
					list.add(val);
			}
		}

		int decrease[] = new int[n];

		for (int i = 0; i < n; i++) { // 모닥불의 화력이 얼마나 감소하는지 계산
			if (fire[i] > 0&&(i!=loc||time==1)) {
				int cnt = 0;
				int left = i - 1;
				int right = i + 1;

				if (left >= 0 && fire[left] > 0)
					cnt++;

				if (right < n && fire[right] > 0)
					cnt++;

				decrease[i] = decreaseVal[cnt];

			}
		}
		
		
		for(int i=0; i<n; i++) //감소
		{
			fire[i]-=decrease[i];
		}
		
		
		if(time<t)
		{
			for(int i=0; i<list.size(); i++)
			{
				simulation(time+1,list.get(i));
			}
		}
		
		else {
			simulation(time+1,0);
		}
		
		
		for(int i=0; i<n; i++) //다시 증가
		{
			fire[i]+=decrease[i];
		}
		
		

	}
}
