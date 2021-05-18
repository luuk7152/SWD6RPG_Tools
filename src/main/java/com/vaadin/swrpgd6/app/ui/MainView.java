package com.vaadin.swrpgd6.app.ui;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.swrpgd6.app.backend.entity.PlayerCharacter;
import com.vaadin.swrpgd6.app.backend.service.PlayerCharacterService;
//import com.vaadin.swrpgd6.app.backend.service.PlayerCharacterService;  Keep this here, I may revive this service

@Route("")
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout
{
    private PlayerCharacter playerCharacter= new PlayerCharacter();
    //private DatabaseService databaseService = new DatabaseService();
    private Grid<PlayerCharacter> grid = new Grid<>(PlayerCharacter.class);
    private TextField filterText = new TextField();
    private Button newCharacterButton = new Button("New Character");
    private Button editCharacterButton = new Button("Edit Character");
    private Button deleteCharacterButton = new Button("Delete Character");
    RouterLink routerLink = new RouterLink("", CharacterForm.class);  //This turns the button into nav link, I like this!



    public MainView()        //PlayerCharacterService playerCharacterService  I took this arg out of MainView()
    {
        routerLink.getElement().appendChild(newCharacterButton.getElement());
        addClassName("list-view");
        setSizeFull();
        configureFilter();
        configureGrid();
        newCharacterButton.addClickListener(event -> editCharacter(PlayerCharacterService.getInstance().getHeldCharacter()));
        createHeader();
        add(routerLink,  grid);  // I removed filterText, for now
        updateList();

    }

    private void createHeader() {
        H1 logo = new H1("SWRPGD6 Tools");
        logo.addClassName("logo");
        Anchor logout = new Anchor("logout", "Log out");
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo,
                logout);
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");
        add(header);
    }

    private void configureFilter()
    {
        filterText.setPlaceholder("Filter by name. . .");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
    }

    private void configureGrid()
    {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("characterName", "template", "background", "maker", "makeDate");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> editCharacter(event.getValue()));
    }

    public void editCharacter(PlayerCharacter playerCharacter )
    {
        //This is where we will query the DB, get the full character, then pass that to holdCharacter()
        PlayerCharacterService.getInstance().holdCharacter(playerCharacter);
        UI.getCurrent().navigate(CharacterForm.class);
    }

    private void updateList()
    {   //This is querying the database and returning an ArrayList of characters to populate the grid
        //The getList method below should be in the PlayerCharacterService, since I'm creating an instance
        //of that already.  databaseService should only do database tasks, PlayerCharacterService, should handle
        //any playerCharacter requests.

        grid.setItems(PlayerCharacterService.getInstance().getCharacterList());
    }

}