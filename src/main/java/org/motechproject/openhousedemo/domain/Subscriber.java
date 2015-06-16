/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.openhousedemo.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

/**
 *
 * @author liman
 */

@Entity
public class Subscriber {
    
    @Field
    private String name;
    
    @Field
    private String company;
    
    @Field(required = true)
    private String phoneNumber;

    public Subscriber() {
    }

    public Subscriber(String name, String company, String phoneNumber) {
        this.name = name;
        this.company = company;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Subscriber{" + "phoneNumber=" + phoneNumber + '}';
    }
    
    
        
}
