package br.com.alura.Forum_Hub_Alura_ONE.dto;

import br.com.alura.Forum_Hub_Alura_ONE.domain.users.Usuario;

public record UsuarioDTO(Long id, String nome, String email) {
    public UsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
