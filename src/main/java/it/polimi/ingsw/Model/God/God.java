package it.polimi.ingsw.Model.God;


import java.io.Serializable;
import java.util.List;

public class God implements Serializable {

    private String godName;
    private List<String> effects;
    private String descriptionTitle;
    private String descriptionStep;
    private String descriptionEffect;

    public String getDescriptionTitle() {
        return descriptionTitle;
    }

    public String getDescriptionEffect() {
        return descriptionEffect;
    }

    public God(String godName, List<String> effects, String descriptionTitle, String descriptionStep, String descriptionEffect) {
        this.godName = godName;
        this.effects = effects;
        this.descriptionTitle = descriptionTitle;
        this.descriptionStep = descriptionStep;
        this.descriptionEffect = descriptionEffect;
    }

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

