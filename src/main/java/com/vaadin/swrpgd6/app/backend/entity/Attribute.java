package com.vaadin.swrpgd6.app.backend.entity;

import java.util.ArrayList;

public class Attribute extends AbstractEntity
{
    //really think about this and make sure it is needed, this might not be needed. . .
    //when does the character object exist?  Yes, this will be needed to clean up the PlayerCharacter class.
    //Getters and Setters will go here, I think.
    private String attributeName;
    private int attributeLevel;
    private ArrayList<Skill> attributeSkills;




}
