package com.example.kahoot;

import javafx.stage.FileChooser;

import java.io.File;

public class Controller {

    public static File chooseFile(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter excelFilter = new FileChooser.ExtensionFilter("Excel Files", "*.xlsx", "*.xls");
        fileChooser.getExtensionFilters().add(excelFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        return selectedFile;
    }
}
