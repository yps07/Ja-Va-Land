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

**How to run JUnit Tests:

The test case classes as well as the test runner can be found in the \Angry_Birds by Ja_Va_Land\core\src\test\java directory.

There are 4 tests{
     BlockTestCase{
             --> tests the damage updation code for a block class instance.
             --> a wood block is tested (durability = 30), damage dealt is 5, the test should be successfull, updating the durability to 25.
     }

     PigTestCase{
             --> tests the damage updation code for a pig class instance.
             --> a small_pig is tested (durability = 50), damage dealt is 5, the test should be successfull, updating the durability to 45.
     }

     ReadFileTestCase{
             --> tests the reading ability of the FileHandler class which reads any type of file (.ser in our case).
             --> a file is read using the FileHandler class which does not exist, the class should throw an exception in this case.
             --> the test should be successfull displaying the exception message in the command line: Error reading from file: assets\files\saved_games_1.ser (The system cannot find the file specified)
     }

     WriteFileTestCase{
             --> tests the writing ability of the FileHandler class which reads any type of file (.ser in our case).
             --> a file is written on using the FileHandler class which does exists, the class should be able to write a GameState class instance into the provided .ser file without an exception.
             --> the test should be successfull.
     }

}

--> UML Class Diagram Link (Miro): https://miro.com/app/board/uXjVLbvTwdE=/?share_link_id=418057852281

--> UML Use Case Diagram Link (LucidChart): https://lucid.app/lucidchart/bb806432-fe5d-432e-b03f-a2cc36e8fa99/edit?viewport_loc=30%2C-563%2C2752%2C1173%2C0_0&invitationId=inv_e9c8cf9e-448c-4ec9-95a4-a85987560789

---------------------------------------------------------------------#------------------------------------------------------------------------
