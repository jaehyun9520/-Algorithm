class Solution {

	static int[] apeach;
	static int[] ryan = new int[11];
	static int[] ans = new int[11];
	static int scoreDif = 0;

	public int[] solution(int n, int[] info) {
		int[] answer = { -1 };

		apeach = info;

		combin(0, 0, n);

		if (scoreDif != 0) {
			return ans;
		}

		else
			return answer;
	}

	static void combin(int index, int cnt, int n) {

		// n개의 화살을 다 쓴 경우
		if (cnt == n) {

			// 1. 어피치와 비교해서 승리했는지 확인

			int apeachScore = 0;
			int ryanScore = 0;

			for (int i = 0; i <= 10; i++) {

				if (apeach[i] >= ryan[i] && apeach[i] != 0) {

					apeachScore += (10 - i);

				}

				else if (apeach[i] < ryan[i]) {
					ryanScore += (10 - i);
				}
			}

			// 2. 이전의 승리결과가 있다면 비교해서 더 낮은 점수를 많이 맞췄는지 확인
			if (ryanScore > apeachScore) {
				if (scoreDif < ryanScore - apeachScore) {

					scoreDif = (ryanScore - apeachScore);

					for (int j = 0; j <= 10; j++) {

						ans[j] = ryan[j];

					}

				}

				else if (scoreDif == (ryanScore - apeachScore)) {
					for (int i = 10; i >= 0; i--) {

						// 낮은점수를 더 많이 맞힌경우 해당 경우로 변경
						if (ans[i] < ryan[i]) {

							for (int j = 0; j <= 10; j++) {

								ans[j] = ryan[j];

							}
							break;

						} else if (ans[i] > ryan[i]) {
							break;
						}

					}
				}

			}

		}

		else {

			for (int num = index; num <= 10; num++) {

				ryan[num]++;

				combin(num, cnt + 1, n);

				ryan[num]--;

			}
		}

	}
}