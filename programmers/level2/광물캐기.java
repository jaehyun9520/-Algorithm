public class 광물캐기 {

    static int[] selected;
    static int[][] fatigueInfo = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}}; // 곡괭이별 광물 피로도 정보
    static int answer = Integer.MAX_VALUE;
    static String[] mineralInfo;

    public int solution(int[] picks, String[] minerals) {

        mineralInfo = minerals;
        int pickCnt = picks[0] + picks[1] + picks[2];
        int mineralsCnt = minerals.length / 5;
        // 나머지가 있는 경우
        if (minerals.length % 5 != 0) {
            mineralsCnt++;
        }
        selected = new int[Integer.min(pickCnt, mineralsCnt)];

        // 다이아몬드, 철, 돌, 선택한 횟수
        selectPick(picks[0], picks[1], picks[2], 0);
        return answer;
    }
    static void selectPick(int dia, int iron, int stone, int cnt) {
        if (cnt == selected.length) {
            answer = Integer.min(simulation(), answer);
            return;
        }

        if (dia > 0) {
            selected[cnt] = 0;
            selectPick(dia - 1, iron, stone, cnt + 1);
        }

        if (iron > 0) {
            selected[cnt] = 1;
            selectPick(dia, iron - 1, stone, cnt + 1);
        }

        if (stone > 0) {
            selected[cnt] = 2;
            selectPick(dia, iron, stone - 1, cnt + 1);
        }
    }
    static int simulation() {
        int sum = 0;
        for (int i = 0; i < selected.length; i++) {


            int pickType = selected[i]; // 1은 다이아몬드,

            for (int j = 0 + 5 * i; j <= 4 + 5 * i && j < mineralInfo.length; j++) {

                String info = mineralInfo[j];
                int mineralType;
                if (info.equals("diamond")) {
                    mineralType = 0;
                } else if (info.equals("iron")) {
                    mineralType = 1;
                } else {
                    mineralType = 2;
                }

                sum += fatigueInfo[pickType][mineralType];
            }
        }
        return sum;
    }
}
