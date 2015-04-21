package com.ahowe.autotext;

import java.util.Date;

/**
 * This is the Text class
 * Packages all the information needed for a text
 *
 * Created by jbruzek on 4/18/15.
 */
public class Text {

    private String text;
    private int number;
    private String contact;
    private long sendDate;
    private long submitDate;

    public Text(String text, int number, String contact, long sendDate, long submitDate) {
        this.text = text;
        this.number = number;
        this.contact = contact;
        this.sendDate = sendDate;
        this.submitDate = submitDate;
    }

    public String text() {
        return text;
    }

    public void text(String text) {
        this.text = text;
    }

    public int number() {
        return number;
    }

    public void number(int number) {
        this.number = number;
    }

    public String contact() {
        return contact;
    }

    public void contact(String contact) {
        this.contact = contact;
    }

    public long sendDate() {
        return sendDate;
    }

    public void sendDate(long sendDate) {
        this.sendDate = sendDate;
    }

    public long submitDate() {
        return submitDate;
    }

    public void submitDate(long submitDate) {
        this.submitDate = submitDate;
    }

    public boolean isSent() {
        //NOT IMPLEMENTED YET
        //return sendDate < currentDate
        return false;
    }
}
