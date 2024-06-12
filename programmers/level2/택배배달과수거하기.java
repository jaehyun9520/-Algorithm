package programmers.level2;

public class 택배배달과수거하기 {


    public static void main(String[] args) {


        System.out.println(solution(2, 7, new int[]{1, 0, 2, 0, 1, 0, 2}, new int[]{0, 2, 0, 1, 0, 2, 0}));
    }

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {

        int deliveryBox =0;
        int pickupBox = 0;
        long answer = 0;
        for (int i = n - 1; i >= 0; i--) {

            deliveryBox -= deliveries[i]; // -40
            pickupBox -= pickups[i];

            while ((deliveryBox < 0 || pickupBox < 0)) {
                answer += ((i+1) * 2);
                deliveryBox += cap;
                pickupBox += cap;
            }

        }
        return answer;
    }
}
