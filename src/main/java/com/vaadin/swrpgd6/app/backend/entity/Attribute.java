package com.vaadin.swrpgd6.app.backend.entity;

import java.util.ArrayList;

public class Attribute extends AbstractEntity
{
    //really think about this and make sure it is needed, this might not be needed. . .
    //when does the character object exist?  Yes, this will be needed to clean up the PlayerCharacter class.
    //Getters and Setters will go here, I think.
    private String attributeName;
    private int attributeLevel = 3; //that's the minimum for any character always
    private ArrayList<Skill> attributeSkills = new ArrayList<Skill>();

    Attribute(String name, int level){
        this.attributeName = name;
        this.attributeLevel = level;
    }

    public String getAttributeName()
    {
        return attributeName;
    }

    public void setAttributeName(String attributeName)
    {
        this.attributeName = attributeName;
    }

    public int getAttributeLevel()
    {

        return attributeLevel;
    }

    public void setAttributeLevel(int attributeLevel)
    {
        this.attributeLevel = attributeLevel;
    }

    public Skill getSkill(int index)
    {
        return attributeSkills.get(index);
    }

    public void addSkill(Skill skill)
    {
        attributeSkills.add(skill);
    }

    public ArrayList<Skill> getAttributeSkills()
    {
        return attributeSkills;
    }
    public void setAttributeSkills(ArrayList<Skill> attributeSkills)
    {
        this.attributeSkills = attributeSkills;
    }


}
