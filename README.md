# Gacha Simulator

## Introduction
The **Gacha Simulator** will create a simulation of what the user will receive with respect to probability. Likewise, the simulator
will reveal pre-determined cards to the user. Data and probability will determine the odds of each card and their rarity. The simulator 
will then store the cards into a list to be presented. Users will be able to view statistics of cards either from the banner or from their 
storage box.

## Who will use this application?
This application will be used for people who want to simulate the odds/attempts needed to receive a specific character. This application can also 
be used when users want to satisfy the need to **summon** or when users have insufficient amount of currency in an actual game.

## Interest
This project is something I have always wanted to do, but was unable to find time to do. 
The project is inspired from my interest for gacha games. I have used many characters from other video games to display what I am passionate about.

## Features

**List of what will be including**:
- Sound
- Gacha simulator
- Viewing characters + stats
- Stores User data via userID
- Currency system
- Images from video games

**Probability**
- 20 Units 
- Rarity Probability
    - *UR 5%*
    - *SR 20%*
    - *R 75%*
 
 ## User Stories
- As a user, I want to be able to summon on the banner and add the character to the user's box.
- As a user, I want to be able to view the card for what I have received
- As a user, I want to be able to view the card on the respected banner
- As a user, I want to be able to select a card from the list and view the stats for that card.
- As a user, I want to be able to save my **box, credits, userID, and other statistics**
- As a user, I want to be able to load my **box, credits, userID, and other statistics**

## Phase 4: Task 2

*"Test and design a class in your model package that is robust. You must have at least one method that throws a checked exception.
You must have one test for the case where the exception is expected and another where the exception is not expected."*

In Player Class, the Pay method throws an InsufficientValueException. In PlayerTest Class, the testCurrencySystem method tests for when 
the exception is caught and when the other not caught.

## Phase 4: Task 3

Looking at the UML class Diagram, I would have combined BannerCreation and dataBase into one function.
The methods within one another had duplicates and had the same functionality. For GUI, the methods did not inherit
the single point responsibility Principle. The GUI has a lot of coupling where it is directly getting character instead of
getting characters from pool.