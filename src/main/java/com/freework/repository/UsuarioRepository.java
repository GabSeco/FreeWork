package com.freework.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.freework.config.DynamoConfig;
import com.freework.entity.UsuarioEntity;
import com.freework.exception.BusinessException;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public UsuarioRepository() {
        dynamoDBMapper = DynamoConfig.getDynamoDBMapper();
    }

    public void salvarCadastroUsuario(UsuarioEntity usuario) throws BusinessException {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":documento", new AttributeValue().withS(usuario.getDocumento()));
        eav.put(":email", new AttributeValue().withS(usuario.getEmail()));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("documento = :documento or email = :email")
                .withExpressionAttributeValues(eav);

        List<UsuarioEntity> usuarios = dynamoDBMapper.scan(UsuarioEntity.class, scanExpression);
        if(usuarios.isEmpty()) {
            dynamoDBMapper.save(usuario);
        } else {
            throw new BusinessException("Usuário já existente.");
        }
    }

}
