package com.vaadin.swrpgd6.app.backend.service;

import org.springframework.stereotype.Service;

@Service
public class PlayerCharacterService
{
 /*   private static final Logger LOGGER = Logger.getLogger(PlayerCharacterService.class.getName());
    private PlayerCharacterRepository playerCharacterRepository;
*/
    /*public PlayerCharacterService(PlayerCharacterRepository playerCharacterRepository)
    {
        this.playerCharacterRepository = playerCharacterRepository;

    }
    public List<PlayerCharacter> findAll()
    {
        return playerCharacterRepository.findAll();
    }

    public long count()
    {
        return playerCharacterRepository.count();
    }

    public void delete(PlayerCharacter contact)
    {
        playerCharacterRepository.delete(contact);
    }

    public void save(PlayerCharacter contact)
    {
        if (contact == null)
        {
            LOGGER.log(Level.SEVERE,
                    "Contact is null. Are you sure you have connected your form"
                    + " to the application?");
            return;
        }
        playerCharacterRepository.save(contact);
    }*/
    //*************************************************************************************************************
    /*@PostConstruct  //I might be able to make this work for the Character, otherwise i'll go right to the SQL DB
    public void populateTestData() {
        if (playerCharacterRepository.count() == 0) {
            //Random r = new Random(0);
                        playerCharacterRepository.saveAll(
                    Stream.of("Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
                            "Koen Johansen", "Alejandro Macdonald", "Angel Karlsson",
                            "Emily Stewart", "Corinne Davis", "Ryann Davis", "Yurem Jackson",
                            "Kelly Gustavsson",
                            "Eileen Walker", "Katelyn Martin", "Israel Carlsson",
                            "Danielle Watson", "Leland Harris", "Gunner Karlsen",
                            "Ann Andersson", "Remington Andersson", "Rene Carlsson",
                            "Jaydan Jackson", "Bernard Nilsen")
                            .map(name -> {
                                String[] split = name.split(" ");
                                PlayerCharacter character = new PlayerCharacter();
                                character.setCharacterName(split[0]);
                                character.setTemplate(split[1]);
                                character.setBackground("This will be your story, it begins here.");
                                character.setDex(12);
                                character.setStr(16);
                                character.setKno(36);
                                return character;
                            }).collect(Collectors.toList()));
        }
    }*/
    //***************************************************************************************************************

}

