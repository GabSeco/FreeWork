package com.freework.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.freework.config.DynamoConfig;
import com.freework.entity.UsuarioEntity;
import com.freework.exception.BusinessException;

import java.util.HashMap;
import java.util.Map;

public class UsuarioRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public UsuarioRepository() {
        dynamoDBMapper = DynamoConfig.getDynamoDBMapper();
    }

    public void salvarCadastroUsuario(UsuarioEntity usuario) throws BusinessException {
        dynamoDBMapper.save(usuario);
    }

    public PaginatedQueryList<UsuarioEntity> buscarUsuarioPorEmail(String email) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":email", new AttributeValue().withS(email));

        DynamoDBQueryExpression<UsuarioEntity> queryExpression = new DynamoDBQueryExpression<UsuarioEntity>()
                .withIndexName("emailIndex")
                .withKeyConditionExpression("email = :email")
                .withConsistentRead(false)
                .withExpressionAttributeValues(eav);

        return dynamoDBMapper.query(UsuarioEntity.class, queryExpression);
    }

}
