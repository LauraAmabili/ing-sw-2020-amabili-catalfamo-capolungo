package it.polimi.ingsw;

public class Worker {

        private int idWorker;
        private Player playerWorker;
        private int location;

        public Worker(int idWorker, Player playerWorker) {
            this.idWorker = idWorker;
            this.playerWorker = playerWorker;
        }

        public int getIdWorker() {
            return idWorker;
        }

        public Player getPlayerWorker() {
            return playerWorker;
        }

        public void setIdWorker(int idWorker) {
            this.idWorker = idWorker;
        }

        public void setPlayerWorker(Player playerWorker) {
            this.playerWorker = playerWorker;
        }

        public int getLocation() {
            return location;
        }

        public void setLocation(int location) {
            this.location = location;
        }

}
