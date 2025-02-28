import java.util.*;

public class 튜플 {
    public static int[] solution(String s) {

        List<Integer> ans = new ArrayList<>();
        boolean[] used = new boolean[100001];
        List<List<Integer>> elementList = new ArrayList<>();
        List<Integer> list;
        int start = 0;
        int end = 0;
        for (int i = 1; i < s.length() - 1; i++) {

            char val = s.charAt(i);

            if (val == '{') {
                start = i;
            } else if (val == '}') {
                end = i;
                String substring = s.substring(start + 1, end);
                StringTokenizer st = new StringTokenizer(substring, ",");
                list = new ArrayList<>();

                while (st.hasMoreTokens()) {
                    list.add(Integer.parseInt(st.nextToken()));
                }
                elementList.add(list);
            }
        }

        Collections.sort(elementList, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> list1, List<Integer> list2) {
                return list1.size() - list2.size();
            }
        });

        for (int i = 0; i < elementList.size(); i++) {

            List<Integer> element = elementList.get(i);

            for (int j = 0; j < element.size(); j++) {
                int num = element.get(j);
                if (!used[num]) {
                    used[num] = true;
                    ans.add(num);
                }
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();

    }
}
