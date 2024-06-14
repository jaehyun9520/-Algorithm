package programmers.level2;

import java.util.*;


public class 후보키 {

    public static void main(String[] args) {

//        String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
        String[][] relation = {{"1", "1", "1", "2"}, {"1", "1", "1", "3"}, {"2", "1", "1", "2"}, {"2", "1", "1", "3"}};
        System.out.println(solution(relation));
    }


    static int answer = 0;
    static LinkedList<Integer> selectedColumns = new LinkedList<>();
    static Set<List<Integer>> candidateKeySet = new HashSet<>();

    public static int solution(String[][] relation) {


        for (int i = 1; i <= relation.length; i++)
        // 조합 1개 2개 3개 선택된거 인덱스
        {
            comb(0, relation, i);
        }
        return answer;
    }

    static void comb(int index, String[][] relation, int cnt) {

        // 컬럼에서 원하는 개수를 다 뽑았다면? 유일성 확인
        if (selectedColumns.size() == cnt && uniquenessCheck(relation)) {

            System.out.println();
            answer++;
            candidateKeySet.add(new ArrayList<>(selectedColumns));
            return;
        }

        // 최소성을 만족하면 더 진행
        for (int i = index; i < relation[0].length; i++) {
            selectedColumns.addLast(i);
            if (minimalityCheck()) {
                comb(i + 1, relation, cnt);
            }
            selectedColumns.removeLast();
        }
    }

    static boolean uniquenessCheck(String[][] relation) {
        boolean result = true;

        Set<List<String>> set = new HashSet<>();

        for (int i = 0; i < relation.length; i++) {
            List<String> tuple = new ArrayList<>();

            for (int j = 0; j < selectedColumns.size(); j++) {

                tuple.add(relation[i][selectedColumns.get(j)]);
            }
            if (set.contains(tuple)) {
                result = false; // 중복되니 후보키가 될 수 없음
                break;
            } else {
                set.add(tuple);
            }
        }


        return result;
    }

    static boolean minimalityCheck() {

        Iterator<List<Integer>> iterator = candidateKeySet.iterator();


        while (iterator.hasNext()) {

            List<Integer> list = iterator.next();

            int cnt = 0;

            for (int i = 0; i < list.size(); i++) {

                for (int j = 0; j < selectedColumns.size(); j++) {

                    if (list.get(i) == selectedColumns.get(j)) {
                        cnt++;
                        break;
                    }
                }
            }
            if (cnt == list.size()) {
                return false;
            }
        }


        return true;
    }


}
