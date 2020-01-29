package com.example.sm_linguiz.model.ranking;//package com.example.sm_linguiz.model.ranking;
//
//public class SortingByPoints implements Sorting {
//    @Override
//    public void sort(Ranking ranking){
//        String help;
//        int[] points = ranking.scores;
//
//        for (int i = 0; i < points.length; i++) {
//            int min;
//            for (int j = 0; j < points.length; j++) {
//                if (points[j] < points[i]) {
//                    min = points[i];
//                    points[i] = points[j];
//                    points[j] = min;
//
//                    help = ranking.levels[i];
//                    ranking.levels[i] = ranking.levels[j];
//                    ranking.levels[j] = help;
//
//                    help = ranking.rankingList.get(i);
//                    ranking.rankingList.set(i, ranking.rankingList.get(j));
//                    ranking.rankingList.set(j,help);
//                }
//            }
//        }
//    }
//}
