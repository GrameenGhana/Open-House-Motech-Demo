/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.openhousedemo.service.impl;

import java.util.List;
import org.motechproject.openhousedemo.domain.Subscriber;
import org.motechproject.openhousedemo.repository.SubscriberDataService;
import org.motechproject.openhousedemo.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author liman
 */
@Service("subscriberService")
public class SubscriberServiceImpl implements SubscriberService{
    
    
    @Autowired
    private SubscriberDataService subscriberDataService;

    @Override
    public void create(String name, String company, String phoneNumber) {
        subscriberDataService.create(new Subscriber(name, company, phoneNumber));
    }

    @Override
    public void add(Subscriber subscriber) {
        subscriberDataService.create(subscriber);
    }

    @Override
    public Subscriber findSubscriberByName(String name) {
        Subscriber subscriber = subscriberDataService.findByName(name);
        if (null == subscriber) {
            return null;
        }
        return subscriber;
    }

    @Override
    public Subscriber findSubscriberByPhoneNumber(String phoneNumber) {
         Subscriber subscriber = subscriberDataService.findByPhoneNumber(phoneNumber);
        if (null == subscriber) {
            return null;
        }
        return subscriber;
    }

    @Override
    public List<Subscriber> getSubscribers() {
        return subscriberDataService.retrieveAll();
    }

    @Override
    public void delete(Subscriber subscriber) {
        subscriberDataService.delete(subscriber);
    }

    @Override
    public void update(Subscriber subscriber) {
        subscriberDataService.update(subscriber);
    }
    
}
