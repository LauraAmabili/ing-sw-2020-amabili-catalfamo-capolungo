package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;

public class AddNickname extends PlayerFSA {

    final PlayerInterface player;
    final Game game;
    public static final String PURPLE = "\033[0;35m";

    public AddNickname(PlayerInterface player, Game game) {
        this.player = player;
        this.game = game;
    }

    /**
     * Check if nickname is valid. If it's valid, name, is set on the player else send error message.
     * @param name
     * @throws IOException
     */
    @Override
    public void addNickname(String name) {
        if(game.getNicknames().size() == 0){
                game.getNicknames().add(name);
                player.setNickname(name);
                player.setColor(PURPLE);
                for (int i = 0; i < game.getStateList().size(); i++) {
                    if (game.getNicknames().get(i).equals(player.getNickname())) {
                        game.getStateList().set(i, new Idle(player, this, game));
                        break;
                    }
                }
                game.nameAccepted(name);

        } else {
            for (PlayerInterface p : game.getOnlinePlayers()) {
                if (p.getNickname().equals(name)) {
                    game.sameNameError(name);
                } else {
                    game.getNicknames().add(name);
                    player.setNickname(name);
                    for (int i = 0; i < game.getStateList().size(); i++) {
                        if (game.getNicknames().get(i).equals(player.getNickname())) {
                            game.getStateList().set(i, new Idle(player, this, game));
                            break;
                        }
                    }
                    game.nameAccepted(name);
                    if(game.getOnlinePlayers().size() == game.getNicknames().size()) {
                        game.createChallenger();
                        game.timeToChallenger();
                    }
                }
                break;
            }
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
