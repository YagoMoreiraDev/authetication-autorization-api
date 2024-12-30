package com.yagocloud.autenticacaoautorizacao.domain.users;

public record RegisterUserDTO(String login, String password, UserRole role) {
}
