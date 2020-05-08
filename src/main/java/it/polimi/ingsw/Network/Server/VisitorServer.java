package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.*;
import it.polimi.ingsw.Network.Message.MessageFromClient.ChosenCard;
import it.polimi.ingsw.Network.Message.MessageFromClient.ChosenGod;
import it.polimi.ingsw.Network.Message.MessageFromClient.NicknameResponse;
import it.polimi.ingsw.Network.Message.MessageFromClient.NumberOfPlayerResponse;
import it.polimi.ingsw.Network.Message.MessageFromServer.NicknameRequest;

public interface VisitorServer {





    public void visit(NicknameResponse nicknameResponse);
    public void visit(NumberOfPlayerResponse numberOfPlayerResponse);
    public void visit(ChosenCard chosenCard);
    public void visit(ChosenGod chosenGod);




}
