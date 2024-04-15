public class n2배열자르기 {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int) right - (int) left + 1];
        boolean first = true; // 처음 시작인 left인지
        int cnt = 0;
        for (long i = left / n; i <= right / n; i++) {

            long j = 0;
            if (i == left / n && first) {
                first = false;
                j = left % n;
            }

            for (; j < n; j++) {


                if (i == right / n && j == right % n + 1) {
                    break;
                }

                if (i >= j) {
                    answer[cnt] = (int) (i + 1);
                } else {
                    answer[cnt] = (int) (j + 1);
                }
                cnt++;
            }
        }
        return answer;
    }
}
