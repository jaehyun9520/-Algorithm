package programmers.level2;

public class 카펫 {

    public static void main(String[] args) {

    }
    public int[] solution(int brown, int yellow) {
        int[] answer = {0, 0};
        int yellowHeight = 0;
        int yellowLength = 0;
        while (true) {
            yellowHeight++;

            if (yellow % yellowHeight == 0) {

                yellowLength = yellow / yellowHeight;

                int sum = (yellowLength + 2) * 2 + yellowHeight * 2;

                if (sum == brown) {
                    answer[0] = yellowLength + 2;
                    answer[1] = yellowHeight + 2;
                    break;

                }


            }


        }

        return answer;
    }
}
