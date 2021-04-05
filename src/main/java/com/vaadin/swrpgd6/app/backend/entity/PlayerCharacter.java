package com.vaadin.swrpgd6.app.backend.entity;

//import javax.persistence.Entity;
//put string data in hashmap

import java.util.ArrayList;

public class PlayerCharacter extends AbstractEntity
{

    private int characterId;  //This will be the private key and foreign key connecting characters and skills on the DB
    private String accessDate;
    private String maker;  //the username of the person who created the character
    private String characterName = "";
    private String template = "";
    private String background = "";
    private int characterPoints = 54;  //this will be the number of pips available, new characters will have 54
    //I set characterPoints to 54 here as a shortcut, in the future startingPoints will be calculated by the business
    //class considering the template and species, I think.
    private int forcePoints;
//    private Attribute dex;  //this is how attributes and skills will be handled in the future.
//    private Attribute kno;
//    private Attribute mec;
//    private Attribute per;
//    private Attribute str;
//    private Attribute tec;
    private int dex;
    private ArrayList<Skill> dexSkills;  //these lists of skills should be populate from the linked table in the DB. . .
    private int kno;
    private ArrayList<Skill> knoSkills;
    private int mec;
    private ArrayList<Skill> mecSkills;
    private int per;
    private ArrayList<Skill> perSkills;
    private int str;
    private ArrayList<Skill> strSkills;
    private int tec;
    private ArrayList<Skill> tecSkills;
    private boolean isNew;  // this won't be needed "if characterID is null" will work.

    public PlayerCharacter()
    {

    }

    public PlayerCharacter(String characterName, String template, String background, int dex, int kno, int mec,
                            int per, int str, int tec)
    {
        this.characterName = characterName;
        this.template = template;
        this.background = background;
        this.dex = dex;
        this.kno = kno;
        this.mec = mec;
        this.per = per;
        this.str = str;
        this.tec = tec;
    }
    //I have removed the  @NotNull notations from each of the following, I may add them back, if needed.
    //                    @NotEmpty




    public String toString()
    {
        return characterName + " " + template + " " + background + " " + dex  + " " + kno + " " + mec + " " + per
                + " " + str + " " + tec;
    }
//Getters and Setters
    public String getBackground()
    {
        return background;
    }
    public void setBackground(String background)
    {
        this.background = background;
    }

    public String getTemplate()
    {
        return template;
    }
    public void setTemplate(String template)
    {
        this.template = template;
    }

    public String getCharacterName()
    {
        return characterName;
    }
    public void setCharacterName(String characterName)
    {
        this.characterName = characterName;
    }

    public int getDex()
{
    return dex;
}
    public void setDex(int dex)
    {
        this.dex = dex;
    }

    public int getKno()
    {
        return kno;
    }
    public void setKno(int kno)
    {
        this.kno = kno;
    }

    public int getStr()
    {
        return str;
    }
    public void setStr(int str)
    {
        this.str = str;
    }

    public int getMec()
    {
        return mec;
    }
    public void setMec(int mec)
    {
        this.mec = mec;
    }

    public int getTec()
    {
        return tec;
    }
    public void setTec(int tec)
    {
        this.tec = tec;
    }

    public int getPer()
    {
        return per;
    }
    public void setPer(int per)
    {
        this.per = per;
    }

}
