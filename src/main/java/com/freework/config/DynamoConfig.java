package com.freework.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.Objects;

public class DynamoConfig {

    private static AmazonDynamoDB amazonDynamoDB;

    public static DynamoDBMapper getDynamoDBMapper() {
        if(Objects.isNull(amazonDynamoDB)) {
            amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                    .withRegion(Regions.US_EAST_1)
                    .build();
        }
        return new DynamoDBMapper(amazonDynamoDB);
    }

}
