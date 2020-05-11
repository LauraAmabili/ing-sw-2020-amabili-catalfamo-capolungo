package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.*;
import it.polimi.ingsw.Network.Message.MessageFromClient.*;
import it.polimi.ingsw.Network.Message.MessageFromServer.ChooseYourWorkerEffectRequest;
import it.polimi.ingsw.Network.Message.MessageFromServer.NicknameRequest;

import java.io.IOException;

public interface VisitorServer {



    public void visit(NicknameResponse nicknameResponse) throws IOException, InterruptedException;
    public void visit(NumberOfPlayerResponse numberOfPlayerResponse) throws IOException, InterruptedException;
    public void visit(ChosenCard chosenCard) throws IOException;
    public void visit(ChosenGod chosenGod) throws IOException;
    public void visit(SetWorkerResponse setWorkerResponse) throws IOException;
    public void visit(FirstInput firstInput) throws IOException, InterruptedException;
    public void visit(ChooseYourWorkerResponse chooseYourWorkerResponse) throws IOException;
    public void visit(ChooseRowAndColResponse chooseRowAndColResponse) throws IOException;
    public void visit(BuildingRowAndColResponse buildingRowAndColResponse) throws IOException;
    public void visit(AskEffectReply askEffectReply) throws IOException;
    public void visit(ChooseYourWorkerEffectResponse chooseYourWorkerEffectResponse) throws IOException, InterruptedException;
}
