package com.freework.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freework.ApiGatewayRequest;
import com.freework.ApiGatewayResponse;
import com.freework.dto.UsuarioDto;
import com.freework.service.FornecedoresService;
import com.freework.service.UsuarioService;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BuscarFornecedoresHandler implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse> {

    private static final Logger LOGGER = LogManager.getLogger(BuscarFornecedoresHandler.class);
    private FornecedoresService fornecedoresService;

    public BuscarFornecedoresHandler() {
        fornecedoresService = new FornecedoresService();
    }

    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest request, Context context) {
        LOGGER.info("Dados recebidos para busca: {}", request);
        String response = "";
        try{
            String filtro = request.getBody().get("filtro");
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
                .setObjectBody(response)
                .build();
    }

}
