package it.polimi.ingsw.Model;

public interface Observer {

    public void update(Object obj);
    public void updatespecial(Object o, Object obj);
}
