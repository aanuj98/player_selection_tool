package com.jetbrains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


class Players {
    public String name;
    public String team;
    public String type;
    public double points;
    public double price;

    public Players(String name, String team, String type, double points, double price) {
        this.name = name;
        this.team = team;
        this.type = type;
        this.points = points;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public double getPrice() {
        return price;
    }

    public double getPoints() {
        return points;
    }

    // Create an Arraylist to hold final player object
    public static ArrayList<Players> finalTeam = new ArrayList<>();

    public String toString() {
        return name + " " + team + " " + price + " " + points;
    }

    // Method to call the main function since all the code is in the main function
    static int count = 0;
    static void mainCaller(){
        if (count < 1){
            main(null);
        }
        count++;
    }
    public static void main(String[] args) {
        //Calls the main functions of all the files so the finalTeam arraylist has all the required data
        goalkeepers.mainCaller();
        forwards.mainCaller();
        midfielders.mainCaller();
        defenders.mainCaller();

        // Sort the Array using Collections API a final time to make sure they are from highest price to lowest player
        Collections.sort(finalTeam, new Comparator<Players>() {
            @Override
            public int compare(Players o1, Players o2) {
                return Double.valueOf(o2.price).compareTo(o1.price);
            }
        });

        int player_count = 1;
        for (Players finalPlayers : finalTeam) {
            System.out.println("Player: " + player_count);
            System.out.println("Name: " + finalPlayers.name + ";" + " Team: " + finalPlayers.team + ";" + " Points: " + finalPlayers.points + ";" + " Position: " + finalPlayers.type + ";" + " Price: £" + finalPlayers.price);
            player_count++;
        }
    }
}

