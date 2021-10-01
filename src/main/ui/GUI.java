package ui;

import exceptions.InsufficientValueException;
import model.Banner;
import model.Character;
import model.Player;
import org.json.JSONException;
import org.omg.CORBA.BAD_INV_ORDER;
import persistence.JsonReader;
import persistence.JsonWriter;


import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUI implements ActionListener {

    private static final String JSON_STORE = "./data/player.json";
    private static final int SINGLE_SUMMON_COST = 5;
    private static final int MULTI_SUMMON_COST = 50;
    private static final String space = "                                                                  ";
    private JButton enterUserID;
    private JButton statistics;
    private JButton banner;
    private JButton save;
    private JButton load;
    private JFrame frame;
    private JPanel buttonPanel;
    private JLabel checkSet;
    private JTextField textField;
    private Banner pool;
    private Character card;
    private List<Character> cards;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Player user;
    private JFrame confirmed;
    private JLabel title;
    private JLabel status;
    private JPanel bannerButtonPanel;
    private JPanel bannerScreen;
    private JPanel messagePanel;
    private JLabel bannerStatus;
    private JPanel wallpaperPanel;
    private JLabel credits;
    private JButton credSet;
    private JButton viewBox;
    private JPanel playerButtonPanel;
    private JPanel playerScreenPanel;
    private JButton single;
    private JButton multi;
    private JButton back;
    private Clip bannerMusicClip;
    private JButton finish;
    private JButton next;
    private JPanel textBox;
    private JLabel characterImage;
    private JLabel bannerPicture;
    private JComboBox listOfBox;
    private List<String> characterBox;
    private JButton viewBanner;
    private JLabel multiA = new JLabel();
    private JLabel multiB = new JLabel();
    private JLabel multiC = new JLabel();
    private JLabel multiD = new JLabel();
    private JLabel multiE = new JLabel();
    private JLabel multiF = new JLabel();
    private JLabel multiG = new JLabel();
    private JLabel multiH = new JLabel();
    private JLabel multiI = new JLabel();
    private JLabel multiJ = new JLabel();
    private JLabel boxImage;
    private JLabel descriptionLabel;
    private JFrame viewPool;
    private JLabel poolImage;
    private JLabel poolDecriptionLabel;
    private JLabel wallpaperImage;

    //Class constructor is inspired by https://www.youtube.com/watch?v=5o3fMLPY7qY&ab_channel=AlexLee.
    //MODIFIES: this
    //EFFECTS: creates the frame of the GUI.
    public GUI() {

        frame = new JFrame();
        buttonPanel = new JPanel();
        frame.setPreferredSize(new Dimension(700, 500));
        beginningInit();
        initializeButton();
        initializeButtonPanel();
        initializeMessagePanel();
        initializeWallpaperPanel();
        createBanner();
        frame.add(messagePanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.PAGE_END);
        frame.add(wallpaperPanel, BorderLayout.CENTER);
        bannerButtonPanel.setVisible(false);
        bannerScreen.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Gacha Simulator");
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

    }

    //EFFECTS:Initializes beginning variables for GUI to start
    private void beginningInit() {
        pool = new Banner();
        card = new Character();
        cards = new ArrayList<>();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    //MODIFIES: this
    //EFFECTS:Initializes buttons for the main Window for the beginning of the GUI
    private void initializeButton() {
        enterUserID = new JButton("Create");
        enterUserID.addActionListener(this);

        load = new JButton("Load");
        load.addActionListener(this);

        statistics = new JButton("Player Statistics");
        statistics.addActionListener(this);
        statistics.setVisible(false);

        banner = new JButton("Banner");
        banner.addActionListener(this);
        banner.setVisible(false);

        save = new JButton("Save");
        save.addActionListener(this);
        save.setVisible(false);

        status = new JLabel("Welcome to the Gacha Simulator!");

    }


    //https://stackoverflow.com/questions/11165807/put-jbutton-in-bottom-right
    //MODIFIES: this
    //EFFECTS:Initializes beginning panel for the beginning of the GUI
    private void initializeButtonPanel() {
        buttonPanel.setBorder(BorderFactory.createEmptyBorder());
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(enterUserID, BorderLayout.SOUTH);
        buttonPanel.add(load, BorderLayout.SOUTH);
        buttonPanel.add(save, BorderLayout.SOUTH);
        buttonPanel.add(statistics, BorderLayout.SOUTH);
        buttonPanel.add(banner, BorderLayout.SOUTH);
    }

    //MODIFIES: this
    //EFFECTS:Initializes beginning panel for the beginning of the GUI
    private void initializeMessagePanel() {
        messagePanel = new JPanel();
        messagePanel.setBorder(BorderFactory.createEmptyBorder());
        messagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        messagePanel.add(status, BorderLayout.NORTH);
    }

    //EFFECTS:Initializes beginning panel for the beginning of the GUI
    private void initializeWallpaperPanel() {
        wallpaperPanel = new JPanel();
        wallpaperPanel.setBorder(BorderFactory.createEmptyBorder());
        wallpaperPanel.setLayout(new BorderLayout());
        wallpaperImage = new JLabel();
        resizeImage(wallpaperImage, "./data/Images/MainWallpaper.jpg",700,500);
        wallpaperPanel.add(wallpaperImage, BorderLayout.CENTER);

    }


    //https://docs.oracle.com/javase/tutorial/uiswing/components/textfield.html
    //EFFECTS: calls methods depending on the ActionEvent.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Create") {
            initializeMenu("Enter a 6 digit user ID", "Set", "Not Set", "Set ID");
        } else if (e.getActionCommand() == "Set") {
            setEnterUserID();
        } else if (e.getActionCommand() == "Banner") {
            openBannerWindow();
        } else if (e.getActionCommand() == "Save") {
            saveFile();
        } else if (e.getActionCommand() == "Ok") {
            confirmed.dispose();
        } else if (e.getActionCommand() == "Load") {
            loadFile();
        } else if (e.getActionCommand() == "Back") {
            back();
        } else {
            actionPerformedExtend(e);
        }
    }

    //EFFECTS: calls methods depending on the ActionEvent.
    private void actionPerformedExtend(ActionEvent e) {
        if (e.getActionCommand() == "Single Summon") {
            singleSummon();
        } else if (e.getActionCommand() == "Player Statistics") {
            playerStats();
        } else if (e.getActionCommand() == "Add") {
            initializeMenu("Enter any Amount of credits", "insert", "", "Set Cred");
        } else if (e.getActionCommand() == "insert") {
            addCred();
        } else if (e.getActionCommand() == "View") {
            displayStats();
        } else if (e.getActionCommand() == "Finish") {
            finish();
        } else if (e.getActionCommand() == "Multi Summon") {
            multiSummon();
        } else if (e.getActionCommand() == "View Banner") {
            bannerPoolWindow();
        } else if (e.getActionCommand() == "comboBoxChanged") {
            displayPoolStats();
        }
    }

    //MODIFIES: this
    //EFFECTS: Sets text to selected character Stats and shows character image. If character is a "UR", play sound.
    private void displayStats() {
        int index = listOfBox.getSelectedIndex();
        if (index == -1) {
            descriptionLabel.setVisible(true);
            descriptionLabel.setText("<html>" + "Invalid Character" + "</html>");
        } else {
            String stringDescription = user.displayStatsAsString(index + 1);
            Character character = user.getBox().get(index);
            descriptionLabel.setText("<html>" + stringDescription + "</html>");
            resizeImage(boxImage, character.getImage(), 300, 300);
            boxImage.setVisible(true);
            descriptionLabel.setVisible(true);
            frame.pack();
            if (character.getRarity().equals("UR")) {
                playUltraRareSound(character);
            }
        }
    }

    //https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html
    //MODIFIES: this
    //EFFECTS: creates a new JComboBox with the list of character names in user's box
    private void displayBox() {
        characterBox = new ArrayList<>();
        for (Character c : user.getBox()) {
            characterBox.add(c.getName());
        }
        listOfBox = new JComboBox(characterBox.toArray());
        playerScreenPanel.add(listOfBox, BorderLayout.NORTH);
        listOfBox.setVisible(true);
        frame.pack();


    }

    //MODIFIES: user, this
    //EFFECTS: adds the amount specified from textField to user's credit
    private void addCred() {
        try {
            int cred = Integer.parseInt(textField.getText());
            user.addCred(cred);
            if (cred <= 0) {
                checkSet.setText("Invalid Input");
            } else {
                checkSet.setText("Added!");
                credits.setText(space + "Credits: " + user.getCred());
            }
        } catch (NumberFormatException e) {
            checkSet.setText("Input is not a number!");
        }
    }

    //MODIFIES: this
    //EFFECTS: creates a new button panel with buttons and screen panel with user's list of characters.
    private void playerStats() {
        credSet = new JButton("Add");
        credSet.addActionListener(this);
        viewBox = new JButton("View");
        viewBox.addActionListener(this);
        JButton back = new JButton("Back");
        back.addActionListener(this);
        boxImage = new JLabel();
        descriptionLabel = new JLabel();
        playerScreenPanel = new JPanel();
        playerScreenPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        playerScreenPanel.setLayout(new BorderLayout(5, 5));
        initializePlayerButtonBanner();
        playerButtonPanel.add(viewBox, BorderLayout.WEST);
        playerButtonPanel.add(credSet, BorderLayout.WEST);
        playerButtonPanel.add(back, BorderLayout.WEST);
        playerScreenPanel.add(boxImage, BorderLayout.WEST);
        playerScreenPanel.add(descriptionLabel,BorderLayout.CENTER);
        frame.add(playerScreenPanel, BorderLayout.CENTER);
        frame.add(playerButtonPanel, BorderLayout.PAGE_END);
        displayBox();
        disableUserOptions();
        status.setText(space + "What would you like to do?");
    }

    //Helper Method
    //EFFECTS: initializes playerButtonpanel
    private void initializePlayerButtonBanner() {
        playerButtonPanel = new JPanel();
        playerButtonPanel.setBorder(BorderFactory.createEmptyBorder());
        playerButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

     /*
    MODIFIES: this
    EFFECTS: If user cred is sufficient, then it calls singleSummon(), stores the called card into user's box
    displays card's image on screen, if card is a "UR", play specified sound,
    otherwise (catches insufficientValueException) and bannerText is set to insufficient funds
      */
    private void singleSummon() {
        try {
            user.pay(SINGLE_SUMMON_COST);
            bannerStatus.setVisible(true);
            bannerMusicClip.stop();
            bannerButtonPanel.setVisible(false);
            bannerPicture.setVisible(false);
            card = pool.singleSummon();
            user.store(card);
            bannerStatus.setText("You received: " + "[" + card.getRarity() + "] " + card.getName());
            resizeImage(characterImage, card.getImage(), 300, 300);
            bannerScreen.add(characterImage);
            characterImage.setVisible(true);
            credits.setText(space + "Credits: " + user.getCred());
            if (pool.getUltraRareList().contains(card)) {
                playUltraRareSound(card);
            }
            finish.setVisible(true);
        } catch (InsufficientValueException e) {
            bannerStatus.setText("You do not have enough credits");
            bannerStatus.setVisible(true);
        }
        frame.pack();
    }

        /*
       MODIFIES: this
       EFFECTS: If user cred is sufficient, then it calls MultiSummon(), stores the called card into user's box,
       displays all characters image on screen, if card is a "UR", play specified sound,
       otherwise (catches insufficientValueException) bannerText is set to insufficient funds
        */
    private void multiSummon() {
        try {
            user.pay(MULTI_SUMMON_COST);
            bannerMusicClip.stop();
            bannerButtonPanel.setVisible(false);
            bannerPicture.setVisible(false);
            cards = pool.multiSummon();
            user.store(cards);
            for (Character c :cards) {
                if (pool.getUltraRareList().contains(c)) {
                    playUltraRareSound(c);
                    break;
                }
            }
            resizeMulti(cards);
            addMultiToScreen();
            finish.setVisible(true);
            credits.setText(space + "Credits: " + user.getCred());
        } catch (InsufficientValueException e) {
            bannerStatus.setText("You do not have enough credits");
            bannerStatus.setVisible(true);
        }
    }

    //Helper Method
    //MODIFIES: this
    //EFFECTS: adds 10 of the characters image to screen.
    private void addMultiToScreen() {
        bannerScreen.add(multiA);
        bannerScreen.add(multiB);
        bannerScreen.add(multiC);
        bannerScreen.add(multiD);
        bannerScreen.add(multiE);
        bannerScreen.add(multiF);
        bannerScreen.add(multiG);
        bannerScreen.add(multiH);
        bannerScreen.add(multiI);
        bannerScreen.add(multiJ);
        multiA.setVisible(true);
        multiB.setVisible(true);
        multiC.setVisible(true);
        multiD.setVisible(true);
        multiE.setVisible(true);
        multiF.setVisible(true);
        multiG.setVisible(true);
        multiH.setVisible(true);
        multiI.setVisible(true);
        multiJ.setVisible(true);

    }

    //Helper Method
    //MODIFIES: this
    //EFFECTS: calls resizeImage on 10 cards with width and height of 60
    private void resizeMulti(List<Character> cards) {
        resizeImage(multiA, cards.get(0).getImage(), 60, 60);
        resizeImage(multiB, cards.get(1).getImage(), 60, 60);
        resizeImage(multiC, cards.get(2).getImage(), 60,60);
        resizeImage(multiD, cards.get(3).getImage(), 60,60);
        resizeImage(multiE, cards.get(4).getImage(), 60,60);
        resizeImage(multiF, cards.get(5).getImage(), 60,60);
        resizeImage(multiG, cards.get(6).getImage(), 60,60);
        resizeImage(multiH, cards.get(7).getImage(), 60,60);
        resizeImage(multiI, cards.get(8).getImage(), 60,60);
        resizeImage(multiJ, cards.get(9).getImage(), 60,60);

    }


    //EFFECTS: play sound of specified character
    private void playUltraRareSound(Character card) {
        switch (card.getName()) {
            case "Lucian":
                initializeAudio("./data/Sound/Lucian.wav");
                break;
            case "Hero":
                initializeAudio("./data/Sound/Hero.wav");
                break;
            case "Joker":
                initializeAudio("./data/Sound/Joker.wav");
                break;
            case "Kevin":
                initializeAudio("./data/Sound/Kevin.wav");
                break;
            case "Goku":
                initializeAudio("./data/Sound/Goku.wav");
                break;
            case "Omar":
                initializeAudio("./data/Sound/Goku.wav");
                break;
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes variables and calls playAudio.
    private void initializeAudio(String s) {
        AudioInputStream audio;
        Clip audioClip;
        audio = getAudioInputStream(new File(s));
        audioClip = getClip(audio);
        playAudio(audioClip, audio);
    }


    //MODIFIES: this
    //EFFECTS: resizes Image with specified width and height
    private void resizeImage(JLabel holder, String path, int width, int height) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(path));
            Image dimensionImage = bufferedImage.getScaledInstance(width, height, 5);
            ImageIcon image = new ImageIcon(dimensionImage);
            holder.setIcon(image);
        } catch (IOException e) {
            System.out.println("Invalid File");
        }
    }

    //EFFECT: sets multiSummon's 10 character image to false and resets panels back as it was originally.
    private void finish() {
        finish.setVisible(false);
        characterImage.setVisible(false);
        bannerButtonPanel.setVisible(true);
        bannerPicture.setVisible(true);
        bannerStatus.setVisible(false);
        bannerMusicClip.start();
        multiA.setVisible(false);
        multiB.setVisible(false);
        multiC.setVisible(false);
        multiD.setVisible(false);
        multiE.setVisible(false);
        multiF.setVisible(false);
        multiG.setVisible(false);
        multiH.setVisible(false);
        multiI.setVisible(false);
        multiJ.setVisible(false);

    }

    //EFFECTS: If bannerScreen is visible, then close bannerScreen
    // else if playerScreen is visible, then close player screen. Return back to main menu
    public void back() {
        if (bannerButtonPanel.isVisible() || bannerScreen.isVisible()) {
            bannerButtonPanel.setVisible(false);
            bannerScreen.setVisible(false);
            bannerMusicClip.stop();
            bannerStatus.setVisible(false);
            status.setText(space + "What would you like to do?");
        } else if (playerButtonPanel.isVisible()) {
            playerButtonPanel.setVisible(false);
            playerScreenPanel.setVisible(false);
        }
        wallpaperPanel.setVisible(true);
        buttonPanel.setVisible(true);

    }

    //MODIFIES: this
    //EFFECTS: reads input from textField, determines if it is a number or not. If input is a number and is 6 digits
    // set create a new Player with specified number else if  input is not 6 digits, user is notified
    // that it is not 6 digits.Else if input is not a number, user is notified that it is not a number
    public void setEnterUserID() {
        try {
            int id = Integer.parseInt(textField.getText());
            if (checkTextField(id)) {
                user = new Player(id);
                checkSet.setText("UserID is set");
                credits = new JLabel(space + "Credits: " + user.getCred());
                messagePanel.add(credits, BorderLayout.EAST);
                initializeUserOptions();
                status.setText(space + "           Welcome, " + user.getUserID() + "!");
            } else {
                checkSet.setText("ID is not 6 digits");
            }
        } catch (NumberFormatException e) {
            checkSet.setText("ID is not a number");
        }

    }

    //Helper Method
    //EFFECTS: checks if id is 6 digits
    private boolean checkTextField(int id)  {
        if (id < 1000000 && id > 99999) {
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: constructs a new frame, panel, button, and textfield.
    public void initializeMenu(String promptText, String buttonText, String checkedSet, String windowTitle) {
        JFrame idMenu = new JFrame();

        textField = new JTextField(6);
        JLabel label = new JLabel(promptText);
        JButton enterKey = new JButton(buttonText);
        enterKey.addActionListener(this);
        checkSet = new JLabel(checkedSet);

        JPanel menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        menuPanel.setLayout(new GridLayout(5, 5));
        menuPanel.add(label);
        menuPanel.add(textField);
        menuPanel.add(enterKey);
        menuPanel.add(checkSet);

        idMenu.add(menuPanel, BorderLayout.CENTER);
        idMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        idMenu.setTitle(windowTitle);
        idMenu.setResizable(false);
        idMenu.pack();
        idMenu.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: creates new buttons for summoning, displays the button and screen.
    public void createBanner() {
        single = new JButton("Single Summon");
        single.addActionListener(this);
        multi = new JButton("Multi Summon");
        multi.addActionListener(this);
        back = new JButton("Back");
        back.addActionListener(this);
        finish = new JButton("Finish");
        finish.addActionListener(this);
        next = new JButton("Next");
        next.addActionListener(this);
        viewBanner = new JButton("View Banner");
        viewBanner.addActionListener(this);
        createBannerScreen();
        bannerButtonPanel = new JPanel();
        bannerButtonPanel.setBorder(BorderFactory.createLineBorder(Color.yellow));
        bannerButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bannerButtonPanel.add(single, BorderLayout.SOUTH);
        bannerButtonPanel.add(multi, BorderLayout.SOUTH);
        bannerButtonPanel.add(viewBanner,BorderLayout.SOUTH);
        bannerButtonPanel.add(back, BorderLayout.SOUTH);

    }

    //MODIFIES: this
    //EFFECTS: initializes the Banner screen and displays the banner picture
    private void createBannerScreen() {
        bannerScreen = new JPanel();
        bannerPicture = new JLabel();
        resizeImage(bannerPicture, "./data/Images/BannerPicture.png", 300, 300);
        textBox = new JPanel();
        textBox.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bannerScreen.setLayout(new GridBagLayout());
        bannerScreen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
        bannerStatus = new JLabel("");
        characterImage = new JLabel();
        characterImage.setLocation(25, 25);
        bannerScreen.add(bannerPicture);
        bannerPicture.setVisible(true);
        textBox.add(bannerStatus, BorderLayout.PAGE_START);
        textBox.add(finish, BorderLayout.SOUTH);
        textBox.add(next, BorderLayout.SOUTH);
        finish.setVisible(false);
        next.setVisible(false);
        characterImage.setVisible(false);
        bannerScreen.add(characterImage);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        bannerScreen.add(textBox, c);

    }


    //MODIFIES: this
    //EFFECTS: sets anything related to the banner screen visible and plays music
    private void openBannerWindow() {
        status.setText(space + "    Newly Released Banner!");
        bannerStatus.setText("");
        disableUserOptions();
        frame.add(bannerButtonPanel, BorderLayout.PAGE_END);
        bannerButtonPanel.setVisible(true);
        frame.add(bannerScreen, BorderLayout.CENTER);
        bannerScreen.setVisible(true);
        AudioInputStream bannerMusic = getAudioInputStream(new File("./data/Sound/BannerMusic.wav"));
        bannerMusicClip = getClip(bannerMusic);
        playAudio(bannerMusicClip, bannerMusic);
        frame.pack();
    }

    //MODIFIES: this
    //EFFECTS: Creates a new window that displays a dragdown list of all the Characters in the banner
    private void bannerPoolWindow() {
        characterBox = new ArrayList<>();
        viewPool = new JFrame();
        poolImage = new JLabel();
        poolDecriptionLabel = new JLabel();
        viewPool.setPreferredSize(new Dimension(700,500));
        viewPool.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewPool.setResizable(false);
        JPanel viewPoolPanel = new JPanel();
        JLabel rates = new JLabel("<html> UR: 5% \n SR: 25% \n R: 70% </html>");
        viewPoolPanel.setLayout(new BorderLayout());
        for (Character c : pool.getPool()) {
            characterBox.add(c.getName());
        }
        listOfBox = new JComboBox(characterBox.toArray());
        listOfBox.addActionListener(this);
        viewPoolPanel.add(listOfBox, BorderLayout.PAGE_START);
        viewPoolPanel.add(rates, BorderLayout.PAGE_END);
        viewPoolPanel.add(poolDecriptionLabel,BorderLayout.CENTER);
        viewPoolPanel.add(poolImage,BorderLayout.WEST);
        listOfBox.setVisible(true);
        viewPool.setVisible(true);
        viewPool.add(viewPoolPanel,BorderLayout.CENTER);
        viewPool.pack();

    }

    //EFFECTS: displays stats of all the character and character image
    private void displayPoolStats() {
        int index = listOfBox.getSelectedIndex();
        String stringDescription = pool.displayStatsAsString(index + 1);
        Character character = pool.getPool().get(index);
        poolDecriptionLabel.setText("<html>" + stringDescription + "</html>");
        resizeImage(poolImage,character.getImage(),300,300);
        poolImage.setVisible(true);
        poolDecriptionLabel.setVisible(true);
        frame.pack();
    }

    //EFFECTS: sets user options (Main Menu) to true
    private void initializeUserOptions() {
        enterUserID.setVisible(false);
        save.setVisible(true);
        banner.setVisible(true);
        statistics.setVisible(true);
    }

    //EFFECTS: sets user options (Main Menu) to false
    private void disableUserOptions() {
        buttonPanel.setVisible(false);
        wallpaperPanel.setVisible(false);
    }

    //Inspired by JsonSerializationDemo
    //EFFECTS: saves the data of Player to a JSON file. If exception is caught, title of window is changed
    public void saveFile() {
        initializeSaveLoadWindow();
        confirmed.setTitle("Save");
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            title.setText("File has been saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            title.setText("File has failed to be saved");
        }
    }


    //EFFECTS: creates window to notify users that file has been saved or loaded or not saved/loaded.
    private void initializeSaveLoadWindow() {
        confirmed = new JFrame();
        JButton ok = new JButton("Ok");
        ok.addActionListener(this);
        title = new JLabel();
        JPanel confirmWindow = new JPanel();
        confirmWindow.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        confirmWindow.add(title);
        confirmWindow.add(ok);
        confirmed.add(confirmWindow, BorderLayout.CENTER);
        confirmed.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        confirmed.setResizable(false);
        confirmed.pack();
        confirmed.setVisible(true);
    }

    //Inspired by JsonSerializationDemo
    //EFFECTS: loads the data of Player from JSON file. If exception is caught, title of window is changed
    private void loadFile() {
        initializeSaveLoadWindow();
        confirmed.setTitle("Load");
        try {
            user = jsonReader.read();
            title.setText("Loaded " + user.getUserID() + " from " + JSON_STORE);
            initializeUserOptions();
            status.setText(space + "           Welcome Back, " + user.getUserID() + "!");
            credits = new JLabel(space + "Credits: " + user.getCred());
            messagePanel.add(credits, BorderLayout.EAST);
        } catch (IOException e) {
            title.setText("Unable to read from file: " + JSON_STORE);
        } catch (JSONException e) {
            title.setText("There is no Load File.");
        }

    }

    //Helper Method
    //EFFECTS: creates a new AudioInputStream
    private AudioInputStream getAudioInputStream(File audioFile) {
        AudioInputStream audio = null;
        try {
            audio = AudioSystem.getAudioInputStream(audioFile);
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Invalid File");
        } catch (IOException e) {
            System.out.println("Invalid File");
        }
        return audio;
    }

    //https://www.codejava.net/coding/how-to-play-back-audio-in-java-with-examples for audio file.
    //Helper Method
    //EFFECTS: creates a new Clip from AudioInputStream.
    private Clip getClip(AudioInputStream audio) {
        Clip audioClip = null;
        AudioFormat format = audio.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        try {
            audioClip = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            System.out.println("invalid File");
        }
        return audioClip;
    }

    //EFFECTS: plays Audio/Music from audioClip
    private void playAudio(Clip audioClip, AudioInputStream audio) {
        try {
            audioClip.open(audio);
            audioClip.start();
        } catch (LineUnavailableException e) {
            System.out.println("invalid");
        } catch (IOException e) {
            System.out.println("invalid");
        }
    }
}
