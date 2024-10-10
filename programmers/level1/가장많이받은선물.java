package programmers.level1;

import java.util.HashMap;
import java.util.Map;

public class 가장많이받은선물 {

  public static void main(String[] args) {

    String[] friends = {"a", "b", "c"};
    String[] gifts = {"a b", "b a", "c a", "a c", "a c", "c a"};

    System.out.println(solution(friends, gifts));
  }

  public static int solution(String[] friends, String[] gifts) {

    // 친구들에게 번호부여 0부터 시작
    Map<String, Integer> friendNumber = new HashMap<>();

    for (int i = 0; i < friends.length; i++) {
      friendNumber.put(friends[i], i);
    }

    // 각 친구들의 선물지수
    int[] giftScore = new int[friends.length];
    // 다음달 선물 개수
    int[] nextGiftCnt = new int[friends.length];
    // 이번달 주고받은 선물정보표
    int[][] giftBoard = new int[friends.length][friends.length];

    // 선물정보표 채우기
    for (int i = 0; i < gifts.length; i++) {
      String[] s = gifts[i].split(" ");
      int a = friendNumber.get(s[0]);
      int b = friendNumber.get(s[1]);
      giftBoard[a][b]++;
    }

    // 선물지수 계산
    for (int i = 0; i < friends.length; i++) {
      for (int j = 0; j < friends.length; j++) {
        // 1. 자신이 친구에게 준 선물
        giftScore[i] += giftBoard[i][j];
        // 2. 받은 선물
        giftScore[i] -= giftBoard[j][i];
      }
    }

    // 다음달 선물을 얼마나 받을지 계산
    for (int i = 0; i < friends.length - 1; i++) {
      for (int j = i + 1; j < friends.length; j++) {

        if (giftBoard[i][j] > giftBoard[j][i]) {
          nextGiftCnt[i]++;
        } else if (giftBoard[i][j] < giftBoard[j][i]) {
          nextGiftCnt[j]++;
        } else {
          //선물지수가 더 큰 사람이 받는다
          if (giftScore[i] > giftScore[j]) {
            nextGiftCnt[i]++;
          } else if (giftScore[i] < giftScore[j]) {
            nextGiftCnt[j]++;
          }

        }
      }
    }
    int max = 0;
    for (int i = 0; i < friends.length; i++) {
      max = Integer.max(max, nextGiftCnt[i]);
    }
    int answer = max;
    return answer;
  }
}
