package model;

import java.util.List;

//class that pre-determines the banner
abstract class BannerCreation extends DataBase {
    private List<Character> listOfCards;

    //EFFECTS: taking the character list from DataBase and returning it
    public BannerCreation() {
        listOfCards = characterLists();
    }

    //Getter
    public List<Character> returnPool() {
        return this.listOfCards;
    }

}
