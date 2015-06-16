/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.openhousedemo.repository;

import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;
import org.motechproject.openhousedemo.domain.Subscriber;

/**
 *
 * @author liman
 */
public interface SubscriberDataService extends MotechDataService<Subscriber> {
    
    
    @Lookup
    Subscriber findByPhoneNumber(@LookupField(name = "phoneNumber") String phoneNumber);
    
    @Lookup
    Subscriber findByName(@LookupField(name = "name") String name);
    
    
}
