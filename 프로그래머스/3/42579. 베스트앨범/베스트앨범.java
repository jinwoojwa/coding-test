import java.util.*;

class Solution {

    static class Song {
        int index;
        int play;

        Song(int index, int play) {
            this.index = index;
            this.play = play;
        }
    }

    public int[] solution(String[] genres, int[] plays) {

        Map<String, Integer> totalMap = new HashMap<>(); // 장르별 총 재생수

        Map<String, List<Song>> songMap = new HashMap<>(); // 장르별 노래 목록

        for (int i = 0; i < genres.length; i++) {
            totalMap.merge(genres[i], plays[i], Integer::sum);

            songMap.computeIfAbsent(genres[i], g -> new ArrayList<>())
                    .add(new Song(i, plays[i]));
        }

        List<String> genreOrder = new ArrayList<>(totalMap.keySet());
        genreOrder.sort((a, b) ->
                totalMap.get(b) - totalMap.get(a));

        List<Integer> result = new ArrayList<>();
        for (String genre : genreOrder) {
            List<Song> songs = songMap.get(genre);

            // 노래 정렬
            songs.sort((a, b) -> {
                if (a.play == b.play) {
                    return a.index - b.index;
                }
                return b.play - a.play;
            });

            // 최대 2개 선택
            for (int i = 0; i < Math.min(2, songs.size()); i++) {
                result.add(songs.get(i).index);
            }
        }
        return result.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}