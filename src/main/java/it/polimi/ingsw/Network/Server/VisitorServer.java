package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.*;
import it.polimi.ingsw.Network.Message.MessageFromClient.ChosenCard;
import it.polimi.ingsw.Network.Message.MessageFromClient.ChosenGod;
import it.polimi.ingsw.Network.Message.MessageFromClient.NicknameResponse;
import it.polimi.ingsw.Network.Message.MessageFromClient.NumberOfPlayerResponse;
import it.polimi.ingsw.Network.Message.MessageFromServer.NicknameRequest;

import java.io.IOException;

public interface VisitorServer {





    public void visit(NicknameResponse nicknameResponse) throws IOException;
    public void visit(NumberOfPlayerResponse numberOfPlayerResponse) throws IOException;
    public void visit(ChosenCard chosenCard) throws IOException;
    public void visit(ChosenGod chosenGod) throws IOException;




}
