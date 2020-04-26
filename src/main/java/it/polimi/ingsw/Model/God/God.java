package it.polimi.ingsw.Model.God;

//To delete

import java.util.ArrayList;
import java.util.List;

public class God {

    private String godName;
    private List<String> Effects;

    //Constructor

    public God(String godName, List<String> effects) {
        this.godName = godName;
        Effects = effects;
    }

    //Getter & Setter


    public String getGodName() {
        return godName;
    }

    public void setGodName(String godName) {
        this.godName = godName;
    }

    public List<String> getEffects() {
        return Effects;
    }

    public void setEffects(List<String> effects) {
       Effects = effects;
    }
}

