package com.example.calculatorapplication;

public class CalList {
    private String CalValue;
    private String Answer;

    public CalList(String CalValue,String Answer){
        this.CalValue=CalValue;
        this.Answer=Answer;
    }

    public String getvalue(){
        return CalValue;
    }

    public String getans(){
        return Answer;
    }
}
