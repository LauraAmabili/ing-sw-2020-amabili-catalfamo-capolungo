package it.polimi.ingsw.Model;

import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Controller.Observable;
import it.polimi.ingsw.Controller.Observer;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.View.CLIView;
import it.polimi.ingsw.View.View;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.management.modelmbean.ModelMBean;
import java.util.ArrayList;
import java.util.List;

public class GameTest {

    Game game = new Game();
    CLIView view1 = new CLIView();
    GameController controller = new GameController();


    @Before
    public void setUp() throws Exception {
        game.initialiseMatch(2);
        game.createTurn();
        controller.addObserver(view1);
        view1.AddObserver(controller);
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void delPlayer() {

        BoardCell b1 = new BoardCell(0, 0);
        BoardCell b2 = new BoardCell(0, 1);
        PlayerInterface player1 = game.getOnlinePlayers().get(0);
        PlayerInterface player2 = game.getOnlinePlayers().get(1);
        List<PlayerInterface> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        Turn turn1 = new Turn(playerList);
        game.setCurrentTurn(turn1);
        b1.setWorker(player1.getWorkerRef().get(0));
        b2.setWorker(player1.getWorkerRef().get(1));
        player1.getWorkerRef().get(0).setCurCell(b1);
        player1.getWorkerRef().get(1).setCurCell(b2);
        game.delPlayer(game.getOnlinePlayers().get(0));
        Assert.assertEquals(game.getOnlinePlayers().get(0), player2);
        Assert.assertEquals(0, player1.getWorkerRef().size());
        Assert.assertNull(b1.getWorker());
        Assert.assertNull(b2.getWorker());
    }

    @Test
    public void initialiseMatch() {
        //game.initialiseMatch(2);
        Assert.assertNotNull(game.getOnlinePlayers().get(0));
        Assert.assertNotNull(game.getOnlinePlayers().get(1));
        Assert.assertNotNull(game.getOnlinePlayers().get(0).getWorkerRef().get(0));
        Assert.assertNotNull(game.getOnlinePlayers().get(0).getWorkerRef().get(1));
        Assert.assertNotNull(game.getOnlinePlayers().get(1).getWorkerRef().get(0));
        Assert.assertNotNull(game.getOnlinePlayers().get(1).getWorkerRef().get(1));
    }


    @Test
    public void addNickname(){
       // game.initialiseMatch(2);
        game.createTurn();
        game.addNickname("Notateen");
        game.notifyPlayerAdded("Notateen");
        //Assert.assertEquals(game.getCurrentTurn().getCurrentPlayer().getNickname(), view1.getCurrentPlayer().getNickname());
        game.getCurrentTurn().nextTurn();
        game.addNickname("SuperRexo");
        Assert.assertEquals(game.getOnlinePlayers().get(0).getNickname(), "Notateen");
        Assert.assertEquals(game.getOnlinePlayers().get(1).getNickname(), "SuperRexo");
    }

    @Test
    public void NicknameNotCorrect() {
        game.addNickname("Notateen");
        game.getCurrentTurn().nextTurn();
        game.addNickname("Notateen");
        Assert.assertNotEquals(game.getCurrentTurn().getCurrentPlayer().getNickname(), "Notateen");
    }

    @Test
    public void isGodNameSetted(){

        game.addNickname("Notateen");
        game.setGod("Apollo");
        Assert.assertSame(game.getCurrentTurn().getCurrentPlayer().getActiveCard().getGodName(), "Apollo");
        game.getCurrentTurn().nextTurn();
        game.addNickname("SuperRexo");
        game.setGod("Pan");
        Assert.assertSame(game.getCurrentTurn().getCurrentPlayer().getActiveCard().getGodName(), "Pan");
        Assert.assertNotEquals(game.getCurrentTurn().getCurrentPlayer().getActiveCard().getGodName(), "Apollo");

    }
    @Test
    public void checkUpdatesOfSettingGod(){

        game.addNickname("Notateen");
        game.setGod("Pan");
        Assert.assertEquals(game.getCurrentTurn().getActivePlayers().get(0).getActiveCard().getGodName(), "Pan");
        Assert.assertEquals(game.getOnlinePlayers().get(0).getActiveCard().getGodName(), "Pan");
        game.notifyGodSetted(game.getCurrentTurn().getCurrentPlayer(), "Pan");
        view1.updateGodSetted(game.getCurrentTurn().getCurrentPlayer(), "Pan");
        Assert.assertEquals(view1.getCurrentPlayer().getActiveCard().getGodName(), "Pan");
        Assert.assertEquals(view1.getCurrentPlayer().getActiveCard().getGodName(), "Pan");
        game.getCurrentTurn().nextTurn();
        game.addNickname("SuperRexo");
        game.setGod("Atlas");
        game.notifyGodSetted(game.getCurrentTurn().getCurrentPlayer(), "Atlas");
        view1.updateGodSetted(game.getCurrentTurn().getCurrentPlayer(), "Atlas");
        Assert.assertEquals(view1.getCurrentPlayer().getActiveCard().getGodName(), "Atlas");
        Assert.assertNotEquals(game.getCurrentTurn().getCurrentPlayer().getActiveCard().getGodName(), "Butterfly");
        Assert.assertEquals(game.getCurrentTurn().getCurrentPlayer().getActiveCard().getGodName(), "Atlas");

    }

    @Test
    public void CardsNotChosenYet(){

        game.addNickname("Notateen");
        Assert.assertEquals(game.getChosenGods().size(), 0 );
        game.chooseCards();
        game.createChallenger();
        Assert.assertNotNull(game.getCurrentTurn().getCurrentPlayer());
        game.notifyChoose(false, game.getGodListNames(), game.getCurrentTurn().getCurrentPlayer().getNickname());

    }

    @Test
    public void CheckCard(){
         game.checkAndAdd("Apollo");
         Assert.assertNotNull(game.getChosenGods());
         Assert.assertEquals(game.getChosenGods().get(0).getGodName(), "Apollo");
         game.checkAndAdd("Pan");
         Assert.assertEquals(game.getChosenGods().get(1).getGodName(), "Pan");

    }











    /*
    State state = State.ADDNICKNAME;
    assertEquals(game.getCurrentTurn().getCurrentPlayer().getNickname(), "Notateen");
     */

}