package com.techelevator.tenmo.model;


import java.util.Map;

public class LanguageResources {
    private Map<String, LanguageResource> languageResourceMap;
    private final String languageCode;


    public LanguageResources(String languageCode, Map<String, LanguageResource> languageResourceMap) {
        this.languageResourceMap = languageResourceMap;
        this.languageCode = languageCode;
    }


    public String getDisplay(String code){
        if (languageResourceMap.containsKey(code)) {
            return languageResourceMap.get(code).getDisplayMessage();
        }
        return "Missing language resource:" + code;
    }

    public String getLanguageCode() {
        return languageCode;
    }

}
