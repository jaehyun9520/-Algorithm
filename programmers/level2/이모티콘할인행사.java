public class 이모티콘할인행사 {

    static int[] discount; // 각 이모티콘의 할인율 (10~40)
    static int[] answer = new int[2]; // {이모티콘 플러스 가입자, 판매액}
    static int[] emoticons; // 이모티콘
    static int[][] user; // 유저


    public int[] solution(int[][] intput1, int[] intput2) {
        emoticons = intput2;
        user = intput1;
        discount = new int[emoticons.length];

        dfs(0);

        return answer;
    }

    static void dfs(int number) {


        // 이모티콘의 할인율을 다 정했으면 실제로 계산
        if (number == emoticons.length) {

            int[] result = simulation();

            if (result[0] > answer[0]) {
                answer = result;
            } else if (result[0] == answer[0] && result[1] > answer[1]) {
                answer = result;
            }


            return;
        }
        for (int i = 10; i <= 40; i += 10) {
            discount[number] = i;
            dfs(number + 1);
        }
    }

    static int[] simulation() {

        // 정해진 할인율이 적용된 이모티콘 가격
        int[] discountedPrice = new int[emoticons.length];

        for (int i = 0; i < emoticons.length; i++) {

            discountedPrice[i] = emoticons[i] * (100 - discount[i]) / 100; // 100의 배수이니 이런식으로 될듯. .?
        }

        int[] result = new int[2];

        for (int i = 0; i < user.length; i++) {

            int disRate = user[i][0]; // 사용자 기준 할인율
            int price = user[i][1]; // 사용자 기준 가격

            int sum = 0;
            boolean emoticonPlus = false;
            for (int j = 0; j < emoticons.length; j++) {

                if (disRate <= discount[j]) {

                    sum += discountedPrice[j];

                    if (sum >= price) {
                        emoticonPlus = true;
                        break;
                    }

                }

            }

            if (emoticonPlus) {
                result[0]++;
            } else {
                result[1] += sum;
            }

        }

        return result;

    }

}
