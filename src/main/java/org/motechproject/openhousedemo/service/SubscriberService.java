/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.openhousedemo.service;

import java.util.List;
import org.motechproject.openhousedemo.domain.Subscriber;

/**
 *
 * @author liman
 */
public interface SubscriberService {
    
    void create(String name, String company,String phoneNumber);

    void add(Subscriber subscriber);

    Subscriber findSubscriberByName(String name);
    
    Subscriber findSubscriberByPhoneNumber(String phoneNumber);

    List<Subscriber> getSubscribers();

    void delete(Subscriber subscriber);

    void update(Subscriber subscriber);
    
}
