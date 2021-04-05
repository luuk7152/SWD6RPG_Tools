package com.vaadin.swrpgd6.app.backend.service;

import com.vaadin.swrpgd6.app.backend.entity.PlayerCharacter;

import java.sql.*;
import java.util.ArrayList;
/*
To do:
implement the filter.
Perhaps move the update list logic to a query method and have the update call the query method
this would pass an empty string to the query
if logic in the query method would either add + "WHERE " + column + "like" + filterText" or not if the string is empty or not.
maybe two strings are sent for now, first the column we're filtering, then the filterText.
Version 2 would overload the updateList method and more or less do the above

 */

public class DatabaseService {
    private String url = "jdbc:sqlserver://DESKTOP-3S2OPCN\\SQLEXPRESS;databaseName=characters";  //;integratedsecurity=true
    private String userName = "d6test ";
    private String password = "testtest";
    //This method will need to be updated as PlayerCharacter is expanded.  Likely, it will spin through an array or some other
    //datatype and insert the values so I'm not sending a million parameters to the method.

    public void dbInsert(PlayerCharacter playerCharacter)
    {
        try
        {     //refactor this, don't use these variables here, use the methods below
            String characterName = playerCharacter.getCharacterName();
            String CharacterTemplate = playerCharacter.getTemplate();
            String CharacterBackground = playerCharacter.getBackground();
            int str = playerCharacter.getStr();
            int kno = playerCharacter.getKno();
            int dex = playerCharacter.getDex();
            int mec = playerCharacter.getMec();
            int per = playerCharacter.getPer();
            int tec = playerCharacter.getTec();


            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connected to Microsoft SQL Server to insert Character");

            String sql = "INSERT INTO character_stats (name, template, dexterity, knowledge, mechanical, perception, strength, technology, background) "
                    + "VALUES ('"+ characterName +"' , '" + CharacterTemplate +"' , " + dex + ", " + kno +", "
                    + mec +", " + per +", " + str +", " + tec +", '" + CharacterBackground + "' ) ";
            Statement statement = connection.createStatement();

            int rows = statement.executeUpdate(sql);

            if (rows > 0)
            {
                System.out.println("Row has been added");
            }
            connection.close();
        }
        catch (SQLException e)
        { System.out.println("oops, there's an error:");
            e.printStackTrace();
        }
    }

    public ArrayList<PlayerCharacter> getList()    //List<PlayerCharacter>
    {
        System.out.println("This is happening");
//        DatabaseService dbService = new DatabaseService();
//
//        for (int i=0; i<35; i++)
//        {
//            String name = "Luke";
//            dbService.doIt(name);
//            System.out.println( i + " character added to the db.");
//        }
        ArrayList<PlayerCharacter> playerList = new ArrayList<>();

        try
        {
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement;
        statement = connection.createStatement();
        System.out.println("Connected to Microsoft SQL Server to updateList");
        String sql = "SELECT * FROM character_stats";
        ResultSet resultSet;
        resultSet = statement.executeQuery(sql);
        System.out.println("The query happened.");

        while (resultSet.next())
        {
            PlayerCharacter playerCharacter = new PlayerCharacter(resultSet.getString("name"),
                    resultSet.getString("template"), resultSet.getString("background"),
                    resultSet.getInt("dexterity"), resultSet.getInt("knowledge"),
                    resultSet.getInt("mechanical"), resultSet.getInt("perception"),
                    resultSet.getInt("strength"), resultSet.getInt("technology") );
            playerList.add(playerCharacter);
        }
            connection.close();
        }
        catch(SQLException e)
        {
            { System.out.println("oops, there's an error:");
                e.printStackTrace();
            }
        }
        return playerList;
    }


    public void dbUpdate()
    {

    }

    public void dbDelete()
    {

    }
    //This just builds characters for test data.
    public void doIt(String name)
    {
        System.out.println("doIt() is running.");
        try
        {
            String characterName = name;
            String template = "X-wing Pilot";
            String background = "farm boy, he is just a silly farm boy.";
            int dex = 16;
            int kno = 14;
            int mec = 12;
            int per = 16;
            int str = 14;
            int tec = 12;
            System.out.println(characterName + template + str + kno + dex);
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connected to Microsoft SQL Server");


            String sql = "INSERT INTO character_stats (name, template, background, dexterity, knowledge, mechanical," +
                    " perception, strength, technology) "
                    + "VALUES ('" + characterName + "' , '" + template + "' , '" + background + "' ," + dex + ", " + kno + "," + mec + "," + per + "," + str +
                    "," + tec +")";

            System.out.println(sql);

            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);

            if (rows > 0)
            {
                System.out.println("Row has been added");
            }
            connection.close();
        }
        catch (SQLException e)
        { System.out.println("oops, there's an error:");
            e.printStackTrace();
        }


    }


}

