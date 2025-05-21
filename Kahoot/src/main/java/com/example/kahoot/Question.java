package com.example.kahoot;

public class Question {
    private String question;
    private String ans1;
    private String ans2;
    private String ans3;
    private String ans4;
    private int right;
    private int time;

    public Question(String question, String ans1, String ans2, String ans3, String ans4, int right, int time) {
        this.question = question;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.right = right;
        this.time = time;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getAns4() {
        return ans4;
    }

    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }
}
