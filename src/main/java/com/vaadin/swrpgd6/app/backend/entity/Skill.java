package com.vaadin.swrpgd6.app.backend.entity;

public class Skill extends AbstractEntity
{
    //Skills will exist on the DB in their own table the linked table will include the skillID, characterID, and skill Level
    //description and skill name will stay on the skill table
    private int skillId;
    private String skillName;
    private String skillAttribute;
    private String skillDescription;
    private int skillLevel;

    //Constructor for new skills added to Database
    public Skill(String skillName, String skillAttribute, String skillDescription)
    {
        //this.skillId = skillId;
        this.skillName = skillName;
        this.skillAttribute = skillAttribute;
        this.skillDescription = skillDescription;

    }
    //Constructor for existing skills specific to a character from the database
    public Skill(int skillId, String skillName, String skillAttribute, int skillLevel, String skillDescription)
    {
        this.skillId = skillId;
        this.skillName = skillName;
        this.skillAttribute = skillAttribute;
        this.skillDescription = skillDescription;
        this.skillLevel = skillLevel;

    }

    //Constructor for skill loaded from the DB for the comboboxes
    public Skill(int skillId, String skillName, String skillAttribute, String skillDescription)
    {
        this.skillId = skillId;
        this.skillName = skillName;
        this.skillDescription = skillDescription;
        this.skillAttribute = skillAttribute;
        //this.skillLevel = skillLevel;

    }


    public String getSkillName()
    {
        return skillName;
    }
    public void setSkillName(String skillName)
    {
        this.skillName = skillName;
    }

    public String getSkillAttribute()
    {
        return skillAttribute;
    }
    public void setSkillAttribute(String skillAttribute)
    {
        this.skillAttribute = skillAttribute;
    }

    public String getSkillDescription()
    {
        return skillDescription;
    }
    public void setSkillDescription(String skillDescription)
    {
        this.skillDescription = skillDescription;
    }

    public int getSkillId()
    {
        return skillId;
    }
    public void setSkillId(int skillId)
    {
        this.skillId = skillId;
    }

    public int getSkillLevel()
    {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel)
    {
        this.skillLevel = skillLevel;
    }
}
