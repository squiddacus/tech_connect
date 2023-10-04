package com.techelevator.tenmo.view;

public class AnsiColors {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    private static String valueWithReset(String color, String value, boolean reset) {
        return color + value + ((reset)?ANSI_RESET:"");
    }
    public static String blackValue(String value, boolean reset) {return valueWithReset(ANSI_BLACK,value,reset);}
    public static String redValue(String value, boolean reset) {return valueWithReset(ANSI_RED,value,reset);}
    public static String greenValue(String value, boolean reset) {return valueWithReset(ANSI_GREEN,value,reset);}
    public static String yellowValue(String value, boolean reset) {return valueWithReset(ANSI_YELLOW,value,reset);}
    public static String blueValue(String value, boolean reset) {return valueWithReset(ANSI_BLUE,value,reset);}
    public static String purpleValue(String value, boolean reset) {return valueWithReset(ANSI_PURPLE,value,reset);}
    public static String cyanValue(String value, boolean reset) {return valueWithReset(ANSI_CYAN,value,reset);}
    public static String whiteValue(String value, boolean reset) {return valueWithReset(ANSI_WHITE,value,reset);}

    public static String blackBgValue(String value, boolean reset) {return valueWithReset(ANSI_BLACK_BACKGROUND,value,reset);}
    public static String redBgValue(String value, boolean reset) {return valueWithReset(ANSI_RED_BACKGROUND,value,reset);}
    public static String greenBgValue(String value, boolean reset) {return valueWithReset(ANSI_GREEN_BACKGROUND,value,reset);}
    public static String yellowBgValue(String value, boolean reset) {return valueWithReset(ANSI_YELLOW_BACKGROUND,value,reset);}
    public static String blueBgValue(String value, boolean reset) {return valueWithReset(ANSI_BLUE_BACKGROUND,value,reset);}
    public static String purpleBgValue(String value, boolean reset) {return valueWithReset(ANSI_PURPLE_BACKGROUND,value,reset);}
    public static String cyanBgValue(String value, boolean reset) {return valueWithReset(ANSI_CYAN_BACKGROUND,value,reset);}
    public static String whiteBgValue(String value, boolean reset) {return valueWithReset(ANSI_WHITE_BACKGROUND,value,reset);}


    public static String replaceInLineValues(String value){
        value = value.replace("#black#",    ANSI_BLACK);
        value = value.replace("#blue#",     ANSI_BLUE);
        value = value.replace("#green#",    ANSI_GREEN);
        value = value.replace("#yellow#",   ANSI_YELLOW);
        value = value.replace("#cyan#",     ANSI_CYAN);
        value = value.replace("#red#",      ANSI_RED);
        value = value.replace("#purple#",   ANSI_PURPLE);
        value = value.replace("#white#",    ANSI_WHITE + ";1m");

        value = value.replace("#blackbg#",    ANSI_BLACK_BACKGROUND);
        value = value.replace("#bluebg#",     ANSI_BLUE_BACKGROUND);
        value = value.replace("#greenbg#",    ANSI_GREEN_BACKGROUND);
        value = value.replace("#yellowbg#",   ANSI_YELLOW_BACKGROUND);
        value = value.replace("#cyanbg#",     ANSI_CYAN_BACKGROUND);
        value = value.replace("#redbg#",      ANSI_RED_BACKGROUND);
        value = value.replace("#purplebg#",   ANSI_PURPLE_BACKGROUND);

        value = value.replace("#reset#",   ANSI_RESET);
        return value;
    }
}
