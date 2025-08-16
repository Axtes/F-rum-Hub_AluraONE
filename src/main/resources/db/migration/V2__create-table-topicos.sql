create table topicos (

    id bigint not null auto_increment,
    titulo varchar(255) not null,
    mensagem text not null,
    data_criacao timestamp not null default current_timestamp,
    autor_id bigint not null,
    status varchar(50) not null,
    nome_curso varchar(255) not null,
    primary key (id),
    constraint fk_topicos_usuario foreign key (autor_id) references usuarios(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;