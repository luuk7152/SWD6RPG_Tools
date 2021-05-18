package com.vaadin.swrpgd6.app.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.swrpgd6.app.backend.entity.Skill;
import com.vaadin.swrpgd6.app.backend.service.SkillService;

@Route("skillform")
public class SkillForm extends VerticalLayout
{

    private Skill skill;

    TextField skillName = new TextField("Name");
    TextField skillAttribute = new TextField("Attribute");
    TextField skillDescription = new TextField("Description");
    IntegerField skillLevel = new IntegerField("skillLevel");


    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Close");
    Binder<Skill> binder = new BeanValidationBinder<>(Skill.class);

    public SkillForm()
    {
        addClassName("contact-form");
        binder.bindInstanceFields(this);
        add(buildForm());
    }

    private void LoadSkill()
    {
        setSkill(SkillService.heldSkill);
        skillName.setValue(SkillService.heldSkill.getSkillName());
        skillAttribute.setValue(SkillService.heldSkill.getSkillAttribute());
        skillDescription.setValue(SkillService.heldSkill.getSkillDescription());
        //skillLevel.setValue(SkillService.heldSkill.getSkillLevel());

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
        save.addClickListener(event -> validateAndSave());    //playerCharacter
        //delete.addClickListener(event -> validateAndDelete(skill));
        close.addClickListener(event -> UI.getCurrent().navigate(MainView.class));

        return new HorizontalLayout(save, delete, close);
    }

    private VerticalLayout verticalFields()
    {

        skillName.setReadOnly(false);
        skillAttribute.setReadOnly(false);
        skillDescription.setReadOnly(false);

        return new VerticalLayout(skillName,
                skillAttribute,
                skillDescription);
    }

    private void validateAndSave()             //PlayerCharacter playerCharacter
    {
        //this works but there is currently no validation besides the constraints of the fields integer, text, etc.
        //Also need to check for existing character and do an update vs save
        System.out.println("Well, the button worked, that's something right?");
        Skill saveSkill = new Skill(skillName.getValue(), skillAttribute.getValue(), skillDescription.getValue());
        SkillService skillService = new SkillService();
        skillService.saveSkill(saveSkill);
        UI.getCurrent().navigate(MainView.class);

    }

    private void validateAndDelete(Skill skill)
    {
        return;
    }


    public void setSkill(Skill skill)  //This may not be needed
    {
        this.skill = skill;
        binder.readBean(skill);
    }

    public static abstract class SkillFormEvent extends ComponentEvent<SkillForm>
    {
        private Skill skill;
        protected SkillFormEvent(SkillForm source, Skill skill)
        {
            super(source, false);
            this.skill = skill;
        }

        public Skill getSkill()
        {
            return skill;
        }
    }

//    public static class SaveEvent extends SkillFormEvent
//    {
//        SaveEvent(CharacterForm source, Skill skill)
//        {
//            super(source, skill);
//        }
//    }
//
//    public static class DeleteEvent extends SkillFormEvent
//    {
//        DeleteEvent(SkillForm source, Skill skill)
//        {
//            super(source, skill);
//        }
//    }
//
//    public static class CloseEvent extends SkillFormEvent
//    {
//        CloseEvent(SkillForm source)
//        {
//            super(source, null);
//        }
//    }
//
//    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener)
//    {
//        return getEventBus().addListener(eventType, listener);
//    }
}

