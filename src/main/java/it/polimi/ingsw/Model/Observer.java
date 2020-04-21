package it.polimi.ingsw.Model;

public interface Observer {

    public void update(Object obj);
    public void update(Object o, Object obj);
}
