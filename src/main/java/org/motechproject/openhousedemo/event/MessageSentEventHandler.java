/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.openhousedemo.event;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.motechproject.event.MotechEvent;
import org.motechproject.event.listener.annotations.MotechListener;
import org.motechproject.messagecampaign.EventKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Liman
 */
@Component
public class MessageSentEventHandler {

    private static final Logger log = LoggerFactory.getLogger(MessageSentEventHandler.class);

    
    @MotechListener(subjects = {EventKeys.SEND_MESSAGE})
    public void handleAfterMsgSent(MotechEvent event) throws Exception {
        String campaignKey = (String) event.getParameters().get(EventKeys.CAMPAIGN_NAME_KEY);

        String msgKey = (String) event.getParameters().get(EventKeys.MESSAGE_KEY);
        String jobId = (String) event.getParameters().get(EventKeys.SCHEDULE_JOB_ID_KEY);
        String externalId = (String) event.getParameters().get(EventKeys.EXTERNAL_ID_KEY);
        System.out.println(String.format("message Details  msgkey %s externalId %s", msgKey, externalId));
        if (validateMsgKey(campaignKey)) {
          
            //send to api
            
            sendGet(externalId,msgKey);
            
        } else {
            log.warn("Not HandledHer :" + campaignKey);
        }
    }
    
    // HTTP GET request
	private void sendGet(String externalId,String msgKey) throws Exception {
 
		String url = "http://41.191.245.72/nymess/ivr.php?msisdn=" + externalId +  "&msg=" + msgKey ;
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
 
	}

    @MotechListener(subjects = {EventKeys.CAMPAIGN_COMPLETED})
    public void processCompletedCampaignEvent(MotechEvent event) {

        if (event.getParameters().get("CampaignName").toString().startsWith("mmnaija")) {
            log.info("Handling CAMPAIGN_COMPLETED event {}: message={} from campaign={} for externalId={}", event.getSubject(),
                    event.getParameters().get("MessageKey"), event.getParameters().get("CampaignName"), event.getParameters().get("ExternalID"));
            Map<String, Object> parametersMap = event.getParameters();
            String clientId = (String) parametersMap.get("ExternalID");
            String campaign = (String) parametersMap.get("CampaignName");
            

        }
    }

    public boolean validateMsgKey(String campaignKey) {
//        return (campaignKey.startsWith("mmnaija") && !campaignKey.contains("_SMS"));
        return (campaignKey.equalsIgnoreCase("OPEN_HOUSE_DEMO_IVR"));
    }
}