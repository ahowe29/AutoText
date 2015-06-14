package com.ahowe.autotext.models;

/**
 * A contact data model. Holds the information of a contact.
 *
 * Created by jbruzek on 6/14/15.
 */
public class Contact {

    private String name;
    private String number;
    //image?

    /**
     * default constructor
     */
    public Contact() {
        //nothing here
    }

    /**
     * Constructor, initiaize the name and number
     * @param name
     * @param number
     */
    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    /**
     * Get the contact name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the contact name
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the contact number
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Set the contact number
     * @param number the number
     */
    public void setNumber(String number) {
        this.number = number;
    }
}
