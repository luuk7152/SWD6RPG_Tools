package com.vaadin.swrpgd6.app.ui;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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
import com.vaadin.swrpgd6.app.backend.service.PlayerCharacterService;

@Route("form")
public class CharacterForm extends FormLayout
{
    private PlayerCharacter playerCharacter;
    private int pipTotal = 0;
    TextField characterName = new TextField("Name");
    TextField template = new TextField("Template");
    TextField background = new TextField("Background");
    IntegerField pipCount = new IntegerField("Total Pips");
    IntegerField dex = new IntegerField("Dexterity");
    TextField dexD6 = new TextField();
    IntegerField kno = new IntegerField("Knowledge");
    TextField knoD6 = new TextField();
    IntegerField mec = new IntegerField("Mechanical");
    TextField mecD6 = new TextField();
    IntegerField per = new IntegerField("Perception");
    TextField perD6 = new TextField();
    IntegerField str = new IntegerField("Strength");
    TextField strD6 = new TextField();
    IntegerField tec = new IntegerField("Technology");
    TextField tecD6 = new TextField();

    Button save = new Button("Save");
   // Button delete = new Button("Delete");
    Button close = new Button("Close");
    Binder<PlayerCharacter> binder = new BeanValidationBinder<>(PlayerCharacter.class);

    public CharacterForm()
    {
        addClassName("contact-form");
        binder.bindInstanceFields(this);
        add(buildForm());
    }

    private Component buildForm()
    {
        Div wrapperLayout = new Div(createButtonsLayout(), verticalFields());
        return wrapperLayout;
    }
    private HorizontalLayout createButtonsLayout()
    {

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        //delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        save.addClickListener(event -> validateAndSave(playerCharacter));
        //delete.addClickListener(event -> System.out.println("You clicked delete"));
        close.addClickListener(event -> UI.getCurrent().navigate(MainView.class));

        return new HorizontalLayout(save, close);   //delete
    }

    private void validateAndSave(PlayerCharacter playerCharacter)
    {
    //this works but there is currently no validation besides the constraints of the fields integer, text, etc.
        System.out.println("Well, the button worked, that's something right?");
        PlayerCharacter saveCharacter = new PlayerCharacter(characterName.getValue(), template.getValue(),background.getValue(),dex.getValue(),kno.getValue(),mec.getValue(),per.getValue(),str.getValue(),tec.getValue());
        PlayerCharacterService pCService = new PlayerCharacterService();
        pCService.saveCharacter(saveCharacter);
        UI.getCurrent().navigate(MainView.class);

    }

    private VerticalLayout verticalFields()
    {
        pipCount.setReadOnly(true);
        dex.setHasControls(true);
        dex.setClearButtonVisible(true);
        dex.addValueChangeListener(event -> dexD6.setValue( pipsToD(dex.getValue())));
        dexD6.setReadOnly(true);

        kno.setHasControls(true);
        kno.setClearButtonVisible(true);
        kno.addValueChangeListener(event -> knoD6.setValue( pipsToD(kno.getValue())));
        knoD6.setReadOnly(true);

        mec.setHasControls(true);
        mec.setClearButtonVisible(true);
        mec.addValueChangeListener(event -> mecD6.setValue( pipsToD(mec.getValue())));
        mecD6.setReadOnly(true);

        per.setHasControls(true);
        per.setClearButtonVisible(true);
        per.addValueChangeListener(event -> perD6.setValue( pipsToD(per.getValue())));
        perD6.setReadOnly(true);

        str.setHasControls(true);
        str.setClearButtonVisible(true);
        str.addValueChangeListener(event -> strD6.setValue( pipsToD(str.getValue())));
        strD6.setReadOnly(true);

        tec.setHasControls(true);
        tec.setClearButtonVisible(true);
        tec.addValueChangeListener(event -> tecD6.setValue( pipsToD(tec.getValue())));
        tecD6.setReadOnly(true);
        return new VerticalLayout(characterName,
                template,
                background,
                dex, dexD6,
                kno, knoD6,
                mec, mecD6,
                per, perD6,
                str, strD6,
                tec, tecD6);
    }

    public void setPlayerCharacter(PlayerCharacter playerCharacter)
    {
        this.playerCharacter = playerCharacter;
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

    public static class SaveEvent extends CharacterFormEvent
    {
        SaveEvent(CharacterForm source, PlayerCharacter playerCharacter)
        {
            super(source, playerCharacter);
        }
    }

    public static class DeleteEvent extends CharacterFormEvent
    {
        DeleteEvent(CharacterForm source, PlayerCharacter playerCharacter)
        {
            super(source, playerCharacter);
        }
    }

    public static class CloseEvent extends CharacterFormEvent
    {
        CloseEvent(CharacterForm source)
        {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener)
    {
        return getEventBus().addListener(eventType, listener);
    }

    //Pips Logic
    public void checkPips(int attPips)
    {
        int pipTotal = dex.getValue() + kno.getValue() + mec.getValue() + per.getValue() + str.getValue() + tec.getValue();
        if (pipTotal > pipCount.getValue())
        {
            if (pipTotal < 55) {
                dex.setValue(attPips);
                pipCount.setValue(pipTotal);

            } else {
                dex.setValue(attPips -1);
            }
        }
    }

    public String pipsToD(int pips)
    {
        String Dice;
        if (pips %3 == 0)
        {
            Dice = pips / 3 + "D";
        }
        else
        {
            Dice = pips /3 + "D+ " + pips%3;
        }

        return Dice;
    }
}
