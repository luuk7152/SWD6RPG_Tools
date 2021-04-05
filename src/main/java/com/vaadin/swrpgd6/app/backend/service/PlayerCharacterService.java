package com.vaadin.swrpgd6.app.backend.service;


import com.vaadin.swrpgd6.app.backend.entity.PlayerCharacter;
import org.springframework.stereotype.Service;

@Service
public class PlayerCharacterService
{
    PlayerCharacter playerCharacter;

     public PlayerCharacterService()
    {

    }

    public void saveCharacter(PlayerCharacter playerCharacter)
    {
        DatabaseService dbService = new DatabaseService();
        dbService.dbInsert(playerCharacter);
    }

}
