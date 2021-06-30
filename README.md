Independent Study Final Report
John Luukkonen
5/20/2021

I was inspired by the Dungeons and Dragons Beyond website to create a tool to support the Star Wars Tabletop Roleplaying Game.  This discontinued D6 based game has a strong following online and this tool seems like it may provide some value to those fans. The application will perform the CRUD operations and store characters in a relational database to be recalled, updated, or deleted.  I began with the Vaadin tutorial app as a start.  I removed most of the elements and built my application upon the existing structure.  I used Vaadin primarily to build the front end.  I decided to implement an MS SQL database and access it using JDBC instead of Spring and JPA from the tutorial as an additional challenge and opportunity to gain experience with JDBC and database administration.  

   

A user will log in to the application.  A list of characters in the database will be displayed and accessible by the user.  The user may also create a new character and save it to the database.  The create, recall, update, and delete functions will happen in the CharacterView screen, a form with fields for all the data on a character sheet.

User will log in on the landing page, this handles Authentication and Authorization.




The MainView displays the characters in the database and offers users the opportunity to select an existing character to view/edit/delete in the CharacterForm, or by clicking New Character, navigate to a blank CharacterForm to create a new Character.



The CharacterForm is a form which has fields for all elements of a Star Wars RPG character sheet.  Users can enter any or all data, including selecting
Attribute Skills from the drop-down menus and adjust Attribute levels .  From this screen characters can be created, viewed, updated, or deleted.
