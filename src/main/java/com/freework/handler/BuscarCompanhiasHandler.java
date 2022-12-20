package com.freework.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freework.ApiGatewayRequest;
import com.freework.ApiGatewayResponse;
import com.freework.dto.UsuarioDto;
import com.freework.entity.CompanhiaEntity;
import com.freework.service.CompanhiaService;
import com.freework.service.UsuarioService;

import java.util.List;
import java.util.Objects;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BuscarCompanhiasHandler implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse> {

    private static final Logger LOGGER = LogManager.getLogger(BuscarCompanhiasHandler.class);
    private CompanhiaService companhiaService;

    public BuscarCompanhiasHandler() {
        companhiaService = new CompanhiaService();
    }

    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest request, Context context) {
        LOGGER.info("Dados recebidos para busca: {}", request);
        List<CompanhiaEntity> companhias;
        try{
            companhias = companhiaService.buscarCompanhias(request.getQueryParams());
            LOGGER.info("Companhias encontradas: {}", companhias);

            if(request.getQueryParams().containsKey("id")) {
                return ApiGatewayResponse.builder()
                .setStatusCode(HttpStatus.SC_OK)
                .setObjectBody(companhias.get(0))
                .build();
            }
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
                .setObjectBody(companhias)
                .build();
    }

}
