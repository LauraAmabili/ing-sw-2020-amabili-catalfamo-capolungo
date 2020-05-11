package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.MessageFromClient.*;

import java.io.IOException;

public interface VisitorServer {



    public void visit(NicknameResponse nicknameResponse) throws IOException, InterruptedException;
    public void visit(PlayerNumberResponse playerNumberResponse) throws IOException, InterruptedException;
    public void visit(ChosenCardsUpdate chosenCardsUpdate) throws IOException;
    public void visit(SetYourCardResponse setYourCardResponse) throws IOException;
    public void visit(StartingSetWorkerResponse startingSetWorkerResponse) throws IOException;
    public void visit(ChooseYourWorkerResponse chooseYourWorkerResponse) throws IOException;
    public void visit(MoveResponse moveResponse) throws IOException;
    public void visit(BuildResponse buildResponse) throws IOException;
    public void visit(PingResponse pingResponse);
    void visit(AskEffectReply askEffectReply) throws IOException;

    void visit(ChooseYourWorkerEffectResponse chooseYourWorkerEffectResponse) throws IOException, InterruptedException;
}
