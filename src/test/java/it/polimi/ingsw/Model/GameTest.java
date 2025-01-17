package it.polimi.ingsw.Model;

import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Network.Server.Server;
import it.polimi.ingsw.Network.Server.ServerThread;
import it.polimi.ingsw.View.VirtualView;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameTest {

    final Game game = new Game();
    final VirtualView view1 = new VirtualView(new ServerThread(new Socket(), new Server(), 2, true));
    final GameController controller = new GameController();

    public GameTest() throws IOException {
    }


    @Before
    public void setUp() throws Exception {
        game.initialiseMatch(2);
        game.createTurn();
        view1.AddObserver(controller);
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
    public void delPlayer() {

        game.setStarted(true);
        game.setBoard(new Board());
        BoardCell b1 = game.getBoard().getGrid()[0][0];
        BoardCell b2 = game.getBoard().getGrid()[0][1];
        PlayerInterface player1 = game.getOnlinePlayers().get(0);
        player1.setNickname("Rob");
        PlayerInterface player2 = game.getOnlinePlayers().get(1);
        player2.setNickname("Laura");
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
   public void CorrectCards() throws IOException {


       game.getStateList().get(0).addNickname("Notateen");
       game.getStateList().get(1).addNickname("SuperRexo");
       game.chooseCards();
       for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
           game.getStateList().get(i).chosenCard("Apollo");
       }
       for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
           game.getStateList().get(i).chosenCard("Atlas");
       }
       Assert.assertNotNull(game.getAvailableGods());
       Assert.assertEquals(game.getAvailableGods().get(0), "Apollo");
       Assert.assertEquals(game.getAvailableGods().get(1), "Atlas");

   }




    @Test
    public void isGodNameSet() throws IOException {

        game.getStateList().get(0).addNickname("Notateen");
        game.getStateList().get(1).addNickname("SuperRexo");
        game.chooseCards();
        game.getStateList().get(0).chosenCard("Pan");
        game.getStateList().get(1).chosenCard("Athena");
        game.getCurrentTurn().nextTurn(game);
        game.getStateList().get(1).setCard("Pan");
        game.getStateList().get(0).setCard("Athena");
        Assert.assertEquals(game.getOnlinePlayers().get(1).getActiveCard().getGodName(), "Pan");
        Assert.assertNotEquals(game.getOnlinePlayers().get(1).getActiveCard().getGodName(), "Butterfly ");
    }




    @Test
    public void CardsNotChosenYet() throws IOException {

        game.getStateList().get(0).addNickname("Notateen");
        Assert.assertEquals(game.getAvailableGods().size(), 0 );
        game.chooseCards();
        game.createChallenger();
        Assert.assertNotNull(game.getCurrentTurn().getCurrentPlayer());
        game.notifyChoose(false, game.getGodListNames(), game.getCurrentTurn().getCurrentPlayer().getNickname());

    }



    @Test
    public void CheckCard(){
         game.getAvailableGods().add("Apollo");
         Assert.assertNotNull(game.getAvailableGods());
         Assert.assertEquals(game.getAvailableGods().get(0), "Apollo");
       game.getAvailableGods().add("Pan");
         Assert.assertEquals(game.getAvailableGods().get(1), "Pan");



    }

    @Test
    public void isWorkerAddedCorrectly() throws IOException {

        game.getOnlinePlayers().get(0).setNickname("Notateen");
        game.getOnlinePlayers().get(1).setNickname("SuperRexo");
        game.getStateList().get(0).addNickname("Notateen");
        game.getStateList().get(1).addNickname("SuperRexo");
        game.chooseCards();
        game.getStateList().get(0).chosenCard("Pan");
        game.getStateList().get(1).chosenCard("Athena");
        game.getCurrentTurn().nextTurn(game);
        game.getStateList().get(1).setCard("Pan");
        game.getStateList().get(0).setCard("Athena");
        game.getCurrentTurn().nextTurn(game);
        game.getStateList().get(1).placeWorker(1, 1, 0);
        Assert.assertNotNull(game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(0));
        Assert.assertEquals(game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(0).getCurCell().getRow(), 0);
        Assert.assertEquals(game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(0).getCurCell().getCol(), 0);
        game.getStateList().get(1).placeWorker(1, 2, 1);
        Assert.assertNotNull(game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(1));
        game.getCurrentTurn().nextTurn(game);
        game.getStateList().get(0).placeWorker(5, 5, 0);
        Assert.assertEquals(game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(0).getCurCell().getRow(), 4);
        Assert.assertEquals(game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(0).getCurCell().getCol(), 4);
        game.getStateList().get(0).placeWorker(5, 4, 1);
        Assert.assertNotNull(game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(0));
        Assert.assertNotNull(game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(1));

    }



    @Test
    public void isMovingAndBuildingCorrectly() throws IOException {

        game.getOnlinePlayers().get(0).setNickname("Notateen");
        game.getOnlinePlayers().get(1).setNickname("SuperRexo");
        game.getStateList().get(0).addNickname("Notateen");
        game.getStateList().get(1).addNickname("SuperRexo");
        game.chooseCards();
        game.getStateList().get(0).chosenCard("Pan");
        game.getStateList().get(1).chosenCard("Athena");
        game.getCurrentTurn().nextTurn(game);
        game.getStateList().get(1).setCard("Pan");
        game.getStateList().get(0).setCard("Athena");
        game.getCurrentTurn().nextTurn(game);
        game.getStateList().get(1).placeWorker(1, 1, 0);
        game.getStateList().get(1).placeWorker(1, 2, 1);
        game.getStateList().get(0).placeWorker(5, 5, 0);
        game.getStateList().get(0).placeWorker(5, 4, 1);
        game.getStateList().get(1).canIMove();
        game.getStateList().get(1).checkWorker(1, false);
        game.getStateList().get(1).move(2 , 2, 1);
        Assert.assertEquals(game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(0).getCurCell().getRow(),1);
        Assert.assertEquals(game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(0).getCurCell().getCol(), 1);
        game.getStateList().get(1).build(2, 3, 1);
        Assert.assertEquals(game.getBoard().getGrid()[1][2].getLevel(), 1);


    }




}