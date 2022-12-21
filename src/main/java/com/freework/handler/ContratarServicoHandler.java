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
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContratarServicoHandler implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse> {

    private static final Logger LOGGER = LogManager.getLogger(ContratarServicoHandler.class);
	private final ContratacoesService contratacoesService;

	public ContratarServicoHandler() {
		contratacoesService = new ContratacoesService();
	}

	@Override
	public ApiGatewayResponse handleRequest(ApiGatewayRequest request, Context context) {
		try{
			ContratacaoEntity contratacao = new ObjectMapper().convertValue(request.getBody(), ContratacaoEntity.class);
			contratacoesService.registrarContratacao(contratacao);
		}catch (Exception e) {
			LOGGER.error("Erro ao cadastrar serviço contratado. Erro: {}", e.getMessage());
			e.printStackTrace();
			return ApiGatewayResponse.builder()
					.setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
					.setObjectBody(e.getMessage())
					.build();
		}
		return ApiGatewayResponse.builder()
				.setStatusCode(HttpStatus.SC_OK)
				.setObjectBody("Registro de contratação de serviço salvo com sucesso.")
				.build();
	}
}
