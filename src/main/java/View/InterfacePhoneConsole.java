package View;
import Controller.Connection;

import java.util.Scanner;

/*
 * Created by levelarde on 26-04-16.
 */
public class InterfacePhoneConsole implements UserInterface {
    private Scanner scanner;
    public InterfacePhoneConsole(){
        this.scanner = new Scanner(System.in);
    }


    public void speak(String message){
        System.out.println(message);
    }

    public void start(Connection connection){
        String input = "";
        while(input != null){
            input = this.scanner.nextLine();
            connection.receiveInput(input);
        }
    }
}
