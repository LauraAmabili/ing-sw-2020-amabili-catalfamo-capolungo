package it.polimi.ingsw.Helper;

import java.util.List;

public class GameConf {
    private static List<String> illegalUsernames = null;
    private static String godConf = "godConf.json";
    private static String serverConf = "serverConf.json";


    public static String getGodConf() {
        return godConf;
    }

    public static void setGodConf(String godConf) {
        GameConf.godConf = godConf;
    }

    public static String getServerConf() {
        return serverConf;
    }

    public static void setServerConf(String serverConf) {
        GameConf.serverConf = serverConf;
    }

    public static void setIllegalUsernames(List<String> illegalUsernames) {
        GameConf.illegalUsernames = illegalUsernames;
    }


    public static List<String> getIllegalUsernames() {
        return illegalUsernames;
    }
}
