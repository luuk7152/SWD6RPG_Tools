package com.vaadin.swrpgd6.app.ui;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.swrpgd6.app.backend.entity.PlayerCharacter;
import com.vaadin.swrpgd6.app.backend.entity.Skill;
import com.vaadin.swrpgd6.app.backend.service.GameService;
import com.vaadin.swrpgd6.app.backend.service.PlayerCharacterService;

@Route("testform")
@CssImport("./styles/shared-styles.css")
public class TestForm extends FormLayout
{
    private PlayerCharacter currentCharacter = PlayerCharacterService.getInstance().getHeldCharacter();

    //Form Elements
    private TextField characterName = new TextField("Name");
    TextField template = new TextField("Template");
    TextField background = new TextField("Background");
    IntegerField pipCount = new IntegerField("Total Pips");
//    IntegerField dex = new IntegerField("Dexterity");
//    TextField dexD6 = new TextField();
//    ComboBox<Skill> dexSkills = new ComboBox<Skill>("Dexterity Skills");
//    TextField dexSkill1 = new TextField();
//    IntegerField dexSkillLevel1 = new IntegerField();
//    TextField dexSkill2 = new TextField();
//    IntegerField dexSkillLevel2 = new IntegerField();
//    TextField dexSkill3 = new TextField();
//    IntegerField dexSkillLevel3 = new IntegerField();
//    TextField dexSkill4 = new TextField();
//    IntegerField dexSkillLevel4 = new IntegerField();
//    TextField dexSkill5 = new TextField();
//    IntegerField dexSkillLevel5 = new IntegerField();
//    TextField dexSkill6 = new TextField();
//    IntegerField dexSkillLevel6 = new IntegerField();
//    TextField dexSkill7 = new TextField();
//    IntegerField dexSkillLevel7 = new IntegerField();
//    TextField dexSkill8 = new TextField();
//    IntegerField dexSkillLevel8 = new IntegerField();
//    TextField dexSkill9 = new TextField();
//    IntegerField dexSkillLevel9 = new IntegerField();
//    TextField dexSkill10 = new TextField();
//    IntegerField dexSkillLevel10 = new IntegerField();



    IntegerField kno = new IntegerField("Knowledge");
    TextField knoD6 = new TextField();
    ComboBox<Skill> knoSkills = new ComboBox<Skill>("Knowledge Skills");
    IntegerField mec = new IntegerField("Mechanical");
    TextField mecD6 = new TextField();
    ComboBox<Skill> mecSkills = new ComboBox<Skill>("Mechanical Skills");
    IntegerField per = new IntegerField("Perception");
    TextField perD6 = new TextField();
    ComboBox<Skill> perSkills = new ComboBox<Skill>("Perception Skills");
    IntegerField str = new IntegerField("Strength");
    TextField strD6 = new TextField();
    ComboBox<Skill> strSkills = new ComboBox<Skill>("Strength Skills");
    IntegerField tec = new IntegerField("Technology");
    TextField tecD6 = new TextField();
    ComboBox<Skill> tecSkills = new ComboBox<Skill>("Technology Skills");
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Close");
    Binder<PlayerCharacter> binder = new BeanValidationBinder<>(PlayerCharacter.class);

    public TestForm()
    {
        addClassName("contact-form");
        binder.bindInstanceFields(this);
        add(buildForm());
        LoadCharacter();
    }

    private void LoadCharacter()
    {

        setPlayerCharacter(PlayerCharacterService.getInstance().getHeldCharacter());
        characterName.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getCharacterName());
        template.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getTemplate());
        background.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getBackground());
//        dex.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getDex());
        //load the combobox for skills here
        kno.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getKno());
        //load the combobox for skills here
        mec.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getMec());
        //load the combobox for skills here
        per.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getPer());
        //load the combobox for skills here
        str.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getStr());
        //load the combobox for skills here
        tec.setValue(PlayerCharacterService.getInstance().getHeldCharacter().getTec());
        //load the combobox for skills here
    }

    private Component buildForm()
    {
        Div wrapperLayout = new Div(createButtonsLayout(), verticalFields());
        return wrapperLayout;
    }
    private HorizontalLayout createButtonsLayout()
    {

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
       // save.addClickListener(event -> validateAndSave());    //playerCharacter
        delete.addClickListener(event -> validateAndDelete(currentCharacter));
        close.addClickListener(event -> UI.getCurrent().navigate(MainView.class));

        return new HorizontalLayout(save, delete, close);
    }

    private VerticalLayout verticalFields()
    {
        pipCount.setReadOnly(true);
//        dex.setHasControls(true);
//        //dex.setClearButtonVisible(true);
//        dex.setMin(3);
//        dex.addValueChangeListener(event -> dexD6.setValue(GameService.getInstance().pipsToD(dex.getValue())));
//        dexD6.setReadOnly(true);

        kno.setHasControls(true);
        //kno.setClearButtonVisible(true);
        kno.setMin(3);
        kno.addValueChangeListener(event -> knoD6.setValue(GameService.getInstance().pipsToD(kno.getValue())));
        knoD6.setReadOnly(true);

        mec.setHasControls(true);
        //mec.setClearButtonVisible(true);
        mec.setMin(3);
        mec.addValueChangeListener(event -> mecD6.setValue(GameService.getInstance().pipsToD(mec.getValue())));
        mecD6.setReadOnly(true);

        per.setHasControls(true);
        //per.setClearButtonVisible(true);
        per.setMin(3);
        per.addValueChangeListener(event -> perD6.setValue(GameService.getInstance().pipsToD(per.getValue())));
        perD6.setReadOnly(true);

        str.setHasControls(true);
        //str.setClearButtonVisible(true);
        str.setMin(3);
        str.addValueChangeListener(event -> strD6.setValue(GameService.getInstance().pipsToD(str.getValue())));
        strD6.setReadOnly(true);

        tec.setHasControls(true);
        //tec.setClearButtonVisible(true);
        tec.setMin(3);
        tec.addValueChangeListener(event -> tecD6.setValue(GameService.getInstance().pipsToD(tec.getValue())));
        tecD6.setReadOnly(true);
        return new VerticalLayout(characterName,
                template,
                background, pipCount, dexCluster(),
                //dex, dexD6, dexSkills,
                kno, knoD6, knoSkills,
                mec, mecD6, mecSkills,
                per, perD6, perSkills,
                str, strD6, strSkills,
                tec, tecD6, tecSkills);
    }


    private VerticalLayout dexCluster()
    {
        IntegerField dex = new IntegerField("Dexterity");
        TextField dexD6 = new TextField();
        ComboBox<Skill> dexSkills = new ComboBox<Skill>("Dexterity Skills");


        TextField dexSkill2 = new TextField();
        IntegerField dexSkillLevel2 = new IntegerField();
        TextField dexSkill3 = new TextField();
        IntegerField dexSkillLevel3 = new IntegerField();
        TextField dexSkill4 = new TextField();
        IntegerField dexSkillLevel4 = new IntegerField();
        TextField dexSkill5 = new TextField();
        IntegerField dexSkillLevel5 = new IntegerField();
        TextField dexSkill6 = new TextField();
        IntegerField dexSkillLevel6 = new IntegerField();
        TextField dexSkill7 = new TextField();
        IntegerField dexSkillLevel7 = new IntegerField();
        TextField dexSkill8 = new TextField();
        IntegerField dexSkillLevel8 = new IntegerField();
        TextField dexSkill9 = new TextField();
        IntegerField dexSkillLevel9 = new IntegerField();
        TextField dexSkill10 = new TextField();
        IntegerField dexSkillLevel10 = new IntegerField();


        dex.setHasControls(true);
        //dex.setClearButtonVisible(true);
        dex.setMin(3);
        dex.addValueChangeListener(event -> dexD6.setValue(GameService.getInstance().pipsToD(dex.getValue())));
        dexD6.setReadOnly(true);

       // skillCluster dexter = new skillCluster();  ,dexter.getCluster()
     return new VerticalLayout(dex,dexD6,dexSkills,dexSkill2,dexSkillLevel2,dexSkill3,dexSkillLevel3,dexSkill4,dexSkillLevel4
                                                    ,dexSkill5,dexSkillLevel5,dexSkill6,dexSkillLevel6,dexSkill7,dexSkillLevel7
                                                    ,dexSkill8,dexSkillLevel8,dexSkill9,dexSkillLevel9,dexSkill10,dexSkillLevel10);
    }

//    private void validateAndSave()             //PlayerCharacter playerCharacter
//    {
//        //use this date method to date/time stamp every character when saved.
////        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
////        LocalDateTime now = LocalDateTime.now();
////        System.out.println(dtf.format(now));
//
//        //this works but there is currently no validation besides the constraints of the fields integer, text, etc.
//        //Also need to check for existing character and do an update vs save
//        System.out.println("Well, the button worked, that's something right?");
//        int scID = PlayerCharacterService.getInstance().getHeldCharacter().getCharacterId();
//        PlayerCharacter saveCharacter = new PlayerCharacter(scID ,characterName.getValue(), template.getValue(),
//                background.getValue(),dex.getValue(),kno.getValue(),
//                mec.getValue(),per.getValue(),str.getValue(),tec.getValue());
//        //PlayerCharacterService playerCharacterService = new PlayerCharacterService();
//        PlayerCharacterService.getInstance().saveCharacter(saveCharacter);
//        UI.getCurrent().navigate(MainView.class);
//
//    }

    private void validateAndDelete(PlayerCharacter deleteCharacter)
    {   //deleteCharacter is null if the character hasn't been created yet, since the
        if (deleteCharacter == null)
        {
            System.out.println("Character must be new, nothing to delete.");
            UI.getCurrent().navigate(MainView.class);
        }
        else
        {
            if (deleteCharacter.getCharacterId() > 0)
            {
                System.out.println("This is the old character" + deleteCharacter.getCharacterId());
                UI.getCurrent().navigate(MainView.class);
            }
            else
            {
                System.out.println("not totally sure what happened here, character is neither old nor new");
                UI.getCurrent().navigate(MainView.class);
            }
        }
    }


    public void setPlayerCharacter(PlayerCharacter playerCharacter)  //This may not be needed
    {
        this.currentCharacter = playerCharacter;
        binder.readBean(playerCharacter);
    }

    public static abstract class CharacterFormEvent extends ComponentEvent<CharacterForm>
    {
        private PlayerCharacter playerCharacter;
        protected CharacterFormEvent(CharacterForm source, PlayerCharacter playerCharacter)
        {
            super(source, false);
            this.playerCharacter = playerCharacter;
        }

        public PlayerCharacter getPlayerCharacter()
        {
            return playerCharacter;
        }
    }

//    public static class SaveEvent extends CharacterForm.CharacterFormEvent
//    {
//        SaveEvent(CharacterForm source, PlayerCharacter playerCharacter)
//        {
//            super(source, playerCharacter);
//        }
//    }
//
//    public static class DeleteEvent extends CharacterForm.CharacterFormEvent
//    {
//        DeleteEvent(CharacterForm source, PlayerCharacter playerCharacter)
//        {
//            super(source, playerCharacter);
//        }
//    }
//
//    public static class CloseEvent extends CharacterForm.CharacterFormEvent
//    {
//        CloseEvent(CharacterForm source)
//        {
//            super(source, null);
//        }
//    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener)
    {
        return getEventBus().addListener(eventType, listener);
    }



}

class skillzCluster
{
    private HorizontalLayout dexSkillz()
    {
        TextField dexSkill1 = new TextField();
            dexSkill1.setReadOnly(true);
            dexSkill1.setClearButtonVisible(true);
        IntegerField dexSkillLevel1 = new IntegerField();
        dexSkillLevel1.setHasControls(true);

        TextField dexSkill2 = new TextField();
            dexSkill2.setReadOnly(true);
            dexSkill2.setClearButtonVisible(true);
        IntegerField dexSkillLevel2 = new IntegerField();
        dexSkillLevel2.setHasControls(true);

            TextField dexSkill3 = new TextField();
            dexSkill3.setReadOnly(true);
            dexSkill3.setClearButtonVisible(true);
        IntegerField dexSkillLevel3 = new IntegerField();
        dexSkillLevel3.setHasControls(true);

        TextField dexSkill4 = new TextField();
            dexSkill4.setReadOnly(true);
            dexSkill4.setClearButtonVisible(true);
        IntegerField dexSkillLevel4 = new IntegerField();
        dexSkillLevel4.setHasControls(true);

        TextField dexSkill5 = new TextField();
            dexSkill5.setReadOnly(true);
            dexSkill5.setClearButtonVisible(true);
        IntegerField dexSkillLevel5 = new IntegerField();
        dexSkillLevel5.setHasControls(true);

        TextField dexSkill6 = new TextField();
            dexSkill6.setReadOnly(true);
            dexSkill6.setClearButtonVisible(true);
        IntegerField dexSkillLevel6 = new IntegerField();
        dexSkillLevel6.setHasControls(true);

        TextField dexSkill7 = new TextField();
        dexSkill7.setReadOnly(true);
        dexSkill7.setClearButtonVisible(true);
        IntegerField dexSkillLevel7 = new IntegerField();
        dexSkillLevel7.setHasControls(true);

        TextField dexSkill8 = new TextField();
        dexSkill8.setReadOnly(true);
        dexSkill8.setClearButtonVisible(true);
        IntegerField dexSkillLevel8 = new IntegerField();
        dexSkillLevel8.setHasControls(true);

        TextField dexSkill9 = new TextField();
        dexSkill9.setReadOnly(true);
        dexSkill9.setClearButtonVisible(true);
        IntegerField dexSkillLevel9 = new IntegerField();
        dexSkillLevel9.setHasControls(true);

        TextField dexSkill10 = new TextField();
        dexSkill10.setReadOnly(true);
        dexSkill10.setClearButtonVisible(true);
        IntegerField dexSkillLevel10 = new IntegerField();
        dexSkillLevel10.setHasControls(true);

        return new HorizontalLayout(dexSkill1,dexSkillLevel1,dexSkill2,dexSkillLevel2,dexSkill3,dexSkillLevel3,dexSkill4,dexSkillLevel4,
                                    dexSkill5,dexSkillLevel5,dexSkill6,dexSkillLevel6,dexSkill7,dexSkillLevel7,dexSkill8,dexSkillLevel8,
                                    dexSkill9,dexSkillLevel9,dexSkill10,dexSkillLevel10);
    }
    public HorizontalLayout getCluster()
    {
        return dexSkillz();
    }

}