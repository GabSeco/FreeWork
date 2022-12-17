package com.freework.service;

import com.freework.dto.UsuarioDto;
import com.freework.entity.UsuarioEntity;

import com.freework.exception.BusinessException;
import com.freework.repository.UsuarioRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UsuarioService {

    private static final Logger LOGGER = LogManager.getLogger(UsuarioService.class);
    private final UsuarioRepository usuarioRepository;

    public UsuarioService() {
        usuarioRepository = new UsuarioRepository();
    }

    public void cadastrarUsuario(UsuarioEntity usuario) throws BusinessException {
        LOGGER.info("Salvando cadastro de usuário. Dados: {}", usuario);
        List<UsuarioEntity> usuarios = usuarioRepository.buscarUsuarioPorEmail(usuario.getEmail());

        if(!usuarios.isEmpty()) {
            throw new BusinessException("Usuário já existente.");
        }
        usuarioRepository.salvarCadastroUsuario(usuario);
    }

    public String autenticarUsuario(UsuarioDto usuario) throws BusinessException {
        LOGGER.info("Autenticando usuário. Dados: {}", usuario);
        List<UsuarioEntity> usuarios = usuarioRepository.buscarUsuarioPorEmail(usuario.getEmail());
        LOGGER.info("Usuário encontrado: {}", usuarios);
        if(dadosCorretosParaAutenticacao(usuarios, usuario)) {
            return "12323131";
        }
        throw new BusinessException("Dados de autenticação incorretos.");
    }

    public boolean dadosCorretosParaAutenticacao(List<UsuarioEntity> usuarios, UsuarioDto usuario) {
       return !usuarios.isEmpty() && usuarios.get(0).getSenha().equals(usuario.getSenha());
    }
}
