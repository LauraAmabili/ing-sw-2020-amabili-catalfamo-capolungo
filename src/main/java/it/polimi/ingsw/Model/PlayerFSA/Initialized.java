package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.PlayerCreator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import java.io.IOException;

public class Initialized extends PlayerFSA {

    PlayerInterface player;
    Game game;
    PlayerCreator p = new PlayerCreator();


    public Initialized(PlayerInterface player, Game game) {
        this.player = player;
        this.game = game;

    }

    /**
     * Add the God name on the availableGods. if godName is not present in the godListNames return an error message.
     * @param godName
     * @throws IOException
     */
    @Override
    public void chosenCard(String godName) throws IOException {
        boolean flag = true;
        if(game.getGodListNames().contains(godName)) {
            if(game.getAvailableGods().isEmpty()) {
                game.getAvailableGods().add(godName);
                game.getChosenGodList().add(p.find(godName));
            } else {
                for(String g : game.getAvailableGods()) {
                    if(g.equals(godName)) {
                        flag = false;
                        game.godNotAdded();
                        break;
                    }
                }
                if(flag) {
                    game.getAvailableGods().add(godName);
                    game.getChosenGodList().add(p.find(godName));
                    System.out.println(game.getChosenGodList());
                }
            }
            if(game.getAvailableGods().size() == game.getOnlinePlayers().size()) {
                game.godAdded(true);
                for (int i = 0; i < game.getOnlinePlayers().size(); i++) {
                    if (game.getNicknames().get(i).equals(player.getNickname())) {
                        game.getStateList().set(i, new Idle(player, this, game));
                        break;
                    }
                }
                //game.getCurrentTurn().nextTurn(game);
                game.setFirstPlayer();
                //game.toSetCard(); //faccio questo dopo che ho scelto il primo giocatore
            } else {
                game.godAdded(false);
            }
        } else {
            game.godNotAdded();
        }
    }

    /**
     * Set the next state.
     */
    @Override
    public void next() {
        for(int i = 0; i < game.getStateList().size(); i++) {
            if(game.getNicknames().get(i).equals(player.getNickname())) {
                game.getStateList().set(i, new SetCard(player, game));
                break;
            }
        }
    }

}
