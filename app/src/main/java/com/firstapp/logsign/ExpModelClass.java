package com.firstapp.logsign;

public class ExpModelClass {
    private String id,amount, date, note, expense_type,user_id;
    public ExpModelClass(){
    }

    public ExpModelClass(String id,String amount, String date, String note, String expense_type,String user_id) {
        this.id=id;
        this.amount = amount;
        this.date = date;
        this.note = note;
        this.expense_type = expense_type;
        this.user_id = user_id;
    }

    public String getAmount(){ return amount; }
    public void setAmount(String amount){ this.amount = amount; }
    public String getDate(){ return date; }
    public void setDate(String date){ this.date = date; }
    public String getNote(){ return note; }
    public void setNote(String note){ this.note = note; }
    public String getExpense_type(){ return expense_type; }
    public void setExpense_type(String expense_type){ this.expense_type = expense_type; }
}
