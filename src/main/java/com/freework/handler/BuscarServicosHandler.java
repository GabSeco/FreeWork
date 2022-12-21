package com.freework.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freework.ApiGatewayRequest;
import com.freework.ApiGatewayResponse;
import com.freework.entity.ContratacaoEntity;
import com.freework.entity.UsuarioEntity;
import com.freework.service.ContratacoesService;
import com.freework.service.UsuarioService;

import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BuscarServicosHandler implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse> {

    private static final Logger LOGGER = LogManager.getLogger(AutenticarUsuarioHandler.class);
    private ContratacoesService contratacoesService;

    public BuscarServicosHandler() {
        contratacoesService = new ContratacoesService();
    }

    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest request, Context context) {
        LOGGER.info("Dados recebidos para busca de contratações: {}", request);
        List<ContratacaoEntity> contratacoes = null;
        try{
            if(request.getQueryParams().containsKey("email")) {
                contratacoes = contratacoesService.buscarContratacoes(request.getQueryParams().get("email"));
            }
            LOGGER.info("Contratações encontradas: {}", contratacoes);
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return ApiGatewayResponse.builder()
                    .setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .setObjectBody(e.getMessage())
                    .build();
        }
        return ApiGatewayResponse.builder()
                .setStatusCode(HttpStatus.SC_OK)
                .setObjectBody(contratacoes)
                .build();
    }

}