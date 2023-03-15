package com.jetbrains;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*;
public class Positions {

    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Player> sorted_price_players = new ArrayList<Player>();
    ArrayList<Player> sorted_score_players = new ArrayList<Player>();
    public Positions() {
    }

    public Positions(String filename,String position) {
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfStr = data.split("#");
                if((arrOfStr.length >= 4) && isNumeric(arrOfStr[2]) && isNumeric(arrOfStr[3])){
                    //System.out.format("%s %s %.2f %.2f\n",arrOfStr[0],arrOfStr[1],Float.parseFloat(arrOfStr[2]),Float.parseFloat(arrOfStr[3]));
                    this.players.add(new Player(arrOfStr[0],arrOfStr[1],Float.parseFloat(arrOfStr[2]),Float.parseFloat(arrOfStr[3]),position));
                }
            }
            this.sorted_price_players = new ArrayList<Player>(this.players);
            this.sorted_price_players = bubbleSortPrice(this.sorted_price_players);
            this.sorted_score_players = new ArrayList<Player>(this.players);
            this.sorted_score_players = bubbleSortScore(this.sorted_score_players);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }

    private static ArrayList<Player> bubbleSortPrice(ArrayList<Player> arr1)
    {
        int n = arr1.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr1.get(j).price < arr1.get(j+1).price)
                {
                    Player temp = new Player();
                    temp = arr1.get(j);
                    arr1.set(j,arr1.get(j+1));
                    arr1.set(j+1,temp);
                }
                else if(arr1.get(j).price == arr1.get(j+1).price)
                {
                    if(arr1.get(j).score < arr1.get(j+1).score)
                    {
                        Player temp = new Player();
                        temp = arr1.get(j);
                        arr1.set(j,arr1.get(j+1));
                        arr1.set(j+1,temp);
                    }
                }

        return arr1;
    }

    private static ArrayList<Player> bubbleSortScore(ArrayList<Player> arr)
    {
        int n = arr.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr.get(j).score < arr.get(j+1).score)
                {
                    Player temp = new Player();
                    temp = arr.get(j);
                    arr.set(j,arr.get(j+1));
                    arr.set(j+1,temp);
                }
                else if(arr.get(j).score == arr.get(j+1).score)
                {
                    if(arr.get(j).price < arr.get(j+1).price)
                    {
                        Player temp = new Player();
                        temp = arr.get(j);
                        arr.set(j,arr.get(j+1));
                        arr.set(j+1,temp);
                    }
                }
        return arr;
    }

    public void filterTeam(String team)
    {
        ArrayList<Player> new_arr = new ArrayList<Player>();
        for (int i=0;i<sorted_score_players.size();i++)
        {
            if(sorted_score_players.get(i).team.equals(team))
            {
                new_arr.add(sorted_score_players.get(i));
            }
        }
        sorted_score_players=new_arr;
    }

    public void filterName(String name)
    {
        ArrayList<Player> new_arr = new ArrayList<Player>();
        for (int i=0;i<sorted_score_players.size();i++)
        {
            if(sorted_score_players.get(i).name.toLowerCase().contains(name.toLowerCase()))
            {
                new_arr.add(sorted_score_players.get(i));
            }
        }
        sorted_score_players=new_arr;
    }

    public void display_limit(int limit)
    {
        if(limit>sorted_score_players.size() || limit == -1)
            limit=sorted_score_players.size();

        for (int i=0;i<limit;i++)
        {
            System.out.format("%d ",i+1);
            sorted_score_players.get(i).display();
        }
    }
}