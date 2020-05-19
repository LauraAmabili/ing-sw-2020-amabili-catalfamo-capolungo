package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.MessageFromClient.*;
import it.polimi.ingsw.Network.Message.MessageFromServer.*;
import it.polimi.ingsw.View.VirtualView;

import java.io.IOException;

public class VisitorMethodsServer implements VisitorServer {

    VirtualView view;
    ServerThread serverThread;

    public VisitorMethodsServer(VirtualView view, ServerThread serverThread) {
        this.view = view;
        this.serverThread = serverThread;

    }

    /**
     * receive the number of players
     * check if the format is correct
     * check if the number is available
     * otherwise the view is notified
     * @param playerNumberResponse
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void visit(PlayerNumberResponse playerNumberResponse) throws IOException, InterruptedException {

        String num = playerNumberResponse.getNumberOfPlayers();
        try {
            int numberOfPlayers = Integer.parseInt(num);
            if(numberOfPlayers == 3 || numberOfPlayers == 2){
                serverThread.setNumPlayers(numberOfPlayers);
                serverThread.setMaxPlrMsg(true);
                view.notifyNumberOfPlayer(numberOfPlayers);
                if (serverThread.getServer().getServerThreads().size() == numberOfPlayers) {
                    for (int i = 0; i < serverThread.getServer().getServerThreads().size(); i++) {
                        serverThread.getServer().getServerThreads().get(i).sendToClient(new NicknameRequest());
                    }
                }
            }
            else
            {
                serverThread.sendToClient(new NumberOfPlayerWrong());
               serverThread.sendToClient(new PlayerNumberRequest());

            }

        } catch (NumberFormatException e){

            serverThread.sendToClient(new NumberOfPlayerWrong());
            serverThread.sendToClient(new PlayerNumberRequest());

        }

    }

    /**
     * cards chosen by the challenger
     * @param chosenCardsUpdate
     * @throws IOException
     */
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
            serverThread.sendToClient(new WrongCoordinatesUpdate(worker));
        }


    }



    @Override
    public void visit(ChooseYourWorkerResponse chooseYourWorkerResponse) throws IOException {

        String worker  = chooseYourWorkerResponse.getWorker();
        try {
            int w = Integer.parseInt(worker);
            if(w<3) {
                view.tryThisWorker(w);
            } else {
                serverThread.sendToClient(new WorkerInputNotValid());
                serverThread.sendToClient(new ChooseYourWorkerRequest());
            }
        }
        catch (NumberFormatException e) {
            serverThread.sendToClient(new WorkerInputNotValid());
            serverThread.sendToClient(new ChooseYourWorkerRequest());
        }



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
            serverThread.sendToClient(new TryNewCoordinatesRequest(worker));
            serverThread.sendToClient(new MoveRequest(worker));
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
            serverThread.sendToClient(new TryNewCoordinatesRequest(worker));
            serverThread.sendToClient(new BuildRequest(worker));
        }

    }

    @Override
    public void visit(AskEffectReply askEffectReply) throws IOException {

        /*
        if(askEffectReply.getEffect()=="y" ) {
            boolean effect = askEffectReply.getEffect().equals("y");
            view.updateTimeToChooseWorkerEffect(effect);
        }
        else if(askEffectReply.getEffect()=="n"){


         */
        if( askEffectReply.getEffect().equals("y") || askEffectReply.getEffect().equals("n")){

            boolean effect = askEffectReply.getEffect().equals("y");
            view.updateTimeToChooseWorkerEffect(effect);

        } else {
            serverThread.sendToClient(new WorkerInputNotValid());
            view.updateAskForEffect(askEffectReply.getPlayerNickname());
        }


           // boolean effect = askEffectReply.getEffect().equals("y");
            //view.updateTimeToChooseWorkerEffect(effect);
            /*
        }
        else {
           // if(askEffectReply.getEffect().equals("y") || askEffectReply.getEffect().equalsIgnoreCase("yes")){
            serverThread.sendToClient(new WorkerInputNotValid());
            view.updateAskForEffect(askEffectReply.getPlayerNickname());
            //}
        }

             */


    }

    @Override
    public void visit(ChooseYourWorkerEffectResponse chooseYourWorkerEffectResponse) throws IOException, InterruptedException {

        boolean effect = chooseYourWorkerEffectResponse.isEffect();
        String worker  = chooseYourWorkerEffectResponse.getWorker();
        try {
            int w = Integer.parseInt(worker);
            if(w<3){
                view.tryThisWorkerEffect(effect,w);
            }
            else {
                serverThread.sendToClient(new WorkerInputNotValid());
                serverThread.sendToClient(new ChooseYourWorkerEffectRequest(effect));
            }

        }
        catch (NumberFormatException e) {
            serverThread.sendToClient(new WorkerInputNotValid());
            serverThread.sendToClient(new ChooseYourWorkerEffectRequest(effect));
        }


    }

    @Override
    public void visit(AskEffectBuildResponse askEffectBuildResponse) throws IOException {

            /*
            if(askEffectBuildResponse.getEffect()!="y" || askEffectBuildResponse.getEffect() != "n") {
                serverThread.sendToClient(new WorkerInputNotValid());
                view.updateAskForEffectBuild(askEffectBuildResponse.getPlayerNickname(), askEffectBuildResponse.getWorker());
            }
            else {


               if (askEffectBuildResponse.getEffect().equals("y") || askEffectBuildResponse.getEffect().equalsIgnoreCase("yes")) {

             */
            if( askEffectBuildResponse.getEffect().equals("y") || askEffectBuildResponse.getEffect().equals("n")){

                    boolean effect = askEffectBuildResponse.getEffect().equals("y");
                    view.updatePlayerBuild(effect, askEffectBuildResponse.getPlayerNickname(), askEffectBuildResponse.getWorker());

            } else {
                serverThread.sendToClient(new WorkerInputNotValid());
                view.updateAskForEffectBuild(askEffectBuildResponse.getPlayerNickname(), askEffectBuildResponse.getWorker());
            }

               // }



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
            serverThread.sendToClient(new TryNewCoordinatesRequest(worker));
            serverThread.sendToClient(new BuildTwoInputRequest(worker));
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
            serverThread.sendToClient(new TryNewCoordinatesRequest(worker));
            serverThread.sendToClient(new MoveTwoInputRequest(worker));
        }
    }

    @Override
    public void visit(BeatUpdate beatUpdate) {
        serverThread.getServer().serverBeatReceiver.receiveBeat(serverThread);
    }

    @Override
    public void visit(NicknameResponse nicknameResponse) throws IOException, InterruptedException {

        boolean check = false;
        String nickname = nicknameResponse.getNickname();
        for(int i = 0; i < serverThread.getServer().getServerThreads().size() - 1; i++) {
            if(serverThread.getServer().getServerThreads().get(i).getView().getNickname() == null) {
                view.AddingNickname(nickname);
                return;
            }
        }
        for (int i = 0; i < serverThread.getServer().getServerThreads().size() - 1; i++) {
            if (serverThread.getServer().getServerThreads().get(i).getView().getNickname().equals(nickname)) {
                check = true;
                break;
            }
        }
        if (!check) {
            view.AddingNickname(nickname);
        } else {
            serverThread.sendToClient(new NicknameNotValidUpdate());
            serverThread.sendToClient(new NicknameRequest());
        }
    }



}
