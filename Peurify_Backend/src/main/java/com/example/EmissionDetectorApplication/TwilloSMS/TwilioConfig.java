package com.example.EmissionDetectorApplication.TwilloSMS;

import com.twilio.Twilio;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TwilioConfig implements InitializingBean {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String phoneNumber;

    @Override
    public void afterPropertiesSet() {
        Twilio.init(accountSid, authToken);  // Initialize the Twilio SDK after properties are set
    }
}
