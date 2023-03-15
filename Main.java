package com.jetbrains;

import java.util.*;

class Main extends Player{

    public static void main(String args[]) {
        Positions forwards = new Positions("C:\\Users\\Aanuj Sharma\\OneDrive\\Desktop\\DS\\forwards.txt", "FW");
        Positions midfielders = new Positions("C:\\Users\\Aanuj Sharma\\OneDrive\\Desktop\\DS\\midfielders.txt", "MF");
        Positions defenders = new Positions("C:\\Users\\Aanuj Sharma\\OneDrive\\Desktop\\DS\\defenders.txt", "DF");
        Positions goalkeepers = new Positions("C:\\Users\\Aanuj Sharma\\OneDrive\\Desktop\\DS\\goalkeepers.txt", "GK");
        // get new team, budget 100,
        float budget = 100;
        ArrayList<String> list_club = new ArrayList<String>();
        SelectedPlayer sel_fw_player1 = new SelectedPlayer();
        SelectedPlayer sel_fw_player2 = new SelectedPlayer();
        SelectedPlayer sel_fw_player3 = new SelectedPlayer();
        SelectedPlayer sel_mf_player1 = new SelectedPlayer();
        SelectedPlayer sel_mf_player2 = new SelectedPlayer();
        SelectedPlayer sel_mf_player3 = new SelectedPlayer();
        SelectedPlayer sel_mf_player4 = new SelectedPlayer();
        SelectedPlayer sel_mf_player5 = new SelectedPlayer();
        SelectedPlayer sel_df_player1 = new SelectedPlayer();
        SelectedPlayer sel_df_player2 = new SelectedPlayer();
        SelectedPlayer sel_df_player3 = new SelectedPlayer();
        SelectedPlayer sel_df_player4 = new SelectedPlayer();
        SelectedPlayer sel_df_player5 = new SelectedPlayer();
        SelectedPlayer sel_gk_player1 = new SelectedPlayer();
        SelectedPlayer sel_gk_player2 = new SelectedPlayer();

        Scanner user = new Scanner(System.in);
        System.out.println("Enter 1 if you want to display players selected automatically by Highest Price \nEnter 2 if you want to display players selected automatically by Highest Score \nEnter 3 if you want to select players manually ");
        int user_data = user.nextInt();
        // Get user input to see what option they want to pick
        if (user_data == 1) {
            System.out.println("You have selected 5 Defenders, 5 Midfielders, 3 Forwards, and 2 Goalkeepers with a budget of £100");
            // Calls the main file for sorting using the budget
            Players.mainCaller();
        }
        // Sorts the players by highest points
        if (user_data == 2) {

            float min_allocated_budget;

            float totalScore = getTotalScore(sel_fw_player1, sel_fw_player2, sel_fw_player3, sel_mf_player1, sel_mf_player2, sel_mf_player3, sel_mf_player4, sel_mf_player5, sel_df_player1, sel_df_player2, sel_df_player3, sel_df_player4, sel_df_player5, sel_gk_player1, sel_gk_player2);
            float totalPrice = getTotalPrice(sel_fw_player1, sel_fw_player2, sel_fw_player3, sel_mf_player1, sel_mf_player2, sel_mf_player3, sel_mf_player4, sel_mf_player5, sel_df_player1, sel_df_player2, sel_df_player3, sel_df_player4, sel_df_player5, sel_gk_player1, sel_gk_player2);

            // two high price forwards
            min_allocated_budget = get_min_allocated_budget(2, 5, 5, 2, list_club, forwards, midfielders, defenders, goalkeepers);
            sel_fw_player1 = get_player("score", forwards, budget, 0, list_club, min_allocated_budget);
            min_allocated_budget = get_min_allocated_budget(1, 5, 5, 2, sel_fw_player1.list_club, forwards, midfielders, defenders, goalkeepers);
            sel_fw_player2 = get_player("score", forwards, sel_fw_player1.remaining_budget, sel_fw_player1.next_index, sel_fw_player1.list_club, min_allocated_budget);
            // three high price midfielders
            min_allocated_budget = get_min_allocated_budget(1, 4, 5, 2, sel_fw_player2.list_club, forwards, midfielders, defenders, goalkeepers);
            sel_mf_player1 = get_player("score", midfielders, sel_fw_player2.remaining_budget, 0, sel_fw_player2.list_club, min_allocated_budget);
            min_allocated_budget = get_min_allocated_budget(1, 3, 5, 2, sel_mf_player1.list_club, forwards, midfielders, defenders, goalkeepers);
            sel_mf_player2 = get_player("score", midfielders, sel_mf_player1.remaining_budget, sel_mf_player1.next_index, sel_mf_player1.list_club, min_allocated_budget);
            min_allocated_budget = get_min_allocated_budget(1, 2, 5, 2, sel_mf_player2.list_club, forwards, midfielders, defenders, goalkeepers);
            sel_mf_player3 = get_player("score", midfielders, sel_mf_player2.remaining_budget, sel_mf_player2.next_index, sel_mf_player2.list_club, min_allocated_budget);
            // three high price defenders
            min_allocated_budget = get_min_allocated_budget(1, 2, 4, 2, sel_mf_player3.list_club, forwards, midfielders, defenders, goalkeepers);
            sel_df_player1 = get_player("score", defenders, sel_mf_player3.remaining_budget, 0, sel_mf_player3.list_club, min_allocated_budget);
            min_allocated_budget = get_min_allocated_budget(1, 2, 3, 2, sel_df_player1.list_club, forwards, midfielders, defenders, goalkeepers);
            sel_df_player2 = get_player("score", defenders, sel_df_player1.remaining_budget, sel_df_player1.next_index, sel_df_player1.list_club, min_allocated_budget);
            min_allocated_budget = get_min_allocated_budget(1, 2, 2, 2, sel_df_player2.list_club, forwards, midfielders, defenders, goalkeepers);
            sel_df_player3 = get_player("score", defenders, sel_df_player2.remaining_budget, sel_df_player2.next_index, sel_df_player2.list_club, min_allocated_budget);
            // a high price goalkeeper
            min_allocated_budget = get_min_allocated_budget(1, 2, 2, 1, sel_df_player3.list_club, forwards, midfielders, defenders, goalkeepers);
            sel_gk_player1 = get_player("score", goalkeepers, sel_df_player3.remaining_budget, 0, sel_df_player3.list_club, min_allocated_budget);
            // a forwards with remaining budget
            min_allocated_budget = get_min_allocated_budget(0, 2, 2, 1, sel_gk_player1.list_club, forwards, midfielders, defenders, goalkeepers);
            sel_fw_player3 = get_player("score", forwards, sel_gk_player1.remaining_budget, sel_fw_player2.next_index, sel_gk_player1.list_club, min_allocated_budget);
            // two midfielders with remaining budget
            min_allocated_budget = get_min_allocated_budget(0, 1, 2, 1, sel_fw_player3.list_club, forwards, midfielders, defenders, goalkeepers);
            sel_mf_player4 = get_player("score", midfielders, sel_fw_player3.remaining_budget, sel_mf_player3.next_index, sel_fw_player3.list_club, min_allocated_budget);
            min_allocated_budget = get_min_allocated_budget(0, 0, 2, 1, sel_mf_player4.list_club, forwards, midfielders, defenders, goalkeepers);
            sel_mf_player5 = get_player("score", midfielders, sel_mf_player4.remaining_budget, sel_mf_player4.next_index, sel_mf_player4.list_club, min_allocated_budget);
            // two defenders with remaining budget
            min_allocated_budget = get_min_allocated_budget(0, 0, 1, 1, sel_mf_player5.list_club, forwards, midfielders, defenders, goalkeepers);
            sel_df_player4 = get_player("score", defenders, sel_mf_player5.remaining_budget, sel_df_player3.next_index, sel_mf_player5.list_club, min_allocated_budget);
            min_allocated_budget = get_min_allocated_budget(0, 0, 0, 1, sel_df_player4.list_club, forwards, midfielders, defenders, goalkeepers);
            sel_df_player5 = get_player("score", defenders, sel_df_player4.remaining_budget, sel_df_player4.next_index, sel_df_player4.list_club, min_allocated_budget);
            // three defenders with remaining budget
            min_allocated_budget = get_min_allocated_budget(0, 0, 0, 0, sel_df_player5.list_club, forwards, midfielders, defenders, goalkeepers);
            sel_gk_player2 = get_player("score", goalkeepers, sel_df_player5.remaining_budget, sel_gk_player1.next_index, sel_df_player5.list_club, min_allocated_budget);

            totalScore = getTotalScore(sel_fw_player1, sel_fw_player2, sel_fw_player3, sel_mf_player1, sel_mf_player2, sel_mf_player3, sel_mf_player4, sel_mf_player5, sel_df_player1, sel_df_player2, sel_df_player3, sel_df_player4, sel_df_player5, sel_gk_player1, sel_gk_player2);
            totalPrice = getTotalPrice(sel_fw_player1, sel_fw_player2, sel_fw_player3, sel_mf_player1, sel_mf_player2, sel_mf_player3, sel_mf_player4, sel_mf_player5, sel_df_player1, sel_df_player2, sel_df_player3, sel_df_player4, sel_df_player5, sel_gk_player1, sel_gk_player2);

            System.out.println();
            System.out.println("Team with budget 100, with highest score priority:");
            sel_fw_player1.display_player();
            sel_fw_player2.display_player();
            sel_fw_player3.display_player();
            sel_mf_player1.display_player();
            sel_mf_player2.display_player();
            sel_mf_player3.display_player();
            sel_mf_player4.display_player();
            sel_mf_player5.display_player();
            sel_df_player1.display_player();
            sel_df_player2.display_player();
            sel_df_player3.display_player();
            sel_df_player4.display_player();
            sel_df_player5.display_player();
            sel_gk_player1.display_player();
            sel_gk_player2.display_player();
            System.out.format("Total score: %.2f\n", totalScore);
            System.out.format("Total price: %.2f\n", totalPrice);
            System.out.println();
        }

        // for manual use
        if (user_data == 3) {

        Scanner sc = new Scanner(System.in);
        String inputFlag, nameFilter, posFilter, teamFilter;
        int dataMax;
        inputFlag = ".";
        while ((!inputFlag.equals("Y")) && (!inputFlag.equals("N"))) {
            System.out.print("Do you want to filter the name? (Y/N) ");
            inputFlag = sc.next();
        }
        if (inputFlag.equals("Y")) {
            System.out.print("Filter name input: ");
            nameFilter = sc.next();
        } else
            nameFilter = "All";


        inputFlag = ".";
        while ((!inputFlag.equals("Y")) && (!inputFlag.equals("N"))) {
            System.out.print("Do you want to filter the position? (Y/N) ");
            inputFlag = sc.next();
        }
        if (inputFlag.equals("Y")) {
            posFilter = ".";
            while ((!posFilter.equals("MF")) && (!posFilter.equals("FW")) && (!posFilter.equals("DF")) && (!posFilter.equals("GK"))) {
                System.out.print("Filter position input (FW,MF,DF,GK): ");
                posFilter = sc.next();
            }
        } else
            posFilter = "All";


        inputFlag = ".";
        while ((!inputFlag.equals("Y")) && (!inputFlag.equals("N"))) {
            System.out.print("Do you want to filter the team? (Y/N) ");
            inputFlag = sc.next();
        }
        if (inputFlag.equals("Y")) {
            System.out.print("Filter team input (integer): ");
            teamFilter = sc.next();
        } else
            teamFilter = "All";


        inputFlag = ".";
        while ((!inputFlag.equals("Y")) && (!inputFlag.equals("N"))) {
            System.out.print("Do you want to limit the number of data shown? (Y/N) ");
            inputFlag = sc.next();
        }
        if (inputFlag.equals("Y")) {
            System.out.print("Input maximum number of data shown each position: ");
            dataMax = sc.nextInt();
        } else
            dataMax = -1;

        Positions filteredForwards = new Positions();
        Positions filteredMidfielders = new Positions();
        Positions filteredDefenders = new Positions();
        Positions filteredGoalkeepers = new Positions();

        filteredForwards = forwards;
        filteredMidfielders = midfielders;
        filteredDefenders = defenders;
        filteredGoalkeepers = goalkeepers;
        if (!teamFilter.equals("All")) {
            filteredForwards.filterTeam(teamFilter);
            filteredMidfielders.filterTeam(teamFilter);
            filteredDefenders.filterTeam(teamFilter);
            filteredGoalkeepers.filterTeam(teamFilter);
        }
        if (!nameFilter.equals("All")) {
            filteredForwards.filterName(nameFilter);
            filteredMidfielders.filterName(nameFilter);
            filteredDefenders.filterName(nameFilter);
            filteredGoalkeepers.filterName(nameFilter);
        }
        if (posFilter.equals("All")) {
            System.out.println();
            System.out.println("Forwards:");
            filteredForwards.display_limit(dataMax);
            System.out.println();
            System.out.println("Midfielders:");
            filteredMidfielders.display_limit(dataMax);
            System.out.println();
            System.out.println("Defenders:");
            filteredDefenders.display_limit(dataMax);
            System.out.println();
            System.out.println("Goalkeepers:");
            filteredGoalkeepers.display_limit(dataMax);
        } else {
            if (posFilter.equals("FW")) {
                System.out.println();
                System.out.println("Forwards:");
                filteredForwards.display_limit(dataMax);
            } else if (posFilter.equals("MF")) {
                System.out.println();
                System.out.println("Midfielders:");
                filteredMidfielders.display_limit(dataMax);
            } else if (posFilter.equals("DF")) {
                System.out.println();
                System.out.println("Defenders:");
                filteredDefenders.display_limit(dataMax);
            } else if (posFilter.equals("GK")) {
                System.out.println();
                System.out.println("Goalkeepers:");
                filteredGoalkeepers.display_limit(dataMax);
            } else {
                System.out.println("Position not valid");
            }
        }
    }
    }

    public static float getTotalPrice(SelectedPlayer p1,SelectedPlayer p2,SelectedPlayer p3,SelectedPlayer p4,SelectedPlayer p5,SelectedPlayer p6,SelectedPlayer p7,SelectedPlayer p8,SelectedPlayer p9,SelectedPlayer p10,SelectedPlayer p11,SelectedPlayer p12,SelectedPlayer p13,SelectedPlayer p14,SelectedPlayer p15)
    {
        float total = 0;
        total = p1.selectedPlayer.price+p2.selectedPlayer.price+p3.selectedPlayer.price+p4.selectedPlayer.price+p5.selectedPlayer.price+p6.selectedPlayer.price+p7.selectedPlayer.price+p8.selectedPlayer.price+p9.selectedPlayer.price+p10.selectedPlayer.price+p11.selectedPlayer.price+p12.selectedPlayer.price+p13.selectedPlayer.price+p14.selectedPlayer.price+p15.selectedPlayer.price;
        return total;
    }


    public static float getTotalScore(SelectedPlayer p1,SelectedPlayer p2,SelectedPlayer p3,SelectedPlayer p4,SelectedPlayer p5,SelectedPlayer p6,SelectedPlayer p7,SelectedPlayer p8,SelectedPlayer p9,SelectedPlayer p10,SelectedPlayer p11,SelectedPlayer p12,SelectedPlayer p13,SelectedPlayer p14,SelectedPlayer p15)
    {
        float total = 0;
        total = p1.selectedPlayer.score+p2.selectedPlayer.score+p3.selectedPlayer.score+p4.selectedPlayer.score+p5.selectedPlayer.score+p6.selectedPlayer.score+p7.selectedPlayer.score+p8.selectedPlayer.score+p9.selectedPlayer.score+p10.selectedPlayer.score+p11.selectedPlayer.score+p12.selectedPlayer.score+p13.selectedPlayer.score+p14.selectedPlayer.score+p15.selectedPlayer.score;
        return total;
    }

    public static SelectedPlayer get_player(String constraint,Positions pos,float budget_start,int start_idx,ArrayList<String> list_club,float min_alloc_budget)
    {
        int idx=start_idx;
        Player player;
        float budget;
        while(true)
        {
            if(constraint.equals("price"))
                player=pos.sorted_price_players.get(idx);
            else if(constraint.equals("score"))
                player=pos.sorted_score_players.get(idx);
            else
                player=pos.sorted_score_players.get(idx);

            budget = budget_start-player.price ;
            idx++;
            if((budget>=min_alloc_budget) && (count_occurence(list_club,player.team) < 3))
            {
                list_club.add(player.team);
                break;
            }
        }
        SelectedPlayer selPlayer = new SelectedPlayer(player,budget,idx,list_club);
        return selPlayer;
    }

    public static float get_min_allocated_budget(int n_fw,int n_mf,int n_df,int n_gk,ArrayList<String> list_club,Positions fw,Positions mf,Positions df,Positions gk)
    {
        float total_cheapest_price_list = 0;
        total_cheapest_price_list = get_min_allocated_budget_pos(total_cheapest_price_list,n_fw,list_club,fw);
        total_cheapest_price_list = get_min_allocated_budget_pos(total_cheapest_price_list,n_mf,list_club,mf);
        total_cheapest_price_list = get_min_allocated_budget_pos(total_cheapest_price_list,n_df,list_club,df);
        total_cheapest_price_list = get_min_allocated_budget_pos(total_cheapest_price_list,n_gk,list_club,gk);
        return total_cheapest_price_list;
    }

    public static float get_min_allocated_budget_pos(float total,int n_pos,ArrayList<String> list_club,Positions pos)
    {
        if(n_pos>0)
        {
            int i=0;
            int n_p=0;
            while(n_p < n_pos)
            {
                if(count_occurence(list_club,pos.sorted_price_players.get(pos.sorted_price_players.size()-1-i).team)<3)
                {
                    total=total+pos.sorted_price_players.get(pos.sorted_price_players.size()-1-i).price;
                    //System.out.format("tot: %f\n",total);
                    n_p++;
                }
                i++;
            }
        }
        return total;
    }

    public static int count_occurence(ArrayList<String> list_str, String str)
    {
        int occ = 0;
        for(int i=0;i<list_str.size();i++)
        {
            if(list_str.get(i).equals(str))
            {
                occ++;
            }
        }
        return occ;
    }
}

