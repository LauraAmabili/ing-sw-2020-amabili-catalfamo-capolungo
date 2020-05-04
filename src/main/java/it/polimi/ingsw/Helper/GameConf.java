package it.polimi.ingsw.Helper;

import java.util.List;

public class GameConf {
    private static List<String> illegalUsernames = null;
    private static String godConf = "./src/main/java/it/polimi/ingsw/resources/godConf.json";
    private static String serverConf = "./src/main/java/it/polimi/ingsw/resources/serverConf.json";


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