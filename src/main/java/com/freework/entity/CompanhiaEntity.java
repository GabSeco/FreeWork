package com.freework.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "companhias")
public class CompanhiaEntity {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private Integer id;

    @DynamoDBIndexHashKey(attributeName = "category", globalSecondaryIndexName = "categoryIndex")
    private String category;
    private String description;
    private String image;
    private String name;
    private String resume;

}