package com.vaadin.swrpgd6.app.backend.service;
import org.springframework.stereotype.Service;

@Service
public final class GameService
{
    private static GameService Instance;
    private GameService() {
    }
    public static GameService getInstance() {
        if(Instance == null) {
            Instance = new GameService();
        }
        return Instance;
    }
    //This will handle all the "rules" of character creation pip to D logic, min/max levels.
    //eventually it will set limits based on species and template.
    public String pipsToD(int pips) {
        String Dice;
        if (pips %3 == 0) {
            Dice = pips / 3 + "D";
        }else{
            Dice = pips /3 + "D+ " + pips%3;
        }
        return Dice;
    }
    //The rule for pips to skill level is slightly different than pips to attribute level
    public String skillPipsToD(int attPips, int skillPips) {
        String Dice;
        if (skillPips %3 == 0) {
            Dice = skillPips / 3 + "D";
        }else{
            Dice = skillPips /3 + "D+ " + skillPips%3;
        }
        return Dice;
    }
}
