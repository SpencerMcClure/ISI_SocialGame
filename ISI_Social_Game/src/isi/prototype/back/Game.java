 

/**
 * The Game class
 * 
 * @author Aaron Harris
 * @version 0.1
 */

import java.util.*;
import java.io.*;

public class Game
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("What number do you want to set node1 to: ");
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input == "") {
                sc.close();
                return;
            }
            System.out.println("You inputted " + input);
        }
    }
    
    public static void goodguydone(World w) {
    }
}
