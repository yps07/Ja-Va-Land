----------------------------------------------------------- ANGRY-BIRDS STATIC GUI -----------------------------------------------------------

**How to run:

Just run the Lwjgl3Launcher class.
The execution initiates from the Core class via Lwjgl3Launcher and the following screens appear (in order): 

The main screen with a game wallpaper and name (MainGameScreen class)
                            |
                            |
                            V
                      The Main menu (Menu class)

           |                    |                   |
           |                    |                   |
           V                    V                   V
        New Game            Load Game              Exit
     (Level1 class)       (Level1 class)           ( - )


** Functionalities within screens: 

New Game{
    --> You will be directed to level 1

    --> There is a menu button on the to left of the screen which has the following buttons{

                     --> Resume: returns to the current level screen

                     --> Save & Exit: returns to the menu screen

                     --> Forfeit: displays the losing screen, has the following buttons{

                                      --> Retry: returns to the current level screen                                   
                     }
    }

    --> Order of levels: Level 1 (Level1 class) -> Level 2 (Level2 class) -> Level 3 (Level3 class)

    --> After clicking the ">>" button at each level screen, you will be directed to the winning screen which has the following buttons{

                     --> Next: directs to the next level screen (for level 3 -> returns to the menu screen)
    }

Saved Game{

    --> Has the same functionality as the "New Game" button for now
}

Exit{

    --> Exits the game (program terminates)
}

---------------------------------------------------------------------#------------------------------------------------------------------------

