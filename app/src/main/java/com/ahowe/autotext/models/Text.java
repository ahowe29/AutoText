package com.ahowe.autotext.models;

import java.util.Date;

/**
 * Data model for a text message
 *
 * Created by jbruzek on 6/14/15.
 */
public class Text {
    private long id;
    private String message;
    private Long creationDate;
    private Long sendDate;
    private Contact recipient;

    /**
     * Default constructor
     */
    public Text() {
        //nothing here
    }

    public boolean isSent() {
        //NOT IMPLEMENTED YET
        //return sendDate < currentDate
        return false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public Long getSendDate() {
        return sendDate;
    }

    public void setSendDate(Long sendDate) {
        this.sendDate = sendDate;
    }

    public Contact getRecipient() {
        return recipient;
    }

    public void setRecipient(Contact recipient) {
        this.recipient = recipient;
    }
}
