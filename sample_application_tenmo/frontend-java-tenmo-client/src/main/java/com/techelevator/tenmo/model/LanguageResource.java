package com.techelevator.tenmo.model;

public class LanguageResource {
    private String code;
    private String displayMessage;
    public LanguageResource(){}
    public LanguageResource(String code, String displayMessage) {
        this.code = code;
        this.displayMessage = displayMessage.replace("\\n","\n");
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public String getDisplayMessage(){
        return this.displayMessage.replace("\\n","\n");
    }
}
