public class 행렬테두리회전하기 {

    static int[][] array;

    public int[] solution(int rows, int columns, int[][] queries) {

        int[] answer = new int[queries.length];
        init(rows, columns);

        for (int i = 0; i < queries.length; i++) {

            answer[i] = rotate(queries[i][0], queries[i][1], queries[i][2], queries[i][3]);
        }
        return answer;
    }

    static int rotate(int sr, int sc, int er, int ec) {


        int firstValue = array[sr][sc]; // 왼쪽 맨위의 값
        int min = firstValue;

        int preR = sr, preC = sc, nowR = sr + 1, nowC = sc;


        // 다시 시작점으로 돌아올때까지 반복
        while (!(nowR == sr && nowC == sc)) {

            min = Integer.min(min, array[nowR][nowC]); // 테두리를 돌아주며 더 최솟값이 있는지 확인

            array[preR][preC] = array[nowR][nowC]; // 시계방향으로 한칸씩 땡겨준다

            // 현재 위치가 어디냐에 따라서 상 하 좌 우 중 하나로 이동시켜준다

            preR = nowR;
            preC = nowC; // 이동전 현재 위치 기록

            if (nowR != er && nowC == sc) { // 왼쪽 테두리인 경우 아래로 이동
                nowR += 1;
            } else if (nowR == er && nowC != ec) { // 아래 테두리인 경우 오른쪽으로 이동
                nowC += 1;
            } else if (nowR != sr && nowC == ec) { // 오른쪽 테두리인 경우 위로 이동
                nowR -= 1;
            } else {  // 위쪽 테두리인 경우 왼쪽으로 이동
                nowC -= 1;
            }
        }
        array[sr][sc + 1] = firstValue;


        return min;
    }


    static void init(int r, int c) {
        array = new int[r + 1][c + 1];
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                array[i][j] = (i - 1) * c + j;
            }
        }
    }
}
