package com.jetbrains;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class goalkeepers extends Players{

    public goalkeepers(String name, String team, String type, double points, double price) {
        super(name, team, type, points, price);
    }
    // Method to call the main function since all the code is in the main function
    static int count = 0;
    static void mainCaller(){
        if (count < 1){
            main(null);
        }
        count++;
    }

    public static void main(String[] args){
        String data;
        String sName;
        String sTeam;
        String stype;
        double sPoints;
        double sPrice;
        String[] tokens;

        // Create an Arraylist to hold player object
        ArrayList<Players> players = new ArrayList<>();
        // Scanner for Goalkeepers file
        try {
            File obj = new File("C:\\Users\\Aanuj Sharma\\OneDrive\\Documents\\UDC\\Data Structures\\Group Project\\goalkeepers.txt");
            Scanner read = new Scanner(obj);

            // Read the content of the file and parse it then pass it into an array then using that array's index pass it into original arraylist matching the name, price etc.
            while (read.hasNextLine()) {
                data = read.nextLine();
                tokens = data.split("#");
                sName = tokens[0];
                sTeam = tokens[1];
                sPoints = Double.parseDouble(tokens[2]);
                sPrice = Double.parseDouble(tokens[3]);
                stype = "Goalkeeper";
                players.add(new Players(sName, sTeam, stype, sPoints, sPrice));
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("File did not work");
            e.printStackTrace();
        }


        // Sort the Array using Collections API
        Collections.sort(players, new Comparator<Players>() {
            @Override
            public int compare(Players o1, Players o2) {
                return Double.valueOf(o2.price).compareTo(o1.price);
            }
        });

        // Create a new Arraylist to pass the players after sorting by constraints
        // Squad can only have players from less than 3 team
        // Budget is also addressed here
        ArrayList<Players> newPlayers = new ArrayList<>();
        double budget = 12;
        for (Players player : players) {
            int team_count = 0;
            for (Players newPlayer : newPlayers) {
                if (player.team.equals(newPlayer.team)) {
                    team_count++;
                }

            }
            if (team_count < 1 && player.price < budget && player.price < 7) {
                newPlayers.add(player);
                budget = budget - player.price;
            }
        }
        /* To print newPlayers Arraylist
        for (Players newPlayer : newPlayers) {
            System.out.println(newPlayer.name + " " + newPlayer.team + " " + newPlayer.points + " " + newPlayer.price + " " + newPlayer.type);
        }

         */
        // Add the Players to the final Arraylist which is going to display the results in the main
        Players.finalTeam.addAll(newPlayers);




        /* to add to a file
        try{
            FileWriter myWriter = new FileWriter("C:\\Users\\Aanuj Sharma\\OneDrive\\Documents\\UDC\\Data Structures\\Group Project\\finalTeam");
            for(Players str: newPlayers) {
                myWriter.append(str + System.lineSeparator());
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }

        catch (IOException e) {
            System.out.println("An error happened");
            e.printStackTrace();
        }
        for (Players newPlayer : finalTeam) {
            System.out.println(newPlayer.name + " " + newPlayer.team + " " + newPlayer.points + " " + newPlayer.price + " " + newPlayer.type);
        }

 */


    }
}
