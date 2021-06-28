package com.vaadin.swrpgd6.app.backend.service;


import com.vaadin.swrpgd6.app.backend.entity.PlayerCharacter;
import com.vaadin.swrpgd6.app.backend.entity.Skill;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public final class PlayerCharacterService {
    private static PlayerCharacterService Instance;
    private DatabaseService dbService = new DatabaseService();
    private PlayerCharacterService() {
    }
    public static PlayerCharacterService getInstance() {
        if(Instance == null) {
            Instance = new PlayerCharacterService();
        }
        return Instance;
    }
    private PlayerCharacter heldCharacter = new PlayerCharacter();
    public void holdCharacter(PlayerCharacter playerCharacter) {
        heldCharacter = playerCharacter;
    }
    public void saveCharacter()  {
        dbService.upsert(heldCharacter);  //upsert logic also checks for "new character" and queries the CharacterId for the following Skill inserts
        dbService.dbUpsertCharacterSkills(heldCharacter.getDexterity());
        dbService.dbUpsertCharacterSkills(heldCharacter.getKnowledge());
        dbService.dbUpsertCharacterSkills(heldCharacter.getMechanical());
        dbService.dbUpsertCharacterSkills(heldCharacter.getPerception());
        dbService.dbUpsertCharacterSkills(heldCharacter.getStrength());
        dbService.dbUpsertCharacterSkills(heldCharacter.getTechnology());

    }
    public void deleteCharacter() {
        dbService.dbDelete();
    }
    public PlayerCharacter getHeldCharacter() {
        return heldCharacter;
    }
    public void saveSkillTest(Skill skill) {
        //DatabaseService dbService = new DatabaseService();
        dbService.dbInsertCharacterSkill(skill);
    }
    public ArrayList<PlayerCharacter> getCharacterList() {
        return dbService.getList();
    }
    public void addCharacterSkill(Skill skill) {
        switch(skill.getSkillAttribute()){
            case "dex":
            if (heldCharacter.getDexSkills().contains(skill)){
                heldCharacter.getDexSkills().set(heldCharacter.getDexSkills().indexOf(skill), skill);
            }else{
                heldCharacter.getDexSkills().add(skill);
            }
            break;
            case "kno":
                if (heldCharacter.getKnoSkills().contains(skill)){
                    heldCharacter.getKnoSkills().set(heldCharacter.getKnoSkills().indexOf(skill), skill);
                }else{
                    heldCharacter.getKnoSkills().add(skill);
                }
            break;
            case "per":
                if (heldCharacter.getPerSkills().contains(skill)){
                    heldCharacter.getPerSkills().set(heldCharacter.getPerSkills().indexOf(skill), skill);
                }else{
                    heldCharacter.getPerSkills().add(skill);
                }
            break;
            case "str":
                if (heldCharacter.getStrSkills().contains(skill)){
                    heldCharacter.getStrSkills().set(heldCharacter.getStrSkills().indexOf(skill), skill);
                }else{
                    heldCharacter.getStrSkills().add(skill);
                }
            break;
            case "mec":
                if (heldCharacter.getMecSkills().contains(skill)){
                    heldCharacter.getMecSkills().set(heldCharacter.getMecSkills().indexOf(skill), skill);
                }else{
                    heldCharacter.getMecSkills().add(skill);
                }
            break;
            case "tec":
                if (heldCharacter.getTecSkills().contains(skill)){
                    heldCharacter.getTecSkills().set(heldCharacter.getTecSkills().indexOf(skill), skill);
                }else{
                    heldCharacter.getTecSkills().add(skill);
                }
            break;
        }
    }
    public void initializeHeldCharacter() {
        PlayerCharacter initCharacter = new PlayerCharacter();
        holdCharacter(initCharacter);
    }
}
