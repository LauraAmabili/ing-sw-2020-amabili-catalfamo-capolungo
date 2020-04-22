package it.polimi.ingsw;

import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Model;
import it.polimi.ingsw.Model.Observable;
import it.polimi.ingsw.View.CLIView;
import it.polimi.ingsw.View.View;

public class ClientApp {

    public static void main(String[] args ) {


        Controller controller = new Controller();
        View view1 = new CLIView(controller);
        controller.addObserver(view1);
        view1.start();

    }

}
