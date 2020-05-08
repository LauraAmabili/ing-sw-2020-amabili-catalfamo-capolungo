package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.*;
import it.polimi.ingsw.Network.Message.MessageFromClient.*;
import it.polimi.ingsw.Network.Message.MessageFromServer.NicknameRequest;

import java.io.IOException;

public interface VisitorServer {





    public void visit(NicknameResponse nicknameResponse) throws IOException;
    public void visit(NumberOfPlayerResponse numberOfPlayerResponse) throws IOException;
    public void visit(ChosenCard chosenCard) throws IOException;
    public void visit(ChosenGod chosenGod) throws IOException;
    public void visit(SetWorkerResponse setWorkerResponse) throws IOException;




}
