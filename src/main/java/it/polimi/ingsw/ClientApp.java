package it.polimi.ingsw;

import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.View.CLIView;
import it.polimi.ingsw.View.View;

public class ClientApp {

    public static void main(String[] args ) {


        GameController controller = new GameController();
        View view1 = new CLIView(controller);
        controller.addObserver(view1);
        view1.start();

    }

}
