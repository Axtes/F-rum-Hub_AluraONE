package br.com.alura.Forum_Hub_Alura_ONE.infra.security;

import br.com.alura.Forum_Hub_Alura_ONE.domain.users.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecretRepository extends JpaRepository<Secret, Long> {
}
