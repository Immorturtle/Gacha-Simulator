package ui;

import exceptions.InsufficientValueException;
import model.Banner;
import model.Player;
import model.Character;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//UI format is inspired by the TellerApp. This class helps create the foundation of
//the simulator.
public class Gacha {
    private static final int SINGLE_SUMMON_COST = 5;
    private static final int MULTI_SUMMON_COST = 50;
    private static final String JSON_STORE = "./data/player.json";
    private Player user;
    private Banner pool;
    private Character card;
    private List<Character> cards;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    //EFFECTS: run the gacha game
    public Gacha() {
        runGacha();
    }

    //EFFECTS: runs the Gacha simulator. Asks user to input a 6 digit ID. If userID is not 6 digit,
    // then print invalid userID, otherwise create user with that userID. Afterwards, asks user to input a command.
    private void runGacha() {
        String command = "standpoint";
        input = new Scanner(System.in);
        beginningInit();
        beginning();
        while (command != "q") {
            options();
            command = input.next().toLowerCase();
            processCommand(command);
        }
    }

    //EFFECTS: initializes userID if userid is 6 digits long
    private void userCreation() {
        int userID = -1;
        while (userID < 100000 || userID >= 999999) {
            System.out.println("Please enter a 6 digit userID");
            userID = input.nextInt();
            if (userID < 100000 || userID >= 999999) {
                System.out.println("Invalid userID");
            }
        }
        userInit(userID);
    }

    //EFFECTS: creates options for the beginning.
    private void beginning() {
        System.out.println("Welcome to the Gacha Simulator!");
        System.out.println("Please select one of the options below:");
        System.out.println("load -> load data");
        System.out.println("create -> create an account");
        beginningOptions();
    }

    //EFFECTS: reads input from user for the beginning.
    private void beginningOptions() {
        String command = "standpoint";
        input = new Scanner(System.in);
        command = input.next().toLowerCase();

        if (command.equals("load")) {
            if (loadPlayer() == false) {
                userCreation();
            }

        } else if (command.equals("create")) {
            userCreation();
        } else {
            System.out.println("invalid command please try again");
            beginning();
        }
    }

    //REQUIRES: id > 0
    //EFFECTS: creates NEW data to be used in the Gacha Simulator
    private void userInit(int id) {
        user = new Player(id);
    }

    //EFFECTS: constructs pool.
    private void beginningInit() {
        pool = new Banner();
        card = new Character();
        cards = new ArrayList<>();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    //EFFECTS: prints general options
    private void options() {
        System.out.println("Please select the options below:");
        System.out.println("cred -> Go to credit option");
        System.out.println("banner -> Go to banner");
        System.out.println("player -> view personal options");
        System.out.println("save -> save data");
        System.out.println("load -> load data");
        System.out.println("q -> quit");
    }

    //EFFECTS: prints credit options
    private void credOptions() {
        System.out.println("Credit Options - Please select the options from the following: ");
        System.out.println("add -> add credit");
        System.out.println("view -> view credit");
        System.out.println("b -> return to previous option");
    }

    //EFFECTS: prints banner options
    private void bannerOptions() {
        System.out.println("Banner Options - Please select the options from the following: ");
        System.out.println("s -> single summon");
        System.out.println("m -> multi summon");
        System.out.println("v -> view banner");
        System.out.println("b -> return to previous option");
    }

    //EFFECTS: prints player options
    private void playerOptions() {
        System.out.println("Player Options - Please select the options from the following: ");
        System.out.println("spent -> view amount spent");
        System.out.println("box -> view characters in player's box");
        System.out.println("b -> return to previous option");
    }

    //EFFECTS: takes in inputted command and branches off to different options. User then creates inputs another
    // command.
    private void processCommand(String command) {
        if (command.equals("cred")) {
            credOptions();
            processCreditCommand(command = input.next().toLowerCase());
        } else if (command.equals("banner")) {
            bannerOptions();
            processBannerCommand(command = input.next().toLowerCase());
        } else if (command.equals("player")) {
            playerOptions();
            processPlayerCommand(command = input.next().toLowerCase());

        } else if (command.equals("q")) {
            System.out.println("See you next time.");
            System.exit(1);
        } else if (command.equals("save")) {
            savePlayer();
        } else if (command.equals("load")) {
            loadPlayer();
        } else {
            System.out.println("Invalid command, please try again");
        }
    }

    //Inspired by JsonSerializationDemo
    //EFFECTS: saves the data of Player to a JSON file
    private void savePlayer() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            System.out.println("Saved to file: " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //Inspired by JsonSerializationDemo
    //EFFECTS: loads the data of player to a JSON file
    private boolean loadPlayer() {
        try {
            user = jsonReader.read();
            System.out.println("Loaded " + user.getUserID() + "from " + JSON_STORE);
            return true;
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            return false;
        } catch (JSONException e) {
            System.out.println("File is Empty");
            return false;
        }
    }

    //Function is here to make intelliJ not angry about an empty if statement
    //EFFECTS: make x = 0.
    private void doNothing() {
        int x = 0;
    }

    //MODIFIES: this
    //EFFECTS: takes in command. If command is add, then add credits to cred. If command is view, print user's cred.
    private void processCreditCommand(String command) {
        if (command.equals("add")) {
            System.out.println("Enter amount of credits");
            int val = input.nextInt();
            user.addCred(val);
            processCommand("cred");
        } else if (command.equals("view")) {
            System.out.println("current credit: " + user.getCred());
            processCommand("cred");
        } else if (command.equals("b")) {
            doNothing();
        } else {
            System.out.println("Invalid command, try again");
            processCommand("cred");
        }
    }

    //MODIFIES: this
    //EFFECTS: If user cred is sufficient, then it calls singleSummon() and prints out the card, otherwise prints out
    // insufficient credits
    private void singleSummon() {
        try {
            user.pay(SINGLE_SUMMON_COST);
            card = pool.singleSummon();
            user.store(card);
            System.out.println("you received: " + "[" + card.getRarity() + "] " + card.getName());
        } catch (InsufficientValueException e) {
            System.out.println("You do not have enough credits");
        }
        processCommand("banner");
    }

    //MODIFIES: this
    //EFFECTS: If user cred is sufficient, then it calls multiSummon() and prints out multiple card,
    // otherwise prints out insufficient credits
    private void multiSummon() {
        try {
            user.pay(MULTI_SUMMON_COST);
            cards = pool.multiSummon();
            user.store(cards);
            for (Character c : cards) {
                System.out.println("you received: " + "[" + c.getRarity() + "] " + c.getName());
            }
        } catch (InsufficientValueException e) {
            System.out.println("You do not have enough credits");
        }
        processCommand("banner");
    }

    //EFFECT: shows list of cards in the pool and allows user to display specific card in the banner
    private void viewBanner() {
        List<Character> featured = pool.getPool();
        for (Character c : featured) {
            System.out.println("[" + c.getId() + "]" + "[" + c.getRarity() + "] - " + c.getName());
        }
        System.out.println("Enter ID to view card or -1 to return to previous option");
        int characterID = input.nextInt();
        if (characterID == -1) {
            doNothing();
        } else if (pool.checkIDInList(characterID)) {
            System.out.println(pool.displayStatsAsString(characterID));
        } else {
            System.out.println("The ID is invalid");
        }

        processCommand("banner");
    }

    //EFFECTS: process input command
    private void processBannerCommand(String command) {
        if (command.equals("s")) {
            singleSummon();
        } else if (command.equals("m")) {
            multiSummon();
        } else if (command.equals("v")) {
            viewBanner();
        } else if (command.equals("b")) {
            doNothing();
        } else {
            System.out.println("Invalid command, try again");
            processCommand("banner");
        }
    }

    //EFFECTS: prints out the card and its position in the box
    private void printBox() {
        int pos = 1;
        for (Character c : user.getBox()) {
            System.out.println("[" + pos + "]" + "[" + c.getRarity() + "] - " + c.getName());
            pos++;
        }

    }

    //EFFECTS: process input command.
    private void processPlayerCommand(String command) {
        if (command.equals("spent")) {
            System.out.println("You have used: " + user.getSpent() + " credits");
            processCommand("player");
        } else if (command.equals("box")) {
            printBox();
            System.out.println("Enter number to view card or -1 to return to previous option");
            int characterPos = input.nextInt();
            if (characterPos == -1) {
                doNothing();
            } else if (user.checkIDInList(characterPos)) {
                System.out.println(user.displayStatsAsString(characterPos));
            } else {
                System.out.println("The ID is either invalid or you do not have this ID in the box");
            }
            processCommand("player");
        } else if (command.equals("b")) {
            doNothing();
        } else {
            System.out.println("Invalid command, try again");
            processCommand("player");
        }
    }


}


