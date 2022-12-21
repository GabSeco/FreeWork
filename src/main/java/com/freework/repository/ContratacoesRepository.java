package com.freework.repository;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.freework.config.DynamoConfig;
import com.freework.entity.CompanhiaEntity;
import com.freework.entity.ContratacaoEntity;
import com.freework.exception.BusinessException;

public class ContratacoesRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public ContratacoesRepository() {
        dynamoDBMapper = DynamoConfig.getDynamoDBMapper();
    }

    public void salvarContratacao(ContratacaoEntity contratacao) throws BusinessException {
        dynamoDBMapper.save(contratacao);
    }

    public PaginatedQueryList<ContratacaoEntity> buscarContratacoesPorEmail(String email) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":email", new AttributeValue().withS(email));

        DynamoDBQueryExpression<ContratacaoEntity> queryExpression = new DynamoDBQueryExpression<ContratacaoEntity>()
                .withIndexName("emailIndex")
                .withKeyConditionExpression("email = :email")
                .withConsistentRead(false)
                .withExpressionAttributeValues(eav);

        return dynamoDBMapper.query(ContratacaoEntity.class, queryExpression);
    }
}
