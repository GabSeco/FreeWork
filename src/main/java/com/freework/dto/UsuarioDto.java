package com.freework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private String id;
    private String documento;
    private String email;
    private String nome;
    private String senha;
    private String cidade;
    private String estado;
    private String cep;

}
