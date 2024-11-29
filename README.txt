----------------------------------------------------------- ANGRY-BIRDS by Ja_Va_Land -----------------------------------------------------------

**How to run:

Just run the Lwjgl3Launcher class (class can be found at the address: \Angry_Birds by Ja_Va_Land\lwjgl3\src\main\java\io\github\JaVaLand\lwjgl3)
The execution initiates from the Core class via Lwjgl3Launcher and the following screens appear (in order): 

The main screen with a game wallpaper and name (MainGameScreen class)
                            |
                            |
                            V
                  The Main menu (Menu class)

           |                    |                            |
           |                    |                            |
           V                    V                            V
        New Game            Load Game                       Exit
     (Level1 class)       (Level1 class, updated)           ( - )


** Functionalities within screens: 

New Game{
    --> You will be directed to level 1

    --> There is a menu button on the to left of the screen which has the following buttons{

                     --> Resume: returns to the current level screen

                     --> Save & Exit: saves and returns to the menu screen

                     --> Forfeit: returns to the menu screen
    }

    --> Order of levels: Level 1 (Level1 class) -> Level 2 (Level2 class) -> Level 3 (Level3 class)

    --> If you exhaust all your birds with still pigs remaining (atleast one) then you'll lose and will be directed to the lose screen where you have the option to retry.

    --> If you are able to kill all the pigs then you'll win and be directed to the winning screen 
    }

Saved Game{

    --> recovers the saved game from the .ser file in files folder in the package only. [full path: assets/files/saved_games.ser]
}

Exit{
    --> Exits the game (program terminates)
}

--> UML Class Diagram Link (Miro): https://miro.com/app/board/uXjVLbvTwdE=/?share_link_id=418057852281

--> UML Use Case Diagram Link (LucidChart): https://lucid.app/lucidchart/bb806432-fe5d-432e-b03f-a2cc36e8fa99/edit?viewport_loc=30%2C-563%2C2752%2C1173%2C0_0&invitationId=inv_e9c8cf9e-448c-4ec9-95a4-a85987560789

---------------------------------------------------------------------#------------------------------------------------------------------------
