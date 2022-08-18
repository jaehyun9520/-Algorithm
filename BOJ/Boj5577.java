package a5577;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boj5577_RBY팡 {

	static int n;
	static int array[];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());

		array = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			array[i] = Integer.parseInt(in.readLine());
		}
		int ans = 0;
		int color = 0;
		int totalCount = 0;
		ArrayList<Integer> list=new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			list.clear();
			int uc = 0, dc = 0;
			int now = array[i];
			if (i - 1 >= 1) {
				uc = array[i - 1];
				if(uc!=now) list.add(uc);
			}

			if (i + 1 <= n) {
				dc = array[i + 1];
				
				if(dc!=now)
				{
					if(list.size()==0||list.get(0)!=dc)
						list.add(dc);
				}
				
			}

			for(int j=0; j<list.size(); j++) { // 진행해볼 가능성이 있다면?
				int preColor = now;
				
				totalCount = 0;
	
				
				color = list.get(j);
				array[i] = color;
				int ui = i;
				int di = i + 1;
				while (true) {

					int upCnt = 0, downCnt = 0;
					// 위쪽 카운트 (인덱스와 현재색을 넘겨주고 같은색의 개수를 받는다)
					upCnt = upCount(color, ui);
					// 아래쪽 카운트
					downCnt = downCount(color, di);

					int count = upCnt + downCnt;

					if (count >= 4) {
						totalCount += count;
						count = 0;

						ui = ui - upCnt;
						di = di + downCnt;
					
						uc = 0;
						dc = 0;

						if (ui >= 1) {
							uc = array[ui];
						}

						if (di <= n) {
							dc = array[di];
						}
					
						
						if ((uc == 0 || dc == 0) || (uc != 0 && dc != 0 && dc != uc)) // 둘 다 이미 끝까지 진행했거나 둘의 색이 다른경우
						{
							break;

						}

						else {

							color = uc;

						}

					}

					else {
						break;
					}

				}
				ans = Integer.max(ans, totalCount);
				array[i] = preColor;
			}

		}

		System.out.println(n - ans);

	}

	static int upCount(int color, int index) {
		int cnt = 0;
		for (int i = index; i >= 1; i--) {
			if (color == array[i]) {
				cnt++;
			}

			else {
				break;
			}

		}
		return cnt;
	}

	static int downCount(int color, int index) {
		int cnt = 0;
		for (int i = index; i <= n; i++) {
			if (color == array[i]) {
				cnt++;
			} else {
				break;
			}
		}
		return cnt;
	}
}
