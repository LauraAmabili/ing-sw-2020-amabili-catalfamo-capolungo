package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.MessageFromClient.*;

import java.io.IOException;

public interface VisitorServer {

    void visit(BeatUpdate beatUpdate);

    void visit(NicknameResponse nicknameResponse) throws IOException, InterruptedException;
    void visit(PlayerNumberResponse playerNumberResponse) throws IOException, InterruptedException;
    void visit(ChosenCardsUpdate chosenCardsUpdate) throws IOException;
    void visit(SetYourCardResponse setYourCardResponse) throws IOException;
    public void visit(StartingSetWorkerResponse startingSetWorkerResponse) throws IOException;
    public void visit(ChooseYourWorkerResponse chooseYourWorkerResponse) throws IOException;
    public void visit(MoveResponse moveResponse) throws IOException;
    public void visit(BuildResponse buildResponse) throws IOException;
    void visit(AskEffectReply askEffectReply) throws IOException;
    void visit(ChooseYourWorkerEffectResponse chooseYourWorkerEffectResponse) throws IOException, InterruptedException;
    void visit(AskEffectBuildResponse askEffectBuildResponse) throws IOException;
    void visit(BuildTwoInputResponse buildTwoInputResponse) throws IOException;
    void visit(MoveTwoInputResponse moveTwoInputResponse) throws IOException;
}
