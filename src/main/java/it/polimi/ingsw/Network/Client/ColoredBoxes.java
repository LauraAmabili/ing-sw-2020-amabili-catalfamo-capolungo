package it.polimi.ingsw.Network.Client;

public class ColoredBoxes {



    public static String ANSI_BLUE = "\u001B[34m";
    public static String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String PURPLE = "\033[0;35m";
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";




    public void printBasicBoard(){

        for(int i=0; i<6; i++){
           square();
        }
    }

    public void square(){

        int x = 5;
        int y = 5;
        System.out.println(PURPLE);


            //for (int j = 0; j < y; j++) {
                System.out.print(" ----------- ");
            //}
            System.out.println();
            for (int i = 0; i < x; i++) {
                System.out.print("| ");
                for (int z = 0; z < y ; z++) {
                    System.out.print("  ");
                }
                System.out.println("| ");
            }

            //for (int k = 0; k < y; k++) {
                System.out.print(" ----------- ");
            //}


    }

    public void cards(String playerName){

        int x = 5;
        int y = 5;
        System.out.println(PURPLE);


        //for (int j = 0; j < y; j++) {
        System.out.print(" ----------- ");
        //}
        System.out.println();
        for (int i = 0; i < x; i++) {
            System.out.print("| ");
             //for (int z = 0; z < y ; z++) {
                System.out.print(playerName);
           // }
            System.out.println("| ");
        }

        //for (int k = 0; k < y; k++) {
        System.out.print(" ----------- ");
        //}



    }









}
