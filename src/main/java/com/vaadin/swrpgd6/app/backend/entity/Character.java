package com.vaadin.swrpgd6.app.backend.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Character
{

    @NotNull
    @NotEmpty
    private String characterName = "";

    @NotNull
    @NotEmpty
    private String template = "";


    @NotNull
    @NotEmpty
    private String email = "";

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return characterName + " " + template;
    }
}
