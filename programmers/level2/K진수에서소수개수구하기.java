package a0921;

import java.util.ArrayList;
import java.util.List;

public class K진수에서소수개수구하기 {

	public int solution(int n, int k) {
        List<Integer> list = numberConversion(n, k);
        int answer = primeFind(list);
        return answer;
    }
    static int primeFind(List<Integer> list) {

		int cnt = 0;

		long number = 0;

		for (int i = list.size() - 1; i >= 0; i--) {

			int now = list.get(i);

			if (number != 0 && now == 0) {

				if (check(number)) // 소수라면?
				{
					cnt++;

				}
				number = 0;
			}

			else {
				number *= 10;
				number += now;
			}
		}

		if (number != 0) {

			if (check(number)) // 소수라면?
			{
				cnt++;
			}
		}

		return cnt;

	}
    	static boolean check(long number) {
        if(number==1) return false;
		for (int i = 2; i <= Math.sqrt(number); i++) {

			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
    
    static List<Integer> numberConversion(int n, int k) {
		List<Integer> list = new ArrayList<>();
		while (true) {

			if (n / k == 0) {
				list.add(n);
				break;
			} else {
				list.add(n % k);
				n = n / k;
			}
		}
		return list;
	}

}
