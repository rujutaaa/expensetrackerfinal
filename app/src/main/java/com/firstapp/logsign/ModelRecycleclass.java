package com.firstapp.logsign;

public class ModelRecycleclass {

    private String textview1;
    private String textview2;
    private String textview3;

    ModelRecycleclass(String textview1, String textview2, String textview3){
        this.textview1=textview1;
        this.textview2=textview2;
        this.textview3=textview3;
    }

    public String getTextview1() {
        return textview1;
    }

    public String getTextview2() {
        return textview2;
    }

    public String getTextview3() {
        return textview3;
    }
}
