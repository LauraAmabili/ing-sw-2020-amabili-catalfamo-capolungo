package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.*;
import it.polimi.ingsw.Network.Message.MessageFromClient.*;
import it.polimi.ingsw.Network.Message.MessageFromServer.NicknameRequest;

import java.io.IOException;

public interface VisitorServer {

    void visit(ChosenCard chosenCard) throws IOException;
    void visit(ChosenGod chosenGod) throws IOException;
    void visit(SetWorkerResponse setWorkerResponse) throws IOException;
    void visit(NicknameResponse nicknameResponse) throws IOException;
    void visit(NumberOfPlayerResponse numberOfPlayerResponse) throws IOException;


    public void visit(NicknameResponse nicknameResponse) throws IOException;
    public void visit(NumberOfPlayerResponse numberOfPlayerResponse) throws IOException, InterruptedException;
    public void visit(ChosenCard chosenCard) throws IOException;
    public void visit(ChosenGod chosenGod) throws IOException;
    public void visit(SetWorkerResponse setWorkerResponse) throws IOException;
    public void visit (FirstInput firstInput) throws IOException;



}
