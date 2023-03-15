package com.jetbrains;

import com.jetbrains.Player;

import java.util.*;
public class SelectedPlayer {
    Player selectedPlayer = new Player();
    float remaining_budget;
    int next_index;
    ArrayList<String> list_club = new ArrayList<String>();
    public SelectedPlayer() {
    }

    public SelectedPlayer(Player selectedPlayer_,float remaining_budget_,int next_index_, ArrayList<String> list_club_) {
        selectedPlayer=selectedPlayer_;
        remaining_budget=remaining_budget_;
        next_index=next_index_;
        list_club=list_club_;
    }

    public void display_player()
    {
        System.out.format("%s %s %.2f %.2f %s\n",selectedPlayer.name,selectedPlayer.team,selectedPlayer.score,selectedPlayer.price,selectedPlayer.position);
    }
}