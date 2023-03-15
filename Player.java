package com.jetbrains;

public class Player {

    String name;
    String team;
    float score;
    float price;
    String position;
    public Player() {
    }

    public Player(String name_,String team_,float score_,float price_,String position_) {
        name = name_;
        team = team_;
        score = score_;
        price = price_;
        position = position_;
    }

    public void display() {
        System.out.format("%s %s %f %f %s\n",name,team,score,price,position);
    }
}

