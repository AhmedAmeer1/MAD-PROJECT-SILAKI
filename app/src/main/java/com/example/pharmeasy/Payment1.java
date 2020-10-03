package com.example.pharmeasy;

import java.util.Map;

public class Payment1 {

    private String id;
    private long cardnumber;
    private String cardname;
    private int expdate;
    private int cvv;

    public Payment1() {
    }

    public long getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(long cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public int getExpdate() {
        return expdate;
    }

    public void setExpdate(int expdate) {
        this.expdate = expdate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
