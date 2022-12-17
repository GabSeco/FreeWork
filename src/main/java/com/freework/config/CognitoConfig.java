package com.freework.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;

public class CognitoConfig {

    public static AWSCognitoIdentityProvider getCognitoIdentityClient() {
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
    }

}
