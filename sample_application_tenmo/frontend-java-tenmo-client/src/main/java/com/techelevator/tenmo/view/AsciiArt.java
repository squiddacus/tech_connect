package com.techelevator.tenmo.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AsciiArt {
    Map<String, List<String>> icons = new HashMap<>();
    public AsciiArt(String fileName) {
        loadIcons(fileName);
    }
    private void loadIcons(String fileName){
        File inputFile = new File(fileName);
        try (Scanner inputScanner = new Scanner(inputFile.getAbsoluteFile())){
            List<String> iconLines = new ArrayList<>();
            String currentIcon = null;
            while (inputScanner.hasNextLine()) {
                String line = inputScanner.nextLine();
                if (line.startsWith("#")) continue;
                if (line.startsWith("icon:")){
                    if (currentIcon != null) {
                        icons.put(currentIcon,iconLines);
                    }
                    currentIcon = line.substring(5);
                    iconLines = new ArrayList<>();
                    continue;
                }
                iconLines.add(line);
            }
            icons.put(currentIcon,iconLines);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> getIcon(String icon){
        return icons.get(icon);
    }
}
