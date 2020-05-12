package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.MessageFromClient.*;
import it.polimi.ingsw.Network.Message.MessageFromServer.ChooseYourWorkerEffectRequest;
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
    public void visit(PlayerNumberResponse playerNumberResponse) throws IOException, InterruptedException {

        int numberOfPlayers = playerNumberResponse.getNumberOfPlayers();
        server.setNumPlayers(numberOfPlayers);
        server.setMaxPlrMsg(true);
        view.notifyNumberOfPlayer(numberOfPlayers);
        if(server.getServer().getClients().size() == numberOfPlayers) {
            for (int i = 0; i < server.getServer().getClients().size(); i++) {
                server.getServer().getClients().get(i).sendToClient(new NicknameRequest());
            }
        }

    }

    @Override
    public void visit(ChosenCardsUpdate chosenCardsUpdate) throws IOException {

        String cardName = chosenCardsUpdate.getChosenCard();
        view.tryThisCard(cardName);

    }

    @Override
    public void visit(SetYourCardResponse setYourCardResponse) throws IOException {

        view.godNameChosen(setYourCardResponse.getChosenGod());

    }

    @Override
    public void visit(StartingSetWorkerResponse startingSetWorkerResponse) throws IOException {

        int row = startingSetWorkerResponse.getRow();
        int col = startingSetWorkerResponse.getCol();
        int worker = startingSetWorkerResponse.getWorker();
        view.toSetWorker(row, col, worker);

    }



    @Override
    public void visit(ChooseYourWorkerResponse chooseYourWorkerResponse) throws IOException {

        int worker  = chooseYourWorkerResponse.getWorker();
        view.tryThisWorker(worker);

    }

    @Override
    public void visit(MoveResponse moveResponse) throws IOException {

        int row = moveResponse.getRow();
        int col = moveResponse.getCol();
        int worker = moveResponse.getWorker();
        view.tryMoving(row, col, worker);
    }

    @Override
    public void visit(BuildResponse buildResponse) throws IOException {

        int row = buildResponse.getRow();
        int col = buildResponse.getCol();
        int worker = buildResponse.getWorker();
        view.tryToBuild(row, col, worker);
    }

    @Override
    public void visit(AskEffectReply askEffectReply) throws IOException {

        boolean effect = askEffectReply.getEffect().equals("y");
        view.updateTimeToChooseWorkerEffect(effect);

    }

    @Override
    public void visit(ChooseYourWorkerEffectResponse chooseYourWorkerEffectResponse) throws IOException, InterruptedException {

        view.tryThisWorkerEffect(chooseYourWorkerEffectResponse.isEffect(), chooseYourWorkerEffectResponse.getWorker());

    }

    @Override
    public void visit(AskEffectBuildResponse askEffectBuildResponse) throws IOException {

        boolean effect = askEffectBuildResponse.getEffect().equals("y");
        view.updatePlayerBuild(effect, askEffectBuildResponse.getPlayerNickname(), askEffectBuildResponse.getWorker());

    }

    @Override
    public void visit(BuildTwoInputResponse buildTwoInputResponse) throws IOException {

        int row1 = buildTwoInputResponse.getRow1();
        int row2 = buildTwoInputResponse.getRow2();
        int col1 = buildTwoInputResponse.getCol1();
        int col2 = buildTwoInputResponse.getCol2();
        int worker = buildTwoInputResponse.getWorker();
        view.timeToBuildTwoInput(row1, col1, row2, col2, worker);

    }

    @Override
    public void visit(NicknameResponse nicknameResponse) throws IOException, InterruptedException {

        String nickname = nicknameResponse.getNickname();
        view.AddingNickname(nickname);

    }

    @Override
    public void visit(PingResponse pingResponse){
        server.getServer().getConnectionManager().receivePing(pingResponse.getN());
    }

}
