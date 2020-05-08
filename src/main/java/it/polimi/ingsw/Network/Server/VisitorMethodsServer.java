package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.*;
import it.polimi.ingsw.Network.Message.MessageFromClient.ChosenCard;
import it.polimi.ingsw.Network.Message.MessageFromClient.ChosenGod;
import it.polimi.ingsw.Network.Message.MessageFromClient.NicknameResponse;
import it.polimi.ingsw.Network.Message.MessageFromClient.NumberOfPlayerResponse;
import it.polimi.ingsw.Network.Message.MessageFromServer.NicknameRequest;
import it.polimi.ingsw.View.VirtualView;

import java.io.IOException;

public class VisitorMethodsServer implements VisitorServer {

    VirtualView view;

    public VisitorMethodsServer(VirtualView view) {
        this.view = view;
    }



    @Override
    public void visit(NumberOfPlayerResponse numberOfPlayerResponse) throws IOException {

        view.notifyNumberOfPlayer(numberOfPlayerResponse.getNumberOfPlayers());

    }

    @Override
    public void visit(ChosenCard chosenCard) throws IOException {

        view.tryThisCard(chosenCard.getChosenCard());

    }

    @Override
    public void visit(ChosenGod chosenGod) throws IOException {

        view.godNameChosen(chosenGod.getChosenGod());

    }

    @Override
    public void visit(NicknameResponse nicknameResponse) throws IOException {

        view.AddingNickname(nicknameResponse.getNickname());

    }

}
