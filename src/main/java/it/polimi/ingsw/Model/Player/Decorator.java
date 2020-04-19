package it.polimi.ingsw.Model.Player;

public class Decorator {

    public Decorator() {
    }

    /*
    for (PlayerInterface onlinePlayer : onlinePlayers) {
      map.put("Apollo", ApolloList);
      map.get("Apollo").add(new SpecialMove_SwapWorkers(onlinePlayer));
      map.put("Artemis", ArtemisList);
      map.get("Artemis").add(new SpecialMove_MoveTwice(onlinePlayer));
      map.put("Athena", AthenaList);
      map.get("Athena").add(new SpecialOpponentTurn_LockMoveUp(onlinePlayer));
      map.put("Atlas", AtlasList);
      map.get("Atlas").add(new SpecialBuild_DomeAnyLevel(onlinePlayer));
      map.put("Demeter", DemeterList);
      map.get("Demeter").add(new SpecialBuild_BuildTwiceDifferent(onlinePlayer));
      map.put("Hephaestus", HephaestusList);
      map.get("Hephaestus").add(new SpecialBuild_BuildTwiceSame(onlinePlayer));
      map.put("Minotaur", MinotaurList);
      map.get("Minotaur").add(new SpecialMove_PushOpponent(onlinePlayer));
      map.put("Pan", PanList);
      map.get("Pan").add(new SpecialWin_MoveDown(onlinePlayer));
      map.put("Prometheus", PrometheusList);
      map.get("Prometheus").add(new SpecialWin_MoveDown(onlinePlayer)); //TODO: Creare classe per movimento e costruzione di prometeo
    }
     */
    public PlayerInterface Apollo (PlayerInterface player){
        return new SpecialMove_SwapWorkers(player);
    }

    public PlayerInterface Artemis(PlayerInterface player){
        return new SpecialMove_MoveTwice(player);
    }

    

}
