package com.freework.service;

import com.freework.ApiGatewayRequest;
import com.freework.entity.CompanhiaEntity;
import com.freework.handler.AutenticarUsuarioHandler;
import com.freework.repository.CompanhiaRepository;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CompanhiaService {

    private static final Logger LOGGER = LogManager.getLogger(CompanhiaService.class);
    private final CompanhiaRepository companhiaRepository;

    public CompanhiaService() {
        companhiaRepository = new CompanhiaRepository();
    }

    public List<CompanhiaEntity> buscarCompanhias(Map<String, String> requestParams) {
        return existeFiltroParaBusca(requestParams) ?
                companhiaRepository.buscarCompanhiasPorCategoria(requestParams.get("filter")) :
                companhiaRepository.buscarCompanhias();
    }

    public boolean existeFiltroParaBusca(Map<String, String> requestParams) {
        return requestParams.isEmpty();
    }
}