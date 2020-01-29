package com.example.sm_linguiz.model.ranking;//package com.example.sm_linguiz.model.ranking;
//
//public class SortingByLevel implements Sorting {
//
//    @Override
//    public void sort(Ranking ranking) {
//        String[] level = ranking.levels;
//        int x;
//
//        for (int i = 0; i < level.length; i++) {
//            String help;
//            for (int j = 0; j < level.length; j++) {
//                if (level[j].compareTo(level[i]) > 0) {
//                    help = level[i];
//                    level[i] = level[j];
//                    level[j] = help;
//
//                    x = ranking.scores[i];
//                    ranking.scores[i] = ranking.scores[j];
//                    ranking.scores[j] = x;
//
//                    help = ranking.rankingList.get(i);
//                    ranking.rankingList.set(i, ranking.rankingList.get(j));
//                    ranking.rankingList.set(j,help);
//                }
//            }
//        }
//    }
//}
