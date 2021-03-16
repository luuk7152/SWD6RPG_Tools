package com.vaadin.swrpgd6.app.ui;


import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.swrpgd6.app.backend.entity.PlayerCharacter;
import com.vaadin.swrpgd6.app.backend.service.DatabaseService;
import com.vaadin.swrpgd6.app.backend.service.PlayerCharacterService;

@Route("")   //I think inside the quotes is the name of the html file that will be created black means index.html
public class MainView extends VerticalLayout
{
    private PlayerCharacterService playerCharacterService;
    private DatabaseService databaseService = new DatabaseService();
    private Grid<PlayerCharacter> grid = new Grid<>(PlayerCharacter.class);
    public MainView(PlayerCharacterService playerCharacterService)
    {
//        System.out.println("This is happening");
//        DatabaseService dbService = new DatabaseService();
//        for (int i =1; i>35; i++)
//        {
//            String name = "Luke";
//            dbService.doIt(name);
//            System.out.println( i + " character added to the db.");
//        }

        this.playerCharacterService = playerCharacterService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(grid);
        updateList();

    }

    private void configureGrid()
    {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("characterName", "template", "background", "dex", "kno", "mec", "per", "str", "tec");
        //error when the above names didn't match variable names in PlayerCharacter, displaying fine now
    }
    private void updateList()
    {   //This is querying the database and returning an ArrayList of characters to populate the grid
        grid.setItems(databaseService.getList());

    }
}