package com.vaadin.swrpgd6.app.backend.entity;

public class Skill extends AbstractEntity
{
    //Skills will exist on the DB in their own table the linked table will include the skillID, characterID, and skill Level
    //description and skill name will stay on the skill table
    private String skillName;
    private int skillId;
    private int skillLevel;
    private String skillDescription;


}
