package com.vaadin.swrpgd6.app.backend.entity;

//import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class PlayerCharacter extends AbstractEntity
{
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

    @NotNull
    @NotEmpty
    private String characterName = "";

    @NotNull
    @NotEmpty
    private String template = "";


    @NotNull
    @NotEmpty
    private String background = "";

    @NotNull
    @NotEmpty
    private int dex;

    @NotNull
    @NotEmpty
    private int kno;

    @NotNull
    @NotEmpty
    private int mec;

    @NotNull
    @NotEmpty
    private int per;

    @NotNull
    @NotEmpty
    private int str;

    @NotNull
    @NotEmpty
    private int tec;



    @Override
    public String toString() {
        return characterName + " " + template + " " + background + " " + dex  + " " + kno + " " + str;
    }
//Getters and Setters
    public String getBackground() {
        return background;
    }
    public void setBackground(String background) {
        this.background = background;
    }

    public String getTemplate() {
        return template;
    }
    public void setTemplate(String template) {
        this.template = template;
    }

    public String getCharacterName() {
        return characterName;
    }
    public void setCharacterName(String characterName) {
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
