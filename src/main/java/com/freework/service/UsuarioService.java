package com.freework.service;

import com.freework.entity.UsuarioEntity;

import com.freework.exception.BusinessException;
import com.freework.repository.UsuarioRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UsuarioService {

    private static final Logger LOGGER = LogManager.getLogger(UsuarioService.class);
    private final UsuarioRepository usuarioRepository;

    public UsuarioService() {
        usuarioRepository = new UsuarioRepository();
    }

    public void cadastrarUsuario(UsuarioEntity usuario) throws BusinessException {
        LOGGER.info("Salvando cadastro de usuário. Dados: {}", usuario);
        usuarioRepository.salvarCadastroUsuario(usuario);
    }

}
