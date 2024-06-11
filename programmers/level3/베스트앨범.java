package programmers.level3;


import java.util.*;


public class 베스트앨범 {


    static class Genre {
        String name; // 장르의 이름
        int playCnt; // 재생횟수


        Genre(String name, int playCnt) {
            this.name = name;
            this.playCnt = playCnt;
        }
    }


    public static void main(String[] args) {

        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        System.out.println(Arrays.toString(solution(genres, plays)));

    }


    public static int[] solution(String[] genres, int[] plays) {

        List<Integer> answer = new ArrayList<>();

        // 장르와 해당 장르의 인덱스 번호
        Map<String, Integer> map = new HashMap<>();
        List<Genre> list = new ArrayList<>();

        // 각 장르가 가지고 있는 곡정보들 ( 인덱스번호와 재생횟수)
        Map<String, List<int[]>> songInfo = new HashMap<>();


        for (int i = 0; i < genres.length; i++) {

            String name = genres[i];

            // 장르명이 처음이라면
            if (!map.containsKey(name)) {

                list.add(new Genre(name, plays[i]));
                map.put(name, list.size() - 1); //몇번째 인덱스정보를 가지고 있는지
                List<int[]> songList = new ArrayList<>();
                songList.add(new int[]{i, plays[i]});
                songInfo.put(name, songList);
            }

            // 장르명이 이미 포함되어 있다면?
            else {
                int index = map.get(name);

                list.get(index).playCnt += plays[i];

                songInfo.get(name).add(new int[]{i, plays[i]});

            }

        }


        list.sort((genre, t1) -> t1.playCnt - genre.playCnt);

        for (Genre genre : list) {

            String name = genre.name;

            List<int[]> songList = songInfo.get(name);

            songList.sort((ints, t1) -> {

                if (ints[1] == t1[1]) {
                    return ints[0] - t1[0];
                }
                return t1[1] - ints[1];
            });

            for (int j = 0; j < songList.size() && j <= 1; j++) {
                int[] ints = songList.get(j);
                answer.add(ints[0]);

            }


        }


        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

}
