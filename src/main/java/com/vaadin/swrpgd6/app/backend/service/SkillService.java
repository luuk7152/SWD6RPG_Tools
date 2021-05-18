package com.vaadin.swrpgd6.app.backend.service;

import com.vaadin.swrpgd6.app.backend.entity.Skill;

public class SkillService
{
    public static Skill heldSkill;
    public static void holdSkill(Skill skill)
    {
        heldSkill = skill;
    }

    public void saveSkill(Skill skill)
    {
        DatabaseService dbService = new DatabaseService();
        dbService.dbInsertSkill(skill);
    }


}
