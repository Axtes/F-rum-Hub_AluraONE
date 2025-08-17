package br.com.alura.Forum_Hub_Alura_ONE.infra.security;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "secrets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Secret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "token_secret", nullable = false)
    private String tokenSecret;
}
