package org.motechproject.openhousedemo.web;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import org.motechproject.openhousedemo.service.SubscriberService;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.motechproject.messagecampaign.contract.*;
import org.motechproject.messagecampaign.service.MessageCampaignService;
import org.motechproject.openhousedemo.domain.Subscriber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller 
 */
@Controller
@RequestMapping("/web-api")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;
    
     @Autowired
    MessageCampaignService messageCampaignService;

    private static final String OK = "OK";

    @RequestMapping("/status")
    @ResponseBody
    public String status() {
        return OK;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity <String> registerSubscriber(HttpServletRequest request) {
        
        String name = request.getParameter("name");
        String company = request.getParameter("company");
        String phoneNumber = request.getParameter("phoneNumber");
        
        for (Enumeration e = request.getParameterNames();e.hasMoreElements();) {
            String param = (String) e.nextElement();
            System.out.println(param + ": " + request.getParameter(param));

        }
        
        System.out.println(String.format("Request Recieved /register?name=%s&company=%s&phoneNumber=%s", name, company, phoneNumber));
        List<String> errors = new ArrayList<>();
        Subscriber subscriber = null;
        try {
            subscriber = new Subscriber(name, company, phoneNumber);
            
            if (subscriberService.findSubscriberByPhoneNumber(phoneNumber) != null) {
                System.out.println("Already registered!");
                errors.add("Already registered!");
            }
        } catch (NullPointerException e) {
            System.out.println("Missing Parameters");
            errors.add("Missing parameters.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid parameters. "+e.getLocalizedMessage());
            errors.add("Invalid parameters.");
        }

        if (subscriber != null && errors.isEmpty()) {
            
            subscriberService.add(subscriber);
            
            try {
                
            CampaignRequest campaignRequest;
                campaignRequest = new CampaignRequest(phoneNumber, "OPEN_HOUSE_DEMO", new LocalDate(), null);
            
            messageCampaignService.enroll(campaignRequest);
            
            } catch (Exception e) {
            }
            
            System.out.println("Register successful");
            return new ResponseEntity<String>("success", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(StringUtils.join(errors, ", "), HttpStatus.OK);
        }
        
    }
}
