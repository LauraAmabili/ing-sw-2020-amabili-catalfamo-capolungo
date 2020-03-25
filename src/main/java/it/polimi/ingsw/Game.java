package it.polimi.ingsw;
import java.util.ArrayList;


public class Game {


        //Player
        private int id;
        private int playerRound; //player in turno
        private ArrayList<Player> activePlayers; //players online
        private Player challenger; //TODO : implementare scelta challenger

        //God
        private ArrayList<God> godChallengerList;

        public Game(ArrayList<Player> activePlayers) {

            this.activePlayers = activePlayers;

        }
        public int getid() {
            return id;
        }

        public void setid(int id) {
            this.id = id;
        }

        public int getPlayerRound() {
            return playerRound;
        }

        public void setPlayerRound(int playerRound) {
            this.playerRound = playerRound;
        }

        public Player getChallenger() {
            return challenger;
        }

        public void setChallenger(Player challenger) {
            this.challenger = challenger;
        }

        public void addPlayers(ArrayList<Player> activePlayers, Player player){
            activePlayers.add(player);
        }

        public void delPlayer(ArrayList <Player> activePlayers, Player player){
            activePlayers.remove(player);
        }

        //Aggiungo alla lista di divinità con cui giocare le divinità scelte dal challenger
        public void addGod(ArrayList<God> godChallengerList, God chosenGod){
            godChallengerList.add(chosenGod);
        }

        //ordina i giocatori
        public void OrderActivePlayers(){
        }

    }

