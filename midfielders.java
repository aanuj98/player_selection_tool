package com.jetbrains;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class midfielders extends Players {
    public midfielders(String name, String team, String type, double points, double price) {
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
        // Scanner for Goalkeepers
        try {
            File obj = new File("C:\\Users\\Aanuj Sharma\\OneDrive\\Documents\\UDC\\Data Structures\\Group Project\\midfielders.txt");
            Scanner read = new Scanner(obj);

            // Read the content of the file and parse it then pass it into an array then using that array's index pass it into original arraylist matching the name, price etc.
            while (read.hasNextLine()) {
                data = read.nextLine();
                tokens = data.split("#");
                sName = tokens[0];
                sTeam = tokens[1];
                sPoints = Double.parseDouble(tokens[2]);
                sPrice = Double.parseDouble(tokens[3]);
                stype = "Midfielders";
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
        /* Print the arraylist
        for (int i=0; i<players.size(); i++){
            System.out.println(players.get(i).name + " " + players.get(i).team + " " + players.get(i).points + " " + players.get(i).price + " " + players.get(i).type);
        }
       */
        // Create a new Arraylist to pass the players after sorting by constraints
        // Squad can only have players from less than 3 team
        // Budget is also addressed here
        ArrayList<Players> newPlayers = new ArrayList<>();
        double budget = 35;
        for (int i =0; i< players.size();i++) {
            int team_count = 0;
            int position_count = 0;
            for (int j = 0; j < newPlayers.size(); j++) {
                if (players.get(i).team.equals(newPlayers.get(j).team)) {
                    team_count++;
                }
                if (players.get(i).type.equals(newPlayers.get(j).type)) {
                    position_count++;

                }
            }
            if (team_count < 1 && players.get(i).price < budget && players.get(i).price < 7.5) {
                newPlayers.add(players.get(i));
                budget = budget - players.get(i).price;
            }
        }
        Players.finalTeam.addAll(newPlayers);

    }
}

