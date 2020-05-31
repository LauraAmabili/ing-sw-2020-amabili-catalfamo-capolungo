package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.MessageFromClient.*;

import java.io.IOException;

/**
 * Each incoming message is sent here.
 * The type of the message is analysed and the right method
 *      is sent to VisitorMethodsServer
 *
 */
public interface VisitorServer {

    void visit(BeatUpdate beatUpdate);
    void visit(NicknameResponse nicknameResponse) throws IOException;
    void visit(PlayerNumberResponse playerNumberResponse) throws IOException, InterruptedException;
    void visit(ChosenCardsUpdate chosenCardsUpdate) throws IOException;
    void visit(SetYourCardResponse setYourCardResponse) throws IOException;
    void visit(StartingSetWorkerResponse startingSetWorkerResponse) throws IOException;
    void visit(ChooseYourWorkerResponse chooseYourWorkerResponse) throws IOException;
    void visit(MoveResponse moveResponse) throws IOException;
    void visit(BuildResponse buildResponse) throws IOException;
    void visit(AskEffectReply askEffectReply) throws IOException;
    void visit(ChooseYourWorkerEffectResponse chooseYourWorkerEffectResponse) throws IOException;
    void visit(AskEffectBuildResponse askEffectBuildResponse) throws IOException;
    void visit(BuildTwoInputResponse buildTwoInputResponse) throws IOException;
    void visit(MoveTwoInputResponse moveTwoInputResponse) throws IOException;
    void visit(PlayerThatStart playerThatStart) throws IOException;

}
