
package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.PlayerCreator;

import java.io.IOException;
import java.util.ArrayList;

public class GameController implements Observer {


    private final Game game = new Game();

    public GameController() {
    }

    public Game getGame() {
        return game;
    }

    /**
     * calls the method on game to create the match with the number of players chosen and create the turn
     *
     * @param numberOfPlayers number of player decided by the first client
     */
    @Override
    public synchronized void updateInitialiseMatch(int numberOfPlayers) {

        game.initialiseMatch(numberOfPlayers);
        game.createTurn();

    }

    /**
     * checks the player with the correct state and set the nickname chosen by the client
     *
     * @param nickname chosen nickname
     * @throws IOException Exception
     */
    @Override
    public synchronized void updateNickname(String nickname) throws IOException {

        for (int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if (game.getOnlinePlayers().get(i).getNickname() == null) {
                game.getStateList().get(i).addNickname(nickname);
                break;
            }
        }

    }

    /**
     * Calls the method to choose the cards by the challenger
     *
     * @throws IOException Exception
     */
    @Override
    public synchronized void updateChoosingCards() throws IOException {

        game.chooseCards();

    }

    /**
     * If the current player is choosing the cards calls the method to add the chosencard to the list
     *
     * @param in name of the chosen card
     * @throws IOException Exception
     */
    @Override
    public synchronized void updateTryThisCard(String in) throws IOException {

        for (int i = 0; i < game.getOnlinePlayers().size(); i++) {
            game.getStateList().get(i).chosenCard(in);
        }

    }

    /**
     * Calls the method if the player has the correct state to set the card chosen
     *
     * @param godName name of the god chosen by the current player
     * @throws IOException Exception
     */
    @Override
    public synchronized void updateSetGodName(String godName) throws IOException {

        for (int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if (game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                game.getStateList().get(i).setCard(godName);
                break;
            }
        }


    }

    /**
     * calls the method with the coordinates for the worker to set
     *
     * @param row    chosen row
     * @param col    chosen col
     * @param worker worker to be set
     * @throws IOException Exception
     */
    @Override
    public synchronized void updateAddingWorker(int row, int col, int worker) throws IOException {

        for (int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if (game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                game.getStateList().get(i).placeWorker(row, col, worker);
                break;
            }
        }

    }

    /**
     * After checking if the player is in the correct state, calling the build with the coordinates chosen
     *
     * @param row    chosen row
     * @param col    chosen col
     * @param worker worker just moved
     * @throws IOException Exception
     */
    @Override
    public synchronized void updateBuilding(int row, int col, int worker) throws IOException {


        for (int i = 0; i < game.getOnlinePlayers().size(); i++) {
            game.getStateList().get(i).build(row, col, worker);
        }

    }

    /**
     * After checking if the player is in the correct state, this check if the current player can move
     *
     * @throws IOException Exception
     */
    @Override
    public synchronized void updateStartMoving() throws IOException {

        for (int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if (game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                game.getStateList().get(i).canIMove();
                break;
            }
        }

    }


    /**
     * This method check if the player of the worker involved has the effect true
     *
     * @param effect effect boolean to set true if the client wants to use the effect
     * @param worker worker to apply the effect to
     * @throws IOException Exception
     */
    @Override
    public synchronized void updateTryThisWorkerEffect(boolean effect, int worker) throws IOException {

        for (int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if (game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                if (game.getCurrentTurn().getCurrentPlayer().isHasSpecialMove()) {
                    game.getStateList().get(i).checkWorker(worker, effect);
                } else {
                    game.getStateList().get(i).checkWorker(worker, false);
                }
                break;
            }
        }

    }

    /**
     * check if the current player in the correct state has the effect flag true
     *
     * @param effect   effect to check
     * @param nickname name of the current player
     * @param worker   worker to build around
     * @throws IOException Exception
     */
    @Override
    public synchronized void updatePlayerBuild(boolean effect, String nickname, int worker) throws IOException {
        for (int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if (game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                if (game.getCurrentTurn().getCurrentPlayer().isHasSpecialBuild()) {
                    game.getStateList().get(i).checkBuild(worker, effect);
                } else {
                    game.getStateList().get(i).checkBuild(worker, false);
                }
                break;
            }
        }
    }

    /**
     * check if the current player is the correct state and then move with the coordinates
     *
     * @param row1   first chosen row
     * @param col1   first chosen col
     * @param row2   second chosen row
     * @param col2   second chosen col
     * @param worker worker to move twice
     * @throws IOException exception for the message
     */
    @Override
    public synchronized void updateTimeToMoveTwoInput(int row1, int col1, int row2, int col2, int worker) throws IOException {
        for (int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if (game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                game.getStateList().get(i).move(row1, col1, row2, col2, worker);
                break;
            }
        }
    }

    /**
     * check if the current player is in the correct state and then build in the coordinates given
     *
     * @param row1   first chosen row
     * @param col1   first chosen col
     * @param row2   second chosen row
     * @param col2   second chosen col
     * @param worker worker that should build, but, you know, is just a game
     * @throws IOException exception for the message
     */
    @Override
    public synchronized void updateTimeToBuildTwoInput(int row1, int col1, int row2, int col2, int worker) throws IOException {
        for (int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if (game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                game.getStateList().get(i).build(row1, col1, row2, col2, worker);
                break;
            }
        }
    }

    /**
     * check the current player that is in the correct state and checks if the chosen worker can move
     *
     * @param worker number of the chosen worker
     * @throws IOException exception for the message
     */
    @Override
    public synchronized void updateTryThisWorker(int worker) throws IOException {

        for (int i = 0; i < game.getOnlinePlayers().size(); i++) {
            game.getStateList().get(i).checkWorker(worker, false);
        }

    }

    /**
     * checks if the current player is in the correct state and calls the move on the coordinates given
     *
     * @param row    chosen row
     * @param col    chosen col
     * @param worker numbero of the worker to move
     * @throws IOException exception for the message
     */
    @Override
    public synchronized void updateMoving(int row, int col, int worker) throws IOException {

        for (int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if (game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                game.getStateList().get(i).move(row, col, worker);
                break;
            }
        }


    }

    /**
     * calls the starting game in the model
     *
     * @throws IOException          exception for the message
     * @throws InterruptedException exception
     */
    @Override
    public synchronized void updateStartingGame() throws IOException, InterruptedException {
        game.notifyStartingGame();
    }

    /**
     * When the player lose his connection this delete his information in the game and sends a message to all the observers
     *
     * @param nickname name of the player that drop his connection
     */
    @Override
    public synchronized void updateDropConnection(String nickname) throws IOException {
        boolean state = false;
        for (int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if (nickname != null) {
                if (game.getOnlinePlayers().get(i).getNickname().equals(nickname)) {
                    if (game.getCurrentTurn().getCurrentPlayer().getNickname().equals(game.getOnlinePlayers().get(i).getNickname())) {
                        if (!game.isStarted() && game.getOnlinePlayers().size() > 2) {
                            state = true;
                            PlayerCreator playerCreator = new PlayerCreator();
                            game.setAllGods(playerCreator.getArrayGods());
                            game.initialiseGodList();
                            game.setChosenGodList(new ArrayList<>());
                            game.setAvailableGods(new ArrayList<>());
                        } else if(game.getOnlinePlayers().size() == 3) {
                            game.getCurrentTurn().nextTurn(game);
                        }
                    }
                    game.delPlayer(game.getOnlinePlayers().get(i));
                    if (state) {
                        game.createChallenger();
                        game.timeToChallenger();
                    }
                    if(game.isStarted() && game.getOnlinePlayers().size() == 1) {
                        game.updateWin(game.getOnlinePlayers().get(0));
                    }
                    break;
                }
            }
        }
    }


    /**
     * Sends to the model the name of the first player chosen to start the game
     * @param player name of the player
     * @throws IOException Exception
     */

    @Override
    public synchronized void updateFirstPlayer(int player) throws IOException {
        game.getCurrentTurn().firstTurn(player, game);
        game.toSetCard();
    }


}
