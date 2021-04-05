package com.vaadin.swrpgd6.app.ui;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.swrpgd6.app.backend.entity.PlayerCharacter;
import com.vaadin.swrpgd6.app.backend.service.DatabaseService;
//import com.vaadin.swrpgd6.app.backend.service.PlayerCharacterService;  Keep this here, I may revive this service

@Route("")   //I think inside the quotes is the name of the html file that will be created blank means index.html
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout
{
    //private PlayerCharacterService playerCharacterService;  //This might not be needed, however I may wish to update this
    private DatabaseService databaseService = new DatabaseService();
    private Grid<PlayerCharacter> grid = new Grid<>(PlayerCharacter.class);
    private TextField filterText = new TextField();
    private Button button = new Button("New Character");
    RouterLink routerLink = new RouterLink("", CharacterForm.class);  //This turns the button into nav link, I like this!



    public MainView()        //PlayerCharacterService playerCharacterService  I took this arg out of MainView()
    {
        routerLink.getElement().appendChild(button.getElement());
        addClassName("list-view");
        setSizeFull();
        configureFilter();
        configureGrid();
        button.addClickListener(event -> System.out.println("you navigated to the Character Form"));
        add(routerLink, filterText, grid);
        updateList();
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
        grid.setColumns("characterName", "template", "background");//, "dex", "kno", "mec", "per", "str", "tec");
        //error when the above names didn't match variable names in PlayerCharacter, displaying fine now
        //grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
    private void updateList()
    {   //This is querying the database and returning an ArrayList of characters to populate the grid
        //The getList method below should be in the PlayerCharacterService, since I'm creating an instance
        //of that already.  databaseService should only do database tasks, PlayerCharacterService, should handle
        //any playerCharacter requests.
        grid.setItems(databaseService.getList());

    }

}