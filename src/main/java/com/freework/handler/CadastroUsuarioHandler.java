package com.freework.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freework.ApiGatewayRequest;
import com.freework.ApiGatewayResponse;
import com.freework.entity.UsuarioEntity;
import com.freework.service.UsuarioService;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CadastroUsuarioHandler implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse> {

	private static final Logger LOGGER = LogManager.getLogger(CadastroUsuarioHandler.class);
	private final UsuarioService usuarioService;

	public CadastroUsuarioHandler() {
		this.usuarioService = new UsuarioService();
	}

	@Override
	public ApiGatewayResponse handleRequest(ApiGatewayRequest request, Context context) {
		LOGGER.info("Dados recebidos para cadastro de usuário: {}", request);
		try{
			UsuarioEntity usuario = new ObjectMapper().convertValue(request.getBody(), UsuarioEntity.class);
			usuarioService.cadastrarUsuario(usuario);
		}catch (Exception e) {
			LOGGER.error("Erro ao cadastrar usuário. Erro: {}", e.getMessage());
			e.printStackTrace();
			return ApiGatewayResponse.builder()
					.setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
					.setObjectBody(e.getMessage())
					.build();
		}
		return ApiGatewayResponse.builder()
				.setStatusCode(HttpStatus.SC_OK)
				.setObjectBody("Usuário cadastrado com sucesso.")
				.build();
	}

}