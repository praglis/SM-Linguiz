package com.example.sm_linguiz.model.ranking;//package com.example.sm_linguiz.model.ranking;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.control.ListView;
//import main.MainLauncher;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.PrintWriter;
//import java.util.Objects;
//import java.util.Scanner;
//
//public class Ranking {
//    public ObservableList<String> rankingList;
//    public String[] levels = new String[6];
//    public int[] scores = new int[6];
//    private Sorting sorting;
//
//    public Ranking() throws FileNotFoundException {
//        File plik = new File(Objects.requireNonNull(MainLauncher.class.getClassLoader().getResource("ranking")).getFile());
//        Scanner odczyt = new Scanner(plik);
//
//        rankingList = FXCollections.observableArrayList();
//
//        for(int i=0; i<6;i++){
//            if(odczyt.hasNextLine()) {
//                levels[i] = odczyt.nextLine();
//                scores[i] = Integer.parseInt(odczyt.nextLine());
//                String zdanie = levels[i] + " - " + scores[i] + "/10";
//                rankingList.add(zdanie);
//            }
//        }
//
//        setSortingByLevel();
//
//        odczyt.close();
//    }
//
//    public void resetRanking() throws FileNotFoundException {
//        File plik = new File(Objects.requireNonNull(MainLauncher.class.getClassLoader().getResource("ranking")).getFile());
//        Scanner odczyt = new Scanner(plik);
//
//        for (int i = 0; i < 6; i++) {
//            if (odczyt.hasNextLine()) {
//                levels[i] = odczyt.nextLine();
//                odczyt.nextLine();
//                scores[i] = 0;
//                String zdanie = levels[i] + " - " + scores[i] + "/10";
//                rankingList.set(i,zdanie);
//            }
//        }
//        odczyt.close();
//
//        PrintWriter zapis = new PrintWriter(plik);
//
//        for (int i = 0; i < 6; i++) {
//            zapis.println(levels[i]);
//            zapis.println(scores[i]);
//        }
//        zapis.close();
//    }
//
//    public void setList(ListView list) {
//        list.setItems(rankingList);
//    }
//
//    public void setSortingByLevel() {
//        sorting = new SortingByLevel();
//        sorting.sort(this);
//    }
//
//    public void setSortingByPoints(){
//        sorting = new SortingByPoints();
//        sorting.sort(this);
//    }
//
//}
