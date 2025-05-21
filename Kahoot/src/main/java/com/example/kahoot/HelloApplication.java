package com.example.kahoot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class HelloApplication extends Application {
    private static ArrayList<Question> Questions = new ArrayList<>();
    private static int currentIndex = 0;
    private static Timeline timeline;
    @Override
    public void start(Stage stage) throws IOException {

        Button chooseFile = new Button("choose File");

        Label question = new Label();
        Button ans1 = new Button();
        Button ans2 = new Button();
        Button ans3 = new Button();
        Button ans4 = new Button();

        ProgressBar progressBar = new ProgressBar(0);

        Button next = new Button("next");

        chooseFile.setOnAction(e->{
            File selectedFile = Controller.chooseFile();
            readExcelFile(selectedFile);
            showQuestion(question, ans1, ans2, ans3, ans4, progressBar);
        });

        ans1.setOnAction(e-> {
            timeline.stop();
            if (Questions.get(currentIndex).getRight() == 1){
                ans1.setStyle("-fx-background-color: green;");
                ans1.setDisable(true);
                ans2.setDisable(true);
                ans3.setDisable(true);
                ans4.setDisable(true);
            } else {
                ans1.setStyle("-fx-background-color: red;");
                ans1.setDisable(true);
                ans2.setDisable(true);
                ans3.setDisable(true);
                ans4.setDisable(true);
            }
        });

        ans2.setOnAction(e-> {
            timeline.stop();
            if (Questions.get(currentIndex).getRight() == 2){
                ans2.setStyle("-fx-background-color: green;");
                ans1.setDisable(true);
                ans2.setDisable(true);
                ans3.setDisable(true);
                ans4.setDisable(true);
            } else {
                ans2.setStyle("-fx-background-color: red;");
                ans1.setDisable(true);
                ans2.setDisable(true);
                ans3.setDisable(true);
                ans4.setDisable(true);
            }
        });

        ans3.setOnAction(e-> {
            timeline.stop();
            if (Questions.get(currentIndex).getRight() == 3){
                ans3.setStyle("-fx-background-color: green;");
                ans1.setDisable(true);
                ans2.setDisable(true);
                ans3.setDisable(true);
                ans4.setDisable(true);
            } else {
                ans3.setStyle("-fx-background-color: red;");
                ans1.setDisable(true);
                ans2.setDisable(true);
                ans3.setDisable(true);
                ans4.setDisable(true);
            }
        });

        ans4.setOnAction(e-> {
            timeline.stop();
            if (Questions.get(currentIndex).getRight() == 4){
                ans4.setStyle("-fx-background-color: green;");
                ans1.setDisable(true);
                ans2.setDisable(true);
                ans3.setDisable(true);
                ans4.setDisable(true);
            } else {
                ans4.setStyle("-fx-background-color: red;");
                ans1.setDisable(true);
                ans2.setDisable(true);
                ans3.setDisable(true);
                ans4.setDisable(true);
            }
        });

        next.setOnAction(e-> {
            next(ans1, ans2, ans3, ans4, question, progressBar);
        });


        VBox root = new VBox(20, chooseFile, question, ans1, ans2, ans3, ans4, progressBar, next);

        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Kahoot!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void readExcelFile(File file) {
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String a = row.getCell(0).getStringCellValue();
                String b = row.getCell(1).getStringCellValue();
                String c = row.getCell(2).getStringCellValue();
                String d = row.getCell(3).getStringCellValue();
                String e = row.getCell(4).getStringCellValue();
                int right = (int) row.getCell(5).getNumericCellValue();
                int time = (int) row.getCell(6).getNumericCellValue();

                Questions.add(new Question(a, b, c, d, e, right, time));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showQuestion(Label question, Button ans1, Button ans2, Button ans3, Button ans4, ProgressBar progressBar) {
        if (currentIndex >= Questions.size()) {
            question.setText("Конец викторины!");
            ans1.setDisable(true);
            ans2.setDisable(true);
            ans3.setDisable(true);
            ans4.setDisable(true);
            return;
        }

        Question q = Questions.get(currentIndex);
        question.setText(q.getQuestion());
        ans1.setText(q.getAns1());
        ans2.setText(q.getAns2());
        ans3.setText(q.getAns3());
        ans4.setText(q.getAns4());

        int totalSteps = q.getTime() * 10;
        final int[] stepCounter = {0};

        progressBar.setProgress(0);
        if (timeline != null) {
            timeline.stop();
        }

        timeline = new Timeline();
        timeline.setCycleCount(totalSteps);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {
            stepCounter[0]++;
            progressBar.setProgress((double) stepCounter[0] / totalSteps);

            if (stepCounter[0] >= totalSteps) {
                timeline.stop();
                ans1.setDisable(true);
                ans2.setDisable(true);
                ans3.setDisable(true);
                ans4.setDisable(true);
                if (Questions.get(currentIndex).getRight() == 1){
                    ans1.setStyle("-fx-background-color: green;");
                } else if(Questions.get(currentIndex).getRight() == 2){
                    ans2.setStyle("-fx-background-color: green;");
                } else if(Questions.get(currentIndex).getRight() == 3){
                    ans3.setStyle("-fx-background-color: green;");
                } else {
                    ans4.setStyle("-fx-background-color: green;");
                }
            }
        });

        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public static void next(Button ans1, Button ans2, Button ans3, Button ans4, Label question, ProgressBar progressBar) {
        ans1.setDisable(false);
        ans2.setDisable(false);
        ans3.setDisable(false);
        ans4.setDisable(false);
        ans1.setStyle(null);
        ans2.setStyle(null);
        ans3.setStyle(null);
        ans4.setStyle(null);

        currentIndex++;
        showQuestion(question, ans1, ans2, ans3, ans4, progressBar);
    }

}