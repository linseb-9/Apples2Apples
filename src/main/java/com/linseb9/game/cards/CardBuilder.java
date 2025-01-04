package com.linseb9.game.cards;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CardBuilder {
    private String filePathGreen = "src/main/resources/data/greenApples.txt";
    private String getFilePathGRed = "src/main/resources/data/redApples.txt";
    private File greenApples;
    private File redApples;
    private Scanner greenScanner;
    private Scanner redScanner;

    public CardBuilder(){
        try{
            this.greenApples = new File(filePathGreen);
            this.redApples = new File(getFilePathGRed);
            this.greenScanner = new Scanner(greenApples);
            this.redScanner = new Scanner(redApples);
        }
        catch(NullPointerException | FileNotFoundException e) {
            System.out.println(e);

        }
    }

    public ArrayList<Card> buildGreenApples(){
        ArrayList<Card> greenApples = new ArrayList<Card>();
        while(greenScanner.hasNextLine()) {
            String row = greenScanner.nextLine();
            greenApples.add(buildGreenApple(row));
        }
        return greenApples;
    }

    public ArrayList<Card> buildRedApples() {
        ArrayList<Card> redApples = new ArrayList<Card>();
        while(redScanner.hasNextLine()) {
            String row = redScanner.nextLine();
            redApples.add(buildRedApple(row));
        }
        return redApples;
    }

    private Card buildGreenApple(String appleText) {
        try {
            int charStart = appleText.indexOf("[") + 1;
            int charEnd = appleText.indexOf("]");
            int synonymStart = appleText.indexOf("(") + 1;
            int synonymEnd = appleText.lastIndexOf(")");
            if (charStart == 0 || charEnd == -1) {
                throw new IllegalArgumentException("Characteristics not found in: " + appleText);
            }
            String characteristics = appleText.substring(charStart, charEnd);
            String synonym = appleText.substring(synonymStart, synonymEnd);

            return new GreenApple(characteristics, synonym);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private Card buildRedApple(String appleText) {
        try {
            int titleStart = appleText.indexOf("[") + 1;
            int titleEnd = appleText.indexOf("]");
            int descriptionStart = appleText.indexOf("-") + 1;
            if (titleStart == 0 || titleEnd == -1) {
                throw new IllegalArgumentException("Title not found in: " + appleText);
            }
            String title = appleText.substring(titleStart, titleEnd);
            String description = appleText.substring(descriptionStart);

            return new RedApple(title, description);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        CardBuilder builder = new CardBuilder();
        ArrayList<Card> testGreen = builder.buildGreenApples();
        for (Card card: testGreen) {
            System.out.println(card.attributes());
        }
        ArrayList<Card> testRed = builder.buildRedApples();
        for (Card card: testRed) {
            System.out.println(card.attributes());
        }

    }
}
