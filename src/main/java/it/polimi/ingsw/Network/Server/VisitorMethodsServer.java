package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.MessageFromClient.*;
import it.polimi.ingsw.Network.Message.MessageFromServer.*;
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

        String num = playerNumberResponse.getNumberOfPlayers();
        try {
            int numberOfPlayers = Integer.parseInt(num);
            if(numberOfPlayers == 3 || numberOfPlayers == 2){
                server.setNumPlayers(numberOfPlayers);
                server.setMaxPlrMsg(true);
                view.notifyNumberOfPlayer(numberOfPlayers);
                if (server.getServer().getClients().size() == numberOfPlayers) {
                    for (int i = 0; i < server.getServer().getClients().size(); i++) {
                        server.getServer().getClients().get(i).sendToClient(new NicknameRequest());
                    }
                }
            }
            else
            {
                server.sendToClient(new NumberOfPlayerWrong());
               server.sendToClient(new PlayerNumberRequest());

            }

        } catch (NumberFormatException e){

            server.sendToClient(new NumberOfPlayerWrong());
            server.sendToClient(new PlayerNumberRequest());

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


        String rowString = startingSetWorkerResponse.getRow();
        String colString = startingSetWorkerResponse.getCol();
        try {
            int row = Integer.parseInt(rowString);
            int col = Integer.parseInt(colString);
            int worker = startingSetWorkerResponse.getWorker();
            view.toSetWorker(row, col, worker);
        } catch (NumberFormatException e){
            int worker = startingSetWorkerResponse.getWorker();
            server.sendToClient(new WrongCoordinatesUpdate(worker));
        }


    }



    @Override
    public void visit(ChooseYourWorkerResponse chooseYourWorkerResponse) throws IOException {

        int worker  = chooseYourWorkerResponse.getWorker();
        view.tryThisWorker(worker);

    }

    @Override
    public void visit(MoveResponse moveResponse) throws IOException {

        String rowString = moveResponse.getRow();
        String colString = moveResponse.getCol();
        try {
            int row = Integer.parseInt(rowString);
            int col = Integer.parseInt(colString);
            int worker = moveResponse.getWorker();
            view.tryMoving(row, col, worker);
        } catch (NumberFormatException e){
            int worker = moveResponse.getWorker();
            server.sendToClient(new TryNewCoordinatesRequest(worker));
            server.sendToClient(new MoveRequest(worker));
        }

    }

    @Override
    public void visit(BuildResponse buildResponse) throws IOException {

        String rowString = buildResponse.getRow();
        String colString = buildResponse.getCol();
        try {
            int row = Integer.parseInt(rowString);
            int col = Integer.parseInt(colString);
            int worker = buildResponse.getWorker();
            view.tryToBuild(row, col, worker);
        } catch (NumberFormatException e){
            int worker = buildResponse.getWorker();
            server.sendToClient(new TryNewCoordinatesRequest(worker));
            server.sendToClient(new BuildRequest(worker));
        }

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

        String rowString1 = buildTwoInputResponse.getRow1();
        String colString1 = buildTwoInputResponse.getCol1();
        String rowString2 = buildTwoInputResponse.getRow2();
        String colString2 = buildTwoInputResponse.getCol2();
        int worker = buildTwoInputResponse.getWorker();
        try {
            int row1 = Integer.parseInt(rowString1);
            int col1 = Integer.parseInt(colString1);
            int row2 = Integer.parseInt(rowString2);
            int col2 = Integer.parseInt(colString2);
            view.timeToBuildTwoInput(row1, col1, row2, col2, worker);
        } catch (NumberFormatException e){
            server.sendToClient(new TryNewCoordinatesRequest(worker));
            server.sendToClient(new BuildTwoInputRequest(worker));
        }

        /*
        int row1 = buildTwoInputResponse.getRow1();
        int row2 = buildTwoInputResponse.getRow2();
        int col1 = buildTwoInputResponse.getCol1();
        int col2 = buildTwoInputResponse.getCol2();
        int worker = buildTwoInputResponse.getWorker();
        view.timeToBuildTwoInput(row1, col1, row2, col2, worker);

         */

    }

    @Override
    public void visit(MoveTwoInputResponse moveTwoInputResponse) throws IOException {
        String rowString1 = moveTwoInputResponse.getRow1();
        String rowString2 = moveTwoInputResponse.getRow2();
        String colString1 = moveTwoInputResponse.getCol1();
        String colString2 = moveTwoInputResponse.getCol2();
        int worker = moveTwoInputResponse.getWorker();
        try {
            int row1 = Integer.parseInt(rowString1);
            int col1 = Integer.parseInt(colString1);
            int row2 = Integer.parseInt(rowString2);
            int col2 = Integer.parseInt(colString2);
            view.timeToMoveTwoInput(row1, col1, row2, col2, worker);
        } catch (NumberFormatException e) {
            server.sendToClient(new TryNewCoordinatesRequest(worker));
            server.sendToClient(new MoveTwoInputRequest(worker));
        }
    }

    @Override
    public void visit(NicknameResponse nicknameResponse) throws IOException, InterruptedException {

        boolean check = false;
        String nickname = nicknameResponse.getNickname();
        for(int i = 0; i < server.getServer().getClients().size() - 1; i++) {
            if(server.getServer().getClients().get(i).getView().getNickname() == null) {
                view.AddingNickname(nickname);
                return;
            }
        }
        for (int i = 0; i < server.getServer().getClients().size() - 1; i++) {
            if (server.getServer().getClients().get(i).getView().getNickname().equals(nickname)) {
                check = true;
                break;
            }
        }
        if (!check) {
            view.AddingNickname(nickname);
        } else {
            server.sendToClient(new NicknameNotValidUpdate());
            server.sendToClient(new NicknameRequest());
        }
    }

    @Override
    public void visit(PingResponse pingResponse){
        server.getServer().getConnectionManager().receivePing(pingResponse.getId());
    }

}
