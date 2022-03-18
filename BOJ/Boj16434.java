package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj16434 {

	static class Room {
		int type, atk, hp;

		Room(int type, int atk, int hp) {
			this.type = type;
			this.atk = atk;
			this.hp = hp;
		}

	}

	static int n;
	static ArrayList<Room> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		long atk, low = 1, mid = 0, high = 0;
		n = Integer.parseInt(st.nextToken());
		atk = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(in.readLine());
			int type = Integer.parseInt(st.nextToken());
			int dm = Integer.parseInt(st.nextToken());
			int hp = Integer.parseInt(st.nextToken());
			long cnt = 0;
			if (type == 1) {

				cnt = hp / atk;
				if (hp % atk != 0) {
					cnt++;
				}

				high += (dm * (cnt - 1));

			}
			list.add(new Room(type, dm, hp));

		}

		high++;
		long ans = 0;
		while (low <= high) {

			mid = (low + high) / 2;

			if (simul(mid, atk)) {
				ans = mid;
				high = mid - 1;
			}

			else {
				low = mid + 1;
			}
		}
		System.out.println(ans);

	}

	private static boolean simul(long hp, long atk) {

		long nowHp = hp;
		for (int i = 0; i < list.size(); i++) {
			Room room = list.get(i);
			if (room.type == 2) // 포션인 경우
			{
				atk += room.atk;
				nowHp += room.hp;

				if (nowHp > hp) {
					nowHp = hp;
				}

			}

			else {

				long cnt = room.hp / atk;

				if (room.hp % atk != 0) {
					cnt++;
				}

				nowHp -= ((cnt - 1) * room.atk);

				if (nowHp <= 0)
					return false;
			}

		}

		return true;
	}
}
