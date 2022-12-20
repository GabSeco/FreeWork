package com.freework.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.freework.config.DynamoConfig;
import com.freework.entity.CompanhiaEntity;
import com.freework.exception.BusinessException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanhiaRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public CompanhiaRepository() {
        dynamoDBMapper = DynamoConfig.getDynamoDBMapper();
    }

    public PaginatedScanList<CompanhiaEntity> buscarCompanhias() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        scanExpression.addFilterCondition("category", 
        new Condition().withComparisonOperator(ComparisonOperator.NOT_NULL.toString()));

        return dynamoDBMapper.scan(CompanhiaEntity.class, scanExpression);
    }

    public List<CompanhiaEntity> buscarCompanhiaPeloId(String id) {
        return Arrays.asList(dynamoDBMapper.load(CompanhiaEntity.class, Integer.valueOf(id)));
    }

    public PaginatedQueryList<CompanhiaEntity> buscarCompanhiasPorCategoria(String categoria) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":category", new AttributeValue().withS(categoria));

        DynamoDBQueryExpression<CompanhiaEntity> queryExpression = new DynamoDBQueryExpression<CompanhiaEntity>()
                .withIndexName("categoryIndex")
                .withKeyConditionExpression("category = :category")
                .withConsistentRead(false)
                .withExpressionAttributeValues(eav);

        return dynamoDBMapper.query(CompanhiaEntity.class, queryExpression);
    }

}
