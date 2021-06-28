package com.vaadin.swrpgd6.app.backend.service;

import com.vaadin.swrpgd6.app.backend.entity.Attribute;
import com.vaadin.swrpgd6.app.backend.entity.PlayerCharacter;
import com.vaadin.swrpgd6.app.backend.entity.Skill;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseService {
    private String url = "jdbc:sqlserver://DESKTOP-3S2OPCN\\SQLEXPRESS;databaseName=characters";
    private String userName = "d6test ";
    private String password = "testtest";
    //This method will need to be updated as PlayerCharacter is expanded.  Likely, it will spin through an array or some other
    //datatype and insert the values so I'm not sending a million parameters to the method.

    public void upsert(PlayerCharacter playerCharacter) {
        if (PlayerCharacterService.getInstance().getHeldCharacter().getCharacterId() > 0) {
            update(playerCharacter);
            System.out.println("CharacterId must be greater than zero this character exists");
            //Update
        } else {
            insert(playerCharacter);
            System.out.println("CharacterId must not be greater than zero, this character doesn't exist yet");
            //Insert
        }
    }

    public ArrayList<PlayerCharacter> getList() {   //List<PlayerCharacter>

        ArrayList<PlayerCharacter> playerList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement;
            statement = connection.createStatement();
            System.out.println("Connected to Microsoft SQL Server to updateList");
            String sql = "SELECT CharacterId, CharacterName, Template, Background, Maker, MakeDate FROM CHARACTERS";
            ResultSet resultSet;
            resultSet = statement.executeQuery(sql);
            System.out.println("The query happened.");

            while (resultSet.next()) {
                //These are partial characters just for the grid
                PlayerCharacter playerCharacter = new PlayerCharacter(resultSet.getInt("CharacterId"),
                        resultSet.getString("characterName"),
                        resultSet.getString("Template"),
                        resultSet.getString("Background"),
                        resultSet.getString("Maker"),
                        resultSet.getString("MakeDate"));
                playerList.add(playerCharacter);
            }
            connection.close();
        } catch (SQLException e) {
            {
                System.out.println("oops, there's an error getting the grid list:");
                e.printStackTrace();
            }
        }
        return playerList;
    }
    public void recall(int characterId) {
        if (characterId > 0) {
            recallCharacter(characterId);
            recallCharacterSkills(characterId);
        }
    }

    public void recallCharacter(int characterId)  {  //List<PlayerCharacter>
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement;
            statement = connection.createStatement();
            System.out.println("Connected to Microsoft SQL Server to updateList");
            String sql = "SELECT * FROM CHARACTERS WHERE CharacterId = " + characterId;
            ResultSet resultSet;
            resultSet = statement.executeQuery(sql);
            System.out.println("The CHARACTER query for recalling a single character happened.");
//This is a lot!  Loading all elements of the character from the resultSet, this can probably be made more elegant
            while (resultSet.next()) {
                PlayerCharacterService.getInstance().getHeldCharacter().setCharacterId(resultSet.getInt("CharacterId"));
                PlayerCharacterService.getInstance().getHeldCharacter().setMakeDate(resultSet.getString("MakeDate"));
                PlayerCharacterService.getInstance().getHeldCharacter().setMaker(resultSet.getString("Maker"));
                PlayerCharacterService.getInstance().getHeldCharacter().setCharacterName(resultSet.getString("CharacterName"));
                PlayerCharacterService.getInstance().getHeldCharacter().setTemplate(resultSet.getString("Template"));
                PlayerCharacterService.getInstance().getHeldCharacter().setDex(resultSet.getInt("Dexterity"));
                PlayerCharacterService.getInstance().getHeldCharacter().setKno(resultSet.getInt("Knowledge"));
                PlayerCharacterService.getInstance().getHeldCharacter().setMec(resultSet.getInt("Mechanical"));
                PlayerCharacterService.getInstance().getHeldCharacter().setPer(resultSet.getInt("Perception"));
                PlayerCharacterService.getInstance().getHeldCharacter().setStr(resultSet.getInt("Strength"));
                PlayerCharacterService.getInstance().getHeldCharacter().setTec(resultSet.getInt("Technology"));
                PlayerCharacterService.getInstance().getHeldCharacter().setBackground(resultSet.getString("Background"));
                PlayerCharacterService.getInstance().getHeldCharacter().setSpecies(resultSet.getString("Species"));
                PlayerCharacterService.getInstance().getHeldCharacter().setDescription(resultSet.getString("Description"));
                PlayerCharacterService.getInstance().getHeldCharacter().setPersonality(resultSet.getString("Personality"));
                PlayerCharacterService.getInstance().getHeldCharacter().setObjectives(resultSet.getString("Objectives"));
                PlayerCharacterService.getInstance().getHeldCharacter().setQuote(resultSet.getString("Quote"));
                PlayerCharacterService.getInstance().getHeldCharacter().setAssociates(resultSet.getString("Associates"));
                PlayerCharacterService.getInstance().getHeldCharacter().setSpecialAbilities(resultSet.getString("SpecialAbilities"));
                PlayerCharacterService.getInstance().getHeldCharacter().setEquipment(resultSet.getString("Equipment"));
                PlayerCharacterService.getInstance().getHeldCharacter().setMove(resultSet.getInt("Move"));
                PlayerCharacterService.getInstance().getHeldCharacter().setCharacterPoints(resultSet.getInt("CharacterPoints"));
                PlayerCharacterService.getInstance().getHeldCharacter().setForcePoints(resultSet.getInt("ForcePoints"));
                PlayerCharacterService.getInstance().getHeldCharacter().setDarksidePoints(resultSet.getInt("DarksidePoints"));
                PlayerCharacterService.getInstance().getHeldCharacter().setCondition(resultSet.getString("Condition"));
                PlayerCharacterService.getInstance().getHeldCharacter().setForceSensitive(resultSet.getString("ForceSensitive"));
            }
            connection.close();
        } catch (SQLException e) {
            {
                System.out.println("oops, there's an error getting the character table elements:");
                e.printStackTrace();
            }
        }

    }

    public void insert(PlayerCharacter playerCharacter)  {//Create
        try {
            //Update all these to reference the singleton character maybe?
            String makeDate = playerCharacter.getMakeDate();
            String maker = playerCharacter.getMaker();
            String characterName = playerCharacter.getCharacterName();
            String characterTemplate = playerCharacter.getTemplate();

            int dex = playerCharacter.getDex();
            int kno = playerCharacter.getKno();
            int mec = playerCharacter.getMec();
            int per = playerCharacter.getPer();
            int str = playerCharacter.getStr();
            int tec = playerCharacter.getTec();

            String characterBackground = playerCharacter.getBackground();
            String species = playerCharacter.getSpecies();
            String description = playerCharacter.getDescription();
            String personality = playerCharacter.getPersonality();
            String objectives = playerCharacter.getObjectives();
            String quote = playerCharacter.getQuote();
            String associates = playerCharacter.getAssociates();
            String specialAbilities = playerCharacter.getSpecialAbilities();
            String equipment = playerCharacter.getEquipment();
            int move = playerCharacter.getMove();
            int characterPoints = playerCharacter.getCharacterPoints();
            int forcePoints = playerCharacter.getForcePoints();
            int darkSidePoints = playerCharacter.getDarksidePoints();
            String condition = playerCharacter.getCondition();
            String forceSensitive = playerCharacter.getForceSensitive();

            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connected to Microsoft SQL Server to insert Character");

            //This totally works, well I think so, I pasted it and ran it in MS SQL studio
            String sql = "INSERT INTO CHARACTERS (MakeDate, Maker, CharacterName, Template, Dexterity, Knowledge, Mechanical," +
                    "Perception, Strength, Technology, Background, Species, Description, Personality," +
                    "Objectives, Quote, Associates, SpecialAbilities, Equipment, Move, CharacterPoints," +
                    "ForcePoints, DarkSidePoints, Condition, ForceSensitive) "
                    + "VALUES ('" + makeDate + "' , '" + maker + "' , '" + characterName + "','" + characterTemplate + "'," + dex +
                    "," + kno + "," + mec + "," + per + "," + str + "," + tec + ",'" + characterBackground + "','" + species +
                    "','" + description + "','" + personality + "','" + objectives + "','" + quote + "','" + associates +
                    "','" + specialAbilities + "','" + equipment + "'," + move + "," + characterPoints + "," + forcePoints +
                    "," + darkSidePoints + ",'" + condition + "','" + forceSensitive + "') ";
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if (rows > 0) {  //this bit returns the new CharacterId of the above character and adds it to the instance.  This allows the skills to be saved properly
                System.out.println("Row has been added");
                if (PlayerCharacterService.getInstance().getHeldCharacter().getCharacterId() > 0){
                }else{
                    String sqlB = "SELECT MAX(CharacterId) AS CharacterId FROM CHARACTERS";
                    ResultSet resultSet;
                    resultSet = statement.executeQuery(sqlB);
                    while (resultSet.next()) {
                        PlayerCharacterService.getInstance().getHeldCharacter().setCharacterId(resultSet.getInt("CharacterId"));
                        System.out.println("And the New CharacterId is: " + PlayerCharacterService.getInstance().getHeldCharacter().getCharacterId());
                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("oops, there's an error:");
            e.printStackTrace();
        }
    }

    public void update(PlayerCharacter playerCharacter) {
        try {
            int characterId = playerCharacter.getCharacterId();
            String makeDate = playerCharacter.getMakeDate();
            String maker = playerCharacter.getMaker();
            String characterName = playerCharacter.getCharacterName();
            String characterTemplate = playerCharacter.getTemplate();

            int dex = playerCharacter.getDex();
            int kno = playerCharacter.getKno();
            int mec = playerCharacter.getMec();
            int per = playerCharacter.getPer();
            int str = playerCharacter.getStr();
            int tec = playerCharacter.getTec();

            String characterBackground = playerCharacter.getBackground();
            String species = playerCharacter.getSpecies();
            String description = playerCharacter.getDescription();
            String personality = playerCharacter.getPersonality();
            String objectives = playerCharacter.getObjectives();
            String quote = playerCharacter.getQuote();
            String associates = playerCharacter.getAssociates();
            String specialAbilities = playerCharacter.getSpecialAbilities();
            String equipment = playerCharacter.getEquipment();
            int move = playerCharacter.getMove();
            int characterPoints = playerCharacter.getCharacterPoints();
            int forcePoints = playerCharacter.getForcePoints();
            int darkSidePoints = playerCharacter.getDarksidePoints();
            String condition = playerCharacter.getCondition();
            String forceSensitive = playerCharacter.getForceSensitive();

            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connected to Microsoft SQL Server to update Character");

            String sql = "UPDATE CHARACTERS SET MakeDate='" + makeDate + "', Maker='" + maker + "', CharacterName= '" + characterName + "', Template= '" + characterTemplate + "', " +
                    "Dexterity= " + dex + ", Knowledge= " + kno + ", Mechanical= " + mec + ", Perception= " + per + ", Strength= " + str + ", Technology= " + tec + "," +
                    "Background= '" + characterBackground + "', Species= '" + species + "', Description= '" + description + "', Personality= '" + personality + "', Objectives= '" + objectives + "'," +
                    "Quote= '" + quote + "', Associates= '" + associates + "', SpecialAbilities= '" + specialAbilities + "', Equipment= '" + equipment + "', Move= '" + move + "'," +
                    "CharacterPoints= " + characterPoints + ", ForcePoints= " + forcePoints + ", DarkSidePoints= " + darkSidePoints + ", Condition= '" + condition + "', ForceSensitive= '" + forceSensitive +
                    "' WHERE CharacterId= " + characterId;

            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if (rows > 0) {
                System.out.println("update complete");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("oops, there's an error:");
            e.printStackTrace();
        }
    }

    public void dbDelete() {
        try {
            //Delete the PlayerCharacter
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement;
            statement = connection.createStatement();
            System.out.println("Connected to Microsoft SQL Server to delete a Character");
            String sql = "DELETE FROM CHARACTERS WHERE CharacterId = " + PlayerCharacterService.getInstance().getHeldCharacter().getCharacterId();

            statement.executeUpdate(sql);
            System.out.println("The CHARACTER query for deleting a single character happened. ");
            //            //Delete CharacterSkills before closing
            System.out.println("Connected to Microsoft SQL Server to delete a Character");
            sql = "DELETE FROM CHARACTERSKILLS WHERE CharacterId = " + PlayerCharacterService.getInstance().getHeldCharacter().getCharacterId();

            statement.executeUpdate(sql);
            System.out.println("The CHARACTERSKILLS query for deleting CHARACTERSKILLS has happened. ");

            connection.close();
        } catch (SQLException e) {
            {
                System.out.println("oops, there's an error getting the character table elements:");
                e.printStackTrace();
            }
        }
    }

    // Skill related Queries
    public ArrayList<Skill> querySkills(int characterId, String skillAttribute)
    {
        ArrayList<Skill> skillList = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement;
            statement = connection.createStatement();
            System.out.println("Connected to Microsoft SQL Server to recallSkillList");
            String sql = "SELECT * FROM CHARACTERSKILLS WHERE CharacterId = " +characterId+ " and Attribute = '"+skillAttribute+"'";
            ResultSet resultSet;
            resultSet = statement.executeQuery(sql);
            System.out.println("The " +skillAttribute+" CHARACTERSKILL query happened.");

//Several Queries in a row, one for each attribute and set the attribute skills to those skills
            while (resultSet.next())
            {
                Skill attributeSkill = new Skill(resultSet.getInt("SkillId"),
                        resultSet.getString("Name"),
                        resultSet.getString("Attribute"),
                        resultSet.getInt("skillLevel"),
                        resultSet.getString("Description"));
                skillList.add(attributeSkill);
            }

            connection.close();
        } catch (SQLException e) {
            {
                System.out.println("oops, there's an error getting CharacterSkills from " +skillAttribute+ ":");
                e.printStackTrace();
            }
        }
        return skillList;
    }

    public void recallCharacterSkills(int characterId)
    {
        PlayerCharacterService.getInstance().getHeldCharacter().setDexSkills(querySkills(characterId, "dex"));
        PlayerCharacterService.getInstance().getHeldCharacter().setKnoSkills(querySkills(characterId, "kno"));
        PlayerCharacterService.getInstance().getHeldCharacter().setMecSkills(querySkills(characterId, "mec"));
        PlayerCharacterService.getInstance().getHeldCharacter().setPerSkills(querySkills(characterId, "per"));
        PlayerCharacterService.getInstance().getHeldCharacter().setStrSkills(querySkills(characterId, "str"));
        PlayerCharacterService.getInstance().getHeldCharacter().setTecSkills(querySkills(characterId, "tec"));
    }

    public void dbInsertSkill(Skill skill)  //Create
    {
        try {     //these variables aren't used here, the getters are used in the SQL statement
            String skillName = skill.getSkillName();
            String skillAttribute = skill.getSkillAttribute();
            String skillDescription = skill.getSkillDescription();


            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connected to Microsoft SQL Server to insert Character");

            String sql = "INSERT INTO skills (name, attribute, description) "
                    + "VALUES ('" + skill.getSkillName() + "' , '" + skill.getSkillAttribute() + "' , '" + skill.getSkillDescription() + "' ) ";
            Statement statement = connection.createStatement();

            int rows = statement.executeUpdate(sql);

            if (rows > 0) {
                System.out.println("Row has been added");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("oops, there's an error:");
            e.printStackTrace();
        }
    }

    public ArrayList<Skill> getSkillList(String attribute)
    {
        ArrayList<Skill> skillList = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement;
            statement = connection.createStatement();
            System.out.println("Connected to Microsoft SQL Server to recallSkillList");
            String sql = "SELECT * FROM SKILLS WHERE Attribute = '"+attribute+"'";
            ResultSet resultSet;
            resultSet = statement.executeQuery(sql);
            System.out.println("The " +attribute+" CHARACTERSKILL query happened.");

//Several Queries in a row, one for each attribute and set the attribute skills to those skills
            while (resultSet.next())
            {
                Skill attributeSkill = new Skill(resultSet.getInt("SkillId"),
                        resultSet.getString("Name"),resultSet.getString("Attribute"),
                        resultSet.getString("Description"));
                skillList.add(attributeSkill);
            }

            connection.close();
        } catch (SQLException e) {
            {
                System.out.println("oops, there's an error getting CharacterSkills from " +attribute+ ":");
                e.printStackTrace();
            }
        }
        return skillList;
    }

    public void dbInsertCharacterSkill(Skill skill)  //Create for a new skill
    {
        try {     //refactor this, don't use these variables here, use the methods below
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connected to Microsoft SQL Server to insert Character");

            String sql = "INSERT INTO CHARACTERSKILLS (CharacterId, SkillId, Attribute, SkillLevel, Name, Description) "
                    + "VALUES (" + PlayerCharacterService.getInstance().getHeldCharacter().getCharacterId() + " , " + skill.getSkillId() + " , '" + skill.getSkillAttribute()
                    + "', " + skill.getSkillLevel() + ", '" +skill.getSkillName()+ "', '" +skill.getSkillDescription()+ "') ";
            Statement statement = connection.createStatement();

            int rows = statement.executeUpdate(sql);

            if (rows > 0) {
                System.out.println("CharacterSkill has been added");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("oops, there's an error adding CharacterSkill:");
            e.printStackTrace();
        }
    }

    public void dbUpsertCharacterSkills(Attribute attribute){
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connected to Microsoft SQL Server to DELETE/INSERT CharacterSkills from "+attribute.getAttributeName());

            String sql = "DELETE FROM CHARACTERSKILLS WHERE CharacterId = "+PlayerCharacterService.getInstance().getHeldCharacter().getCharacterId()+
                        " AND Attribute = '" +attribute.getAttributeName()+ "'";
            Statement statement = connection.createStatement();

            int rows = statement.executeUpdate(sql);

            if (rows > 0) {
                System.out.println("CharacterSkills for "+attribute.getAttributeName()+ " have been deleted");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("oops, there's an error deleting CharacterSkill: "+attribute.getAttributeName());
            e.printStackTrace();
        }

        try {     //refactor this, don't use these variables here, use the methods below
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connected to Microsoft SQL Server to insert Character");

            for (int i=0;i<attribute.getAttributeSkills().size();i++) {
                String sql = "INSERT INTO CHARACTERSKILLS (CharacterId, SkillId, Attribute, SkillLevel, Name, Description) "
                        + "VALUES (" + PlayerCharacterService.getInstance().getHeldCharacter().getCharacterId() + " , " + attribute.getAttributeSkills().get(i).getSkillId() +
                        " , '" + attribute.getAttributeSkills().get(i).getSkillAttribute()
                        + "', " + attribute.getAttributeSkills().get(i).getSkillLevel() + ", '" + attribute.getAttributeSkills().get(i).getSkillName() + "', '"
                        + attribute.getAttributeSkills().get(i).getSkillDescription() + "') ";
                Statement statement = connection.createStatement();

                int rows = statement.executeUpdate(sql);

                if (rows > 0) {
                    System.out.println("CharacterSkill has been added for "+attribute.getAttributeName());
                }
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("oops, there's an error adding CharacterSkills for " +attribute.getAttributeName());
            e.printStackTrace();
        }
    }

}

