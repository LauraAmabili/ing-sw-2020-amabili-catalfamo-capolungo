
package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;

public class GameController implements Observer {


    private final Game game = new Game();

    public static final String PURPLE = "\033[0;35m";
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";



    public GameController() {
    }

    public Game getGame() {
        return game;
    }

    /**
     * calls the method on game to create the match with the number of players chosen and create the turn
     * @param numberOfPlayers number of player decided by the first client
     * @throws IOException Exception
     * @throws InterruptedException Exception
     */
    @Override
    public synchronized void updateInitialiseMatch(int numberOfPlayers) throws IOException, InterruptedException {

        game.initialiseMatch(numberOfPlayers);
        game.createTurn();

    }

    /**
     * checks the player with the correct state and set the nickname chosen by the client
     * @param nickname chosen nickname
     * @throws IOException Exception
     */
    @Override
    public synchronized void updateNickname(String nickname) throws IOException {

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if(game.getOnlinePlayers().get(i).getNickname() == null) {
                game.getStateList().get(i).addNickname(nickname);
                break;
            }
        }

    }

    /**
     * Calls the method to choose the cards by the challenger
     * @throws IOException Exception
     */
    @Override
    public synchronized void updateChoosingCards() throws IOException {

        game.chooseCards();

    }

    /**
     * If the current player is choosing the cards calls the method to add the chosencard to the list
     * @param in name of the chosen card
     * @throws IOException Exception
     */
    @Override
    public synchronized void updateTryThisCard(String in) throws IOException {

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            game.getStateList().get(i).chosenCard(in);
        }

    }

    /**
     * Calls the method if the player has the correct state to set the card chosen
     * @param godName name of the god chosen by the current player
     * @throws IOException Exception
     */
    @Override
    public synchronized void updateSetGodName(String godName) throws IOException {

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if(game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                game.getStateList().get(i).setCard(godName);
                break;
            }
        }


    }

    /**
     * calls the method with the coordinates for the worker to set
     * @param row chosen row
     * @param col chosen col
     * @param worker worker to be set
     * @throws IOException Exception
     */
    @Override
    public synchronized void updateAddingWorker(int row, int col, int worker) throws IOException {

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if(game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                game.getStateList().get(i).placeWorker(row, col, worker);
                break;
            }
        }

    }

    /**
     * After checking if the player is in the correct state, calling the build with the coordinates chosen
     * @param row chosen row
     * @param col chosen col
     * @param worker worker just moved
     * @throws IOException Exception
     */
    @Override
    public void updateBuilding(int row, int col, int worker) throws IOException {


        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            game.getStateList().get(i).build(row, col, worker);
        }

    }

    /**
     * After checking if the player is in the correct state, this check if the current player can move
     * @throws IOException Exception
     */
    @Override
    public void updateStartMoving() throws IOException {

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if(game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                game.getStateList().get(i).canIMove();
                break;
            }
        }

    }


    /**
     * This method check if the player of the worker involved has the effect true
     * @param effect effect boolean to set true if the client wants to use the effect
     * @param worker worker to apply the effect to
     * @throws IOException Exception
     */
    @Override
    public void updateTryThisWorkerEffect(boolean effect, int worker) throws IOException {

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if(game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                if(game.getCurrentTurn().getCurrentPlayer().isHasSpecialMove()) {
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
     * @param effect effect to check
     * @param nickname name of the current player
     * @param worker worker to build around
     * @throws IOException Exception
     */
    @Override
    public void updatePlayerBuild(boolean effect, String nickname, int worker) throws IOException {
        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if(game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                if(game.getCurrentTurn().getCurrentPlayer().isHasSpecialBuild()) {
                    game.getStateList().get(i).checkBuild(worker, effect);
                } else {
                    game.getStateList().get(i).checkBuild(worker, false);
                }
                break;
            }
        }
    }

    /**
     *
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     * @param worker
     * @throws IOException
     */
    @Override
    public void updateTimeToMoveTwoInput(int row1, int col1, int row2, int col2, int worker) throws IOException {
        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if(game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                game.getStateList().get(i).move(row1, col1, row2, col2, worker);
                break;
            }
        }
    }

    @Override
    public void updateTimeToBuildTwoInput(int row1, int col1, int row2, int col2, int worker) throws IOException {
        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if(game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                game.getStateList().get(i).build(row1, col1, row2, col2, worker);
                break;
            }
        }
    }

    @Override
    public void updateTryThisWorker(int worker) throws IOException {

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            game.getStateList().get(i).checkWorker(worker, false);
        }

    }

    @Override
    public void updateMoving(int row, int col, int worker) throws IOException {

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if(game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                game.getStateList().get(i).move(row, col, worker);
                break;
            }
        }



    }

    @Override
    public void updateStartingGame() throws IOException, InterruptedException {
        game.notifyStartingGame();
    }

    @Override
    public void updateDropConnection(String nickname) {
        for (PlayerInterface p : game.getOnlinePlayers()) {
            if(p.getNickname().equals(nickname)) {
                if(game.getCurrentTurn().getCurrentPlayer().getNickname().equals(p.getNickname())) {
                    //TODO: send to other players drop connection error
                    game.getCurrentTurn().nextTurn(game);
                }
                game.delPlayer(p);
            }
        }
    }


}
