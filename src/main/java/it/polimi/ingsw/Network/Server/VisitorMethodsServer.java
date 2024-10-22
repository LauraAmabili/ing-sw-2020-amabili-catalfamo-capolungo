package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.MessageFromClient.*;
import it.polimi.ingsw.Network.Message.MessageFromServer.*;
import it.polimi.ingsw.View.VirtualView;

import java.io.IOException;

public class VisitorMethodsServer implements VisitorServer {

    final VirtualView view;
    final ServerThread serverThread;

    public VisitorMethodsServer(VirtualView view, ServerThread serverThread) {
        this.view = view;
        this.serverThread = serverThread;

    }

    /**
     * receive the number of players
     * check if the format is correct
     * check if the number is available
     * otherwise the view is notified
     *
     * @param playerNumberResponse message
     */
    @Override
    public void visit(PlayerNumberResponse playerNumberResponse) throws InterruptedException {

        String num = playerNumberResponse.getNumberOfPlayers();
        try {
            int numberOfPlayers = Integer.parseInt(num);
            if (numberOfPlayers == 3 || numberOfPlayers == 2) {
                serverThread.setNumPlayers(numberOfPlayers);
                serverThread.setMaxPlrMsg(true);
                view.notifyNumberOfPlayer(numberOfPlayers);
                for (int i = 0; i < serverThread.getServer().getServerThreads().size(); i++) {
                    serverThread.getServer().getServerThreads().get(i).setMaxPlayerNumberSet(true);
                }
                if (serverThread.getServer().getServerThreads().size() == numberOfPlayers) {
                    for (int i = 0; i < serverThread.getServer().getServerThreads().size(); i++) {
                        serverThread.getServer().getServerThreads().get(i).sendToClient(new NicknameRequest());
                    }
                }
            } else {
                serverThread.sendToClient(new NumberOfPlayerWrong());
                serverThread.sendToClient(new PlayerNumberRequest());
            }
        } catch (NumberFormatException e) {
            serverThread.sendToClient(new NumberOfPlayerWrong());
            serverThread.sendToClient(new PlayerNumberRequest());

        }

    }

    /**
     * cards chosen by the challenger
     *
     * @param chosenCardsUpdate
     * @
     */
    @Override
    public void visit(ChosenCardsUpdate chosenCardsUpdate)  {

        String cardName = chosenCardsUpdate.getChosenCard();
        view.tryThisCard(cardName);

    }

    /**
     * Choose the card
     * @param setYourCardResponse
     * @
     */
    @Override
    public void visit(SetYourCardResponse setYourCardResponse)  {

        view.godNameChosen(setYourCardResponse.getChosenGod());

    }

    /**
     * Set Worker Coordinates
     * @param startingSetWorkerResponse
     * @
     */
    @Override
    public void visit(StartingSetWorkerResponse startingSetWorkerResponse)  {


        String rowString = startingSetWorkerResponse.getRow();
        String colString = startingSetWorkerResponse.getCol();
        try {
            int row = Integer.parseInt(rowString);
            int col = Integer.parseInt(colString);
            int worker = startingSetWorkerResponse.getWorker();
            view.toSetWorker(row, col, worker);
        } catch (NumberFormatException e) {
            int worker = startingSetWorkerResponse.getWorker();
            serverThread.sendToClient(new WrongCoordinatesUpdate(worker));
        }


    }

    /**
     * Choose the number of worker
     * @param chooseYourWorkerResponse
     * @
     */
    @Override
    public void visit(ChooseYourWorkerResponse chooseYourWorkerResponse)  {

        String worker = chooseYourWorkerResponse.getWorker();
        try {
            int w = Integer.parseInt(worker);
            if (w < 3 && w > 0) {
                view.tryThisWorker(w);
            } else {
                serverThread.sendToClient(new WorkerInputNotValid());
                serverThread.sendToClient(new ChooseYourWorkerRequest());
            }
        } catch (NumberFormatException e) {
            serverThread.sendToClient(new WorkerInputNotValid());
            serverThread.sendToClient(new ChooseYourWorkerRequest());
        }


    }

    /**
     * Move coordinates
     * @param moveResponse
     * @
     */
    @Override
    public void visit(MoveResponse moveResponse)  {

        String rowString = moveResponse.getRow();
        String colString = moveResponse.getCol();
        try {
            int row = Integer.parseInt(rowString);
            int col = Integer.parseInt(colString);
            int worker = moveResponse.getWorker();
            if(row > 0 && row < 6 && col > 0 && col < 6) {
                view.tryMoving(row, col, worker);
            } else {
                serverThread.sendToClient(new TryNewCoordinatesRequest(worker));
                serverThread.sendToClient(new MoveRequest(worker, moveResponse.getAvailable()));
            }
        } catch (NumberFormatException e) {
            int worker = moveResponse.getWorker();
            serverThread.sendToClient(new TryNewCoordinatesRequest(worker));
            serverThread.sendToClient(new MoveRequest(worker, moveResponse.getAvailable()));
        }

    }

    /**
     * Build coordinates
     * @param buildResponse
     * @
     */
    @Override
    public void visit(BuildResponse buildResponse)  {

        String rowString = buildResponse.getRow();
        String colString = buildResponse.getCol();
        try {
            int row = Integer.parseInt(rowString);
            int col = Integer.parseInt(colString);
            int worker = buildResponse.getWorker();
            if(row > 0 && row < 6 && col > 0 && col < 6) {
                view.tryToBuild(row, col, worker);
            } else {
                serverThread.sendToClient(new TryNewCoordinatesRequest(worker));
                serverThread.sendToClient(new BuildRequest(worker, buildResponse.getAv()));
            }
        } catch (NumberFormatException e) {
            int worker = buildResponse.getWorker();
            serverThread.sendToClient(new TryNewCoordinatesRequest(worker));
            serverThread.sendToClient(new BuildRequest(worker, buildResponse.getAv()));
        }

    }

    /**
     * Prompt to the user if they want to enable the effect
     * @param askEffectReply
     * @
     */
    @Override
    public void visit(AskEffectReply askEffectReply)  {

        if (askEffectReply.getEffect().equals("y")) {
            view.updateTimeToChooseWorkerEffect(true);
        } else if (askEffectReply.getEffect().equals("n")) {
            view.updateTimeToChooseWorkerEffect(false);
        } else {
            serverThread.sendToClient(new WorkerInputNotValid());
            view.updateAskForEffect(askEffectReply.getPlayerNickname());
        }

    }

    /**
     * Enable the special card effect
     * @param chooseYourWorkerEffectResponse
     * @
     */
    @Override
    public void visit(ChooseYourWorkerEffectResponse chooseYourWorkerEffectResponse)  {

        boolean effect = chooseYourWorkerEffectResponse.isEffect();
        String worker = chooseYourWorkerEffectResponse.getWorker();
        try {
            int w = Integer.parseInt(worker);
            if (w < 3 && w > 0) {
                view.tryThisWorkerEffect(effect, w);
            } else {
                serverThread.sendToClient(new WorkerInputNotValid());
                serverThread.sendToClient(new ChooseYourWorkerEffectRequest(effect));
            }

        } catch (NumberFormatException e) {
            serverThread.sendToClient(new WorkerInputNotValid());
            serverThread.sendToClient(new ChooseYourWorkerEffectRequest(effect));
        }


    }

    /**
     * Enable or not the effect
     * @param askEffectBuildResponse
     * @
     */
    @Override
    public void visit(AskEffectBuildResponse askEffectBuildResponse)  {

        if (askEffectBuildResponse.getEffect().equals("y")) {
            view.updatePlayerBuild(true, askEffectBuildResponse.getPlayerNickname(), askEffectBuildResponse.getWorker());
        } else if (askEffectBuildResponse.getEffect().equals("n")) {
            view.updatePlayerBuild(false, askEffectBuildResponse.getPlayerNickname(), askEffectBuildResponse.getWorker());

        } else {
            serverThread.sendToClient(new WorkerInputNotValid());
            view.updateAskForEffectBuild(askEffectBuildResponse.getPlayerNickname(), askEffectBuildResponse.getWorker());
        }

    }

    /**
     * Request two inputs for a special build
     * @param buildTwoInputResponse
     * @
     */
    @Override
    public void visit(BuildTwoInputResponse buildTwoInputResponse)  {

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
            if(row1 > 0 && row1 < 6 && row2 > 0 && row2 < 6 && col1 > 0 && col1 < 6 && col2 > 0 && col2 < 6 ) {
                view.timeToBuildTwoInput(row1, col1, row2, col2, worker);
            } else {
                serverThread.sendToClient(new TryNewCoordinatesRequest(worker));
                serverThread.sendToClient(new BuildTwoInputRequest(worker));
            }
        } catch (NumberFormatException e) {
            serverThread.sendToClient(new TryNewCoordinatesRequest(worker));
            serverThread.sendToClient(new BuildTwoInputRequest(worker));
        }

    }

    /**
     * Request two inputs for a special move
     * @param moveTwoInputResponse
     * @
     */
    @Override
    public void visit(MoveTwoInputResponse moveTwoInputResponse) {
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
            if(row1 > 0 && row1 < 6 && row2 > 0 && row2 < 6 && col1 > 0 && col1 < 6 && col2 > 0 && col2 < 6 ) {
                view.timeToMoveTwoInput(row1, col1, row2, col2, worker);
            } else {
                serverThread.sendToClient(new TryNewCoordinatesRequest(worker));
                serverThread.sendToClient(new MoveTwoInputRequest(worker));
            }
        } catch (NumberFormatException e) {
            serverThread.sendToClient(new TryNewCoordinatesRequest(worker));
            serverThread.sendToClient(new MoveTwoInputRequest(worker));
        }
    }

    /**
     * Takes the number of player for the game that starts and sends it to the Model
     * @param playerThatStart number of the player that starts
     */
    @Override
    public void visit(PlayerThatStart playerThatStart) {
        int MaxPlayer = serverThread.getServer().getServerThreads().size();
        try {
            int player = Integer.parseInt(playerThatStart.getPlayer());
            if (player > 0 && player <= serverThread.getServer().getServerThreads().size()) {
                view.setFirstPlayer(player);
            } else {
                serverThread.sendToClient(new WorkerInputNotValid());
                serverThread.sendToClient(new SetFirstPlayer(playerThatStart.getOnlinePlayers()));
            }
        } catch (NumberFormatException e) {
            serverThread.sendToClient(new WorkerInputNotValid());
            serverThread.sendToClient(new SetFirstPlayer(playerThatStart.getOnlinePlayers()));
        }


    }

    /**
     * Client beat
     * @param beatUpdate message
     */
    @Override
    public void visit(BeatUpdate beatUpdate) {
        serverThread.getServer().serverBeatReceiver.receiveBeat(serverThread);
    }

    /**
     * Nickname chosen by the client
     * @param nicknameResponse
     * @
     */
    @Override
    public void visit(NicknameResponse nicknameResponse)  {

        boolean check = true;
        String nickname = nicknameResponse.getNickname();
        for (int i = 0; i < serverThread.getServer().getServerThreads().size(); i++) {
            int j = serverThread.getServer().getServerThreads().indexOf(view.getThread());
            if (i != j) {
                if (serverThread.getServer().getServerThreads().get(i).getView().getNickname() != null) {
                    if (serverThread.getServer().getServerThreads().get(i).getView().getNickname().equals(nickname)) {
                        check = false;
                    }
                }
            }
        }
        if(nickname.length()>19){
            serverThread.sendToClient(new tooLongName());
            serverThread.sendToClient(new NicknameRequest());
        } else if (check && nickname.length()<19) {
            view.AddingNickname(nickname);
            view.getThread().setNicknameSet(true);
        } else {
            serverThread.sendToClient(new NicknameNotValidUpdate());
            serverThread.sendToClient(new NicknameRequest());
        }
    }

}


