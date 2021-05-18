package com.vaadin.swrpgd6.app.backend.entity;

import java.util.ArrayList;


public class PlayerCharacter extends AbstractEntity
{

    private int characterId = 0;  //This will be the private key and foreign key connecting characters and skills on the DB
    private String makeDate = "";
    private String maker = "";  //the username of the person who created the character
    private String characterName = "";
    private String template = "";

    private Attribute dexterity = new Attribute("dex", 3);  //this is how attributes and skills will be handled in the future.
    private Attribute knowledge = new Attribute("kno", 3);
    private Attribute mechanical = new Attribute("mec", 3);
    private Attribute perception = new Attribute("per", 3);
    private Attribute strength = new Attribute("str", 3);
    private Attribute technology = new Attribute("tec", 3);

    private String background = "";
    private String species = "";
    private String description = "";
    private String personality = "";
    private String objectives = "";
    private String quote = "";
    private String associates = "";
    private String specialAbilities = "";
    private String equipment = "";
    private int move = 0;
    private int characterPoints = 54;  //this will be the number of pips available, new characters will have 54
    //I set characterPoints to 54 here as a shortcut, in the future startingPoints will be calculated by the business
    //    //class considering the template and species, I think.
    private int forcePoints = 0;
    private int darkSidePoints = 0;
    private String condition = "";
    private String forceSensitive = "";



    public PlayerCharacter(){}

    //This constructor is for new characters, no ID from the database yet.
    //This can be replaced by the constructor after, delete this once the updates are complete.
    public PlayerCharacter(String characterName, String template, String background, int dex, int kno, int mec,
                           int per, int str, int tec)
    {
        this.characterName = characterName;
        this.template = template;
        this.background = background;
        this.dexterity.setAttributeLevel(dex);
        this.knowledge.setAttributeLevel(kno);
        this.mechanical.setAttributeLevel(mec);
        this.perception.setAttributeLevel(per);
        this.strength.setAttributeLevel(str);
        this.technology.setAttributeLevel(tec);
    }
    public PlayerCharacter(String makeDate, String maker, String characterName, String template, int dexterity, int knowledge,
                           int mechanical, int perception, int strength, int technology, ArrayList<Skill> dexSkills, ArrayList<Skill> knoSkills,
                           ArrayList<Skill> mecSkills, ArrayList<Skill> perSkills, ArrayList<Skill> strSkills, ArrayList<Skill> tecSkills,
                           String background, String species, String description, String personality, String objectives, String quote,
                           String associates, String specialAbilities, String equipment, int move, int characterPoints, int forcePoints,
                           int darkSidePoints, String condition, String forceSensitive)
    {
        this.makeDate = makeDate;
        this.maker = maker;
        this.characterName = characterName;
        this.template = template;
        this.dexterity.setAttributeLevel(dexterity);
        this.knowledge.setAttributeLevel(knowledge);
        this.mechanical.setAttributeLevel(mechanical);
        this.perception.setAttributeLevel(perception);
        this.strength.setAttributeLevel(strength);
        this.technology.setAttributeLevel(technology);
        this.dexterity.setAttributeSkills(dexSkills);
        this.knowledge.setAttributeSkills(knoSkills);
        this.mechanical.setAttributeSkills(mecSkills);
        this.perception.setAttributeSkills(perSkills);
        this.strength.setAttributeSkills(strSkills);
        this.technology.setAttributeSkills(tecSkills);
        this.background = background;
        this.species = species;
        this.description = description;
        this.personality = personality;
        this.objectives = objectives;
        this.quote = quote;
        this.associates = associates;
        this.specialAbilities = specialAbilities;
        this.equipment = equipment;
        this.move = move;
        this.characterPoints = characterPoints;
        this.forcePoints = forcePoints;
        this.darkSidePoints = darkSidePoints;
        this.condition =condition;
        this.forceSensitive =forceSensitive;
    }
    //This constructor includes the characterId for importing existing characters from database
    //Constructor for creating the List for the Grid
    public PlayerCharacter(int characterId, String characterName, String template, String background, String maker, String makeDate)
    {
        this.characterId = characterId;
        this.characterName = characterName;
        this.template = template;
        this.background = background;
        this.maker = maker;
        this.makeDate = makeDate;


    }


    public PlayerCharacter(int characterId, String makeDate, String maker, String characterName, String template, int dexterity, int knowledge,
                           int mechanical, int perception, int strength, int technology, ArrayList<Skill> dexSkills, ArrayList<Skill> knoSkills,
                           ArrayList<Skill> mecSkills, ArrayList<Skill> perSkills, ArrayList<Skill> strSkills, ArrayList<Skill> tecSkills,
                           String background, String species, String description, String personality, String objectives, String quote,
                           String associates, String specialAbilities, String equipment, int move, int characterPoints, int forcePoints,
                           int darkSidePoints, String condition, String forceSensitive)
    {
        this.characterId = characterId;
        this.makeDate = makeDate;
        this.maker = maker;
        this.characterName = characterName;
        this.template = template;
        this.dexterity.setAttributeLevel(dexterity);
        this.knowledge.setAttributeLevel(knowledge);
        this.mechanical.setAttributeLevel(mechanical);
        this.perception.setAttributeLevel(perception);
        this.strength.setAttributeLevel(strength);
        this.technology.setAttributeLevel(technology);
        this.dexterity.setAttributeSkills(dexSkills);
        this.knowledge.setAttributeSkills(knoSkills);
        this.mechanical.setAttributeSkills(mecSkills);
        this.perception.setAttributeSkills(perSkills);
        this.strength.setAttributeSkills(strSkills);
        this.technology.setAttributeSkills(tecSkills);
        this.background = background;
        this.species = species;
        this.description = description;
        this.personality = personality;
        this.objectives = objectives;
        this.quote = quote;
        this.associates = associates;
        this.specialAbilities = specialAbilities;
        this.equipment = equipment;
        this.move = move;
        this.characterPoints = characterPoints;
        this.forcePoints = forcePoints;
        this.darkSidePoints = darkSidePoints;
        this.condition =condition;
        this.forceSensitive =forceSensitive;
    }


    public String toString()
    {
        return characterName + " " + template + " " + background + " " + dexterity.getAttributeLevel()  + " " + knowledge.getAttributeLevel()
                + " " + mechanical.getAttributeLevel() + " " + perception.getAttributeLevel()
                + " " + strength.getAttributeLevel() + " " + technology.getAttributeLevel();
    }
//Getters and Setters
    public int getCharacterId()
    {
        return characterId;
    }
    public void setCharacterId(int characterId)
    {
        this.characterId = characterId;
    }
    public String getBackground()
    {
        return background;
    }
    public void setBackground(String background)
    {
        System.out.println("you updated the background");
        this.background = background;
    }

    public String getTemplate()
    {
        return template;
    }
    public void setTemplate(String template)
    {
        this.template = template;
    }

    public String getCharacterName()
    {
        return characterName;
    }
    public void setCharacterName(String characterName)
    {
        this.characterName = characterName;
    }

    public String getMaker()
    {
        return maker;
    }
    public void setMaker(String maker)
    {
        this.maker = maker;
    }

    public String getMakeDate()
    {
        return makeDate;
    }
    public void setMakeDate(String makeDate)
    {
        this.makeDate = makeDate;
    }


    public Attribute getDexterity()
{
    return dexterity;
}

    public Attribute getKnowledge()
    {
        return knowledge;
    }

    public Attribute getStrength()
    {
        return strength;
    }

    public Attribute getMechanical()
    {
        return mechanical;
    }

    public Attribute getTechnology()
    {
        return technology;
    }

    public Attribute getPerception()
    {
        return perception;
    }
    //********************************************
    public int getDex()
    {
        return dexterity.getAttributeLevel();
    }
    public void setDex(int dex)
    {
        this.dexterity.setAttributeLevel(dex);
    }

    public int getKno()
    {
        return knowledge.getAttributeLevel();
    }
    public void setKno(int kno)
    {
        this.knowledge.setAttributeLevel(kno);
    }

    public int getStr()
    {
        return strength.getAttributeLevel();
    }
    public void setStr(int str)
    {
        this.strength.setAttributeLevel(str);
    }

    public int getMec()
    {
        return mechanical.getAttributeLevel();
    }
    public void setMec(int mec)
    {
        this.mechanical.setAttributeLevel(mec);
    }

    public int getTec()
    {
        return technology.getAttributeLevel();
    }
    public void setTec(int tec)
    {
        this.technology.setAttributeLevel(tec);
    }

    public int getPer()
    {
        return perception.getAttributeLevel();
    }
    public void setPer(int per)
    {
        this.perception.setAttributeLevel(per);
    }
    //********************************************

    public String getSpecies()
    {
        return species;
    }
    public void setSpecies(String species)
    {
        this.species = species;
    }

    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getPersonality()
    {
        return personality;
    }
    public void setPersonality(String personality)
    {
        this.personality = personality;
    }

    public String getObjectives()
    {
        return objectives;
    }
    public void setObjectives(String objectives)
    {
        this.objectives = objectives;
    }

    public String getQuote()
    {
        return quote;
    }
    public void setQuote(String quote)
    {
        this.quote = quote;
    }

    public String getAssociates()
    {
        return associates;
    }
    public void setAssociates(String associates)
    {
        this.associates = associates;
    }

    public String getSpecialAbilities()
    {
        return specialAbilities;
    }
    public void setSpecialAbilities(String specialAbilities)
    {
        this.specialAbilities = specialAbilities;
    }

    public String getEquipment()
    {
        return equipment;
    }
    public void setEquipment(String equipment)
    {
        this.equipment = equipment;
    }

    public int getMove()
    {
        return move;
    }
    public void setMove(int move)
    {
        this.move = move;
    }

    public int getCharacterPoints()
    {
        return characterPoints;
    }

    public void setCharacterPoints(int characterPoints)
    {
        this.characterPoints = characterPoints;
    }

    public int getForcePoints()
    {
        return forcePoints;
    }
    public void setForcePoints(int forcePoints)
    {
        this.forcePoints = forcePoints;
    }

    public int getDarksidePoints()
    {
        return darkSidePoints;
    }
    public void setDarksidePoints(int darksidePoints)
    {
        this.darkSidePoints = darksidePoints;
    }

    public String getCondition()
    {
        return condition;
    }
    public void setCondition(String condition)
    {
        this.condition = condition;
    }

    public String getForceSensitive()
    {
        return forceSensitive;
    }
    public void setForceSensitive(String forceSensitive)
    {
        this.forceSensitive = forceSensitive;
    }

    public ArrayList<Skill> getDexSkills() {
        return dexterity.getAttributeSkills();
    }
    public void setDexSkills(ArrayList<Skill> dexSkills) {
        this.dexterity.setAttributeSkills(dexSkills);
    }

    public ArrayList<Skill> getKnoSkills() {
        return knowledge.getAttributeSkills();
    }
    public void setKnoSkills(ArrayList<Skill> knoSkills) {
        this.knowledge.setAttributeSkills(knoSkills);
    }

    public ArrayList<Skill> getMecSkills() {
        return mechanical.getAttributeSkills();
    }
    public void setMecSkills(ArrayList<Skill> mecSkills) {
        this.mechanical.setAttributeSkills(mecSkills);
    }

    public ArrayList<Skill> getPerSkills() {
        return perception.getAttributeSkills();
    }
    public void setPerSkills(ArrayList<Skill> perSkills) {
        this.perception.setAttributeSkills(perSkills);
    }

    public ArrayList<Skill> getStrSkills() {
        return strength.getAttributeSkills();
    }
    public void setStrSkills(ArrayList<Skill> strSkills) {
        this.strength.setAttributeSkills(strSkills);
    }

    public ArrayList<Skill> getTecSkills() {
        return technology.getAttributeSkills();
    }
    public void setTecSkills(ArrayList<Skill> tecSkills) {
        this.technology.setAttributeSkills(tecSkills);
    }
}

