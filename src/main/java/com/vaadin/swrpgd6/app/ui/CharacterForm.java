package com.vaadin.swrpgd6.app.ui;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.swrpgd6.app.backend.entity.PlayerCharacter;
import com.vaadin.swrpgd6.app.backend.entity.Skill;
import com.vaadin.swrpgd6.app.backend.service.DatabaseService;
import com.vaadin.swrpgd6.app.backend.service.GameService;
import com.vaadin.swrpgd6.app.backend.service.PlayerCharacterService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Route("form")
@CssImport("./styles/shared-styles.css")
@JsModule("@vaadin/vaadin-lumo-styles/presets/compact.js")
public class CharacterForm extends FormLayout {

    TextField characterName = new TextField("Name");
    TextField template = new TextField("Template");
    TextField maker = new TextField("Maker");
    TextField species = new TextField("Species");
    Select<String> forceSensitive = new Select<>();
    Select<String> condition = new Select<>();
    TextArea background = new TextArea("Background");
    TextArea description = new TextArea("Description");
    TextArea personality = new TextArea("Personality");
    TextArea objectives = new TextArea("Objectives");
    TextArea quote = new TextArea("Quote");
    TextArea associates = new TextArea("Associates");
    TextArea specialAbilities = new TextArea("Special Abilities");
    TextArea equipment = new TextArea("Equipment");

    IntegerField characterPointCount = new IntegerField("Character Points");
    IntegerField forcePointCount = new IntegerField("Force Points");
    IntegerField darkSideCount = new IntegerField("darkSidePoints");
    IntegerField moveRate = new IntegerField("Move");

    AttributeHeader dexHeader = new AttributeHeader("Dexterity");
    SkillCluster dexSkills = new SkillCluster();
    AttributeHeader perHeader = new AttributeHeader("Perception");
    SkillCluster    perSkills = new SkillCluster();
    AttributeHeader knoHeader = new AttributeHeader("Knowledge");
    SkillCluster knoSkills = new SkillCluster();
    AttributeHeader strHeader = new AttributeHeader("Strength");
    SkillCluster    strSkills = new SkillCluster();
    AttributeHeader mecHeader = new AttributeHeader("Mechanical");
    SkillCluster mecSkills = new SkillCluster();
    AttributeHeader tecHeader = new AttributeHeader("Technical");
    SkillCluster    tecSkills = new SkillCluster();

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Close");

    private CharacterForm() {
        addClassName("contact-form");
        //binder.bindInstanceFields(this);
        add(buildForm());
        DatabaseService databaseService = new DatabaseService();
        databaseService.recall(PlayerCharacterService.getInstance().getHeldCharacter().getCharacterId());
        loadCharacter();
    }
    private HorizontalLayout createHeader() {
        H1 logo = new H1("SWRPGD6 Tools");
        logo.addClassName("logo");
        Anchor logout = new Anchor("logout", "Log out");
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");
        return header;
    }

    private Component buildForm() {
        Div wrapperLayout = new Div(createHeader(),createButtonsLayout(),descriptiveFields(),pointsRow(),attributeRow());
        return wrapperLayout;
    }
    private HorizontalLayout createButtonsLayout() {
        H3 print = new H3("Ctrl+p to Print");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        close.addClickShortcut(Key.ESCAPE);
        save.addClickListener(event -> validateAndSave());    //playerCharacter
        delete.addClickListener(event -> validateAndDelete());
        close.addClickListener(event -> closeForm());

        return new HorizontalLayout(save, delete, close, print);
    }
    private HorizontalLayout attributeRow() {
        return new HorizontalLayout(attributeColumn1(),attributeColumn2(),attributeColumn3());
    }
    private HorizontalLayout descriptiveFields() {
        return new HorizontalLayout(descriptiveColumn1(),descriptiveColumn2(),descriptiveColumn3());
    }
    private VerticalLayout descriptiveColumn1() {
        return new VerticalLayout(characterName, maker, template, species, quote);
    }
    private VerticalLayout descriptiveColumn2() {
        background.setHeight("150px");
        background.setWidth("300px");
        background.setValueChangeMode(ValueChangeMode.LAZY);
        background.addValueChangeListener(e -> PlayerCharacterService.getInstance().getHeldCharacter().setBackground(background.getValue()));
        description.setHeight("75px");
        description.setWidth("300px");
        description.setValueChangeMode(ValueChangeMode.LAZY);
        description.addValueChangeListener(e -> PlayerCharacterService.getInstance().getHeldCharacter().setDescription(description.getValue()));
        personality.setHeight("75px");
        personality.setWidth("300px");
        personality.setValueChangeMode(ValueChangeMode.LAZY);
        personality.addValueChangeListener(e -> PlayerCharacterService.getInstance().getHeldCharacter().setPersonality(personality.getValue()));
        return new VerticalLayout(background, description, personality);
    }
    private VerticalLayout descriptiveColumn3() {
        associates.setHeight("75px");
        associates.setWidth("300px");
        associates.setValueChangeMode(ValueChangeMode.LAZY);
        associates.addValueChangeListener(e -> PlayerCharacterService.getInstance().getHeldCharacter().setAssociates(associates.getValue()));
        specialAbilities.setHeight("75px");
        specialAbilities.setWidth("300px");
        specialAbilities.setValueChangeMode(ValueChangeMode.LAZY);
        specialAbilities.addValueChangeListener(e -> PlayerCharacterService.getInstance().getHeldCharacter().setSpecialAbilities(specialAbilities.getValue()));
        equipment.setHeight("75px");
        equipment.setWidth("300px");
        equipment.setValueChangeMode(ValueChangeMode.LAZY);
        equipment.addValueChangeListener(e -> PlayerCharacterService.getInstance().getHeldCharacter().setEquipment(equipment.getValue()));
        objectives.setHeight("75px");
        objectives.setWidth("300px");
        objectives.setValueChangeMode(ValueChangeMode.LAZY);
        objectives.addValueChangeListener(e -> PlayerCharacterService.getInstance().getHeldCharacter().setObjectives(objectives.getValue()));
        return new VerticalLayout(associates, specialAbilities, equipment, objectives);
    }
    private HorizontalLayout pointsRow() {
        condition.setItems("Well", "Wounded", "Incapacitated","Mortally Wounded");
        condition.setLabel("Condition");
        condition.setValue("Well");
        //comboBox doesn't have a ValueChangeMode so this might be an issue, we'll see.
        condition.addValueChangeListener(e -> PlayerCharacterService.getInstance().getHeldCharacter().setCondition(condition.getValue()));
        forceSensitive.setItems("Yes", "No");
        forceSensitive.setLabel("Force Sensitive");
        forceSensitive.setValue("Yes");
        forceSensitive.addValueChangeListener(e -> PlayerCharacterService.getInstance().getHeldCharacter().setForceSensitive(forceSensitive.getValue()));
        return new HorizontalLayout(characterPointCount,forcePointCount,darkSideCount,moveRate,condition,forceSensitive);
    }
    private VerticalLayout attributeColumn1() {
        dexSkills.addSkill.addClickListener(event -> addCharacterSkill(dexSkills));
        perSkills.addSkill.addClickListener(event -> addCharacterSkill(perSkills));
        dexHeader.getAttr().addValueChangeListener(e -> dexHeader.getAttrD6().setValue(GameService.getInstance().pipsToD(dexHeader.getAttr().getValue())));
        perHeader.getAttr().addValueChangeListener(e -> perHeader.getAttrD6().setValue(GameService.getInstance().pipsToD(perHeader.getAttr().getValue())));
        dexSkills.skill1.deleteSkill.addClickListener(e -> adjustSkillLevel(dexSkills.skill1,"delete"));
        return new VerticalLayout(dexHeader.getHeader(),dexSkills.getCluster(), perHeader.getHeader(),perSkills.getCluster());
    }
    private VerticalLayout attributeColumn2() {
        knoSkills.addSkill.addClickListener(event -> addCharacterSkill(knoSkills));
        strSkills.addSkill.addClickListener(event -> addCharacterSkill(strSkills));
        knoHeader.getAttr().addValueChangeListener(e -> knoHeader.getAttrD6().setValue(GameService.getInstance().pipsToD(knoHeader.getAttr().getValue())));
        strHeader.getAttr().addValueChangeListener(e -> strHeader.getAttrD6().setValue(GameService.getInstance().pipsToD(strHeader.getAttr().getValue())));
        return new VerticalLayout(knoHeader.getHeader(),knoSkills.getCluster(), strHeader.getHeader(),strSkills.getCluster());
    }
    private VerticalLayout attributeColumn3() {
        mecSkills.addSkill.addClickListener(event -> addCharacterSkill(mecSkills));
        tecSkills.addSkill.addClickListener(event -> addCharacterSkill(tecSkills));
        mecHeader.getAttr().addValueChangeListener(e -> mecHeader.getAttrD6().setValue(GameService.getInstance().pipsToD(mecHeader.getAttr().getValue())));
        tecHeader.getAttr().addValueChangeListener(e -> tecHeader.getAttrD6().setValue(GameService.getInstance().pipsToD(tecHeader.getAttr().getValue())));
        return new VerticalLayout(mecHeader.getHeader(),mecSkills.getCluster(), tecHeader.getHeader(),tecSkills.getCluster());
    }
    private void adjustAttributeSkillPips(AttributeHeader attributeHeader)
    {
        attributeHeader.getAttrD6().setValue(GameService.getInstance().pipsToD(attributeHeader.getAttr().getValue()));
        loadAllCharacterSkills();
    }
    private void addCharacterSkill(SkillCluster skillCluster) {
        PlayerCharacterService.getInstance().addCharacterSkill(skillCluster.skillBox.getValue());
        loadAllCharacterSkills();
    }
    private void closeForm() {
        PlayerCharacterService.getInstance().initializeHeldCharacter();
        UI.getCurrent().navigate(MainView.class);
    }
    private void loadCharacter() {
        characterName.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getCharacterName());
        template.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getTemplate());
        maker.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getMaker());
        species.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getSpecies());
        forceSensitive.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getForceSensitive());
        condition.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getCondition());
        background.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getBackground());
        description.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getDescription());
        personality.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getPersonality());
        objectives.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getObjectives());
        quote.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getQuote());
        associates.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getAssociates());
        specialAbilities.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getSpecialAbilities());
        equipment.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getEquipment());
        characterPointCount.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getCharacterPoints());
        forcePointCount.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getForcePoints());
        darkSideCount.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getDarksidePoints());
        moveRate.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getMove());
        loadAttributeLevels();
        loadCharacterPoints();
        loadSkills();
        loadAllCharacterSkills();
    }
    private void loadCharacterPoints() {
        if (PlayerCharacterService.getInstance().getHeldCharacter().getCharacterId() > 0) {
            characterPointCount.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getCharacterPoints());
        }
    }
    private void loadAttributeLevels() {
        dexHeader.getAttr().setValue(PlayerCharacterService.getInstance().getHeldCharacter().getDex());
        dexHeader.getAttrD6().setValue(GameService.getInstance().pipsToD(dexHeader.getAttr().getValue()));
        knoHeader.getAttr().setValue(PlayerCharacterService.getInstance().getHeldCharacter().getKno());
        knoHeader.getAttrD6().setValue(GameService.getInstance().pipsToD(knoHeader.getAttr().getValue()));
        mecHeader.getAttr().setValue(PlayerCharacterService.getInstance().getHeldCharacter().getMec());
        mecHeader.getAttrD6().setValue(GameService.getInstance().pipsToD(mecHeader.getAttr().getValue()));
        perHeader.getAttr().setValue(PlayerCharacterService.getInstance().getHeldCharacter().getPer());
        perHeader.getAttrD6().setValue(GameService.getInstance().pipsToD(perHeader.getAttr().getValue()));
        strHeader.getAttr().setValue(PlayerCharacterService.getInstance().getHeldCharacter().getStr());
        strHeader.getAttrD6().setValue(GameService.getInstance().pipsToD(strHeader.getAttr().getValue()));
        tecHeader.getAttr().setValue(PlayerCharacterService.getInstance().getHeldCharacter().getTec());
        tecHeader.getAttrD6().setValue(GameService.getInstance().pipsToD(tecHeader.getAttr().getValue()));
    }
    private void loadSkills() {
        //this loads the available skills in the combobox, this will be replaced.
        DatabaseService databaseService = new DatabaseService();
        dexSkills.skillBox.setItemLabelGenerator(Skill::getSkillName);
        dexSkills.skillBox.setItems(databaseService.getSkillList("dex"));
        knoSkills.skillBox.setItemLabelGenerator(Skill::getSkillName);
        knoSkills.skillBox.setItems(databaseService.getSkillList("kno"));
        mecSkills.skillBox.setItemLabelGenerator(Skill::getSkillName);
        mecSkills.skillBox.setItems(databaseService.getSkillList("mec"));
        perSkills.skillBox.setItemLabelGenerator(Skill::getSkillName);
        perSkills.skillBox.setItems(databaseService.getSkillList("per"));
        strSkills.skillBox.setItemLabelGenerator(Skill::getSkillName);
        strSkills.skillBox.setItems(databaseService.getSkillList("str"));
        tecSkills.skillBox.setItemLabelGenerator(Skill::getSkillName);
        tecSkills.skillBox.setItems(databaseService.getSkillList("tec"));
    }
    private void loadAllCharacterSkills() {
        loadCharacterSkills(PlayerCharacterService.getInstance().getHeldCharacter().getDexSkills(),dexHeader, dexSkills);
        loadCharacterSkills(PlayerCharacterService.getInstance().getHeldCharacter().getMecSkills(),mecHeader, mecSkills);
        loadCharacterSkills(PlayerCharacterService.getInstance().getHeldCharacter().getPerSkills(),perHeader, perSkills);
        loadCharacterSkills(PlayerCharacterService.getInstance().getHeldCharacter().getKnoSkills(),knoHeader, knoSkills);
        loadCharacterSkills(PlayerCharacterService.getInstance().getHeldCharacter().getStrSkills(),strHeader, strSkills);
        loadCharacterSkills(PlayerCharacterService.getInstance().getHeldCharacter().getTecSkills(),tecHeader, tecSkills);
    }
    private void loadCharacterSkills(ArrayList<Skill> attributeSkills, AttributeHeader attributeHeader, SkillCluster skillCluster) {
        int i=0;
        loadSkillLine(i++,attributeSkills, attributeHeader, skillCluster.skill1);
        loadSkillLine(i++,attributeSkills, attributeHeader, skillCluster.skill2);
        loadSkillLine(i++,attributeSkills, attributeHeader, skillCluster.skill3);
        loadSkillLine(i++,attributeSkills, attributeHeader, skillCluster.skill4);
        loadSkillLine(i++,attributeSkills, attributeHeader, skillCluster.skill5);
        loadSkillLine(i++,attributeSkills, attributeHeader, skillCluster.skill6);
        loadSkillLine(i++,attributeSkills, attributeHeader, skillCluster.skill7);
        loadSkillLine(i++,attributeSkills, attributeHeader, skillCluster.skill8);
        loadSkillLine(i++,attributeSkills, attributeHeader, skillCluster.skill9);
        loadSkillLine(i,attributeSkills, attributeHeader, skillCluster.skill10);
    }
    private void loadSkillLine(int i, ArrayList<Skill> skills, AttributeHeader attributeHeader, SkillLine skillLine) {
        if (PlayerCharacterService.getInstance().getHeldCharacter().getCharacterId() > 0) {
            if (skills.isEmpty()) {
                System.out.println("No Skills Here");
            } else {
                if (i < skills.size()) {
                    skillLine.skillName.setValue(skills.get(i).getSkillName());
                    int totalPips = skills.get(i).getSkillLevel() + attributeHeader.getAttr().getValue();
                    skillLine.skillD6.setValue(GameService.getInstance().pipsToD(totalPips)); //change this with pip logic
                }
            }
        } else {
            if (skills.isEmpty()){
                System.out.println("New character, but no skills here");
            }else{
                if (i < skills.size()) {
                    skillLine.skillName.setValue(skills.get(i).getSkillName());
                    int totalPips = skills.get(i).getSkillLevel() + attributeHeader.getAttr().getValue();
                    skillLine.skillD6.setValue(GameService.getInstance().pipsToD(totalPips)); //change this with pip logic
                }
                System.out.println("New Character, but, SkillList was NOT empty");
            }
        }
    }
    private void saveCharacterSkills(ArrayList<Skill> skills, SkillCluster cluster) {
        //This method should send an Attriubte and the method should spin through the attribute skills.
    }
    private void validateAndSave(){
        validateCharacter();
        PlayerCharacterService.getInstance().saveCharacter();
        UI.getCurrent().navigate(MainView.class);
    }
    private void validateCharacter() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        PlayerCharacterService.getInstance().getHeldCharacter().setMakeDate(dtf.format(now));
        PlayerCharacterService.getInstance().getHeldCharacter().setMaker(maker.getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setCharacterName(characterName.getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setTemplate(template.getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setSpecies(species.getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setDex(dexHeader.getAttr().getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setKno(knoHeader.getAttr().getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setMec(mecHeader.getAttr().getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setPer(perHeader.getAttr().getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setStr(strHeader.getAttr().getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setTec(tecHeader.getAttr().getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setBackground(background.getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setDescription(description.getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setPersonality(personality.getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setObjectives(objectives.getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setQuote(quote.getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setAssociates(associates.getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setSpecialAbilities(specialAbilities.getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setEquipment(equipment.getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setMove(moveRate.getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setCharacterPoints(characterPointCount.getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setForcePoints(forcePointCount.getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setDarksidePoints(darkSideCount.getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setCondition(condition.getValue());
        PlayerCharacterService.getInstance().getHeldCharacter().setForceSensitive(forceSensitive.getValue());
    }
    private void validateAndDelete() {   //deleteCharacter is null if the character hasn't been created yet, since the
            if (PlayerCharacterService.getInstance().getHeldCharacter().getCharacterId() > 0) {
                PlayerCharacterService.getInstance().deleteCharacter();
                System.out.println("This is the old character" + PlayerCharacterService.getInstance().getHeldCharacter().getCharacterId()+ " and it has been deleted.");
                UI.getCurrent().navigate(MainView.class);
            }else{
                System.out.println("Character is new, nothing to delete");
                UI.getCurrent().navigate(MainView.class);
        }
        PlayerCharacterService.getInstance().initializeHeldCharacter();
    }
//    public void setPlayerCharacter(PlayerCharacter playerCharacter)  {
//        PlayerCharacterService.getInstance().holdCharacter(playerCharacter);
//    }
    public static abstract class CharacterFormEvent extends ComponentEvent<CharacterForm> {
        private PlayerCharacter playerCharacter;
        protected CharacterFormEvent(CharacterForm source, PlayerCharacter playerCharacter) {
            super(source, false);
            this.playerCharacter = playerCharacter;
        }
        public PlayerCharacter getPlayerCharacter() {
            return playerCharacter;
        }
    }
    public static class SaveEvent extends CharacterFormEvent {
        SaveEvent(CharacterForm source, PlayerCharacter playerCharacter) {
            super(source, playerCharacter);
        }
    }
    public static class DeleteEvent extends CharacterFormEvent {
        DeleteEvent(CharacterForm source, PlayerCharacter playerCharacter) {
            super(source, playerCharacter);
        }
    }
    public static class CloseEvent extends CharacterFormEvent {
        CloseEvent(CharacterForm source) {
            super(source, null);
        }
    }
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
    //**************************************************************************************************************************
    private void adjustSkillLevel(SkillLine skillLine, String command){
        switch (command){
            case "up":
                System.out.println("You want this skill to go up!");
            case "down":
                System.out.println("You want this skill to go down!");
            case "delete":
                System.out.println("You want this skill to go away!");
        }
    }
    //**************************************************************************************************************************
    class SkillCluster {
        //Use this to keep track of how many skill lines are used and add a skill accordingly
        //It's probably a better idea to use some other means of managing this, but I can't really think of it now.
        public ComboBox<Skill> skillBox = new ComboBox<>();
        Button addSkill = new Button("<");
        SkillLine skill1 = new SkillLine();
        SkillLine skill2 = new SkillLine();
        SkillLine skill3 = new SkillLine();
        SkillLine skill4 = new SkillLine();
        SkillLine skill5 = new SkillLine();
        SkillLine skill6 = new SkillLine();
        SkillLine skill7 = new SkillLine();
        SkillLine skill8 = new SkillLine();
        SkillLine skill9 = new SkillLine();
        SkillLine skill10 = new SkillLine();
        private HorizontalLayout skillSelectionLine()
        {
            addSkill.addThemeVariants(ButtonVariant.LUMO_SMALL);
            //addSkill.addClickListener(event -> addCharacterSkill());
            return new HorizontalLayout(skillBox, addSkill);
        }
        //(Skill::SkillgetSkillName());

        public HorizontalLayout skillName1() {
            return new HorizontalLayout(skill1.getSkillLine());
        }
        public HorizontalLayout skillName2() {
            return new HorizontalLayout(skill2.getSkillLine());
        }
        public HorizontalLayout skillName3() {
            return new HorizontalLayout(skill3.getSkillLine());
        }
        public HorizontalLayout skillName4() {
            return new HorizontalLayout(skill4.getSkillLine());
        }
        public HorizontalLayout skillName5() {
            return new HorizontalLayout(skill5.getSkillLine());
        }
        public HorizontalLayout skillName6() {
            return new HorizontalLayout(skill6.getSkillLine());
        }
        public HorizontalLayout skillName7() {
            return new HorizontalLayout(skill7.getSkillLine());
        }
        public HorizontalLayout skillName8() {
            return new HorizontalLayout(skill8.getSkillLine());
        }
        public HorizontalLayout skillName9() {
            return new HorizontalLayout(skill9.getSkillLine());
        }
        public HorizontalLayout skillName10() {
            return new HorizontalLayout(skill10.getSkillLine());
        }
        private VerticalLayout Skills() {
            return new VerticalLayout(skillSelectionLine(),skillName1(), skillName2(), skillName3(), skillName4(),
                    skillName5(), skillName6(), skillName7(),
                    skillName8(), skillName9(), skillName10());
        }
        public VerticalLayout getCluster() {
            return Skills();
        }
    }
    private class SkillLine {
        TextField skillName = new TextField();
        Button deleteSkill = new Button("x");
        TextField skillD6 = new TextField();
        Button plusSkill = new Button("+");
        Button minusSkill = new Button("-");

        private HorizontalLayout skillLine()  {
            skillName.setClassName("attributefield");
            skillName.setReadOnly(true);
            skillName.setClearButtonVisible(true);
            skillName.addThemeVariants(TextFieldVariant.LUMO_SMALL);
            skillD6.setReadOnly(true);
            skillD6.addThemeVariants(TextFieldVariant.LUMO_SMALL);
            deleteSkill.addThemeVariants(ButtonVariant.LUMO_SMALL);
            plusSkill.addThemeVariants(ButtonVariant.LUMO_SMALL);
            minusSkill.addThemeVariants(ButtonVariant.LUMO_SMALL);
            return new HorizontalLayout(skillName,skillD6); //,plusSkill,minusSkill,deleteSkill
        }

        public HorizontalLayout getSkillLine() {
            return skillLine();
        }
    }
    private class AttributeHeader {
        private FormLayout attributeLayout = new FormLayout();
        private IntegerField attr = new IntegerField();
        private TextField attrD6 = new TextField();
        private Button plusAttribute = new Button("+");
        private Button minusAttribute = new Button("-");
        private String attributeName;

        AttributeHeader(String attributeName) {
            this.attributeName = attributeName;
        }
        private FormLayout attributeHeader() {
            attr.setHasControls(true);
            attrD6.setWidth("75px");
            attrD6.addThemeVariants(TextFieldVariant.LUMO_SMALL.LUMO_SMALL);
            attrD6.setReadOnly(true);
            attr.setWidth("50px");
            attr.setMin(3);
            attributeLayout.addFormItem(attrD6, attributeName);
            attributeLayout.add(attr);  //,plusAttribute,minusAttribute
            return attributeLayout;
        }
        public Button getPlusAttribute(){
            return plusAttribute;
        }
        public Button getMinusAttribute(){
            return minusAttribute;
        }
        public IntegerField getAttr() {
            return attr;
        }
        public TextField getAttrD6() {
            return attrD6;
        }
        public FormLayout getHeader() {
            return attributeHeader();
        }
    }
}




