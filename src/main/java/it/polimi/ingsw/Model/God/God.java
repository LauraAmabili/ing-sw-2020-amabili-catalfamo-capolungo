package it.polimi.ingsw.Model.God;

//To delete

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class God implements Serializable {

    private String godName;
    private List<String> effects;

    //Constructor

    public God(String godName, List<String> effects) {
        this.godName = godName;
        this.effects = effects;
    }

    //Getter & Setter

    public God(String godName) {
        this.godName = godName;
    }

    public String getGodName() {
        return godName;
    }

    public void setGodName(String godName) {
        this.godName = godName;
    }

    public List<String> getEffects() {
        return effects;
    }

    public void setEffects(List<String> effects) {
        this.effects = effects;
    }
}

