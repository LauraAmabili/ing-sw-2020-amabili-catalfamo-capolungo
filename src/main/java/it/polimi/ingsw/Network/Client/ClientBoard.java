package it.polimi.ingsw.Network.Client;

public class ClientBoard {


    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private int[][] board = new int[5][5];

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void print(int nr, int nc)
    {
        int i, j;
        for(i=0; i<nr; i++)
        {
            for (j=0; j<nc; j++)
                System.out.print(board[i][j] + "  ");
            System.out.println();
        }
    }

    public void Martello(){

        System.out.println("                           ,\n" +
                "                       ,,    ,░░░░░,\n" +
                "                     ;░╠░░░░░░░░░░░░\n" +
                "                    ░░╬░░░░\"'  .░░░\n" +
                "                  ░╠╬░░░░     ░░░░\n" +
                "                ,╠╬╬░░░░    ¡░░░\n" +
                "               ░╠╬╬░░░░    ░░░░\n" +
                "              ;╠╬░░░░     ░╣▒▒╦╓\n" +
                "             ░░░░░░░    ;░╬▓▓▓▓▓▒▄╦,\n" +
                "           ;░░░░░░     ░╬▓▓╬╬╬╬▀▓▓▓▓▒╦░\n" +
                "          ░░╬░░░░    ;░░░╬╬╣╬╬╬╬╬╬╬▓▓▓▓▓╦░\n" +
                "          ░░╬░░░    ░░░░''░░╬╬╬╬╬╬╬░╬╬╬▓▓▓▓╬░,\n" +
                "           '░░░░░░;░░░░     '░░░╬╬╬╬╬╬╬╬╬╬▓▓▓▓╬╦,\n" +
                "              '░░░░░░           \"░╩╬╬╬╬╬╬╬╬╬╬▓▓▓▓▒▄╦,\n" +
                "                 ''                '░╙╩╬╬╬╬╬╬╬╬╬▓▓▓▓▒▄╦,\n" +
                "                                       ░░╩╬╬╬╬╬╬╬╬╬╬▓▓▓▓▒╦░\n" +
                "                                          \"░░╬╬╬╬╬╬╬╬╬╬▓▓▓▓▒▒░,\n" +
                "                                             '\"░╚╬▓╣╬╬╬╬╬╬╬▓▓▓▓▒░,\n" +
                "                                                 '░░╬╬▓╬╬╬╬╬╬╬▓▓▓▓▓╦,\n" +
                "                                                    '░░╬╬╬╬╬╬╬╬╬╬╬▓▓▓░\n" +
                "                                                        ░░╠╬╬╬╬╬╬╬╬╬░\n" +
                "                                                          '\"░░╬╬╬╬╬░'\n" +
                "                                                              '░░░░\n" +
                " "
        );
    }


    public void Players(){

         /*
        System.out.println(PURPLE_BOLD);

        System.out.println("\n" +
                " \n" +
                "                                                          ,ç▓\n" +
                "                                                  ,▄▓▓▓▓▓▄┘#\n" +
                "                        ╓▓▓▓▓▄▄  ▄,             ,▓▓▓▓▓▓▓▓▓\"\n" +
                "                      ,▓▓▓▓▓▓▓▓▓▌▓█Γ╙▄          ▓▓▓▓▓▓▓▓▓▓\n" +
                "                      ▓▓▓▓▓▓▓▓▓▓▓└▄╙¼ ▀▄  ,,╓,, ▓▓▓▓▓▓▓▓▓Γ\n" +
                "                      ▀▓▓▓▓▓▓▓▓▓▀  ▀▄\"╦\"▀▄     ▄▀▓▀▀▀▀▀Γ\n" +
                "                        ▀▀▀▓▀▀▀,     ▀▄▀▄▀▄  ▄▀   ^█∩\n" +
                "                          ,▓▓▀▓▌\"\"╙╨w▓▀▀▄▀▄█▌     ╓█Γ\n" +
                "                      ╓Φ▀▓▀   ▓▀▓     %▄¼▀▄▓▓▓¬ ▄▀ ▐Γ\n" +
                "                     ╟▄,▌Γ   ]▌  ▀▄    ╞Θ▌▐▓▀▓▌¬   ▐⌐\n" +
                "                       ╟▒    ╫⌐   ╙▓   ║Γ▌    ▀    ▐\n" +
                "                       ⌡▓    ▓      ▀▄╓▒           ▐\n" +
                "                        ▀▐½,▐▓▄     ╠▀∩╠Γ          ╟\n" +
                "                          ▀▀╬,╙╜╙Θ▐3Å╜Γ            ▓\n" +
                "                          ╓▀▐▌▄▄▄                  ▓\n" +
                "                        ▄▀ ╓▓    ▀▄            ╓┘\"^▀▀▄\n" +
                "                      #▀  ]▌      ▀▄           ▌     ╙▄\n" +
                "                    #Γ    ▐▌       ▀▌         ╫       ╙▄\n" +
                "                  #Γ       ▓        ▓         ▌        ╙▄\n" +
                "                           ▐▌       ▐▌        ▌         \"▌\n" +
                "                            ▌\n" +
                " ");

        System.out.println("                                         ,\n" +
                "                                                    ,, ,-Q▓\n" +
                "                                                 ▄▓▓▓▓▓▓Öƒ\n" +
                "                         ▄▓▓▓▓▓▓▓ ▓Mw           ▓▓▓▓▓▓▓▓▌\n" +
                "                        ▓▓▓▓▓▓▓▓▓▓▐\"¼ ▀µ       ▐▓▓▓▓▓▓▓▓Γ\n" +
                "                        ▓▓▓▓▓▓▓▓▓▌ ^¼└¼>▀▌Γ¬¬¬└▒▓▀▀▓▀▀▀\n" +
                "                         '▀▀▀▓▀▀Γ    └¼\"w╙▄  ▄Γ  `%▌\n" +
                "                           ,▄▓▀▀▓`²\"┴╬▓▓▌▓▄█▌    ,Φ▌\n" +
                "                        ÆΓ╓▀   ╟Γ▀µ ▓▓▓▓▓▓▓▓▓█ ▄╜▄⌐▌\n" +
                "                       ╙≈╗╠    ▌  ╙▓▓▓▓▓▓▓▓▓▓▓▄ ▀▌▌▌\n" +
                "                         ▀▌   ▐Γ    ▀▓▓▓▓▓▓▓▀ ⌠▌▐▐ Γ\n" +
                "                         ╙▓▄  ▓      ╝▌▓╒▌    ▐]▀▌]⌐\n" +
                "                           ╙╬▓▀▀me─ÿΘ▀Å^ ▌    ▌▓▀ ▐\n" +
                "                            ,Æ▓,Σ,^`   ▄\"▌▄  █▓▓▄⌐╟\n" +
                "                          ,╩¬▓▀   █▄▄ΦΓ  ▌ ▀ ╣▓▄\"Γ▀└▄\n" +
                "                        ╓▀  ▓      ▀▄    ▌    ╟     \"▄\n" +
                "                      ╓▀    ▓       ▀µ   ▌    ▌      \"▄\n" +
                "                    ▄▀      \"▌       ▓   ▌   ]─       '▄\n" +
                "                             ▓       ^ ▄┘ ╙▄\n" +
                "                                      ▌    ▐\n" +
                "                                     ▐      ▌\n" +
                "                                     ▓      ▓\n" +
                "                                     ▀      \"\n" +
                " ");


         */
        System.out.println(BLUE_BOLD);
        System.out.println("\n" +
                " \n" +
                " \n" +
                "                                                                , -W\n" +
                "                        ,▄▓▓▄Γ                   ╓▄▄▄ ▄      .▓▓▓▓▌\n" +
                "           ▓▓▓▓▓▓Ö┐    j▓▓▓▓▌                   ▐▓▓▓▓▓\\ⁿ/▄-~-▐▓▓▓▀\n" +
                "           ▀▓▓▓█ ^QÇ▌ .╜▀▓▀`                     └▀▓▓▄╓.hA\\,┘ `▌\n" +
                "            ╓╝╙▓^\"ΦÅò▒, ,Φ                      ,\"▀ ▐╙,▓▓▓▓▓Q⌐▓Γ\n" +
                "          └√Γ ▐ └⌐ │Γ▀▀⌐ ╞                       ╢  ▌  ▓▓▓▓▓╙Γ▓Γ\n" +
                "           └▄ ▌   ╗╢     ⌡                       ^Φ╗m,.╔╠^⌐ ]╠¬Γ\n" +
                "             ╠▌Γ=─^      Γ                        ,φ═─▄µ⌐ ▀ ▓▀L▄\n" +
                "           «`ƒ¬ ╙      f Γy                     ,` ▌  `▄    ▐   \\\n" +
                "         /`  ▌   ▐         t                  ,`   ▌   ▐    ⌠    \\\n" +
                "             ▐    \"                                ╙    /  b\n" +
                "                                                           ▐\n" +
                " \n" +
                " \n" +
                " \n" +
                " ");
        System.out.println(RESET);
        System.out.println();
    }

    public void Santorini(){

        System.out.println(BLUE);
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓╬▒░░╦@▓╬╬╬░░░░▓▓▓▓▓▀▀▀░░ .▓▓╩░╓Γ░▐▓▓░░▄╗▒wj▓\n" +
                "▓▓▓▓▓▓▓▀▓╝▀█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▀▓▓▓▓▓╬╬╬░░░░░░Å╟╫▒▒╬▓▓▓éQƒ▄ ░▓▓▌░ÿ╩ ╓▓Å╣▓▓▓╬░░╟╬\n" +
                "▓▓▓▓▓▓▓▀ ╒▓▓ ▀▓▓▓▓▓▓▓▓▓▓▓▓▓▀▀▓▓,`╙▀▀▀▀▀▓▓╫▓▓▓▓▓¬ ▐▌, '▀▓  ▓▓M▓▓╬▓█▒▒▓▓,,▓▓▓░░╠╬╣\n" +
                "▓▓▓▓▓▓▓▌ └▓▓▓▓▓▓▓▓  ╙▓▓ \"▓╓▓  ▓▓▓▌  ▓▓▓▓▓▄▄▄▀▓▓⌐ ▐▓▌  ▐▓▀Γ▓▓ └▓▀,  ▀▓▓⌐ ▐▓▌░▓▓░▌\n" +
                "▓▓▓▓▓▓▓▓▄  ▀▓▓▓▓▓─j  ▓▓∩ j▓▓  ▐▓▓▌  ▓▓Γ  , ▀▌ ▓µ j▓Γ ▄▓▓▌ ▐▓⌐ '@▓▌  ▓▓─ ▐▓▓▓▓▓▀░\n" +
                "▓▓▓▓▓▓▓▓▓▌  └▓▓▓▓ ▐▌ \"▓▌  ▓▓  j▓▓  j▓¬  ▓▓▓ ▓ ▐    ⁿ▓▓▓▓▌ ▐▓▌ j▓▓─  ▓▓⌐ ▐▓╬░░░░░\n" +
                "▓▓▓▓▓▓▀▀▓▓▓   ▀▓Γ ╓▄  ▀▓  ▓▓   ▓▓  ▐▌  ▐▌▀▀▐▌ ╫  j▓µ ▀▓▓▌ ▐▓▌  ▓▓   ▓▓  ▐▓░░░░░░\n" +
                "▓▓▓▓▓▌ ▐▓▓▓▓   ▓⌐ ▓▓µ  ▓⌐ ▐▓▌  ▀▓  ▐▌  ▐▓▄▒▓ ╓▓  j▓▓  ▐▓▌  ▓▌  ▓▓   ▓▓  ▐▓▒░░░░░\n" +
                "▓▓▓▓▓▓⌐ `▀▀▀   ▓  ▓▓▓⌐ ▐▓▄▓▓▓▓▓▓▌  ▐▓▓ç ╙▀▀ ▄▓▓▄,,▓▓▌  ▀▌▄▄▓▌  ▓▓▄  ╙▓ç ,▓▓░░░░░\n" +
                "▓▓▓▓▓▓▓▓▄▄µµ▄▄▓▓▓▒▓▓▓▓▒m▓▓▓▓▓▓▓▓▓▄µ▄▓▓▓▓▓▓▒▓▓▓▓▓▓▓▓▓▓╗▄▄▓▓▓▓▓▓▓▓▓▓▓m▒▓▓▓▓▓░░░░░░\n" +
                "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▀▀▀░░╬╬░░╨░╟▓▓▓▓╬╬╬▓▓▓╬╬╬▓▓▀▀░░░░░░░░░░");
        System.out.println(RESET);
    }



}
