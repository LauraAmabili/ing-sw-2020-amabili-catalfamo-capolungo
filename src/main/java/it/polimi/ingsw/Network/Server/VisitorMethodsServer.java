package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.*;
import it.polimi.ingsw.Network.Message.MessageFromClient.*;
import it.polimi.ingsw.Network.Message.MessageFromServer.NicknameRequest;
import it.polimi.ingsw.View.VirtualView;

import java.io.IOException;

public class VisitorMethodsServer implements VisitorServer {

    VirtualView view;
    ServerThread server;

    public VisitorMethodsServer(VirtualView view, ServerThread server) {
        this.view = view;
        this.server = server;
    }


    @Override
    public void visit(NumberOfPlayerResponse numberOfPlayerResponse) throws IOException, InterruptedException {

        int numberOfPlayers = numberOfPlayerResponse.getNumberOfPlayers();
        server.setNumPlayers(numberOfPlayers);
        server.setMaxPlrMsg(true);
        view.notifyNumberOfPlayer(numberOfPlayers);

    }

    @Override
    public void visit(ChosenCard chosenCard) throws IOException {

        String cardName = chosenCard.getChosenCard();
        view.tryThisCard(cardName);

    }

    @Override
    public void visit(ChosenGod chosenGod) throws IOException {

        view.godNameChosen(chosenGod.getChosenGod());

    }

    @Override
    public void visit(SetWorkerResponse setWorkerResponse) throws IOException {

        int row = setWorkerResponse.getRow();
        int col = setWorkerResponse.getCol();
        System.out.println("Sono nel server visitor, set worker response");
        view.toSetWorker(row, col, setWorkerResponse.getWorker());

    }

    @Override
    public void visit(FirstInput firstInput) throws IOException, InterruptedException {


            view.startingGame();
    }

    @Override
    public void visit(ChooseYourWorkerResponse chooseYourWorkerResponse) throws IOException {

        int worker  = chooseYourWorkerResponse.getWorker();
        view.tryThisWorker(worker);

    }

    @Override
    public void visit(ChooseRowAndColResponse chooseRowAndColResponse) throws IOException {

        int row = chooseRowAndColResponse.getRow();
        int col = chooseRowAndColResponse.getCol();
        int worker = chooseRowAndColResponse.getWorker();
        view.tryMoving(row, col, worker);
    }

    @Override
    public void visit(NicknameResponse nicknameResponse) throws IOException, InterruptedException {

        view.AddingNickname(nicknameResponse.getNickname());

    }

}
