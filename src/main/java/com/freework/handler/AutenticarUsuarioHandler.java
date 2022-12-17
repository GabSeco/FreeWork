package com.freework.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freework.ApiGatewayRequest;
import com.freework.ApiGatewayResponse;
import com.freework.dto.UsuarioDto;
import com.freework.service.UsuarioService;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AutenticarUsuarioHandler implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse> {

    private static final Logger LOGGER = LogManager.getLogger(AutenticarUsuarioHandler.class);
    private UsuarioService usuarioService;

    public AutenticarUsuarioHandler() {
        usuarioService = new UsuarioService();
    }

    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest request, Context context) {
        LOGGER.info("Dados recebidos para login: {}", request);
        String token;
        try{
            UsuarioDto usuario = new ObjectMapper().convertValue(request.getBody(), UsuarioDto.class);
            token = usuarioService.autenticarUsuario(usuario);
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
                .setObjectBody(token)
                .build();
    }

}
