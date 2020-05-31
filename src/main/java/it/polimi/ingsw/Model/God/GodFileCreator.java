/*package it.polimi.ingsw.Model.God;

import com.google.gson.*;
import it.polimi.ingsw.Helper.GameConf;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class GodFileCreator {

    final Gson gson = new Gson();
    final String godConf = GameConf.getGodConf();

    public void create() {
        ArrayList<God> arrayGods = new ArrayList<>();
        arrayGods.add(new God("Apollo", Arrays.asList("SpecialMove_SwapWorkers"), "God Of Music", "Your Move", "Your Worker may move into an opponent Worker’s space by forcing their Worker to the space yours just vacated."));
        arrayGods.add(new God("Artemis", Arrays.asList("SpecialMove_MoveTwice"), "Goddess of the Hunt", "Your Move", "Your Worker maymove one additional time, but not back to its initial space."));
        arrayGods.add(new God("Athena", Arrays.asList("SpecialOpponentTurn_LockMoveUp"), "Goddess of Wisdom", "Opponent’s Turn:", "If one of your Workers moved up on your last turn, opponent Workers cannot move up this turn. "));
        arrayGods.add(new God("Atlas", Arrays.asList("SpecialBuild_DomeAnyLevel"), "Titan Shouldering the Heavens", "Your Build", "Your Worker may build a dome at any level."));
        arrayGods.add(new God("Demeter", Arrays.asList("SpecialMove_SwapWorkers"), "Goddess of the Harvest", "Your Build", "Your Worker may build one additional time, but not on the same space."));
        arrayGods.add(new God("Hephaestus", Arrays.asList("SpecialBuild_BuildTwiceDifferent"), "God of Blacksmiths", "Your Build", "Your Worker may build one additional block(not dome)on top of your first block."));
        arrayGods.add(new God("Minotaur", Arrays.asList("SpecialMove_PushOpponent"), "Bull-headed Monster", "Your Move", "Your Worker may move into an opponent Worker’s space, if their Worker can be forced one space straight backwards to an unoccupied space at any level. "));
        arrayGods.add(new God("Pan", Arrays.asList("SpecialWin_MoveDown"), "God of the Wild", "Win Condition", "You also win if your Worker moves down two or more levels."));
        arrayGods.add(new God("Prometheus", Arrays.asList("SpecialMove_BMB"), "Titan Benefactor of Mankind", "Your Turn", "If your Worker does not move up, it may build both before and after moving"));

        write(arrayGods);
    }

    public void write(ArrayList<God> arrayGods) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(godConf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gson.toJson(arrayGods, fileWriter);
        try {
            assert fileWriter != null;
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GodFileCreator godFileCreator = new GodFileCreator();
        godFileCreator.create();
    }

}

 */