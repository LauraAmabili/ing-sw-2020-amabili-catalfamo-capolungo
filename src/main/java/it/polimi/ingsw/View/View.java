package it.polimi.ingsw.View;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Controller.*;
import it.polimi.ingsw.Model.Player.PlayerInterface;

import java.util.List;
import java.util.Scanner;


public class View implements Observer {


  Controller controller;
  Scanner input = new Scanner (System.in);



  public View(Controller controller){
    this.controller = controller;
  }

  @Override
  public void updateNickname() {
    System.out.println("Insert nickname: ");
    String name = input.nextLine();
    controller.setNickname(name);
    System.out.println(name + " accepted");
  }


  public void updateGame(){
    controller.initialiseMatch();
    System.out.println("Starting game");
  }

  @Override
  public void updateTurnCreated() {
    controller.setTurn();
    String player = controller.getTurn().getCurrentPlayer().getNickname();
    System.out.println(player + "Game Created");
    System.out.println("Turn Created");
  }




  @Override
  public void updateChooseGod(){
    System.out.println("Scegli la tua divinità");
    String name = input.nextLine();
    controller.setActiveCard(name);

  }

  public void updateToDecide(){
    System.out.println("il tuo God è stato decido");
  }



}




