package it.polimi.ingsw.Model.God;

//To delete

public class God {

    private String godName;

    private boolean specialBuild_BuildTwiceDifferent;
    private boolean specialBuild_BuildTwiceSame;
	private boolean specialBuild_DomeAnyLevel;
	private boolean specialMove_BMB;
	private boolean specialMove_MoveTwice;
	private boolean specialMove_PushOpponent;
	private boolean specialMove_SwapWorkers;
	private boolean specialOpponentTurn_LockMoveUp;
    private boolean specialWin_MoveDown;
	
	
	
    //Constructor

    public God(String godName){
        this.godName = godName;
    }

    public God(String godName, boolean specialBuild_BuildTwiceDifferent, boolean specialBuild_BuildTwiceSame, boolean specialBuild_DomeAnyLevel, boolean specialMove_BMB, boolean specialMove_MoveTwice, boolean specialMove_PushOpponent, boolean specialMove_SwapWorkers, boolean specialOpponentTurn_LockMoveUp, boolean specialWin_MoveDown) {
        this.godName = godName;
        this.specialBuild_BuildTwiceDifferent = specialBuild_BuildTwiceDifferent;
        this.specialBuild_BuildTwiceSame = specialBuild_BuildTwiceSame;
        this.specialBuild_DomeAnyLevel = specialBuild_DomeAnyLevel;
        this.specialMove_BMB = specialMove_BMB;
        this.specialMove_MoveTwice = specialMove_MoveTwice;
        this.specialMove_PushOpponent = specialMove_PushOpponent;
        this.specialMove_SwapWorkers = specialMove_SwapWorkers;
        this.specialOpponentTurn_LockMoveUp = specialOpponentTurn_LockMoveUp;
        this.specialWin_MoveDown = specialWin_MoveDown;
    }


    //Getter & Setter

    public String getGodName() {
        return godName;
    }

    public void setGodName(String godName) {
        this.godName = godName;
    }

    public boolean isSpecialBuild_BuildTwiceDifferent() {
        return specialBuild_BuildTwiceDifferent;
    }

    public void setSpecialBuild_BuildTwiceDifferent(boolean specialBuild_BuildTwiceDifferent) {
        this.specialBuild_BuildTwiceDifferent = specialBuild_BuildTwiceDifferent;
    }
}

