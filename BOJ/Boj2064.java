package study55;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2064 {

	public static void main(String[] args) throws IOException {

		int n;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());
		int ipAddress[][] = new int[n + 1][4]; // 0.0.0.0
		int networkMask[] = { 255, 255, 255, 255 };

		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), ".");

			for (int j = 0; j <= 3; j++) {
				ipAddress[i][j] = Integer.parseInt(st.nextToken());
			}

		}

		int octet = 3;
		int loc = 0;
		for (int i = 0; i <= 32; i++) {
			
			if(i!=0) {
			networkMask[octet] = networkMask[octet] & ~(1 << loc);
			loc++;
			if (loc == 8) {
				loc = 0;
				octet--;
			}
			}
			int net[] = new int[4];
			boolean flag = true;
			loop: for (int j = 1; j <= n; j++) {
				int val[] = new int[4];
				for (int k = 0; k <= 3; k++) {
					val[k] = ipAddress[j][k] & networkMask[k];
				}

				if (j == 1) {
					net = val;
				} else {

					for (int k = 0; k <= 3; k++) {
						if (net[k] != val[k]) {
							flag = false;
							break loop;
						}
					}

				}

			}
			
			if(flag)
			{
				System.out.println(net[0]+"."+net[1]+"."+net[2]+"."+net[3]);
				System.out.println(networkMask[0]+"."+networkMask[1]+"."+networkMask[2]+"."+networkMask[3]);
				break;
				
			}

		}

	}
}
