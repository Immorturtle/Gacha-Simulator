package model;

import java.util.ArrayList;
import java.util.List;

public interface Statistic {

    List<Character> getUltraRareList();

    List<Character> getSuperRareList();

    List<Character> getRareList();

    int numberOfCard();

    String displayStatsAsString(int id);

    boolean checkIDInList(int id);

}
