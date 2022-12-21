package com.freework.service;

import com.freework.dto.UsuarioDto;
import com.freework.entity.ContratacaoEntity;
import com.freework.entity.UsuarioEntity;

import com.freework.exception.BusinessException;
import com.freework.repository.ContratacoesRepository;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContratacoesService {
    
    private static final Logger LOGGER = LogManager.getLogger(ContratacoesService.class);
    private final ContratacoesRepository contratacoesRepository;

    public ContratacoesService() {
        contratacoesRepository = new ContratacoesRepository();
    }

    public void registrarContratacao(ContratacaoEntity contratacao) throws BusinessException {
        LOGGER.info("Salvando registro de contratação de serviço. Dados: {}", contratacao);
        contratacoesRepository.salvarContratacao(contratacao);
    }

    public List<ContratacaoEntity> buscarContratacoes(String email) {
        return contratacoesRepository.buscarContratacoesPorEmail(email);
    }

}
