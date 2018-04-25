package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import minesweeper.core.*;

import static minesweeper.core.Tile.State.CLOSED;
import static minesweeper.core.Tile.State.MARKED;
import static minesweeper.core.Tile.State.OPEN;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
    /** Playing field. */
    private Field field;
    
    /** Input reader. */
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    /**
     * Reads line of text from the reader.
     * @return line as a string
     */
    private String readLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }
    
    /**
     * Starts the game.
     * @param field field of mines and clues
     */
    @Override
    public void newGameStarted(Field field) {
        this.field = field;
        do {
            update();
            processInput();
            if(field.getState() == GameState.SOLVED ){
                update();
                System.out.println("Congratulations, "+System.getProperty("user.name")+", you have solved this puzzle!");
                System.exit(0);
            } else if (field.getState() == GameState.FAILED ){
                update();
                System.out.println("Sorry, you lost!");
                System.exit(0);
            }
        } while(true);
    }
    
    /**
     * Updates user interface - prints the field.
     */
    @Override
    public void update() {
        //first line- numbers of columns
        System.out.print("  ");

        for (int column = 0; column<field.getColumnCount(); column++) System.out.print(column+" ");
        System.out.println();
        //other lines- start with letter of row and write out the tiles
        for (int row = 0; row<field.getRowCount(); row++){
            char rowChar = (char) (65 + row);
            System.out.print(rowChar +" ");


            for (int column = 0; column<field.getColumnCount(); column++){
                if (field.getTile(row, column).getState() == (CLOSED)){
                    System.out.print("- ");

                }
                if (field.getTile(row, column).getState() == (OPEN)){
                    if (field.getTile(row, column) instanceof Mine){
                        System.out.print("X ");
                    }
                    if (field.getTile(row, column) instanceof Clue){
                        System.out.print(((Clue) field.getTile(row, column)).getValue()+" " );
                    }


                }
                if (field.getTile(row, column).getState() == (MARKED)){
                    System.out.print("M ");

                }
            }

            System.out.println();


        }
    }
    
    /**
     * Processes user input.
     * Reads line from console and does the action on a playing field according to input string.
     */
    private void processInput() {
        System.out.println("Choose your action: X- exit game, MA1- mark A1 tile, OB4- open B4");
        String command = this.readLine().toUpperCase();
        Pattern pattern = Pattern.compile("([XMOC])([A-Z][\\d])?");
        Matcher matcher = pattern.matcher(command);


        if(matcher.matches()){

            char commandChar = matcher.group(1).charAt(0);
            if(commandChar == 'X'){
                System.out.println("Goodbye, "+System.getProperty("user.name")+".");
                System.exit(0);
            }
            if(commandChar == 'C'){
                field.solve();
               return;
                }
            char rowChar = matcher.group(2).charAt(0);
            int row = rowChar -'A';
            char columnChar = matcher.group(2).charAt(1);
            int column = columnChar-'0';

            switch(commandChar){

                case'M':
                    System.out.println(row);
                    System.out.println(column);
                    field.markTile(row,column);
                    break;
                case 'O':
                    System.out.println(row);
                    System.out.println(column);
                    field.openTile(row,column);
                    break;

            }
        }else {
            System.out.println("Wrong input, please try again.");
            processInput();
        }
    }
}
