public class 퍼즐게임챌린지 {

    public static int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;

        //이분탐색으로 제한 시간 내에 통과하는 가장 낮은 숙련도를 찾아준다

        int low = 1;
        int high = 100000;
        int mid = 0;

        while (low <= high) {

            mid = (low + high) / 2;

            // 해당 숙련도(mid)로 제한 시간 내에 통과가 가능하다면?
            if (simulation(diffs, times, limit, mid)) {
                answer = mid;  // 왜 이게 가능한가?
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return answer;
    }

    private static boolean simulation(int[] diffs, int[] times, long limit, int level) {
        long totalTime = 0;
        long prevTime = 0;

        for (int i = 0; i < diffs.length; i++) {

            // 난이도와 소요시간
            int diff = diffs[i];
            int time = times[i];

            // 난이도보다 레벨이 더 높거나 같은 경우
            if (diff <= level) {
                totalTime += time;
            } else if (diff > level) {
                totalTime += (prevTime + time) * (diff - level) + time;

            }

            prevTime = time;

            if (limit < totalTime) {
                return false;
            }


        }

        return true;
    }


}
